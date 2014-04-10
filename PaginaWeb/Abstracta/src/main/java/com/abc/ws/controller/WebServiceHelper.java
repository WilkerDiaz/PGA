/**
 * WebServiceHelper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.abc.ws.controller;

public interface WebServiceHelper extends java.rmi.Remote {
    public com.abc.ws.bean.XxWabClassinfo[] findAll() throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo[] findProductsForGift() throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo[] findProductByLevelID(int classlvl, long id) throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabClassinfo[] findClassByLevelID(int classlvl, long id) throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo findProductById(long id) throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo[] findProductByNameDesc(java.lang.String search) throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo[] findProductByValue(java.lang.String value) throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo[] findNewProducts() throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo[] findPromoProducts() throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo[] findTrendSetProducts(int product) throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabProductinfo[] findComplementProducts(int product) throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabHomepage[] findHomePageCovers() throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabCostumerservice findCostumerServiceByName(java.lang.String name) throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabCostumerservice[] findAllCostumerService() throws java.rmi.RemoteException;
    public com.abc.ws.bean.XxWabCostumerservice[] findAllCostumerServiceReturn() throws java.rmi.RemoteException;
}
