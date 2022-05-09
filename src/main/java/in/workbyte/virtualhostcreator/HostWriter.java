package in.workbyte.virtualhostcreator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HostWriter {

    @Autowired
    Configuration configuration;

    @Value("${vhc.workspace}")
    String vhWorkspace;

    private String createFile(String templateName, String outputFile, Map<String, String> input) {

        try {
            File folder = new File(vhWorkspace + input.get("domainName"));
            if (folder.mkdir()) System.out.println(folder.getName() + " folder created");
            Template template = configuration.getTemplate(templateName);
            Writer fileWriter = new FileWriter(vhWorkspace + folder.getName() + "/" + outputFile);
            template.process(input, fileWriter);
            fileWriter.close();
            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public Resource getVhFiles(Map<String, String> domainName) throws IOException {
        return Util.zipFiles(domainName);
    }

    public Map<String, List<String>> createVhFiles(Map<String, String> vhDetails) throws IOException {

        List<String> outputFiles = new ArrayList<>();
        outputFiles.add(createFile("index.ftl", "index.html", vhDetails));
        outputFiles.add(createFile("vh.ftl", vhDetails.get("domainName").toLowerCase() + ".conf", vhDetails));
        outputFiles.add(createFile("vh_script.ftl", "script.sh", vhDetails));
        outputFiles.add(createFile("index.ftl", "index.html", vhDetails));
        Map<String, List<String>> files = new HashMap<>();
        files.put("files", outputFiles);
        return files;
    }

}
