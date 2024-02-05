package com.mypetmanager.global.handler;

import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.ObservationHandler;
import io.opentelemetry.api.trace.Tracer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyObservationHandler implements ObservationHandler {
	private Tracer tracer;

	public MyObservationHandler(Tracer inputTracer) {
		this.tracer = inputTracer;
	}

	@Override
	public boolean supportsContext(Context context) {
		//throw new UnsupportedOperationException("Unimplemented method 'supportsContext'");
		log.info("\n ======== context supportsContext {} ", context);
		//		log.info("\n span = \n {}", Span.current());
		//		log.info("\n ======== tracer tracer {} ", tracer);

		//		context.setContextualName("fistContext");

		return true;
	}

	@Override
	public void onStart(Context context) {
		System.out.println("-=========== start ====== ");
		log.info("\n ======== onStart \n {} ", context);

		//		Span span = tracer.spanBuilder("myFirstSpan").startSpan();
		//		span.setStatus(StatusCode.OK);
		//		span.setAttribute("hwta?", "왓");
		//		span.end();

		ObservationHandler.super.onStart(context);

		System.out.println("-=========== end ====== ");

	}

	@Override
	public void onError(Context context) {
		ObservationHandler.super.onError(context);
	}

}
