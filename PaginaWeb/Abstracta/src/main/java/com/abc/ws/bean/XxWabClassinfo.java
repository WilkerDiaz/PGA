/**
 * XxWabClassinfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.abc.ws.bean;

public class XxWabClassinfo  implements java.io.Serializable {
    private long classlevel1Id;

    private java.lang.String classlevel1Name;

    private long classlevel2Id;

    private java.lang.String classlevel2Name;

    private long classlevel3Id;

    private java.lang.String classlevel3Name;

    public XxWabClassinfo() {
    }

    public XxWabClassinfo(
           long classlevel1Id,
           java.lang.String classlevel1Name,
           long classlevel2Id,
           java.lang.String classlevel2Name,
           long classlevel3Id,
           java.lang.String classlevel3Name) {
           this.classlevel1Id = classlevel1Id;
           this.classlevel1Name = classlevel1Name;
           this.classlevel2Id = classlevel2Id;
           this.classlevel2Name = classlevel2Name;
           this.classlevel3Id = classlevel3Id;
           this.classlevel3Name = classlevel3Name;
    }
    
    /**
     * Gets the classlevelZName value formatted to replace %20.
     * 
     * @return classlevelZName
     */
    public String getClasslevel1NameFormatted() {
        
    	return getClasslevel1Name().replace(" ", "-");
    }
    
    /**
     * Gets the classlevelZName value formatted to replace %20.
     * 
     * @return classlevelZName
     */
    public String getClasslevel2NameFormatted() {
        
    	if(getClasslevel2Name()==null)
    		return "";
    	
    	return getClasslevel2Name().replace(" ", "-");
    }
    
    /**
     * Gets the classlevelZName value formatted to replace %20.
     * 
     * @return classlevelZName
     */
    public String getClasslevel3NameFormatted() {
        
       	if(getClasslevel3Name()==null)
    		return "";
    	
    	return getClasslevel3Name().replace(" ", "-");
    }

    /**
     * Gets the classlevel1Id value for this XxWabClassinfo.
     * 
     * @return classlevel1Id
     */
    public long getClasslevel1Id() {
        return classlevel1Id;
    }


    /**
     * Sets the classlevel1Id value for this XxWabClassinfo.
     * 
     * @param classlevel1Id
     */
    public void setClasslevel1Id(long classlevel1Id) {
        this.classlevel1Id = classlevel1Id;
    }


    /**
     * Gets the classlevel1Name value for this XxWabClassinfo.
     * 
     * @return classlevel1Name
     */
    public java.lang.String getClasslevel1Name() {
        return classlevel1Name;
    }


    /**
     * Sets the classlevel1Name value for this XxWabClassinfo.
     * 
     * @param classlevel1Name
     */
    public void setClasslevel1Name(java.lang.String classlevel1Name) {
        this.classlevel1Name = classlevel1Name.toLowerCase();
    }


    /**
     * Gets the classlevel2Id value for this XxWabClassinfo.
     * 
     * @return classlevel2Id
     */
    public long getClasslevel2Id() {
        return classlevel2Id;
    }


    /**
     * Sets the classlevel2Id value for this XxWabClassinfo.
     * 
     * @param classlevel2Id
     */
    public void setClasslevel2Id(long classlevel2Id) {
        this.classlevel2Id = classlevel2Id;
    }


    /**
     * Gets the classlevel2Name value for this XxWabClassinfo.
     * 
     * @return classlevel2Name
     */
    public java.lang.String getClasslevel2Name() {
        return classlevel2Name;
    }


    /**
     * Sets the classlevel2Name value for this XxWabClassinfo.
     * 
     * @param classlevel2Name
     */
    public void setClasslevel2Name(java.lang.String classlevel2Name) {
        this.classlevel2Name = classlevel2Name.toLowerCase();
    }


    /**
     * Gets the classlevel3Id value for this XxWabClassinfo.
     * 
     * @return classlevel3Id
     */
    public long getClasslevel3Id() {
        return classlevel3Id;
    }


    /**
     * Sets the classlevel3Id value for this XxWabClassinfo.
     * 
     * @param classlevel3Id
     */
    public void setClasslevel3Id(long classlevel3Id) {
        this.classlevel3Id = classlevel3Id;
    }


    /**
     * Gets the classlevel3Name value for this XxWabClassinfo.
     * 
     * @return classlevel3Name
     */
    public java.lang.String getClasslevel3Name() {
        return classlevel3Name;
    }
    
    

    /**
     * Sets the classlevel3Name value for this XxWabClassinfo.
     * 
     * @param classlevel3Name
     */
    public void setClasslevel3Name(java.lang.String classlevel3Name) {
        this.classlevel3Name = classlevel3Name.toLowerCase();
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof XxWabClassinfo)) return false;
        XxWabClassinfo other = (XxWabClassinfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.classlevel1Id == other.getClasslevel1Id() &&
            ((this.classlevel1Name==null && other.getClasslevel1Name()==null) || 
             (this.classlevel1Name!=null &&
              this.classlevel1Name.equals(other.getClasslevel1Name()))) &&
            this.classlevel2Id == other.getClasslevel2Id() &&
            ((this.classlevel2Name==null && other.getClasslevel2Name()==null) || 
             (this.classlevel2Name!=null &&
              this.classlevel2Name.equals(other.getClasslevel2Name()))) &&
            this.classlevel3Id == other.getClasslevel3Id() &&
            ((this.classlevel3Name==null && other.getClasslevel3Name()==null) || 
             (this.classlevel3Name!=null &&
              this.classlevel3Name.equals(other.getClasslevel3Name())));
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
        _hashCode += new Long(getClasslevel1Id()).hashCode();
        if (getClasslevel1Name() != null) {
            _hashCode += getClasslevel1Name().hashCode();
        }
        _hashCode += new Long(getClasslevel2Id()).hashCode();
        if (getClasslevel2Name() != null) {
            _hashCode += getClasslevel2Name().hashCode();
        }
        _hashCode += new Long(getClasslevel3Id()).hashCode();
        if (getClasslevel3Name() != null) {
            _hashCode += getClasslevel3Name().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(XxWabClassinfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bean.ws.abc.com", "XxWabClassinfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classlevel1Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "classlevel1Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classlevel1Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "classlevel1Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classlevel2Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "classlevel2Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classlevel2Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "classlevel2Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classlevel3Id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "classlevel3Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classlevel3Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bean.ws.abc.com", "classlevel3Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
