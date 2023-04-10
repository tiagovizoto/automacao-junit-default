package br.com.quality.robots.support;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(RobotParallelized.class)
public class RobotMobileParallel {
    protected static JSONObject config;

    @Parameter(value = 0)
    public int taskID;

    @Parameters
    public static Iterable<? extends Object> data() throws Exception {
        List<Integer> taskIDs = new ArrayList<Integer>();

        JSONParser parser = new JSONParser();
        config = (JSONObject) parser.parse(new FileReader("src/main/resources/jsonFile/env.devices.json"));
        int envs = ((JSONArray) config.get("environments")).size();

        for (int i = 0; i < envs; i++) {
            taskIDs.add(i);
        }

        return taskIDs;
    }

    protected void setEnvDevice(DesiredCapabilities capabilities){
        JSONArray envs = (JSONArray) config.get("environments");
        Map<String, String> envCapabilities = (Map<String, String>) envs.get(taskID);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }
    }
}
