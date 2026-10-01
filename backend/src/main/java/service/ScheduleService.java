package com.example.schedulingbackend.service;

import com.example.schedulingbackend.model.ClassSchedule;
import com.example.schedulingbackend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public ClassSchedule bookClass(LocalDateTime dateTime, String studentName) {

        Optional<ClassSchedule> optionalSchedule = scheduleRepository.findAndLockByDateTime(dateTime);

        if (optionalSchedule.isPresent()) {
            ClassSchedule schedule = optionalSchedule.get();

            schedule.setStatus("AGENDADO");
            schedule.setStudentName(studentName);

            return scheduleRepository.save(schedule);
        }

        throw new RuntimeException("Horário não disponível ou já reservado por outro aluno.");
    }
}