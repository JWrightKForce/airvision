package org.united.airvision.models.uflifo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Equipment {

    @JsonGetter("Model")
    public AirModel getModel(){
        return this.model;
    }

    public void setModel(AirModel model) {
        this.model = model;
    }

    AirModel model;
}
