package com.project.canchas.service;

import com.project.canchas.interfaceService.IreservaService;
import com.project.canchas.interfaces.IReserva;
import com.project.canchas.model.Reserva;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService implements IreservaService {

    @Autowired
    private IReserva data;
    
    @Override
    public List<Reserva> all() {
        return (List<Reserva>)data.findAll();
    }

    @Override
    public Optional<Reserva> get(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Reserva u) {
        int res = 0;
        Reserva booking = data.save(u);
        return res;
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
    @Override
    public List<String> getAvailableHours(String date, Integer pitch_id) {
        return data.findAvailableHours(date, pitch_id);
    }
    
    @Override
    public List<Reserva> getAvailableHoursAndPitches(String date) {
        return data.findAvailableHoursAndPitches(date);
    }
    
    @Override
    public List<Reserva> getBookings(Integer id) {
        return data.findBookings(id);
    }       
}
