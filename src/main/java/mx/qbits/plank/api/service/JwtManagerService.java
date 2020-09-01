/*
 * Licencia:    Este código se encuentra bajo la protección
 *              que otorga el contrato establecido entre
 *              Ultrasist SA de CV y su cliente, IMPI, por lo
 *              que queda estrictamente prohibido copiar, donar
 *              vender y/o distribuir el presente código por
 *              cualquier medio electrónico o impreso sin el
 *              permiso explícito y por escrito del cliente.
 *
 * Proyecto:    Chatbot IMPI
 * Paquete:     mx.gob.impi.chatbot.persistence.api.service
 * Modulo:      JwtManagerService
 * Tipo:        interface
 * Autor:       Gustavo A. Arellano (GAA)
 * Fecha:       1 de Octubre de 2019 (12_26)
 * Version:     1.0-SNAPSHOT
 * .
 * Servicio de User
 *
 * Historia:    .
 *              20191001_1226 Creación del tipo
 *
 *
 */
package mx.qbits.plank.api.service;

/**
 * <p>Descripción:</p>
 * Interface asociado al servicio de JSON Web Tokens
 *
 * @author Gustavo A. Arellano (GAA)
 * @version 1.0-SNAPSHOT
 */
public interface JwtManagerService {
    /**
     * Crea el JWT para el usuario solicitado
     * @param username Cadena con el nombre de usuario
     *                 para el que se le genera el JWT
     * @return Cadena con el JSON Web Token
     */
    String createToken(String username);

    /**
     * Verifica que el JWT que se recibe correnponde con
     * el usuario que se quiere autorizar
     * @param jwt
     * @param user
     * @return
     */
    boolean verifyToken(String jwt, String user);
}
