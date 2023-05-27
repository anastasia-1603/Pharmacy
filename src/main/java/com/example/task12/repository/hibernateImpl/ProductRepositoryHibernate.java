package com.example.task12.repository.hibernateImpl;

import com.example.task12.config.HibernateConfig;
import com.example.task12.entity.Medicament;
import com.example.task12.entity.Product;
import com.example.task12.repository.IRepository;
import com.example.task12.repository.RepositoryKeyObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryHibernate implements RepositoryKeyObject<Product, Medicament>
{

    private  HibernateConfig _hibernateConfig  = new HibernateConfig();


    @Override
    public Product get(Integer id)
    {
        return _hibernateConfig.getSessionFactory().openSession().get(Product.class, id);
    }

    @Override
    public List<Product> getAll()
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        List<Product> l = IRepository.loadAllData(Product.class, session);
        session.close();
        return l;
    }

    @Override
    public void save(Product product)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Product product)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Product product)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(product);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Product> getAllBy(Medicament medicament)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        String hql = "from Product where Product.medicament.id = " + medicament.getId().toString();
        Query query = session.createQuery(hql);
        List result = query.list();
        session.close();
        return result;
    }
}
