package com.hotel.controller;

import com.hotel.entity.CheckIn;
import com.hotel.entity.Hospede;
import com.hotel.repository.CheckInRepository;
import com.hotel.repository.HospedeRepository;
import com.hotel.service.CheckInService;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chekin")
public class CheckInController {

    @Autowired
    private CheckInService CheckInService;

    @Autowired
    private HospedeService hospedeService;
    @Autowired
    private HospedeRepository hospedeRepository;



    @PostMapping("/checkin")
    public ResponseEntity<CheckIn> save(@RequestBody @Validated CheckIn checkIn) {

        return CheckInService.createUpdate(checkIn);
    }

    @GetMapping("/checkin")
    public ResponseEntity<Page<CheckIn>> list(Pageable pageable) {
        return ResponseEntity.ok(CheckInService.listAll(pageable));
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

   // @GetMapping("/checkin/query")
  //  public ResponseEntity <List<CheckIn>> getByNomeOu(@RequestParam("query") String query){
  //      return new ResponseEntity<>(CheckInService.search(query), HttpStatus.OK);
   // }


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
