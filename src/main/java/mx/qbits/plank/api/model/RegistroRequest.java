package mx.qbits.plank.api.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class RegistroRequest {
    @Pattern(regexp="^[A-Za-z]+$")
    private String nombre;
    
    @Pattern(regexp="^[A-Z0-9._%+-]+$")
    private String clave;
    
    @Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Correo electrónico inválido")
    private String correo;
    
    @Pattern(regexp="^[0-9]+$")
    private String telefono;
    
    @Pattern(regexp="^[A-Z0-9._%+-]+$")
    private String direccion;
    
    private Date fechaNacimiento;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
}
