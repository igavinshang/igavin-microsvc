package org.igavin.microsvc.web.handler;

/**
 * 默认尾部handler，必须添加
 * */
public class DefaultTailHandler extends Handler{

    public DefaultTailHandler(Integer order){
        super(order);
    }

    @Override
    public RiskHandlerEnum process(String msg) {
        System.out.println(msg+"default last handler，success");
        return RiskHandlerEnum.SUCCESS;
    }
}
