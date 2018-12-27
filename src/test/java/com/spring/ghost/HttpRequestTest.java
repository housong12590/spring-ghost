package com.spring.ghost;

import com.spring.ghost.utils.DingTalkUtils;
import org.junit.Test;


public class HttpRequestTest {

    @Test
    public void test01() {
        boolean b = DingTalkUtils.sendLink("1231231", "asdfasdf", "http://www.baidu.com");
        System.out.println(b);
    }
}
