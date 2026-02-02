package com.eyetech.events.dtos;

import org.springframework.web.multipart.MultipartFile;

public record EventRequestDTO(
    String title,
    String description,
    Long date,
    Boolean remote,
    String eventUrl,
    MultipartFile image
) {} 
