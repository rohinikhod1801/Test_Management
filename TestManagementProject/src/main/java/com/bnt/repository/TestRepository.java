package com.bnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.model.TestManagement;

@Repository
public interface TestRepository extends JpaRepository<TestManagement, Long>{

}
