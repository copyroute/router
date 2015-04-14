//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.14 at 12:46:51 AM EDT 
//


package com.copyroute.cdm.rss;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
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
 * <p>Java class for SampleContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SampleContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SampleContent", propOrder = {
    "title",
    "url",
    "summary",
    "createdDate"
})
public class SampleContent
    implements Serializable, Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(required = true)
    protected String summary;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar createdDate;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
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
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle);
        }
        {
            String theUrl;
            theUrl = this.getUrl();
            strategy.appendField(locator, this, "url", buffer, theUrl);
        }
        {
            String theSummary;
            theSummary = this.getSummary();
            strategy.appendField(locator, this, "summary", buffer, theSummary);
        }
        {
            XMLGregorianCalendar theCreatedDate;
            theCreatedDate = this.getCreatedDate();
            strategy.appendField(locator, this, "createdDate", buffer, theCreatedDate);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SampleContent)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SampleContent that = ((SampleContent) object);
        {
            String lhsTitle;
            lhsTitle = this.getTitle();
            String rhsTitle;
            rhsTitle = that.getTitle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "title", lhsTitle), LocatorUtils.property(thatLocator, "title", rhsTitle), lhsTitle, rhsTitle)) {
                return false;
            }
        }
        {
            String lhsUrl;
            lhsUrl = this.getUrl();
            String rhsUrl;
            rhsUrl = that.getUrl();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "url", lhsUrl), LocatorUtils.property(thatLocator, "url", rhsUrl), lhsUrl, rhsUrl)) {
                return false;
            }
        }
        {
            String lhsSummary;
            lhsSummary = this.getSummary();
            String rhsSummary;
            rhsSummary = that.getSummary();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "summary", lhsSummary), LocatorUtils.property(thatLocator, "summary", rhsSummary), lhsSummary, rhsSummary)) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsCreatedDate;
            lhsCreatedDate = this.getCreatedDate();
            XMLGregorianCalendar rhsCreatedDate;
            rhsCreatedDate = that.getCreatedDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "createdDate", lhsCreatedDate), LocatorUtils.property(thatLocator, "createdDate", rhsCreatedDate), lhsCreatedDate, rhsCreatedDate)) {
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
            String theTitle;
            theTitle = this.getTitle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "title", theTitle), currentHashCode, theTitle);
        }
        {
            String theUrl;
            theUrl = this.getUrl();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "url", theUrl), currentHashCode, theUrl);
        }
        {
            String theSummary;
            theSummary = this.getSummary();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "summary", theSummary), currentHashCode, theSummary);
        }
        {
            XMLGregorianCalendar theCreatedDate;
            theCreatedDate = this.getCreatedDate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "createdDate", theCreatedDate), currentHashCode, theCreatedDate);
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
        if (draftCopy instanceof SampleContent) {
            final SampleContent copy = ((SampleContent) draftCopy);
            if (this.title!= null) {
                String sourceTitle;
                sourceTitle = this.getTitle();
                String copyTitle = ((String) strategy.copy(LocatorUtils.property(locator, "title", sourceTitle), sourceTitle));
                copy.setTitle(copyTitle);
            } else {
                copy.title = null;
            }
            if (this.url!= null) {
                String sourceUrl;
                sourceUrl = this.getUrl();
                String copyUrl = ((String) strategy.copy(LocatorUtils.property(locator, "url", sourceUrl), sourceUrl));
                copy.setUrl(copyUrl);
            } else {
                copy.url = null;
            }
            if (this.summary!= null) {
                String sourceSummary;
                sourceSummary = this.getSummary();
                String copySummary = ((String) strategy.copy(LocatorUtils.property(locator, "summary", sourceSummary), sourceSummary));
                copy.setSummary(copySummary);
            } else {
                copy.summary = null;
            }
            if (this.createdDate!= null) {
                XMLGregorianCalendar sourceCreatedDate;
                sourceCreatedDate = this.getCreatedDate();
                XMLGregorianCalendar copyCreatedDate = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "createdDate", sourceCreatedDate), sourceCreatedDate));
                copy.setCreatedDate(copyCreatedDate);
            } else {
                copy.createdDate = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new SampleContent();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SampleContent) {
            final SampleContent target = this;
            final SampleContent leftObject = ((SampleContent) left);
            final SampleContent rightObject = ((SampleContent) right);
            {
                String lhsTitle;
                lhsTitle = leftObject.getTitle();
                String rhsTitle;
                rhsTitle = rightObject.getTitle();
                String mergedTitle = ((String) strategy.merge(LocatorUtils.property(leftLocator, "title", lhsTitle), LocatorUtils.property(rightLocator, "title", rhsTitle), lhsTitle, rhsTitle));
                target.setTitle(mergedTitle);
            }
            {
                String lhsUrl;
                lhsUrl = leftObject.getUrl();
                String rhsUrl;
                rhsUrl = rightObject.getUrl();
                String mergedUrl = ((String) strategy.merge(LocatorUtils.property(leftLocator, "url", lhsUrl), LocatorUtils.property(rightLocator, "url", rhsUrl), lhsUrl, rhsUrl));
                target.setUrl(mergedUrl);
            }
            {
                String lhsSummary;
                lhsSummary = leftObject.getSummary();
                String rhsSummary;
                rhsSummary = rightObject.getSummary();
                String mergedSummary = ((String) strategy.merge(LocatorUtils.property(leftLocator, "summary", lhsSummary), LocatorUtils.property(rightLocator, "summary", rhsSummary), lhsSummary, rhsSummary));
                target.setSummary(mergedSummary);
            }
            {
                XMLGregorianCalendar lhsCreatedDate;
                lhsCreatedDate = leftObject.getCreatedDate();
                XMLGregorianCalendar rhsCreatedDate;
                rhsCreatedDate = rightObject.getCreatedDate();
                XMLGregorianCalendar mergedCreatedDate = ((XMLGregorianCalendar) strategy.merge(LocatorUtils.property(leftLocator, "createdDate", lhsCreatedDate), LocatorUtils.property(rightLocator, "createdDate", rhsCreatedDate), lhsCreatedDate, rhsCreatedDate));
                target.setCreatedDate(mergedCreatedDate);
            }
        }
    }

}
