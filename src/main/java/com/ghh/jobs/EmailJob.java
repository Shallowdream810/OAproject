package com.ghh.jobs;

import com.ghh.info.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //此方法得到的map与控制层是同一个map对象
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        //获取email对象以及创建邮件的对象
        Email email = (Email)dataMap.get("email");
        //获取文件信息
        try {
        JavaMailSenderImpl javaMailSender =(JavaMailSenderImpl)dataMap.get("javaMailSender");
        Scheduler sched = (Scheduler)dataMap.get("sched");
            String realFilePath =(String)dataMap.get("realFilePath");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //发送方
            helper.setFrom("guanyue0107@163.com");
            //接收方
            helper.setTo(email.getEname());
            //标题
            helper.setSubject(email.getTitle());
            //内容
            helper.setText(email.getContent());
            //附件形式发送
            File file = new File(realFilePath);
            FileSystemResource fsr = new FileSystemResource(file);
            helper.addInline("1.jpg",fsr);
            javaMailSender.send(mimeMessage);
            sched.standby();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
