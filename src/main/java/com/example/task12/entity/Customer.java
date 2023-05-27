package com.example.task12.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "numberphone")
    private String numberPhone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores;


    public Customer(String name, String numberPhone, String address)
    {
        this.name = name;
        this.numberPhone = numberPhone;
        this.address = address;
    }

}
