package in.workbyte.virtualhostcreator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VHCInit implements CommandLineRunner {

    @Value("${vhc.template}")
    String templatePath;

    @Value("${vhc.script}")
    String scriptPath;

    @Value("${vhc.workspace}")
    String vhWorkspace;

    Logger logger = Logger.getLogger("VHCInit");


    public void createWorkingDirectory() {
        File vhcWorkspace = new File(vhWorkspace);
        if (!vhcWorkspace.exists()) {
            logger.log(Level.INFO, "Creating vhc workspace.");
            String isCreated = Boolean.toString(vhcWorkspace.mkdir());
            logger.log(Level.INFO, isCreated);
        } else {
            logger.log(Level.INFO, "Directory vhWorkspace exists.");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        createWorkingDirectory();
    }
}
