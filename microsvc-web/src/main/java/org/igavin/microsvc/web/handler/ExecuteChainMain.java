package org.igavin.microsvc.web.handler;

import org.apache.catalina.core.ApplicationFilterChain;

public class ExecuteChainMain {

    public static void main(String[] args) {

        // 构造责任链:
        HandlerChain chain = new HandlerChain()
                .addHandler(new WhitelistHandler(1))
                .addHandler(new BlacklistHandler(2))
                .addHandler(new LimitPerDayHandler(3));

        String mobile = "132";
        // 处理请求:
        RiskHandlerEnum isCanSendSms = chain.process(mobile);
        if(RiskHandlerEnum.SUCCESS.equals(isCanSendSms)){
            System.out.println(mobile+"发送成功");
        }


    }


}
