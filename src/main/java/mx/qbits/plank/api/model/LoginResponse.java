package mx.qbits.plank.api.model;

import java.util.List;

public class LoginResponse {
    private Usuario usuario;
    private List<Rol> roles;
    private String jwt;
    private UsuarioDetalles usuarioDetalles;
    
    public LoginResponse(Usuario usuario, List<Rol> roles, String jwt, UsuarioDetalles usuarioDetalles) {
        this.usuario = usuario;
        this.roles = roles;
        this.jwt = jwt;
        this.usuarioDetalles = usuarioDetalles;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public List<Rol> getRoles() {
        return roles;
    }
    public String getJwt() {
        return jwt;
    }
    public UsuarioDetalles getUsuarioDetalles() {
        return usuarioDetalles;
    }
}
