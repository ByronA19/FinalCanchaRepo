package com.project.canchas.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "canchas")
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer numero_canchas;
    private String telefono;
    private String email;
    private Double valor_dia;
    private Double valor_noche;
    private String imagen;
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Timestamp creado;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp modificado;
    
    public Cancha() {}

    public Cancha(Integer id, String nombre, String descripcion, Integer numero_canchas, String telefono, String email, Double valor_dia, Double valor_noche, String imagen, Timestamp creado, Timestamp modificado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numero_canchas = numero_canchas;
        this.telefono = telefono;
        this.email = email;
        this.valor_dia = valor_dia;
        this.valor_noche = valor_noche;
        this.imagen = imagen;
        this.creado = creado;
        this.modificado = modificado;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Integer getNumero_canchas() {
        return numero_canchas;
    }

    public void setNumero_canchas(Integer numero_canchas) {
        this.numero_canchas = numero_canchas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getValor_dia() {
        return valor_dia;
    }

    public void setValor_dia(Double valor_dia) {
        this.valor_dia = valor_dia;
    }

    public Double getValor_noche() {
        return valor_noche;
    }

    public void setValor_noche(Double valor_noche) {
        this.valor_noche = valor_noche;
    }    

    public Timestamp getCreado() {
        return creado;
    }

    public void setCreado(Timestamp creado) {
        this.creado = creado;
    }

    public Timestamp getModificado() {
        return modificado;
    }

    public void setModificado(Timestamp modificado) {
        this.modificado = modificado;
    }
    
    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public String getPath() {
        return this.imagen != null ? "uploads/pitches/" + this.getId() + "/" + this.imagen : null;
    }
    
}
