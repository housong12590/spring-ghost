package com.spring.ghost.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

import java.util.Map;

public class DingTalkUtils {

    private static String serviceUrl;

    static {
        Map<String, String> envMap = System.getenv();
        String token = envMap.getOrDefault("DING_TOKEN", "9d6da20b7e3e596c660b5b6379a2e10f962b823d076c11bbfea3f393bfdcb1cd");
        serviceUrl = String.format("https://oapi.dingtalk.com/robot/send?access_token=%s", token);
    }

    public static boolean sendLink(String title, String msg, String messageUrl) {
        OapiRobotSendResponse resp = null;
        try {
            DingTalkClient client = new DefaultDingTalkClient(serviceUrl);
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(MsgType.link.getName());
            OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
            link.setMessageUrl(messageUrl);
            link.setPicUrl("");
            link.setTitle(title);
            link.setText(msg);
            request.setLink(link);
            resp = client.execute(request);
            if (resp.isSuccess()) {
                return true;
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        assert resp != null;
        throw new NullPointerException(resp.getErrmsg());
    }


    enum MsgType {
        text, link, markdown, actioncard, feedcard;

        public String getName() {
            return this.name().toLowerCase();
        }
    }
}
