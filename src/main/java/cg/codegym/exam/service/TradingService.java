package cg.codegym.exam.service;

import cg.codegym.exam.model.Trading;
import cg.codegym.exam.repository.ITradingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class TradingService implements ITradingService {

    @Autowired
    private ITradingRepository iTradingRepository;

    @Autowired
    private ITradingRepository iComputerRepository;
    @Override
    public Iterable<Trading> findAll() {
        return iComputerRepository.findAll();
    }

}
