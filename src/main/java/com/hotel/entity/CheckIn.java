package com.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalQuery;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private Boolean adicionalVeiculo;

    @ManyToOne
    @JoinColumn(name = "hospede_id", referencedColumnName = "id")
    private Hospede hospede;



}
