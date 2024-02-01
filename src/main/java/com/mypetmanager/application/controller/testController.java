package com.mypetmanager.application.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mypetmanager.business.domain.owner.OwnerDomain;
import com.mypetmanager.business.domain.owner.OwnerFactory;
import com.mypetmanager.business.domain.owner.dto.OwnerDto;
import com.mypetmanager.integration.repository.owner.OwnerRepository;
import com.mypetmanager.integration.repository.pet.PetRepository;
import com.mypetmanager.integration.repository.pet.dto.PetResponseDto;
import com.mypetmanager.integration.repository.querydsl.AdvancedRepository;

import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationView;
import io.opentelemetry.api.trace.Span;
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
	private RestTemplate restTemplate;
	private ObservationRegistry observ;

	//@Qualifier("innerMicroRestTemplate")
	private RestTemplate innerMicroRestTemplate;
	// Observability
	private final ObservationRegistry registry;

	@RequestMapping("/owners/testM1")
	public void testMethod() throws Exception {
		System.out.println("testM1");
		String targetRootDomain = "OwnerDomain";

		ownerFactory.createDomain(1L);
	}

	@RequestMapping("/owners/testM2")
	public void testMethod2() throws Exception {
		log.trace("call testMethod2 : {}");
		Span test = Span.current();
		test.setAttribute("mymethod2 =>", "hahaha");
		log.info("current  span => {}", test);

	}

	@RequestMapping("/owners/get")
	public ResponseEntity<OwnerDto> getOwner(@RequestParam(value = "ownerId", defaultValue = "000")
	String ownerId) {

		final Logger logger = LoggerFactory.getLogger(testController.class);
		String LOCAL_URL = "http://localhost:8089";
		String BASE_URL = "http://localhost:8081";
		logger.info("가즈아");
		Observation currentOb = observ.getCurrentObservation();

		var observation = Observation.createNotStarted("fetch-commit", registry).start();
		Span test = Span.current();
		test.setAttribute("mymethod1 =>", "kkkkkkkkk");
		logger.info("current  span => {}", test);

		try (var ignored = currentOb.openScope()) {
			String commitMsg = this.restTemplate.getForObject("https://whatthecommit.com/index.txt", String.class);
			currentOb.highCardinalityKeyValue("commit.message", commitMsg);

			currentOb.event(Observation.Event.of("commit-fetched"));


			Context context = currentOb.getContext();
			logger.info("show context => {}", context);
			logger.info("show getAllKeyValues => {}", context.getAllKeyValues());

			innerMicroRestTemplate.getForObject(BASE_URL + "/owners/testM1", String.class);

			ObservationView obv = context.getParentObservation();
			logger.info("show ObservationView => {}", obv);

		}

		OwnerDomain ownerDomain = null;
		List<OwnerDomain> ownerDomainList = null;
		OwnerDto resultDto = null;
		try {
			ownerDomain = ownerFactory.createDomain(1L);
			ownerDomainList = ownerFactory.createOwnerDomainList();

			OwnerDomain owner1 = ownerDomainList.get(1);
			//System.out.printf(" owner1 name = %s \n %s \n", owner1.getOwnerId() );
			//OwnerDomain owner2 = ownerDomainList.get(2);
			//System.out.printf(" owner2 name = %s \n %s \n", owner2.getOwnerId() );

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ownerDomain != null) {
			resultDto = ownerDomain.getOwnerInformation();
		}

		try {
			adRepo.findPetPageBySearchParams(1L, "", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		innerMicroRestTemplate.getForObject(LOCAL_URL + "/owners/testM2", String.class);

		currentOb.stop();

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
