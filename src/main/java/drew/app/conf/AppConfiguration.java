package drew.app.conf;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix="app")
public class AppConfiguration {

    @NotEmpty
    private String name;

    @NotNull
    @Valid
    private Accumulo accumulo = new Accumulo();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Accumulo getAccumulo() {
        return accumulo;
    }

    public void setAccumulo(Accumulo accumulo) {
        this.accumulo = accumulo;
    }

    public static class Accumulo {
        @NotEmpty
        String zookeepers;
        @NotEmpty
        String instance;
        @NotEmpty
        String username;
        @NotEmpty
        String password;

        public String getZookeepers() {
            return zookeepers;
        }

        public void setZookeepers(String zookeepers) {
            this.zookeepers = zookeepers;
        }

        public String getInstance() {
            return instance;
        }

        public void setInstance(String instance) {
            this.instance = instance;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
