package com.project.canchas.interfaceService;

import com.project.canchas.model.TipoCancha;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface ItipocanchaService {
    
    public List<TipoCancha>all();
    public Optional<TipoCancha>get(int id);
    public int save(TipoCancha u);
    public void delete(int id);
}
