package cg.codegym.exam.service;

import cg.codegym.exam.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGenerateService<Customer> {
    Page<Customer> findAll(Pageable pageable);
    Customer findOne(Long id) throws Exception;
    Page<Customer> findAllByNameContaining(Pageable pageable, String name);
    Customer findByID(Long id);
}
