/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.CONTROLADOR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.masterclinic.MODELO.Cita;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gott Jagger
 */
public class TratamientoDatos {

    private static Cita datosCita = null;
    
    public static JSONObject extraerDatosCita() {
        JSONObject js = null;
        try {

            URLConnection connection = new URL("https://my.api.mockaroo.com/cita.json?key=dfef97c0").openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                String line = in.readLine();
                js = new JSONObject(line);

                in.close();

            } catch (IOException ex) {

                Logger.getLogger(TratamientoDatos.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (MalformedURLException ex) {

            Logger.getLogger(TratamientoDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TratamientoDatos.class.getName()).log(Level.SEVERE, null, ex);

        }

        return js;
    }

    public static  Cita llenarCita(JSONObject citas) {
        
        try {
            datosCita = new Cita();
            Date fecha = new SimpleDateFormat("yyyy-mm-dd").parse(citas.getString("fecha"));
            datosCita.setId(citas.getInt("id"));
            datosCita.setPaciente(citas.getString("paciente"));
            datosCita.setMedico(citas.getString("medico"));
            datosCita.setEmpresa(citas.getString("empresa"));
            datosCita.setEntidad(citas.getString("entidad"));
            datosCita.setTipo_consulta(citas.getString("tipo_consulta"));
            datosCita.setObservaciones(citas.getString("observaciones"));
            datosCita.setFecha(fecha);
            
            return datosCita;
        } catch (ParseException ex) {
            Logger.getLogger(TratamientoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datosCita;
    }
    
}
