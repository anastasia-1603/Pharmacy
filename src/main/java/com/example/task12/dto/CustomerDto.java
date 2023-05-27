package com.example.task12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto
{
    private Integer id;
    private String name;
    private String numberPhone;
    private String address;


    public CustomerDto(String name, String numberPhone, String address)
    {
        this.name = name;
        this.numberPhone = numberPhone;
        this.address = address;
    }
}
