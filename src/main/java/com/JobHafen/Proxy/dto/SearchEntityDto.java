package com.JobHafen.Proxy.dto;

import java.util.List;

public record SearchEntityDto(
        Long id,
        List<String> urls,
        String keyword,
        String postal_code,
        String radius
) { }

