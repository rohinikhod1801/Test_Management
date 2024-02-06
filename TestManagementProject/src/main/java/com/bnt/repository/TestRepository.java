package com.bnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.model.TestRequest;

@Repository
public interface TestRepository extends JpaRepository<TestRequest, Long>{

}
