package org.united.airvision.models;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialMealsResponse {
    String PAXSEATCABINCLASS;
    String SPECIALMEALCODE;
    String PAXFIRSTNAME;
    String PAXLASTNAME;

    @JsonGetter("PAXFIRSTNAME")
    public String getPAXFIRSTNAME() {
        return PAXFIRSTNAME;
    }

    public void setPAXFIRSTNAME(String PAXFIRSTNAME) {
        this.PAXFIRSTNAME = PAXFIRSTNAME;
    }
    @JsonGetter("SPECIALMEALCODE")
    public String getSPECIALMEALCODE() {
        return SPECIALMEALCODE;
    }

    public void setSPECIALMEALCODE(String SPECIALMEALCODE) {
        this.SPECIALMEALCODE = SPECIALMEALCODE;
    }
    @JsonGetter("PAXLASTNAME")
    public String getPAXLASTNAME() {
        return PAXLASTNAME;
    }

    public void setPAXLASTNAME(String PAXLASTNAME) {
        this.PAXLASTNAME = PAXLASTNAME;
    }
    @JsonGetter("PAXSEATCABINCLASS")
    public String getPAXSEATCABINCLASS() {
        return PAXSEATCABINCLASS;
    }

    public void setPAXSEATCABINCLASS(String PAXSEATCABINCLASS) {
        this.PAXSEATCABINCLASS = PAXSEATCABINCLASS;
    }

}
