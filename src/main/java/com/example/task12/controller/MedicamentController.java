package com.example.task12.controller;

import com.example.task12.dto.MedicamentDto;
import com.example.task12.service.MedicamentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicament")
@AllArgsConstructor
public class MedicamentController {

    private final MedicamentService medicamentService;

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentDto> getById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(medicamentService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicamentDto>> getAll()
    {
        return ResponseEntity.ok(medicamentService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody MedicamentDto medicamentDto) {
        medicamentService.save(medicamentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medicamentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody MedicamentDto medicamentDto) {
        medicamentService.update(medicamentDto);
        return ResponseEntity.ok().build();
    }
}
