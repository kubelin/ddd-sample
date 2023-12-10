package com.mypetmanager.application.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mypetmanager.business.domain.owner.OwnerDomain;
import com.mypetmanager.business.domain.owner.OwnerFactory;
import com.mypetmanager.integration.repository.owner.OwnerRepository;
import com.mypetmanager.integration.repository.owner.dto.OwnerDto;
import com.mypetmanager.integration.repository.pet.PetRepository;
import com.mypetmanager.integration.repository.pet.dto.PetResponseDto;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class testController {
	// repository
	final OwnerRepository ownerRepo;
	final PetRepository petRepo;
	// factory
	final OwnerFactory ownerFactory;
	
	@RequestMapping("/owners/get")
	public ResponseEntity<OwnerDto> getOwner(@RequestParam(value = "ownerId", defaultValue = "000") String ownerId) {
		
		OwnerDomain ownerDomain = null;
		List<OwnerDomain> ownerDomainList = null;
		try {
		ownerDomain = ownerFactory.createDomain(1L);
		ownerDomainList = ownerFactory.createOwnerDomainList();

		OwnerDomain owner1 = ownerDomainList.get(1);
		System.out.printf(" owner1 name = %s \n %s \n", owner1.getOwnerId(), owner1.getName());
		OwnerDomain owner2 = ownerDomainList.get(2);
		System.out.printf(" owner2 name = %s \n %s \n", owner2.getOwnerId(), owner2.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
		OwnerDto resultDto = ownerDomain.getOwnerInformation();
		
		return new ResponseEntity<>(resultDto, HttpStatus.OK);
	}
	
	@RequestMapping("/owners/save")
	public ResponseEntity<OwnerDto> ping(@RequestParam(value = "name", defaultValue = "World") String name) {
		OwnerDto ownerDto = OwnerDto.builder()
									.name(name)
									.birthDate("20231125")
									.email("aaa@gmail.com")
									.address("세종시")
									.ownerId(12l)
									.build();
		
		
		try {
			ownerRepo.saveOwner(ownerDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(ownerDto, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping("/owners/update")
	public ResponseEntity<OwnerDto> updateOwner(@RequestParam(value = "name", defaultValue = "World") String name) {
		OwnerDomain ownerDomain = null;
		OwnerDto ownerDto1 = null;
		OwnerDto ownerDto2 = null;
		
		try {
		ownerDomain = ownerFactory.createDomain(1L);
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
		
		ownerDomain.setAddress("강남구");
		ownerDomain.setAddress("송파구");
		
		ownerRepo.updateOwner(ownerDto2);
		
		ownerRepo.updateOwner(new OwnerDto().builder().ownerId(6L).address("양천동").build());
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>(ownerDto1, HttpStatus.OK);
	}
	
	@GetMapping("/owners/{ownerId}/pet")
	public ResponseEntity<PetResponseDto> getPetByOwnerId(@PathVariable Long ownerId) {
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
	    
	    return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
