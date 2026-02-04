package com.eyetech.events.repositories;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyetech.events.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
    Page<Event> findByDateGreaterThanEqual(Date date, Pageable pageable);
}