package org.united.airvision.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.nio.charset.Charset;

//being used as an instance. Rather than a bean. Need to replace.

@Component
public class CustomJsonBodyHandler<T> implements HttpResponse.BodyHandler<T>{

    Class tClass;

    public void settClass(Class<T> pClass){
        this.tClass=pClass;
    }

    public <T> HttpResponse.BodySubscriber<T> asJson(Class<T> refClass){

        HttpResponse.BodySubscriber<String> stringBodySubscriber=HttpResponse.BodySubscribers.ofString(Charset.defaultCharset());
        HttpResponse.BodySubscriber<T> tBodySubscriber=HttpResponse.BodySubscribers.mapping(stringBodySubscriber, (String body) -> {

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                System.out.println("JsonParser initialized successfully");
                return  objectMapper.readValue(body,refClass);
            }catch(Exception e){
                return null;
            }
        });
        return tBodySubscriber;
    }
    @Override
    public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {

        return asJson(this.tClass);

     }
}
