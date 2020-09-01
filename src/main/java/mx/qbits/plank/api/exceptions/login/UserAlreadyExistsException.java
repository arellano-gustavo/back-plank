package mx.qbits.plank.api.exceptions.login;

import mx.qbits.plank.api.exceptions.BusinessException;

public class UserAlreadyExistsException extends BusinessException{
    private static final long serialVersionUID = -7083159020205284484L;
    private static final String CVE_EXCEPTION = "cve-2005";
    private static final int IDENTIFICADOR = 2005;
    
    public UserAlreadyExistsException() {
        super("Correo Existente");
        super.setCveExcepcion(CVE_EXCEPTION);
        super.setDescExcepcion("El correo proporcionado ya se encuentra registrado.");
        super.setIdentificador(IDENTIFICADOR);
    }

}
