package org.example.models;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationalFlightSegment {

    @JsonGetter("FlightStatuses")
    public List<FlightStatus> getFlightStatusList(){
        return this.flightStatusList;
    }

    @JsonGetter("OperatingAirline")
    public OperatingAirline getOperatingAirline(){
        return this.operatingAirline;
    }

    @JsonGetter("Equipment")
    public Equipment getEquipment(){
        return this.equipment;
    }

    @JsonGetter("EstimatedDepartureTime")
    public String getEstimatedDepartureTime(){
        return this.EstimatedDepartureTime;
    }

    @JsonGetter("DepartureGate")
    public String getDepartureGate(){
        return this.DepartureGate;
    }

    public void setOperatingAirline(OperatingAirline operatingAirline) {
        this.operatingAirline = operatingAirline;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void setEstimatedDepartureTime(String estimatedDepartureTime) {
        EstimatedDepartureTime = estimatedDepartureTime;
    }

    public void setDepartureGate(String departureGate) {
        DepartureGate = departureGate;
    }

    public void setFlightStatusList(List<FlightStatus> flightStatusList) {
        this.flightStatusList = flightStatusList;
    }
    @JsonGetter("EstimatedArrivalDelayMinutes")
    public String getEstimatedArrivalDelayMinutes() {
        return estimatedArrivalDelayMinutes;
    }

    public void setEstimatedArrivalDelayMinutes(String estimatedArrivalDelayMinutes) {
        this.estimatedArrivalDelayMinutes = estimatedArrivalDelayMinutes;
    }

    @JsonGetter("EstimatedDepartureDelayMinutes")
    public String getEstimatedDepartureDelayMinutes() {
        return estimatedDepartureDelayMinutes;
    }

    public void setEstimatedDepartureDelayMinutes(String estimatedDepartureDelayMinutes) {
        this.estimatedDepartureDelayMinutes = estimatedDepartureDelayMinutes;
    }
    OperatingAirline operatingAirline;
    Equipment equipment;
    String EstimatedDepartureTime;
    String DepartureGate;
    String estimatedArrivalDelayMinutes;
    String estimatedDepartureDelayMinutes;
    List<FlightStatus> flightStatusList;
}
