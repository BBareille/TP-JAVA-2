package com.tpjava.tpjava2.repository;

import com.tpjava.tpjava2.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
}
