//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.06 at 05:08:11 PM EDT 
//


package org.sitemaps.schemas.sitemap._0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
 * 
 *         Container for the data needed to describe a sitemap.
 *       
 * 
 * <p>Java class for tSitemap complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tSitemap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loc" type="{http://www.sitemaps.org/schemas/sitemap/0.9}tLocSitemap"/>
 *         &lt;element name="lastmod" type="{http://www.sitemaps.org/schemas/sitemap/0.9}tLastmodSitemap" minOccurs="0"/>
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSitemap", propOrder = {
    "loc",
    "lastmod",
    "any"
})
public class TSitemap
    implements Serializable, Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String loc;
    protected String lastmod;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the loc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoc() {
        return loc;
    }

    /**
     * Sets the value of the loc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoc(String value) {
        this.loc = value;
    }

    /**
     * Gets the value of the lastmod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastmod() {
        return lastmod;
    }

    /**
     * Sets the value of the lastmod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastmod(String value) {
        this.lastmod = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
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
            String theLoc;
            theLoc = this.getLoc();
            strategy.appendField(locator, this, "loc", buffer, theLoc);
        }
        {
            String theLastmod;
            theLastmod = this.getLastmod();
            strategy.appendField(locator, this, "lastmod", buffer, theLastmod);
        }
        {
            List<Object> theAny;
            theAny = (((this.any!= null)&&(!this.any.isEmpty()))?this.getAny():null);
            strategy.appendField(locator, this, "any", buffer, theAny);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TSitemap)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TSitemap that = ((TSitemap) object);
        {
            String lhsLoc;
            lhsLoc = this.getLoc();
            String rhsLoc;
            rhsLoc = that.getLoc();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "loc", lhsLoc), LocatorUtils.property(thatLocator, "loc", rhsLoc), lhsLoc, rhsLoc)) {
                return false;
            }
        }
        {
            String lhsLastmod;
            lhsLastmod = this.getLastmod();
            String rhsLastmod;
            rhsLastmod = that.getLastmod();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastmod", lhsLastmod), LocatorUtils.property(thatLocator, "lastmod", rhsLastmod), lhsLastmod, rhsLastmod)) {
                return false;
            }
        }
        {
            List<Object> lhsAny;
            lhsAny = (((this.any!= null)&&(!this.any.isEmpty()))?this.getAny():null);
            List<Object> rhsAny;
            rhsAny = (((that.any!= null)&&(!that.any.isEmpty()))?that.getAny():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "any", lhsAny), LocatorUtils.property(thatLocator, "any", rhsAny), lhsAny, rhsAny)) {
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
            String theLoc;
            theLoc = this.getLoc();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loc", theLoc), currentHashCode, theLoc);
        }
        {
            String theLastmod;
            theLastmod = this.getLastmod();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastmod", theLastmod), currentHashCode, theLastmod);
        }
        {
            List<Object> theAny;
            theAny = (((this.any!= null)&&(!this.any.isEmpty()))?this.getAny():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "any", theAny), currentHashCode, theAny);
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
        if (draftCopy instanceof TSitemap) {
            final TSitemap copy = ((TSitemap) draftCopy);
            if (this.loc!= null) {
                String sourceLoc;
                sourceLoc = this.getLoc();
                String copyLoc = ((String) strategy.copy(LocatorUtils.property(locator, "loc", sourceLoc), sourceLoc));
                copy.setLoc(copyLoc);
            } else {
                copy.loc = null;
            }
            if (this.lastmod!= null) {
                String sourceLastmod;
                sourceLastmod = this.getLastmod();
                String copyLastmod = ((String) strategy.copy(LocatorUtils.property(locator, "lastmod", sourceLastmod), sourceLastmod));
                copy.setLastmod(copyLastmod);
            } else {
                copy.lastmod = null;
            }
            if ((this.any!= null)&&(!this.any.isEmpty())) {
                List<Object> sourceAny;
                sourceAny = (((this.any!= null)&&(!this.any.isEmpty()))?this.getAny():null);
                @SuppressWarnings("unchecked")
                List<Object> copyAny = ((List<Object> ) strategy.copy(LocatorUtils.property(locator, "any", sourceAny), sourceAny));
                copy.any = null;
                if (copyAny!= null) {
                    List<Object> uniqueAnyl = copy.getAny();
                    uniqueAnyl.addAll(copyAny);
                }
            } else {
                copy.any = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TSitemap();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof TSitemap) {
            final TSitemap target = this;
            final TSitemap leftObject = ((TSitemap) left);
            final TSitemap rightObject = ((TSitemap) right);
            {
                String lhsLoc;
                lhsLoc = leftObject.getLoc();
                String rhsLoc;
                rhsLoc = rightObject.getLoc();
                String mergedLoc = ((String) strategy.merge(LocatorUtils.property(leftLocator, "loc", lhsLoc), LocatorUtils.property(rightLocator, "loc", rhsLoc), lhsLoc, rhsLoc));
                target.setLoc(mergedLoc);
            }
            {
                String lhsLastmod;
                lhsLastmod = leftObject.getLastmod();
                String rhsLastmod;
                rhsLastmod = rightObject.getLastmod();
                String mergedLastmod = ((String) strategy.merge(LocatorUtils.property(leftLocator, "lastmod", lhsLastmod), LocatorUtils.property(rightLocator, "lastmod", rhsLastmod), lhsLastmod, rhsLastmod));
                target.setLastmod(mergedLastmod);
            }
            {
                List<Object> lhsAny;
                lhsAny = (((leftObject.any!= null)&&(!leftObject.any.isEmpty()))?leftObject.getAny():null);
                List<Object> rhsAny;
                rhsAny = (((rightObject.any!= null)&&(!rightObject.any.isEmpty()))?rightObject.getAny():null);
                List<Object> mergedAny = ((List<Object> ) strategy.merge(LocatorUtils.property(leftLocator, "any", lhsAny), LocatorUtils.property(rightLocator, "any", rhsAny), lhsAny, rhsAny));
                target.any = null;
                if (mergedAny!= null) {
                    List<Object> uniqueAnyl = target.getAny();
                    uniqueAnyl.addAll(mergedAny);
                }
            }
        }
    }

}
