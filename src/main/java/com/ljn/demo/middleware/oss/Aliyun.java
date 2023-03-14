package com.ljn.demo.middleware.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ljn.demo.utils.DateUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@RestController
@Data
@ConfigurationProperties(prefix = "oss")
public class Aliyun {

    // Endpoint填写Bucket所在地域对应的Endpoint。
    // 以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    String endpoint;
    String accessKeyId;
    String accessKeySecret;
    String bucketName;

    public static void main(String[] args) {
        File file = new File("C:\\Users\\ljn\\Pictures\\Saved Pictures\\gtq2.png");
        String url = testUploadFile(file);
        System.out.println(url);
    }

    // file必须前端表单的name属性一致
    public String uploadFile(MultipartFile file) {
        // TODO 判断文件类型和文件大小

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            // 获取文件名称
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();
            String datePath = DateUtil.formatDate(new Date());
            String filePath = datePath + "/" + filename;
            ossClient.putObject(bucketName, filePath, inputStream);

            // https://edu-ljn.oss-cn-chengdu.aliyuncs.com/01.png
            String url = "https://" + bucketName + "." + endpoint + "/" + filePath;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }
    }

    /**
     * 修改的地方
     * 1.File file
     * 2.inputStream = new FileInputStream(file);
     * 3.file.getName()
     * @param file
     * @return
     */
    public static String testUploadFile(File file) {
        // Endpoint填写Bucket所在地域对应的Endpoint。
        // 以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        // 不加https
        String endpoint = "";
        String accessKeyId = "";
        String accessKeySecret = "";
        String bucketName = "";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            // 获取文件名称
            String filename = UUID.randomUUID().toString().replaceAll("-", "")
                    + file.getName();
            String datePath = DateUtil.formatDate(new Date());
            String filePath = datePath + "/" + filename;
            ossClient.putObject(bucketName, filePath, inputStream);

            // https://edu-ljn.oss-cn-chengdu.aliyuncs.com/01.png
            String url = "https://" + bucketName + "." + endpoint + "/" + filePath;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }
    }
}
