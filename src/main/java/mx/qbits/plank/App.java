package mx.qbits.plank;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("mx.qbits.plank.api.mapper")
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        logger.info("Iniciando contexto de Spring, by Goose");
        SpringApplication.run(App.class, args);
    }
}
