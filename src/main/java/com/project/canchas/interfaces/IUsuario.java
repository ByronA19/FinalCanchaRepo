package com.project.canchas.interfaces;

import com.project.canchas.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
    Usuario findByCedula(String cedula);
}
