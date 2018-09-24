 var url = "index.do";
var panda;
 
 $(document).ready(function(){

		
 	$("#button1").click(function(){$.getJSON(url+"?more=1",function(data){
 		var img = $("div");
 		
		for(var i = 0;i<data.length;i++){
			
			img.append("<a href='/SSH/showPanda.do?zid="+data[i].zid+"'>"+data[i].zid+" </a>");
			img.html(img.html()+data[i].name);
			img.html(img.html()+data[i].place);
			img.html(img.html()+"</br>");
			
		}
		$("#p1").append(img);
 	    
 	});
  		
    
});
});

 
  		 
   /**添加参数data后result[0].panda.name会出现乱码，使用$.getJSON(url,function(result){})后解决 
     $.ajax({
        type: "POST",
        url: url,
        data: {},
        dataType: "json",
        async:true,
        success:function(result) */
        $.getJSON(url+"?display=ANCESTRY&ancestryId=382",function(result){
        	$("body").html("");
        	if(result[0].line){
        		var div1 = $("<div id = 'div1' overflow='auto'>1:</div>");
        		var div2 = $("<div id = 'div2' style='float:left'>2:</div>");
        		var div3 = $("<div id = 'div3' overflow='auto'>3:</div>");
        		var div4 = $("<div id = 'div4' overflow='auto'>4:</div>");
        		$("body").append(div1);
        		$("body").append(div2);
        		$("body").append(div3);
        		$("body").append(div4);
        		for(var i = 0;i<result.length;i++){
            		//var newCanvas = $("<canvas></canvas>");
            		//newCanvas.id = "canvas"+result[i].zid;
            		//$("body").append("<canvas id = '"+"canvas"+result[i].panda.zid+"' width='100' height='100'></canvas>");
            		
            		
            		if(result[i].line ==1){
            			
            			div1.append("<canvas id = '"+"canvas"+result[i].panda.zid+"' width='100' height='100'></canvas>");
            			drawPandaNewCanvas(result[i].panda,50,50);
            			
            		}
            		else if(result[i].line ==2){
            			
            			div2.append("<canvas id = '"+"canvas"+result[i].panda.zid+"' width='100' height='100'></canvas>");
            			drawPandaNewCanvas(result[i].panda,50,50);
            			
            		}
            		else if(result[i].line ==3){
            			
            			div3.append("<canvas id = '"+"canvas"+result[i].panda.zid+"' width='100' height='100'></canvas>");
            			drawPandaNewCanvas(result[i].panda,50,50);
            		}
            		else if(result[i].line ==4){
            			
            			div4.append("<canvas id = '"+"canvas"+result[i].panda.zid+"' width='100' height='100'></canvas>");
            			drawPandaNewCanvas(result[i].panda,50,50);
            		}
            		
            		
            		
            		
            		
            		
        		}
        	}else{
        		for(var i = 0;i<result.length;i++){
            		//var newCanvas = $("<canvas></canvas>");
            		//newCanvas.id = "canvas"+result[i].zid;
            		$("body").append("<canvas id = '"+"canvas"+result[i].zid+"' width='100' height='100'></canvas>");
            		
            		drawPandaNewCanvas(result[i],50,50);
        		}
        		
        	}
        	
        	
        //}
    });
        
        
       