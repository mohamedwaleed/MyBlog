package my.config;

import my.ServerTest.TestController;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.SortedMap;

/**
 * Created by mohamed on 30/08/15.
 */
@Configuration
@ComponentScan(basePackages = "my")
@Import(value = {WebConfig.class,SecurityConfig.class,PersistenceConfig.class,FlywayConfig.class,PropertyConfig.class,BeanConfig.class,DatabaseConfig.class,DozerConfig.class})
public class MainConfig  {


}
