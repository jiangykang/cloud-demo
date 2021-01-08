import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer // 声明这个应用是一个EurekaServer
public class EurekaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaDemoApplication.class, args);
    }
}