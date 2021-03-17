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
    Cita datosCita = datos.llenarCita(datos.extraerDatosCita());

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

        if (request.getParameter("id").equals("1")) {
            response.addHeader("id", String.valueOf(datosCita.getId()));
            response.addHeader("paciente",datosCita.getPaciente());
            response.addHeader("medico",datosCita.getMedico());
            response.addHeader("fecha",datosCita.getFecha().toString());
            response.addHeader("empresa",datosCita.getEmpresa());
            response.addHeader("entidad",datosCita.getEntidad());
            response.addHeader("tipo_consulta",datosCita.getTipo_consulta());
            response.addHeader("observaciones",datosCita.getObservaciones());
            
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();

            try {
                GeneradorPDF pdf = new GeneradorPDF();
                pdf.crearPDF(datosCita, out);

            } finally {
                out.close();
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error Usuario</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>no existe en nuestra base de datos </h1>");
                out.println("</body>");
                out.println("</html>");
            }

        }

//        UUID uuid = generateType1UUID();
//        datosCita.setUuid(uuid);
//        System.out.println("ESTE ES EL UUID!!!!!!!!!!!!!!" + datosCita.getUuid());
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    //metodos aparte
    public static UUID generateType1UUID() {

        System.out.println("ESTOS SON LOS DATOS" + get64LeastSignificantBitsForVersion1() + "/////" + get64MostSignificantBitsForVersion1());
        long most64SigBits = get64MostSignificantBitsForVersion1();
        long least64SigBits = get64LeastSignificantBitsForVersion1();

        return new UUID(most64SigBits, least64SigBits);
    }

    private static long get64LeastSignificantBitsForVersion1() {
        Random random = new Random();
        long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
        long variant3BitFlag = 0x8000000000000000L;
        return random63BitLong + variant3BitFlag;
    }

    private static long get64MostSignificantBitsForVersion1() {
        LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
        Duration duration = Duration.between(start, LocalDateTime.now());
        long seconds = duration.getSeconds();
        long nanos = duration.getNano();
        long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
        long least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
        long version = 1 << 12;
        return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;
    }
}
