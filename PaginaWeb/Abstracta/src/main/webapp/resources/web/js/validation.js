$(document).ready(function() {
	
	datepickerSetUp();

	//*** Orden de Los Productos del Show Room
	var mylist = $('#sortable');
    var listitems = mylist.children('li').get();
    
    $("#sort_Asc").click(function(){
  
    	var button = $(".orderBy").get(0);
    	if(button.text == "Nombre (Asc)"){
    		$(".orderByDrop").removeClass( "open");
    		$(".orderByDrop").css( "left","-9999px");
    		return;
    	}
    	    	
    	$(".orderBy").text("Nombre (Asc)");
    	$(".orderByDrop").removeClass( "open");
    	$(".orderByDrop").css( "left","-9999px");
    	
        listitems.sort(function(a, b) {
           return $(a).text().toUpperCase().localeCompare($(b).text().toUpperCase());
        });
        $.each(listitems, function(idx, itm) { mylist.append(itm); });	
    }); 
    	    
    $("#sort_Desc").click(function(){
    	
    	var button = $(".orderBy").get(0);
    	if(button.text == "Nombre (Desc)"){
    		$(".orderByDrop").removeClass( "open");
    		$(".orderByDrop").css( "left","-9999px");
    		return;
    	}
    	
    	$(".orderBy").text("Nombre (Desc)");
    	$(".orderByDrop").removeClass( "open");
    	$(".orderByDrop").css( "left","-9999px");
    	
        listitems.sort(function(a, b) {
           return $(b).text().toUpperCase().localeCompare($(a).text().toUpperCase());
        });
        $.each(listitems, function(idx, itm) { mylist.append(itm); });
    }); 
    	    
    $("#sort_Price1").click(function(){
    	
    	var button = $(".orderBy").get(0);
    	if(button.text == "Precio (Asc)"){
    		$(".orderByDrop").removeClass( "open");
    		$(".orderByDrop").css( "left","-9999px");
    		return;
    	}
    	
    	$(".orderBy").text("Precio (Asc)");
    	$(".orderByDrop").removeClass( "open");
    	$(".orderByDrop").css( "left","-9999px");
    	
        listitems.sort(function(a, b) {

            var valueA = $(a).find("#price").val();
            var valueB = $(b).find("#price").val();
 
	        if ((valueA-valueB)<0)
	        	return -1;
	        if ((valueA-valueB)>0) 
	           return 1;

	        return 0;
     	  });
     	  $.each(listitems, function(idx, itm) { mylist.append(itm);});	
    }); 
    	    
    $("#sort_Price2").click(function(){
    	
    	var button = $(".orderBy").get(0);
    	if(button.text == "Precio (Desc)"){
    		$(".orderByDrop").removeClass( "open");
    		$(".orderByDrop").css( "left","-9999px");
    		return;
    	}
    	
    	$(".orderBy").text("Precio (Desc)");
    	$(".orderByDrop").removeClass( "open");
    	$(".orderByDrop").css( "left","-9999px");
    	
    	listitems.sort(function(a, b) {

           var valueA = $(a).find("#price").val();
           var valueB = $(b).find("#price").val();
  
     	    if ((valueA-valueB)>0)
     	    	return -1;
     	    if ((valueA-valueB)<0) 
     	    	return 1;

     	    return 0;
      	 });
      	 $.each(listitems, function(idx, itm) { mylist.append(itm);});	
    }); 
    	    
    $("img").bind("mouseenter mouseleave", function() {
			    
    	if($(this).attr("data-other-src")==null)
    		return;
    	
    	$(this).attr({
	        src: $(this).attr("data-other-src") 
	        , "data-other-src": $(this).attr("src") 
	    });
	});
    	   
    //** ZOOM	   
    var myLeft = 0; var myTop = 0;
    var bigImageWidth = 500; var bigImageHeight = 500;
    //Changes the thumbnailed pics of the product for the big photo
    $(".thumb").click(function(event) {
    	
    	bigImageWidth = 500; 
    	bigImageHeight = 500;
    	myLeft = 0;
    	myTop = 0;
    	$("#bigImage").attr("src", $(this).attr("data-big-src"));
    	$("#bigImage").attr("data-info-src", $(this).attr("data-info-src"));
    	$("#bigImage").removeAttr('style');
    	$("#bigImage").draggable("destroy");
    });
    	    
    var zoomed = false;
    
    //Zoom In Item Photo
    $(".imgZoomIn").click(function(event) {
    	
    	if(bigImageWidth>=2000)
    		return;
    	
    	bigImageWidth = bigImageWidth + 500;
    	bigImageHeight = bigImageHeight + 500;
    	
    	myLeft = myLeft + 250;
    	myTop = myTop + 250;
    
    	
    	$("#bigImage").animate({
    	    width: bigImageWidth,
    	    height: bigImageHeight,
    	    left: -myLeft,
    	    top: -myTop
    	  }, {
		    queue:    false,
		    duration: 1500,
		    complete: function() {
		    	
    	    	zoomed = true;
    	    
    	    	$("#bigImage").attr("src", "/Images/i/"+bigImageWidth+"/"+bigImageHeight+"/"+$("#bigImage").attr("data-info-src"));
		    }
		});	
	    	
	    	$( "#bigImage" ).draggable();
	    	$("#bigImage").css({"max-width":"none","cursor": "move"});
	    	
	    });
    	    
    
    //Zoom Out Item Photo
    $(".imgZoomOut").click(function(event) {
    	
    	if(zoomed == false)
    		return;
    	
    	if(bigImageWidth<=500){
	    	return;
    	}
    	    	    	
    	bigImageWidth = bigImageWidth - 500;
    	bigImageHeight = bigImageHeight - 500;
    	
    	myLeft = myLeft - 250;
    	myTop = myTop - 250;
    	
    	$("#bigImage").animate({
    	    width: bigImageWidth,
    	    height: bigImageHeight,
    	    left: -myLeft,
    	    top: -myTop
    	  }, {
    		    queue:    false,
    		    duration: 1500,
    		    complete: function() {
    		    	if(bigImageWidth<=500){
        	    		$("#bigImage").removeAttr('style');
        	    		$("#bigImage").draggable("destroy");
        	    	}
    		    	$("#bigImage").attr("src", "/Images/i/"+bigImageWidth+"/"+bigImageHeight+"/"+$("#bigImage").attr("data-info-src"));
    		    }
    		});	
    	
    	});
    	    
	    //** Verifica  el textfield del search (client-side) (boton)
	    $("#searchButton").click(function()          
	    {                   
		    if( !$("#searchtext").val() ) {                
		         return false;   
		    }
	    });
	    
    	    
	    //** Back-to-top
	    var offset = 400;
	    var duration = 700;
	    jQuery(window).scroll(function() {
	        if (jQuery(this).scrollTop() > offset) {
	            jQuery('.go-top').fadeIn(duration);
	        } else {
	            jQuery('.go-top').fadeOut(duration);
	        }
	    });
    	    
	    jQuery('.back-to-top').click(function(event) {
	        event.preventDefault();
	        jQuery('html, body').animate({scrollTop: 0}, duration);
	        return false;
	    });
	    
	    
	    //Cantidad en Carrito
	    var count=getCookie("BasketCnt");
	    if (count!=null && count!="")
	    {
	    	$("#basketCount").text(count);
	    }
	    
	    //delete record item cart
	    $(document).on("click", ".removeItem", function(){
	    	removeItem(this);
	    });
	    
	    //update qty on cart item
	    $(document).on("click", ".updateQty", function(){
		    updateItem(this);
	    });

	    //move item to favorites
	    $(document).on("click", ".moveItemToWishList", function(){
	    	moveItemToWishList(this);
	    });
	    
	    //move item to cart
	    $(document).on("click", ".moveItemToCart", function(){
	    	moveItemToCart(this);
	    });
	    
	    //remove item from wish list
	    $(document).on("click", ".removeItemFromWishList", function(){
	    	removeItemFromWishList(this);
	    });
}); 


function addItem() {
	
    // get the form values
    var myItemId =  $('#addItem').attr("data-item-id");
    var myItemQty = $('#addItem').prev().val();
	
    // url with custom callbacks
    $('#addItemReveal').foundation('reveal', 'open', {
    	type: "POST",
    	url: "/AddItem",
    	data: {itemId: myItemId, itemQty: myItemQty},
        success: function(data) {
        	var count=getCookie("BasketCnt");
        	if (count!=null && count!="" && count>0)
        	{
        		$("#basketCount").text(count);
        	}
        	else{
        		$("#basketCount").text(myItemQty);
        	}
        	
        },
        error: function() {
            alert('failed loading modal');
        }
    });
}

var mutex = false;

function removeItem(element) {
	
	if(mutex){
		return;
	}
	mutex = true;
    
	var myItemId =  $(element).closest("#cartline").attr("data-item-id");
	
    $.ajax({
    type: "POST",
    url: "/RemoveItem",
    data: "itemId=" + myItemId,
    success: function(response){
    	
    	// we have the response
    	var total = formatNumber(JSON.parse(response.total));
    	var merchandise = formatNumber(JSON.parse(response.merchandise));
    	var tax = formatNumber(JSON.parse(response.tax));
    	var shipping = formatNumber(JSON.parse(response.shipping));
    	
    	var merchandisePlain = JSON.parse(response.merchandise);
    	
		$('#total').html(total);
		$('#merchandise').html(merchandise);
		$('#tax').html(tax);
		$('#shipping').html(shipping);
		
		$(element).closest("#cartline").fadeOut(1000, function(){ $(this).remove(); mutex=false;});
		
		if(merchandisePlain==0){
			$("#cartDetail").delay(100).fadeOut(500);
			$("#noItems").delay(500).fadeIn(1000);
		}
		
    },
    error: function(e){
    	alert('Error: ' + e);
    	mutex=false;
    }
    });
}

/*
 * Cart Item Qty
 */
function updateItem(element) {

	var myItemId =  $(element).closest("#cartline").attr("data-item-id");
	var myItemQty = $(element).parent().find("#qty").val();
	
	if(myItemQty==""){
		$(element).parent().find("#qty").val("0");
		myItemQty = 0;
	}
	
	if(myItemQty==0){
		
		removeItem(element);
		return;
	}
		
    $.ajax({
    type: "POST",
    url: "/UpdateItem",
    data: {itemId: myItemId, itemQty: myItemQty},
    success: function(response){
    	
    	var itemTotal = formatNumber(JSON.parse(response.itemPrice));
    	var total = formatNumber(JSON.parse(response.total));
    	var merchandise = formatNumber(JSON.parse(response.merchandise));
    	var tax = formatNumber(JSON.parse(response.tax));
    	var shipping = formatNumber(JSON.parse(response.shipping));

    	$(element).parent().parent().parent().siblings().find("#itemTotal").text(itemTotal);
		$('#total').html(total);
		$('#merchandise').html(merchandise);
		$('#tax').html(tax);
		$('#shipping').html(shipping);
    },
    error: function(e){
    	alert('Error: ' + e);
    }
    });
}

/*
 * move Item to Wish List
 */
function moveItemToWishList(element) {

	var myCartLineID =  $(element).closest("#cartline").attr("data-item-id");

    $.ajax({
    type: "POST",
    url: "/Cuenta/MoveItemToWishListAjax",
    data: {cartLineID: myCartLineID},
    success: function(response){

    	if(response!=null){

    		if(response.moveToWishList === undefined){
    			
    			$(element).attr("href", "/Cuenta/MoveItemToWishList?cartLine="+myCartLineID);
    			$(element).removeClass("moveItemToWishList");
    			alert("Tu sesi\u00F3n expir\u00F3, intenta nuevamente.");
    			return;
    		}
    		
    		$(element).closest("#cartline").children("td").children("div").animate(
    			    			{
    			    			 height: '4px',
    			    			 opacity: 0
    			    			}, 1000,  
    			    			function(){
    			    				var wishListMsg = response.moveToWishList;
    			    				$(element).closest("#cartline").css("opacity","0");
    			    				$(element).closest("#cartline").html("<td colspan=\"5\"><div>"+ wishListMsg +"</div></td>");  
    			    			}
    			    			);  
    		
	    	$(element).closest("#cartline").delay(1000).animate(
	    			{
	    				opacity: 1
		    		}, 1000);
	    	
	    	$(element).closest("#cartline").delay(5000).fadeOut(1000, function(){ 
	    		
	    		$(this).remove(); mutex=false;
	    	});
	    	
	    	
	    	// update Totals
	    	var total = formatNumber(JSON.parse(response.total));
	    	var merchandise = formatNumber(JSON.parse(response.merchandise));
	    	var tax = formatNumber(JSON.parse(response.tax));
	    	var shipping = formatNumber(JSON.parse(response.shipping));
	    	var merchandisePlain = JSON.parse(response.merchandise);
	    	
			$('#total').html(total);
			$('#merchandise').html(merchandise);
			$('#tax').html(tax);
			$('#shipping').html(shipping);
			
			if(merchandisePlain==0){
				$("#cartDetail").delay(100).fadeOut(500);
				$("#noItems").delay(500).fadeIn(1000);
			}
    	}
	
    },
    error: function(e){
    	alert('Error: ' + e);
    }
    });
}

/*
 * move Item to Cart
 */
function moveItemToCart(element) {

	var myWLLineID =  $(element).closest("#wishlistline").attr("data-item-id");

    $.ajax({
    type: "POST",
    url: "/Cuenta/MoveItemToCart",
    data: {wlLineID: myWLLineID},
    success: function(response){

    	if(response!=null){
    		
    		$(element).closest("#wishlistline").children("td").children("div").animate(
    			    			{
    			    			 height: '4px',
    			    			 opacity: 0
    			    			}, 1000,  
    			    			function(){
    			    				var wishListMsg = response[0];
    			    				var counter = response[1];
    			    				$(element).closest("#wishlistline").css("opacity","0");
    			    				$(element).closest("#wishlistline").html("<td colspan=\"5\"><div>"+ wishListMsg +"</div></td>");  
    			    				$("#basketCount").text(counter);
    			    			}
    			    			);  
    		
	    	$(element).closest("#wishlistline").delay(1000).animate(
	    			{
	    				opacity: 1
		    		}, 1000);
	    	
	    	$(element).closest("#wishlistline").delay(5000).fadeOut(1000, function(){ 
	    		
	    		$(this).remove(); mutex=false;
	    	});
    	}
	
    },
    error: function(e){
    	alert('Error: ' + e);
    }
    });
}

function removeItemFromWishList(element) {
	
	if(mutex){
		return;
	}
	mutex = true;
    
	var myItemId =  $(element).closest("#wishlistline").attr("data-item-id");
	
    $.ajax({
    type: "POST",
    url: "/Cuenta/RemoveItemFromWishList",
    data: "itemId=" + myItemId,
    success: function(response){
    	
		if(response!=null)
			$(element).closest("#wishlistline").fadeOut(1000, function(){ $(this).remove(); mutex=false;});

    },
    error: function(e){
    	alert('Error: ' + e);
    	mutex=false;
    }
    });
}

/*
 * Formats the number (currency type)
 */
function formatNumber(nStr)
{	
	nStr = nStr.toFixed(2);
	nStr = nStr.replace(".",",");
	
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + '.' + '$2');
	}
	return x1 + x2;
}


function getCookie(c_name)
{
	var c_value = document.cookie;
	var c_start = c_value.indexOf(" " + c_name + "=");
	if (c_start == -1)
	{
	  c_start = c_value.indexOf(c_name + "=");
	}
	if (c_start == -1)
	{
	  c_value = null;
	}
	else
	{
	  c_start = c_value.indexOf("=", c_start) + 1;
	  var c_end = c_value.indexOf(";", c_start);
	  if (c_end == -1)
	  {
		  c_end = c_value.length;
	  }
	  c_value = unescape(c_value.substring(c_start,c_end));
	}
	return c_value;
}


(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/es_LA/all.js#xfbml=1";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));


!function(d,s,id){
		var js,fjs=d.getElementsByTagName(s)[0];
		if(!d.getElementById(id)){
			js=d.createElement(s);
			js.id=id;
			js.src="https://platform.twitter.com/widgets.js";
			fjs.parentNode.insertBefore(js,fjs);
		}}(document,"script","twitter-wjs");

/*
 * Allows Only Numbers
 */
function isNumberKey(evt){

	if (evt.which == 13 || evt.keyCode == 13) {
		
		if(evt.target.id=="qtyAdd"){
			addItem();
		}
		else{
			updateItem(evt.target);
		}
		return true;
	}
    
	var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
       return false;

    return true;
}

/*
 * Datepicker
 */
function datepickerSetUp(){
	
	//DatePicker
	$.datepicker.regional['es'] = {
		closeText: 'Cerrar',
		prevText: '<Ant',
		nextText: 'Sig>',
		currentText: 'Hoy',
		monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
		'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
		'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
		dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi\u00e9rcoles', 'Jueves', 'Viernes', 'S\u00e1bado'],
		dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mi\u00e9;', 'Juv', 'Vie', 'S\u00e1b'],
		dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'S\u00e1'],
		weekHeader: 'Sm',
		dateFormat: 'dd/mm/yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''
	};
	
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	$('.datepicker').datepicker({
								changeMonth: true, 
								changeYear: true, 
								yearRange: '-100:+0'
	});
}

/*
 * Newsletter Subscription
 */
function subscribe(evt){
	
	$('.newsletterMsg').removeClass("successNewsletter");
	$('.newsletterMsg').removeClass("errorNewsletter");
	
	if (evt.which != 13 && evt.keyCode != 13 && evt.which != 1 && evt.keyCode != 1)
		return;
	
	var customerEmail =  $("#newsletterMail").val();
	
	if(customerEmail=="")
		return;
		
    $.ajax({
    type: "POST",
    url: "/Newsletter/Subscribe",
    data: {email: customerEmail},
    success: function(response){
    	$('.newsletterMsg').addClass(response[0]);
		$('.newsletterMsg').html(response[1]);
		
		if(response[0]!="errorNewsletter")
			$("#newsletterMail").val("");
    },
    error: function(e){
    	alert("Can't add your mail right now");
    }
    });
}


    