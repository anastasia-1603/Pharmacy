package com.example.task12.service;

import com.example.task12.dto.CustomerDto;
import com.example.task12.entity.Customer;
import com.example.task12.mapper.CustomerMapper;
import com.example.task12.repository.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService
{
    private final IRepository<Customer> _customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDto getById(Integer id)
    {
        return customerMapper.toDto(_customerRepository.get(id));
    }

    public List<CustomerDto> getAll()
    {
        return customerMapper.toDtos(_customerRepository.getAll());
    }

    public void save(CustomerDto customerDto)
    {
        _customerRepository.save(customerMapper.toEntity(customerDto));
    }

    public void delete(Integer id)
    {
        _customerRepository.delete(_customerRepository.get(id));
    }

    public void update(CustomerDto customerDto)
    {
        Customer customer = _customerRepository.get(customerDto.getId());
        if (customer != null) {
            customer.setName(customerDto.getName());
            customer.setAddress(customerDto.getAddress());
            customer.setNumberPhone(customerDto.getNumberPhone());
            _customerRepository.save(customer);
        }
    }
}
