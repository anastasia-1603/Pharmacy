package com.example.task12.repository.hibernateImpl;

import com.example.task12.config.HibernateConfig;
import com.example.task12.entity.Customer;
import com.example.task12.repository.IRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryHibernate implements IRepository<Customer>
{


    private HibernateConfig _hibernateConfig = new HibernateConfig();


    @Override
    public Customer get(Integer id)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll()
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        List<Customer> l = IRepository.loadAllData(Customer.class, session);
        session.close();
        return l;
    }

    @Override
    public void save(Customer customer)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Customer customer)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Customer customer)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();
    }


}
