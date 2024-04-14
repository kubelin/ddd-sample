package com.mypetmanager.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypetmanager.application.service.SampleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class SampleController {
	// service
	private SampleService sampleService;

	@RequestMapping("v1/owners/testM1")
	public void saveOwner(HttpServletResponse response, HttpServletRequest request) throws Exception {
		log.trace("==== begin saveOwner ===");

		sampleService.saveOwner();
		//mySpan.end();
	}

	//
	//	@WithSpan(value = "span name")
	//	@RequestMapping("/owners/testM2")
	//	public void testMethod2(String ow, HttpServletResponse response, HttpServletRequest request) throws Exception {
	//		log.trace("call testMethod2 :\n {}", request);
	//
	//		Span test = Span.current();
	//		test.setAttribute("mymethod2 =>", "hahaha");
	//		log.info("current testM2 span => {}", test);
	//
	//		//		Context extractedContext = otel.getPropagators().getTextMapPropagator().extract(Context.current(), request,
	//		//			HttpServletRequestTextMapGetter.INSTANCE);
	//
	//		//		log.info("current span  extractedContext {}", Span.fromContext(extractedContext));
	//
	//		Enumeration<String> temp = request.getHeaderNames();
	//
	//		while (temp.hasMoreElements()) {
	//			String headerName = temp.nextElement();
	//			System.out.println("while print");
	//			System.out.println(headerName);
	//			System.out.println(response.getHeader(headerName));
	//		}
	//
	//		//innerMicroRestTemplate.getForObject(LOCAL_URL + "/owners/testM1", String.class);
	//
	//	}
	//
	//	private String calculateLlvm() {
	//		Span.current().setAttribute("type", "smarter");
	//		//noinspection DataFlowIssue
	//		Span.current().setAttribute("foo", Baggage.current().getEntryValue("foo"));
	//		return "OK";
	//	}
	//
	//	//	public void setBaggageAndRun(ExtendedTracer tracer) {
	//	//		try {
	//	//			Baggage.current().toBuilder()
	//	//				.put("foo", "bar")
	//	//				.build()
	//	//				.storeInContext(Context.current())
	//	//				.wrap(() -> tracer.spanBuilder("span with baggage").startAndCall(this::calculateLlvm))
	//	//				.call();
	//	//		} catch (Exception e) {
	//	//			throw new RuntimeException(e);
	//	//		}
	//	//	}
	//
	//	public static class HttpServletRequestTextMapGetter implements TextMapGetter<HttpServletResponse> {
	//
	//		public final static HttpServletRequestTextMapGetter INSTANCE = new HttpServletRequestTextMapGetter();
	//
	//		@Override
	//		@Nullable
	//		public String get(@Nullable
	//		HttpServletResponse carrier, String key) {
	//			return carrier.getHeader(key);
	//		}
	//
	//		@Override
	//		public Iterable<String> keys(HttpServletResponse carrier) {
	//			// TODO Auto-generated method stub
	//			return carrier.getHeaderNames();
	//		}
	//	}
	//
	//	static class AkkaHttpHeaders {
	//		private HttpRequest request;
	//
	//		public AkkaHttpHeaders(HttpRequest request) {
	//			this.request = request;
	//		}
	//
	//		public HttpRequest getRequest() {
	//			return request;
	//		}
	//
	//		public void setRequest(HttpRequest request) {
	//			this.request = request;
	//		}
	//	}
	//
	//	@RequestMapping("/owners/get")
	//	public ResponseEntity<OwnerDto> getOwner(@RequestParam(value = "ownerId", defaultValue = "000")
	//	String ownerId, HttpServletResponse response, HttpServletRequest request) throws Exception {
	//
	//		OwnerDomain ownerDomain = null;
	//		List<OwnerDomain> ownerDomainList = null;
	//		OwnerDto resultDto = null;
	//
	//		//try (Scope ignored = mySpan.makeCurrent()) {
	//
	//			// set tags
	//			//			mySpan.setAttribute("firstSpan", "hahahaha");
	//			//			myRequest.setHeader("mineHeader1", "kkkkkkkkkkkkkkkk");
	//			//			Context contextWith = Context.current().with(mySpan);
	//			//			otel.getPropagators().getTextMapPropagator().inject(contextWith, myRequest,
	//			//				HttpServletRequestTextMapSetter.INSTANCE);
	//
	//			//mySpan.makeCurrent();
	//
	//			//		final ExtendedTracer tracer = ExtendedTracer.create(otel.getTracer("mmyymymym"));
	//			//		tracer.create(otel.getTracer("myTracer"));
	//			//		for (int i = 0; i < 5; i++) {
	//			//			myWonderfulUseCase(tracer);
	//			//		}
	//			//
	//			//		setBaggageAndRun(tracer);
	//
	//			//		Observation currentOb = observ.getCurrentObservation();
	//			//var observation = Observation.createNotStarted("fetch-commit", registry).start();
	//
	//			//Span test = Span.current();f
	//			//test.setAttribute("mymethod1 =>", "kkkkkkkkk");
	//			//logger.info("current  span => {}", test);
	//
	//			//log.info("controller myspan print =>> {} ", mySpan);
	//
	//			//innerMicroRestTemplate.getForObject(LOCAL_URL + "/owners/testM1", String.class);
	//
	//			try {
	//				//				ownerDomain = ownerFactory.createDomain(1L);
	//				//				ownerDomainList = ownerFactory.createOwnerDomainList();
	//
	//				OwnerDomain owner1 = ownerDomainList.get(1);
	//				log.info("ownerDomain : {}", owner1);
	//				//OwnerDomain owner2 = ownerDomainList.get(2);
	//				//System.out.printf(" owner2 name = %s \n %s \n", owner2.getOwnerId() );
	//
	//				adRepo.findPetPageBySearchParams(1L, "", null);
	//
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
	//			if (ownerDomain != null) {
	//				resultDto = ownerDomain.getOwnerInformation();
	//			}
	//
	//			//			AkkaHttpHeaders akHeader = new AkkaHttpHeaders(hRequest);
	//
	//			//			otel.getPropagators().getTextMapPropagator().inject(Context.current(), headers,
	//			//				MyHttpSetter.INSTANCE);
	//
	//			// call testM2
	//			innerMicroRestTemplate.getForObject(LOCAL_URL + "/owners/testM2", String.class);
	//			innerMicroRestTemplate.getForObject(BASE_URL + "/owners/testM2", String.class);
	////		} catch (Exception e) {
	////			// TODO: handle exception
	////		} finally {
	////			log.info("mySpan end : \n{}", mySpan);
	////			mySpan.end();
	////		}
	//		return new ResponseEntity<>(resultDto, HttpStatus.OK);
	//	}
	//
	//	@RequestMapping("/owners/save")
	//	public ResponseEntity<OwnerDto> ping(@RequestParam(value = "name", defaultValue = "World")
	//	String name) {
	//		OwnerDto ownerDto = OwnerDto.builder()
	//			.name(name)
	//			.birthDate("20231125")
	//			.email("aaa@gmail.com")
	//			.address("세종시")
	//			.ownerId(12l)
	//			.build();
	//
	//		if (ownerDto.getAddress() != null) {
	//			System.out.println("haha");
	//		}
	//
	//		try {
	//			ownerRepo.saveOwner(ownerDto);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//
	//		return new ResponseEntity<>(ownerDto, HttpStatus.OK);
	//	}
	//
	//	@Transactional
	//	@RequestMapping("/owners/update")
	//	public ResponseEntity<OwnerDto> updateOwner(@RequestParam(value = "name", defaultValue = "World")
	//	String name) {
	//		OwnerDto ownerDto1 = null;
	//		OwnerDto ownerDto2 = null;
	//
	//		try {
	//			//			OwnerDomain ownerDomain = ownerFactory.createDomain(1L);
	//			//		OwnerDomain ownerDomain2 = ownerFactory.createDomain(2L);
	//			//		OwnerDomain ownerDomain3 = ownerFactory.createDomain(3L);
	//
	//			ownerDto1 = OwnerDto.builder().ownerId(4L)
	//				.address("구로구")
	//				.email("k22k@gmail.con")
	//				.phoneNumber("0104446666")
	//				.build();
	//
	//			ownerRepo.updateOwner(ownerDto1);
	//
	//			ownerDto2 = OwnerDto.builder().ownerId(5L)
	//				.name("도로토")
	//				.address("도봉구")
	//				.email("11@gmail.con")
	//				.phoneNumber("010242411")
	//				.updatedAt(new Timestamp(new Date().getTime()))
	//				.build();
	//
	//			ownerRepo.updateOwner(ownerDto1);
	//			//		ownerDomain.setAddress("강남구");
	//			//		ownerDomain.setAddress("송파구");
	//
	//			ownerRepo.updateOwner(ownerDto2);
	//
	//			ownerRepo.updateOwner(new OwnerDto().builder().ownerId(6L).address("양천동").build());
	//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<>(ownerDto1, HttpStatus.OK);
//	}
//
//	@GetMapping("/owners/{ownerId}/pet")
//	public ResponseEntity<PetResponseDto> getPetByOwnerId(@PathVariable
//	Long ownerId) {
//		System.out.println("ownerId() = " + ownerId);
//		//	    Optional<Owner> ownerOptional = ownerRepo.findById(ownerId);
//		//	    Optional<PetDomain> petOptional = petRepo.findById(ownerId);
//
//		//	    System.out.println("petOptional() = " + petOptional.get().getPetName());
//
//		//	    System.out.println("getName() = " + ownerOptional.get().getName());
//		//	    Owner temp  = ownerOptional.get();
//
//		//        if (!temp.getPets().isEmpty()) {
//		//
//		//            List<Pet> pets = temp.getPets();
//		//            Pet myPet = pets.get(0);
//		//            PetResponseDto responseDto = PetResponseDto.builder().breed(myPet.getBreed()).petName(myPet.getPetName()).build();
//		//
//		//            System.out.println("pet.getPetName() = " + responseDto );
//		//
//		//	        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//		//
//		//	    } else {
//		//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		//	    }
//
//		//	    PetDomain myPet = petOptional.get();
//		//	    Owner myOnwer = myPet.getOwner();
//
//		//	    System.out.println("myOnwer() = " + myOnwer.getEmail() );
//
//		//	    PetResponseDto responseDto = PetResponseDto.builder().breed(myPet.getBreed()).petName(myPet.getPetName()).build();
//		PetResponseDto responseDto = null;
//
//		return new ResponseEntity<>(responseDto, HttpStatus.OK);
//	}

}
