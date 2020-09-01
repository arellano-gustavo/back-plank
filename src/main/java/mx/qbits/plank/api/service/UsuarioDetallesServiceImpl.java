/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank
 * Paquete:     mx.qbits.plank.api.service
 * Modulo:      UsuarioDetalles
 * Tipo:        clase 
 * Autor:       Gustavo A. Arellano
 * Fecha:       martes 08 de agosto de 2020 (19_08)
 * Version:     0.1.1-SNAPSHOT
 * .
 * Servicio asociado a la entidad 'usuario_detalles'. 
 *
 * Historia:    .
 *              20200811_1908 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */

package mx.qbits.plank.api.service;

import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.qbits.plank.api.model.LoginResponse;
import mx.qbits.plank.api.model.Registro;
import mx.qbits.plank.api.model.RegistroRequest;
import mx.qbits.plank.api.model.Rol;
import mx.qbits.plank.api.model.Usuario;
import mx.qbits.plank.api.model.UsuarioDetalles;
import mx.qbits.plank.api.model.VwUsuarioAll;
import mx.qbits.plank.util.DigestEncoder;
import mx.qbits.plank.util.HashUtils;
import mx.qbits.plank.util.ValidadorClave;
import mx.qbits.plank.api.mapper.RegistroMapper;
import mx.qbits.plank.api.mapper.UsuarioMapper;
import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.exceptions.InvalidPassword;
import mx.qbits.plank.api.exceptions.confirm.TokenExpiredException;
import mx.qbits.plank.api.exceptions.confirm.TokenNotExistException;
import mx.qbits.plank.api.exceptions.confirm.WrongTokenException;
import mx.qbits.plank.api.exceptions.login.BadCredentialsException;
import mx.qbits.plank.api.exceptions.login.BlockedUserException;
import mx.qbits.plank.api.exceptions.login.DisabledUserException;
import mx.qbits.plank.api.exceptions.login.UserAlreadyExistsException;
import mx.qbits.plank.api.exceptions.login.WaitLoginException;

/**
 * <p>Descripción:</p>
 * Servicio asociado a la entidad 'usuario_detalles'. 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
@Service("usuarioDetallesService")
public class UsuarioDetallesServiceImpl implements UsuarioDetallesService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioDetallesServiceImpl.class);

    private UsuarioMapper usuarioMapper;
    private RegistroMapper registroMapper;
    private MailSenderService mailSenderService;
    private JwtManagerService jwtManagerService;
    
    private static final int RANDOM_STRING_LEN = 6;

    public UsuarioDetallesServiceImpl(
            UsuarioMapper usuarioMapper, 
            MailSenderService mailSenderService, 
            JwtManagerService jwtManagerService, 
            RegistroMapper registroMapper) {
        logger.info("Invoking UsuarioDetallesServiceImpl constructor");
        this.usuarioMapper = usuarioMapper;
        this.mailSenderService = mailSenderService;
        this.jwtManagerService = jwtManagerService;
        this.registroMapper = registroMapper;
    }

    @Transactional(
            propagation = Propagation.REQUIRED, 
            isolation = Isolation.DEFAULT,
            timeout = 36000, 
            rollbackFor = BusinessException.class)
    public boolean confirmacion(String randomString) throws BusinessException {
        // Si no hay registro notifica el error
        Registro registro = getByRandomString(randomString);
        if(registro==null) throw new TokenNotExistException();
        
        // Si ya expiró el token, notifica el error:
        long last = System.currentTimeMillis()-registro.getFechaRegistro();
        if(last>1000*60*50) { // Five minutes
            throw new TokenExpiredException();
        }
        
        // Si la clave no es la misma, notifica el error:
        if(!randomString.equals(registro.getRandomString())) {
            throw new WrongTokenException();
        }
        
        // Si todito lo anterior salió bien, guarda los datos y elimina el registro:
        try {
            doTransaction(registro, randomString);
        } catch (SQLException e) {
            throw new BusinessException("Registro fallido. Haciendo rollback a la transaccion");
        }
        return true;
    }
    
    @Override
    public int registro(RegistroRequest registroRequest) throws BusinessException {
        int id = 0;

        // Busca al usuario por su correo en la tabla de 'usuario'
        Usuario usuario = getUserByMail(registroRequest.getCorreo());
        
        // Si el usuario ya está en la tabla 'usuario', avisa error:
        if(usuario!=null) throw new UserAlreadyExistsException();

        // Busca el registro por mail en la tabla de 'registro':
        Registro registro = getRegistroByMail(registroRequest.getCorreo());
        
        // Genera una cadena aleatoria de caracteres y crea un objeto de tipo 'Registro':
        String randomString = HashUtils.getRandomString(RANDOM_STRING_LEN);
        Registro reg = new Registro(registroRequest, randomString);
        
        // Si el usuario NO está en la tabla de 'registro', insertar info:
        if(registro==null) {
            logger.info("Creando registro en la tabla 'Registro'");
            id = this.insertRegistro(reg);
        } else { // Si el usuario SI está: actualizar info:
            logger.info("Actualizando registro en la tabla 'Registro'");
            id = this.updateRegistro(reg);
        }
        
        // envia correo de notificación:
        sendMail(registroRequest.getCorreo(), randomString);
        logger.info("Se ha enviado un correo para confirmación a: " + registroRequest.getCorreo());
        return id;
    }
    
    /**
     * Valida que tanto el correo como la clave no sean vacias.
     * 
     * @param mail Correo del usuario
     * @param pass Clave del ususraio
     * 
     * @throws BusinessException Si alguna de ellas es inválida, se dispara esta excepcion
     */
    private void valida(String mail, String pass) throws BusinessException {
        if(mail==null || pass==null || mail.trim().length()<1 || pass.trim().length()<1) {
            throw new BusinessException("Datos de ingreso incorrectos", HttpStatus.BAD_REQUEST);
        }
    }

    private void sendMail(String correo, String randomString) {
        mailSenderService.sendHtmlMail(correo, "Confirmacion", "<h1>Clave: "+randomString+"</h1>");
    }
    
    private int update(Usuario usr) throws BusinessException {
        try {
            return usuarioMapper.update(usr);
        } catch (SQLException e) {
            throw new BusinessException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int insertRegistro(Registro registro) {
        try {
            return registroMapper.insertRegistro(registro);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return 0;
        }
    }
    
    private int updateRegistro(Registro registro) {
        try {
            return registroMapper.update(registro);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return 0;
        }
    }
    
    private Usuario getUserByMail(String mail) {
        Usuario usuario = null;
        try {
            usuario = usuarioMapper.getByMail(mail);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return usuario;
    }

    private Registro getRegistroByMail(String mail) {
        Registro registro = null;
        try {
            registro = registroMapper.getByMail(mail);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return registro;
    }

    private Registro getByRandomString(String randomString) throws BusinessException {
        try {
            return registroMapper.getByRandomString(randomString);
        } catch (SQLException e) {
            throw new BusinessException("Clave ramdomString no encontrada en la tabla registro");
        }
    }
    
    private int doTransaction(Registro registro, String randomString) throws SQLException {
        // Crea un usuario e insertalo
        Usuario usuario = new Usuario(
                0, 
                registro.getCorreo(), 
                registro.getClaveHash(), 
                System.currentTimeMillis(), // created
                true, // active
                0,  // wrongAccessCount
                0,  // lockedTime
                "", // randomString 
                1,  // requestType
                0,  // requestTime
                true // requestCompleted
        );
        usuarioMapper.insert(usuario);
        int idUsuario = usuario.getId();
        
        // Crea un usuarioDetalles e insertalo
        UsuarioDetalles usuarioDetalles = new UsuarioDetalles(
                idUsuario, 
                registro.getNombre(), 
                registro.getTelefono(), 
                registro.getDireccion(), 
                new Date());
        registroMapper.insertUsuarioDetalles(usuarioDetalles);
        
        // asociar el usuario recién creado con el rol 2:
        registroMapper.asociateRol(idUsuario, 2);
        
        // Borra lo que tengas en la tabla registro
        registroMapper.deleteByRandomString(randomString);
        return idUsuario;
    }
    
    @Override
    public String forgotPassword(String mail, long currentTime) throws BusinessException {
        try {
            List<VwUsuarioAll> lista = usuarioMapper.getUsuarioTable();
            for(VwUsuarioAll row : lista) {
                System.out.println(row.getMail());
            }
            Usuario usuario = usuarioMapper.getByMail(mail);
            if(usuario!=null) {
                String randomString = HashUtils.getRandomString(6);
                usuario.setRequestType(1);
                usuario.setRequestTime(currentTime);
                usuario.setRequestCompleted(false);
                usuario.setRequestKey(randomString);
                usuarioMapper.update(usuario);
                sendMail(mail, randomString);
            }
        } catch (SQLException e) {
            throw new BusinessException("Error interno al actualizar el password: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "{'code-sent-to':'"+mail+"'}".replace("'", "\"");
    }
    
    @Override
    public String confirmForgotPassword(String code, String pass, long currentTime, long delta) throws BusinessException {
        try {
            Usuario usuario = usuarioMapper.getByCode(code);
            if(usuario==null) {
                throw new BusinessException("Bad code");
            }
            if(usuario.getRequestType()!=1) {
                throw new BusinessException("Wrong Request. Not for forget password");
            }
            if(usuario.getRequestCompleted()) {
                throw new BusinessException("Request already completed");
            }
            if(currentTime>usuario.getRequestTime()+delta) {
                throw new TokenExpiredException();
            }
            if(!code.equals(usuario.getRequestKey())) {
                throw new BusinessException("Bad code");
            }
            if(pass.trim().length()<1) {
                throw new BusinessException("La clave no puede ser vacia ni una cadena en balnco.");
            }

            ValidadorClave.validate(pass);
            
            // Si todito lo anterior no represntó un error:
            usuario.setRequestType(0);
            usuario.setRequestTime(0);
            usuario.setRequestCompleted(true);
            usuario.setRequestKey("");
            usuario.setPass(DigestEncoder.digest(pass, usuario.getMail()));
            usuarioMapper.update(usuario);
            sendMail(usuario.getMail(), "Clave cambiada exitosamente");
            return "{'code-sent-to':'"+usuario.getMail()+"'}".replace("'", "\"");            
        } catch (SQLException e) {
            throw new BusinessException("Error interno al actualizar el password: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }
    
    @Override
    public String changePassword(String jwt, String mail, String nuevaClave, String claveAnterior) throws BusinessException {
        // Valida el token:
        if(!jwtManagerService.verifyToken(jwt, mail)) {
            throw new BusinessException("Error de autenticacion. jwt inválido.", HttpStatus.FORBIDDEN);
        }
        
        // Actualiza password
        try {
            // Existe un usuario con ese mail?
            Usuario usuario = usuarioMapper.getByMail(mail);
            if(usuario==null) throw new BusinessException("changePassword error. Mail not found", HttpStatus.BAD_REQUEST);
            
            // validar que el password antiguo sea correcto...
            String pDig = DigestEncoder.digest(claveAnterior, mail);
            if(!pDig.equals(usuario.getPass())) throw new InvalidPassword("La clave anterior es incorrecta");
            
            // Valida que la clave proporcionada cumpla con las reglas establecidas:
            ValidadorClave.validate(nuevaClave);
            
            // Si cumple, entonces guarda el nuevo password digesto:
            usuario.setPass(DigestEncoder.digest(nuevaClave, mail));
            usuarioMapper.update(usuario);
        } catch (SQLException e) {
            throw new BusinessException("Error interno al actualizar el password: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        // Si todito bien, retorna un "ok".
        return "{'succeed':'true'}".replace("'", "\"");
    }
    
    @Override
    public LoginResponse login(Usuario usuario, String pass, long delta, int invalidLoginMax, long currentTime) throws BusinessException {
        // Notifica, si el usuario no pudo ser encontrado:
        if(usuario==null) throw new BadCredentialsException();
        
        // Si el usuario fue encontrado, pero está inactivo, Notifica:
        if(!usuario.getActive()) throw new DisabledUserException();
        
        // Calcula cuanto tiempo lleva bloqueado el usuario. Si lleva menos de lo establecido, Notifica:
        long diff = currentTime - usuario.getBlockedTime();
        long restante = delta - diff;
        if(usuario.getBlockedTime()>0 && restante>0) throw new WaitLoginException(restante/1000);
        
        // Comportamiento cuando las Credenciales son INCORRECTAS
        String pass2 = DigestEncoder.digest(pass, usuario.getMail());
        System.out.println("--------------->"+pass2+"<----------------------");
        if(!usuario.getPass().equals(pass2)) {
            // Incrementa el contador de intentos erroneos de ingreso y actualiza:
            int invalidLoginAttemps = usuario.getWrongAccessCount()+1;
            usuario.setWrongAccessCount(invalidLoginAttemps);
            this.update(usuario);
            
            // Si los intentos de ingreso inválidos superan un limite, actualiza y Notifica:
            if(invalidLoginAttemps>=invalidLoginMax) {
                usuario.setBlockedTime(currentTime);
                this.update(usuario);
                throw new BlockedUserException(invalidLoginMax, delta);
            }
            
            // Si no se disparó la Notificación anterior, de todas formas notifica un intento
            // fallido de ingreso al sistema:
            throw new BadCredentialsException(invalidLoginAttemps, invalidLoginMax);
        } else { // Credenciales CORRECTAS
            // Resetea todoas las banderas de advertencia y bloqueo. Luego, actualiza y retorna el usuario:
            usuario.setWrongAccessCount(0);
            usuario.setBlockedTime(0);
            usuario.setLastAccessTime(currentTime);
            this.update(usuario);
            
            // Crea un token y regrésalo junto con el usuario y los roles:
            String jwt = jwtManagerService.createToken(usuario.getMail());
            List<Rol> roles = getRolesFromUserId(usuario.getId());
            UsuarioDetalles usuarioDetalles = getUsuarioDetalles(usuario.getId());

            // Esto va al front y se almacena en 'localStorage' (setItem)
            // https://gitlab.ci.ultrasist.net/root/impi-chatbot-frontend/blob/develop/src/components/04-LogIn/login.vue
            return new LoginResponse(usuario, roles, jwt, usuarioDetalles); // Mejor hay que retornar Usuario + Roles en un objeto que los encapsule
        }
    }
    
    private List<Rol> getRolesFromUserId(long id) {
        return usuarioMapper.getRolesFromUserId(id);
    }
    
    private UsuarioDetalles getUsuarioDetalles(long id) throws BusinessException {
        try {
            return usuarioMapper.getUsuarioDetalles(id);
        } catch (SQLException e) {
            throw new BusinessException(e.getMessage());
        }
    }
    
    @Override
    public LoginResponse login(String mail, String pass) throws BusinessException {
        this.valida(mail, pass);
        int invalidLoginMax = 5; // 5 intentos
        long delta = 1000*60*5L; // 5 minutos
        long currentTime = System.currentTimeMillis();
        Usuario usuario = getByMail(mail);
        return login(usuario, pass, delta, invalidLoginMax, currentTime);
    }
    
    /**
     * Método utilizado para recuperar un elemento de la tabla 'USUARIO'. por medio de su correo 
     * electrónico.
     * 
     * @param mail String que representa el correo de un usuario.
     * @return Objeto de tipo 'Usuario' asociado al correo dado.
     */
    public Usuario getByMail(String mail) {
        try {
            return usuarioMapper.getByMail(mail);
        } catch (SQLException e) {
            // Aqui particularmente me interesa que NO se dispare una excepción cuando NO sea 
            // posible recuperar un usuario a partir de su correo. En vez de ello, registro el
            // fallo y retorno una cadena vacia.
            logger.error(e.getMessage());
            return null;
        }
    }


}
