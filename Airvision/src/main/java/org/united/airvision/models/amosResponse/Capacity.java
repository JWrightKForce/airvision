

package org.united.airvision.models.amosResponse;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.stereotype.Component;

@Component
@JacksonXmlRootElement(localName = "Capacity")
public class Capacity implements Serializable {


    @JacksonXmlProperty(localName="CapacityBreakdown")
    protected CapacityBreakdown capacityBreakdown;
    @JacksonXmlProperty(localName = "Total")
    protected BigInteger total;

    public CapacityBreakdown getCapacityBreakdown() {
        return capacityBreakdown;
    }

    /**
     * Sets the value of the capacityBreakdown property.
     * 
     * @param value
     *     allowed object is
     *     {@link CapacityBreakdown }
     *     
     */
    public void setCapacityBreakdown(CapacityBreakdown value) {
        this.capacityBreakdown = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotal(BigInteger value) {
        this.total = value;
    }

}
