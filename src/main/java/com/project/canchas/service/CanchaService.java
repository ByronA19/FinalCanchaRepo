package com.project.canchas.service;

import com.project.canchas.interfaceService.IcanchaService;
import com.project.canchas.interfaces.ICancha;
import com.project.canchas.model.Cancha;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanchaService implements IcanchaService {

    @Autowired
    private ICancha data;
    
    @Override
    public List<Cancha> all() {
        return (List<Cancha>)data.findAll();
    }

    @Override
    public Optional<Cancha> get(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Cancha u) {
        int res = 0;
        Cancha pitch = data.save(u);
        return res;
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
