package com.example.task12.service;

import com.example.task12.dto.ProductDto;
import com.example.task12.entity.*;
import com.example.task12.repository.IRepository;
import com.example.task12.repository.RepositoryKeyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppendService
{
    @Autowired
    private RepositoryKeyObject<Product, Medicament> productRepository;
    @Autowired
    private IRepository<Score> scoreRepository;
    @Autowired
    private IRepository<Purchase> purchaseRepository;
    @Autowired
    private IRepository<Customer> customerRepository;
    @Autowired
    private IRepository<Medicament> medicamentRepository;

    public void addMedicament(List<ProductDto> products)
    {
        for (ProductDto product : products) {
            Medicament medicament = medicamentRepository.get(product.getMedicament().getId());
            Product product1 = new Product();
            product1.setDelay(product.getDelay());
            product1.setMedicament(medicament);
            product1.setCount(product.getCount());
            productRepository.save(product1);
        }
    }

    public void generateProduction()
    {
        List<Medicament> medicaments = medicamentRepository.getAll();
        Product product = new Product();
        product.setCount(400);
        product.setMedicament(medicaments.get(0));
        product.setDelay(340);
        productRepository.save(product);
        product = new Product();
        product.setDelay(100);
        product.setMedicament(medicaments.get(3));
        product.setCount(200);
        productRepository.save(product);
        product = new Product();
        product.setMedicament(medicaments.get(1));
        product.setCount(124);
        product.setDelay(300);
        productRepository.save(product);
        product = new Product();
        product.setCount(400);
        product.setMedicament(medicaments.get(2));
        product.setDelay(450);
        productRepository.save(product);
    }
}
