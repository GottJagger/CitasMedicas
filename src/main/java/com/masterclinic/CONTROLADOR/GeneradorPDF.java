/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.CONTROLADOR;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.masterclinic.MODELO.Cita;
import java.io.OutputStream;

/**
 *
 * @author Gott Jagger
 */
public class GeneradorPDF {

    
//    private static Cita datos = datosCita.llenarCita(datosCita.extraerDatosCita());

    public  void crearPDF(Cita datos, OutputStream ruta) {
        try {
            
            Document documento = new Document();
            PdfWriter writer = PdfWriter.getInstance(documento, ruta);
            documento.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            documento.addTitle("cita medica");
            Chapter chapter = new Chapter(new Paragraph(), 0);
            chapter.setNumberDepth(0);
            chapter.add(new Paragraph("paciente: " + datos.getPaciente()));
            chapter.add(new Paragraph("Id del paciente: " + datos.getId()));
            chapter.add(new Paragraph("Fecha en que el paciente tiene que ir a la cita: " + datos.getFecha()));
            chapter.add(new Paragraph("Medico: " + datos.getMedico()));
            chapter.add(new Paragraph("Empresa: " + datos.getEmpresa()));
            chapter.add(new Paragraph("Entidad: " + datos.getEntidad()));
            chapter.add(new Paragraph("Tipo de consulta: " + datos.getTipo_consulta()));
            chapter.add(new Paragraph("Observaciones: \n" + datos.getObservaciones()));
            documento.add(chapter);
            documento.close();
        } catch (DocumentException ex) {
            Logger.getLogger(GeneradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
