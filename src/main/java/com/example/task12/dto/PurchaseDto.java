package com.example.task12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto
{
    private Integer id ;
    private MedicamentDto medicament;
    private Integer count;
    private Integer delay;
    private Integer timeDelay;

    public PurchaseDto(MedicamentDto medicament, Integer count, Integer delay)
    {
        this.medicament = medicament;
        this.count = count;
        this.delay = delay;
        this.timeDelay = delay;
    }
}
