package org.example.controller;



 import org.example.models.*;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;
 import java.util.List;
 import java.util.ArrayList;


@RestController
public class MockBackend {

    @GetMapping(path="/uflifo",produces={"application/json"})
    public UFLIFOResponse uflifoMock(){
         UFLIFOResponse mockResponse=new UFLIFOResponse();
         FlightLeg flightLeg=new FlightLeg();
         List<FlightLeg> flightLegList=new ArrayList<>();
         OperationalFlightSegment flightSegment=new OperationalFlightSegment();
         List<OperationalFlightSegment> operationalFlightSegmentList=new ArrayList<>();
         OperatingAirline operatingAirline=new OperatingAirline();
         AirModel airModel=new AirModel();
         FlightStatus status=new FlightStatus();
         List<FlightStatus> flightStatusList=new ArrayList<>();
         Equipment equipment=new Equipment();
         UFLIFOError uflifoError=new UFLIFOError();
         List<UFLIFOError> uflifoErrorList=new ArrayList<>();

         uflifoError.setCode("404");
         uflifoError.setDescription("Airline not Found");
         airModel.setKey("BOE777");
         operatingAirline.setIATACode("UA");
         flightSegment.setDepartureGate("2024-05-27T20:58:00");
         flightSegment.setEstimatedDepartureTime("2024-05-27T21:30:00");
         flightSegment.setDepartureGate("HOLD1");
         flightSegment.setEstimatedArrivalDelayMinutes("55");
         flightSegment.setEstimatedDepartureDelayMinutes("55");
         status.setStatusType("LegStatus");
         status.setCode("CNCL");
         equipment.setModel(airModel);
         flightSegment.setOperatingAirline(operatingAirline);
         flightSegment.setEquipment(equipment);
         flightStatusList.add(status);
         flightSegment.setFlightStatusList(flightStatusList);
         operationalFlightSegmentList.add(flightSegment);
         flightLeg.setOperationalFlightSegment(operationalFlightSegmentList);
         flightLegList.add(flightLeg);
         mockResponse.setFlightLegList(flightLegList);
         uflifoErrorList.add(uflifoError);
         mockResponse.setError(uflifoErrorList);
         return mockResponse;
    }

    @GetMapping(path="/paxCount",produces={"application/json"})
    public PaxCount paxCountMock(){
        PaxCount mockResponse=new PaxCount();
        mockResponse.setNOSENUMBER("77K");
        mockResponse.setBOOKEDPAXCOUNTC("100");
        mockResponse.setBOOKEDPAXCOUNTY("60");
        mockResponse.setBOOKEDPAXCOUNTF("10");
        mockResponse.setCABININDICATOR("C");
        mockResponse.setFlightStatus("STANDBY");
        mockResponse.setDepartureGate("C11");
        mockResponse.setAUTHORIZEDTOBOARDCOUNT("160");
        mockResponse.setPHYSICALSEATCOUNT("170");
        mockResponse.setCHECKEDINPAXCOUNTY("160");
        mockResponse.setBOARDEDPAXCOUNTY("100");
        return mockResponse;
    }

    @PostMapping(path="/GetPreOrderMeals",produces={"application/json"})
    public SabrePreOrderMeals preOrderMealsMock (){
        SabrePreOrderMeals mockResponse=new SabrePreOrderMeals();
        List<Order> orders=new ArrayList<>();
        Order order=new Order();
        List<CabinCode> cabinCodeList=new ArrayList<>();
        CabinCode cabinCode=new CabinCode();
        Traveler traveler=new Traveler();
        List<Traveler> travelerList=new ArrayList<>();
        List<SelectedMeals> selectedMealsList=new ArrayList<>();
        SelectedMeals selectedMeals=new SelectedMeals();
        selectedMeals.setServiceSeqNumber("10001");
        selectedMeals.setMealCode("SPL");
        selectedMealsList.add(selectedMeals);
        traveler.setGivenName("Dheeraj");
        traveler.setSurName("M");
        traveler.setSelectedMealsList(selectedMealsList);
        travelerList.add(traveler);
        cabinCode.setCabinCode("J");
        cabinCode.setTravelerList(travelerList);
        cabinCodeList.add(cabinCode);
        order.setCabinCodeList(cabinCodeList);
        orders.add(order);
        mockResponse.setOrders(orders);
        return mockResponse;
    }

    @GetMapping(path="/specialmealrequest",produces={"application/json"})
    public SpecialMealResponse specialMealMock(){
        SpecialMealResponse mealMockResponse=new SpecialMealResponse();
        mealMockResponse.setPAXFIRSTNAME("John");
        mealMockResponse.setPAXLASTNAME("Doe");
        mealMockResponse.setPAXSEATCABINCLASS("Y");
        mealMockResponse.setSPECIALMEALCODE("PREO");
        return mealMockResponse;
    }
}
