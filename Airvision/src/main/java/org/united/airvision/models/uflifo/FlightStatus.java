package org.united.airvision.models.uflifo;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightStatus {

    @JsonGetter("StatusType")
    public String getStatusType(){
        return this.StatusType;
    }
    @JsonGetter("Code")
    public String getCode(){
        return this.Code;
    }

    public void setStatusType(String statusType) {
        StatusType = statusType;
    }

    public void setCode(String code) {
        Code = code;
    }

    String StatusType;
    String Code;
}