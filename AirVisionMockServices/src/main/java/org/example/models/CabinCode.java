package org.example.models;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CabinCode {

    @JsonGetter("Travelers")
    public List<Traveler> getTravelerList() {
        return travelerList;
    }

    public void setTravelerList(List<Traveler> travelerList) {
        this.travelerList = travelerList;
    }
    @JsonGetter("CabinCode")
    public String getCabinCode() {
        return CabinCode;
    }

    public void setCabinCode(String cabinCode) {
        CabinCode = cabinCode;
    }

    String CabinCode;
    List<Traveler> travelerList;
}
