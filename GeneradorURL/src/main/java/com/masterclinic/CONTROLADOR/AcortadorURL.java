/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.CONTROLADOR;

import com.masterclinic.MODELO.Cita;
import com.masterclinic.MODELO.Url;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLEditorKit;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gott Jagger
 */
/**
 *
 * @author Gott Jagger
 */
public class AcortadorURL {

    private static ArrayList<Url> ExtraerURL(ArrayList<Cita> listaCitas) {

        ArrayList<Url> listaUrl = new ArrayList<>();
        for (int i = 0; i < listaCitas.size(); i++) {
            Url url = new Url();
            url.setUrl("http://192.168.137.1:8080/GenratorURL/ServletGenerarPDF?uuid=" + String.valueOf(listaCitas.get(i).getUuid()));
            listaUrl.add(url);
        }
        return listaUrl;
    }

    private static ArrayList<Url> AcortarURL(ArrayList<Url> listaURL) {
        ArrayList<Url> listaUrlAcortada = new ArrayList<>();

        for (int i = 0; i < listaURL.size(); i++) {
            try {
                Url UrlAcortada = new Url();
                URL url = new URL("https://cutt.ly/api/api.php?key=d20dd473ff9f3c2ac18e39ee48dd4508bd119&short=" + listaURL.get(i).getUrl());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject json = new JSONObject(sb.toString());
                JSONObject jsonHijo = json.getJSONObject("url");
                
                br.close();
                

                UrlAcortada.setUrl(jsonHijo.getString("shortLink"));
                listaUrlAcortada.add(UrlAcortada);

            } catch (MalformedURLException ex) {
                Logger.getLogger(AcortadorURL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AcortadorURL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listaUrlAcortada;
    }

    public static void main(String[] args) {
        TratamientoDatos datos = new TratamientoDatos();
        ArrayList<Cita> datosCita = datos.llenarCita(datos.extraerDatosCita());
        ArrayList<Url> lista= AcortarURL(ExtraerURL(datosCita));
        
    }
}
