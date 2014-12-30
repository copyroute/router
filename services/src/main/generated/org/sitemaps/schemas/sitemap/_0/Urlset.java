//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.17 at 04:43:11 PM EST 
//


package org.sitemaps.schemas.sitemap._0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.sitemaps.org/schemas/sitemap/0.9}tUrl" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "any",
    "url"
})
@XmlRootElement(name = "urlset")
public class Urlset
    implements Serializable, Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlElement(required = true)
    protected List<TUrl> url;

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

    /**
     * Gets the value of the url property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the url property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TUrl }
     * 
     * 
     */
    public List<TUrl> getUrl() {
        if (url == null) {
            url = new ArrayList<TUrl>();
        }
        return this.url;
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
            List<Object> theAny;
            theAny = (((this.any!= null)&&(!this.any.isEmpty()))?this.getAny():null);
            strategy.appendField(locator, this, "any", buffer, theAny);
        }
        {
            List<TUrl> theUrl;
            theUrl = (((this.url!= null)&&(!this.url.isEmpty()))?this.getUrl():null);
            strategy.appendField(locator, this, "url", buffer, theUrl);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Urlset)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Urlset that = ((Urlset) object);
        {
            List<Object> lhsAny;
            lhsAny = (((this.any!= null)&&(!this.any.isEmpty()))?this.getAny():null);
            List<Object> rhsAny;
            rhsAny = (((that.any!= null)&&(!that.any.isEmpty()))?that.getAny():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "any", lhsAny), LocatorUtils.property(thatLocator, "any", rhsAny), lhsAny, rhsAny)) {
                return false;
            }
        }
        {
            List<TUrl> lhsUrl;
            lhsUrl = (((this.url!= null)&&(!this.url.isEmpty()))?this.getUrl():null);
            List<TUrl> rhsUrl;
            rhsUrl = (((that.url!= null)&&(!that.url.isEmpty()))?that.getUrl():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "url", lhsUrl), LocatorUtils.property(thatLocator, "url", rhsUrl), lhsUrl, rhsUrl)) {
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
            List<Object> theAny;
            theAny = (((this.any!= null)&&(!this.any.isEmpty()))?this.getAny():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "any", theAny), currentHashCode, theAny);
        }
        {
            List<TUrl> theUrl;
            theUrl = (((this.url!= null)&&(!this.url.isEmpty()))?this.getUrl():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "url", theUrl), currentHashCode, theUrl);
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
        if (draftCopy instanceof Urlset) {
            final Urlset copy = ((Urlset) draftCopy);
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
            if ((this.url!= null)&&(!this.url.isEmpty())) {
                List<TUrl> sourceUrl;
                sourceUrl = (((this.url!= null)&&(!this.url.isEmpty()))?this.getUrl():null);
                @SuppressWarnings("unchecked")
                List<TUrl> copyUrl = ((List<TUrl> ) strategy.copy(LocatorUtils.property(locator, "url", sourceUrl), sourceUrl));
                copy.url = null;
                if (copyUrl!= null) {
                    List<TUrl> uniqueUrll = copy.getUrl();
                    uniqueUrll.addAll(copyUrl);
                }
            } else {
                copy.url = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Urlset();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof Urlset) {
            final Urlset target = this;
            final Urlset leftObject = ((Urlset) left);
            final Urlset rightObject = ((Urlset) right);
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
            {
                List<TUrl> lhsUrl;
                lhsUrl = (((leftObject.url!= null)&&(!leftObject.url.isEmpty()))?leftObject.getUrl():null);
                List<TUrl> rhsUrl;
                rhsUrl = (((rightObject.url!= null)&&(!rightObject.url.isEmpty()))?rightObject.getUrl():null);
                List<TUrl> mergedUrl = ((List<TUrl> ) strategy.merge(LocatorUtils.property(leftLocator, "url", lhsUrl), LocatorUtils.property(rightLocator, "url", rhsUrl), lhsUrl, rhsUrl));
                target.url = null;
                if (mergedUrl!= null) {
                    List<TUrl> uniqueUrll = target.getUrl();
                    uniqueUrll.addAll(mergedUrl);
                }
            }
        }
    }

}
