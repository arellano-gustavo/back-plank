package mx.qbits.plank.util;

public class HashUtils {
    
    /**
     * Constructor privado para clase con puros métodos estáticos
     */
    private HashUtils() {
    }
    
    /**
     * Retorna una cadena de 'len' caracteres aleatoria
     * 
     * @param len longitud de la cadena retornada
     * @return String aleatorio
     */
    public static String getRandomString(int len) {
        StringBuilder result = new StringBuilder();
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for(int i =0; i<len; i++) {
            double position = Math.random()*(base.length()+1);
            result.append(base.charAt((int)position));
        }
        return result.toString();
    }

}
