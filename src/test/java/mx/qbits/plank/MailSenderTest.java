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
 * Paquete:     mx.qbits.plank.pruebas
 * Modulo:      MailSenderTest
 * Tipo:        clase
 * Autor:       Chandra Quintas
 * Fecha:       07 de Abril de 2020
 * Version:     0.0.2-SNAPSHOT
 * .
 * Contendrá las pruebas relacionadas con el envio de correos.
 */
package mx.qbits.plank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.service.MailSenderService;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Before;

import static org.mockito.Mockito.*;

/**
 * @author Chandra Quintas
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSenderTest {

    private Logger logger = LoggerFactory.getLogger(MailSenderTest.class);

    @Mock
    private MailSenderService mailSenderService;

    @Before
    public void prepara() {
        when(mailSenderService.sendHtmlMail(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString())
                ).thenReturn("ok");
    }

    /**
     * Prueba que envia un email
     */
    @Test
    public void enviarMailOK() {
        try {
            String response = mailSenderService.sendHtmlMail("arellano.gustavo@gmail.com", "prueba", "<h1>Lo que va en el cuerpo<\\h1>");
            Assert.assertTrue(response.length()>0);
        } catch (Exception ex) {
            logger.error(getStackTraceExStr(ex));
            Assert.assertFalse(true);
        }
    }

    private String getStackTraceExStr(Exception excepcion) {
        StringBuilder errorGenerica = new StringBuilder();
        if (excepcion instanceof BusinessException) {
            BusinessException ge = (BusinessException) (excepcion);
            errorGenerica.append("tipo: ");
            errorGenerica.append(ge.getIdentificador());
            errorGenerica.append(": ");
            errorGenerica.append(ge.getCveExcepcion());
            errorGenerica.append(": ");
            errorGenerica.append(ge.getDescExcepcion());
            StringWriter errores = new StringWriter();
            ge.printStackTrace(new PrintWriter(errores));
            errorGenerica.append("\n");
            errorGenerica.append(errores.toString());
        }
        return errorGenerica.toString();
    }
}
