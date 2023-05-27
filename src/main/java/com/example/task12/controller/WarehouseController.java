package com.example.task12.controller;

import com.example.task12.dto.OfferDto;
import com.example.task12.dto.ScoreDto;
import com.example.task12.entity.Score;
import com.example.task12.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
@AllArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping("/buy")
    public ResponseEntity<List<ScoreDto>> buy(@RequestBody OfferDto offerDto)
    {
        return ResponseEntity.ok(warehouseService.buy(offerDto));
    }







}
