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
 * Implementacion del Servicio del envio de mail
 *
 * Historia:    .
 *              20200402_2231 Creación del tipo
 *
 *
 */
package mx.qbits.plank.api.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.qbits.plank.api.exceptions.BusinessException;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Descripción:
 * </p>
 * Implementacion del servicio de envio de mail.
 *
 * @author Gustavo A. Arellano (GAA)
 * @version 1.0-SNAPSHOT
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {

    private static final String ERROR_IN_MAIL_SERVICE_SEND_HTML_MAIL_METHOD = "error in mail service sendHtmlMail method {}";

    /** logger. */
    private Logger logger = LoggerFactory.getLogger(MailSenderServiceImpl.class);

    /** java mail sender. */
    private JavaMailSender javaMailSender;

    /** Constante NUM_QUICK_SERVICE_THREADS. */
    public static final int NUM_QUICK_SERVICE_THREADS = 20;

    /** quick service. */
    private ScheduledExecutorService quickService = Executors
            .newScheduledThreadPool(NUM_QUICK_SERVICE_THREADS);

    /**
     * CBDI Constructor.
     *
     * @param javaMailSender (avoiding Autowire)
     */
    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sendMail2(String to, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);
        logger.info("Sending...");
        javaMailSender.send(mail);
        logger.info("Done!");
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Async
    public String sendHtmlMail(String to, String subject, String body) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(mail);
            return "";
        } catch (MessagingException me) {
            logger.error(ERROR_IN_MAIL_SERVICE_SEND_HTML_MAIL_METHOD, me.getMessage());
            return ERROR_IN_MAIL_SERVICE_SEND_HTML_MAIL_METHOD;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Async
    public String sendHtmlMail(HelperConfig helperConfig) throws BusinessException {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helperConfig.configure(helper);
            javaMailSender.send(mail);
            return "";
        } catch (MessagingException me) {
            throw new BusinessException(me.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Async
    public String sendHtmlMail2(String to, String subject, String body, File file) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.addAttachment("Adjuntinto", file);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(mail);
            return "";
        } catch (MessagingException me) {
            logger.error(ERROR_IN_MAIL_SERVICE_SEND_HTML_MAIL_METHOD, me.getMessage());
            return ERROR_IN_MAIL_SERVICE_SEND_HTML_MAIL_METHOD;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sendASynchronousHtmlMail(String to, String subject, String body) {
        logger.debug("inside sendASynchronousMail method");
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
        } catch (MessagingException me) {
            logger.error(ERROR_IN_MAIL_SERVICE_SEND_HTML_MAIL_METHOD, me.getMessage());
        }
        // FROM:
        // https://www.oodlestechnologies.com/blogs/Asynchronous-Mail-In-Spring-Boot/
        quickService.submit(() -> {
            try {
                javaMailSender.send(mail);
            } catch (Exception e) {
                logger.error("Exception occur while send a mail : ", e);
            }
        });
        return "";
    }

}
