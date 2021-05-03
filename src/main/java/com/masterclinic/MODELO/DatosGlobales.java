/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.MODELO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gott Jagger
 */
public class DatosGlobales {

    String ACCOUNT_SID;
    String AUTH_TOKEN;
    String telefonoTiwilio;
    String keyAcortadorURL;
    int Hora;
    int minuto;
    int segundo;

    protected Connection conectar = null;
    private final String url = "jdbc:mysql://localhost:3306/cita";
    private final String usuario = "root";
    private final String password = "";

    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public String getTelefonoTiwilio() {
        return telefonoTiwilio;
    }

    public String getKeyAcortadorURL() {
        return keyAcortadorURL;
    }

    public int getHora() {
        return Hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setACCOUNT_SID(String ACCOUNT_SID) {
        this.ACCOUNT_SID = ACCOUNT_SID;
    }

    public void setAUTH_TOKEN(String AUTH_TOKEN) {
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    public void setTelefonoTiwilio(String telefonoTiwilio) {
        this.telefonoTiwilio = telefonoTiwilio;
    }

    public void setKeyAcortadorURL(String keyAcortadorURL) {
        this.keyAcortadorURL = keyAcortadorURL;
    }

    public void setHora(int Hora) {
        this.Hora = Hora;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    public DatosGlobales() {

    }

    public DatosGlobales(String ACCOUNT_SID, String AUTH_TOKEN, String telefonoTiwilio, String keyAcortadorURL, int Hora, int minuto, int segundo) {
        this.ACCOUNT_SID = ACCOUNT_SID;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.telefonoTiwilio = telefonoTiwilio;
        this.keyAcortadorURL = keyAcortadorURL;
        this.Hora = Hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    public void abrirConexion() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión Exitosa");

        } catch (SQLException ex) {
            System.out.println("Error al abrir Conexión: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatosGlobales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDatos() {

        try {

            PreparedStatement stmt = conectar.prepareStatement("SELECT * FROM datos_globales_sistema_java");
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatosGlobales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
