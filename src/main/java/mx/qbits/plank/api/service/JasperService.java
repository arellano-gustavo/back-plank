/*
* Licencia:    Este código se encuentra bajo la protección
*              que otorga el contrato establecido entre
*              Ultrasist SA de CV y su cliente, Cinepolis, por lo
*              que queda estrictamente prohibido copiar, donar
*              vender y/o distribuir el presente código por
*              cualquier medio electrónico o impreso sin el
*              permiso explícito y por escrito del cliente.
*
* Proyecto:    Cinepolis
* Paquete:     mx.qbits.plank.api.service
* Modulo:      cinepolis
* Tipo:        interfaz
* Autor:       Gustavo Adolfo Arellano Sandoval
* Fecha:       28 de Marzo de 2020
* Version:     0.0.1-SNAPSHOT
* .
*/
package mx.qbits.plank.api.service;

import java.io.IOException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Interface JasperService.
 */
public interface JasperService {

    /**
     * Genera un archivo de tipo PDF en la ruta absoluta especificada por
     * 'outputFileName' Y populado con los datos que provienen del atrchivo
     * 'dataFile' y la plantilla jrxml definida en 'inputFileName' y cuya ruta NO
     * deberá contener la extensión '.jrxml'.
     *
     * <p>Nota: Cada archivo '.jrxml' será compilado en un nuevo archivo '.jasper' (si
     * es que éste NO existe) y el PDF final será generado a partir de tal archivo
     * compilado.
     *
     * @param inputFileName  String Nombre del archivo .jrxml
     * @param dataFile       String Nombre del archivo de datos json
     * @param outputFileName String Nombre del archivo pdf resultante
     * @return String El md5 del pdf generado
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws JRException the JR exception
     */
    String generatePdfFromCompiledJrxmlFilename(String inputFileName, String dataFile, String outputFileName)
            throws IOException, JRException;

    /**
     * Genera un archivo de tipo PDF en la ruta absoluta especificada por
     * 'outputFileName' y populado con los datos que provienen del atrchivo
     * 'dataFile' y el archivo compilado .jasper definido en 'inputFileName' y cuya
     * ruta NO deberá contener la extensión '.jasper'.
     *
     * @param inputFileName  String Nombre del archivo .jasper
     * @param dataFile       String Nombre del archivo de datos json
     * @param outputFileName String Nombre del archivo pdf resultante
     * @return String El md5 del pdf generado
     * @throws IOException Se dispara cuando 'inputFileName' no existe
     * @throws JRException the JR exception
     */
    String generatePdfFromCompiledJasperFilename(String inputFileName, String dataFile, String outputFileName)
            throws IOException, JRException;

    /**
     * Genera un archivo de tipo PDF en la ruta absoluta especificada por
     * 'outputFileName' y populado con los datos que provienen del atrchivo
     * 'dataFile' y el objeto Jaspereport dado en 'jasperReport'.
     *
     * @param jasperReport   JasperReport Objeto jasper
     * @param dataFile       String Nombre del archivo de datos json
     * @param outputFileName String Nombre del archivo pdf resultante
     * @return String El md5 del pdf generado
     * @throws IOException Se dispara cuando 'inputFileName' no existe
     * @throws JRException the JR exception
     */
    String generatePdfFromCompiledJasperObject(JasperReport jasperReport, String dataFile, String outputFileName)
            throws IOException, JRException;
}
