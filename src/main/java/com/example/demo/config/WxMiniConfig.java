package com.example.demo.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.example.demo.properties.WxMiniProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxMiniConfig {


    @Autowired
    private WxMiniProperties wxMiniProperties;


    @Bean
    public WxMaService wxMaService(){
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();

        config.setAppid(wxMiniProperties.getAppid());
        config.setSecret(wxMiniProperties.getSecret());
        config.setToken(wxMiniProperties.getToken());
        config.setAesKey(wxMiniProperties.getAesKey());
        config.setMsgDataFormat(wxMiniProperties.getMsgDataFormat());
        // 云环境
        config.setCloudEnv(wxMiniProperties.getCloudEnv());

        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);
        return wxMaService;
    }
}
