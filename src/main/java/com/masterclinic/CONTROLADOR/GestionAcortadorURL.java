/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.CONTROLADOR;

import com.masterclinic.MODELO.Cita;
import com.masterclinic.MODELO.DatosGlobales;
import com.masterclinic.MODELO.Url;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

/**
 *
 * @author Gott Jagger
 */
/**
 *
 * @author Gott Jagger
 */
public class GestionAcortadorURL {

    public static ArrayList<Url> ExtraerURL(ArrayList<Cita> listaCitas) {

        ArrayList<Url> listaUrl = new ArrayList<>();
        for (int i = 0; i < listaCitas.size(); i++) {
            Url url = new Url();
            
            url.setUuid(listaCitas.get(i).getUuid());
            url.setUrl("http://127.0.0.1/CodeIgniter/index.php/Citas/consultar?uuid=" + String.valueOf(listaCitas.get(i).getUuid()));
            
            listaUrl.add(url);
        }
        return listaUrl;
    }

    public static ArrayList<Url> AcortarURL(ArrayList<Url> listaURL, Vector<String> estadoAcURL) {
        DatosGlobales data = new DatosGlobales();
        ArrayList<Url> listaUrlAcortada = new ArrayList<>();
        String code;
        for (int i = 0; i < listaURL.size(); i++) {
            try {
                Url UrlAcortada = new Url();
                URL url = new URL("https://cutt.ly/api/api.php?key="+data.getKeyAcortadorURL()+"&short=" + listaURL.get(i).getUrl());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                
                estadoAcURL.add(""+conn.getResponseCode());
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
                UrlAcortada.setUuid(listaURL.get(i).getUuid());
                listaUrlAcortada.add(UrlAcortada);

            } catch (MalformedURLException ex) {
                Logger.getLogger(GestionAcortadorURL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GestionAcortadorURL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listaUrlAcortada;
    }

}
