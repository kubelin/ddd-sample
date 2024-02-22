package com.mypetmanager.application.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mypetmanager.business.domain.owner.OwnerDomain;
import com.mypetmanager.business.domain.owner.OwnerFactory;
import com.mypetmanager.business.domain.owner.dto.OwnerDto;
import com.mypetmanager.global.config.HttpConfig.HttpServletRequestTextMapSetter;
import com.mypetmanager.integration.repository.owner.OwnerRepository;
import com.mypetmanager.integration.repository.pet.PetRepository;
import com.mypetmanager.integration.repository.pet.dto.PetResponseDto;
import com.mypetmanager.integration.repository.querydsl.AdvancedRepository;

import io.micrometer.observation.ObservationRegistry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class testController {
	// repository
	final OwnerRepository ownerRepo;
	final PetRepository petRepo;
	final AdvancedRepository adRepo;
	// factory
	final OwnerFactory ownerFactory;

	final OpenTelemetry otel;
	final OtlpHttpSpanExporter otelHttpSpanExporter;
	//	private RestTemplate icroRestTemplate;
	//	private ExtendedTracer tracer;

	@Qualifier("innerMicroRestTemplate")
	private RestTemplate innerMicroRestTemplate;

	private Tracer tracer; // OpenTelemetry Tracer 주입

	//	@Autowired
	//	private TextMapPropagator propagator; // OpenTelemetry TextMapPropagator 주입

	// Observability
	private ObservationRegistry observ;

	private final String LOCAL_URL = "http://localhost:8089";
	private final String BASE_URL = "http://localhost:9081";

	@RequestMapping("/owners/testM1")
	public void testMethod(HttpServletRequest request, @RequestHeader
	HttpHeaders headers) throws Exception {
		System.out.println("testM1");

		// 현재의 span 가져오기
		//OpenTelemetry otel = initOpenTelemetry();
		//Tracer trace = otel.getTracerProvider().get("mine");
		//Span mySpan = tracer.spanBuilder("haha2").setParent(Context.current()).startSpan();

		Context extractedContext = otel.getPropagators().getTextMapPropagator().extract(Context.current(), request,
			HttpServletRequestTextMapGetter.INSTANCE);

		log.info("current span {}", extractedContext);
		log.info("headers : \n {}", headers);

		ownerFactory.createDomain(1L);

	}

	@RequestMapping("/owners/testM2")
	public void testMethod2(HttpServletRequest response) throws Exception {
		log.trace("call testMethod2 : {}");
		Span test = Span.current();
		test.setAttribute("mymethod2 =>", "hahaha");
		log.info("current  span => {}", test);

		Enumeration<String> temp = response.getHeaderNames();

		while (temp.hasMoreElements()) {
			String headerName = temp.nextElement();
			System.out.println("while print");
			System.out.println(headerName);
			System.out.println(response.getHeader(headerName));
		}

		innerMicroRestTemplate.getForObject(LOCAL_URL + "/owners/testM1", String.class);

	}

	//	public void myWonderfulUseCase(ExtendedTracer tracer) {
	//		tracer.spanBuilder("calculate LLVM").startAndCall(this::calculateLlvm);
	//	}

	private String calculateLlvm() {
		Span.current().setAttribute("type", "smarter");
		//noinspection DataFlowIssue
		Span.current().setAttribute("foo", Baggage.current().getEntryValue("foo"));
		return "OK";
	}

	//	public void setBaggageAndRun(ExtendedTracer tracer) {
	//		try {
	//			Baggage.current().toBuilder()
	//				.put("foo", "bar")
	//				.build()
	//				.storeInContext(Context.current())
	//				.wrap(() -> tracer.spanBuilder("span with baggage").startAndCall(this::calculateLlvm))
	//				.call();
	//		} catch (Exception e) {
	//			throw new RuntimeException(e);
	//		}
	//	}

	public static class HttpServletRequestTextMapGetter implements TextMapGetter<HttpServletRequest> {

		public final static HttpServletRequestTextMapGetter INSTANCE = new HttpServletRequestTextMapGetter();

		@Override
		public Iterable<String> keys(HttpServletRequest carrier) {
			return Collections.list(carrier.getHeaderNames());
		}

		@Nullable
		@Override
		public String get(@Nullable
		HttpServletRequest carrier, String key) {
			return carrier.getHeader(key);
		}
	}

	@RequestMapping("/owners/get")
	public ResponseEntity<OwnerDto> getOwner(@RequestParam(value = "ownerId", defaultValue = "000")
	String ownerId, HttpServletResponse myRequest, HttpServletRequest request) throws Exception {
		//OpenTelemetry otel = initOpenTelemetry();
		//Tracer trace = otel.getTracerProvider().get("mine");
		Span mySpan = tracer.spanBuilder("haha").startSpan();

		OwnerDomain ownerDomain = null;
		List<OwnerDomain> ownerDomainList = null;
		OwnerDto resultDto = null;

		try (Scope ignored = mySpan.makeCurrent()) {

			// set tags
			mySpan.setAttribute("firstSpan", "hahahaha");
			Context contextWith = Context.current().with(mySpan);
			otel.getPropagators().getTextMapPropagator().inject(contextWith, myRequest,
				HttpServletRequestTextMapSetter.INSTANCE);

			//mySpan.makeCurrent();

			//		final ExtendedTracer tracer = ExtendedTracer.create(otel.getTracer("mmyymymym"));
			//		tracer.create(otel.getTracer("myTracer"));
			//		for (int i = 0; i < 5; i++) {
			//			myWonderfulUseCase(tracer);
			//		}
			//
			//		setBaggageAndRun(tracer);

			//		Observation currentOb = observ.getCurrentObservation();
			//var observation = Observation.createNotStarted("fetch-commit", registry).start();

			//Span test = Span.current();f
			//test.setAttribute("mymethod1 =>", "kkkkkkkkk");
			//logger.info("current  span => {}", test);

			log.info("controller myspan print =>> {} ", mySpan);

			innerMicroRestTemplate.getForObject(LOCAL_URL + "/owners/testM1", String.class);

			try {
				ownerDomain = ownerFactory.createDomain(1L);
				ownerDomainList = ownerFactory.createOwnerDomainList();

				OwnerDomain owner1 = ownerDomainList.get(1);
				log.info("ownerDomain : {}", owner1);
				//OwnerDomain owner2 = ownerDomainList.get(2);
				//System.out.printf(" owner2 name = %s \n %s \n", owner2.getOwnerId() );

				adRepo.findPetPageBySearchParams(1L, "", null);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (ownerDomain != null) {
				resultDto = ownerDomain.getOwnerInformation();
			}

			// call testM2
			innerMicroRestTemplate.getForObject(BASE_URL + "/owners/testM2", String.class);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			log.info("mySpan end : \n{}", mySpan);
			mySpan.end();
		}
		return new ResponseEntity<>(resultDto, HttpStatus.OK);
	}

	@RequestMapping("/owners/save")
	public ResponseEntity<OwnerDto> ping(@RequestParam(value = "name", defaultValue = "World")
	String name) {
		OwnerDto ownerDto = OwnerDto.builder()
			.name(name)
			.birthDate("20231125")
			.email("aaa@gmail.com")
			.address("세종시")
			.ownerId(12l)
			.build();

		if (ownerDto.getAddress() != null) {
			System.out.println("haha");
		}

		try {
			ownerRepo.saveOwner(ownerDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(ownerDto, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping("/owners/update")
	public ResponseEntity<OwnerDto> updateOwner(@RequestParam(value = "name", defaultValue = "World")
	String name) {
		OwnerDto ownerDto1 = null;
		OwnerDto ownerDto2 = null;

		try {
			OwnerDomain ownerDomain = ownerFactory.createDomain(1L);
			//		OwnerDomain ownerDomain2 = ownerFactory.createDomain(2L);
			//		OwnerDomain ownerDomain3 = ownerFactory.createDomain(3L);

			ownerDto1 = OwnerDto.builder().ownerId(4L)
				.address("구로구")
				.email("k22k@gmail.con")
				.phoneNumber("0104446666")
				.build();

			ownerRepo.updateOwner(ownerDto1);

			ownerDto2 = OwnerDto.builder().ownerId(5L)
				.name("도로토")
				.address("도봉구")
				.email("11@gmail.con")
				.phoneNumber("010242411")
				.updatedAt(new Timestamp(new Date().getTime()))
				.build();

			ownerRepo.updateOwner(ownerDto1);
			//		ownerDomain.setAddress("강남구");
			//		ownerDomain.setAddress("송파구");

			ownerRepo.updateOwner(ownerDto2);

			ownerRepo.updateOwner(new OwnerDto().builder().ownerId(6L).address("양천동").build());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>(ownerDto1, HttpStatus.OK);
	}

	@GetMapping("/owners/{ownerId}/pet")
	public ResponseEntity<PetResponseDto> getPetByOwnerId(@PathVariable
	Long ownerId) {
		System.out.println("ownerId() = " + ownerId);
		//	    Optional<Owner> ownerOptional = ownerRepo.findById(ownerId);
		//	    Optional<PetDomain> petOptional = petRepo.findById(ownerId);

		//	    System.out.println("petOptional() = " + petOptional.get().getPetName());

		//	    System.out.println("getName() = " + ownerOptional.get().getName());
		//	    Owner temp  = ownerOptional.get();

		//        if (!temp.getPets().isEmpty()) {
		//
		//            List<Pet> pets = temp.getPets();
		//            Pet myPet = pets.get(0);
		//            PetResponseDto responseDto = PetResponseDto.builder().breed(myPet.getBreed()).petName(myPet.getPetName()).build();
		//
		//            System.out.println("pet.getPetName() = " + responseDto );
		//
		//	        return new ResponseEntity<>(responseDto, HttpStatus.OK);
		//
		//	    } else {
		//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//	    }

		//	    PetDomain myPet = petOptional.get();
		//	    Owner myOnwer = myPet.getOwner();

		//	    System.out.println("myOnwer() = " + myOnwer.getEmail() );

		//	    PetResponseDto responseDto = PetResponseDto.builder().breed(myPet.getBreed()).petName(myPet.getPetName()).build();
		PetResponseDto responseDto = null;

		String d;

		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
