package com.hotel.service;

import com.hotel.entity.CheckIn;
import com.hotel.repository.CheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalQuery;

@Service
public class CheckInService {


    @Autowired
    private CheckInRepository checkInRepository;

    public ResponseEntity<CheckIn> createUpdate(@RequestBody CheckIn checkIn) {

        if (checkIn.getDataEntrada() != null && checkIn.getDataSaida() != null) {



            // TemporalQuery que verifica se é fim de semana
            TemporalQuery<Boolean> fds = t -> {
                DayOfWeek dow = DayOfWeek.from(t);
                return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
            };

            long days = ChronoUnit.DAYS.between(checkIn.getDataEntrada(),checkIn.getDataSaida());
            Duration t1 = Duration.between(checkIn.getDataEntrada(),checkIn.getDataSaida());
            int dias = (int) t1.toDays();
            int dias2 = (int) t1.toDaysPart();
            for (int x = 0; x < days; x ++){

           }

            return ResponseEntity.ok(checkInRepository.save(checkIn));

        } else if (checkIn.getDataEntrada() != null && checkIn.getDataSaida() == null) {
            var data = checkIn.getDataEntrada();
            checkIn.setDataEntrada(data);
            return ResponseEntity.ok(checkInRepository.save(checkIn));

        } else {
            checkIn.setDataEntrada(null);
            checkIn.setDataSaida(null);
            return ResponseEntity.ok(checkInRepository.save(checkIn));
        }

    }


    public Page<CheckIn> listAll(Pageable pageable) {
        return checkInRepository.findAll(pageable);
    }
/*
    public CheckIn findById(@PathVariable(value = "id") Long id) {
        Optional<CheckIn> checkIn = checkInRepository.findById(id);
        return checkIn.orElseThrow(() -> new ObjectNotFoundException("Id Inexistente: " + id, ""));

    }

    public void delete(Long id) {
        checkInRepository.delete(findById(id));
    }
    */


}
