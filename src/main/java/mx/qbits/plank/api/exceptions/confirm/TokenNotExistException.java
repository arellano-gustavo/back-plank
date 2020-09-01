package mx.qbits.plank.api.exceptions.confirm;

import mx.qbits.plank.api.exceptions.BusinessException;

public class TokenNotExistException extends BusinessException {
    private static final long serialVersionUID = -7083159020205284484L;
    private static final String CVE_EXCEPTION = "cve-2006";
    private static final int IDENTIFICADOR = 2006;
    
    public TokenNotExistException() {
        super("Token inexistente");
        super.setCveExcepcion(CVE_EXCEPTION);
        super.setDescExcepcion("El token proporcionado no se encuentra registrado.");
        super.setIdentificador(IDENTIFICADOR);
    }

}
