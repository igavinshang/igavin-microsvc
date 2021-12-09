package org.igavin.microsvc.web.handler;

public class BlacklistHandler extends Handler{

    public BlacklistHandler(Integer order){
        super(order);
    }

    @Override
    public RiskHandlerEnum process(String msg) {
        if(msg.equals("131")){
            System.out.println(msg+"在黑名单中，风控STOP，更新发送状态。");
            return RiskHandlerEnum.FAIL;
        }else {
            System.out.println(msg+"不在在黑名单中，风控NEXT。");
            return RiskHandlerEnum.NEXT;
        }
    }
}
