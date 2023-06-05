package com.tpjava.tpjava2.repository;

import com.tpjava.tpjava2.entity.Former;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormerRepository extends JpaRepository<Former, Long> {
}
