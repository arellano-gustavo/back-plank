/*
 *
 *  *
 *  *  *
 *  *  *  Licencia:    Este código se encuentra bajo la protección
 *  *  *               que otorga el contrato establecido entre
 *  *  *               Ultrasist SA de CV y su cliente, Cinepolis, por lo
 *  *  *               que queda estrictamente prohibido copiar, donar
 *  *  *               vender y/o distribuir el presente código por
 *  *  *               cualquier medio electrónico o impreso sin el
 *  *  *               permiso explícito y por escrito del cliente.
 *  *  *
 *  *  *  Proyecto:   plank-back
 *  *  *  Modulo:     plank-back
 *  *  *  Clase          FileUploadController
 *  *  *  Autor:
 *  *  *  Fecha:        4/30/20, 11:26 PM
 *  *  *  Version:     0.0.1-SNAPSHOT
 *  *  *
 *  *  *  /
 *  *
 *
 *
 */

package mx.qbits.plank.api.rest;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.qbits.plank.api.exceptions.UploadException;
import mx.qbits.plank.api.model.UploadModel;
import mx.qbits.plank.api.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Endpoint para subir archivos.
 */
@RestController
@Api(value = "negocio")
@RequestMapping(value = "/api")
public class FileUploadController {

    private final UploadService uploadService;
    @Value("${app.destination-folder}")
    private String destinationFolder;
    @Value("${app.download-folder}")
    private String downloadFolder;
    @Value("${app.max-file-size}")
    private long max;

    /**
     * Constructor para la inyeccion de dependencias.
     *
     * @param uploadService el servicio que ofrece almacenamiento
     */
    @Autowired
    public FileUploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    // FAVOR DE AGREGAR JAVADOC
    // FAVOR DE AGREGER SWAGGER DOC
    // FAVOR DE INDICAR QUE HAY QUE INCORPORAR VALIDACIONES AL ARCHIVO A SUBIR TANTO EN FRONT COMO EN BACK


    /**
     * Metodo encargado de recibir un archivo del front y lo almacena en la carpeta configurada.
     * en app.destination-folder configurada en  application.properties
     *
     * @param file archivo a almacenar
     * @return lista que contiene el modelo de donde se almaceno el archivo
     * @throws UploadException si hay algun error
     */
    @ApiOperation(
            value = "FileUploadController::handleFileUpload",
            notes = "Metododo encargado de recibir un archivo del front y lo "
                    + "almacena en una ruta configurada.")
    @PostMapping(
            path = "/upload.json",
            produces = "application/json; charset=utf-8")
    public List<UploadModel> handleFileUpload(@RequestParam MultipartFile[] file) throws UploadException {
        return uploadService.store(file, destinationFolder, max);
    }

    /**
     * Recibe un(os) archivo(s) del front y lo almacena en la carpeta y almacena una copia en
     * ${app.destination-folder}/kdm y otra copia en ${application.properties}/kdm.
     *
     * @param files archivo a almacenar
     * @return lista que contiene el modelo de donde se almaceno el archivo
     * @throws UploadException si hay algun error
     */
    @PostMapping(path = "/upload2.json", produces = "application/json; charset=utf-8")
    public List<UploadModel> handleFileUploadWithKDMCopy(@RequestParam MultipartFile[] files)
            throws UploadException {
        List<UploadModel> listaUpload = uploadService.store(
                files, destinationFolder + "kdm", max);
        List<UploadModel> listaCopy = uploadService.store(
                files, downloadFolder + "kdm", max);
        listaUpload.addAll(listaCopy);
        return listaUpload;
    }
}
/*
curl http://localhost:8081/api/upload.json -X POST \
-F 'files=@/Users/garellano/Desktop/bot.png' \
-F 'files=@/Users/garellano/Desktop/declaranet-paola.pdf'


image/png
application/pdf
application/x-tika-ooxml excel
image/jpeg

*/
