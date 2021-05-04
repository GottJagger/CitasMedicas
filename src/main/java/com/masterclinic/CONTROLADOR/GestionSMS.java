/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.CONTROLADOR;

import com.masterclinic.MODELO.Cita;
import com.masterclinic.MODELO.DatosGlobales;
import com.masterclinic.MODELO.Url;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Gott Jagger
 */
public class GestionSMS {

    DatosGlobales data = new DatosGlobales();

    public void CrearSMS(ArrayList<Cita> listaCitas, ArrayList<Url> listaUrlAc, Vector<String> estadoSMS) {
        Twilio.init(data.getACCOUNT_SID(), data.getAUTH_TOKEN());

        for (int i = 0; i < listaCitas.size(); i++) {
            for (int j = 0; j < listaUrlAc.size(); j++) {
                if (listaCitas.get(i).getUuid().equals(listaUrlAc.get(j).getUuid())) {
                    PhoneNumber para = new PhoneNumber("+57" + listaCitas.get(i).getTelefono());
                    System.out.println(listaCitas.get(i).getTelefono());
                    PhoneNumber de = new PhoneNumber(data.getTelefonoTiwilio());
                    String mensaje = "Estimado " + listaCitas.get(i).getPaciente() + " usted tiene"
                            + "una cita la fecha: " + listaCitas.get(i).getFecha() + " con el medico "
                            + listaCitas.get(i).getMedico() + " para mayor detalle click enlace: \n" + listaUrlAc.get(j).getUrl();
                    Message mensaje1 = Message.creator(
                            para,
                            de,
                            mensaje).create();
                    estadoSMS.add("" + mensaje1.getStatus());
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        DatosGlobales dataTest = new DatosGlobales();
        System.out.println("datos SID:" + dataTest.getACCOUNT_SID() + "\ndatos Token:" + dataTest.getAUTH_TOKEN()+"\ndatos Telefono:"+dataTest.getTelefonoTiwilio());        
    }

}
