package org.example.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class UFLIFOError {
    @JsonGetter("Code")
    public String getCode(){
        return this.Code;
    }

    @JsonGetter("Description")
    public String getDescription(){
        return this.Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCode(String code) {
        Code = code;
    }

    String Code;
    String Description;
}
