/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.CONTROLADOR;

import com.masterclinic.MODELO.Cita;
import com.masterclinic.MODELO.Url;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.ArrayList;

/**
 *
 * @author Gott Jagger
 */
public class GestionSMS {

    public static final String ACCOUNT_SID = "ACf3ee91d324ac6d98ab206031cebb8240";
    public static final String AUTH_TOKEN = "28624246c986514420d87a4f82de0340";

    private static void CrearSMS(ArrayList<Cita> listaCitas, ArrayList<Url> listaUrlAc) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        for (int i = 0; i < listaCitas.size(); i++) {
            for (int j = 0; j < listaUrlAc.size(); j++) {
                if (listaCitas.get(i).getUuid().equals(listaUrlAc.get(j).getId())) {
                    PhoneNumber para = new PhoneNumber("+57" + listaCitas.get(i).getTelefono());
                    PhoneNumber de = new PhoneNumber("+12673231547");
                    String mensaje = "Estimado " + listaCitas.get(i).getPaciente() + " usted tiene"
                            + "una cita la fecha: " + listaCitas.get(i).getFecha() + " con el medico "
                            + listaCitas.get(i).getMedico() + " para mayor detalle click enlace: \n" + listaUrlAc.get(j).getUrl();

                    Message mensaje1 = Message.creator(
                            para,
                            de,
                            mensaje).create();
                    System.out.println(mensaje1.getSid() + "\n" + mensaje1.getStatus());
                    break;
                }
            }
        }
    }
}
