package ci.nocod3r.services;


import ci.nocod3r.Main;
import ci.nocod3r.Wizard;
import ci.nocod3r.config.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    public Response<Wizard> restart(Object resquest, Object response) throws IOException {
        String command = "src/main/resources/restartApp.sh";
        Runtime.getRuntime().exec(command);
        Response<Wizard> wizardResponse = new Response<>();
        Wizard wizard = new Wizard();
        wizard.setName("restart");
        wizardResponse.setHasError(false);
        wizardResponse.setItem(wizard);
        return wizardResponse;
    }
}
