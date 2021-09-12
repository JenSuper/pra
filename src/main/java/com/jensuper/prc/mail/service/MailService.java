package com.jensuper.prc.mail.service;

import com.jensuper.prc.mail.MailVo.MailVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/11/25
 */

@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     *
     * @param mailVo
     * @return
     */
    public MailVo sendMail(MailVo mailVo) {
        try {
            // 检查邮件内容
            this.checkMail(mailVo);
            // 发送邮件
            this.sendMimeMail(mailVo);
            // 保存邮件
            return saveMail(mailVo);
        } catch (Exception e) {
            //打印错误信息
            log.error("发送邮件失败:", e);
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }
    }

    /**
     * 检测邮件信息类
     *
     * @param mailVo
     */
    private void checkMail(MailVo mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * 构建复杂邮件信息类
     *
     * @param mailVo
     */
    private void sendMimeMail(MailVo mailVo) {
        try {
            //true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //邮件发信人从配置项读取
            mailVo.setFrom(getMailSendFrom());
            //邮件发信人
            messageHelper.setFrom(mailVo.getFrom());
            //邮件收信人
            messageHelper.setTo(mailVo.getTo().split(","));
            //邮件主题
            messageHelper.setSubject(mailVo.getSubject());
            //邮件内容
            messageHelper.setText(mailVo.getText());
            //抄送
            if (!StringUtils.isEmpty(mailVo.getCc())) {
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            //密送
            if (!StringUtils.isEmpty(mailVo.getBcc())) {
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            //添加邮件附件
            if (mailVo.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            //发送时间
            if (mailVo.getSentDate() != null) {
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            //正式发送邮件
            mailSender.send(messageHelper.getMimeMessage());
            mailVo.setStatus("ok");
            log.info("发送邮件成功：{}->{}", mailVo.getFrom(), mailVo.getTo());
        } catch (Exception e) {
            //发送失败
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存邮件
     *
     * @param mailVo
     * @return
     */
    private MailVo saveMail(MailVo mailVo) {
        //将邮件保存到数据库..
        log.info("saveMail TO db ");
        return mailVo;
    }

    /**
     * 获取邮件发信人
     *
     * @return
     */
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }
}
