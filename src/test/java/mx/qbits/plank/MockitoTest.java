package mx.qbits.plank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mx.qbits.plank.api.model.Usuario;
import mx.qbits.plank.api.service.UsuarioDetallesService;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @Mock
    private UsuarioDetallesService usuarioDetallesService;

    @Before
    public void prepara() {
        Usuario usr2 = new Usuario();
        usr2.setMail("tavo@aol.com");
        when(usuarioDetallesService.getByMail("gus@yahoo.com")).thenReturn(usr2);
    }

    @Test
    public void pba() {
        Usuario dato = usuarioDetallesService.getByMail("gus@yahoo.com");
        assertTrue("tavo@aol.com".equals(dato.getMail()));
    }
}
/*
Gracias a Mockito:
 - No dependo de una base de datos
 - No se presentan race conditions
 - No tengo que levantar el contexto de Spring
 - No tengo que restaurar el estado inicial de una prueba
*/