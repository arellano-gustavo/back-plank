/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank
 * Paquete:     mx.qbits.plank.api.rest
 * Modulo:      Usuario
 * Tipo:        clase 
 * Autor:       Gustavo A. Arellano
 * Fecha:       jueves 07 de julio de 2020 (18_08)
 * Version:     0.1.1-SNAPSHOT
 * .
 * Rest controller asociado a la entidad 'Usuario'. 
 *
 * Historia:    .
 *              20200723_1808 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */
package mx.qbits.plank.api.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.model.CambiaClave;
import mx.qbits.plank.api.model.Credenciales;
import mx.qbits.plank.api.model.LoginResponse;
import mx.qbits.plank.api.model.Usuario;
import mx.qbits.plank.api.service.UsuarioDetallesService;
import mx.qbits.plank.api.service.UsuarioService;

@RestController
@Api(value = "administracion")
@RequestMapping(value = "/api")
public class UsuarioRest {

    private UsuarioService usuarioService;
    private UsuarioDetallesService usuarioDetallesService;
    
    
    public UsuarioRest(UsuarioService usuarioService, UsuarioDetallesService usuarioDetallesService) {
        this.usuarioService = usuarioService;
        this.usuarioDetallesService = usuarioDetallesService;
    }

    @ApiOperation(
        value = "UsuarioController::getAll",
        notes = "Regresa una lista de todos los objetos Usuario "
            + "debidamente paginados con base en el payload de "
            + "request que determina el tamaño de la página, la "
            + "longitud de la página, el campo por el que se va a "
            + "ordenar y si el orden es ascendente o descendente."
            + "<br/><br/>"
            + "En el caso de que los parámetros proporcionados "
            + "<b><i><label style='color:blue;'>excedan</label><i></b> las "
            + "dimensiones de la lista real de datos, este método es "
            + "capaz de ajustar lo necesario para que la lista resultante "
            + "sea suceptible de ser manipulada adecuadamente.")
    @GetMapping(
        value = "/usuarios.json",
        produces = "application/json; charset=utf-8")
    public List<Usuario> getAllUsuario() throws BusinessException {
        return usuarioService.getAll();
    }

    @GetMapping(
        value = "/usuario.json",
        produces = "application/json; charset=utf-8")
    public Usuario getUsuario(@RequestParam long id) throws BusinessException {
        return usuarioService.getById(id);
    }

    @PostMapping(
            value = "/usuario.json",
            produces = "application/json; charset=utf-8")
    public int insert(@Valid @RequestBody Usuario usr) throws BusinessException {
        return usuarioService.insert(usr);
    }

    @PutMapping(
            value = "/usuario.json",
            produces = "application/json; charset=utf-8")
    public int update(@RequestBody Usuario usr) throws BusinessException {
        return usuarioService.update(usr);
    }
    
    @PostMapping(
            value = "/login.json",
            produces = "application/json; charset=utf-8")
    public LoginResponse login(@RequestBody Credenciales cred) throws BusinessException {
        return usuarioDetallesService.login(cred.getUsr(), cred.getPass());
    }

    @ApiOperation(
            value = "UsuarioRest::change-password",
            notes = "Realiza el cambio de la clave de un usuario")
    @PostMapping(
            value = "/change-password.json",
            produces = "application/json; charset=utf-8")
    public String changePassword(@RequestHeader("X-CSRFToken") String jwt, @RequestBody CambiaClave cambiaClave) throws BusinessException {
        return usuarioDetallesService.changePassword(jwt, cambiaClave.getUsr(), cambiaClave.getNuevaClave(), cambiaClave.getClaveAnterior());
    }

    @ApiOperation(
            value = "UsuarioRest::forgot-password",
            notes = "Realiza solicitud de regeneración de la clave de un usuario")
    @GetMapping(
            value = "/forgot-password.json",
            produces = "application/json; charset=utf-8")
    public String forgotPassword(@RequestParam String mail) throws BusinessException {
        return usuarioDetallesService.forgotPassword(mail, System.currentTimeMillis());
    }

    @ApiOperation(
            value = "UsuarioRest::forgot-password",
            notes = "Confirma solicitud de regeneración de la clave de un usuario")
    @GetMapping(
            value = "/confirm-forgot-password.json",
            produces = "application/json; charset=utf-8")
    public String confirmForgotPassword(@RequestParam String code, @RequestParam String pass) throws BusinessException {
        long currentTime = System.currentTimeMillis();
        long delta = 1000*60*10L;
        return usuarioDetallesService.confirmForgotPassword(code, pass, currentTime, delta);
    }

}
