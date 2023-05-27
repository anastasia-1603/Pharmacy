package com.example.task12.repository.hibernateImpl;

import com.example.task12.config.HibernateConfig;
import com.example.task12.entity.Customer;
import com.example.task12.entity.Medicament;
import com.example.task12.entity.Purchase;
import com.example.task12.repository.IRepository;
import com.example.task12.repository.RepositoryKeyObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepositoryHibernate implements RepositoryKeyObject<Purchase, Customer>
{
    private HibernateConfig _hibernateConfig = new HibernateConfig();

    @Override
    public Purchase get(Integer id)
    {
        return _hibernateConfig.getSessionFactory().openSession().get(Purchase.class, id);
    }

    @Override
    public List<Purchase> getAll()
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        List<Purchase> l = IRepository.loadAllData(Purchase.class, session);
        session.close();
        return l;
    }

    @Override
    public void save(Purchase purchase)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(purchase);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Purchase purchase)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(purchase);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Purchase purchase)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(purchase);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Purchase> getAllBy(Customer customer)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        String hql = "from Purchase where customer_id = " + customer.getId().toString();
        Query query = session.createQuery(hql);

        List result = query.list();
        session.close();
        return result;
    }
}
