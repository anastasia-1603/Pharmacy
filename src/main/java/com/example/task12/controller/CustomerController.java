package com.example.task12.controller;

import com.example.task12.dto.CustomerDto;
import com.example.task12.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAll()
    {
        return ResponseEntity.ok(customerService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CustomerDto customerDto) {
        customerService.save(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CustomerDto customerDto) {
        customerService.update(customerDto);
        return ResponseEntity.ok().build();
    }


}
