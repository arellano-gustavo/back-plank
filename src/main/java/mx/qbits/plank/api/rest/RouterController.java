/*
 *
 *  *
 *  *  *
 *  *  *  Licencia:    Este código se encuentra bajo la protección
 *  *  *               que otorga el contrato establecido entre
 *  *  *               Ultrasist SA de CV y su cliente, Cinepolis, por lo
 *  *  *               que queda estrictamente prohibido copiar, donar
 *  *  *               vender y/o distribuir el presente código por
 *  *  *               cualquier medio electrónico o impreso sin el
 *  *  *               permiso explícito y por escrito del cliente.
 *  *  *
 *  *  *  Proyecto:   plank-back
 *  *  *  Modulo:     plank-back
 *  *  *  Clase          RouterController
 *  *  *  Autor:
 *  *  *  Fecha:        4/30/20, 11:42 PM
 *  *  *  Version:     0.0.1-SNAPSHOT
 *  *  *
 *  *  *  /
 *  *
 *
 *
 */

package mx.qbits.plank.api.rest;

import io.swagger.annotations.Api;
import mx.qbits.plank.util.JWTUtil;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Router del endpoint.
 */
@RestController
@Api(value = "router")
@RequestMapping(value = "/")
public class RouterController {

    @GetMapping(value = "/ui/**")
    public ModelAndView redirectWithUsingForwardPrefix(ModelMap model) {
        model.addAttribute("attribute", "forwardWithForwardPrefix");
        return new ModelAndView("forward:/", model);
    }

    @GetMapping("/token.json")
    public String getToken(){
        return JWTUtil.getJWTToken();
    }

    @GetMapping("/shutdown.json")
    public void shutdown(){
        System.exit(0);
    }

}
