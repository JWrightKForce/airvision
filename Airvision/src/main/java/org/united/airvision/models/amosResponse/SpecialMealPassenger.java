
package org.united.airvision.models.amosResponse;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
@JacksonXmlRootElement(localName = "SpecialMealPassenger")
public class SpecialMealPassenger implements Serializable {

    @JacksonXmlProperty(localName = "FirstName", isAttribute = true)
    protected String firstName;
    @JacksonXmlProperty(localName = "LastName", isAttribute = true)
    protected String lastName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

}
