//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.07 at 02:17:13 PM EDT 
//


package com.copyroute.cdm.identity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for location complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="location">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "location", propOrder = {
    "address",
    "city",
    "state",
    "zip"
})
public class Location
    implements Serializable, Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String address;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String state;
    @XmlElement(required = true)
    protected String zip;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theAddress;
            theAddress = this.getAddress();
            strategy.appendField(locator, this, "address", buffer, theAddress);
        }
        {
            String theCity;
            theCity = this.getCity();
            strategy.appendField(locator, this, "city", buffer, theCity);
        }
        {
            String theState;
            theState = this.getState();
            strategy.appendField(locator, this, "state", buffer, theState);
        }
        {
            String theZip;
            theZip = this.getZip();
            strategy.appendField(locator, this, "zip", buffer, theZip);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Location)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Location that = ((Location) object);
        {
            String lhsAddress;
            lhsAddress = this.getAddress();
            String rhsAddress;
            rhsAddress = that.getAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "address", lhsAddress), LocatorUtils.property(thatLocator, "address", rhsAddress), lhsAddress, rhsAddress)) {
                return false;
            }
        }
        {
            String lhsCity;
            lhsCity = this.getCity();
            String rhsCity;
            rhsCity = that.getCity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "city", lhsCity), LocatorUtils.property(thatLocator, "city", rhsCity), lhsCity, rhsCity)) {
                return false;
            }
        }
        {
            String lhsState;
            lhsState = this.getState();
            String rhsState;
            rhsState = that.getState();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "state", lhsState), LocatorUtils.property(thatLocator, "state", rhsState), lhsState, rhsState)) {
                return false;
            }
        }
        {
            String lhsZip;
            lhsZip = this.getZip();
            String rhsZip;
            rhsZip = that.getZip();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "zip", lhsZip), LocatorUtils.property(thatLocator, "zip", rhsZip), lhsZip, rhsZip)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theAddress;
            theAddress = this.getAddress();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "address", theAddress), currentHashCode, theAddress);
        }
        {
            String theCity;
            theCity = this.getCity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "city", theCity), currentHashCode, theCity);
        }
        {
            String theState;
            theState = this.getState();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "state", theState), currentHashCode, theState);
        }
        {
            String theZip;
            theZip = this.getZip();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "zip", theZip), currentHashCode, theZip);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof Location) {
            final Location copy = ((Location) draftCopy);
            if (this.address!= null) {
                String sourceAddress;
                sourceAddress = this.getAddress();
                String copyAddress = ((String) strategy.copy(LocatorUtils.property(locator, "address", sourceAddress), sourceAddress));
                copy.setAddress(copyAddress);
            } else {
                copy.address = null;
            }
            if (this.city!= null) {
                String sourceCity;
                sourceCity = this.getCity();
                String copyCity = ((String) strategy.copy(LocatorUtils.property(locator, "city", sourceCity), sourceCity));
                copy.setCity(copyCity);
            } else {
                copy.city = null;
            }
            if (this.state!= null) {
                String sourceState;
                sourceState = this.getState();
                String copyState = ((String) strategy.copy(LocatorUtils.property(locator, "state", sourceState), sourceState));
                copy.setState(copyState);
            } else {
                copy.state = null;
            }
            if (this.zip!= null) {
                String sourceZip;
                sourceZip = this.getZip();
                String copyZip = ((String) strategy.copy(LocatorUtils.property(locator, "zip", sourceZip), sourceZip));
                copy.setZip(copyZip);
            } else {
                copy.zip = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Location();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof Location) {
            final Location target = this;
            final Location leftObject = ((Location) left);
            final Location rightObject = ((Location) right);
            {
                String lhsAddress;
                lhsAddress = leftObject.getAddress();
                String rhsAddress;
                rhsAddress = rightObject.getAddress();
                String mergedAddress = ((String) strategy.merge(LocatorUtils.property(leftLocator, "address", lhsAddress), LocatorUtils.property(rightLocator, "address", rhsAddress), lhsAddress, rhsAddress));
                target.setAddress(mergedAddress);
            }
            {
                String lhsCity;
                lhsCity = leftObject.getCity();
                String rhsCity;
                rhsCity = rightObject.getCity();
                String mergedCity = ((String) strategy.merge(LocatorUtils.property(leftLocator, "city", lhsCity), LocatorUtils.property(rightLocator, "city", rhsCity), lhsCity, rhsCity));
                target.setCity(mergedCity);
            }
            {
                String lhsState;
                lhsState = leftObject.getState();
                String rhsState;
                rhsState = rightObject.getState();
                String mergedState = ((String) strategy.merge(LocatorUtils.property(leftLocator, "state", lhsState), LocatorUtils.property(rightLocator, "state", rhsState), lhsState, rhsState));
                target.setState(mergedState);
            }
            {
                String lhsZip;
                lhsZip = leftObject.getZip();
                String rhsZip;
                rhsZip = rightObject.getZip();
                String mergedZip = ((String) strategy.merge(LocatorUtils.property(leftLocator, "zip", lhsZip), LocatorUtils.property(rightLocator, "zip", rhsZip), lhsZip, rhsZip));
                target.setZip(mergedZip);
            }
        }
    }

}
