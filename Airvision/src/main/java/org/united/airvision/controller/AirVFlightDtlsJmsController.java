package org.united.airvision.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.united.airvision.models.amosRequest.AMOSFlightDetailsRequest;
import org.united.airvision.models.amosResponse.AMOSFlightDetailsResponse;
import org.united.airvision.constants.AirVisionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.united.airvision.service.impl.FlightDetailsProcessImpl;

@Component
@ComponentScan
public class AirVFlightDtlsJmsController {
    @Autowired
    FlightDetailsProcessImpl flightDetailsProcess;

    @JmsListener(destination="UAL.OPS.AMOS.REQ.1",containerFactory = "mqSeriesContainerFactory")
    @SendTo("UAL.OPS.AMOS.RESP.1")
    public String flightDetailsReq(@Payload Message<String> payload) throws Exception{
        XmlMapper xmlParser=new XmlMapper();
        xmlParser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AMOSFlightDetailsRequest payloadrequest=xmlParser.readValue(payload.getPayload(),AMOSFlightDetailsRequest.class);
        AMOSFlightDetailsResponse payloadresponse=flightDetailsProcess.processFlightDetails(payloadrequest);
        return AirVisionConstants.XML_PREPEND+xmlParser.writeValueAsString(payloadresponse);
    }
}
