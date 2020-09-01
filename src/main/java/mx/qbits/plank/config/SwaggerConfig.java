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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * SwaggerConfig class.
 * </p>
 *
 * @author alvaro.salas
 * @version $Id: $Id
 */
@Profile("!production")
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * <p>
     * api.
     * </p>
     *
     * @return a {@link springfox.documentation.spring.web.plugins.Docket} object.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    /**
     * Api info.
     *
     * @return the api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Cinepolis, REST API Documentation by Super Goose!")
                .version("@PROJECT_VERSION@")
                .description("Servicios REST de backend publicados en formato JSON: <a href='/v2/api-docs'>https://app-url/v2/api-docs</a>")
                .license("Apache Licence")
                .termsOfServiceUrl("http://www.qbits.mx")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
