package drew.app;


import drew.app.conf.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AppMain {

    @Autowired
    AppConfiguration config;

    @RequestMapping("/")
    String home() {
        return "Hello World, from " + config.getName() + " accumulo instance: " + config.getAccumulo().getInstance();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppMain.class, args);
    }
}
