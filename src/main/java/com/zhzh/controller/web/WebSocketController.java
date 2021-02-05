package com.zhzh.controller.web;

import com.zhzh.service.impl.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *webSocket的测试接口
 */
@Controller
@RequestMapping("/web")
public class WebSocketController {

    @Autowired
    WebSocketServer webSocketServer;

    /**
     * 发送单人消息
     *
     * @param userId 接收人用户ID
     * @param mes    消息
     * @return 1 发送成功  0 发送失败
     */
    @RequestMapping("/sendInfo")
    @ResponseBody
    public Integer sendInfo(@RequestParam("userId") String userId, @RequestParam("mes") String mes) {

        Integer num = webSocketServer.sendInfo(userId, mes);
        return num;
    }


    /**
     * 群发消息
     *
     * @param mes 消息
     * @return 1 发送成功  0 发送失败
     */
    @RequestMapping("/broadcast")
    @ResponseBody
    public void broadcast(@RequestParam String mes) {
        webSocketServer.broadcast(mes);
    }


    /**
     * 测试发送多条
     */
    @RequestMapping("/sendInfoFor")
    @ResponseBody
    public Integer sendInfo() {
        for (int i = 0; i < 100; i++) {
            webSocketServer.sendInfo(1 + "", i + "");
        }

        return 1;
    }

}

