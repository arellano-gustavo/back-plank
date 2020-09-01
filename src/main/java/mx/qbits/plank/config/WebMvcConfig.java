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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase WebMvcConfig.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** logger. */
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/404/**")
            .addResourceLocations("classpath:/public/error/clouds-404/");
    }

    @Autowired
    private AuthenticationServiceInterceptor productServiceInterceptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor());
        registry.addInterceptor(productServiceInterceptor);// quizá en vez de un Autowire, aqui puedo hace un 'new'
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Disabling CORS");
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST","PUT", "DELETE", "OPTIONS", "HEAD");
    }

}
