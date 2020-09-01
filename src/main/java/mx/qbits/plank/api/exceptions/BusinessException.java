package mx.qbits.plank.api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {
    private static final long serialVersionUID = -102493919617018057L;
    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);
    
    /**
     * Identificador que nos permitira categorizar las excepciones de acceso a
     * datos.
     */
    private String cveExcepcion;

    /**
     * Contedra el mensaje de error que se registrara en el LOG.
     */
    private String descExcepcion;

    /**
     * Contedra el identificador del tipo de excepcion en el LOG.
     */
    private int identificador;
    
    /**
     * Para cuando se requiera, este atributo tendrá el HTTP_ERROR
     */
    private HttpStatus HTTP_ERROR = HttpStatus.ACCEPTED;

    /**
     * Constructor por default.
     */
    public BusinessException() {
        super();
        this.setHttpError(HTTP_ERROR);
    }

    /**
     * Constructor con una excepcion generica de base.
     * 
     * @param e Una excepcion generica.
     */
    public BusinessException(Exception e) {
        super(e);
        this.setHttpError(HTTP_ERROR);
        logger.error(e.getMessage());
    }

    /**
     * Constructor que me permite encadenar excepcion.
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.setHttpError(HTTP_ERROR);
        logger.error(message);
    }

    /**
     * Crea una nueva excepcion generica con un mensage base.
     * 
     * @param message El mensaje base a utilizar para crear una excepcion generica.
     */
    public BusinessException(String message) {
        super(message);
        this.setHttpError(HTTP_ERROR);
        logger.error(message);
    }
    
    public BusinessException(String message, HttpStatus error) {
        super(message);
        this.setHttpError(error);
        logger.error(message);
    }
    

    public BusinessException(String cveExcepcion, String descExcepcion, int identificador) {
        super();
        this.setHttpError(HTTP_ERROR);
        this.cveExcepcion = cveExcepcion;
        this.descExcepcion = descExcepcion;
        this.identificador = identificador;
    }

    /**
     * Obtiene la clave de la excepcion.
     * 
     * @return La clave de la excepcion.
     */
    public String getCveExcepcion() {
        return cveExcepcion;
    }

    /**
     * Settea la clave de la excepcion.
     * 
     * @param cveExcepcion Clave de la excepcion.
     */
    public void setCveExcepcion(String cveExcepcion) {
        this.cveExcepcion = cveExcepcion;
    }

    /**
     * Obtiene la descripcion de la excepcion.
     * 
     * @return La descripcion de la excepcion.
     */
    public String getDescExcepcion() {
        return descExcepcion;
    }

    /**
     * Actualiza la descripcion de la excepcion.
     * 
     * @param descExcepcion Descripcion de la excepcion.
     */
    public void setDescExcepcion(String descExcepcion) {
        this.descExcepcion = descExcepcion;
    }

    /**
     * Obtiene el identificador de la excepcion.
     * @return El identificador de la excepcion. 
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * Actualiza el identificador de la excepcion.
     * 
     * @param identificador El identificador de la excepcion.
     */
    public final void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public final void setHttpError(HttpStatus error) {
        this.HTTP_ERROR = error;
    }
    
    public final HttpStatus getHttpError() {
        return this.HTTP_ERROR;
    }
}
