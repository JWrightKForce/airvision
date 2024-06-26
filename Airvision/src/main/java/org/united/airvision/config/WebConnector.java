package org.united.airvision.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class WebConnector<T,R> {
    CustomJsonBodyHandler<R> bodyConvertor;
    HttpRequest customReq;
    public void setBodyConvertor(CustomJsonBodyHandler<R> customConverter){
        this.bodyConvertor=customConverter;
    }
    public void setcustomReq(String method, String[] headers, T body, String uri){
        try {
            if(method.equalsIgnoreCase("GET")) {
                customReq = HttpRequest.newBuilder().uri(new URI(uri))
                        .method(method, HttpRequest.BodyPublishers.noBody())
                        .headers(headers)
                        .build();
            }else{
                ObjectMapper jsonParser=new ObjectMapper();
                customReq = HttpRequest.newBuilder().uri(new URI(uri))
                        .method(method, HttpRequest.BodyPublishers.ofString(jsonParser.writeValueAsString(body)))
                        .headers(headers)
                        .build();
            }
        }catch(Exception e){ }
    }


    public R callout() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        try {
            R response = httpClient.sendAsync(customReq, bodyConvertor).get(10000, TimeUnit.MILLISECONDS).body();
            return response;
        } catch (Exception e) {
            return null;
        }
    }
}
