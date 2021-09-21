package com.project.canchas.interfaces;

import com.project.canchas.model.Cancha;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICancha extends CrudRepository<Cancha, Integer> {
    
}
