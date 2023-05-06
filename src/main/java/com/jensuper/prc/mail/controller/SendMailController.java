package com.jensuper.prc.mail.controller;

import com.jensuper.prc.mail.MailVo.MailVo;
import com.jensuper.prc.mail.service.MailService;
import io.sentry.Sentry;
import io.sentry.protocol.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/11/25
 */
@Slf4j
@RestController
@RequestMapping(value = "/mail")
public class SendMailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件
     * @param mailVo
     * @param files
     * @return
     */
    @PostMapping("/send")
    public MailVo sendMailController(MailVo mailVo, MultipartFile[] files) {
        mailVo.setMultipartFiles(files);
        return mailService.sendMail(mailVo);
    }

    @PostMapping("/test")
    public void test(MailVo mailVo, MultipartFile[] files) {
        User user = new User();
        user.setEmail("test@163.com");
        Sentry.setUser(user);
        log.error("error");
        log.warn("warn");
        log.info("info");
        int a = 5/0;
    }
}
