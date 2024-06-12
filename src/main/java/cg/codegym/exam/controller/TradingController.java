package cg.codegym.exam.controller;

import cg.codegym.exam.model.Customer;
import cg.codegym.exam.model.Trading;
import cg.codegym.exam.service.TradingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/trading")
public class TradingController {
    @Autowired
    private TradingService tradingService;

    @GetMapping
    public ModelAndView listTrading(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("/computer/list");
        try {
            Pageable pageable = PageRequest.of(page, 3);
            Page<Trading> tradings = tradingService.findAll(pageable);
            modelAndView.addObject("tradings", tradings);
        } catch (Exception e) {
            modelAndView.addObject("error", "An error occurred while retrieving the list of computers.");
            // You can also log the error if needed
            // logger.error("Error occurred while listing computers", e);
        }
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView listTradingSearch(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Trading> customers;
        if(search.isPresent()){
            customers = tradingService.findAllByNameContaining(pageable, search.get());
        } else {
            customers = tradingService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/computer/list");
        modelAndView.addObject("computers", customers);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/computer/create");
        modelAndView.addObject("computer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("computer") Customer customer,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/computer/create");
            modelAndView.addObject("computer", customer);
            return modelAndView;
        }

        computerService.save(computer);
        redirectAttributes.addFlashAttribute("message", "Create new computer successfully");
        return new ModelAndView("redirect:/computers");
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Computer> computer = computerService.findById(id);
        if (computer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/computer/update");
            modelAndView.addObject("computer", computer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@Valid @ModelAttribute("computer") Customer customer,
                               BindingResult bindingResult,
                               RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/computer/update");
            modelAndView.addObject("computer", computer);
            return modelAndView;
        }

        computerService.save(computer);
        redirect.addFlashAttribute("message", "Update computer successfully");
        return new ModelAndView("redirect:/computers");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        computerService.remove(id);
        redirect.addFlashAttribute("message", "Delete computer successfully");
        return "redirect:/computers";
    }
    @ExceptionHandler(DuplicateProductCodeException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/duplicate-code");
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action) throws Exception {
        Optional<Computer> productOptional = computerService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error_404";
        }
        if (action.equals("show")) {
            cart.addComputer(productOptional.get());
            return "redirect:/cart";
        }
        cart.addComputer(productOptional.get());
        return "redirect:/computer";
    }
}
