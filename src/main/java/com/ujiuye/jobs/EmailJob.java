package com.ujiuye.jobs;

import com.ujiuye.info.bean.Email;
import org.quartz.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) map.get("javaMailSenderImpl");
        Email email = (Email) map.get("email");
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String ename = email.getEname();
        String[] split = ename.split("#");
        Scheduler sched = (Scheduler) map.get("scheduler");
        try {
            helper.setFrom("l17805596938@163.com");
            helper.setTo(split[1]);
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent());
            javaMailSenderImpl.send(mimeMessage);
            sched.shutdown(true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        //发送邮件


        System.out.println("定时发送邮件！");
    }
}
