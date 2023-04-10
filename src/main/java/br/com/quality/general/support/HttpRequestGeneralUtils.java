package br.com.quality.general.support;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;

public class HttpRequestGeneralUtils {

    public static String doPost(String url, HashMap<String, String> headers, String payload) {

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);

            headers.forEach((key, value) -> {
                request.addHeader(key, value);
            });

            StringEntity dataPayload = new StringEntity(payload);

            request.setEntity(dataPayload);
            HttpResponse response = client.execute(request);

            String entityUtils = EntityUtils.toString(response.getEntity());

            return entityUtils;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
