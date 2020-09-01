/*
 * Licencia:    Usted puede utilizar libremente este código
 *              para copiarlo, distribuirlo o modificarlo total
 *              o parcialmente siempre y cuando mantenga este
 *              aviso y reconozca la autoría del código al no
 *              modificar los datos establecidos en la mencion de "AUTOR".
 *
 * Proyecto:    plank-back
 * Paquete:     mx.qbits.plank.api.service
 * Modulo:      Usuario
 * Tipo:        clase 
 * Autor:       Gustavo A. Arellano
 * Fecha:       jueves 07 de julio de 2020 (18_08)
 * Version:     0.1.1-SNAPSHOT
 * .
 * Servicio asociado a la entidad 'Usuario'. 
 *
 * Historia:    .
 *              20200723_1808 Generado por arq.gen, basado en los
 *              archivos fuente de Gustavo Arellano
 *
 */
package mx.qbits.plank.api.service;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.mapper.UsuarioMapper;
import mx.qbits.plank.api.model.UserPagination;
import mx.qbits.plank.api.model.Usuario;

/**
 * <p>Descripción:</p>
 * Servicio asociado a la entidad 'Usuario'. 
 *
 * @author Gustavo A. Arellano
 * @version 0.1.1-SNAPSHOT
 */
@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario getById(long id) throws BusinessException {
        if(id>999) {
            BusinessException be = new BusinessException();
            be.setCveExcepcion("CXG76G6");
            be.setIdentificador(1234);
            be.setDescExcepcion("Esta es la descripicion");
            be.setHttpError(HttpStatus.INTERNAL_SERVER_ERROR);
            throw be;
        }
        try {
            return usuarioMapper.getById(id);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new BusinessException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Usuario> getAll() throws BusinessException {
        try {
            return usuarioMapper.getAllAscending("id");
        } catch (SQLException e) {
            throw new BusinessException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int insert(Usuario usr) throws BusinessException {
        try {
            return usuarioMapper.insert(usr);
        } catch (SQLException e) {
            throw new BusinessException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int update(Usuario usr) throws BusinessException {
        try {
            return usuarioMapper.update(usr);
        } catch (SQLException e) {
            throw new BusinessException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int save(Usuario usr) throws BusinessException {
        try {
            if (getById(usr.getId()) == null) {
                return usuarioMapper.insert(usr);
            } else {
                return usuarioMapper.update(usr);
            }
        } catch (SQLException e) {
            throw new BusinessException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    public UserPagination paginate(List<Usuario> originalArray, Integer pageNumber, Integer pageSize) {
        UserPagination emptyResponse = new UserPagination(0,new ArrayList<>());
        if(pageSize<1 || pageNumber<1) return emptyResponse;
        int a = pageSize * pageNumber - pageSize +1;
        int b = pageSize * pageNumber;
        int len = originalArray.size();
        if(a>len) return emptyResponse;
        if(b>len) b = len;
        int newLen = b-a+1;
        List<Usuario> result = new ArrayList<>();
        for(int i = 0; i<newLen; i++) {
            result.add(originalArray.get(a+i-1));
        }
        return new UserPagination(len, result);
    }
    



    
}
