package com.ghh.info.controller;

import com.ghh.commn.ResultEntity;
import com.ghh.info.bean.Email;
import com.ghh.info.service.EmailService;
import com.ghh.jobs.EmailJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("ema")
public class EmailController {
    @Resource
    private EmailService emailService;
    @Resource
    private JavaMailSenderImpl javaMailSender;

    //定时发送邮件任务，
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo (HttpSession session, MultipartFile files, Email email) throws Exception {
        //存储文件的地址
        String realPath = session.getServletContext().getRealPath("/upload");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String Filename = files.getOriginalFilename();
        String realFileName = UUID.randomUUID().toString().replaceAll("-","")+Filename;
        //文件存储的绝对路径
        String realFilePath = realPath+"+"+realFileName;
        files.transferTo(new File(realFilePath));
        //将存储的绝对路径放入数据库中
        email.setPath(realFilePath);

        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJob.class).build();
        //通过getJobDataMap方法将邮件信息，以及创建邮件的对象传递过去
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("email",email);
        jobDataMap.put("realFilePath",realFilePath);
        jobDataMap.put("javaMailSender",javaMailSender);
        //获取前台传回来的时间
//        Date startTime = email.getSendtime();
        //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).startAt(new Date()).build();
//                        withIntervalInSeconds(10).//重复间隔
//                        withRepeatCount(10)).//重复次数，一直重复的话设置repeatForever()
        //创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        jobDataMap.put("sched",sched);
        //为Scheduler对象新增JOB以及对应的SimpleTrigger
        sched.scheduleJob(job, trigger);
        //启动定时任务管理器,会根据你的设定的时间去执行job作业
        sched.start();
        emailService.saveInfo(email);
        return ResultEntity.success().put("message","发送成功");
    }
}
