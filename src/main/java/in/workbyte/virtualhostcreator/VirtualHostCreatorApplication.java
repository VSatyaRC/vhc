package in.workbyte.virtualhostcreator;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class VirtualHostCreatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualHostCreatorApplication.class, args);
    }

    @Bean
    public Configuration ftlConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_19);
        try {
            cfg.setDirectoryForTemplateLoading(new File(getClass().getClassLoader().getResource("templates").getFile()));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            return cfg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cfg;
    }
}