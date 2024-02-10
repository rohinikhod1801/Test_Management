package com.bnt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.entity.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

	Optional<Categories> findByTitle(String title);
}
