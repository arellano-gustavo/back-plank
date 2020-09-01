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
 * Modulo:      plank-back
 * Tipo:        clase
 * Autor:       Gustavo A. Arellano (GAA)
 * Fecha:       Jueves 2 de Abril de 2020 (22_29)
 * Version:     1.0-SNAPSHOT
 * .
 * Implementacion del Servicio de informe de salud
 *
 * Historia:    .
 *              20200402_2229 Creación del tipo
 *
 */
package mx.qbits.plank.api.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import mx.qbits.plank.api.exceptions.UploadException;
import mx.qbits.plank.api.model.UploadModel;

/**
 * Interface UploadService.
 */
public interface UploadService {

    /**
     * Procesa un conjunto de archivos que le son enviados desde el front.
     *
     * @param mpfArray MultipartFile[] arreglo de archivos enviados desde el front
     * @param destinationFolder String path to file destination
     * @param max long max size allowed for the file
     * 
     * @return Lista de objetos de tipo UploadModel
     * 
     * @throws UploadException Se dispara en caso de que el proceso de upload falle
     */
    public List<UploadModel> store(MultipartFile[] mpfArray, String destinationFolder, long max) throws UploadException;
}
