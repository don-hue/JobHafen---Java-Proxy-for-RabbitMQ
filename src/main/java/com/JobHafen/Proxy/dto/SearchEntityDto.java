package com.JobHafen.Proxy.dto;

public record SearchEntityDto(
        Long id,
        String keyword,
        String postal_code,
        String radius
) { }

