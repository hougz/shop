package com.hgz.shop.controller;


import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("邮件发送")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @GetMapping
    public R<?> sendemail(){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        //发件人
        mailMessage.setFrom(from);
        //收件人
        mailMessage.setTo("2549794720@qq.com");
        //邮件主题
        mailMessage.setSubject("测试邮件");
        mailMessage.setText("你好");
        mailSender.send(mailMessage);
        return R.ok("200");
    }

}
