package org.united.airvision.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.united.airvision.models.uflifo.FlightStatus;
import org.united.airvision.models.uflifo.UFLIFOResponse;
import org.united.airvision.models.uflifo.UFLIFOTransformObj;

import java.util.List;
import java.util.Optional;

@Service
public class UflifoResponseTransformImpl {
    public void setUflifoResponse(UFLIFOResponse uflifoResponse) {
        this.uflifoResponse = uflifoResponse;
    }

    UFLIFOResponse uflifoResponse;
    UFLIFOTransformObj respTransformObj;
    public UFLIFOTransformObj respTransform(){
        respTransformObj=new UFLIFOTransformObj();
        List<FlightStatus> flightStatusList=uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getFlightStatusList();

        Optional<FlightStatus> flightStatus=flightStatusList.stream().filter(t->t.getStatusType().equalsIgnoreCase("LegStatus")).findFirst();
        if(((flightStatus.get()==null)?"":flightStatus.get().getCode()).equalsIgnoreCase("CNCL")){
            respTransformObj.setStatus("CANCEL");
                    }
        else if((uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEstimatedArrivalDelayMinutes()!=null?
                Integer.parseInt(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEstimatedArrivalDelayMinutes()):0)>0
                && (uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEstimatedDepartureDelayMinutes()!=null?
                Integer.parseInt(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEstimatedDepartureDelayMinutes()):0)>0){
            respTransformObj.setStatus("DELAY");
        }else{
            respTransformObj.setStatus("NA");
        }

        if(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEstimatedDepartureTime()!=null &&
        uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEstimatedDepartureTime()!=""){
            respTransformObj.setEstDepartDateTime(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEstimatedDepartureTime());
        }

        if(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEquipment().getModel().getKey()!=null &&
                uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEquipment().getModel().getKey()!="" ){
            respTransformObj.setEquipmentTypeCd(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getEquipment().getModel().getKey().trim());
        }

        if(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getOperatingAirline().getIATACode()!=null &&
                uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getOperatingAirline().getIATACode()!="" ){
            respTransformObj.setCarrCode(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getOperatingAirline().getIATACode());
        }
        if(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getDepartureGate()!=null &&
                uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getDepartureGate()!=""){
            respTransformObj.setDepartureGate(uflifoResponse.getFlightLegList().get(0).getOperationalFlightSegment().get(0).getDepartureGate());
        }

        return respTransformObj;

    }




}
