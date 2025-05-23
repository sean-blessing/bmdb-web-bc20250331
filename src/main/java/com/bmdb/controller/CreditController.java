package com.bmdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.bmdb.db.CreditRepo;
import com.bmdb.model.Credit;

@CrossOrigin
@RestController
@RequestMapping("/api/credits")
public class CreditController {

	@Autowired
	private CreditRepo creditRepo;

//	@GetMapping("/dto")
//	public List<CreditDTO> getAllCreditDTOs() {
//		List<CreditDTO> creditDTOs = new ArrayList<>();
//		List<Credit> credits = creditRepo.findAll();
//		for (Credit c: credits) {
//			CreditDTO cdto = new CreditDTO(c.getId(), c.getMovie().getId(), 
//					c.getActor().getId(), c.getRole());
//			creditDTOs.add(cdto);
//		}
//		return creditDTOs;
//	}

	@GetMapping("/")
	public List<Credit> getAllCredits() {
		return creditRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Credit> getCreditById(@PathVariable int id) {
		Optional<Credit> c = creditRepo.findById(id);
		if (c.isPresent()) {
			return c;
		}
		else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Credit not found for id "+id);
		}
	}

	@PostMapping("")
	public Credit addCredit(@RequestBody Credit credit) {
		return creditRepo.save(credit);
	}

	@PutMapping("/{id}")
	public void put(@PathVariable int id, @RequestBody Credit credit) {
		if (id != credit.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Credit id mismatch vs URL.");
		} 
		else if (creditRepo.existsById(id)) {
			creditRepo.save(credit);
		} 
		else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Credit not found for id "+id);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteCredit(@PathVariable int id) {
		if (creditRepo.existsById(id)) {
			creditRepo.deleteById(id);
		}
		else {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Credit not found for id: "+id);
		}
	}
	
	@GetMapping("/by-movie/{movieId}")
	public List<Credit> getAllCreditsForMovieId(@PathVariable int movieId) {
		return creditRepo.findAllByMovieId(movieId);
	}
	

//	
//	// new requirement: Actor-Filmography: return all credits for an actor
//	@GetMapping("/actor-credits/{actorId}")
//	public List<Credit> getCreditsForActor(@PathVariable int actorId) {
//		return creditRepo.findByActorId(actorId);
//	}


}