package com.jensuper.prc.mail.MailVo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/11/25
 */
@Data
public class MailVo {

    /**
     * 邮件id
     */
    private String id;
    /**
     * 邮件发送人
     */
    private String from;
    /**
     * 邮件接收人（多个邮箱则用逗号","隔开）
     */
    private String to;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String text;
    /**
     * 发送时间
     */
    private Date sentDate;
    /**
     * 抄送（多个邮箱则用逗号","隔开）
     */
    private String cc;
    /**
     * 密送（多个邮箱则用逗号","隔开）
     */
    private String bcc;
    /**
     * 状态
     */
    private String status;
    /**
     * 报错信息
     */
    private String error;

    /**
     * 邮件附件
     */
    @JsonIgnore
    private MultipartFile[] multipartFiles;

}
