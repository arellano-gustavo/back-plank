/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank-backend
 * Paquete:     mx.qbits.plank.api.mapper
 * Modulo:      Usuario
 * Tipo:        interface 
 * Autor:       Gustavo A. Arellano
 * Fecha:       jueves 07 de julio de 2020 (18_08)
 * Version:     0.1.1-SNAPSHOT
 * .
 * Interface    'Mapper' MyBatis asociado a la entidad Usuario 
 *
 * Historia:    .
 *              20200723_1808 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */
package mx.qbits.plank.api.mapper;

import java.util.List;
import java.sql.SQLException;
import org.apache.ibatis.annotations.*;

import mx.qbits.plank.api.model.Rol;
import mx.qbits.plank.api.model.Usuario;
import mx.qbits.plank.api.model.UsuarioDetalles;
import mx.qbits.plank.api.model.VwUsuarioAll;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad 'usuario' 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
public interface UsuarioMapper {
    /**
     * Obtiene una lista paginada de objetos de tipo 'usuario' ordenada por una cierta 
     * columna, de manera ascendente.
     *
     * @param Objeto de tipo 'PageBoundaries' que define el tamaño de una determinada 
     * página de resultados y define la columna para ordenación.
     *
     * @return Lista paginada, ordenanda por un cierto campo de manera ascendente.
     * 
     * @throws SQLException Se dispara en caso de que se dispare un error en esta operación desde la base de datos.
     */
    @Results(value = {
            @Result(property = "wrongAccessCount", column = "wrong_access_count"),
            @Result(property = "blockedTime",      column = "blocked_time"),
            @Result(property = "requestKey",       column = "request_key"),
            @Result(property = "requestTime",      column = "request_time"),
            @Result(property = "requestType",      column = "request_type"),
            @Result(property = "requestCompleted", column = "request_completed"),
            @Result(property = "lastAccessTime",         column = "last_access_time"),
            @Result(property = "lastPasswordUpdateTime", column = "last_password_update_time")
          })
    @Select("SELECT id, mail, pass, created, active, wrong_access_count, blocked_time, request_key, request_type, request_time, request_completed, last_access_time, last_password_update_time FROM usuario order by #{sortColumn} asc")
    List<Usuario> getAllAscending(String sortColumn) throws SQLException;
    
    /**
     * Obtiene una lista paginada de objetos de tipo 'usuario' ordenada por una cierta 
     * columna, de manera descendente.
     *
     * @param Objeto de tipo 'PageBoundaries' que define el tamaño de una determinada 
     * página de resultados y define la columna para ordenación.
     *
     * @return Lista paginada, ordenanda por un cierto campo de manera descendente.
     * 
     * @throws SQLException Se dispara en caso de que se dispare un error en esta operación desde la base de datos.
     */
    @Results(value = {
            @Result(property = "wrongAccessCount", column = "wrong_access_count"),
            @Result(property = "blockedTime",      column = "blocked_time"),
            @Result(property = "requestKey",       column = "request_key"),
            @Result(property = "requestTime",      column = "request_time"),
            @Result(property = "requestType",      column = "request_type"),
            @Result(property = "requestCompleted", column = "request_completed"),
            @Result(property = "lastAccessTime",         column = "last_access_time"),
            @Result(property = "lastPasswordUpdateTime", column = "last_password_update_time")
          })
    @Select("SELECT id, mail, pass, created, active, wrong_access_count, blocked_time, request_key, request_type, request_time, request_completed, last_access_time, last_password_update_time FROM usuario order by #{sortColumn} desc")
    List<Usuario> getAllDescending(String sortColumn) throws SQLException;
    
    /**
     * Obtiene un objeto de tipo 'usuario' realizando la búsqueda con base en el ID del Usuario.
     *
     * @param int ID del usuario.
     *
     * @return el usuario encontrado con el criterio de búsqueda.
     * 
     * @throws SQLException Se dispara en caso de que se dispare un error en esta operación desde la base de datos.
     */
    @Results(value = {
            @Result(property = "wrongAccessCount", column = "wrong_access_count"),
            @Result(property = "blockedTime",      column = "blocked_time"),
            @Result(property = "requestKey",       column = "request_key"),
            @Result(property = "requestTime",      column = "request_time"),
            @Result(property = "requestType",      column = "request_type"),
            @Result(property = "requestCompleted", column = "request_completed"),
            @Result(property = "lastAccessTime",         column = "last_access_time"),
            @Result(property = "lastPasswordUpdateTime", column = "last_password_update_time")
          })
    @Select("SELECT id, mail, pass, created, active, wrong_access_count, blocked_time, request_key, request_type, request_time, request_completed, last_access_time, last_password_update_time FROM usuario WHERE id = #{id} ") 
    Usuario getById(long id) throws SQLException;
    
    /**
     * Obtiene un objeto de tipo 'usuario' realizando la búsqueda con base en el 'mail' del Usuario.
     *
     * @param String mail del usuario.
     *
     * @return el usuario encontrado con el criterio de búsqueda.
     * 
     * @throws SQLException Se dispara en caso de que se dispare un error en esta operación desde la base de datos.
     */
    @Results(value = {
            @Result(property = "wrongAccessCount", column = "wrong_access_count"),
            @Result(property = "blockedTime",      column = "blocked_time"),
            @Result(property = "requestKey",       column = "request_key"),
            @Result(property = "requestTime",      column = "request_time"),
            @Result(property = "requestType",      column = "request_type"),
            @Result(property = "requestCompleted", column = "request_completed"),
            @Result(property = "lastAccessTime",         column = "last_access_time"),
            @Result(property = "lastPasswordUpdateTime", column = "last_password_update_time")
          })
    @Select("SELECT id, mail, pass, created, active, wrong_access_count, blocked_time, request_key, request_type, request_time, request_completed, last_access_time, last_password_update_time FROM usuario WHERE mail = #{mail} ") 
    Usuario getByMail(String mail) throws SQLException;
    
    /**
     * Inserta un objeto de tipo 'usuario' con base en la infrmación dada por el objeto de tipo 'usuario'.
     *
     * @param Usuario a ser insertado.
     *
     * @return el auto incremental asociado a esa inserción.
     * 
     * @throws SQLException Se dispara en caso de que se dispare un error en esta operación desde la base de datos.
     */
    @Results(value = {
            @Result(property = "wrongAccessCount", column = "wrong_access_count"),
            @Result(property = "blockedTime",      column = "blocked_time"),
            @Result(property = "requestKey",       column = "request_key"),
            @Result(property = "requestTime",      column = "request_time"),
            @Result(property = "requestType",      column = "request_type"),
            @Result(property = "requestCompleted", column = "request_completed"),
            @Result(property = "lastAccessTime",         column = "last_access_time"),
            @Result(property = "lastPasswordUpdateTime", column = "last_password_update_time")
          })
    @Insert("INSERT INTO usuario(mail, pass, created, active, wrong_access_count, blocked_time, request_key, request_type, request_time, request_completed, last_access_time, last_password_update_time) VALUES(#{mail}, #{pass}, #{created}, #{active}, #{wrongAccessCount}, #{blockedTime}, #{requestKey}, #{requestType}, #{requestTime}, #{requestCompleted}, #{lastAccessTime}, #{lastPasswordUpdateTime} )")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn = "id")
    int insert(Usuario usr) throws SQLException;
    
    /**
     * Actualiza un objeto de tipo 'usuario' con base en la infrmación dada por el objeto de tipo 'usuario'.
     *
     * @param Usuario a ser actualizado.
     *
     * @return el numero de registros actualizados.
     * 
     * @throws SQLException Se dispara en caso de que se dispare un error en esta operación desde la base de datos.
     */
    @Results(value = {
            @Result(property = "wrongAccessCount", column = "wrong_access_count"),
            @Result(property = "blockedTime",      column = "blocked_time"),
            @Result(property = "requestKey",       column = "request_key"),
            @Result(property = "requestTime",      column = "request_time"),
            @Result(property = "requestType",      column = "request_type"),
            @Result(property = "requestCompleted", column = "request_completed"),
            @Result(property = "lastAccessTime",         column = "last_access_time"),
            @Result(property = "lastPasswordUpdateTime", column = "last_password_update_time")
          })
    @Update("UPDATE usuario SET mail = #{mail}, pass = #{pass}, created = #{created}, active = #{active}, wrong_access_count = #{wrongAccessCount}, blocked_time = #{blockedTime}, request_key = #{requestKey}, request_type = #{requestType}, request_time = #{requestTime}, request_completed = #{requestCompleted}, last_access_time = #{lastAccessTime}, last_password_update_time = #{lastPasswordUpdateTime} WHERE id = #{id} ")
    int update(Usuario usr) throws SQLException;


    /**
     * Retorna una lista de objetos de tipo 'Rol' asociada a un ususario representado por su 'id'
     * 
     * @param userId long que representa el 'id' de un usuario. Mapea con la llave primaria en la entidad 'Usuario'
     * 
     * @return Lista de objetos 'Rol' asociados al usuario cuyo 'id' fue dado
     */
    @Select("select id, name, description, active from rol where id in (select usuario_rol.id_rol from usuario_rol where id_usuario=#{userId})")
    List<Rol> getRolesFromUserId(long userId);

    /**
     * Retorna un objeto de tipo 'UsuarioDetalles' al que corresponde un cierto idUsuario
     * como llave primeria de la tabla.
     *  
     * @param idUsuario long Que representa la llave primaria de la tabla
     * 
     * @return objeto de tipo 'UsuarioDetalles'
     * @throws SQLException
     */
    @Results(value = {
            @Result(property = "idUsuario",       column = "id_usuario"),
            @Result(property = "fechaNacimiento", column = "fecha_nacimiento")
          })
    @Select("select id_usuario, nombre, telefono, direccion, fecha_nacimiento from usuario_detalles where id_usuario=#{idUsuario}")
    UsuarioDetalles getUsuarioDetalles(long idUsuario) throws SQLException;
    
    
    
    @Results(value = {
            @Result(property = "wrongAccessCount", column = "wrong_access_count"),
            @Result(property = "blockedTime",      column = "blocked_time"),
            @Result(property = "requestKey",       column = "request_key"),
            @Result(property = "requestTime",      column = "request_time"),
            @Result(property = "requestType",      column = "request_type"),
            @Result(property = "requestCompleted", column = "request_completed"),
            @Result(property = "lastAccessTime",         column = "last_access_time"),
            @Result(property = "lastPasswordUpdateTime", column = "last_password_update_time")
          })
    @Select("SELECT id, mail, pass, created, active, wrong_access_count, blocked_time, request_key, request_type, request_time, request_completed, last_access_time, last_password_update_time FROM usuario WHERE request_key = #{code} ") 
    Usuario getByCode(String code) throws SQLException;

    @Results(value = {
            @Result(property = "fechaNacimiento", column = "fecha_nacimiento", javaType = java.util.Date.class)
          })    
    @Select("SELECT id, nombre, mail, telefono, direccion, fecha_nacimiento from vw_usuario_all")
    List<VwUsuarioAll> getUsuarioTable() throws SQLException;
}
