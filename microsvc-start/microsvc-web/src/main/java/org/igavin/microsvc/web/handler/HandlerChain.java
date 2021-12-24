package org.igavin.microsvc.web.handler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HandlerChain {
    // 持有所有Handler:
    private List<Handler> handlers = new ArrayList<>();

    public HandlerChain addHandler(Handler handler) {
        this.handlers.add(handler);
        return this;
    }

    public RiskHandlerEnum process(String msg) {
        // 按照顺序调用每个Handler:
        RiskHandlerEnum result = RiskHandlerEnum.FAIL;
        handlers = handlers.stream()
                .sorted(Comparator.comparing(Handler::getOrder))
                .collect(Collectors.toList());

        handlers.add(new DefaultTailHandler(999999));

        for (Handler handler : handlers) {
            result = handler.process(msg);
            //如果不能往下执行，返回
            if (result != RiskHandlerEnum.NEXT) {
                return result;
            }
        }

        throw new RuntimeException("HandlerChain Could not handle request: " + msg);
    }
}