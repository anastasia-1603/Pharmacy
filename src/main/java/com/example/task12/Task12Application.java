package com.example.task12;

import com.example.task12.dto.CustomerDto;
import com.example.task12.dto.MedicamentDto;
import com.example.task12.dto.OfferDto;
import com.example.task12.dto.ScoreDto;
import com.example.task12.service.AppendService;
import com.example.task12.service.CustomerService;
import com.example.task12.service.MedicamentService;
import com.example.task12.service.WarehouseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Task12Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Task12Application.class);
        AppendService appendService = applicationContext.getBean(AppendService.class);
        WarehouseService warehouseService = applicationContext.getBean(WarehouseService.class);
        CustomerService customerService = applicationContext.getBean(CustomerService.class);
        MedicamentService medicamentService = applicationContext.getBean(MedicamentService.class);
        Random rnd = new Random(100);
        for(int i = 0; i < 100; i++)
        {
            warehouseService.update();
            if(rnd.nextInt() % 83 == 0)
                appendService.generateProduction();
        }

        List<CustomerDto> customers = customerService.getAll();
        List<MedicamentDto> medicaments = medicamentService.getAll();
        OfferDto offerDto = new OfferDto(customers.get(0), null, 40, medicaments.get(2));
        List<ScoreDto> scoreDtos = warehouseService.buy(offerDto);
        for(ScoreDto scoreDto : scoreDtos)

            System.out.println(scoreDto);
    }

}
