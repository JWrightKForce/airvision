

package org.united.airvision.models.amosResponse;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.stereotype.Component;

@Component
@JacksonXmlRootElement(localName = "Cabin")
public class Cabin implements Serializable {

    @JacksonXmlProperty(localName = "Capacity")
    protected Capacity capacity;
    @JacksonXmlProperty(localName = "TotalCheckedInAndAccepted")
    protected TotalCheckedInAndAccepted totalCheckedInAndAccepted;
    @JacksonXmlProperty(localName = "SpecialMealInfo")
    protected SpecialMealInfo specialMealInfo;
    @JacksonXmlProperty(localName = "ClassOfServiceCode", isAttribute = true)
    protected String classOfServiceCode;
    @JacksonXmlProperty(localName = "Booked", isAttribute=true)
    protected BigInteger booked;
    @JacksonXmlProperty(localName = "ChildCount",isAttribute = true)
    protected BigInteger childCount;
    @JacksonXmlProperty(localName = "RevenueStandbys", isAttribute = true)
    protected BigInteger revenueStandbys;
    @JacksonXmlProperty(localName = "NonRevenueStandbys", isAttribute = true)
    protected BigInteger nonRevenueStandbys;
    @JacksonXmlProperty(localName = "PositiveSpaceStandbys", isAttribute = true)
    protected BigInteger positiveSpaceStandbys;
    @JacksonXmlProperty(localName = "FrequentFlierTier1", isAttribute = true)
    protected BigInteger frequentFlierTier1;
    @JacksonXmlProperty(localName = "FrequentFlierTier2",isAttribute = true)
    protected BigInteger frequentFlierTier2;
    @JacksonXmlProperty(localName = "FrequentFlierTier3", isAttribute = true)
    protected BigInteger frequentFlierTier3;
    @JacksonXmlProperty(localName = "FrequentFlierTier4" ,isAttribute = true)
    protected BigInteger frequentFlierTier4;
    @JacksonXmlProperty(localName = "SupplementalMealQuantity" ,isAttribute = true)
    protected BigInteger supplementalMealQuantity;

    public Capacity getCapacity() {
        return capacity;
    }

    /**
     * Sets the value of the capacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Capacity }
     *     
     */
    public void setCapacity(Capacity value) {
        this.capacity = value;
    }

    /**
     * Gets the value of the totalCheckedInAndAccepted property.
     * 
     * @return
     *     possible object is
     *     {@link TotalCheckedInAndAccepted }
     *     
     */
    public TotalCheckedInAndAccepted getTotalCheckedInAndAccepted() {
        return totalCheckedInAndAccepted;
    }

    /**
     * Sets the value of the totalCheckedInAndAccepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalCheckedInAndAccepted }
     *     
     */
    public void setTotalCheckedInAndAccepted(TotalCheckedInAndAccepted value) {
        this.totalCheckedInAndAccepted = value;
    }

    /**
     * Gets the value of the specialMealInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SpecialMealInfo }
     *     
     */
    public SpecialMealInfo getSpecialMealInfo() {
        return specialMealInfo;
    }

    /**
     * Sets the value of the specialMealInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialMealInfo }
     *     
     */
    public void setSpecialMealInfo(SpecialMealInfo value) {
        this.specialMealInfo = value;
    }

    /**
     * Gets the value of the classOfServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassOfServiceCode() {
        return classOfServiceCode;
    }

    /**
     * Sets the value of the classOfServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassOfServiceCode(String value) {
        this.classOfServiceCode = value;
    }

    /**
     * Gets the value of the booked property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBooked() {
        return booked;
    }

    /**
     * Sets the value of the booked property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBooked(BigInteger value) {
        this.booked = value;
    }

    /**
     * Gets the value of the childCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getChildCount() {
        return childCount;
    }

    /**
     * Sets the value of the childCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setChildCount(BigInteger value) {
        this.childCount = value;
    }

    /**
     * Gets the value of the revenueStandbys property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRevenueStandbys() {
        return revenueStandbys;
    }

    /**
     * Sets the value of the revenueStandbys property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRevenueStandbys(BigInteger value) {
        this.revenueStandbys = value;
    }

    /**
     * Gets the value of the nonRevenueStandbys property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNonRevenueStandbys() {
        return nonRevenueStandbys;
    }

    /**
     * Sets the value of the nonRevenueStandbys property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNonRevenueStandbys(BigInteger value) {
        this.nonRevenueStandbys = value;
    }

    /**
     * Gets the value of the positiveSpaceStandbys property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPositiveSpaceStandbys() {
        return positiveSpaceStandbys;
    }

    /**
     * Sets the value of the positiveSpaceStandbys property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPositiveSpaceStandbys(BigInteger value) {
        this.positiveSpaceStandbys = value;
    }

    /**
     * Gets the value of the frequentFlierTier1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFrequentFlierTier1() {
        return frequentFlierTier1;
    }

    /**
     * Sets the value of the frequentFlierTier1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFrequentFlierTier1(BigInteger value) {
        this.frequentFlierTier1 = value;
    }

    /**
     * Gets the value of the frequentFlierTier2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFrequentFlierTier2() {
        return frequentFlierTier2;
    }

    /**
     * Sets the value of the frequentFlierTier2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFrequentFlierTier2(BigInteger value) {
        this.frequentFlierTier2 = value;
    }

    /**
     * Gets the value of the frequentFlierTier3 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFrequentFlierTier3() {
        return frequentFlierTier3;
    }

    /**
     * Sets the value of the frequentFlierTier3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFrequentFlierTier3(BigInteger value) {
        this.frequentFlierTier3 = value;
    }

    /**
     * Gets the value of the frequentFlierTier4 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFrequentFlierTier4() {
        return frequentFlierTier4;
    }

    /**
     * Sets the value of the frequentFlierTier4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFrequentFlierTier4(BigInteger value) {
        this.frequentFlierTier4 = value;
    }

    /**
     * Gets the value of the supplementalMealQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSupplementalMealQuantity() {
        return supplementalMealQuantity;
    }

    /**
     * Sets the value of the supplementalMealQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSupplementalMealQuantity(BigInteger value) {
        this.supplementalMealQuantity = value;
    }

}
