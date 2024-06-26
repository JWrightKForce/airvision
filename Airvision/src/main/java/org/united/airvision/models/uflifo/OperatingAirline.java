package org.united.airvision.models.uflifo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperatingAirline {

    @JsonGetter("IATACode")
    public String getIATACode(){
        return this.IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }

    String IATACode;
}
