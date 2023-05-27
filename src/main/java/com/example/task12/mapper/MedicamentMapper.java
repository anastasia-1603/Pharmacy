package com.example.task12.mapper;

import com.example.task12.dto.CustomerDto;
import com.example.task12.dto.MedicamentDto;
import com.example.task12.entity.Customer;
import com.example.task12.entity.Medicament;

import java.util.ArrayList;
import java.util.List;

public class MedicamentMapper {

    public MedicamentDto toDto(Medicament entity) {
        return new MedicamentDto(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getPrice());
    }

    public Medicament toEntity(MedicamentDto dto) {
        return new Medicament(
                dto.getId(),
                dto.getName(),
                dto.getType(),
                dto.getPrice());
    }

    public List<MedicamentDto> toDtos(List<Medicament> entities) {
        if ( entities == null ) {
            return null;
        }

        List<MedicamentDto> list = new ArrayList<MedicamentDto>( entities.size() );
        for ( Medicament medicament : entities ) {
            list.add(toDto(medicament));
        }

        return list;
    }

    public List<Medicament> toEntities(List<MedicamentDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Medicament> list = new ArrayList<Medicament>( dtos.size() );
        for ( MedicamentDto medicament : dtos ) {
            list.add(toEntity(medicament));
        }

        return list;
    }

}
