package mx.qbits.plank.api.exceptions.confirm;

import mx.qbits.plank.api.exceptions.BusinessException;

public class TokenExpiredException extends BusinessException {
    private static final long serialVersionUID = -7083159020205284484L;
    private static final String CVE_EXCEPTION = "cve-2007";
    private static final int IDENTIFICADOR = 2007;
    
    public TokenExpiredException() {
        super("Token expirado");
        super.setCveExcepcion(CVE_EXCEPTION);
        super.setDescExcepcion("Clave expirada. Repita solicitud");
        super.setIdentificador(IDENTIFICADOR);
    }

}
