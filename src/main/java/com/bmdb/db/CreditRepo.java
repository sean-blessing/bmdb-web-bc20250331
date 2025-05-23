package com.bmdb.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmdb.model.Credit;

public interface CreditRepo extends JpaRepository<Credit, Integer>{
	// sql: select * from credit where movieId = ?
	List<Credit> findAllByMovieId(int movieId);
	// find all by MovieId
	// "find all" -> select *
	// from credit
	// "by" -> where
	// "movieId" -> movieId
	// = movieId (parameter passed into method)

}
