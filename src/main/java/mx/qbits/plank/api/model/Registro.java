/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank
 * Paquete:     mx.qbits.plank.api.model
 * Modulo:      Registro
 * Tipo:        clase 
 * Autor:       Gustavo A. Arellano
 * Fecha:       miércoles 08 de agosto de 2020 (00_58)
 * Version:     0.1.1-SNAPSHOT
 * .
 * POJO asociado a la entidad 'registro'. 
 *
 * Historia:    .
 *              20200812_0058 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */

package mx.qbits.plank.api.model;

import java.io.Serializable;
import java.util.Objects;

import mx.qbits.plank.util.DigestEncoder;

import java.util.Date;
/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad 'registro'. 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
public class Registro implements Serializable {

    private static final long serialVersionUID = 7205872265753757625L;

    private String nombre;
    private String telefono;
    private String direccion;
    private Date fechaNacimiento;
    private String correo;
    private String claveHash;
    private long fechaRegistro;
    private String randomString;

    /**
     * Constructor por default.
     */
    public Registro() {
    }

    public Registro(RegistroRequest registroRequest, String randomString) {
        this.randomString  = randomString;
        this.correo        = registroRequest.getCorreo();
        this.direccion     = registroRequest.getDireccion();
        this.fechaNacimiento = registroRequest.getFechaNacimiento();
        this.nombre        = registroRequest.getNombre();
        this.telefono      = registroRequest.getTelefono();
        this.claveHash     = DigestEncoder.digest(
                registroRequest.getClave(), 
                registroRequest.getCorreo() );
        this.fechaRegistro = System.currentTimeMillis();
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
    
    /**
     * Getter para correo.
     */
    public String getCorreo() {
        return correo;
    }
    
    /**
     * Setter para correo.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Getter para claveHash.
     */
    public String getClaveHash() {
        return claveHash;
    }
    
    /**
     * Setter para claveHash.
     */
    public void setClaveHash(String claveHash) {
        this.claveHash = claveHash;
    }
    
    /**
     * Getter para fechaRegistro.
     */
    public long getFechaRegistro() {
        return fechaRegistro;
    }
    
    /**
     * Setter para fechaRegistro.
     */
    public void setFechaRegistro(long fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    /**
     * Getter para randomString
     */
    public String getRandomString() {
        return randomString;
    }

    /**
     * Setter para randomString
     */
    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }
    
    @Override
    public String toString() {
        return "[Registro] : ["
                + " correo =" + this.correo
                + " nombre =" + this.nombre
                + " telefono =" + this.telefono
                + " direccion =" + this.direccion
                + " fechaNacimiento =" + this.fechaNacimiento
                + " claveHash =" + this.claveHash
                + " fechaRegistro =" + this.fechaRegistro
                + " randomString =" + this.randomString
                + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) obj;
        return
               nombre.equals(other.nombre) && 
               telefono.equals(other.telefono) && 
               direccion.equals(other.direccion) && 
               correo.equals(other.correo) && 
               claveHash.equals(other.claveHash) && 
               fechaRegistro == other.fechaRegistro && 
               randomString.equals(other.randomString); 
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            nombre, 
            telefono, 
            direccion, 
            fechaNacimiento, 
            correo, 
            claveHash, 
            fechaRegistro, 
            randomString
        );
    }

}
