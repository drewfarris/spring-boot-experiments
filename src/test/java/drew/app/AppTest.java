package drew.app;

import drew.app.conf.AppConfiguration;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = AppTest.TestConfig.class)
public class AppTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/", String.class);
        System.err.println(body);
        assertThat(body).contains("drew");
        assertThat(body).contains("accumulo instance: miniInstance");
    }

    @EnableAutoConfiguration
    @ComponentScan
    public static class TestConfig {

        @Autowired
        public void injectMiniAccumuloConfig(AppConfiguration conf, MiniAccumuloBean miniAccumuloBean) {
            AppConfiguration.Accumulo accumulo = conf.getAccumulo();
            accumulo.setInstance(miniAccumuloBean.getInstance());
            accumulo.setZookeepers(miniAccumuloBean.getZookeepers());
            accumulo.setUsername("root");
            accumulo.setPassword(miniAccumuloBean.getPassword());
        }

        @Bean
        public MiniAccumuloBean getMiniAccumuloBean() {
            final File tempDir = new File("target/mini-cluster");
            tempDir.mkdirs();
            FileUtils.deleteQuietly(tempDir);
            tempDir.mkdir();

            return new MiniAccumuloBean(tempDir, "secret");
        }
    }
}
