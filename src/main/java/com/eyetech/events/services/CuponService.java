package com.eyetech.events.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyetech.events.dtos.CuponRequestDTO;
import com.eyetech.events.model.Cupon;
import com.eyetech.events.model.Event;
import com.eyetech.events.repositories.CuponRepository;
import com.eyetech.events.repositories.EventRepository;

@Service
public class CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    @Autowired
    private EventRepository eventRepository;

    public Cupon addCuponToEvent(Long eventId, CuponRequestDTO cuponDto) {

        Objects.requireNonNull(eventId, "Event id must not be null");

        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        
        Cupon cupon = new Cupon();
        cupon.setCode(cuponDto.code());
        cupon.setDiscount(cuponDto.discount());
        cupon.setValid(cuponDto.valid());
        cupon.setEvent(event);

        return cuponRepository.save(cupon);


    }
    
}
