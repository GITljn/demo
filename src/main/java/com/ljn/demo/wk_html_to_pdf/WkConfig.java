package com.ljn.demo.wk_html_to_pdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class WkConfig {
    private static final Logger logger = LoggerFactory.getLogger(WkConfig.class);

    @Value("${wk.image.storage}")
    private String imgStorage;

    @PostConstruct
    public void init() {
        File file = new File(imgStorage);
        if (!file.exists()) {
            file.mkdirs();
            logger.info("创建WK图片目录: " + imgStorage);
        }
    }
}
