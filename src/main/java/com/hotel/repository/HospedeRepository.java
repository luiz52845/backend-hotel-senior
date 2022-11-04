package com.hotel.repository;

import com.hotel.entity.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    List<Hospede> findByNomeOrDocumentoOrTelefone(String nome, String documento, String telefone);
}
