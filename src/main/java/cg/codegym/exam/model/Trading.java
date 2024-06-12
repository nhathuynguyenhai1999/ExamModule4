package cg.codegym.exam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "tradingtions")
public class Trading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customers_id")
    private Customer customer;
    private LocalDate dayTrading;
    private String serviceType;
    private Double price;
    private Double area;
    public Trading() {
    }

}