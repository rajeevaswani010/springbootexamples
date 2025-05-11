package com.rjasw.demo.jpa_sql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rjasw.demo.jpa_sql.entity.CaseFile;

@Repository
public interface CaseFileRepo extends JpaRepository<CaseFile, Integer> {
    // You can add custom query methods here if needed
}
