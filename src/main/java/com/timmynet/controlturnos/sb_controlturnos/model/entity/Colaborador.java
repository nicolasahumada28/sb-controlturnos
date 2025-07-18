package com.timmynet.controlturnos.sb_controlturnos.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "colaboradores")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del colaborador", example = "1")
    private Long id;

    @Column(name = "nombre")
    @Schema(description = "Nombre del colaborador", example = "Juan Pérez")
    private String nombre;

    @Column(name = "email")
    @Schema(description = "Correo electrónico del colaborador", example = "mail@mail.com")
    private String email;

    @Column(name = "rut")
    @Schema(description = "RUT del colaborador", example = "12345678-9")
    private String rut;

    @Column(name = "edad")
    @Schema(description = "Edad del colaborador", example = "30")
    private int edad;

    @Column(name = "telefono")
    @Schema(description = "Teléfono del colaborador", example = "987654321")
    private int telefono;

    @Column(name = "direccion")
    @Schema(description = "Dirección del colaborador", example = "Av. Siempre Viva 123")
    private String direccion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name ="fecha_creacion")
    @Schema(description = "Fecha de creación del colaborador", example = "01-01-2023 12:00:00")
    private LocalDateTime fechaCreacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "fecha_modificacion")
    @Schema(description = "Fecha de modificación del colaborador", example = "01-01-2023 12:00:00")
    private LocalDateTime fechaModificacion;

    @Column(name = "activo")
    @Schema(description = "Estado activo del colaborador", example = "true")
    private boolean activo;

    @Column(name = "supervisor")
    @Schema(description = "Indica si el colaborador es supervisor", example = "false")
    private boolean supervisor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "inicio_contrato")
    @Schema(description = "Fecha de inicio del contrato del colaborador", example = "01-01-2023")
    private LocalDate inicioContrato;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @Schema(description = "Rol del colaborador", example = "ADMIN")
    private Rol rol;
    
    @PrePersist
    public void prePersist(){
        this.setFechaCreacion(LocalDateTime.now(ZoneId.of("America/Santiago")));
        this.setFechaModificacion(LocalDateTime.now(ZoneId.of("America/Santiago")));
    }

    @PreUpdate
    public void preUpdate(){
        this.setFechaModificacion(LocalDateTime.now(ZoneId.of("America/Santiago")));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isSupervisor() {
        return supervisor;
    }

    public void setSupervisor(boolean supervisor) {
        this.supervisor = supervisor;
    }

    public LocalDate getInicioContrato() {
        return inicioContrato;
    }

    public void setInicioContrato(LocalDate inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    @Override
    public String toString() {
        return "Colaborador{ id=" + id + ", nombre='" + nombre + "', email='" + email + "', rut='" + rut + "', edad=" + edad + ", telefono=" + telefono +", direccion='" + direccion +"', fechaCreacion=" + fechaCreacion +", fechaModificacion=" + fechaModificacion +", activo=" + activo +", supervisor=" + supervisor +", inicioContrato=" + inicioContrato +", Rol=" + rol + "}"; 
    }

}
