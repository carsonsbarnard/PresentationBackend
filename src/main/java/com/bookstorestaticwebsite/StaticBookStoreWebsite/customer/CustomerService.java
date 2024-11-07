package com.bookstorestaticwebsite.StaticBookStoreWebsite.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){return customerRepository.findAll();}

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer, int id){
        Customer existing = getCustomerById(id);

        existing.setEmail(customer.getEmail());
        existing.setPassword(customer.getPassword());
        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setAddressLine1(customer.getAddressLine1());
        existing.setAddressLine2(customer.getAddressLine2());
        existing.setCity(customer.getCity());
        existing.setState(customer.getState());
        existing.setCountry(customer.getCountry());
        existing.setZipcode(customer.getZipcode());
        existing.setPhone(customer.getPhone());
        existing.setRegisterDate(customer.getRegisterDate());

        customerRepository.save(existing);


        return existing;
    }

    public Customer getCustomerById(int id){
        return customerRepository.findById(id).orElse(null);
    }
    public void deleteCustomer(int id){
        customerRepository.deleteById(id);
    }

    //check email already exists or not
    public boolean checkEmailExist(Customer customer){
        boolean existed = false;
        List<Customer> list_customers = getAllCustomers();
        for (Customer c : list_customers){
            if(customer.getEmail().equals(c.getEmail())){
                existed = true;
                break;
            }
        }
        return existed;
    }




}
