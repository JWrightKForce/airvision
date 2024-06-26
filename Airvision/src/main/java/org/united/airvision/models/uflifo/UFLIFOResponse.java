package org.united.airvision.models.uflifo;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class UFLIFOResponse {
    @JsonGetter("FlightLegs")
    public List<FlightLeg> getFlightLegList(){
        return this.flightLegList;
    }

    @JsonGetter("Error")
    public List<UFLIFOError> getError(){
        return this.error;
    }


    public void setFlightLegList(List<FlightLeg> flightLegList) {
        this.flightLegList = flightLegList;
    }

    public void setError(List<UFLIFOError> error) {
        this.error = error;
    }

    List<FlightLeg> flightLegList;

    List<UFLIFOError> error;

}
