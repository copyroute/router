//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.10 at 03:11:04 PM EDT 
//


package com.copyroute.cdm.rss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for playList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="playList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataSources" type="{http://copyroute.com/cdm/rss}dataSource" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "playList", propOrder = {
    "id",
    "name",
    "dataSources"
})
public class PlayList
    implements Serializable, Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(name = "_id", required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    protected List<DataSource> dataSources;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the dataSources property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSources property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSources().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSource }
     * 
     * 
     */
    public List<DataSource> getDataSources() {
        if (dataSources == null) {
            dataSources = new ArrayList<DataSource>();
        }
        return this.dataSources;
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
            String theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId);
        }
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            List<DataSource> theDataSources;
            theDataSources = (((this.dataSources!= null)&&(!this.dataSources.isEmpty()))?this.getDataSources():null);
            strategy.appendField(locator, this, "dataSources", buffer, theDataSources);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PlayList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PlayList that = ((PlayList) object);
        {
            String lhsId;
            lhsId = this.getId();
            String rhsId;
            rhsId = that.getId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId)) {
                return false;
            }
        }
        {
            String lhsName;
            lhsName = this.getName();
            String rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            List<DataSource> lhsDataSources;
            lhsDataSources = (((this.dataSources!= null)&&(!this.dataSources.isEmpty()))?this.getDataSources():null);
            List<DataSource> rhsDataSources;
            rhsDataSources = (((that.dataSources!= null)&&(!that.dataSources.isEmpty()))?that.getDataSources():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSources", lhsDataSources), LocatorUtils.property(thatLocator, "dataSources", rhsDataSources), lhsDataSources, rhsDataSources)) {
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
            String theId;
            theId = this.getId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);
        }
        {
            String theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        {
            List<DataSource> theDataSources;
            theDataSources = (((this.dataSources!= null)&&(!this.dataSources.isEmpty()))?this.getDataSources():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dataSources", theDataSources), currentHashCode, theDataSources);
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
        if (draftCopy instanceof PlayList) {
            final PlayList copy = ((PlayList) draftCopy);
            if (this.id!= null) {
                String sourceId;
                sourceId = this.getId();
                String copyId = ((String) strategy.copy(LocatorUtils.property(locator, "id", sourceId), sourceId));
                copy.setId(copyId);
            } else {
                copy.id = null;
            }
            if (this.name!= null) {
                String sourceName;
                sourceName = this.getName();
                String copyName = ((String) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName));
                copy.setName(copyName);
            } else {
                copy.name = null;
            }
            if ((this.dataSources!= null)&&(!this.dataSources.isEmpty())) {
                List<DataSource> sourceDataSources;
                sourceDataSources = (((this.dataSources!= null)&&(!this.dataSources.isEmpty()))?this.getDataSources():null);
                @SuppressWarnings("unchecked")
                List<DataSource> copyDataSources = ((List<DataSource> ) strategy.copy(LocatorUtils.property(locator, "dataSources", sourceDataSources), sourceDataSources));
                copy.dataSources = null;
                if (copyDataSources!= null) {
                    List<DataSource> uniqueDataSourcesl = copy.getDataSources();
                    uniqueDataSourcesl.addAll(copyDataSources);
                }
            } else {
                copy.dataSources = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PlayList();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof PlayList) {
            final PlayList target = this;
            final PlayList leftObject = ((PlayList) left);
            final PlayList rightObject = ((PlayList) right);
            {
                String lhsId;
                lhsId = leftObject.getId();
                String rhsId;
                rhsId = rightObject.getId();
                String mergedId = ((String) strategy.merge(LocatorUtils.property(leftLocator, "id", lhsId), LocatorUtils.property(rightLocator, "id", rhsId), lhsId, rhsId));
                target.setId(mergedId);
            }
            {
                String lhsName;
                lhsName = leftObject.getName();
                String rhsName;
                rhsName = rightObject.getName();
                String mergedName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName));
                target.setName(mergedName);
            }
            {
                List<DataSource> lhsDataSources;
                lhsDataSources = (((leftObject.dataSources!= null)&&(!leftObject.dataSources.isEmpty()))?leftObject.getDataSources():null);
                List<DataSource> rhsDataSources;
                rhsDataSources = (((rightObject.dataSources!= null)&&(!rightObject.dataSources.isEmpty()))?rightObject.getDataSources():null);
                List<DataSource> mergedDataSources = ((List<DataSource> ) strategy.merge(LocatorUtils.property(leftLocator, "dataSources", lhsDataSources), LocatorUtils.property(rightLocator, "dataSources", rhsDataSources), lhsDataSources, rhsDataSources));
                target.dataSources = null;
                if (mergedDataSources!= null) {
                    List<DataSource> uniqueDataSourcesl = target.getDataSources();
                    uniqueDataSourcesl.addAll(mergedDataSources);
                }
            }
        }
    }

}
