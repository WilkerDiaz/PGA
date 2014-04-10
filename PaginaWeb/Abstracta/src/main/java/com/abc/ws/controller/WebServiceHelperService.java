/**
 * WebServiceHelperService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.abc.ws.controller;

public interface WebServiceHelperService extends javax.xml.rpc.Service {
    public java.lang.String getWebServiceHelperAddress();

    public com.abc.ws.controller.WebServiceHelper getWebServiceHelper() throws javax.xml.rpc.ServiceException;

    public com.abc.ws.controller.WebServiceHelper getWebServiceHelper(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
