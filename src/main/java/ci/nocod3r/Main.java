package ci.nocod3r;
import ci.nocod3r.config.JsonTransformer;
import ci.nocod3r.config.Properties;
import ci.nocod3r.config.Response;
import ci.nocod3r.controllers.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static spark.Spark.*;
import com.google.gson.Gson;

import java.util.concurrent.locks.ReentrantLock;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private Gson gson = new Gson();
    private static MainController mainController;
    private static ReentrantLock lock;

    private static String error;


    private static void init(){
        lock = new ReentrantLock();
        lock.lock();
        try{
            if (mainController == null)
                mainController = new MainController();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        init();
        //init app
        Properties.Builder().setPort(3000).minMaxThread(1).setMaxThread(1).build();
        get("restart", "application/json", (request, response) -> {
            error = String.format("endpoint : %s", request.uri());
            logger.info(error);
            // validation du format de la requete ici
            return mainController.restart(request, response);
        }, new JsonTransformer());

        internalServerError((req, res) -> {
            res.type("application/json");
            error = String.format("Error:::: %s: %s %s %s", res.status(), res.body(), res.type(), res.raw());
            logger.error(error);
            return "{\"message\":\"Custom 500 handling\"}";
        });
    }
}