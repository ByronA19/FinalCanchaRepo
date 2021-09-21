package com.project.canchas.interfaceService;

import com.project.canchas.model.Cancha;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface IcanchaService {
    
    public List<Cancha>all();
    public Optional<Cancha>get(int id);
    public int save(Cancha u);
    public void delete(int id);
}
