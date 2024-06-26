package org.united.airvision.models.uflifo;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirModel {
    @JsonGetter("Key")
    public String getKey(){
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String key;
}
