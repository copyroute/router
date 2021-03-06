//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.22 at 06:42:51 PM EDT 
//


package org.sitemaps.schemas.sitemap._0;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sitemap" type="{http://www.sitemaps.org/schemas/sitemap/0.9}tSitemap" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "any",
    "sitemap"
})
@XmlRootElement(name = "sitemapindex")
public class Sitemapindex
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlElement(required = true)
    protected List<TSitemap> sitemap;

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
     * Gets the value of the sitemap property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sitemap property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSitemap().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSitemap }
     * 
     * 
     */
    public List<TSitemap> getSitemap() {
        if (sitemap == null) {
            sitemap = new ArrayList<TSitemap>();
        }
        return this.sitemap;
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
            List<TSitemap> theSitemap;
            theSitemap = (((this.sitemap!= null)&&(!this.sitemap.isEmpty()))?this.getSitemap():null);
            strategy.appendField(locator, this, "sitemap", buffer, theSitemap);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Sitemapindex)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Sitemapindex that = ((Sitemapindex) object);
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
            List<TSitemap> lhsSitemap;
            lhsSitemap = (((this.sitemap!= null)&&(!this.sitemap.isEmpty()))?this.getSitemap():null);
            List<TSitemap> rhsSitemap;
            rhsSitemap = (((that.sitemap!= null)&&(!that.sitemap.isEmpty()))?that.getSitemap():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sitemap", lhsSitemap), LocatorUtils.property(thatLocator, "sitemap", rhsSitemap), lhsSitemap, rhsSitemap)) {
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
            List<TSitemap> theSitemap;
            theSitemap = (((this.sitemap!= null)&&(!this.sitemap.isEmpty()))?this.getSitemap():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sitemap", theSitemap), currentHashCode, theSitemap);
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
        if (draftCopy instanceof Sitemapindex) {
            final Sitemapindex copy = ((Sitemapindex) draftCopy);
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
            if ((this.sitemap!= null)&&(!this.sitemap.isEmpty())) {
                List<TSitemap> sourceSitemap;
                sourceSitemap = (((this.sitemap!= null)&&(!this.sitemap.isEmpty()))?this.getSitemap():null);
                @SuppressWarnings("unchecked")
                List<TSitemap> copySitemap = ((List<TSitemap> ) strategy.copy(LocatorUtils.property(locator, "sitemap", sourceSitemap), sourceSitemap));
                copy.sitemap = null;
                if (copySitemap!= null) {
                    List<TSitemap> uniqueSitemapl = copy.getSitemap();
                    uniqueSitemapl.addAll(copySitemap);
                }
            } else {
                copy.sitemap = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Sitemapindex();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof Sitemapindex) {
            final Sitemapindex target = this;
            final Sitemapindex leftObject = ((Sitemapindex) left);
            final Sitemapindex rightObject = ((Sitemapindex) right);
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
                List<TSitemap> lhsSitemap;
                lhsSitemap = (((leftObject.sitemap!= null)&&(!leftObject.sitemap.isEmpty()))?leftObject.getSitemap():null);
                List<TSitemap> rhsSitemap;
                rhsSitemap = (((rightObject.sitemap!= null)&&(!rightObject.sitemap.isEmpty()))?rightObject.getSitemap():null);
                List<TSitemap> mergedSitemap = ((List<TSitemap> ) strategy.merge(LocatorUtils.property(leftLocator, "sitemap", lhsSitemap), LocatorUtils.property(rightLocator, "sitemap", rhsSitemap), lhsSitemap, rhsSitemap));
                target.sitemap = null;
                if (mergedSitemap!= null) {
                    List<TSitemap> uniqueSitemapl = target.getSitemap();
                    uniqueSitemapl.addAll(mergedSitemap);
                }
            }
        }
    }

}
