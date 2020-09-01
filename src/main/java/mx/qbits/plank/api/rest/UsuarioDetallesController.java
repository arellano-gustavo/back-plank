/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank
 * Paquete:     mx.qbits.plank.api.rest
 * Modulo:      UsuarioDetallesController
 * Tipo:        clase 
 * Autor:       Gustavo A. Arellano
 * Fecha:       martes 08 de agosto de 2020 (19_08)
 * Version:     0.1.1-SNAPSHOT
 * .
 * Rest controller asociado a la entidad 'usuario_detalles'. 
 *
 * Historia:    .
 *              20200811_1908 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */

package mx.qbits.plank.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.model.GoogleCaptcha;
import mx.qbits.plank.api.model.RegistroRequest;
import mx.qbits.plank.api.service.InvokeRestServiceImpl;
import mx.qbits.plank.api.service.UsuarioDetallesService;

@RestController
@Api(value = "administracion")
@RequestMapping(value = "/api")
public class UsuarioDetallesController {
    @Autowired
    private UsuarioDetallesService usuarioDetallesService;
    
    @Autowired
    private InvokeRestServiceImpl invokeRestServiceImpl;
    
    @PostMapping(
            value = "/register.json",
            produces = "application/json; charset=utf-8")
    public int registro(@RequestBody RegistroRequest registroRequest) throws BusinessException {
        return usuarioDetallesService.registro(registroRequest);
    }
    
    @GetMapping(
            value = "/confirm-register.json",
            produces = "application/json; charset=utf-8")
    public boolean confirm(@RequestParam String code) throws BusinessException {
        return usuarioDetallesService.confirmacion(code);
    }
    
    @PostMapping(
            value = "/check-captcha.json",
            produces = "application/json; charset=utf-8")
    public String checkCaptcha(@RequestBody GoogleCaptcha googleCaptcha) throws BusinessException {
        return invokeRestServiceImpl.checkCaptcha(googleCaptcha);
    }

}
