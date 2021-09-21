package com.project.canchas.controller;

import com.google.gson.GsonBuilder;
import com.project.canchas.interfaceService.IusuarioService;
import com.project.canchas.interfaceService.SecurityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.canchas.model.Usuario;
import groovyjarjarantlr4.v4.runtime.dfa.ArrayEdgeMap;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Keymap;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping
public class UsuariosController {
    
    @Autowired
    private IusuarioService service;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserDetailsService userDetailsService;
    
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        
        if (error != null)
            model.addAttribute("error", "Las credenciales no son correctas.");

        if (logout != null)
            model.addAttribute("message", "Has cerrado sesión exitosamente.");

        return "login/signin";
    }
    
//    @PostMapping("/login")
//    public String login_post() {
//        return "login/signin";
//    }
    
//    @GetMapping("/logout")
//    public String logout() {
//        return "login/signin";
//    }
    
    @GetMapping("/")
    public String index() {
        return "base/master";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        Optional<Usuario>usuario = Optional.empty();
        model.addAttribute("user", usuario);
        return "login/signup";
    }
    
    @GetMapping("/recover-password")
    public String recoverPassword(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        Optional<Usuario>usuario = Optional.empty();
        model.addAttribute("user", usuario);
        return "login/recover_password";
    }
    
    @PostMapping("/recoverpasswordpost")
    public String recoverPasswordPost(@Valid Usuario u, Model model, RedirectAttributes redirectAttributes) {
        
        Usuario user = service.findByEmail(u.getEmail());
        Usuario user2 = service.findByCedula(u.getCedula());
        
        if ( user == null || user2 == null || !user.getId().equals(user2.getId()) ) {
            redirectAttributes.addFlashAttribute("error", "Cédula o Correo electrónico inexistentes");
            return "redirect:/recover-password";
        }
        
        user.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        service.save(user);
        redirectAttributes.addFlashAttribute("message", "Tu contraseña ha sido actualizada. Puedes iniciar sesión!");
        
        return "redirect:/recover-password";
    }
    
    @PostMapping("/signup")
    public String signup(@Valid Usuario u, Model model, RedirectAttributes redirectAttributes) {
        
        Usuario user = service.findByEmail(u.getEmail());
        if ( user != null ) {
            redirectAttributes.addFlashAttribute("error", "Ya existe una cuenta asociada el email que ingresaste");
            return "redirect:/register";
        }
        
//        user = service.findByUsername(u.getUsername());
//        if ( user != null ) {
//            redirectAttributes.addFlashAttribute("error", "Ya existe una cuenta asociada el usuario que ingresaste");
//            return "redirect:/register";
//        }
        
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        service.save(u);
        redirectAttributes.addFlashAttribute("message", "Tu cuenta ha sido creada. Puedes iniciar sesión!");
        
        return "redirect:/login";
    }
    
    @PostMapping("/users/all")
    public ResponseEntity<List<Usuario>> getAll(Model model) {
        List<Usuario>usuarios = service.all();
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/users")
    public String all(Model model) {
        List<Usuario>usuarios = service.all();
        model.addAttribute("usuarios", usuarios);
        return "users/index";
    }        
    
    @GetMapping("/users/add")
    public String add(Model model) {        
        Optional<Usuario>usuario = Optional.empty();
        model.addAttribute("user", usuario);
        model.addAttribute("roles", new HashMap<Integer, String>(){{
            put(1, "Administrador");
            put(2, "Cliente");
        }});
        return "users/edit";
    }
    
    @GetMapping("/profile")
    public String edit(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Usuario>usuario = Optional.of((Usuario)auth.getPrincipal());
        model.addAttribute("user", usuario);
        model.addAttribute("roles", new HashMap<Integer, String>(){{
            put(1, "Administrador");
            put(2, "Cliente");
        }});
        return "users/edit";
    }
    
    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
//        System.out.println("xxxxxxxx");
        Optional<Usuario>usuario = service.get(id);
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(usuario.get()));
        model.addAttribute("user", usuario);
        model.addAttribute("roles", new HashMap<Integer, String>(){{
            put(1, "Administrador");
            put(2, "Cliente");
        }});
        return "users/edit";
    }
    
    @PostMapping("/users/save")
    public String save(@Valid Usuario u, Model model, @RequestParam("rol.id") Integer rol_id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        
        Usuario user = service.findByEmail(u.getEmail());
        if ( user != null && !user.getId().equals(u.getId())) {
            redirectAttributes.addFlashAttribute("error", "Ya existe una cuenta asociada el email que ingresaste");
            return "redirect:/users/" + (u.getId() == null ? "add" : "edit/" + u.getId());
        }
        
        user = service.findByCedula(u.getCedula());
        if ( user != null && !user.getId().equals(u.getId())) {
            redirectAttributes.addFlashAttribute("error", "Ya existe una cuenta asociada a la cédula que ingresaste");
            return "redirect:/users/" + (u.getId() == null ? "add" : "edit/" + u.getId());
        }
        
//        user = service.findByUsername(u.getUsername());
//        if ( user != null && !user.getId().equals(u.getId())) {
//            redirectAttributes.addFlashAttribute("error", "Ya existe una cuenta asociada el usuario que ingresaste");
//            return "redirect:/users/" + (u.getId() == null ? "add" : "edit/" + u.getId());
//        }
        
        if ( u.getPassword().trim().equals("") && u.getId() != null ) {
            Usuario usuario = service.get(u.getId()).get();
            u.setPassword(usuario.getPassword());
        }
        else {
            u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        }

        if ( file != null && !file.isEmpty() ) {
            u.setImagen(file.getOriginalFilename());
        }
        else {
            if ( u.getId() != null ) {
                u.setImagen(service.get(u.getId()).get().getImagen());
            }
        }
        
        if ( u.getImagen().trim().equals("") ) {
            u.setImagen(null);
        }
        
        u.setDireccion(u.getDireccion().toUpperCase());
        u.setRolId(rol_id);
        service.save(u);
        
        if ( file != null && !file.isEmpty() ) {
            System.out.println("upload");
            try {
                String UPLOADED_FOLDER = "src/main/resources/static/uploads/users/" + u.getId() + "/";
//                Files.delete(Paths.get(UPLOADED_FOLDER));
                
                java.io.File pathAsFile = new java.io.File(UPLOADED_FOLDER);
                FileSystemUtils.deleteRecursively(pathAsFile);
                
                if (!Files.exists(Paths.get(UPLOADED_FOLDER))) {
                    pathAsFile.mkdirs();
                }                                

                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(((Usuario)auth.getPrincipal()).getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        if ( ((Usuario)auth.getPrincipal()).getRol().getNombre().equals("Cliente") ) {
            redirectAttributes.addFlashAttribute("message", "La información ha sido actualizada");
            return "redirect:/profile";
        }
        return "redirect:/users";
    }
    
    @GetMapping("/users/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        service.delete(id);
        return "redirect:/users";
    }
}
