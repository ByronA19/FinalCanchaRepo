package com.project.canchas.interfaces;

import com.project.canchas.model.TipoCancha;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoCancha extends CrudRepository<TipoCancha, Integer> {
    
}
