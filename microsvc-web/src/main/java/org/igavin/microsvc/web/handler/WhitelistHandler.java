package org.igavin.microsvc.web.handler;

public class WhitelistHandler extends Handler{

    public WhitelistHandler(Integer order){
        super(order);
    }

    @Override
    public RiskHandlerEnum process(String msg) {
        if(msg.equals("5216")){
            System.out.println(msg+"在白名单中，风控STOP，直接发送。");
            return RiskHandlerEnum.SUCCESS;
        }else {
            //null 流转给下一个执行
            System.out.println(msg+"不在白名单中，风控NEXT。");
            return RiskHandlerEnum.NEXT;
        }
    }
}
