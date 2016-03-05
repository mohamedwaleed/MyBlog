package my.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KLSX4388 on 9/7/2014.
 */
@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper dozerBeanMapper(){
        DozerBeanMapper dozerBeanMapper= new DozerBeanMapper();
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozerBeanMapping.xml");
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }
}
