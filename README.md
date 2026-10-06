# Sebastian - Bilingual AI English Practice & Class Scheduler

> An interactive, voice-controlled English learning platform featuring a holographic-style AI assistant ("Sebastian") designed to assist students with conversational practice and real-time class scheduling.

![Project Status](https://img.shields.io/badge/status-in%20development-orange)
![Java Spring Boot](https://img.shields.io/badge/backend-Spring%20Boot-green)
![PostgreSQL](https://img.shields.io/badge/database-PostgreSQL-blue)

---

## 🌟 Overview

**Sebastian** bridges the gap between AI voice interfaces and practical educational management. It acts as an interactive desktop assistant where students can practice English via voice commands, receive real-time bilingual feedback (English/Portuguese-BR), and seamlessly book conversation time slots. 

Built with a strong emphasis on **backend robustness, concurrency control, and clean architecture**, this project serves as a practical implementation of modern web development patterns.

---

## 🚀 Key Features

* **Holographic Voice Interface:** Clean, futuristic UI with reactive CSS animations and Web Speech API integration.
* **Real-time Bilingual Transcription:** Instantly captures user speech, displays transcripts, and pairs them with Portuguese translations to aid learners.
* **Concurrent Class Booking:** Robust backend API endpoint that manages time slots and prevents double-booking race conditions.
* **Database Concurrency Protection:** Utilizes JPA locking mechanisms (`PESSIMISTIC_WRITE`) to ensure data integrity under high concurrent loads.

---

## 🛠️ Tech Stack

### Backend
* **Java** & **Spring Boot** (RESTful APIs, Spring Data JPA)
* **PostgreSQL** (Relational database with pessimistic locking)
* **Maven** (Dependency management)

### Frontend
* **HTML5**, **CSS3**, & **JavaScript (Vanilla)**
* **Web Speech API** (Speech Recognition & Speech Synthesis)

---

## ⚙️ Architecture & Concurrency Highlights

To handle potential overlapping bookings when multiple students try to reserve the same time slot simultaneously, the service layer implements thread-safe data handling:

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT s FROM Schedule s WHERE s.dateTime = :dateTime")
Optional<Schedule> findByDateTimeWithLock(@Param("dateTime") LocalDateTime dateTime);
