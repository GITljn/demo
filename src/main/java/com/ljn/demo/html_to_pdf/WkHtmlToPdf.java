package com.ljn.demo.html_to_pdf;

import org.junit.Test;

import java.io.IOException;

public class WkHtmlToPdf {
    @Test
    public void testHtmlToPdf() {
        String cmd = "d:/wkhtmltopdf/bin/wkhtmltopdf http://www.baidu.com e:/work/data/wk-pdfs/baidu.pdf";
        try {
            // 异步执行
            Runtime.getRuntime().exec(cmd);
            // 输出ok也不一定执行成功
            System.out.println("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHtmlToImg() {
        String cmd = "d:/wkhtmltopdf/bin/wkhtmltoimage http://www.baidu.com e:/work/data/wk-imgs/baidu.png";
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
