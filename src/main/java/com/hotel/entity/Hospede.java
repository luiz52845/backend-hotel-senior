package com.hotel.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Hospede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
   // @NotBlank(message = "Campo não preenchido")
    private String nome;
   // @NotBlank(message = "Campo não preenchido")
    private String documento;
  //  @NotBlank(message = "Campo não preenchido")
    private String telefone;

   // @ManyToOne
   // @JoinColumn(name = "checkIn_id", referencedColumnName = "id")
   // private CheckIn checkIn;

  //  private BigDecimal diariasNormais;
  //  private BigDecimal diariasFDS;
    //private BigDecimal diariasGaregemNormais;
   // private BigDecimal diariasGaregemFDS;
}