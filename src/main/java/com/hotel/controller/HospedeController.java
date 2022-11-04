package com.hotel.controller;
import com.hotel.entity.Hospede;
import com.hotel.repository.HospedeRepository;
import com.hotel.service.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hospede")
public class HospedeController {

    @Autowired
    private HospedeService hospedeService;
    @Autowired
    private HospedeRepository hospedeRepository;

    @PostMapping("/hospede")
    public ResponseEntity<Hospede> save(@RequestBody @Validated Hospede hospede) {
        return hospedeService.createUpdate(hospede);
    }

    @GetMapping()
    public ResponseEntity<Page<Hospede>> list(Pageable pageable) {
        return ResponseEntity.ok(hospedeService.listAll(pageable));
    }

    @GetMapping("/hospede/{id}")
    public ResponseEntity<Hospede> finById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(hospedeService.findById(id));
    }

    @DeleteMapping("/hospede/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        hospedeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hospede/nomeou")
    public ResponseEntity <List<Hospede>> getByNomeOu(@RequestParam String nome, @RequestParam String documento, @RequestParam String telefone){
        return new ResponseEntity<>(hospedeRepository.findByNomeOrDocumentoOrTelefone(nome, documento, telefone), HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fildname = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fildname, errorMessage);
        });
        return errors;
    }
}
