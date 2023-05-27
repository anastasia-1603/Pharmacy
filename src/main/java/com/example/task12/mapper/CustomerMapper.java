package com.example.task12.mapper;

import com.example.task12.dto.CustomerDto;
import com.example.task12.dto.MedicamentDto;
import com.example.task12.entity.Customer;
import com.example.task12.entity.Medicament;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    public CustomerDto toDto(Customer entity) {
        return new CustomerDto(
                entity.getId(),
                entity.getName(),
                entity.getNumberPhone(),
                entity.getAddress()
        );
    }

    public Customer toEntity(CustomerDto dto) {
        return new Customer(
                dto.getName(),
                dto.getNumberPhone(),
                dto.getAddress()
        );
    }

    public List<CustomerDto> toDtos(List<Customer> entities)
    {
        if ( entities == null ) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<CustomerDto>( entities.size() );
        for ( Customer customer : entities ) {
            list.add(toDto(customer));
        }

        return list;
    }

    public List<Customer> toEntities(List<CustomerDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( dtos.size() );
        for ( CustomerDto customer : dtos ) {
            list.add(toEntity(customer));
        }

        return list;
    }
}
