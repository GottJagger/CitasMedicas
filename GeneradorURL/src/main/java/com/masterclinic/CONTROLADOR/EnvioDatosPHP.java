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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gott Jagger
 */
public class EnvioDatosPHP {

    public static String getParametrosString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    public static void RequestPHP(ArrayList<Cita> ListaCitas, ArrayList<Url> UrlAcortadas) {

        JSONArray jsonCitas = new JSONArray();

        try {

            URL url = new URL("http://localhost:3000/controlador/APIcita.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);

            for (int i = 0; i < ListaCitas.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uuid", ListaCitas.get(i).getUuid());
                jsonObject.put("id", ListaCitas.get(i).getId());
                jsonObject.put("paciente", ListaCitas.get(i).getPaciente());
                jsonObject.put("medico", ListaCitas.get(i).getMedico());
                jsonObject.put("empresa", ListaCitas.get(i).getEmpresa());
                jsonObject.put("entidad", ListaCitas.get(i).getEntidad());
                jsonObject.put("tipo_consulta", ListaCitas.get(i).getTipo_consulta());
                jsonObject.put("observaciones", ListaCitas.get(i).getObservaciones());
                jsonObject.put("fecha", ListaCitas.get(i).getFecha());
                for (int j = 0; j < UrlAcortadas.size(); j++) {
                    if (ListaCitas.get(i).getUuid().equals(UrlAcortadas.get(j).getId())) {
                        jsonObject.put("url_Arcortada", UrlAcortadas.get(j).getUrl());
                    }
                }
                jsonCitas.put(i, jsonObject);

            }
            
            try ( OutputStream os = con.getOutputStream()) {
                byte[] input = jsonCitas.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try ( BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(EnvioDatosPHP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EnvioDatosPHP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
