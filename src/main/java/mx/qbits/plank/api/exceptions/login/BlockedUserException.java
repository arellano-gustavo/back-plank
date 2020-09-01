package mx.qbits.plank.api.exceptions.login;

import mx.qbits.plank.api.exceptions.BusinessException;

public class BlockedUserException extends BusinessException{
    private static final long serialVersionUID = -7083159020205284484L;
    private static final String CVE_EXCEPTION = "cve-2003";
    private static final int IDENTIFICADOR = 2003;

    public BlockedUserException(int intentos, long delta) {
        super("Número máximo de intentos de ingreso excedido.");
        super.setDescExcepcion("El usuario ha sido bloqueado por exceder el número máximo de intentos ("+intentos+") de ingreso permitidos al sistema.");
        super.setCveExcepcion(CVE_EXCEPTION);
        super.setIdentificador(IDENTIFICADOR);
    }

}