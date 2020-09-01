/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank-backend
 * Paquete:     mx.qbits.plank.api.service
 * Modulo:      USUARIO
 * Tipo:        interface 
 * Autor:       Gustavo A. Arellano
 * Fecha:       jueves 07 de julio de 2020 (18_08)
 * Version:     0.1.1-SNAPSHOT
 * .
 * Interface para el servicio asociado a la entidad 'USUARIO'. 
 *
 * Historia:    .
 *              20200723_1808 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */
package mx.qbits.plank.api.service;

import java.util.List;
import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.model.Usuario;

/**
 * <p>Descripción:</p>
 * Interface para el servicio asociado a la entidad 'USUARIO'. 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
public interface UsuarioService {

    /**
     * Método utilizado para recuperar un elemento de la tabla 'USUARIO'. por medio de su llave primaria.
     * 
     * @param usr Instancia de USUARIO con los datos de la llave.
     * @return La información del elemento recuperado en una instacia de la clase Empleado o nulo si no se encuentra ese elemento en la tabla.
     * @throws Exception es disparada por una regla de negocio
     */
    Usuario getById(long id) throws BusinessException;

    /**
     * Método utilizado para obtener una lista con todos los elementos de la tabla 'Usuario'.
     * 
     * @return Lista con todos los elementos de la tabla 'Usuario'.
     * @throws Exception es disparada por una regla de negocio
     */
    List<Usuario> getAll() throws BusinessException;

    /**
     * Método utilizado para insertar un registro en la tabla 'USUARIO'.
     * 
     * @param usr objeto de tipo 'USUARIO'.
     * @return int numero de registros insertados en la tabla'USUARIO'.
     * @throws Exception es disparada por una regla de negocio
     */
    int insert(Usuario usr) throws BusinessException;

    /**
     * Método utilizado para actualizar un registro en la tabla 'USUARIO'.
     * 
     * @param usr objeto de tipo 'USUARIO'.
     * @return int numero de registros actualizados en la tabla'USUARIO'.
     * @throws Exception es disparada por una regla de negocio
     */
    int update(Usuario usr) throws BusinessException;

    /**
     * Método utilizado para guardar la información de un elemento en la tabla 'USUARIO'.
     * si el elemento no existe se agrega a la base de datos.
     * 
     * @param usr Información del elemento a guardar.
     * @return el id autogenerado de la inserción del objeto
     * @throws Exception En caso un error al momento de guardar los datos. 
     */
    int save(Usuario usr) throws BusinessException;

}

