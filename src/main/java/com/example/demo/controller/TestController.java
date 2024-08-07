package com.example.demo.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.example.demo.model.LoginRequest;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class TestController {

    @PostConstruct
    public void postConstruct() {
        log.info("TestController init");
            /**
             * 用以解决：javax.net.ssl.SSLHandshakeException: PKIX path building failed
             * 参考：https://www.cnblogs.com/cloudapps/p/5022544.html
             */
            String mpCert = ClassLoader.getSystemResource("wx-mp-jssecacerts").getPath();
            String maCert = ClassLoader.getSystemResource("wx-ma-jssecacerts").getPath();
            System.setProperty("javax.net.ssl.trustStore", mpCert + "," + maCert);
            String property = System.getProperty("javax.net.ssl.trustStore");
            System.out.println("javax.net.ssl.trustStore=" + property);

    }

    @Autowired
    private WxMaService wxMaService;

    @PostMapping("/wxLogin")
    public WxMaJscode2SessionResult wxLogin(@RequestBody LoginRequest request) {
        WxMaJscode2SessionResult result = null;
        try {
            result = wxMaService.jsCode2SessionInfo(request.getJsCode());
        } catch (WxErrorException e) {
            log.error("已知错误 error", e);
        } catch (Exception e) {
            log.error("未知错误 error", e);
            throw e;
        }
        log.info("result:{}", result);
        return result;
    }

    @PostMapping("/baidu")
    public void testBaidu() {
       try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpGet get = new HttpGet("https://www.baidu.com");
            CloseableHttpResponse response = httpClient.execute(get);
            log.info("百度 response:{}",response);
       }catch (Exception e){
           log.error("error",e);
       }
    }

}
