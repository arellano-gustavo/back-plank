package mx.qbits.plank.api.service;

import java.io.File;
import org.springframework.mail.javamail.MimeMessageHelper;

import mx.qbits.plank.api.exceptions.BusinessException;

/**
 * <p>
 * Descripción:
 * </p>
 * Interface asociado al servicio de envio de mail.
 *
 * @author Gustavo A. Arellano (GAA)
 * @version 1.0-SNAPSHOT
 */
public interface MailSenderService {

    /**
     * Envio correo electronicos desde la aplicacion chatbot.
     *
     * @param to      Cadena con los correos electronicos a los que se el mensaje
     *                que genera el chatbot
     * @param subject Cadena con el titulo del correo electronico
     * @param body    Cadena con el cuerpo del mensaje que se envia desde el chatbot
     */
    String sendMail2(String to, String subject, String body);

    /**
     * Envio del chatbot por medio de JavaMailSender.
     *
     * @param to      Cadena con los correos electronicos a quienes se desea enviar
     *                el correo generado
     * @param subject Cadena con el titulo del correo electronico
     * @param body    Cadena con el cuerpodo del mensaje de correo
     */
    String sendHtmlMail(String to, String subject, String body);

    /**
     * Envio del chatbot por medio de JavaMailSender.
     *
     * @param to      Cadena con los correos electronicos a quienes se desea enviar
     *                el correo generado
     * @param subject Cadena con el titulo del correo electronico
     * @param body    Cadena con el cuerpodo del mensaje de correo
     * @param file    the file
     */
    String sendHtmlMail2(String to, String subject, String body, File file);

    /**
     * Este método usa un pool de executors para hacer su trabajo de manera
     * asíncrona.
     *
     * @param to      the to
     * @param subject the subject
     * @param body    the body
     */
    String sendASynchronousHtmlMail(String to, String subject, String body);

    /**
     * Envia un correo usando un HelperConfig para personalizar los parametros del
     * mensaje.
     * 
     * @param helperConfig Helper que recibe el objeto MimeMessageHelper
     * @throws GenericaException En caso de error al enviar el correo.
     */
    String sendHtmlMail(HelperConfig helperConfig) throws BusinessException;

    /**
     * Clase auxiliar para exponer el MimeMessageHelper a los clientes.
     * 
     * @author Jacob Lobaco
     *
     */
    public interface HelperConfig {

        /**
         * Configura el mensaje a enviar.
         * 
         * @param helper El objeto que se utiliza para configurar el mensaje.
         * @throws MessagingException En caso de error al configurar el mensaje.
         */
        String configure(MimeMessageHelper helper) throws BusinessException;
    }
}