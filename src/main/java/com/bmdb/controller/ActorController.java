package com.bmdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.bmdb.db.ActorRepo;
import com.bmdb.model.Actor;

@CrossOrigin
@RestController
@RequestMapping("/api/actors")
public class ActorController {
	
	@Autowired
	private ActorRepo actorRepo;
	
	@GetMapping("/")
	public List<Actor> getAllActors() {
		return actorRepo.findAll();
	}
	
	// getById  - "/api/actors/{id}
	//			- return: Actor 
	@GetMapping("/{id}")
	public Optional<Actor> getActorById(@PathVariable int id) {
		// check if actor exists for id
		// if yes, return actor
		// if no, return NotFound
		Optional<Actor> a = actorRepo.findById(id);
		if (a.isPresent()) {
			return a;
		}
		else {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Actor not found for id: "+id);
		}
	}
	
	// post 	- "/api/actors/" (actor will be in the RequestBody)
	//			- return: Actor
	@PostMapping("")
	public Actor addActor(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	// put 		- "/api/actors/{id} (actor passed in RB)
	//			- return: NoContent()
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void putActor(@PathVariable int id, @RequestBody Actor actor) {
		// check id passed vs id in instance
		// - BadRequest if not exist
		if (id != actor.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Actor id mismatch vs URL.");
		}
		// if actor exists, update, else not found
		else if (actorRepo.existsById(actor.getId())) {
			actorRepo.save(actor);
		}
		else {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Actor not found for id: "+id);
		}
	}
	
	
	// delete 	- "/api/actors/{id}
	//			- return: NoContent()
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deleteActor(@PathVariable int id) {
		// check existence, then remove actor
		// return NotFound if not exist
		if (actorRepo.existsById(id)) {
			actorRepo.deleteById(id);
		}
		else {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Actor not found for id: "+id);
		}
	}

}