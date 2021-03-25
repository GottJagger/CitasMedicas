/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.MODELO;

import java.util.Date;
import java.util.UUID;


/**
 *
 * @author Gott Jagger
 */
public class Cita {

    private UUID uuid;
    private int id;
    private String medico;
    private String paciente;
    private Date fecha;
    private String empresa;
    private String tipo_consulta;
    private String entidad;
    private String observaciones;
    private String telefono;

    public Cita() {
    }

    public Cita(UUID uuid, int id, String medico, String paciente, Date fecha, String empresa, String tipo_consulta, String entidad, String observaciones, String telefono,String email) {
        this.uuid = uuid;
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.empresa = empresa;
        this.tipo_consulta = tipo_consulta;
        this.entidad = entidad;
        this.observaciones = observaciones;
        this.telefono = telefono;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTipo_consulta() {
        return tipo_consulta;
    }

    public void setTipo_consulta(String tipo_consulta) {
        this.tipo_consulta = tipo_consulta;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    

   
}
