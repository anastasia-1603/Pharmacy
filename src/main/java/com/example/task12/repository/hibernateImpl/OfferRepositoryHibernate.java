package com.example.task12.repository.hibernateImpl;

import com.example.task12.config.HibernateConfig;
import com.example.task12.entity.Medicament;
import com.example.task12.entity.Offer;
import com.example.task12.repository.IRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfferRepositoryHibernate implements IRepository<Offer>
{

    private  HibernateConfig _hibernateConfig  = new HibernateConfig();


    @Override
    public Offer get(Integer id)
    {
        return _hibernateConfig.getSessionFactory().openSession().get(Offer.class, id);
    }

    @Override
    public List<Offer> getAll()
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        List<Offer> l = IRepository.loadAllData(Offer.class, session);
        session.close();
        return l;
    }

    @Override
    public void save(Offer offer)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(offer);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Offer offer)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(offer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Offer offer)
    {
        Session session = _hibernateConfig.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(offer);
        transaction.commit();
        session.close();
    }
}
