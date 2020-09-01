package mx.qbits.plank.api.service;

import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.model.GoogleCaptcha;

public interface InvokeRestService {
    String checkCaptcha(GoogleCaptcha googleCaptcha) throws BusinessException;
}
