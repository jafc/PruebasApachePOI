package co.com.jafc.components.excelbuilder;

import co.com.jafc.components.beans.MapaPruebasDTO;
import co.com.jafc.components.beans.ReporteMapaPruebaDTO;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.IOUtils;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana
 * Cardona</a>
 * @project sgp-service
 * @class ReporteExcel
 * @description
 * @date 11/04/2014
 */
public class ReporteExcel {

    static {
//    	Properties props = System.getProperties(); 
//    	props.setProperty("java.awt.headless","true"); 
//    	System.setProperty("java.awt.headless", "true");
        try {
            construirArchivoProperties();
        } catch (IOException e) {
            LogUtil.log(ReporteExcel.class.getName(), e.getMessage(), Level.SEVERE, e);
        }
    }

    private static Properties reporteProperties;

    @PostConstruct
    public void init() {
        try {
            construirArchivoProperties();
        } catch (IOException ex) {
            Logger.getLogger(ReporteExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 21/04/2014
     * @description
     * @throws IOException
     */
    private static void construirArchivoProperties() throws IOException {
        reporteProperties = new Properties();
        InputStream input = ReporteExcel.class.getClassLoader().getResourceAsStream(ReporteExcelCons.FILE_NAME);
        reporteProperties.load(input);
        input.close();
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 14/04/2014
     * @description
     * @param nombreEmpresa
     * @param titulo
     * @param version
     * @param codigo
     * @return
     */
    private Workbook createFormatoMapaPruebas(String nombreEmpresa, String titulo,
            String version, String codigo, String nombreHoja) {

        Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet(nombreHoja);

        CellStyle estilo = libro.createCellStyle();
        estilo.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);

        Row fila;
        Cell columna;

        fila = hoja.createRow(0);
        fila.setHeightInPoints(42f);

        columna = fila.createCell(1);
        columna.setCellValue(nombreEmpresa);
        columna.setCellStyle(estilo);

        //Nombre empresa
        estilo = libro.createCellStyle();
        estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);

        //Titulo
        columna = fila.createCell(2);
        columna.setCellValue(titulo);
        columna.setCellStyle(estilo);

        //Version
        columna = fila.createCell(4);
        columna.setCellValue(version);
        columna.setCellStyle(estilo);

        //Codigo
        columna = fila.createCell(5);
        columna.setCellValue(codigo);
        columna.setCellStyle(estilo);

        fila = hoja.createRow(3);
        estilo = libro.createCellStyle();
        estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
        estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);

        //Titulo mayusculas
        columna = fila.createCell(1);
        columna.setCellValue(titulo.toUpperCase());

        Font fuente = libro.createFont();
        fuente.setFontHeightInPoints((short) 20);
        fuente.setFontName("Calibri");
        fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fuente.setColor(HSSFColor.WHITE.index);
        estilo.setFont(fuente);

        columna.setCellStyle(estilo);
        fila.setHeightInPoints(25.50f);

        hoja.setColumnWidth(0, 100 * 12);
        hoja.setColumnWidth(1, 100 * 60);
        hoja.setColumnWidth(2, 100 * 120);
        hoja.setColumnWidth(4, 100 * 120);

        cargarLogo(libro);

        hoja.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
        hoja.addMergedRegion(new CellRangeAddress(0, 1, 2, 3));
        hoja.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));
        hoja.addMergedRegion(new CellRangeAddress(0, 1, 5, 6));
        hoja.addMergedRegion(CellRangeAddress.valueOf("$B$4:$G$4"));

        RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(
                0, 1, 1, 6), hoja, libro);
        RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(0,
                1, 1, 6), hoja, libro);
        RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(0,
                1, 1, 6), hoja, libro);
        RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(
                0, 1, 1, 6), hoja, libro);

        RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(0,
                1, 2, 3), hoja, libro);
        RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(
                0, 1, 2, 3), hoja, libro);

        RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(
                0, 1, 4, 4), hoja, libro);

        // BORDE TITULO MAYUSCULAS
        RegionUtil.setBorderBottom(CellStyle.BORDER_THIN,
                CellRangeAddress.valueOf("$B$4:$G$4"), hoja, libro);
        RegionUtil.setBorderTop(CellStyle.BORDER_THIN,
                CellRangeAddress.valueOf("$B$4:$G$4"), hoja, libro);
        RegionUtil.setBorderLeft(CellStyle.BORDER_THIN,
                CellRangeAddress.valueOf("$B$4:$G$4"), hoja, libro);
        RegionUtil.setBorderRight(CellStyle.BORDER_THIN,
                CellRangeAddress.valueOf("$B$4:$G$4"), hoja, libro);

        return libro;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 14/04/2014
     * @description
     * @param mapaPruebaDTO
     * @return
     * @throws Exception
     * @throws IOException
     */
    public Workbook construirReporteMapaPruebas(List<ReporteMapaPruebaDTO> mapaPruebaDTO, String nombreMapa)
            throws Exception, IOException {

        Properties prop = getArchivoProperties();
        Workbook libro = createFormatoMapaPruebas(
                prop.getProperty("nombreEmpresa"),
                prop.getProperty("titulo"),
                null,
                null,
                nombreMapa);

        List<String> listaEncabezados = new ArrayList<String>();
        listaEncabezados.add(prop.getProperty("No"));
        listaEncabezados.add(prop.getProperty("artefacto"));
        listaEncabezados.add(prop.getProperty("descripcionPrueba"));
        listaEncabezados.add(prop.getProperty("tipoPrueba"));
        listaEncabezados.add(prop.getProperty("resultado"));
        listaEncabezados.add(prop.getProperty("fechaCreacion"));
        listaEncabezados.add(prop.getProperty("usuarioCreador"));
        listaEncabezados.add(prop.getProperty("usuarioEjecutor"));
        listaEncabezados.add(prop.getProperty("fechaEjecucion"));
        listaEncabezados.add(prop.getProperty("cumple"));
        listaEncabezados.add(prop.getProperty("numeroHallazgo"));
        listaEncabezados.add(prop.getProperty("tituloHallazgo"));
        listaEncabezados.add(prop.getProperty("descripcionHallazgo"));
        listaEncabezados.add(prop.getProperty("tipoHallazgo"));
        listaEncabezados.add(prop.getProperty("severidad"));
        listaEncabezados.add(prop.getProperty("prioridad"));
        listaEncabezados.add(prop.getProperty("responsableRealizarAjustes"));
        listaEncabezados.add(prop.getProperty("causaGeneracion"));
        listaEncabezados.add(prop.getProperty("causaAnulacion"));
        listaEncabezados.add(prop.getProperty("estadoHallazgo"));
        listaEncabezados.add(prop.getProperty("fechaEstadoHallazgo"));

        libro = construirEncabezado(libro, listaEncabezados);
        llenarReporteMapaPruebas(libro, mapaPruebaDTO);

        return libro;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 21/04/2014
     * @description
     * @param mapas
     * @param nombreProyecto
     * @return
     * @throws Exception
     */
    public Workbook construirReporteAvanceEjcucionMapas(List<MapaPruebasDTO> mapas, String nombreProyecto)
            throws Exception {
        List<String> listaEncabezados = new ArrayList<String>();
        listaEncabezados.add(reporteProperties.getProperty("nombreMapaPruebas"));
        listaEncabezados.add(reporteProperties.getProperty("pruebasConstruidas"));
        listaEncabezados.add(reporteProperties.getProperty("pruebasEjecutadas"));
        listaEncabezados.add(reporteProperties.getProperty("pruebasSatisfactorias"));
        listaEncabezados.add(reporteProperties.getProperty("pruebasInsatisfactorias"));
        listaEncabezados.add(reporteProperties.getProperty("pruebasNoAplican"));
        listaEncabezados.add(reporteProperties.getProperty("pruebasSinEjecutar"));
        listaEncabezados.add(reporteProperties.getProperty("porcentajeAvancePruebas"));
        listaEncabezados.add(reporteProperties.getProperty("indicadorCalidadICF"));

        Workbook libro = new HSSFWorkbook();
        libro.createSheet(nombreProyecto);
        construirEncabezadoAvanceMapas(libro, listaEncabezados);
        llenarReporteAvanceEjecucionMapas(libro, mapas);

        return libro;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 14/04/2014
     * @description
     * @param libro
     * @param listaEncabezado
     * @return
     */
    private Workbook construirEncabezado(Workbook libro, List<String> listaEncabezado) {

        Sheet hoja = libro.getSheetAt(0);
        Row filaEncabezado = hoja.createRow(ReporteExcelCons.POS_FILA_ENCABEZADO);
        filaEncabezado.setHeightInPoints(45f);

        HSSFPalette paleta = ((HSSFWorkbook) libro).getCustomPalette();
        paleta.setColorAtIndex(HSSFColor.DARK_RED.index,
                (byte) 192,
                (byte) 0,
                (byte) 0);

        CellStyle estilo = libro.createCellStyle();
        estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);
        estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
        estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estilo.setWrapText(true);

        Font fuente = libro.createFont();
        fuente.setFontHeightInPoints((short) 8);
        fuente.setFontName("Arial");
        fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fuente.setColor(HSSFColor.WHITE.index);
        estilo.setFont(fuente);

        // Bordes:
        estilo.setBorderBottom(CellStyle.BORDER_THIN);
        estilo.setBorderLeft(CellStyle.BORDER_THIN);
        estilo.setBorderRight(CellStyle.BORDER_THIN);
        estilo.setBorderTop(CellStyle.BORDER_THIN);

        /// filaEncabezado.setRowStyle(estilo);
        int i = 0;
        for (String nombre : listaEncabezado) {
            Cell celda = filaEncabezado.createCell(i);
            celda.setCellValue(nombre);
            celda.setCellStyle(estilo);
            if (i > 10) {
                hoja.setColumnWidth(i, 25 * 256);
            }
            i++;
        }

        return libro;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 14/04/2014
     * @description
     * @param libro
     * @param data
     * @throws Exception
     */
    private void llenarReporteMapaPruebas(Workbook libro, List<ReporteMapaPruebaDTO> data) throws Exception {
        int filaCnt = ReporteExcelCons.POS_FILA_DATA, registro = 1, columnaCnt = 0;
        Sheet hoja = libro.getSheetAt(0);

        //Configurando el estilo de las celdas
        CellStyle estilo = libro.createCellStyle();
        setEstiloGenerico(estilo);

        CellStyle estiloFecha = libro.createCellStyle();
        setEstiloGenerico(estiloFecha);
        CreationHelper ch = libro.getCreationHelper();
        estiloFecha.setDataFormat(ch.createDataFormat().getFormat(ReporteExcelCons.FORMATO_FECHA));

        //Estilo para las celdas: C,E,L,M
        CellStyle estiloAlineacionIzq = libro.createCellStyle();
        estiloAlineacionIzq.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estiloAlineacionIzq.setAlignment(CellStyle.ALIGN_LEFT);
        estiloAlineacionIzq.setWrapText(true);
        // Bordes:
        estiloAlineacionIzq.setBorderBottom(CellStyle.BORDER_THIN);
        estiloAlineacionIzq.setBorderLeft(CellStyle.BORDER_THIN);
        estiloAlineacionIzq.setBorderRight(CellStyle.BORDER_THIN);
        estiloAlineacionIzq.setBorderTop(CellStyle.BORDER_THIN);

        Row fila;
        Cell celda;
        int idRegistroAnterior = -1;
        for (ReporteMapaPruebaDTO prueba : data) {
            fila = hoja.createRow(filaCnt);

            if (prueba.getId() != idRegistroAnterior) {
                //No.
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(registro);

                //artefacto:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getArtefacto());

                //decripcionPrueba:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloAlineacionIzq);
                celda.setCellValue(prueba.getDescripcionCasoPrueba());

                //tipoPrueba
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getTipoPrueba());

                //resultadoEsperado:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloAlineacionIzq);
                celda.setCellValue(prueba.getResultadoEsperado());

                //fechaCreacion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                if (prueba.getFechaCreacion() != null) {
                    celda.setCellValue(prueba.getFechaCreacion());
                    hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
                }

                //usuarioCreador:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getUsuarioCrea());

                //usuarioEjecutor:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getUsuarioEjecutor());

                //fechaEjecucion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                if (prueba.getFechaEjecucion() != null) {
                    celda.setCellValue(prueba.getFechaEjecucion());
                    hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
                }

                //cumple:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getCumple());

                registro++;
            } else {
                columnaCnt = createEmptyCells(fila, columnaCnt, estilo, 0, 10);
                columnaCnt--; //el metodo createEmptyCells aumenta columnaCnt al final.
            }

            //Numero del hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if (prueba.getNumeroHallazgo() != null) {
                celda.setCellValue(prueba.getNumeroHallazgo());
            }

            //Titulo del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloAlineacionIzq);
            celda.setCellValue(prueba.getTituloHallazgo());

            //Descripcion del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloAlineacionIzq);
            celda.setCellValue(prueba.getDescripcionHallazgo());

            //Tipo de Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getTipoHallazgo());

            //Severidad:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getSeveridad());

            //Prioridad:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getPrioridad());

            //Responsable realizar ajustes:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getResponsableRealizarAjuestes());

            //Causa generacion:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getCausaGeneracion());

            //Causa anulacion:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getCausaAnulacion());

            //Estado del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getEstadoHallazgo());

            //Fecha estado del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloFecha);
            if (prueba.getFechaEstadoHallazgo() != null) {
                celda.setCellValue(prueba.getFechaEstadoHallazgo());
                hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
            }

            idRegistroAnterior = prueba.getId();

            columnaCnt = 0;
            filaCnt++;
        }

        //Configurando el ancho de las celdas fechas:
        /*
         * I M P O R T A N T E 
         * el metodo autoSizeColum de la clase Sheet estaba
         * casusando una Excepcion en el servidor de pruebas:
         * java.lang.NoClassDefFoundError: Could not initialize class sun.awt.X11GraphicsEnvironment
         */
//		hoja.autoSizeColumn(5);
//		hoja.autoSizeColumn(8);
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 25/04/2014
     * @description
     * @param libro
     * @param data
     * @throws Exception
     */
    private void llenarReporteAvanceEjecucionMapas(Workbook libro, List<MapaPruebasDTO> data)
            throws Exception {

        int filaCnt = ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS,
                columnaCnt = 0;

        Sheet hoja = libro.getSheetAt(0);

        //Configurando el estilo de las celdas
        CellStyle estilo = libro.createCellStyle();
        setEstiloGenerico(estilo);

        //Estilo celdas porcentaje
        DataFormat format = libro.createDataFormat();
        CellStyle estiloPorcentaje = libro.createCellStyle();
        setEstiloGenerico(estiloPorcentaje);
        estiloPorcentaje.setDataFormat(format.getFormat(ReporteExcelCons.PATRON_PORCENTAJE));

        //Estilo para las celdas con alineacion a la izquierda.
        CellStyle estiloAlineacionIzq = libro.createCellStyle();
        estiloAlineacionIzq.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estiloAlineacionIzq.setAlignment(CellStyle.ALIGN_LEFT);
        estiloAlineacionIzq.setWrapText(true);
        // Bordes:
        estiloAlineacionIzq.setBorderBottom(CellStyle.BORDER_THIN);
        estiloAlineacionIzq.setBorderLeft(CellStyle.BORDER_THIN);
        estiloAlineacionIzq.setBorderRight(CellStyle.BORDER_THIN);
        estiloAlineacionIzq.setBorderTop(CellStyle.BORDER_THIN);

        Row fila;
        Cell celda;
        for (MapaPruebasDTO mapa : data) {
            fila = hoja.createRow(filaCnt);

            //Nombre mapa de prueba:
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(mapa.getNombre());

            //Pruebas construidas:
            columnaCnt++;
            String cellPruConstruidas = cellName(filaCnt + 1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if (mapa.getTotalPruebasConstruidas() != null) {
                celda.setCellValue(mapa.getTotalPruebasConstruidas());
            }

            //Pruebas ejecutadas:
            columnaCnt++;
            String cellPruEjecutadas = cellName(filaCnt + 1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if (mapa.getTotalPruebasConstruidas() != null) {
                celda.setCellValue(mapa.getTotalPruebasEjecutadas());
            }

            //Pruebas satisfactorias:
            columnaCnt++;
            String cellPruSatisfactorias = cellName(filaCnt + 1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if (mapa.getTotalPruebasSatisfactorias() != null) {
                celda.setCellValue(mapa.getTotalPruebasSatisfactorias());
            }

            //Pruebas insatisfactorias:
            columnaCnt++;
            String cellPruInsatisfactorias = cellName(filaCnt + 1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if (mapa.getTotalPruebasInsatisfactorias() != null) {
                celda.setCellValue(mapa.getTotalPruebasInsatisfactorias());
            }

            //Pruebas N.A.:
            columnaCnt++;
            String cellPruNA = cellName(filaCnt + 1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if (mapa.getTotalPruebasAnuladas() != null) {
                celda.setCellValue(mapa.getTotalPruebasAnuladas());
            }

            //Pruebas sin ejecutar:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellFormula(formulaPruebasSinEjecutar(cellPruConstruidas, cellPruEjecutadas, cellPruNA));

            //% Avance pruebas:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloPorcentaje);
            celda.setCellFormula(formulaPorcentajeAvance(cellPruConstruidas, cellPruEjecutadas, cellPruNA));
//            Double porcentaje = mapa.getCalculatedPorcentajeAvance(false);
//            if(porcentaje != null) {
//            	celda.setCellValue(porcentaje);
//            }

            //Indicador de Calidad ICF:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloPorcentaje);
            Double icf = mapa.getCalculatedIndicadorCalidadICF(false);
            if (icf != null) {
                celda.setCellValue(icf);
            }

            columnaCnt = 0;
            filaCnt++;
        }

        construirTotalesAvanceEjecucionMapas(libro, filaCnt);

    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 14/04/2014
     * @description
     * @return
     * @throws IOException
     */
    public Properties getArchivoProperties() throws Exception, IOException {
        return reporteProperties;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 16/04/2014
     * @description
     * @param mapaPruebaDTO
     * @return
     * @throws Exception
     */
    public String construirNombreArchivoExcel(MapaPruebasDTO mapaPruebaDTO) throws Exception {
        StringBuilder archivoNombre = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat(ReporteExcelCons.FORMATO_FECHA_NOMBRE_ARCHIVO);
        archivoNombre.append(ReporteExcelCons.PREFIJO_MP);
        archivoNombre.append(ReporteExcelCons.PROYECTO);
        archivoNombre.append(dateFormat.format(mapaPruebaDTO.getFecCreacion()));
        archivoNombre.append("_");
        archivoNombre.append(mapaPruebaDTO.getNombre());
        return archivoNombre.toString();
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 14/04/2014
     * @description
     * @param libro
     */
    private void cargarLogo(Workbook libro) {
        Sheet hoja = libro.getSheetAt(0);
        InputStream inputStream = getClass()
                .getResourceAsStream(ReporteExcelCons.LOGO_FILE_NAME);

        if (inputStream != null) {
            try {
                // Se obtiene el contenido de un InputStream como un byte[]
                byte[] bytes = IOUtils.toByteArray(inputStream);
                // Agrega la imagen al workbook
                int pictureIdx = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                inputStream.close();
                CreationHelper helper = libro.getCreationHelper();
                Drawing drawing = hoja.createDrawingPatriarch();
                // Crea un ancla que esta unida al worksheet
                ClientAnchor anchor = helper.createClientAnchor();
                // Establece la esquina superior izquierda de la imagen
                anchor.setCol1(1);
                anchor.setRow1(0);

                // Crea la imagen
                Picture pict = drawing.createPicture(anchor, pictureIdx);
                // Restablece la imagen al tamanio original
                pict.resize();
            } catch (IOException ioex) {
                LogUtil.log(getClass().getName(),
                        "No se pudo cargar el archivo logoPremizeReporteExcel.png",
                        Level.WARNING, ioex);
            }
        }
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 15/04/2014
     * @description
     * @param estilo
     * @return
     */
    private CellStyle setEstiloGenerico(CellStyle estilo) {
        estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);
        estilo.setWrapText(true);
        // Bordes:
        estilo.setBorderBottom(CellStyle.BORDER_THIN);
        estilo.setBorderLeft(CellStyle.BORDER_THIN);
        estilo.setBorderRight(CellStyle.BORDER_THIN);
        estilo.setBorderTop(CellStyle.BORDER_THIN);
        return estilo;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 21/04/2014
     * @description
     * @param fila
     * @param columnaCnt
     * @param estilo
     * @param start
     * @param size
     * @return
     */
    private int createEmptyCells(Row fila, Integer columnaCnt, CellStyle estilo, int start, int size) {
        for (int i = start; i < size; i++) {
            Cell celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(ReporteExcelCons.STRING_CELDA_VACIA);
            columnaCnt++;
        }
        return columnaCnt;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 21/04/2014
     * @description
     * @param libro
     * @param listaEncabezado
     * @return
     * @throws Exception
     */
    private void construirEncabezadoAvanceMapas(Workbook libro, List<String> listaEncabezado)
            throws Exception {

        Sheet hoja = libro.getSheetAt(0);
        Row filaEncabezado = hoja.createRow(ReporteExcelCons.POS_FILA_ENCABEZADO_AVANCE_MAPAS);
        filaEncabezado.setHeightInPoints(31.5f);

        HSSFPalette paleta = ((HSSFWorkbook) libro).getCustomPalette();
        paleta.setColorAtIndex(HSSFColor.DARK_RED.index,
                (byte) 192,
                (byte) 0,
                (byte) 0);

        CellStyle estilo = libro.createCellStyle();
        estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);
        estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
        estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estilo.setWrapText(true);

        Font fuente = libro.createFont();
        fuente.setFontHeightInPoints((short) 8);
        fuente.setFontName("Arial");
        fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fuente.setColor(HSSFColor.WHITE.index);
        estilo.setFont(fuente);

        // Bordes:
        estilo.setBorderBottom(CellStyle.BORDER_THIN);
        estilo.setBorderLeft(CellStyle.BORDER_THIN);
        estilo.setBorderRight(CellStyle.BORDER_THIN);
        estilo.setBorderTop(CellStyle.BORDER_THIN);

        int i = 0;
        for (String nombre : listaEncabezado) {
            Cell celda = filaEncabezado.createCell(i);
            celda.setCellValue(nombre);
            celda.setCellStyle(estilo);
            if (java.awt.GraphicsEnvironment.isHeadless()) {
                hoja.autoSizeColumn(i);
            } else {
                hoja.setColumnWidth(i, 20 * 256);
            }
            i++;
        }
        hoja.setColumnWidth(0, (25 * 256));

//		return libro;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 25/04/2014
     * @description
     * @param libro
     * @param rowIndex
     * @throws Exception
     */
    private void construirTotalesAvanceEjecucionMapas(Workbook libro, int rowIndex) throws Exception {
        Sheet hoja = libro.getSheetAt(0);

        int columnaCnt = 0, lastRowData = rowIndex - 1;

        CellStyle estilo = buildHeaderStyle(libro, rowIndex, 31.5f);

        //Estilo porcentaje:
        CellStyle estiloPorcentaje = buildHeaderStyle(libro, rowIndex, 31.5f);
        estiloPorcentaje.setDataFormat(libro.createDataFormat().getFormat(ReporteExcelCons.PATRON_PORCENTAJE));
        Row fila = hoja.getRow(rowIndex);
        Cell celda;

        // Label totales:
        celda = fila.createCell(columnaCnt);
        celda.setCellValue(reporteProperties.getProperty("totalAvance"));
        celda.setCellStyle(estilo);

        String formula = null;
        String rango = null;

        // Total pruebas construidas;
        columnaCnt++;
        celda = fila.createCell(columnaCnt);
        celda.setCellStyle(estilo);
        rango = buildRange(columnaCnt,
                (ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS + 1), columnaCnt, (lastRowData + 1));
        formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
        celda.setCellFormula(formula);

        // Total pruebas ejecutadas;
        columnaCnt++;
        celda = fila.createCell(columnaCnt);
        celda.setCellStyle(estilo);
        rango = buildRange(columnaCnt,
                (ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS + 1), columnaCnt, (lastRowData + 1));
        formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
        celda.setCellFormula(formula);

        // Total pruebas satisfactorias;
        columnaCnt++;
        celda = fila.createCell(columnaCnt);
        celda.setCellStyle(estilo);
        rango = buildRange(columnaCnt,
                (ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS + 1), columnaCnt, (lastRowData + 1));
        formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
        celda.setCellFormula(formula);

        // Total pruebas insatisfactorias;
        columnaCnt++;
        celda = fila.createCell(columnaCnt);
        celda.setCellStyle(estilo);
        rango = buildRange(columnaCnt,
                (ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS + 1), columnaCnt, (lastRowData + 1));
        formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
        celda.setCellFormula(formula);

        // Total pruebas N.A.;
        columnaCnt++;
        celda = fila.createCell(columnaCnt);
        celda.setCellStyle(estilo);
        rango = buildRange(columnaCnt,
                (ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS + 1), columnaCnt, (lastRowData + 1));
        formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
        celda.setCellFormula(formula);

        // Total pruebas sin ejecutar;
        columnaCnt++;
        celda = fila.createCell(columnaCnt);
        celda.setCellStyle(estilo);
        rango = buildRange(columnaCnt,
                (ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS + 1), columnaCnt, (lastRowData + 1));
        formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
        celda.setCellFormula(formula);

        // Total % avance pruebas;
        columnaCnt++;
        celda = fila.createCell(columnaCnt);
        celda.setCellStyle(estiloPorcentaje);
        rango = buildRange(columnaCnt,
                (ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS + 1), columnaCnt, (lastRowData + 1));
        formula = ReporteExcelCons.EF_PROMEDIO + "(" + rango + ")";
        celda.setCellFormula(formula);

        // Indicador de calidad ICF;
        columnaCnt++;
        celda = fila.createCell(columnaCnt);
        celda.setCellStyle(estiloPorcentaje);
        rango = buildRange(columnaCnt,
                (ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS + 1), columnaCnt, (lastRowData + 1));
        formula = ReporteExcelCons.EF_PROMEDIO + "(" + rango + ")";
        celda.setCellFormula(formula);

    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 22/04/2014
     * @description crea el estilo con el fondo rojo y los bordes
     * @param libro
     * @param rowHeight
     * @return
     * @throws Exception
     */
    private CellStyle buildHeaderStyle(Workbook libro, int rowIndex, float rowHeight) throws Exception {
        Sheet hoja = libro.getSheetAt(0);
        Row filaEncabezado = hoja.createRow(rowIndex);
        filaEncabezado.setHeightInPoints(rowHeight);

        HSSFPalette paleta = ((HSSFWorkbook) libro).getCustomPalette();
        paleta.setColorAtIndex(HSSFColor.DARK_RED.index,
                (byte) 192,
                (byte) 0,
                (byte) 0);

        CellStyle estilo = libro.createCellStyle();
        estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        estilo.setAlignment(CellStyle.ALIGN_CENTER);
        estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
        estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estilo.setWrapText(true);

        Font fuente = libro.createFont();
        fuente.setFontHeightInPoints((short) 8);
        fuente.setFontName("Arial");
        fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fuente.setColor(HSSFColor.WHITE.index);
        estilo.setFont(fuente);

        // Bordes:
        estilo.setBorderBottom(CellStyle.BORDER_THIN);
        estilo.setBorderLeft(CellStyle.BORDER_THIN);
        estilo.setBorderRight(CellStyle.BORDER_THIN);
        estilo.setBorderTop(CellStyle.BORDER_THIN);

        return estilo;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 22/04/2014
     * @description Crea un rango de celdas de excel. Ej: A1:B4
     * @param col1
     * @param fila1
     * @param col2
     * @param fila2
     * @return
     */
    private String buildRange(int col1, int fila1, int col2, int fila2) {
        StringBuilder str = new StringBuilder();
        str.append(CellReference.convertNumToColString(col1));
        str.append(fila1);
        str.append(":");
        str.append(CellReference.convertNumToColString(col2));
        str.append(fila2);
        return str.toString();
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 24/04/2014
     * @description Devueve el nombre de la celada; Ej: "A1" o "D10"
     * @param row
     * @param column
     * @return
     */
    private String cellName(int row, int column) {
        return CellReference.convertNumToColString(column) + row;
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 25/04/2014
     * @description
     * @param pruebasConstruidas
     * @param pruebasEjecutadas
     * @param pruebasNA
     * @return
     */
    private String formulaPruebasSinEjecutar(String pruebasConstruidas, String pruebasEjecutadas,
            String pruebasNA) {
        StringBuilder str = new StringBuilder();
        // str = pruebasConstruidas - (pruebasEjecutadas + pruebasNA)
        str.append(pruebasConstruidas);
        str.append("-(");
        str.append(pruebasEjecutadas);
        str.append("+");
        str.append(pruebasNA);
        str.append(")");
        return str.toString();
    }

    /**
     *
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
     * Filigrana Cardona</a>
     * @date 25/04/2014
     * @description
     * @param pruebasConstruidas
     * @param pruebasEjecutadas
     * @param pruebasNA
     * @return
     */
    private String formulaPorcentajeAvance(String pruebasConstruidas, String pruebasEjecutadas,
            String pruebasNA) {
        StringBuilder str = new StringBuilder();
        /*
         * pruebasEjecutadas / (pruebasConstruidas - pruebasNA) ) * 100
         * En este caso no se multiplica po 100 porque el formato de la
         * celda viene con el formato porcentaje (%)
         */
        str.append(pruebasEjecutadas);
        str.append("/(");
        str.append(pruebasConstruidas);
        str.append("-");
        str.append(pruebasNA);
        str.append(")");
        return str.toString();
    }

}
