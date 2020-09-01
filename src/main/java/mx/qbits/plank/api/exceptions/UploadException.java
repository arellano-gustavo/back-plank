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
* Paquete:     mx.qbits.plank.api.exception
* Modulo:      cinepolis
* Tipo:        clase
* Autor:       Gustavo Adolfo Arellano Sandoval
* Fecha:       24 de marzo del 2020
* Version:     0.0.1-SNAPSHOT
* .
* Clase que contendr&aacute; las excepciones derivadas de errores de negocio como por ejemplo no existen salas disponibles para realizar la reservaci&oactute;n
*/
package mx.qbits.plank.api.exceptions;

/**
 * Modela una excepcion al subir un archivo.
 * 
 * @author Jacob Lobaco.
 *
 */
public class UploadException extends BusinessException {
    private static final long serialVersionUID = -8231101686866958902L;

    private static final int IDENTIFICADOR = 2001;
    private static final String CVE_EXCEPTION = "cve-2001";

    /**
     * Constructor por default.
     */
    public UploadException() {
        super.setIdentificador(IDENTIFICADOR);
        super.setCveExcepcion(CVE_EXCEPTION);
    }

    /**
     * Constructor que me permite encadenar excepcion.
     * 
     * @param message Mensaje de error.
     * @param cause   Causa raiz de la excepcion.
     */
    public UploadException(String message, Throwable cause) {
        super(message, cause);
        super.setIdentificador(IDENTIFICADOR);
        super.setCveExcepcion(CVE_EXCEPTION);
    }

    /**
     * Constructor con un mensaje de error.
     * 
     * @param message El mensaje de error.
     */
    public UploadException(String message) {
        super(message);
        super.setIdentificador(IDENTIFICADOR);
        super.setCveExcepcion(CVE_EXCEPTION);
    }

}
