package org.united.airvision.models.sabre;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Traveler {
    @JsonGetter("SurName")
    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }
    @JsonGetter("GivenName")
    public String getGivenName() {
        return GivenName;
    }

    public void setGivenName(String givenName) {
        GivenName = givenName;
    }
    @JsonGetter("SelectedMeals")
    public List<SelectedMeals> getSelectedMealsList() {
        return selectedMealsList;
    }

    public void setSelectedMealsList(List<SelectedMeals> selectedMealsList) {
        this.selectedMealsList = selectedMealsList;
    }

    String SurName;
    String GivenName;

    List<SelectedMeals> selectedMealsList;
}
