package com.bnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.model.CategoryRequest;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryRequest, Long>{
	

}
