package com.project.canchas.interfaceService;

import com.project.canchas.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface IusuarioService {
    
    public List<Usuario>all();
    public Optional<Usuario>get(int id);
    public int save(Usuario u);
    public void delete(int id);
    
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
    Usuario findByCedula(String cedula);
}
