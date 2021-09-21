package com.project.canchas.service;

import com.project.canchas.interfaceService.ItipocanchaService;
import com.project.canchas.interfaces.ITipoCancha;
import com.project.canchas.model.TipoCancha;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoCanchaService implements ItipocanchaService {

    @Autowired
    private ITipoCancha data;
    
    @Override
    public List<TipoCancha> all() {
        return (List<TipoCancha>)data.findAll();
    }

    @Override
    public Optional<TipoCancha> get(int id) {
        return data.findById(id);
    }

    @Override
    public int save(TipoCancha t) {
        int res = 0;
        TipoCancha type = data.save(t);
        return res;
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }

    
}
