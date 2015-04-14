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
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for rssItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rssItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://copyroute.com/cdm/rss}item">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rssItem", propOrder = {
    "category",
    "company",
    "channel",
    "feed",
    "tag",
    "title",
    "description",
    "uri",
    "date"
})
@XmlSeeAlso({
    RssItemArchive.class,
    RssSearchRequest.class
})
public class RssItem
    extends Item
    implements Serializable, Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String category;
    @XmlElement(required = true)
    protected String company;
    @XmlElement(required = true)
    protected String channel;
    @XmlElement(required = true)
    protected String feed;
    @XmlElement(required = true)
    protected String tag;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String uri;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * Gets the value of the feed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeed() {
        return feed;
    }

    /**
     * Sets the value of the feed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeed(String value) {
        this.feed = value;
    }

    /**
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(String value) {
        this.tag = value;
    }

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            String theCategory;
            theCategory = this.getCategory();
            strategy.appendField(locator, this, "category", buffer, theCategory);
        }
        {
            String theCompany;
            theCompany = this.getCompany();
            strategy.appendField(locator, this, "company", buffer, theCompany);
        }
        {
            String theChannel;
            theChannel = this.getChannel();
            strategy.appendField(locator, this, "channel", buffer, theChannel);
        }
        {
            String theFeed;
            theFeed = this.getFeed();
            strategy.appendField(locator, this, "feed", buffer, theFeed);
        }
        {
            String theTag;
            theTag = this.getTag();
            strategy.appendField(locator, this, "tag", buffer, theTag);
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            String theUri;
            theUri = this.getUri();
            strategy.appendField(locator, this, "uri", buffer, theUri);
        }
        {
            XMLGregorianCalendar theDate;
            theDate = this.getDate();
            strategy.appendField(locator, this, "date", buffer, theDate);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RssItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final RssItem that = ((RssItem) object);
        {
            String lhsCategory;
            lhsCategory = this.getCategory();
            String rhsCategory;
            rhsCategory = that.getCategory();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "category", lhsCategory), LocatorUtils.property(thatLocator, "category", rhsCategory), lhsCategory, rhsCategory)) {
                return false;
            }
        }
        {
            String lhsCompany;
            lhsCompany = this.getCompany();
            String rhsCompany;
            rhsCompany = that.getCompany();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "company", lhsCompany), LocatorUtils.property(thatLocator, "company", rhsCompany), lhsCompany, rhsCompany)) {
                return false;
            }
        }
        {
            String lhsChannel;
            lhsChannel = this.getChannel();
            String rhsChannel;
            rhsChannel = that.getChannel();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "channel", lhsChannel), LocatorUtils.property(thatLocator, "channel", rhsChannel), lhsChannel, rhsChannel)) {
                return false;
            }
        }
        {
            String lhsFeed;
            lhsFeed = this.getFeed();
            String rhsFeed;
            rhsFeed = that.getFeed();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "feed", lhsFeed), LocatorUtils.property(thatLocator, "feed", rhsFeed), lhsFeed, rhsFeed)) {
                return false;
            }
        }
        {
            String lhsTag;
            lhsTag = this.getTag();
            String rhsTag;
            rhsTag = that.getTag();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "tag", lhsTag), LocatorUtils.property(thatLocator, "tag", rhsTag), lhsTag, rhsTag)) {
                return false;
            }
        }
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
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            String lhsUri;
            lhsUri = this.getUri();
            String rhsUri;
            rhsUri = that.getUri();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "uri", lhsUri), LocatorUtils.property(thatLocator, "uri", rhsUri), lhsUri, rhsUri)) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsDate;
            lhsDate = this.getDate();
            XMLGregorianCalendar rhsDate;
            rhsDate = that.getDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "date", lhsDate), LocatorUtils.property(thatLocator, "date", rhsDate), lhsDate, rhsDate)) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            String theCategory;
            theCategory = this.getCategory();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "category", theCategory), currentHashCode, theCategory);
        }
        {
            String theCompany;
            theCompany = this.getCompany();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "company", theCompany), currentHashCode, theCompany);
        }
        {
            String theChannel;
            theChannel = this.getChannel();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "channel", theChannel), currentHashCode, theChannel);
        }
        {
            String theFeed;
            theFeed = this.getFeed();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "feed", theFeed), currentHashCode, theFeed);
        }
        {
            String theTag;
            theTag = this.getTag();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "tag", theTag), currentHashCode, theTag);
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "title", theTitle), currentHashCode, theTitle);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            String theUri;
            theUri = this.getUri();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "uri", theUri), currentHashCode, theUri);
        }
        {
            XMLGregorianCalendar theDate;
            theDate = this.getDate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "date", theDate), currentHashCode, theDate);
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof RssItem) {
            final RssItem copy = ((RssItem) draftCopy);
            if (this.category!= null) {
                String sourceCategory;
                sourceCategory = this.getCategory();
                String copyCategory = ((String) strategy.copy(LocatorUtils.property(locator, "category", sourceCategory), sourceCategory));
                copy.setCategory(copyCategory);
            } else {
                copy.category = null;
            }
            if (this.company!= null) {
                String sourceCompany;
                sourceCompany = this.getCompany();
                String copyCompany = ((String) strategy.copy(LocatorUtils.property(locator, "company", sourceCompany), sourceCompany));
                copy.setCompany(copyCompany);
            } else {
                copy.company = null;
            }
            if (this.channel!= null) {
                String sourceChannel;
                sourceChannel = this.getChannel();
                String copyChannel = ((String) strategy.copy(LocatorUtils.property(locator, "channel", sourceChannel), sourceChannel));
                copy.setChannel(copyChannel);
            } else {
                copy.channel = null;
            }
            if (this.feed!= null) {
                String sourceFeed;
                sourceFeed = this.getFeed();
                String copyFeed = ((String) strategy.copy(LocatorUtils.property(locator, "feed", sourceFeed), sourceFeed));
                copy.setFeed(copyFeed);
            } else {
                copy.feed = null;
            }
            if (this.tag!= null) {
                String sourceTag;
                sourceTag = this.getTag();
                String copyTag = ((String) strategy.copy(LocatorUtils.property(locator, "tag", sourceTag), sourceTag));
                copy.setTag(copyTag);
            } else {
                copy.tag = null;
            }
            if (this.title!= null) {
                String sourceTitle;
                sourceTitle = this.getTitle();
                String copyTitle = ((String) strategy.copy(LocatorUtils.property(locator, "title", sourceTitle), sourceTitle));
                copy.setTitle(copyTitle);
            } else {
                copy.title = null;
            }
            if (this.description!= null) {
                String sourceDescription;
                sourceDescription = this.getDescription();
                String copyDescription = ((String) strategy.copy(LocatorUtils.property(locator, "description", sourceDescription), sourceDescription));
                copy.setDescription(copyDescription);
            } else {
                copy.description = null;
            }
            if (this.uri!= null) {
                String sourceUri;
                sourceUri = this.getUri();
                String copyUri = ((String) strategy.copy(LocatorUtils.property(locator, "uri", sourceUri), sourceUri));
                copy.setUri(copyUri);
            } else {
                copy.uri = null;
            }
            if (this.date!= null) {
                XMLGregorianCalendar sourceDate;
                sourceDate = this.getDate();
                XMLGregorianCalendar copyDate = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "date", sourceDate), sourceDate));
                copy.setDate(copyDate);
            } else {
                copy.date = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new RssItem();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof RssItem) {
            final RssItem target = this;
            final RssItem leftObject = ((RssItem) left);
            final RssItem rightObject = ((RssItem) right);
            {
                String lhsCategory;
                lhsCategory = leftObject.getCategory();
                String rhsCategory;
                rhsCategory = rightObject.getCategory();
                String mergedCategory = ((String) strategy.merge(LocatorUtils.property(leftLocator, "category", lhsCategory), LocatorUtils.property(rightLocator, "category", rhsCategory), lhsCategory, rhsCategory));
                target.setCategory(mergedCategory);
            }
            {
                String lhsCompany;
                lhsCompany = leftObject.getCompany();
                String rhsCompany;
                rhsCompany = rightObject.getCompany();
                String mergedCompany = ((String) strategy.merge(LocatorUtils.property(leftLocator, "company", lhsCompany), LocatorUtils.property(rightLocator, "company", rhsCompany), lhsCompany, rhsCompany));
                target.setCompany(mergedCompany);
            }
            {
                String lhsChannel;
                lhsChannel = leftObject.getChannel();
                String rhsChannel;
                rhsChannel = rightObject.getChannel();
                String mergedChannel = ((String) strategy.merge(LocatorUtils.property(leftLocator, "channel", lhsChannel), LocatorUtils.property(rightLocator, "channel", rhsChannel), lhsChannel, rhsChannel));
                target.setChannel(mergedChannel);
            }
            {
                String lhsFeed;
                lhsFeed = leftObject.getFeed();
                String rhsFeed;
                rhsFeed = rightObject.getFeed();
                String mergedFeed = ((String) strategy.merge(LocatorUtils.property(leftLocator, "feed", lhsFeed), LocatorUtils.property(rightLocator, "feed", rhsFeed), lhsFeed, rhsFeed));
                target.setFeed(mergedFeed);
            }
            {
                String lhsTag;
                lhsTag = leftObject.getTag();
                String rhsTag;
                rhsTag = rightObject.getTag();
                String mergedTag = ((String) strategy.merge(LocatorUtils.property(leftLocator, "tag", lhsTag), LocatorUtils.property(rightLocator, "tag", rhsTag), lhsTag, rhsTag));
                target.setTag(mergedTag);
            }
            {
                String lhsTitle;
                lhsTitle = leftObject.getTitle();
                String rhsTitle;
                rhsTitle = rightObject.getTitle();
                String mergedTitle = ((String) strategy.merge(LocatorUtils.property(leftLocator, "title", lhsTitle), LocatorUtils.property(rightLocator, "title", rhsTitle), lhsTitle, rhsTitle));
                target.setTitle(mergedTitle);
            }
            {
                String lhsDescription;
                lhsDescription = leftObject.getDescription();
                String rhsDescription;
                rhsDescription = rightObject.getDescription();
                String mergedDescription = ((String) strategy.merge(LocatorUtils.property(leftLocator, "description", lhsDescription), LocatorUtils.property(rightLocator, "description", rhsDescription), lhsDescription, rhsDescription));
                target.setDescription(mergedDescription);
            }
            {
                String lhsUri;
                lhsUri = leftObject.getUri();
                String rhsUri;
                rhsUri = rightObject.getUri();
                String mergedUri = ((String) strategy.merge(LocatorUtils.property(leftLocator, "uri", lhsUri), LocatorUtils.property(rightLocator, "uri", rhsUri), lhsUri, rhsUri));
                target.setUri(mergedUri);
            }
            {
                XMLGregorianCalendar lhsDate;
                lhsDate = leftObject.getDate();
                XMLGregorianCalendar rhsDate;
                rhsDate = rightObject.getDate();
                XMLGregorianCalendar mergedDate = ((XMLGregorianCalendar) strategy.merge(LocatorUtils.property(leftLocator, "date", lhsDate), LocatorUtils.property(rightLocator, "date", rhsDate), lhsDate, rhsDate));
                target.setDate(mergedDate);
            }
        }
    }

}
