/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank
 * Paquete:     mx.qbits.plank.api.mapper
 * Modulo:      Registro
 * Tipo:        interface 
 * Autor:       Gustavo A. Arellano
 * Fecha:       miércoles 08 de agosto de 2020 (00_58)
 * Version:     0.1.1-SNAPSHOT
 * .
 * Interface 'Mapper' MyBatis asociado a la entidad Registro 
 *
 * Historia:    .
 *              20200812_0058 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */

package mx.qbits.plank.api.mapper;

import java.sql.SQLException;
import org.apache.ibatis.annotations.*;
import mx.qbits.plank.api.model.Registro;
import mx.qbits.plank.api.model.UsuarioDetalles;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad Registro 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
public interface RegistroMapper {

//    @Results(value = {
//            @Result(property = "fechaNacimiento",  column = "fecha_nacimiento"),
//            @Result(property = "claveHash",        column = "clave_hash"),
//            @Result(property = "fechaRegistro",    column = "fecha_registro"),
//            @Result(property = "randomString",     column = "random_string")
//          })
//    @Select("SELECT nombre, telefono, direccion, fecha_nacimiento, clave_hash, fecha_registro, random_string FROM registro WHERE correo = #{correo} ") 
//    Registro getById(Registro registro) throws SQLException;

//    @Results(value = {
//            @Result(property = "fechaNacimiento",  column = "fecha_nacimiento"),
//            @Result(property = "claveHash",        column = "clave_hash"),
//            @Result(property = "fechaRegistro",    column = "fecha_registro"),
//            @Result(property = "randomString",     column = "random_string")
//          })
//    @Select("SELECT nombre, telefono, direccion, fecha_nacimiento, correo, clave_hash, fecha_registro, random_string FROM registro ") 
//    List<Registro> getAll() throws SQLException;
    
    @Results(value = {
            @Result(property = "fechaNacimiento",  column = "fecha_nacimiento"),
            @Result(property = "claveHash",        column = "clave_hash"),
            @Result(property = "fechaRegistro",    column = "fecha_registro"),
            @Result(property = "randomString",     column = "random_string")
          })
    @Select("SELECT nombre, telefono, direccion, fecha_nacimiento, correo, clave_hash, fecha_registro, random_string FROM registro WHERE correo = #{correo} ") 
    Registro getByMail(String correo) throws SQLException;

    @Results(value = {
            @Result(property = "fechaNacimiento",  column = "fecha_nacimiento"),
            @Result(property = "claveHash",        column = "clave_hash"),
            @Result(property = "fechaRegistro",    column = "fecha_registro"),
            @Result(property = "randomString",     column = "random_string")
          })
    @Select("SELECT nombre, telefono, direccion, fecha_nacimiento, correo, clave_hash, fecha_registro, random_string FROM registro WHERE random_string = #{randomString} ") 
    Registro getByRandomString(String randomString) throws SQLException;

    
    
    
    
    @Insert("INSERT into usuario_rol(id_usuario, id_rol) values(#{idUsuario}, #{idRol})")
    int asociateRol(int idUsuario, int idRol) throws SQLException;
    
    @Insert("INSERT INTO usuario_detalles(id_usuario, nombre, telefono, direccion, fecha_nacimiento) VALUES(#{idUsuario}, #{nombre}, #{telefono}, #{direccion}, #{fechaNacimiento} )")
    int insertUsuarioDetalles(UsuarioDetalles usuarioDetalles) throws SQLException;

    @Insert("INSERT INTO registro(nombre, telefono, direccion, fecha_nacimiento, correo, clave_hash, fecha_registro, random_string) VALUES(#{nombre}, #{telefono}, #{direccion}, #{fechaNacimiento}, #{correo}, #{claveHash}, #{fechaRegistro}, #{randomString} )")
    int insertRegistro(Registro registro) throws SQLException;

    @Update("UPDATE registro SET nombre = #{nombre}, telefono = #{telefono}, direccion = #{direccion}, fecha_nacimiento = #{fechaNacimiento}, clave_hash = #{claveHash}, fecha_registro = #{fechaRegistro}, random_string = #{randomString} WHERE correo = #{correo} ")
    int update(Registro registro) throws SQLException;

    @Select("DELETE FROM registro WHERE random_string = #{randomString} ") 
    void deleteByRandomString(String randomString) throws SQLException;

}
