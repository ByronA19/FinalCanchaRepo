package com.project.canchas.interfaces;

import com.project.canchas.model.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IReserva extends CrudRepository<Reserva, Integer> {
    
    @Query("SELECT date_format(r.hora, '%H:%i') FROM Reserva r where r.fecha = str_to_date(:date, '%Y-%m-%d') and r.cancha_id = :pitch_id and r.cancelada = 0")
    List<String>findAvailableHours(@Param("date") String date, @Param("pitch_id") Integer pitch_id);
    
    @Query("SELECT r FROM Reserva r where r.fecha = str_to_date(:date, '%Y-%m-%d') and r.cancelada = 0 order by r.cancha_id, r.hora")
    List<Reserva>findAvailableHoursAndPitches(@Param("date") String date);
    
    @Query("SELECT r FROM Reserva r where r.usuario_id = :id")
    List<Reserva>findBookings(@Param("id") Integer id);
}
