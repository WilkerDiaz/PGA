/**
 * XxWabHomepage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.abc.ws.bean;

public class XxWabHomepage  implements java.io.Serializable {
    private java.lang.String isactive;

    private java.lang.String name;

    private java.lang.Long seqno;

    private java.lang.String url;

    private long xxWabHomepageId;

    public XxWabHomepage() {
    }

    public XxWabHomepage(
           java.lang.String isactive,
           java.lang.String name,
           java.lang.Long seqno,
           java.lang.String url,
           long xxWabHomepageId) {
           this.isactive = isactive;
           this.name = name;
           this.seqno = seqno;
           this.url = url;
           this.xxWabHomepageId = xxWabHomepageId;
    }


    /**
     * Gets the isactive value for this XxWabHomepage.
     * 
     * @return isactive
     */
    public java.lang.String getIsactive() {
        return isactive;
    }


    /**
     * Sets the isactive value for this XxWabHomepage.
     * 
     * @param isactive
     */
    public void setIsactive(java.lang.String isactive) {
        this.isactive = isactive;
    }


    /**
     * Gets the name value for this XxWabHomepage.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this XxWabHomepage.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the seqno value for this XxWabHomepage.
     * 
     * @return seqno
     */
    public java.lang.Long getSeqno() {
        return seqno;
    }


    /**
     * Sets the seqno value for this XxWabHomepage.
     * 
     * @param seqno
     */
    public void setSeqno(java.lang.Long seqno) {
        this.seqno = seqno;
    }


    /**
     * Gets the url value for this XxWabHomepage.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this XxWabHomepage.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the xxWabHomepageId value for this XxWabHomepage.
     * 
     * @return xxWabHomepageId
     */
    public long getXxWabHomepageId() {
        return xxWabHomepageId;
    }


    /**
     * Sets the xxWabHomepageId value for this XxWabHomepage.
     * 
     * @param xxWabHomepageId
     */
    public void setXxWabHomepageId(long xxWabHomepageId) {
        this.xxWabHomepageId = xxWabHomepageId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof XxWabHomepage)) return false;
        XxWabHomepage other = (XxWabHomepage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.isactive==null && other.getIsactive()==null) || 
             (this.isactive!=null &&
              this.isactive.equals(other.getIsactive()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.seqno==null && other.getSeqno()==null) || 
             (this.seqno!=null &&
              this.seqno.equals(other.getSeqno()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            this.xxWabHomepageId == other.getXxWabHomepageId();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIsactive() != null) {
            _hashCode += getIsactive().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getSeqno() != null) {
            _hashCode += getSeqno().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        _hashCode += new Long(getXxWabHomepageId()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(XxWabHomepage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bean.ws.abc.com", "XxWabHomepage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isactive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "isactive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seqno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "seqno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xxWabHomepageId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "xxWabHomepageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
