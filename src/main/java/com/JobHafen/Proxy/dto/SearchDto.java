package com.JobHafen.Proxy.dto;

public record SearchDto (
        String keyword,
        String postal_code,
        String radius
) {}
