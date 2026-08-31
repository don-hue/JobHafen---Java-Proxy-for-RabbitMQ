package com.JobHafen.Proxy.controller;

import com.JobHafen.Proxy.dto.*;
import com.JobHafen.Proxy.producer.SearchProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/proxy/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchRestController {
    private final SearchProducer searchProducer;

    public SearchRestController(SearchProducer searchProducer) {
        this.searchProducer = searchProducer;
    }
    @PostMapping("/sendSaveSearchRequest")
    public List<SearchEntityDto> publishSaveSearch(@RequestBody SearchDto searchDto) {
        SearchDto javaSearchDto = new SearchDto(searchDto.keyword(), searchDto.postal_code(), searchDto.radius());
        return searchProducer.publishSaveSearch(javaSearchDto);
    }
    @GetMapping("/sendGetAllRequest")
    public List<SearchEntityDto> publishGetSearch() {
        return searchProducer.publishGetSearchRequest();
    }

    @DeleteMapping("sendDeleteRequest/{searchId}")
    public ResponseEntity<Void> publishDeleteSearch(@PathVariable Long searchId) {
        return searchProducer.publishDeleteSearch(searchId);
    }
}
