/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.CONTROLADOR;

import com.masterclinic.MODELO.Cita;
import com.masterclinic.MODELO.Url;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;

/**
 *
 * @author Gott Jagger
 */
public class EnvioDatosPHP {

 

    public static void RequestPHP(ArrayList<Cita> ListaCitas, ArrayList<Url> UrlAcortadas,Vector<String> estadoPHP) {

        JSONArray jsonCitas = new JSONArray();

//        try {
        for (int i = 0; i < ListaCitas.size(); i++) {
            String UrlParametros = "uuid=" + ListaCitas.get(i).getUuid() + "&"
                    + "id=" + ListaCitas.get(i).getId() + "&" + "paciente="
                    + ListaCitas.get(i).getPaciente() + "&" + "medico="
                    + ListaCitas.get(i).getMedico() + "&" + "empresa="
                    + ListaCitas.get(i).getEmpresa() + "&" + "entidad="
                    + ListaCitas.get(i).getEntidad() + "&" + "tipo_consulta="
                    + ListaCitas.get(i).getTipo_consulta() + "&" + "observaciones="
                    + ListaCitas.get(i).getObservaciones() + "&" + "fecha="
                    + ListaCitas.get(i).getFecha();
            for (int j = 0; j < UrlAcortadas.size(); j++) {
                if (ListaCitas.get(i).getUuid().equals(UrlAcortadas.get(j).getUuid())) {
                   UrlParametros= UrlParametros+"&url_Arcortada=" + UrlAcortadas.get(j).getUrl();
                    System.out.println(UrlParametros+"\n\n\n");
                }
            }
            
            try {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("text/plain");
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create("{}", JSON);
                Request request = new Request.Builder()
                        .url("http://127.0.0.1/CodeIgniter/index.php/Citas/Guardar?"+UrlParametros)
                        .method("POST", body)
                        .build();
                Response response = client.newCall(request).execute();
                
                estadoPHP.add(""+response.code());
            } catch (IOException ex) {
                Logger.getLogger(EnvioDatosPHP.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
