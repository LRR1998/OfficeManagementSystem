package com.ujiuye.info.controller;

import com.ujiuye.info.bean.Email;
import com.ujiuye.info.service.EmailService;
import com.ujiuye.jobs.EmailJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping("/msg")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @RequestMapping(value = "send",method = RequestMethod.POST)
    public String sendEmail(Email email) throws SchedulerException {
        emailService.saveInfo(email);
        JobDetail job = JobBuilder.newJob(EmailJob.class).withIdentity("job1","group1").build();
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("email",email);
        jobDataMap.put("javaMailSenderImpl",javaMailSender);
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().startAt(new Date()).build();
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        jobDataMap.put("scheduler",sched);
        //为Scheduler对象新增JOB以及对应的SimpleTrigger
        sched.scheduleJob(job, trigger);
        sched.start();
        return "redirect:/message.jsp";
    }
}
