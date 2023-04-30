package ci.nocod3r.config;

import ci.nocod3r.Wizard;
import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        String response = gson.toJson(model);
        return response;
    }
}
