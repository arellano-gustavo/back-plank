package mx.qbits.plank.api.exceptions.login;

import mx.qbits.plank.api.exceptions.BusinessException;

public class BadCredentialsException extends BusinessException{
    private static final long serialVersionUID = -7083159020205284484L;
    private static final String CVE_EXCEPTION = "cve-2002";
    private static final int IDENTIFICADOR = 2002;

    public BadCredentialsException() {
        super("Credenciales inválidas");
        super.setCveExcepcion(CVE_EXCEPTION);
        super.setDescExcepcion("Las credenciales proporcionadas son inválidas");
        super.setIdentificador(IDENTIFICADOR);
    }
    public BadCredentialsException(int counter, int maxAllowed) {
        super("Credenciales inválidas");
        super.setCveExcepcion(CVE_EXCEPTION);
        int restantes = maxAllowed -counter;
        super.setDescExcepcion("Las credenciales proporcionadas son inválidas. Usted tiene "+restantes+" intentos mas antes de que su usuario quede bloqueado.");
        super.setIdentificador(IDENTIFICADOR);
    }

}
