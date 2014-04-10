package com.abstracta.webstore;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.ws.bean.XxWabHomepage;
import com.abc.ws.controller.WebServiceHelperProxy;
import com.abstracta.enums.Catalog;
import com.mortennobel.imagescaling.ResampleOp;


/**
 * Handles requests for images from the web app
 * @author jtrias
 */
@Controller
public class ImageController {
	
	@Autowired
	ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	/**
	 * Image Dispatcher
	 */
	@ResponseBody
	@RequestMapping(value="/Images/{target}/{w}/{h}/{id}/{number}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable("target") String target, @PathVariable("w") Integer w, 
						@PathVariable("h") Integer h, @PathVariable Integer id,
						@PathVariable("number") Integer number) {
		
		//Size Limit (TODO make it a property) 
		if(w>2000 || h>2000){
			h=2000;w=2000;
		}
		
		int width = w;
		int height = h;
		InputStream originalPhoto = null;
		String imageName = "";
		String folder = "resources/web/images/catalog/";
		String formatt = "jpg";
		
		//Could be N targets like: Item, Department, Section, Home (folders to look up for photos)
		if(target.equalsIgnoreCase("i"))
			folder += "item";
		else if(target.equalsIgnoreCase("h"))
			folder += "home";
		else if(target.equalsIgnoreCase("d"))
			folder += "department";
		else if(target.equalsIgnoreCase("s"))
			folder += "section";
		else if(target.equalsIgnoreCase("t"))
			folder += "type";
		else if(target.equalsIgnoreCase("b"))
			folder += "box";	
		
		folder += "/" + id;
		
		//Se obtiene el directorio donde estan las imagenes
		File directory = new File(servletContext.getRealPath(folder));
		
		if(directory.list()!=null && directory.list().length >= number){

			int i=0;
			for (final File fileEntry : directory.listFiles()) {

		        if(!fileEntry.isDirectory()){
		        	i++;
		        	if(i==number)
		        		imageName = fileEntry.getName();
		        }
		    }
			originalPhoto = servletContext.getResourceAsStream(folder + "/" + imageName);	
		}
		
		if(originalPhoto==null){
			imageName = "noPhoto.png";
			originalPhoto = servletContext.getResourceAsStream("resources/web/images/" + imageName);
		}
		
		ByteArrayOutputStream scaledImage = new ByteArrayOutputStream();
		
		try {
			BufferedImage imBuff = ImageIO.read(originalPhoto);	
			
			//Scale
		    ResampleOp  resampleOp = new ResampleOp (width, height);
		    BufferedImage rescaledTomato = resampleOp.filter(imBuff, null);
			
		    //Set the image to Byte Array
		    formatt = imageName.substring((imageName.indexOf("."))+1, imageName.length());
			ImageIO.write(rescaledTomato, formatt, scaledImage);
			scaledImage.flush();
			scaledImage.close();
			
		} catch (Exception e) {
			
			originalPhoto = servletContext.getResourceAsStream("resources/web/images/noPhoto.png");
			
			try {
				BufferedImage imBuff = ImageIO.read(originalPhoto);
				ResampleOp  resampleOp = new ResampleOp (width, height);
				BufferedImage rescaledTomato = resampleOp.filter(imBuff, null);
				
			    //Set the image to Byte Array
				ImageIO.write(rescaledTomato, formatt, scaledImage);
				scaledImage.flush();
				scaledImage.close();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}
		
	     return scaledImage.toByteArray();
	}
	
	/**
	 * Utils Image Dispatcher
	 */
	@ResponseBody
	@RequestMapping(value="/Utils/{name}/Src", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable("name") String name) {
		
		System.out.println("Imagen: " + name);
		
		String folder = "resources/web/images";
		InputStream originalPhoto = null;
		
		//Se obtiene el directorio donde estan las imagenes
		File directory = new File(servletContext.getRealPath(folder));
		
		if(directory.list()!=null){

			originalPhoto = servletContext.getResourceAsStream(folder + "/" + name);	
		}
		
		if(originalPhoto==null){
			originalPhoto = servletContext.getResourceAsStream("resources/web/images/noPhoto.png");
		}
		
		ByteArrayOutputStream scaledImage = new ByteArrayOutputStream();
		BufferedImage imBuff;
		try {
			imBuff = ImageIO.read(originalPhoto);
			//Set the image to Byte Array
			ImageIO.write(imBuff, "gif", scaledImage);
			scaledImage.flush();
			scaledImage.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	  
	    return scaledImage.toByteArray();
	}
	
	public String getRandomImage(){

		String image = "/Images";
		String[] catalogFolders = {Catalog.HOME.getCode()};
		Random random = new Random();
	
		String folder = catalogFolders[random.nextInt(catalogFolders.length)];
		
		image +=  "/" + folder + "/730" + "/270"; 
		
		WebServiceHelperProxy ws = new WebServiceHelperProxy();
		
		XxWabHomepage[] homeCovers = null;
		
		try {
			homeCovers = ws.findHomePageCovers();
		} catch (RemoteException e) {
			logger.error("findHomePageCovers -> getSliderImages");
			e.printStackTrace();
		}
		
		long id = homeCovers[random.nextInt(homeCovers.length)].getXxWabHomepageId();
		
		image += "/" + id ;
		
		image += "/1";
		
		return image;
	}
	
}
