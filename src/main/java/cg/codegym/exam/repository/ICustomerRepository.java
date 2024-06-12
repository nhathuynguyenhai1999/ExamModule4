package cg.codegym.exam.repository;

import cg.codegym.exam.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer,Long> {
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByNameContaining(Pageable pageable, String name);

    boolean existsByCode(String code);
}
