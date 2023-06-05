package com.tpjava.tpjava2.repository;

import com.tpjava.tpjava2.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>
{
}
