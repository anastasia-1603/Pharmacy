package com.example.task12.service;


import com.example.task12.dto.OfferDto;
import com.example.task12.dto.ScoreDto;
import com.example.task12.entity.*;
import com.example.task12.repository.IRepository;
import com.example.task12.repository.RepositoryKeyObject;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseService
{
    private final RepositoryKeyObject<Product, Medicament> productRepository;
    private final IRepository<Score> scoreRepository;
    private final RepositoryKeyObject<Purchase, Customer> purchaseRepository;
    private final IRepository<Customer> customerRepository;
    private final IRepository<Medicament> medicamentRepository;
    private final IRepository<Offer> offerRepository;

    private List<Score> buyProductByOffer(@NonNull Offer offer)
    {
        List<Score> scores = new LinkedList<>();
        List<Product> allProducts = productRepository.getAllBy(offer.getMedicament());
        int count = offer.getCount();
        for(Product product: allProducts)
        {
            if(count < product.getCount())
            {
                Score score = calculateScore(offer.getMedicament(), offer.getCustomer(), count, product.getDelay());
                scores.add(score);
                product.setCount(product.getCount() - count);
                productRepository.update(product);
                break;
            }
            else
            {
                Score score = calculateScore(offer.getMedicament(), offer.getCustomer(), product.getCount(), product.getDelay());
                scores.add(score);
                count -= product.getCount();
                productRepository.delete(product);
            }
        }
        return scores;
    }

    private Score calculateScore(@NonNull Medicament medicament, @NonNull Customer customer, @NonNull Integer count, @NonNull Integer delay)
    {
        Score score = new Score( 0d, 10, medicament, count, customer);
        Double price = count * medicament.getPrice();
        score.setCost( score.getCost() + (delay < 30 ? price/ 2 : price));
        return score;
    }

    private void revision()
    {
        List<Product> products = productRepository.getAll();
        List<Product> removeProduct = new LinkedList<>();
        for(Product product : products)
        {
            product.setDelay(product.getDelay() - 1);
            if(product.getDelay() <= 0)
            {
                removeProduct.add(product);
                continue;
            }
            productRepository.update(product);
        }
        for(Product product : removeProduct)
            productRepository.delete(product);
    }

    private void spendPurchaseToCustomer(Customer customer)
    {
        List<Purchase> purchases = purchaseRepository.getAllBy(customer);
        for(Purchase purchase: purchases)
        {
            purchase.setDelay(purchase.getDelay() - 1);
            if(purchase.getDelay() <= 0) {
                Offer offer = new Offer(customer, null, purchase.getCount(), purchase.getMedicament());
                offerRepository.save(offer);
                List<Score> scores = buyProductByOffer(offer);
                for(Score score : scores) {
                    scoreRepository.save(score);
                }
                purchase.setDelay(purchase.getTimeDelay());
            }
           purchaseRepository.update(purchase);
        }
    }

    private void periodic()
    {
        for(Customer customer : customerRepository.getAll())
            spendPurchaseToCustomer(customer);
    }

    public void update()
    {
        periodic();
        revision();
    }

    public List<ScoreDto> buy(OfferDto offerDto)
    {
        Customer customer = customerRepository.get(offerDto.getCustomer().getId());
        Medicament medicament = medicamentRepository.get(offerDto.getMedicament().getId());
        Offer offer = new Offer(customer, null, offerDto.getCount(), medicament);
        offerRepository.save(offer);
        List<Score> scores = buyProductByOffer(offer);
        List<ScoreDto> scoreDtos = new LinkedList<>();
        for(Score score: scores) {
            scoreRepository.save(score);
            scoreDtos.add(new ScoreDto(score.getCost(),score.getDate(),offerDto.getMedicament(), score.getCount(), offerDto.getCustomer()));
        }
        return scoreDtos;
    }
}
