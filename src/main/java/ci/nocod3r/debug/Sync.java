package ci.nocod3r.debug;

import ci.nocod3r.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sync {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static Object runner(String command) throws IOException {

        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        // read the output of the command
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            logger.info(line);
            if(line.endsWith("Initializing Spring FrameworkServlet 'dispatcherServlet'")){
                return 0;
            }
        }

        // wait for the process to finish
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Command finished with exit code " + process.exitValue());
        return 0;
    }
}
