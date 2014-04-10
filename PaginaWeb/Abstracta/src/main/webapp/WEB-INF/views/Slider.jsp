<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<div style= "height: 600px;">
<div class="slideshow-wrapper" >
		<span class="preloader"></span>
		<ul id="slide" style= "height: 600px;" data-orbit 
	            	   data-options="pause_on_hover:false;
	             					 slide_number: false;
	             					 animation: 'fade';
	             					 bullets: false;
	             					 timer: true;
	             					 timer_speed:6000;">
	     	${slider} 
	     </ul>         
</div>
</div>