package com.project.canchas.interfaceService;

import com.project.canchas.model.Reserva;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface IreservaService {
    
    public List<Reserva>all();
    public Optional<Reserva>get(int id);
    public int save(Reserva u);
    public void delete(int id);
    public List<String> getAvailableHours(String date, Integer pitch_id);
    public List<Reserva> getBookings(Integer id);
    public List<Reserva> getAvailableHoursAndPitches(String date);
}
