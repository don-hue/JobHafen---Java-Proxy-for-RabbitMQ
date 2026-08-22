package com.JobHafen.Proxy.dto;

import java.net.URL;

public record JobEntityDto(
        Long id,
        String jobTitle,
        boolean applied,
        String companyName,
        URL companyHomepage
) { }
