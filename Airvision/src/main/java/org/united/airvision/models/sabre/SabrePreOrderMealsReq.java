package org.united.airvision.models.sabre;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SabrePreOrderMealsReq {

    @JsonGetter("FlightNumber")
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    @JsonGetter("ArrivalAirport")
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
    @JsonGetter("DepartureAirport")
    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }
    @JsonGetter("FlightDate")
    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    @JsonGetter("IncludeNonMealOption")
    public Boolean getIncludeNonMealOption() {
        return includeNonMealOption;
    }
    public void setIncludeNonMealOption(Boolean includeNonMealOption) {
        this.includeNonMealOption = includeNonMealOption;
    }

    String flightNumber;
    String departureAirport;
    String arrivalAirport;
    String flightDate;
    Boolean includeNonMealOption;

}
