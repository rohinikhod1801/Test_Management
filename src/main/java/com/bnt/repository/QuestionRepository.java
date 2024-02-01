package com.bnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.model.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
	
}
