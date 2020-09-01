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

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface HealthService.
 */
public interface HealthService {

    /**
     * Retorna la resultante de la ejecición de un comando del sistema operativo.
     *
     * @param data String Comando del sistema operativo
     * @return Map estructira de tipo diccionario con la resultante de la ejecución
     * @throws IOException Signals that an I/O exception has occurred.
     */
    Map<String, String> getInfo(String data) throws IOException;

    /**
     * Retorna cierto numero de lineas del log especificado por el
     * parámetro que recibe el método.
     *
     * @param last int numero de lineas del log a retornar
     *
     * @return Lista de cadenas con el numero de lineas del log
     *         determinado por el parámetro dado
     */
    List<String> getLog(int last);
}
