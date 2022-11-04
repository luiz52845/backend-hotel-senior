package com.hotel.service;

import com.hotel.entity.Hospede;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repository.HospedeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    public ResponseEntity<Hospede> createUpdate(@RequestBody Hospede hospede) {
        return ResponseEntity.ok(hospedeRepository.save(hospede));
    }

    public Page<Hospede> listAll(Pageable pageable) {
        return hospedeRepository.findAll(pageable);
    }

    public Hospede findById(@PathVariable(value = "id") Long id) {
        Optional<Hospede> hospede = hospedeRepository.findById(id);
        return hospede.orElseThrow(() -> new ObjectNotFoundException("Id Inexistente: " + id, ""));

    }

    public void delete(Long id) {
        hospedeRepository.delete(findById(id));
    }

}
