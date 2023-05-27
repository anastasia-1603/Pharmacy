package com.example.task12.service;

import com.example.task12.dto.MedicamentDto;
import com.example.task12.entity.Customer;
import com.example.task12.entity.Medicament;
import com.example.task12.mapper.MedicamentMapper;
import com.example.task12.repository.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
@AllArgsConstructor
public class MedicamentService {

    private final IRepository<Medicament> _medicamentRepository;
    private final MedicamentMapper medicamentMapper;

    public List<MedicamentDto> getAll()
    {
        return medicamentMapper.toDtos(_medicamentRepository.getAll());
    }

    public MedicamentDto getById(Integer id)
    {
        return medicamentMapper.toDto(_medicamentRepository.get(id));
    }

    public void save(MedicamentDto medicamentDto)
    {
        _medicamentRepository.save(medicamentMapper.toEntity(medicamentDto));
    }

    public void delete(Integer id)
    {
        _medicamentRepository.delete(_medicamentRepository.get(id));
    }

    public void update(MedicamentDto medicamentDto)
    {
        Medicament medicament = _medicamentRepository.get(medicamentDto.getId());
        if (medicament != null) {
            medicament.setName(medicamentDto.getName());
            medicament.setPrice(medicamentDto.getPrice());
            medicament.setType(medicamentDto.getType());
            _medicamentRepository.save(medicament);
        }
    }
}
