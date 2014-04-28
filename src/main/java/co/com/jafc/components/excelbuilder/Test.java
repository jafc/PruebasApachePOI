/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jafc.components.excelbuilder;

import co.com.jafc.components.beans.ReporteMapaPruebaDTO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author ALEXANDER
 */
public class Test {

    private static final String LOCATION = "d:\\libro.xls";

    private ReporteExcel reporteExcel;

    public Test(ReporteExcel reporteExcel) {
        this.reporteExcel = reporteExcel;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public void construirReporteMapaPruebas() {
        List<ReporteMapaPruebaDTO> listaCasosPrueba = new ArrayList<ReporteMapaPruebaDTO>();
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(
                33, "Artefacto 1 bbbbbbbbbbb", "DSDSD", "Esperada", "DSDSA", new Date(),
                "admin", null, null, null, null, "Mapa Prueba 1",
                1, "prueba1", "otra prueba1", "No Conformidad", "Funcional", "Baja",
                "Valeria.Vasquez", "Actualización del Ambiente",
                "El cliente lo asumirá administrativamente", "Cerrado", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(
                33, "Artefacto 1 bbbbbbbbbbb", "DSDSD", "Esperada", "DSDSA", new Date(),
                "admin", null, null, null, null, "Mapa Prueba 1",
                2, "prueba1", "otra prueba1", "No Conformidad", "Funcional", "Baja",
                "Valeria.Vasquez", "Actualización del Ambiente",
                "El cliente lo asumirá administrativamente", "Cerrado", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(
                34, "Artefacto 1 bbbbbbbbbbb",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5, "sfdsf", "sdfsdf", "No Conformidad", "Bloqueante", "Media",
                "Valeria.Vasquez", "Actualización del Ambiente", "Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));

        Workbook libroExcel = null;

        try {
            String nombreMapaPrueba = "Mapa Prueba";
            libroExcel = reporteExcel.construirReporteMapaPruebas(listaCasosPrueba, nombreMapaPrueba);
        } catch (Exception e) {
            //LogUtil.log(ReporteExcelTest.class.getName(), e.getMessage(), Level.ERROR, e);		
        } finally {
            if (libroExcel != null) {
                try {
                    FileOutputStream fileOut = new FileOutputStream(LOCATION);
                    libroExcel.write(fileOut);
                    Desktop d = Desktop.getDesktop();
                    d.open(new File(LOCATION));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
