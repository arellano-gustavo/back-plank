package mx.qbits.plank.api.exceptions;

public class InvalidPassword extends BusinessException {
    private static final long serialVersionUID = -1222301152057974505L;

    private static final String CVE_EXCEPTION = "cve-3002";
    private static final int IDENTIFICADOR = 3002;

    public InvalidPassword(String message) {
        super("Clave inválida");
        super.setCveExcepcion(CVE_EXCEPTION);
        super.setDescExcepcion(message);
        super.setIdentificador(IDENTIFICADOR);
    }
}
