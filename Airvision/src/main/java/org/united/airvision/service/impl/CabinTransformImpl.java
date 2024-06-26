package org.united.airvision.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.united.airvision.models.PaxCount;
import org.united.airvision.models.SpecialMealsResponse;
import org.united.airvision.models.amosRequest.AMOSFlightDetailsRequest;
import org.united.airvision.models.amosResponse.*;
import org.united.airvision.models.sabre.Order;
import org.united.airvision.models.sabre.SabrePreOrderMealsResp;
import org.united.airvision.models.uflifo.UFLIFOTransformObj;

import java.math.BigInteger;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CabinTransformImpl {


    public void setPaxCountInput(PaxCount paxCountInput) {
        this.paxCountInput = paxCountInput;
    }

    public void setUflifoInput(UFLIFOTransformObj uflifoInput) {
        this.uflifoInput = uflifoInput;
    }

    public void setSpecialMealsInput(SpecialMealsResponse specialMealsInput) {
        SpecialMealsInput = specialMealsInput;
    }

    public void setSabrePreOrderInput(SabrePreOrderMealsResp sabrePreOrderInput) {
        this.sabrePreOrderInput = sabrePreOrderInput;
    }

    public AMOSFlightDetailsRequest getRequest() {
        return request;
    }

    public void setRequest(AMOSFlightDetailsRequest request) {
        this.request = request;
    }

    AMOSFlightDetailsRequest request;
    PaxCount paxCountInput;
    UFLIFOTransformObj uflifoInput;
    SabrePreOrderMealsResp sabrePreOrderInput;
    SpecialMealsResponse SpecialMealsInput;

    public List<Cabin> transformCabinList(){

        String jCabinIndicators[] = {"C", "B"};
        String oCabinIndicators[] = {"F", "B"};
        String jModelKeys[] = {"77H", "75J", "77A", "75K"};
        String inputCabinInd = paxCountInput.getCABININDICATOR();
        String inputModelKey = uflifoInput.getEquipmentTypeCd();
        String CarrCode= uflifoInput.getCarrCode();
        List<Cabin> cabins=new ArrayList<>();
        TotalCheckedInAndAccepted totalCheckedInAndAccepted=new TotalCheckedInAndAccepted();
        String physicalCountJ;
        String authorizedToBoardCountJ;
        String physicalCountO;
        String authorizedToBoardCountO;
        String physicalCountY;
        String authorizedToBoardCountY;
        SpecialMealInfo specialMealInfo;
        SpecialMealPassenger specialMealPassenger;
        SpecialMeal specialMeal;
        List<SpecialMeal> specialMeals;
        PreOrder preOrder;
        List<PreOrder> preOrderList;
        Capacity capacity;
        CapacityBreakdown capacityBreakdown;


        //Cabin for ClassOfService J
        if (Arrays.asList(jCabinIndicators).stream().filter(t->t.equalsIgnoreCase(inputCabinInd)).findAny().isPresent()){
            Cabin cabinJ=new Cabin();
            cabinJ.setClassOfServiceCode("J");
            cabinJ.setBooked(new BigInteger(paxCountInput.getBOOKEDPAXCOUNTC()!=null?
                    paxCountInput.getBOOKEDPAXCOUNTC():"0"));
             if((paxCountInput.getCABININDICATOR()==null?"":paxCountInput.getCABININDICATOR()).equalsIgnoreCase("B")) {
                physicalCountJ = paxCountInput.getPHYSICALSEATCOUNT();// PHYSICALSEATCOUNTF
            }else{
                physicalCountJ = paxCountInput.getPHYSICALSEATCOUNT(); // PHYSICALSEATCOUNTC
            }
            authorizedToBoardCountJ=paxCountInput.getAUTHORIZEDTOBOARDCOUNT(); //AUTHORIZEDTOBOARDCOUNTC
            if(request.getQueryPeriod().intValue()==0){
                totalCheckedInAndAccepted.setTotal(new BigInteger(paxCountInput.getBOARDEDPAXCOUNTY()));// BOARDEDPAXCOUNTC
            }else{
                totalCheckedInAndAccepted.setTotal(new BigInteger(paxCountInput.getCHECKEDINPAXCOUNTY()));// CHECKEDINPAXCOUNTC
            }
            capacity=new Capacity();
            capacityBreakdown=new CapacityBreakdown();
            if (request.getQueryPeriod().intValue()==48 ||
                uflifoInput.getCarrCode().equalsIgnoreCase("UA")){
                    capacity.setTotal(new BigInteger(physicalCountJ==null?"0":physicalCountJ));
                    capacityBreakdown.setAuthorized(new BigInteger(physicalCountJ==null?"0":physicalCountJ));
                    capacity.setCapacityBreakdown(capacityBreakdown);
            }else{
                capacity.setTotal(new BigInteger(authorizedToBoardCountJ==null?"0":authorizedToBoardCountJ));
                capacityBreakdown.setAuthorized(new BigInteger(authorizedToBoardCountJ==null?"0":authorizedToBoardCountJ));
                capacity.setCapacityBreakdown(capacityBreakdown);
            }
            specialMealInfo=new SpecialMealInfo();
            specialMeals=new ArrayList<>();

            if(SpecialMealsInput.getPAXSEATCABINCLASS().equalsIgnoreCase("C")
                    ||SpecialMealsInput.getPAXSEATCABINCLASS().equalsIgnoreCase("J")
            ||(SpecialMealsInput.getPAXSEATCABINCLASS().equalsIgnoreCase("F")&&(
                    paxCountInput.getCABININDICATOR()!="B"))){
                specialMeal=new SpecialMeal();
                specialMealPassenger=new SpecialMealPassenger();
                specialMeal.setSpecialMealCode(SpecialMealsInput.getSPECIALMEALCODE());
                specialMealPassenger.setFirstName(SpecialMealsInput.getPAXFIRSTNAME());
                specialMealPassenger.setLastName(SpecialMealsInput.getPAXLASTNAME());
                specialMeal.setSpecialMealPassenger(specialMealPassenger);
                specialMeals.add(specialMeal);
            }

            List<Order> orders= sabrePreOrderInput.getOrders().stream().filter(s->
                    s.getCabinCodeList().stream()
                            .filter(t->t.getCabinCode().equalsIgnoreCase("J"))
                            .findAny().isPresent()).toList();
            for(int i=0;i<orders.size();i++){
                for(int j=0;j<orders.get(i).getCabinCodeList().size();j++){
                    preOrderList=new ArrayList<>();
                    specialMeal = new SpecialMeal();
                    specialMeal.setSpecialMealCode(orders.get(i)
                            .getCabinCodeList().get(j).getCabinCode());
                    for(int k=0;k<orders.get(i).getCabinCodeList().get(j).getTravelerList().size();k++) {
                        specialMealPassenger = new SpecialMealPassenger();
                        specialMealPassenger.setFirstName(orders.get(i)
                                .getCabinCodeList().get(j).getTravelerList().get(k).getGivenName());
                        specialMealPassenger.setLastName(orders.get(i)
                                .getCabinCodeList().get(j).getTravelerList().get(k).getSurName());
                        preOrderList=new ArrayList<>();
                        if(orders.get(i).getCabinCodeList().get(j).getCabinCode().equalsIgnoreCase("PREO")) {
                            for (int l = 0; l < orders.get(i).getCabinCodeList().get(j).getTravelerList().get(k).getSelectedMealsList().size(); l++) {
                                preOrder = new PreOrder();
                                preOrder.setServiceSequenceNumber(Integer.parseInt(orders.get(i).getCabinCodeList()
                                        .get(j).getTravelerList().get(k)
                                        .getSelectedMealsList().get(l).getServiceSeqNumber()));
                                preOrder.setServiceSequenceNumber(Integer.parseInt(orders.get(i).getCabinCodeList()
                                        .get(j).getTravelerList().get(k)
                                        .getSelectedMealsList().get(l).getMealCode()));
                                preOrderList.add(preOrder);
                            }
                        }
                    }

                    specialMeals.add(specialMeal);
                 }
            }
            specialMealInfo.setSpecialMeal(specialMeals);
            cabinJ.setCapacity(capacity);
            cabinJ.setTotalCheckedInAndAccepted(totalCheckedInAndAccepted);
            cabinJ.setSpecialMealInfo(specialMealInfo);
            cabins.add(cabinJ);
        }
        //Cabin element for ClassOfService O
        if(Arrays.asList(oCabinIndicators).stream().filter(t->t.equalsIgnoreCase(inputCabinInd)).findAny().isPresent()){
            Cabin cabinO=new Cabin();
            cabinO.setClassOfServiceCode("O");
            cabinO.setBooked(new BigInteger(paxCountInput.getBOOKEDPAXCOUNTC()!=null?
                    paxCountInput.getBOOKEDPAXCOUNTF():"0"));
            if((paxCountInput.getCABININDICATOR()==null?"":paxCountInput.getCABININDICATOR()).equalsIgnoreCase("B")) {
                physicalCountO = paxCountInput.getPHYSICALSEATCOUNT();// PHYSICALSEATCOUNTC
            }else{
                physicalCountO = paxCountInput.getPHYSICALSEATCOUNT(); // PHYSICALSEATCOUNTF
            }
            authorizedToBoardCountO=paxCountInput.getAUTHORIZEDTOBOARDCOUNT(); //AUTHORIZEDTOBOARDCOUNTF
            if(request.getQueryPeriod().intValue()==0){
                totalCheckedInAndAccepted.setTotal(new BigInteger(paxCountInput.getBOARDEDPAXCOUNTY()));// BOARDEDPAXCOUNTF
            }else{
                totalCheckedInAndAccepted.setTotal(new BigInteger(paxCountInput.getCHECKEDINPAXCOUNTY()));// CHECKEDINPAXCOUNTF
            }
            capacity=new Capacity();
            capacityBreakdown=new CapacityBreakdown();
            if (request.getQueryPeriod().intValue()==48 ||
                    uflifoInput.getCarrCode().equalsIgnoreCase("UA")){
                capacity.setTotal(new BigInteger(physicalCountO==null?"0":physicalCountO));
                capacityBreakdown.setAuthorized(new BigInteger(physicalCountO==null?"0":physicalCountO));
                capacity.setCapacityBreakdown(capacityBreakdown);
            }else{
                capacity.setTotal(new BigInteger(authorizedToBoardCountO==null?"0":authorizedToBoardCountO));
                capacityBreakdown.setAuthorized(new BigInteger(authorizedToBoardCountO==null?"0":authorizedToBoardCountO));
                capacity.setCapacityBreakdown(capacityBreakdown);
            }
            specialMealInfo=new SpecialMealInfo();
            specialMeals=new ArrayList<>();

            if(SpecialMealsInput.getPAXSEATCABINCLASS().equalsIgnoreCase("F")
                    ||SpecialMealsInput.getPAXSEATCABINCLASS().equalsIgnoreCase("O")
                    ||(SpecialMealsInput.getPAXSEATCABINCLASS().equalsIgnoreCase("C")&&(
                    paxCountInput.getCABININDICATOR()!="B"))){
                specialMeal=new SpecialMeal();
                specialMealPassenger=new SpecialMealPassenger();
                specialMeal.setSpecialMealCode(SpecialMealsInput.getSPECIALMEALCODE());
                specialMealPassenger.setFirstName(SpecialMealsInput.getPAXFIRSTNAME());
                specialMealPassenger.setLastName(SpecialMealsInput.getPAXLASTNAME());
                specialMeal.setSpecialMealPassenger(specialMealPassenger);
                specialMeals.add(specialMeal);
            }

            List<Order> orders= sabrePreOrderInput.getOrders().stream().filter(s->
                    s.getCabinCodeList().stream()
                            .filter(t->t.getCabinCode().equalsIgnoreCase("O"))
                            .findAny().isPresent()).toList();
            for(int i=0;i<orders.size();i++){
                for(int j=0;j<orders.get(i).getCabinCodeList().size();j++){
                    preOrderList=new ArrayList<>();
                    specialMeal = new SpecialMeal();
                    specialMeal.setSpecialMealCode(orders.get(i)
                            .getCabinCodeList().get(j).getCabinCode());
                    for(int k=0;k<orders.get(i).getCabinCodeList().get(j).getTravelerList().size();k++) {
                        specialMealPassenger = new SpecialMealPassenger();
                        specialMealPassenger.setFirstName(orders.get(i)
                                .getCabinCodeList().get(j).getTravelerList().get(k).getGivenName());
                        specialMealPassenger.setLastName(orders.get(i)
                                .getCabinCodeList().get(j).getTravelerList().get(k).getSurName());
                        preOrderList=new ArrayList<>();
                        if(orders.get(i).getCabinCodeList().get(j).getCabinCode().equalsIgnoreCase("PREO")) {
                            for (int l = 0; l < orders.get(i).getCabinCodeList().get(j).getTravelerList().get(k).getSelectedMealsList().size(); l++) {
                                preOrder = new PreOrder();
                                preOrder.setServiceSequenceNumber(Integer.parseInt(orders.get(i).getCabinCodeList()
                                        .get(j).getTravelerList().get(k)
                                        .getSelectedMealsList().get(l).getServiceSeqNumber()));
                                preOrder.setServiceSequenceNumber(Integer.parseInt(orders.get(i).getCabinCodeList()
                                        .get(j).getTravelerList().get(k)
                                        .getSelectedMealsList().get(l).getMealCode()));
                                preOrderList.add(preOrder);
                            }
                        }
                    }

                    specialMeals.add(specialMeal);
                }
            }
            specialMealInfo.setSpecialMeal(specialMeals);
            cabinO.setCapacity(capacity);
            cabinO.setTotalCheckedInAndAccepted(totalCheckedInAndAccepted);
            cabinO.setSpecialMealInfo(specialMealInfo);
            cabins.add(cabinO);
        }
        // Cabin Element for ClassOfService Y
        Cabin cabinY=new Cabin();
        cabinY.setClassOfServiceCode("Y");

        cabinY.setBooked(new BigInteger(paxCountInput.getBOOKEDPAXCOUNTY()!=null?
                paxCountInput.getBOOKEDPAXCOUNTY():"0"));
        if((paxCountInput.getCABININDICATOR()==null?"":paxCountInput.getCABININDICATOR()).equalsIgnoreCase("B")) {
            physicalCountY = paxCountInput.getPHYSICALSEATCOUNT();// PHYSICALSEATCOUNTY
        }else{
            if(request.getQueryPeriod().intValue()!=48){
                physicalCountY = paxCountInput.getPHYSICALSEATCOUNT()+ paxCountInput.getPHYSICALSEATCOUNT(); // PHYSICALSEATCOUNTY+PHYSICALSEATCOUNTC
            }
            else{
                physicalCountY = paxCountInput.getPHYSICALSEATCOUNT(); // PHYSICALSEATCOUNTY
            }
        }
        authorizedToBoardCountY=paxCountInput.getAUTHORIZEDTOBOARDCOUNT(); //AUTHORIZEDTOBOARDCOUNTY
        if(request.getQueryPeriod().intValue()==0){
            totalCheckedInAndAccepted.setTotal(new BigInteger(paxCountInput.getBOARDEDPAXCOUNTY()));// BOARDEDPAXCOUNTY
        }else{
            totalCheckedInAndAccepted.setTotal(new BigInteger(paxCountInput.getCHECKEDINPAXCOUNTY()));// CHECKEDINPAXCOUNTY
        }
        capacity=new Capacity();
        capacityBreakdown=new CapacityBreakdown();
        if (request.getQueryPeriod().intValue()==48 ||
                uflifoInput.getCarrCode().equalsIgnoreCase("UA")){
            capacity.setTotal(new BigInteger(physicalCountY==null?"0":physicalCountY));
            capacityBreakdown.setAuthorized(new BigInteger(physicalCountY==null?"0":physicalCountY));
            capacity.setCapacityBreakdown(capacityBreakdown);
        }else{
            capacity.setTotal(new BigInteger(authorizedToBoardCountY==null?"0":authorizedToBoardCountY));
            capacityBreakdown.setAuthorized(new BigInteger(authorizedToBoardCountY==null?"0":authorizedToBoardCountY));
            capacity.setCapacityBreakdown(capacityBreakdown);
        }
        specialMealInfo=new SpecialMealInfo();
        specialMeals=new ArrayList<>();

        if(SpecialMealsInput.getPAXSEATCABINCLASS().equalsIgnoreCase("Y")
                ||SpecialMealsInput.getPAXSEATCABINCLASS().equalsIgnoreCase("")){
            specialMeal=new SpecialMeal();
            specialMealPassenger=new SpecialMealPassenger();
            specialMeal.setSpecialMealCode(SpecialMealsInput.getSPECIALMEALCODE());
            specialMealPassenger.setFirstName(SpecialMealsInput.getPAXFIRSTNAME());
            specialMealPassenger.setLastName(SpecialMealsInput.getPAXLASTNAME());
            specialMeal.setSpecialMealPassenger(specialMealPassenger);
            specialMeals.add(specialMeal);
        }

        List<Order> orders= sabrePreOrderInput.getOrders().stream().filter(s->
                s.getCabinCodeList().stream()
                        .filter(t->t.getCabinCode().equalsIgnoreCase("O"))
                        .findAny().isPresent()).toList();
        for(int i=0;i<orders.size();i++){
            for(int j=0;j<orders.get(i).getCabinCodeList().size();j++){
                specialMeal = new SpecialMeal();
                specialMeal.setSpecialMealCode(orders.get(i)
                        .getCabinCodeList().get(j).getCabinCode());
                for(int k=0;k<orders.get(i).getCabinCodeList().get(j).getTravelerList().size();k++) {
                    specialMealPassenger = new SpecialMealPassenger();
                    specialMealPassenger.setFirstName(orders.get(i)
                            .getCabinCodeList().get(j).getTravelerList().get(k).getGivenName());
                    specialMealPassenger.setLastName(orders.get(i)
                            .getCabinCodeList().get(j).getTravelerList().get(k).getSurName());
                    preOrderList=new ArrayList<>();
                    if(orders.get(i).getCabinCodeList().get(j).getCabinCode().equalsIgnoreCase("PREO")) {
                        for (int l = 0; l < orders.get(i).getCabinCodeList().get(j).getTravelerList().get(k).getSelectedMealsList().size(); l++) {
                            preOrder = new PreOrder();
                            preOrder.setServiceSequenceNumber(Integer.parseInt(orders.get(i).getCabinCodeList()
                                    .get(j).getTravelerList().get(k)
                                    .getSelectedMealsList().get(l).getServiceSeqNumber()));
                            preOrder.setServiceSequenceNumber(Integer.parseInt(orders.get(i).getCabinCodeList()
                                    .get(j).getTravelerList().get(k)
                                    .getSelectedMealsList().get(l).getMealCode()));
                            preOrderList.add(preOrder);
                        }
                    }
                }

                specialMeals.add(specialMeal);
            }
        }
        specialMealInfo.setSpecialMeal(specialMeals);
        cabinY.setCapacity(capacity);
        cabinY.setTotalCheckedInAndAccepted(totalCheckedInAndAccepted);
        cabinY.setSpecialMealInfo(specialMealInfo);
        cabins.add(cabinY);

        return cabins;
    }
}
