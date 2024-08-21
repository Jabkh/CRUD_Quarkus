package com.example.service;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public List<Customer> listAll() {
        return customerRepository.listAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findByIdOptional(id);
    }

    public Customer createCustomer(Customer customer) {
        customerRepository.persist(customer);
        return customer;
    }

    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findByIdOptional(id).map(customer -> {
            customer.firstName = updatedCustomer.firstName;
            customer.lastName = updatedCustomer.lastName;
            customer.email = updatedCustomer.email;
            customer.phoneNumber = updatedCustomer.phoneNumber;
            customerRepository.persist(customer);
            return customer;
        });
    }

    public boolean deleteCustomer(Long id) {
        return customerRepository.deleteById(id);
    }
}