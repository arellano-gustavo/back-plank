package mx.qbits.plank.api.exceptions.login;

import mx.qbits.plank.api.exceptions.BusinessException;

public class WaitLoginException extends BusinessException{
    private static final long serialVersionUID = -7083159020205284484L;
    private static final String CVE_EXCEPTION = "cve-2006";
    private static final int IDENTIFICADOR = 2006;

    public WaitLoginException(long segundos) {
        super(calc(segundos));
        super.setCveExcepcion(CVE_EXCEPTION);
        super.setIdentificador(IDENTIFICADOR);
        super.setDescExcepcion(calc(segundos));
    }
    
    private static String calc(long segundos) {
        long minutos = segundos/60;
        long seg = segundos%60;
        return "El usuario ha sido bloqueado por los próximos "+minutos+" minutos y "+seg+" segundos";
    }

}