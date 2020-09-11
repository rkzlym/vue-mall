package com.example.mall.config;

import com.example.mall.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@ConfigurationProperties(prefix = "mall.jwt")
@Configuration
@Slf4j
public class JwtProperties {
    private String secret;

    private String pubKeyPath;

    private String priKeyPath;

    private Integer expire;

    private String cookieName;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    //对象一旦实例化后，就读取公钥和私钥
    @PostConstruct
    public void init() throws Exception{
        log.info("公钥路径\t {}", pubKeyPath);
        log.info("私钥路径\t {}", priKeyPath);
        // 公钥和私钥如果不存在，先生成
        File pubPath = new File(pubKeyPath);
        File priPath = new File(priKeyPath);
        if(!pubPath.exists() || !priPath.exists()){
            RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
        }
        // 存在则读取公钥和私钥
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }
}
