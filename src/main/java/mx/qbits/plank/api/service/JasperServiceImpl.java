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
 * Tipo:        clase
 * Autor:       Gustavo Adolfo Arellano Sandoval (garellanos)
 * Fecha:       28 de Marzo de 2020
 * Version:     0.0.1-SNAPSHOT
 * .
 * Clase encargada de controlar las excepciones
 */
package mx.qbits.plank.api.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 * Servicio empleado para la generación de archivos PDF. Ofrece la posibilidad de usar plantillas
 * precompiladas (.jasper) o fuentes (.jrxml) que puede pre compilar al vuelo y generar su asociado
 * .jasper o bien en una modalidad puramente en memoria.
 *
 * <p>Inspirado en: https://www.wavemaker.com/learn/how-tos/generate-pdf-file-using-jasper-reports/
 *
 * @author garellanos
 */
public class JasperServiceImpl implements JasperService {

    /** Constante logger. */
    private static final Logger logger = LoggerFactory.getLogger(JasperServiceImpl.class);

    /** Constante JASPER_EXT. */
    private static final String JASPER_EXT=".jasper";

    /** Constante JRXML_EXT. */
    private static final String JRXML_EXT=".jrxml";

    /** Constante USER_PSWD. */
    private static final String USER_PSWD = "jasper";

    /** Constante OWNER_PSWD. */
    private static final String OWNER_PSWD = "reports";

    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePdfFromCompiledJrxmlFilename(String inputFileName, String dataFile, String outputFileName) throws IOException, JRException {
        InputStream inputStream = getStream(inputFileName + JRXML_EXT);
        if(inputFileName.startsWith("/")) {
            File jasperFile = new File(inputFileName + JASPER_EXT);
            if(!jasperFile.exists()) {
                logger.debug("No existe un compilado para este .jrxml => creando su compilado en la misma ruta...");
                JasperCompileManager.compileReportToFile(inputFileName + JRXML_EXT, inputFileName + JASPER_EXT);
            }
            logger.debug("Generating PDF final file...");
            return generatePdfFromCompiledJasperFilename(inputFileName, dataFile, outputFileName);
        } else {
            // if [existe el archivo jasper en local] then use(jasper_local) else:
            // here, probably would be a good idea to actually generate the .jasper file, instead a
            // full in-memory operation, that, of course will be very slow, since it has to compile
            // the report and then do all the rest of the normal process... :)
            JasperDesign design = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            return generatePdfFromCompiledJasperObject(jasperReport, dataFile, outputFileName);
        }
    }

    /**
     * Retorna un InputStream asociado a una archivo definido por una ruta absoluta o una ruta
     * relativa manejando sus "asegunes" para cada caso. En caso de error, dispara una IOException.
     *
     * @param inputFileName String Nombre del archivo a regresar como Stream
     *
     * @return InputStream Asociado al archivo dado
     * @throws IOException Disparada cuando no es posible procesar el archivo dado
     */
    private InputStream getStream(String inputFileName) throws IOException {
        InputStream inputStream = null;
        if(inputFileName.startsWith("/")) {
            // /Users/garellano/Desktop/example.jrxml
            inputStream = new FileInputStream(inputFileName);
        } else {
            // assets/report/example.jrxml
            inputStream = this.getClass().getClassLoader().getResource(inputFileName).openStream();
        }
        return inputStream;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePdfFromCompiledJasperFilename(String inputFileName, String dataFile, String outputFileName) throws IOException, JRException {
        logger.debug("Get the jasper file as stream");
        InputStream inputStream = getStream(inputFileName + JASPER_EXT);

        logger.debug("Load the stream");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

        logger.debug("Create PDF");
        return generatePdfFromCompiledJasperObject(jasperReport, dataFile, outputFileName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePdfFromCompiledJasperObject(JasperReport jasperReport, String dataFile, String outputFileName) throws IOException, JRException {
        logger.debug("Load the data");
        InputStream inputStream = getStream(dataFile);
        String data = null;
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            data = scanner.useDelimiter("\\A").next();
        }
        logger.debug("data from json file:\n {}", data);

        // Woooow ! It's possible to generate Jasper reports from a JSON source !!!!
        logger.debug("Create a Datasource from data");
        JsonDataSource jsonDataSource = new JsonDataSource(new ByteArrayInputStream(data.getBytes()));

        logger.debug("Creating JasperPrinter...");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), jsonDataSource);

        logger.debug("Encrypt report...");
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setEncrypted(true);
        configuration.set128BitKey(true);
        configuration.setUserPassword(USER_PSWD);
        configuration.setOwnerPassword(OWNER_PSWD);
        configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);

        logger.debug("Creating JasperExporter...");
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setConfiguration(configuration);
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        logger.debug("Export report");
        ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
        pdfExporter.exportReport();

        logger.debug("Writting to a file output");
        byte[] byteArray = pdfReportStream.toByteArray();
        try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
           fos.write(byteArray);
           // nice !!!! no need to close 'fos'...
        }
        // but here, obviously we have to !!!
        logger.debug("PDF generation completed Successfully !!!");
        pdfReportStream.close();

        logger.debug("oky docky... calulating our md5...");
        return md5(outputFileName);
    }

    /**
     * Calcula el md5 de un archivo dado.
     *
     * @param filename String Ruta competa del archivo dado
     * @return String md5 del archivo dado
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public String md5(String filename) throws IOException {
        File file = new File(filename);
        MessageDigest messageDigest = null;
        try {
            // Our catch can be "empty", because MD5 is an existing algoritm
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("MD5 algorithm was not found");
        }
        messageDigest.update(Files.readAllBytes(file.toPath()));
        byte[] hash = messageDigest.digest();
        String result = new String(hash);
        return result.toUpperCase();
    }
}
