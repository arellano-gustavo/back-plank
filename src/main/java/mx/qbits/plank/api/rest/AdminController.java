package mx.qbits.plank.api.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.qbits.plank.api.service.HealthService;

@RestController
@Api(value = "admin")
@RequestMapping(value = "/api")
public class AdminController {
    private HealthService healthService;

    public AdminController(HealthService healthService) {
        this.healthService = healthService;
    }

    @ApiOperation(value = "AdminController::logout", notes = "Provoca un 'logout' del usuario firmado en el sistema")
    @GetMapping(path = "/logout.json", produces = "application/json; charset=utf-8")
    public String logout(HttpServletRequest request) throws ServletException {
        String name = "tavo";
        request.logout();
        String res = "{-" + name + "-:-you have been loged out-}";
        return res.replace('-', '"');
    }

    @ApiOperation(value = "AdminController::health", notes = "Entrega un informe a cerca de las variables del sistema")
    @GetMapping(path = "/health.json", produces = "application/json; charset=utf-8")
    public Map<String, String> health(@RequestParam String data) throws IOException {
        return healthService.getInfo(data);
    }

    @ApiOperation(value = "AdminController::health", notes = "Entrega el log del sistema")
    @GetMapping(path = "/log.json", produces = "application/json; charset=utf-8")
    public List<String> getLog(@RequestParam Integer last) {
        return healthService.getLog(last);
    }

    @GetMapping(path = "/qa-stats.json", produces = "application/json; charset=utf-8")
    public String getQualityStats(@RequestParam int page, @RequestParam int len) {
        final String uri = "https://sonar.ci.ultrasist.net/api/issues/search?ps=" + len + "&p="
                + page
                + "&componentKeys=mx.gob.impi.chatbot.persistence:chatbot-persistence-layer";
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(uri, String.class);
        } catch (RuntimeException e) {
            return "{'error':'" + e.getMessage() + "', 'uri':'" + uri + "'}".replace("'", "\"");
        }
    }

}
