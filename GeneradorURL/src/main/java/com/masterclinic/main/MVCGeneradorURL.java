/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.main;

import com.masterclinic.CONTROLADOR.EnvioDatosPHP;
import com.masterclinic.CONTROLADOR.GestionAcortadorURL;
import com.masterclinic.CONTROLADOR.GestionSMS;
import com.masterclinic.CONTROLADOR.ObtencionDatos;
import com.masterclinic.MODELO.Cita;
import com.masterclinic.MODELO.Url;
import java.util.ArrayList;

/**
 *
 * @author Gott Jagger
 */
public class MVCGeneradorURL {

    public static void main(String[] args) {
        //se obtienen los datos de la base de datos

        ObtencionDatos datos = new ObtencionDatos();
        ArrayList<Cita> datosCita = datos.llenarCita(datos.extraerDatosCita());

        //se instancia las clases para realizar la gestion general.
        GestionAcortadorURL acortardorUrl = new GestionAcortadorURL();
        EnvioDatosPHP expedidorDatosCitas = new EnvioDatosPHP();
        GestionSMS gestorSMS = new GestionSMS();

        //se comienza a trabajar los metodos.
        ArrayList<Url> listaUrlacortadas = acortardorUrl.AcortarURL(acortardorUrl.ExtraerURL(datosCita));

        expedidorDatosCitas.RequestPHP(datosCita, listaUrlacortadas);
        
        gestorSMS.CrearSMS(datosCita, listaUrlacortadas);
        
    }
}
