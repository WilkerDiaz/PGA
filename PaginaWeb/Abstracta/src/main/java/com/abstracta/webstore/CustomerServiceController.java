package com.abstracta.webstore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.ws.bean.XxWabCostumerservice;
import com.abc.ws.controller.WebServiceHelperProxy;

/**
 * Handles Custormer Service Stuff
 * @author jtrias
 */

@Controller
public class CustomerServiceController {

	@Autowired
	ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	@RequestMapping("/Servicio-Al-Cliente/{serviceName}")
	public String costumerServices(@PathVariable String serviceName, Model model){
		
		logger.info("SAC - " + serviceName);
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadMenu(model);
		storeController.loadCostumerService(model);
		
		//Carga del Servicio Al Cliente a Consultar
		WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabCostumerservice cservice = null;
		
		try {
			cservice = ws.findCostumerServiceByName(storeController.unformatName(serviceName));
		} catch (RemoteException e) {
			logger.error("Error WebService costumerServiceByName" + e.getMessage());
		}
		
		//TODO Find the specific service
		model.addAttribute("service", cservice);
		
		return "CostumerService";
	}
	
	@RequestMapping("/Programa-De-Fidelidad/")
	public String architect(Model model){
		
		logger.info("SAC - " + "Plan de Fidelidad");
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadMenu(model);
		storeController.loadCostumerService(model);

		return "Architect";
	}
	
	@RequestMapping(value="/Programa-De-Fidelidad/planilla/{name}")
	@ResponseBody
	public byte[] saveAndShowPDF(@PathVariable String name, HttpServletRequest request, HttpServletResponse httpServletResponse){
	    
		System.out.println(name);
		
		byte[] file = null;
		try {
			file = loadFile(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}
	
	public static byte[] readFully(InputStream stream) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    int bytesRead;
	    while ((bytesRead = stream.read(buffer)) != -1)
	    {
	        baos.write(buffer, 0, bytesRead);
	    }
	    return baos.toByteArray();
	}

	public byte[] loadFile(String name) throws IOException
	{
	    InputStream inputStream = null;
	    try 
	    {
	        inputStream = servletContext.getResourceAsStream("resources/web/docs/" + name + ".pdf");
	        return readFully(inputStream);
	    } 
	    finally
	    {
	        if (inputStream != null)
	        {
	            inputStream.close();
	        }
	    }
	}
}
