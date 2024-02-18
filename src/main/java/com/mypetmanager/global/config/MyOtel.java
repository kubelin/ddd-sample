package com.mypetmanager.global.config;

import org.springframework.beans.factory.annotation.Value;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.semconv.ResourceAttributes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class MyOtel {
	@Value("${otlp.tracing.endpoint}")
	public String url;
	public static OtlpHttpSpanExporter otelhttpExporter;

	public static OpenTelemetry initOpenTelemetry() throws Exception {
		log.info(">>>>>> MyOtel begin initOpenTelemetry initOpenTelemetry");
		// Export traces to Jaeger over OTLP
		// Span Exporter 생성
		OtlpHttpSpanExporter jaegerOtlpExporter = otelhttpExporter;

		Resource serviceNameResource = Resource
			.create(Attributes.of(ResourceAttributes.SERVICE_NAME, "otel-jaeger-example"));

		// Set to process the spans by the Jaeger Exporter
		SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
			.addSpanProcessor(BatchSpanProcessor.builder(jaegerOtlpExporter).build())
			.setResource(Resource.getDefault().merge(serviceNameResource))
			.build();
		OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).build();

		// it's always a good idea to shut down the SDK cleanly at JVM exit.
		Runtime.getRuntime().addShutdownHook(new Thread(tracerProvider::close));

		return openTelemetry;
	}

}
