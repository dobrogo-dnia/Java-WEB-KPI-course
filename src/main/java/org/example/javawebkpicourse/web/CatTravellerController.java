package org.example.javawebkpicourse.web;

import org.example.javawebkpicourse.dto.catTraveller.CatTravellerDetailsDto;
import org.example.javawebkpicourse.dto.catTraveller.CatTravellerDetailsListDto;
import org.example.javawebkpicourse.service.CatTravellerService;
import org.example.javawebkpicourse.service.mapper.CatTravellerDetailsMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/travellers")
public class CatTravellerController {

    private final CatTravellerService catTravellerService;
    private final CatTravellerDetailsMapper catTravellerDetailsMapper;

    public CatTravellerController(CatTravellerService catTravellerService, CatTravellerDetailsMapper catTravellerDetailsMapper) {
        this.catTravellerService = catTravellerService;
        this.catTravellerDetailsMapper = catTravellerDetailsMapper;
    }

    @GetMapping
    public ResponseEntity<CatTravellerDetailsListDto> getAllCatTravellers() {
        return ResponseEntity.ok(catTravellerDetailsMapper.toCatTravellerDetailsListDto(catTravellerService.getAllCatTravellerDetails()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatTravellerDetailsDto> getCatTravellerById(@PathVariable Long id) {
        return ResponseEntity.ok(catTravellerDetailsMapper.toCatTravellerDetailsDto(catTravellerService.getCatTravellerDetailsById(id)));
    }

    @PostMapping
    public ResponseEntity<CatTravellerDetailsDto> createCatTraveller(@RequestBody @Valid CatTravellerDetailsDto catTravellerDetailsDto){
        return ResponseEntity.ok(catTravellerDetailsDto);
    }
}
