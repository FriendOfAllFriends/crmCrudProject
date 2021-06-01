package com.taras.springdemo.dao;

import com.taras.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOimpl implements  CustomerDAO{

    //inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        //get current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //create query
        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

        //execute query and get result list
        List<Customer> customerList = query.getResultList();

        //return result
        return customerList;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        Session session = sessionFactory.getCurrentSession();

        Query query = null;

        if (searchName != null && searchName.trim().length() > 0) {
            query = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like  :theName", Customer.class);
            query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        }
        else {
            query = session.createQuery("from Customer order by lastName", Customer.class);
        }

        List<Customer> customers = query.getResultList();
        return customers;
    }
}
