package com.project.canchas.service;

import com.project.canchas.interfaceService.IusuarioService;
import com.project.canchas.interfaces.IUsuario;
import com.project.canchas.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IusuarioService {

    @Autowired
    private IUsuario data;
    
    @Override
    public List<Usuario> all() {
        return (List<Usuario>)data.findAll();
    }

    @Override
    public Optional<Usuario> get(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Usuario u) {
        int res = 0;
        Usuario user = data.save(u);
//        if ( !user.equals(null) ) {
//            res = 1;
//        }
        return res;
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
    @Override
    public Usuario findByUsername(String username) {
        return data.findByUsername(username);
    }

    @Override
    public Usuario findByEmail(String email) {
        return data.findByEmail(email);
    }

    @Override
    public Usuario findByCedula(String cedula) {
        return data.findByCedula(cedula);
    }
}
