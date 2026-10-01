package com.example.schedulingbackend.controller;

import com.example.schedulingbackend.model.ClassSchedule;
import com.example.schedulingbackend.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // dto interno para mapear o JSON que virá na requisição HTTP
    public static class BookingRequest {
        private LocalDateTime dateTime;
        private String studentName;

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }
    }

    // endpoint http post para realizar o agendamento
    @PostMapping("/book")
    public ResponseEntity<?> bookClass(@RequestBody BookingRequest request) {
        try {
            ClassSchedule booked = scheduleService.bookClass(request.getDateTime(), request.getStudentName());
            return ResponseEntity.ok(booked);
        } catch (RuntimeException e) {
            // retorna erro 400 bad request se o horário já estiver ocupado
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}