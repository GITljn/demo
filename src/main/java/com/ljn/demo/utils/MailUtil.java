package com.ljn.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtil {
    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendMail(String from, String to, String subject, String text, boolean html) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, html);
        mailSender.send(helper.getMimeMessage());
    }

    public void demo() {
        Context context = new Context();
        context.setVariable("email", "xxx@xxx.com");
        context.setVariable("url", "xxxx/activation/userId/activateCode");
        String content = templateEngine.process("/mail/activation", context);
        try {
            sendMail("from", "to", "subject", content, true);
        } catch (MessagingException e) {
            logger.error("发送邮件失败: " + e.getMessage());
        }
    }
}
