package com.example.schedulingbackend.repository;

import com.example.schedulingbackend.model.ClassSchedule;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ClassSchedule, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM ClassSchedule s WHERE s.classDateTime = :dateTime AND s.status = 'LIVRE' ")
    Optional<ClassSchedule> findAndLockByDateTime(@Param("dateTime") LocalDateTime dateTime);
}