/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank
 * Paquete:     mx.qbits.plank.api.model
 * Modulo:      UsuarioDetalles
 * Tipo:        clase 
 * Autor:       Gustavo A. Arellano
 * Fecha:       martes 08 de agosto de 2020 (19_08)
 * Version:     0.1.1-SNAPSHOT
 * .
 * POJO asociado a la entidad 'usuario_detalles'. 
 *
 * Historia:    .
 *              20200811_1908 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */

package mx.qbits.plank.api.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;
/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad 'usuario_detalles'. 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
public class UsuarioDetalles implements Serializable {

    private static final long serialVersionUID = 2134469886702307784L;

    private Integer idUsuario;
    private String nombre;
    private String telefono;
    private String direccion;
    private Date fechaNacimiento;

    /**
     * Constructor por default.
     */
    public UsuarioDetalles() {
    }

    /**
     * Constructor basado en llaves.
     */
    public UsuarioDetalles(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Constructor basado en atributos.
     */
    public UsuarioDetalles(Integer idUsuario, String nombre, String telefono, String direccion, Date fechaNacimiento) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Getter para idUsuario.
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }
    
    /**
     * Setter para idUsuario.
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * Getter para nombre.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Setter para nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Getter para telefono.
     */
    public String getTelefono() {
        return telefono;
    }
    
    /**
     * Setter para telefono.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    /**
     * Getter para direccion.
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Setter para direccion.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Getter para fechaNacimiento.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    /**
     * Setter para fechaNacimiento.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    @Override
    public String toString() {
        return "[UsuarioDetalles] : ["
                + " idUsuario =" + this.idUsuario
                + " nombre =" + this.nombre
                + " telefono =" + this.telefono
                + " direccion =" + this.direccion
                + " fechaNacimiento =" + this.fechaNacimiento
                + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UsuarioDetalles)) {
            return false;
        }
        UsuarioDetalles other = (UsuarioDetalles) obj;
        return
               idUsuario == other.idUsuario && 
               nombre == other.nombre && 
               telefono == other.telefono && 
               direccion == other.direccion && 
               fechaNacimiento == other.fechaNacimiento; 
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            idUsuario, 
            nombre, 
            telefono, 
            direccion, 
            fechaNacimiento
        );
    }

}
