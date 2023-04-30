package ci.nocod3r.config;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import static spark.Spark.*;

import ci.nocod3r.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Properties {
    private int port = 8080;
    private int maxThreadPool = 1;
    private int minThreadPool= 0;
    private int timeOutMillis = 3000;
    private static PropertiesBuilders builder;

    private static ReentrantLock lock;

    private static final Logger logger = LogManager.getLogger(Main.class);

    private Properties(){}

    public static PropertiesBuilders Builder(){
        lock = new ReentrantLock();
        lock.lock();
        try{
            if(builder == null)
                builder = new PropertiesBuilders();
        }finally {
            lock.unlock();
            return builder;
        }
    }

    public static class PropertiesBuilders{
        private Properties instance = new Properties();

        public PropertiesBuilders setPort(int port){
            instance.port = port;
            return this;
        }

        public PropertiesBuilders setMaxThread(int maxThreadPool){
            instance.maxThreadPool = maxThreadPool;
            return this;
        }

        public PropertiesBuilders setTimeOutMillis(int timeOutMillis){
            instance.timeOutMillis = timeOutMillis;
            return this;
        }

        public PropertiesBuilders minMaxThread(int minThreadPool){
            instance.minThreadPool = minThreadPool;
            return this;
        }
        public void build(){
            logger.info("::::: 🔥 app starting 🔥....");
            port(instance.port);
            //threadPool(instance.maxThreadPool, instance.minThreadPool, instance.timeOutMillis);
            logger.info("port :"+instance.port);
            logger.info("max thread allowed :"+ instance.maxThreadPool);
            logger.info("min thread allowed :"+ instance.minThreadPool);
            logger.info("time out allowed :"+ instance.timeOutMillis);
            initExceptionHandler((e) -> {
                logger.error("::::: begin crashed .... 😓");
                logger.error(e.getMessage());
                logger.error("::::: end crashed 😓");
            });
            logger.info("app started success 😁😁😁😁");
        }
    }
}
