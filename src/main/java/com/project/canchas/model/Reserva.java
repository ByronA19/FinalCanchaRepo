package com.project.canchas.model;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cancha_id;
    private Integer usuario_id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    @Column(nullable = false)    
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date hora;
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Timestamp creado;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp modificado;
    private Boolean pagada = false;
    private Boolean cancelada = false;

    @ManyToOne
    @JoinColumn(name = "cancha_id", insertable = false, updatable = false)
    private Cancha cancha;
    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private Usuario usuario;

    public Reserva() {}

    public Reserva(Integer id, Integer cancha_id, Integer usuario_id, Date fecha, Date hora, Timestamp creado, Timestamp modificado) {
        this.id = id;
        this.cancha_id = cancha_id;
        this.usuario_id = usuario_id;
        this.fecha = fecha;
        this.hora = hora;
        this.creado = creado;
        this.modificado = modificado;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    
    
    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCancha_id() {
        return this.cancha_id;
    }

    public void setCancha_id(Integer cancha_id) {
        this.cancha_id = cancha_id;
    }

    public Integer getUsuario_id() {
        return this.usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(hora);
    }

    public void setHora(Date hora) {
        this.hora = hora;
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
    
    public Boolean getPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }    
    
    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }    
    
    public Double getPrecio() {
        
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");        
        try {
            Date hora_noche = parser.parse("15:59");
            if ( this.hora.after(hora_noche) ) {
                return this.cancha.getValor_noche();
            }
        } catch (ParseException ex) {
            Logger.getLogger(Reserva.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return this.cancha.getValor_dia();
    }
}
