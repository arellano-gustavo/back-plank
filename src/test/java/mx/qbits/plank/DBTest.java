package mx.qbits.plank;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.model.Usuario;
import mx.qbits.plank.api.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBTest {
    private Logger logger = LoggerFactory.getLogger(DBTest.class);
    
    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void doit() {
        try {
            List<Usuario> lista = usuarioService.getAll();
            for(Usuario usr : lista) {
                logger.info("Correo: "+usr.getMail());
            }
            assertTrue(true);
        } catch (BusinessException e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }
}
