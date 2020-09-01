package mx.qbits.plank.api.exceptions.login;

import mx.qbits.plank.api.exceptions.BusinessException;

public class DisabledUserException extends BusinessException{
    private static final long serialVersionUID = -7083159020205284484L;
    private static final String CVE_EXCEPTION = "cve-2004";
    private static final int IDENTIFICADOR = 2004;

    public DisabledUserException() {
        super("El usuario está deshabilitado");
        super.setCveExcepcion(CVE_EXCEPTION);
        super.setDescExcepcion("El usuario ha sido deshabilitado");
        super.setIdentificador(IDENTIFICADOR);
    }
}
