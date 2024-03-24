package ua.kolesnyk.automation.hw8;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Task1 {


    @Test
    public void keyCheck() throws IOException {
        String url = "https://api.github.com/emojis";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet getAllKeys = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(getAllKeys);

        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);
        boolean keyCheck = json.contains("articulated_lorry");

        Assert.assertTrue(keyCheck, "Defined key - 'articulated_lorry' is absent");
    }
}
