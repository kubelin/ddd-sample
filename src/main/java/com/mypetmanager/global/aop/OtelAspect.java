package com.mypetmanager.global.aop;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mypetmanager.business.domain.owner.dto.OwnerDto;
import com.mypetmanager.global.config.HttpConfig.HttpServletRequestTextMapGetter;
import com.mypetmanager.global.config.HttpConfig.HttpServletRequestTextMapSetter;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class OtelAspect {

	//	private final Tracer tracer;
	private final OpenTelemetry otel;

	@Before("execution(* com.mypetmanager.application.controller.*.*(..))  && args(ownerId, response, request)")
	public void beforeCallController(JoinPoint jointPoint, String ownerId, HttpServletResponse response,
		HttpServletRequest request) {
		System.out.println("begin of beforeCallController");
		Tracer tracer = otel.getTracer("myTracer");
		Span mySpan = tracer.spanBuilder("aaaaab").startSpan();
		Context contextWith = Context.current().with(Span.wrap(mySpan.getSpanContext()));
		log.info("progation target context \n {}", contextWith);

		try (Scope ignored = mySpan.makeCurrent()) {

			log.info("jointPoint.getSourceLocation().toString() =>> {} ", jointPoint.getSourceLocation().toString());
			log.info("jointPoint Scope =>> {} ", ignored);
			log.info("mySpan\n {}", mySpan);
			log.info("mySpan.getSpanContext().getTraceId() =>> {} ", mySpan.getSpanContext().getTraceId());
			response.setHeader("mine1", "1111111");
			response.setHeader("mySpan", mySpan.getSpanContext().getTraceId());

			otel.getPropagators().getTextMapPropagator().inject(contextWith, response,
				HttpServletRequestTextMapSetter.INSTANCE);
		}

		log.info("1111111111 \n {}", mySpan);
		log.info("Span.fromContext(contextWith).getSpanContext().getTraceState() \n {}",
			Span.fromContext(contextWith).getSpanContext().getTraceState().isEmpty());
		log.info("current Span \n {}", Span.fromContext(contextWith));
		System.out.println("end of beforeCallController");
		mySpan.end();

	}

	private String calculateLlvm() {
		Span.current().setAttribute("type", "smarter");
		Span.current().setAttribute("foo", Baggage.current().getEntryValue("foo"));
		return "OK";
	}

	@AfterReturning(value = "execution(* com.mypetmanager.application.controller..*.*(..)) && args(ownerId, response, request)", returning = "responseEntity")
	public void writeSuccessLog(JoinPoint jointPoint, String ownerId, HttpServletResponse response,
		HttpServletRequest request,
		ResponseEntity<OwnerDto> responseEntity) throws RuntimeException {
		//logging
		Map<String, String> map = new HashMap<>();
		//returnValue 는 해당 메서드의 리턴객체를 그대로 가져올 수 있다.
		System.out.println("begin of writeSuccessLog");
		//Span mySpan = tracer.spanBuilder("aaaaa").startSpan();
		Tracer tracer = otel.getTracer("myTracer");
		Context extractedContext = otel.getPropagators().getTextMapPropagator().extract(Context.current(), response,
			HttpServletRequestTextMapGetter.INSTANCE);

		log.info("response header \n {}", response.getHeaderNames());
		log.info("response header \n {}", response.getHeader("mySpan"));
		log.info("response header \n {}", response.getHeader("traceparent"));
		log.info("extractedContext {}", request.getHeader("mySpan"));
		log.info("extractedContext Span \n {}", Span.fromContext(extractedContext).getSpanContext());

		Span asSpan = Span.fromContext(extractedContext);

		if (asSpan != null) {

			log.info("is span alive?  \n {}", asSpan);
			log.info("span Recording?  \n {}", asSpan.isRecording());
			asSpan.setAttribute("myspan?", "alvie?");

		}

		Span mySpan = tracer.spanBuilder("step2").setParent(extractedContext).startSpan();
		extractedContext.with(Span.wrap(mySpan.getSpanContext()));

		//		Span lastSpan = Span.fromContext(extractedContext);
		mySpan.setAttribute("istrueureurue??", false);
		mySpan.addEvent("End", Instant.now());

		mySpan.end(Instant.now());

		//		ExtendedTracer tracer = ExtendedTracer.create(otel.getTracer("myTracer"));
		//
		//		try {
		//			Baggage.current().toBuilder()
		//				.put("foo", "bar")
		//				.build()
		//				.storeInContext(Context.current())
		//				.wrap(() -> tracer.spanBuilder("returnspan").startAndCall(this::calculateLlvm))
		//				.call();
		//		} catch (Exception e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}

	//	@AfterThrowing(value = "execution(* com.mypetmanager.application.controller.*(..))", throwing = "exception")
	//	public void writeFailLog(JoinPoint joinPoint, Exception exception) throws RuntimeException {
	//		//logging
	//		//exception 으로 해당 메서드에서 발생한 예외를 가져올 수 있다.
	//	}

}
