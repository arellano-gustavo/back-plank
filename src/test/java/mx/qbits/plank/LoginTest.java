package mx.qbits.plank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.mapper.RegistroMapper;
import mx.qbits.plank.api.mapper.UsuarioMapper;
import mx.qbits.plank.api.model.Usuario;
import mx.qbits.plank.api.service.JwtManagerService;
import mx.qbits.plank.api.service.MailSenderService;
import mx.qbits.plank.api.service.UsuarioDetallesService;
import mx.qbits.plank.api.service.UsuarioDetallesServiceImpl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
    @Mock
    private UsuarioMapper usuarioMapper;
    @Mock
    private MailSenderService mailSenderService;
    @Mock
    private JwtManagerService jwtManagerService;
    @Mock
    private RegistroMapper registroMapper;
    
    private UsuarioDetallesService usuarioDetallesService;

    private long currentTimeDummy = System.currentTimeMillis();

    @Before
    public void prepara() {
        this.usuarioDetallesService = new UsuarioDetallesServiceImpl(
                usuarioMapper, 
                mailSenderService, 
                jwtManagerService, 
                registroMapper);
        Usuario usr = createUsuarioDummy();
        try {
            when(usuarioMapper.update(Mockito.any())).thenReturn(1);
            when(usuarioMapper.getByMail(Mockito.anyString())).thenReturn(usr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void actualTest() throws SQLException {
        Usuario usr = usuarioMapper.getByMail("xxxxxx");
        int k = usuarioMapper.update(usr);
        System.out.println("valor obtenido: "+k);
        extraTest();
        long bloqueadoHace = 17251;//1000*60*1;// un minuto
        String clave = "clave";
        try {
            setUsuarioState(createUsuarioDummy(), currentTimeDummy-bloqueadoHace, clave);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }
    
    public void setUsuarioState(Usuario usuario, long blockedTime, String clave) throws BusinessException {
        usuario.setActive(true);
        usuario.setPass("clave2");
        usuario.setWrongAccessCount(4);
        usuario.setBlockedTime(blockedTime);
        invokeUsuarioServiceLogin(usuario, clave);
    }
    
    public void invokeUsuarioServiceLogin(Usuario usuario, String clave) throws BusinessException {
        int invalidLoginTimes = 3;
        long timeUserBlockedInMnutes = 10;
        usuarioDetallesService.login(
                usuario, 
                clave, 
                timeUserBlockedInMnutes*60*1000, 
                invalidLoginTimes, 
                currentTimeDummy
        );
    }
    
    private Usuario createUsuarioDummy() {
        Usuario usuario = new Usuario();
        usuario.setCreated(1000000);
        usuario.setId(1);
        usuario.setRequestCompleted(false);
        usuario.setRequestKey("abc123XYZ");
        usuario.setRequestTime(2000000);
        usuario.setRequestType(1);
        usuario.setMail("gus123@aol.com");
        // Things to change...
        long bloqueadoHace = 59000;//1000*60*1;// un minuto
        usuario.setActive(true);
        usuario.setPass("clave2");
        usuario.setWrongAccessCount(3);
        usuario.setBlockedTime(currentTimeDummy-bloqueadoHace);
        return usuario;
    }
    
    private void extraTest() {
        try {
            Usuario usr = usuarioMapper.getByMail("abc@aol.com");
            System.out.println(usr.getMail());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
