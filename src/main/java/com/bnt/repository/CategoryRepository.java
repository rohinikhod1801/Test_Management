package com.bnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	

}
