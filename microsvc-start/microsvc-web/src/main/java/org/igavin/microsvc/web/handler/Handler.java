package org.igavin.microsvc.web.handler;

public abstract class Handler {

    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public Handler(Integer order){
        this.order = order;
    }

    public abstract RiskHandlerEnum process(String msg);
}