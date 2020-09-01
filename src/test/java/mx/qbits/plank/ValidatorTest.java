package mx.qbits.plank;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import mx.qbits.plank.api.model.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
public class ValidatorTest {
    private Logger logger = LoggerFactory.getLogger(ValidatorTest.class);
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void encryptorTest() {
        Usuario usr = new Usuario();
        usr.setMail("@aool.com");
        usr.setPass("ok");
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usr);
        for(ConstraintViolation<Usuario> cv : violations) {
            System.out.println(cv.getMessage());
        }
        String msg = violations.iterator().next().getMessage();
        logger.info(msg);
        assertEquals(true, msg.length()>0);
    }

}
