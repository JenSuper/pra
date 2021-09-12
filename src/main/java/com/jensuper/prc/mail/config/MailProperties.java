package com.jensuper.prc.mail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/11/25
 */
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol = "smtp";
    private Charset defaultEncoding = DEFAULT_CHARSET;
    private Map<String, String> properties = new HashMap<>();
}
