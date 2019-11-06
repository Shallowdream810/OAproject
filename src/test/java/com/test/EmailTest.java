package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailTest {
    @Test
    public void test01() throws MessagingException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean-Email.xml");
        //获取JavaMailSender对象
        JavaMailSender bean = ac.getBean(JavaMailSenderImpl.class);
//        通过JavaMailSender获取MimeMessage对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        //设置multipart为true，用于将文件放入附件中
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        //发送邮件方
        helper.setFrom("guanyue0107@163.com");
        //收件方
        helper.setTo("lqc980123@163.com");
        //标题
        helper.setSubject("黑龙最骚");
        //内容
        helper.setText("真骚");
        //将图片当做附件去发送
        FileSystemResource fsr = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
//        放到内容中
        helper.addInline("1.jpg",fsr);
        //发送配置完成的邮件
        bean.send(mimeMessage);
        System.out.println("邮件发送成功");
    }
}
