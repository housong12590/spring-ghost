package com.spring.ghost;

import com.spring.ghost.utils.DingTalkUtils;

import org.junit.Test;


public class HttpRequestTest {

    @Test
    public void test01() {
        DingTalkUtils.DingResponse resp = DingTalkUtils.with()
                .setMarkdown(new DingTalkUtils.Markdown().setTitle("Markdown").setText("*** 我的明天"))
                .build();
        System.out.println(resp.isSuccess());
        System.out.println(resp.getErrmsg());
    }
}
