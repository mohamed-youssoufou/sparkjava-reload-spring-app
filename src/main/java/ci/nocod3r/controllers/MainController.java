package ci.nocod3r.controllers;

import ci.nocod3r.Wizard;
import ci.nocod3r.config.Response;
import ci.nocod3r.services.MainService;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class MainController {

    private MainService service;

    private ReentrantLock lock;

    public MainController(){
        lock = new ReentrantLock();
        lock.lock();
        try{
            if(this.service == null)
                this.service = new MainService();
        }finally {
            lock.unlock();
        }
    }

    public Response<Wizard> restart(Object resquest, Object response) throws IOException {
        return this.service.restart(resquest, response);
    }
}
