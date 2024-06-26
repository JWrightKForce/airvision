package org.example.models;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @JsonGetter("CabinCodes")
    public List<CabinCode> getCabinCodeList() {
        return cabinCodeList;
    }

    public void setCabinCodeList(List<CabinCode> cabinCodeList) {
        this.cabinCodeList = cabinCodeList;
    }

    List<CabinCode> cabinCodeList;
}
