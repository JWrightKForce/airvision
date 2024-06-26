package org.united.airvision.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.united.airvision.config.CustomJsonBodyHandler;
import org.united.airvision.config.WebConnector;
import org.united.airvision.models.oauth.OAuthRequest;
import org.united.airvision.models.oauth.OAuthResponse;
import org.united.airvision.models.PaxCount;
import org.united.airvision.models.SpecialMealsResponse;
import org.united.airvision.models.sabre.SabrePreOrderMealsReq;
import org.united.airvision.models.sabre.SabrePreOrderMealsResp;
import org.united.airvision.models.uflifo.UFLIFOResponse;
import org.united.airvision.models.amosRequest.AMOSFlightDetailsRequest;
import org.united.airvision.models.amosResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.united.airvision.models.uflifo.UFLIFOTransformObj;

import java.net.URI;
import java.net.http.HttpRequest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FlightDetailsProcessImpl {
    @Value("${flightdetails.uri.mockuflifo}")
    String UFLIFOMockUri;

    @Value("${flightdetails.uri.mockpaxcount}")
    String PaxCountMockUri;

    @Value("${flightdetails.uri.mockpreordermeals}")
    String PreOrderMealsMockUri;

    @Value("${flightdetails.uri.mockspecialmealrequest}")
    String SplMealsMockUri;

    @Value("${flightdetails.uri.oauthTokenV1}")
    String oauthTokenUri;

    @Value("${flightdetails.oauth_credentials.client_id}")
    String OAuthClientId;

    @Value("${flightdetails.oauth_credentials.client_secret}")
    String OAuthClientSecret;

    @Value("${flightdetails.oauth_credentials.scope}")
    String OAuthScope;

    @Value("${flightdetails.oauth_credentials.userType}")
    String OAuthUserType;

    @Value("${flightdetails.GetPreOrderDetails.IncludeNonMealOptions")
    String includeNonMealOptions;

    @Autowired
    WebConnector webConnector;
    @Autowired
    CustomJsonBodyHandler jsonBodyHandler;

    @Autowired
    CabinTransformImpl cabinTransform;
    @Autowired
    OAuthRequest oAuthRequest;
    @Autowired
    AMOSFlightDetailsResponse amosFlightDetailsResponse;
    @Autowired
    Capacity capacity;
    @Autowired
    TotalCheckedInAndAccepted totalCheckedInAndAccepted;
    List<Cabin> cabins;
    UFLIFOTransformObj uflifoTransformObj;
    @Autowired
    AircraftKey aircraftKey;
    @Autowired
    CapacityBreakdown capacityBreakdown;
    SpecialMeal specialMeal;
    @Autowired
    SpecialMealInfo specialMealInfo;
    @Autowired
    SpecialMealPassenger specialMealPassenger;
    @Autowired
    PreOrder preOrder;
    @Autowired
    SabrePreOrderMealsReq sabrePreOrderMealsReq;
    @Autowired
    UflifoResponseTransformImpl uflifoResponseTransform;

    public AMOSFlightDetailsResponse processFlightDetails(AMOSFlightDetailsRequest request){
        // data setup
        String[] httpHeaders;
        String uflifoParams;
        Logger logger = LoggerFactory.getLogger(FlightDetailsProcessImpl.class);
        ObjectMapper jsonParser=new ObjectMapper();
        uflifoParams="/"+request.getFlightNumber()+"?FlightLegDepDate="+request.getOrigFlightDate()+"&Origin"+request.getBoardingDepartureStationCode();
        try {
            //call backend api
            jsonBodyHandler.settClass(PaxCount.class);
            webConnector.setBodyConvertor(jsonBodyHandler);
            webConnector.setcustomReq("GET",
                    new String[]{"Content-Type","application/json"},
                    null,
                    PaxCountMockUri);
            PaxCount paxCount = (PaxCount) webConnector.callout();
            logger.info(jsonParser.writeValueAsString(paxCount));

            jsonBodyHandler.settClass(OAuthResponse.class);
            webConnector.setBodyConvertor(jsonBodyHandler);
            oAuthRequest.setGrant_type("client_credentials");
            oAuthRequest.setClient_id(OAuthClientId);
            oAuthRequest.setClient_secret(OAuthClientSecret);
            oAuthRequest.setScope(OAuthScope);
            oAuthRequest.setUserType(OAuthUserType);
            webConnector.setcustomReq("POST",
                    new String[]{"Content-Type","application/json"},
                    oAuthRequest,
                    oauthTokenUri);
            OAuthResponse oAuthResponse = (OAuthResponse) webConnector.callout();
            logger.info(jsonParser.writeValueAsString(oAuthResponse));
            jsonBodyHandler.settClass(UFLIFOResponse.class);
            webConnector.setBodyConvertor(jsonBodyHandler);
            httpHeaders=new String[]{
                    "Content-Type", "application/json",
                    "Authorization", "Bearer "+oAuthResponse.getAccess_token()
            };
            logger.info(UFLIFOMockUri+uflifoParams);

            webConnector.setcustomReq("GET",httpHeaders, null,UFLIFOMockUri );
            UFLIFOResponse uflifoResponse = (UFLIFOResponse) webConnector.callout();
            uflifoResponseTransform.setUflifoResponse(uflifoResponse);
            uflifoTransformObj=uflifoResponseTransform.respTransform();
            logger.info(jsonParser.writeValueAsString(uflifoTransformObj));

            jsonBodyHandler.settClass(SabrePreOrderMealsResp.class);
            webConnector.setBodyConvertor(jsonBodyHandler);
            sabrePreOrderMealsReq.setFlightNumber(request.getFlightNumber());
            sabrePreOrderMealsReq.setDepartureAirport(request.getDepartureStationCode());
            sabrePreOrderMealsReq.setArrivalAirport(request.getArrivalStationCode());
            sabrePreOrderMealsReq.setFlightDate(request.getFlightDate());
            sabrePreOrderMealsReq.setIncludeNonMealOption(includeNonMealOptions.equalsIgnoreCase("false")
                    ?false:true);

            webConnector.setcustomReq("POST",
                    new String[]{"Content-Type","application/json"},
                    sabrePreOrderMealsReq,
                    PreOrderMealsMockUri);
            SabrePreOrderMealsResp sabrePreOrderMealsResp = (SabrePreOrderMealsResp) webConnector.callout();
            logger.info(jsonParser.writeValueAsString(sabrePreOrderMealsResp));

            jsonBodyHandler.settClass(SpecialMealsResponse.class);
            webConnector.setBodyConvertor(jsonBodyHandler);
            webConnector.setcustomReq("GET",
                    new String[]{"Content-Type","application/json"},
                    null,
                    SplMealsMockUri);
            SpecialMealsResponse specialMealsResponse = (SpecialMealsResponse) webConnector.callout();
            logger.info(jsonParser.writeValueAsString(specialMealsResponse));
            //AirCraft Element
            if (request.getQueryPeriod() != null) {
                if (request.getQueryPeriod().intValue() != 48) {
                    aircraftKey.setAircraftID(paxCount.getNOSENUMBER());
                }
            }
            if (!uflifoTransformObj.getEquipmentTypeCd().trim().equals("")) {
                aircraftKey.setAircraftTypeCode(uflifoTransformObj.getEquipmentTypeCd());
            }

            //Cabin child element setup
            cabinTransform.setRequest(request);
            cabinTransform.setPaxCountInput(paxCount);
            cabinTransform.setUflifoInput(uflifoTransformObj);
            cabinTransform.setSabrePreOrderInput(sabrePreOrderMealsResp);
            cabinTransform.setSpecialMealsInput(specialMealsResponse);
            cabins=cabinTransform.transformCabinList();
            //other fields mapping
            amosFlightDetailsResponse.setAirlineCode(request.getAirlineCode());
            amosFlightDetailsResponse.setFlightNumber(request.getFlightNumber());
            amosFlightDetailsResponse.setFlightDate(request.getFlightDate());
            amosFlightDetailsResponse.setArrivalStationCode(request.getArrivalStationCode());
            amosFlightDetailsResponse.setQueryPeriod(request.getQueryPeriod());

            //DepartureGate
            if (uflifoTransformObj.getDepartureGate() != null
                    || uflifoTransformObj.getDepartureGate().equalsIgnoreCase("")) {
                if (uflifoTransformObj.getDepartureGate().contains("HOLD1")) {
                    amosFlightDetailsResponse.setDepartureGate("HOLD");
                } else {
                    amosFlightDetailsResponse.setDepartureGate(uflifoTransformObj.getDepartureGate());
                }
            }

            //FlightStatus
            if (uflifoTransformObj.getStatus().equals("CANCEL")) {
                amosFlightDetailsResponse.setFlightStatus("CNCL");
            } else if (((request.getQueryPeriod() == null
                    ? 0 : request.getQueryPeriod().intValue()) == 48)
                    || paxCount.getFlightStatus().equals("O")) {
                amosFlightDetailsResponse.setFlightStatus("OPEN");
            } else {
                amosFlightDetailsResponse.setFlightStatus("CLOSED");
            }
            amosFlightDetailsResponse.setEstimatedDepartureTime(uflifoTransformObj.getEstDepartDateTime());
            amosFlightDetailsResponse.setBoardingAirlineCode(request.getBoardingAirlineCode());
            amosFlightDetailsResponse.setBoardingFlightNumber(request.getBoardingFlightNumber());
            amosFlightDetailsResponse.setBoardingFlightDate(request.getBoardingFlightDate());
            amosFlightDetailsResponse.setBoardingArrivalStationCode(request.getBoardingArrivalStationCode());
            amosFlightDetailsResponse.setBoardingFacilityStationCode(request.getBoardingArrivalStationCode());
            amosFlightDetailsResponse.setBoardingFacilityTypeCode(request.getBoardingFacilityTypeCode());
            amosFlightDetailsResponse.setBoardingFacilitySequenceNumber(request.getBoardingFacilitySequenceNumber());
            amosFlightDetailsResponse.setAircraftKey(aircraftKey);
            amosFlightDetailsResponse.setCabin(cabins);
            return amosFlightDetailsResponse;
        }catch(Exception e){
            logger.error(e.getMessage());
            return amosFlightDetailsResponse;
        }
    }

}
