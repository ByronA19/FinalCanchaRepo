package com.project.canchas.controller;

import com.project.canchas.interfaceService.IcanchaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.canchas.model.Cancha;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class CanchasController {
    
    @Autowired
    private IcanchaService service;
    
    @GetMapping("/pitches")
    public String all(Model model) {
        List<Cancha>canchas = service.all();
        model.addAttribute("canchas", canchas);
        return "pitches/index";
    }
    
    @PostMapping("/pitches/all")
    public ResponseEntity<List<Cancha>> getAll(Model model) {
        List<Cancha>canchas = service.all();        
        return ResponseEntity.ok(canchas);
    }
    
    @GetMapping("/pitches/add")
    public String add(Model model) {        
        Optional<Cancha>cancha = Optional.empty();
        model.addAttribute("pitch", cancha);
        return "pitches/edit";
    }
    
    @GetMapping("/pitches/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Cancha>cancha = service.get(id);
        model.addAttribute("pitch", cancha);
        return "pitches/edit";
    }
    
    @PostMapping("/pitches/save")
    public String save(@Valid Cancha u, Model model, @RequestParam("file") MultipartFile file) {
        
        if ( file != null && !file.isEmpty() ) {
            u.setImagen(file.getOriginalFilename());
        }
        else {
            if ( u.getId() != null ) {
                u.setImagen(service.get(u.getId()).get().getImagen());
            }
        }
        
        service.save(u);
        
        if ( file != null && !file.isEmpty() ) {
            
            try {
                String UPLOADED_FOLDER = "src/main/resources/static/uploads/pitches/" + u.getId() + "/";
                
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
        
        return "redirect:/pitches";
    }
    
    @GetMapping("/pitches/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        service.delete(id);
        return "redirect:/pitches";
    }
}
