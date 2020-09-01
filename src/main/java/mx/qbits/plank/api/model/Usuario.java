/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank
 * Paquete:     mx.qbits.plank.api.model
 * Modulo:      Usuario
 * Tipo:        clase 
 * Autor:       Gustavo A. Arellano
 * Fecha:       jueves 07 de julio de 2020 (19_49)
 * Version:     0.1.1-SNAPSHOT
 * .
 * POJO asociado a la entidad 'usuario'. 
 *
 * Historia:    .
 *              20200723_1949 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */

package mx.qbits.plank.api.model;

import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad 'usuario'. 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
public class Usuario  {
    private int id;
    
    @Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Correo electrónico inválido")
    private String mail;

    @Size(min = 4, max = 13, message = "Longitud de password inadecuada. debe ser minimo {min} y máximo {max}")
    private String pass;
    
    private long created;
    private boolean active;
    private int wrongAccessCount;
    private long blockedTime;
    private String requestKey;
    private int requestType;
    private long requestTime;
    private boolean requestCompleted;
    private long lastAccessTime;
    private long lastPasswordUpdateTime;

    /**
     * Constructor por default.
     */
    public Usuario() {
    }

    /**
     * Constructor basado en llaves.
     */
    public Usuario(int id) {
        this.id = id;
    }

    /**
     * Constructor basado en atributos.
     */
    public Usuario(int id, String mail, String pass, long created, boolean active, int wrongAccessCount, long blockedTime, String requestKey, int requestType, long requestTime, boolean requestCompleted) {
        this.id = id;
        this.mail = mail;
        this.pass = pass;
        this.created = created;
        this.active = active;
        this.wrongAccessCount = wrongAccessCount;
        this.blockedTime = blockedTime;
        this.requestKey = requestKey;
        this.requestType = requestType;
        this.requestTime = requestTime;
        this.requestCompleted = requestCompleted;
    }

    /**
     * Getter para id.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Setter para id.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Getter para mail.
     */
    public String getMail() {
        return mail;
    }
    
    /**
     * Setter para mail.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    /**
     * Getter para pass.
     */
    public String getPass() {
        return pass;
    }
    
    /**
     * Setter para pass.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    /**
     * Getter para created.
     */
    public long getCreated() {
        return created;
    }
    
    /**
     * Setter para created.
     */
    public void setCreated(long created) {
        this.created = created;
    }
    
    /**
     * Getter para active.
     */
    public boolean getActive() {
        return active;
    }
    
    /**
     * Setter para active.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Getter para wrongAccessCount.
     */
    public int getWrongAccessCount() {
        return wrongAccessCount;
    }
    
    /**
     * Setter para wrongAccessCount.
     */
    public void setWrongAccessCount(int wrongAccessCount) {
        this.wrongAccessCount = wrongAccessCount;
    }
    
    /**
     * Getter para blockedTime.
     */
    public long getBlockedTime() {
        return blockedTime;
    }
    
    /**
     * Setter para blockedTime.
     */
    public void setBlockedTime(long blockedTime) {
        this.blockedTime = blockedTime;
    }
    
    /**
     * Getter para requestKey.
     */
    public String getRequestKey() {
        return requestKey;
    }
    
    /**
     * Setter para requestKey.
     */
    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }
    
    /**
     * Getter para requestType.
     */
    public int getRequestType() {
        return requestType;
    }
    
    /**
     * Setter para requestType.
     */
    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
    
    /**
     * Getter para requestTime.
     */
    public long getRequestTime() {
        return requestTime;
    }
    
    /**
     * Setter para requestTime.
     */
    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }
    
    /**
     * Getter para requestCompleted.
     */
    public boolean getRequestCompleted() {
        return requestCompleted;
    }
    
    /**
     * Setter para requestCompleted.
     */
    public void setRequestCompleted(boolean requestCompleted) {
        this.requestCompleted = requestCompleted;
    }
    
    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
 
    public long getLastPasswordUpdateTime() {
        return lastPasswordUpdateTime;
    }

    public void setLastPasswordUpdateTime(long lastPasswordUpdateTime) {
        this.lastPasswordUpdateTime = lastPasswordUpdateTime;
    }
 
    @Override
    public String toString() {
        return "[Usuario] : ["
                + " id =" + this.id
                + " mail =" + this.mail
                + " pass =" + this.pass
                + " created =" + this.created
                + " active =" + this.active
                + " wrongAccessCount =" + this.wrongAccessCount
                + " blockedTime =" + this.blockedTime
                + " requestKey =" + this.requestKey
                + " requestType =" + this.requestType
                + " requestTime =" + this.requestTime
                + " requestCompleted =" + this.requestCompleted
                + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            id, 
            mail, 
            pass, 
            created, 
            active, 
            wrongAccessCount, 
            blockedTime, 
            requestKey, 
            requestType, 
            requestTime, 
            requestCompleted
        );
    }

}
