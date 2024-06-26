package org.example.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightLeg {
    @JsonGetter("OperationalFlightSegments")
    public List<OperationalFlightSegment> getOperationalFlightSegment(){
      return   this.operationalFlightSegment;
    }


    public void setOperationalFlightSegment(List<OperationalFlightSegment> operationalFlightSegment) {
        this.operationalFlightSegment = operationalFlightSegment;
    }

    List<OperationalFlightSegment> operationalFlightSegment;

}
