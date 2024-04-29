package com.mypetmanager.global.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mypetmanager.global.context.MiraeContext;
import com.mypetmanager.global.context.MiraeContextHolder;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.api.trace.TraceState;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Order(1)
public class ContextAspect {

    public void setCustomContext() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        SpanContext spanContext = Span.current().getSpanContext();
        String traceId = spanContext.getTraceId();
        String spanId = spanContext.getSpanId();
        TraceState traceState = spanContext.getTraceState();
        String channelId = "";
        // String channelId = request.getHeader(MiraeHeaders.CHANNEL_ID);

        log.debug("traceId :: {}", traceId);
        log.debug("spanId :: {}", spanId);

        MiraeContext miraeContext = MiraeContextHolder.get();
        miraeContext.setTraceId(traceId);
        miraeContext.setSpanId(spanId);
        miraeContext.setTraceState(traceState.asMap());
        miraeContext.setChannelId((StringUtils.hasText(channelId) ? channelId : ""));

    }

}
