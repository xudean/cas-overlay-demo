package koal.cas.app;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient
public class AppApplication2{

    public static void main(String[] args) {
        SpringApplication.run(AppApplication2.class, args);
    }

}
