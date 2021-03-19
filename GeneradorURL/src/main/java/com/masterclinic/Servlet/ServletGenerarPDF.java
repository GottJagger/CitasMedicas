/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.Servlet;

import com.masterclinic.CONTROLADOR.GeneradorPDF;
import com.masterclinic.CONTROLADOR.TratamientoDatos;
import com.masterclinic.MODELO.Cita;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gott Jagger
 */
@WebServlet(name = "ServletGenerarPDF", urlPatterns = {"/ServletGenerarPDF"})
public class ServletGenerarPDF extends HttpServlet {

    TratamientoDatos datos = new TratamientoDatos();
    ArrayList<Cita> datosCita = datos.llenarCita(datos.extraerDatosCita());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
        for (int i = 0; i < datosCita.size(); i++) {

            if (request.getParameter("uuid").equals(String.valueOf(datosCita.get(i).getUuid()))) {
                response.addHeader("id", String.valueOf(datosCita.get(i).getId()));
                response.addHeader("paciente", datosCita.get(i).getPaciente());
                response.addHeader("medico", datosCita.get(i).getMedico());
                response.addHeader("fecha", datosCita.get(i).getFecha().toString());
                response.addHeader("empresa", datosCita.get(i).getEmpresa());
                response.addHeader("entidad", datosCita.get(i).getEntidad());
                response.addHeader("tipo_consulta", datosCita.get(i).getTipo_consulta());
                response.addHeader("observaciones", datosCita.get(i).getObservaciones());

                response.setContentType("application/pdf");
                OutputStream out = response.getOutputStream();
                try {

                    GeneradorPDF pdf = new GeneradorPDF();

                    pdf.crearPDF(datosCita.get(i), out);
                } finally {
                    out.close();
                }
                out.flush();
                out.close();
                break;

            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
