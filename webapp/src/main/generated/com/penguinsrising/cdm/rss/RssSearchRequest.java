//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.17 at 06:35:48 PM EST 
//


package com.penguinsrising.cdm.rss;

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
 * <p>Java class for rssSearchRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rssSearchRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://penguinsrising.com/cdm/rss}rssItem">
 *       &lt;sequence>
 *         &lt;element name="operation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rssSearchRequest", propOrder = {
    "operation",
    "pageNumber",
    "pageSize"
})
public class RssSearchRequest
    extends RssItem
    implements Serializable, Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected String operation;
    protected int pageNumber;
    protected int pageSize;

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the value of the pageNumber property.
     * 
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the value of the pageNumber property.
     * 
     */
    public void setPageNumber(int value) {
        this.pageNumber = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
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
            String theOperation;
            theOperation = this.getOperation();
            strategy.appendField(locator, this, "operation", buffer, theOperation);
        }
        {
            int thePageNumber;
            thePageNumber = (true?this.getPageNumber(): 0);
            strategy.appendField(locator, this, "pageNumber", buffer, thePageNumber);
        }
        {
            int thePageSize;
            thePageSize = (true?this.getPageSize(): 0);
            strategy.appendField(locator, this, "pageSize", buffer, thePageSize);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RssSearchRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final RssSearchRequest that = ((RssSearchRequest) object);
        {
            String lhsOperation;
            lhsOperation = this.getOperation();
            String rhsOperation;
            rhsOperation = that.getOperation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "operation", lhsOperation), LocatorUtils.property(thatLocator, "operation", rhsOperation), lhsOperation, rhsOperation)) {
                return false;
            }
        }
        {
            int lhsPageNumber;
            lhsPageNumber = (true?this.getPageNumber(): 0);
            int rhsPageNumber;
            rhsPageNumber = (true?that.getPageNumber(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pageNumber", lhsPageNumber), LocatorUtils.property(thatLocator, "pageNumber", rhsPageNumber), lhsPageNumber, rhsPageNumber)) {
                return false;
            }
        }
        {
            int lhsPageSize;
            lhsPageSize = (true?this.getPageSize(): 0);
            int rhsPageSize;
            rhsPageSize = (true?that.getPageSize(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pageSize", lhsPageSize), LocatorUtils.property(thatLocator, "pageSize", rhsPageSize), lhsPageSize, rhsPageSize)) {
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
            String theOperation;
            theOperation = this.getOperation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "operation", theOperation), currentHashCode, theOperation);
        }
        {
            int thePageNumber;
            thePageNumber = (true?this.getPageNumber(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pageNumber", thePageNumber), currentHashCode, thePageNumber);
        }
        {
            int thePageSize;
            thePageSize = (true?this.getPageSize(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pageSize", thePageSize), currentHashCode, thePageSize);
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
        if (draftCopy instanceof RssSearchRequest) {
            final RssSearchRequest copy = ((RssSearchRequest) draftCopy);
            if (this.operation!= null) {
                String sourceOperation;
                sourceOperation = this.getOperation();
                String copyOperation = ((String) strategy.copy(LocatorUtils.property(locator, "operation", sourceOperation), sourceOperation));
                copy.setOperation(copyOperation);
            } else {
                copy.operation = null;
            }
            int sourcePageNumber;
            sourcePageNumber = (true?this.getPageNumber(): 0);
            int copyPageNumber = strategy.copy(LocatorUtils.property(locator, "pageNumber", sourcePageNumber), sourcePageNumber);
            copy.setPageNumber(copyPageNumber);
            int sourcePageSize;
            sourcePageSize = (true?this.getPageSize(): 0);
            int copyPageSize = strategy.copy(LocatorUtils.property(locator, "pageSize", sourcePageSize), sourcePageSize);
            copy.setPageSize(copyPageSize);
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new RssSearchRequest();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof RssSearchRequest) {
            final RssSearchRequest target = this;
            final RssSearchRequest leftObject = ((RssSearchRequest) left);
            final RssSearchRequest rightObject = ((RssSearchRequest) right);
            {
                String lhsOperation;
                lhsOperation = leftObject.getOperation();
                String rhsOperation;
                rhsOperation = rightObject.getOperation();
                String mergedOperation = ((String) strategy.merge(LocatorUtils.property(leftLocator, "operation", lhsOperation), LocatorUtils.property(rightLocator, "operation", rhsOperation), lhsOperation, rhsOperation));
                target.setOperation(mergedOperation);
            }
            {
                int lhsPageNumber;
                lhsPageNumber = (true?leftObject.getPageNumber(): 0);
                int rhsPageNumber;
                rhsPageNumber = (true?rightObject.getPageNumber(): 0);
                int mergedPageNumber = ((int) strategy.merge(LocatorUtils.property(leftLocator, "pageNumber", lhsPageNumber), LocatorUtils.property(rightLocator, "pageNumber", rhsPageNumber), lhsPageNumber, rhsPageNumber));
                target.setPageNumber(mergedPageNumber);
            }
            {
                int lhsPageSize;
                lhsPageSize = (true?leftObject.getPageSize(): 0);
                int rhsPageSize;
                rhsPageSize = (true?rightObject.getPageSize(): 0);
                int mergedPageSize = ((int) strategy.merge(LocatorUtils.property(leftLocator, "pageSize", lhsPageSize), LocatorUtils.property(rightLocator, "pageSize", rhsPageSize), lhsPageSize, rhsPageSize));
                target.setPageSize(mergedPageSize);
            }
        }
    }

}
