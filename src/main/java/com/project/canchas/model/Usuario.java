package com.project.canchas.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String email;
    private String celular;
    private String telefono;
    private String direccion;
    private String username;
    private String password;
    private String imagen = null;
    private int estatus = 1;    
    @Column(name = "rol_id")
    private int rolId = 2;
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Timestamp creado;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp modificado;
    
//    @OneToOne(mappedBy = "username")
//    private Library library;
    
    @ManyToOne
    @JoinColumn(name = "rol_id", insertable = false, updatable = false)
    private Rol rol;    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "usuario_id", updatable = false)
    private List<Reserva> reservas;    
    
    public Usuario() {}

    public Usuario(Integer id, String cedula, String nombre, String apellidos, String email, String celular, String telefono, String direccion, String username, String password, String imagen, Timestamp creado, Timestamp modificado, Rol rol) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.celular = celular;
        this.telefono = telefono;
        this.direccion = direccion;
        this.username = username;
        this.password = password;
        this.imagen = imagen;
        this.creado = creado;
        this.modificado = modificado;
        this.rol = rol;
    }
    

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuariox() {
        return username;
    }

    public void setUsuariox(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rol_id) {
        this.rolId = rol_id;
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

    public String getNombre_completo() {
        return this.nombre + " " + this.apellidos;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }        

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public String getPath() {
        return this.imagen != null ? "uploads/users/" + this.getId() + "/" + this.imagen : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
//    public List<Reserva>getReservas() {
//        return this.reservas;
//    }
}
