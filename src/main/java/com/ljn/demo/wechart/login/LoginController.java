package com.ljn.demo.wechart.login;

import com.alibaba.fastjson.JSON;
import com.ljn.demo.utils.JwtUtil;
import com.ljn.demo.wechart.ConstantWxUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

// 返回的是请求的地址，因此不能使用RestController
@Controller
@RequestMapping("/wx")
public class LoginController {
    @GetMapping("/login")
    public String getWxCode() {
        // 微信开放平台授权baseUrl  %s代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        //对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置%s里面值
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu"
        );
        return "redirect:" + url;
    }
/*  存在没有的类，暂且注释掉
    @GetMapping("/callback")
    public String callback(String code, String state) {
        // System.out.println("code:"+code+"state:"+state);
        try {
            //1 获取code值，临时票据，类似于验证码
            //2 拿着code请求微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            //请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid
            //使用httpclient模拟浏览器发送请求，得到返回结果
            String accessTokenInfo = httpGet(accessTokenUrl, "utf-8");
            //从accessTokenInfo json字符串获取出来两个值 accsess_token 和 openid
            //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
            //将json字符串转换成map对象
            HashMap<String, String> map = JSON.parseObject(accessTokenInfo, HashMap.class);
            String accessToken = map.get("access_token");
            String openid = map.get("openid");
            UcenterMember member = memberService.getByOpenid(openid);
            if (member == null) {
                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        accessToken,
                        openid
                );
                //发送请求
                String userInfo = httpGet(userInfoUrl, "utf-8");
                HashMap<String, String> userInfoMap = JSON.parseObject(userInfo, HashMap.class);
                String nickname = userInfoMap.get("nickname");
                String headimgurl = userInfoMap.get("headimgurl");
                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }
            String token = JwtUtil.createJwtToken(member.getId(), member.getNickname());
            return "redirect:http://localhost:3000?token=" + token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "微信登录失败");
        }
    }

 */
    private String httpGet(String url, String encode) {
        // 创建客户端对象
        HttpClient client = HttpClients.createDefault();
        // 创建请求地址和请求方式，如果是post，就是HttpPost
        HttpGet get = new HttpGet(url);
        // 发起请求，得到响应对象
        HttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 从响应对象中得到响应体
        HttpEntity entity = response.getEntity();
        // 响应数据是基于http协议封装的对象，直接使用可能会出现乱码
        String entityString = null;
        try {
            entityString = EntityUtils.toString(entity, encode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entityString;
    }
}
