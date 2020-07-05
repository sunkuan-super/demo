package com.aspirecn.sk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Title: CityConfig
 * @Package: com.aspirecn.sk.config
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/22 - 17:40
 */
@Configuration
@ConfigurationProperties(prefix = "citys2")
@Data
public class CityConfig {
    private List<String> city;
}
