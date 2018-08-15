package com.example.demo.config;

import com.example.demo.util.ftp.FtpClientFactory;
import com.example.demo.util.ftp.FtpClientPool;
import com.example.demo.util.ftp.FtpPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: FtpPool注入配置
 *
 * @auth guoshuai
 * @since 2018/8/14
 */
@Configuration
public class FtpConfig {

    @Bean
    public FtpClientPool initFtp(){
        // ftp配置
        FtpPoolConfig ftpPoolConfig = new FtpPoolConfig();
        // ftp服务器连接信息
        ftpPoolConfig.setHost("47.104.108.66");
        ftpPoolConfig.setPort(4950);
        ftpPoolConfig.setUsername("simon");
        ftpPoolConfig.setPassword("yongmin521");

        // ftp连接池配置信息

        // ftp链接工厂
        FtpClientFactory ftpClientFactory = new FtpClientFactory();
        ftpClientFactory.setFtpPoolConfig(ftpPoolConfig);
        return new FtpClientPool(ftpClientFactory);
    }

}
