package mx.qbits.plank.api.service;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mx.qbits.plank.api.exceptions.BusinessException;
import mx.qbits.plank.api.model.GoogleCaptcha;

@Service("invokeRestService")
public class InvokeRestServiceImpl implements InvokeRestService {

    public static void main(String...argv) throws BusinessException {
        InvokeRestServiceImpl inv = new InvokeRestServiceImpl();
        String resp = "03AGdBq24Fly1z4KBPLPZ0LC7P5XdFh1oYkBowQbnlExxHAB35FAdzAqOIyuww9whh3hOIa8xHiDNRe9i-Z7H2gFokIuEJapK4CPIHC4H-SMDymyN6fj3YqDJNZ66g5X7gqpLM1EUTQUy3n3GZ4IszumgJGFEAXvZueqDRxt05jsBYClAmsT_WP2HF7nlYDlNcnPTC88mVtJFijmRfOHM2TyQ9S8mouKkU6dBCQN6V_LOx5mr7Dlls-q1gJ5V1r8_L30bAecNir428W6RL8CFvWrVKgqlw_lPj1hEJjFtfguQ1IzxcumExyMY4MQyBI0VhUxnz2Ow4PuQMzL3ujcF6K_yejbXThKkNTt9hp6rzMIAgONzg7XxHOEushIc0A_zJJ8hwHDjMMaX_4X1KUFm7enSBr9QWB0l6gBZU8gq3n7tkGr6NvnYQPKj5Zy2XjK2AgTQIUZ0OONxQ";
        GoogleCaptcha gc = new GoogleCaptcha(resp);
        String result = inv.checkCaptcha(gc);
        System.out.println(result);
    }
    
    @Override
    public String checkCaptcha(GoogleCaptcha googleCaptcha) throws BusinessException {
        try {
            String result = genericChecker(
                    GoogleCaptcha.GOOGLE_RECAPTCHA_VERIFY_URL,
                    GoogleCaptcha.GOOGLE_RECAPTCHA_SECRET_CODE,
                    googleCaptcha.getResponse());
            return result;
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }
    }
    private String genericChecker(String url, String secret, String response) throws IOException {
        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append(url);
        redirectUrl.append("?secret=");
        redirectUrl.append(secret);
        redirectUrl.append("&response=");
        redirectUrl.append(response);
        redirectUrl.append("&remoteip=127.0.0.1");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        String body = ""; // No recibe nada en su body... lo manda como parámetros en el url
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(redirectUrl.toString(), entity, String.class);
    }
}
