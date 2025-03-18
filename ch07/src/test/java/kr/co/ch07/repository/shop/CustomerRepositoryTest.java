package kr.co.ch07.repository.shop;

import kr.co.ch07.entity.shop.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;


    @Test
    public void test1(){
        List<Customer> customerList = customerRepository.selectAllCustomer();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }

    }

    @Test
    public void test2(){
        Customer customer = customerRepository.selectCustomer("a101");
        System.out.println(customer);
    }
}