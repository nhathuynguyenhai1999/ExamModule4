package cg.codegym.exam.repository;

import cg.codegym.exam.model.Trading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ITradingRepository extends CrudRepository<Trading, Long> {
    Page<Trading> findAll(Pageable pageable);
}
