package cg.codegym.exam.service;

import cg.codegym.exam.model.Customer;
import cg.codegym.exam.repository.ICustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return iCustomerRepository.findAll();
    }
    @Override
    public Optional<Computer> findById(Long id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iComputerRepository.deleteById(id);
    }


    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return iCustomerRepository.findAll(pageable);
    }

    @Override
    public Customer findOne(Long id) throws Exception {
        Customer computer = new Customer();
        if(computer.getName() == null){
            throw new Exception("Computer not found");
        }
        return computer;
    }
    @Override
    public Page<Customer> findAllByNameContaining(Pageable pageable, String name) {
        return iCustomerRepository.findAllByNameContaining(pageable, name);
    }
    //fix
    @Override
    public Customer findByID(Long id){
        return iCustomerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computer not found with ID: " + id));
    }
}
