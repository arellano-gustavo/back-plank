/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank
 * Paquete:     mx.qbits.plank.api.service
 * Modulo:      UsuarioDetalles
 * Tipo:        interface 
 * Autor:       Gustavo A. Arellano
 * Fecha:       martes 08 de agosto de 2020 (19_08)
 * Version:     0.1.1-SNAPSHOT
 * .
 * Interface para el servicio asociado a la entidad 'usuario_detalles'. 
 *
 * Historia:    .
 *              20200811_1908 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */
package mx.qbits.plank.api.service;

import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.model.LoginResponse;
import mx.qbits.plank.api.model.RegistroRequest;
import mx.qbits.plank.api.model.Usuario;

/**
 * <p>Descripción:</p>
 * Interface para el servicio asociado a la entidad 'usuario_detalles'. 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
public interface UsuarioDetallesService {
    int registro(RegistroRequest registroRequest) throws BusinessException;
    boolean confirmacion(String randomString) throws BusinessException;
    String forgotPassword(String mail, long currentTime) throws BusinessException;
    String confirmForgotPassword(String code, String pass, long currentTime, long delta) throws BusinessException;
    String changePassword(String jwt, String mail, String password, String claveAnterior) throws BusinessException;
    Usuario getByMail(String mail);
    
    /**
     * Método empleado para vertificar credenciales de acceso al sistema. Las credenciales
     * que se emplean son el correo y el password, que son provistos como parámetros del 
     * método. Este método es capaz de bloquear por un cierto numero de minutos a un usuario
     * que haya intentado acceder al sistema un cierto numero de veces sin éxito. En este
     * caso, el método informará de cada error con un texto informativo adecuado. 
     * 
     * @param mail String que representa el correo del usuario que desea ingresar al sistema.
     * @param pass String que representa la clave de acceso al sistema para el correo dado.
     * 
     * @return LoginResponse en caso de que las credenciales dedas sean correctas.
     * 
     * @throws BusinessException en caso de que se presente algún problema al intentar iniciar sesión.
     */
    LoginResponse login(String mail, String pass) throws BusinessException;
    
    /**
     * Método auxiliar del método 'login(String mail, String pass). En este método se intentan 
     * abstraer y parametrizar los valores empleados para determinar la lógica de bloqueo y espera 
     * en caso de que un usuario intente sin éxito ingresar al sistema repetidamente.<p>
     * 
     * Método que valida la solicitud de ingreso de un usuario al sistema con base en sus
     * credenciales y el estado de éste respecto a sus solicitudes previas. Si las credenciales 
     * son correctas y no existe un impedimento para que ingrese al sistema (como por ejemplo)
     * que el usuario esté deshabilitado o bloqueado por intentar ingresar varias veces de 
     * manera errónea, entonces este método retornará un objeto de tipo usuario asociado al correo
     * dado como parámetro formal. <p>
     * 
     * 
     * Nota importante: Cada Excepción es registrada el el archivo de 'log' de manera automática,
     * dado que el CustomControllerAdvice intercepta todas las excepciones de tipo BusinessException
     * y las registra en el archivo de 'log'. 
     * 
     * @param usuario Objeto de tipo 'Usuario' con toda la información del usuario que intenta ingresar al sistema.
     * @param pass String que representa la clave de acceso al sistema para el correo dado.
     * @param delta long que representa el numero de milisegundos que el usuario deberá permanecer bloqueado
     * @param invalidLoginMax int que representa el máximo numero de intentos fallidos permitidos
     * @param currentTime long que representa el tiempo actual del sistema
     * 
     * @return LoginResponse en caso de que las credenciales dedas sean correctas.
     * @throws BusinessException en caso de que se presente algún problema al intentar iniciar sesión.
     */
    LoginResponse login(Usuario usuario, String pass, long delta, int invalidLoginMax, long currentTime) throws BusinessException;

}
