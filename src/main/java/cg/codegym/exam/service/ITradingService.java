package cg.codegym.exam.service;

import cg.codegym.exam.model.Trading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITradingService extends IGenerateService<Trading> {
    Page<Trading> findAll(Pageable pageable);

    Trading findOne(Long id) throws Exception;
    Page<Trading> findAllByNameContaining(Pageable pageable, String name);
}
