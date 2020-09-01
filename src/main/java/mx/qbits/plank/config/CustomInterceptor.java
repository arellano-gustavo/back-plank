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
 * Paquete:     mx.qbits.plank.config
 * Modulo:      cinepolis
 * Tipo:        clase
 * Autor:       Gustavo Adolfo Arellano Sandoval (garellanos)
 * Fecha:       28 de Marzo de 2020
 * Version:     0.0.2-SNAPSHOT
 * .
 * Clase encargada de ...
 */
package mx.qbits.plank.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Clase CustomInterceptor.
 */
public class CustomInterceptor extends HandlerInterceptorAdapter {

    /** logger. */
    private Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        Enumeration<String> headerNames = request.getHeaderNames();
        ArrayList<String> lista = Collections.list(headerNames);
        for (String s : lista) {
            String mensaje = "Header name --->" + s;
            logger.debug(mensaje);
            Enumeration<String> values = request.getHeaders(s);
            while (values.hasMoreElements()) {
                String otroMensaje = values.nextElement() + "<--- header value ";
                logger.debug(otroMensaje);
            }
        }

        String auth = request.getHeader("Authorization");
        String mensaje = "Authorization::" + auth;
        logger.debug(mensaje);

        String name = "No user is loged in";
        /**
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            name = authentication.getName();
        }
        */
        String queryString = request.getQueryString();
        String remoteAddress = request.getRemoteAddr();
        Date date = new Date();
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/")) {

            // Esta liea es provisional y deberá ser removida en cuanto sea posible:
            String mensajeImprimir = "\n Fecha: " + date.toString() + "\n username:" + name
                    + "\n queryString:" + queryString + "\n uri:" + uri + "\n remoteAddress:"
                    + remoteAddress;
            logger.info(mensajeImprimir);
            // Instead, we have to save object bitacora to DB here .... :)
        }
        return true;
    }

}
