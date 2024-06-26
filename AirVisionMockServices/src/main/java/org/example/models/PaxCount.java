package org.example.models;

import com.fasterxml.jackson.annotation.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaxCount {
    @JsonGetter("CABININDICATOR")
    public String getCABININDICATOR() {
        return CABININDICATOR;
    }

    public void setCABININDICATOR(String CABININDICATOR) {
        this.CABININDICATOR = CABININDICATOR;
    }
    @JsonGetter("BOOKEDPAXCOUNTY")
    public String getBOOKEDPAXCOUNTY() {
        return BOOKEDPAXCOUNTY;
    }

    public void setBOOKEDPAXCOUNTY(String BOOKEDPAXCOUNTY) {
        this.BOOKEDPAXCOUNTY = BOOKEDPAXCOUNTY;
    }
    @JsonGetter("BOOKEDPAXCOUNTC")
    public String getBOOKEDPAXCOUNTC() {
        return BOOKEDPAXCOUNTC;
    }

    public void setBOOKEDPAXCOUNTC(String BOOKEDPAXCOUNTC) {
        this.BOOKEDPAXCOUNTC = BOOKEDPAXCOUNTC;
    }
    @JsonGetter("BOOKEDPAXCOUNTF")
    public String getBOOKEDPAXCOUNTF() {
        return BOOKEDPAXCOUNTF;
    }

    public void setBOOKEDPAXCOUNTF(String BOOKEDPAXCOUNTF) {
        this.BOOKEDPAXCOUNTF = BOOKEDPAXCOUNTF;
    }
    @JsonGetter("NOSENUMBER")
    public String getNOSENUMBER() {
        return NOSENUMBER;
    }

    public void setNOSENUMBER(String NOSENUMBER) {
        this.NOSENUMBER = NOSENUMBER;
    }
    @JsonGetter("CHECKEDINPAXCOUNTY")
    public String getCHECKEDINPAXCOUNTY() {
        return CHECKEDINPAXCOUNTY;
    }

    public void setCHECKEDINPAXCOUNTY(String CHECKEDINPAXCOUNTY) {
        this.CHECKEDINPAXCOUNTY = CHECKEDINPAXCOUNTY;
    }
    @JsonGetter("PHYSICALSEATCOUNT")
    public String getPHYSICALSEATCOUNT() {
        return PHYSICALSEATCOUNT;
    }

    public void setPHYSICALSEATCOUNT(String PHYSICALSEATCOUNT) {
        this.PHYSICALSEATCOUNT = PHYSICALSEATCOUNT;
    }
    @JsonGetter("AUTHORIZEDTOBOARDCOUNT")
    public String getAUTHORIZEDTOBOARDCOUNT() {
        return AUTHORIZEDTOBOARDCOUNT;
    }

    public void setAUTHORIZEDTOBOARDCOUNT(String AUTHORIZEDTOBOARDCOUNT) {
        this.AUTHORIZEDTOBOARDCOUNT = AUTHORIZEDTOBOARDCOUNT;
    }
    @JsonGetter("DepartureGate")
    public String getDepartureGate() {
        return DepartureGate;
    }

    public void setDepartureGate(String departureGate) {
        DepartureGate = departureGate;
    }
    @JsonGetter("FlightStatus")
    public String getFlightStatus() {
        return FlightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        FlightStatus = flightStatus;
    }
    @JsonGetter("BOARDEDPAXCOUNTY")
    public String getBOARDEDPAXCOUNTY() {
        return BOARDEDPAXCOUNTY;
    }

    public void setBOARDEDPAXCOUNTY(String BOARDEDPAXCOUNTY) {
        this.BOARDEDPAXCOUNTY = BOARDEDPAXCOUNTY;
    }
    String NOSENUMBER;
    String CABININDICATOR;
    String BOOKEDPAXCOUNTY;
    String BOOKEDPAXCOUNTC;
    String CHECKEDINPAXCOUNTY;
    String BOOKEDPAXCOUNTF;
    String DepartureGate;
    String FlightStatus;
    String PHYSICALSEATCOUNT;
    String AUTHORIZEDTOBOARDCOUNT;
    String BOARDEDPAXCOUNTY;
 }
