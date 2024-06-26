package org.example.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SelectedMeals {
    String ServiceSeqNumber;
    String MealCode;

    @JsonGetter("MealCode")
    public String getMealCode() {
        return MealCode;
    }

    public void setMealCode(String mealCode) {
        MealCode = mealCode;
    }
    @JsonGetter("ServiceSeqNumber")
    public String getServiceSeqNumber() {
        return ServiceSeqNumber;
    }

    public void setServiceSeqNumber(String serviceSeqNumber) {
        ServiceSeqNumber = serviceSeqNumber;
    }

}
