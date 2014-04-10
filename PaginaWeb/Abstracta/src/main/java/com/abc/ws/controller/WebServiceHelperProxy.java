package com.abc.ws.controller;

public class WebServiceHelperProxy implements com.abc.ws.controller.WebServiceHelper {
  private String _endpoint = null;
  private com.abc.ws.controller.WebServiceHelper webServiceHelper = null;
  
  public WebServiceHelperProxy() {
    _initWebServiceHelperProxy();
  }
  
  public WebServiceHelperProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceHelperProxy();
  }
  
  private void _initWebServiceHelperProxy() {
    try {
      webServiceHelper = (new com.abc.ws.controller.WebServiceHelperServiceLocator()).getWebServiceHelper();
      if (webServiceHelper != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceHelper)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceHelper != null)
      ((javax.xml.rpc.Stub)webServiceHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.abc.ws.controller.WebServiceHelper getWebServiceHelper() {
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper;
  }
  
  public com.abc.ws.bean.XxWabClassinfo[] findAll() throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findAll();
  }
  
  public com.abc.ws.bean.XxWabProductinfo[] findProductsForGift() throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findProductsForGift();
  }
  
  public com.abc.ws.bean.XxWabProductinfo[] findProductByLevelID(int classlvl, long id) throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findProductByLevelID(classlvl, id);
  }
  
  public com.abc.ws.bean.XxWabClassinfo[] findClassByLevelID(int classlvl, long id) throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findClassByLevelID(classlvl, id);
  }
  
  public com.abc.ws.bean.XxWabProductinfo findProductById(long id) throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findProductById(id);
  }
  
  public com.abc.ws.bean.XxWabProductinfo[] findProductByNameDesc(java.lang.String search) throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findProductByNameDesc(search);
  }
  
  public com.abc.ws.bean.XxWabProductinfo[] findProductByValue(java.lang.String value) throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findProductByValue(value);
  }
  
  public com.abc.ws.bean.XxWabProductinfo[] findNewProducts() throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findNewProducts();
  }
  
  public com.abc.ws.bean.XxWabProductinfo[] findPromoProducts() throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findPromoProducts();
  }
  
  public com.abc.ws.bean.XxWabProductinfo[] findTrendSetProducts(int product) throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findTrendSetProducts(product);
  }
  
  public com.abc.ws.bean.XxWabProductinfo[] findComplementProducts(int product) throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findComplementProducts(product);
  }
  
  public com.abc.ws.bean.XxWabHomepage[] findHomePageCovers() throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findHomePageCovers();
  }
  
  public com.abc.ws.bean.XxWabCostumerservice findCostumerServiceByName(java.lang.String name) throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findCostumerServiceByName(name);
  }
  
  public com.abc.ws.bean.XxWabCostumerservice[] findAllCostumerService() throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findAllCostumerService();
  }
  
  public com.abc.ws.bean.XxWabCostumerservice[] findAllCostumerServiceReturn() throws java.rmi.RemoteException{
    if (webServiceHelper == null)
      _initWebServiceHelperProxy();
    return webServiceHelper.findAllCostumerServiceReturn();
  }
  
  
}