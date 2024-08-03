package com.example.demo.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.example.demo.model.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class TestController {


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

}
