package in.workbyte.virtualhostcreator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Service
public class HostWriter {

    @Autowired
    Configuration configuration;

    @Value("${vhc.workspace}")
    String vhWorkspace;

    private void createFile(String templateName, String outputFile, Map<String, String> input) {
        try {
            Template template = configuration.getTemplate(templateName);
            Writer fileWriter = new FileWriter(vhWorkspace + outputFile);
            template.process(input, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> createVhFiles(Map<String, String> vhDetails) {
        createFile("index.ftl", "index.html", vhDetails);
        createFile("vh.ftl", vhDetails.get("domainName").toLowerCase() + ".conf", vhDetails);
        createFile("vh_script.ftl", "script.sh", vhDetails);
        createFile("index.ftl", "index.html", vhDetails);
//        runScripts();
        return new HashMap<>();
    }

    public void runScripts() {
        try {
            Runtime.getRuntime().exec(new String[]{vhWorkspace, "chmod +x", "script.sh"});
            Runtime.getRuntime().exec(vhWorkspace + "./script.sh");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
