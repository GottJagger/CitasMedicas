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
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONArray;

/**
 *
 * @author Gott Jagger
 */
public class ObtencionDatos {

    Cita datosCita = null;
    protected Connection conectar = null;
    private final String urlDatosGlobales = "jdbc:mysql://localhost:3306/cita";
    private final String usuario = "root";
    private final String password = "";

    public static JSONArray extraerDatosCita() {
        JSONArray JsonAr = null;
        try {

            URLConnection connection = new URL("https://my.api.mockaroo.com/cita.json?key=9ae97290").openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            HttpURLConnection conn = (HttpURLConnection) connection;
            System.out.println("Respuesta de la base de Datos: " + conn.getResponseCode());
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                in.close();
                JsonAr = new JSONArray(sb.toString());

            } catch (IOException ex) {

                Logger.getLogger(ObtencionDatos.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(ObtencionDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObtencionDatos.class.getName()).log(Level.SEVERE, null, ex);

        }

        return JsonAr;
    }

    public static ArrayList<Cita> llenarCita(JSONArray citas) {
        ArrayList<Cita> datosCita = new ArrayList();

        try {

            for (int i = 0; i < citas.length(); i++) {
                Cita datosPaciente = new Cita();
                Date fecha = new SimpleDateFormat("yyyy-mm-dd").parse(citas.getJSONObject(i).getString("fecha"));

                datosPaciente.setUuid(generarUUID());
                datosPaciente.setId(citas.getJSONObject(i).getInt("id"));
                datosPaciente.setFecha(fecha);
                datosPaciente.setPaciente(citas.getJSONObject(i).getString("paciente"));
                datosPaciente.setMedico(citas.getJSONObject(i).getString("medico"));
                datosPaciente.setEmpresa(citas.getJSONObject(i).getString("empresa"));
                datosPaciente.setEntidad(citas.getJSONObject(i).getString("entidad"));
                datosPaciente.setTipo_consulta(citas.getJSONObject(i).getString("tipo_consulta"));
                datosPaciente.setObservaciones(citas.getJSONObject(i).getString("observaciones"));
//                datosPaciente.setTelefono(citas.getJSONObject(i).getString("telefono"));
//                datosPaciente.setTelefono("3218480935");
                datosPaciente.setTelefono("3218480935");
                datosCita.add(datosPaciente);

            }

            return datosCita;
        } catch (ParseException ex) {
            Logger.getLogger(ObtencionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datosCita;
    }

    private static UUID generarUUID() {
        UUID uuid = java.util.UUID.randomUUID();
        return uuid;
    }

}
