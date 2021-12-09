package org.igavin.microsvc.web.handler;

public class LimitPerDayHandler extends Handler {

    public LimitPerDayHandler(Integer order){
        super(order);
    }

    @Override
    public RiskHandlerEnum process(String msg) {
        Integer sendSuccessCount = 2;//TODO from db

        if(sendSuccessCount >= 2){
            System.out.println(msg+"防骚扰-今日已发送2条，风控STOP，更新发送状态");
            return RiskHandlerEnum.FAIL;
        }else {
            // 流转给下一个执行
            System.out.println(msg+"防骚扰-通过，风控NEXT。");
            return RiskHandlerEnum.NEXT;
        }
    }
}
