 var url = "index.do?display=ANCESTRY&ancestryId=";
 var url_ancestry = "index.do?display=ANCESTRY&ancestryId=";
 var url_son = "index.do?display=SON&ancestryId=";

var showLine;  //显示多少代族谱

var list = new Array();



function showSon(){
	
	var line = $("#showLine").val()
	//alert(line+"***"+showLine);
	if(line){
		getListByLine(line,url_son+$("#zid").val());
	}
	else if(showLine){
		//alert();
		getListByLine(showLine,url_son+$("#zid").val());
	}
	else{
		getListByLine(4,url_son+$("#zid").val());
	}
	//getList();
	
	
	console.log(url_son+$("#zid").val());
}

function showAncestry(){
	var zid = document.getElementById("zid");//这里使用$("#zid")获取不了值，不过简单情况下document原生最好，原来jquery中获取value 使用 jobj.val()
	var line = $("#showLine").val();
	if(line){
		getListByLine(line,url_ancestry+$("#zid").val());
	}
	else if(showLine){
		getListByLine(showLine,url_ancestry+zid.value);
	}
	else{
		getListByLine(4,url_ancestry+zid.value);
	}
	//getList(url_ancestry+ zid.value);
	console.log(url_ancestry+ zid.value);
	
}


function init(){
		 
	   // mScreenWidth =  window.innerWidth;;
	   // mScreenHeight = window.innerHeight;
	   mScreenWidth = document.documentElement.clientWidth;
	   mScreenHeight = document.documentElement.clientHeight;
	   var c = document.getElementById("drawing");
	   // c.style.width = mScreenWidth + "px";
	   // c.style.height = mScreenHeight + "px";
	   c.width = mScreenWidth;
	   //alert(mScreenWidth+"**"+mScreenHeight);
	   c.height = mScreenHeight;
	 }


/**
$(window).resize(resizeCanvas);

function resizeCanvas() {

$("#drawing").attr("width", $(window).get(0).innerWidth);

$("#drawing").attr("height", $(window).get(0).innerHeight);

}*/
 $(document).ready(function(){
	 var mScreenWidth;
	 var mScreenHeight;
	 init();
	 showLine = 4;
	 if(getPram("showLine")){
		 showLine = getPram("showLine");
	 }
	 //getList(url+getPram("zid"));
	 if(getPram("zid"))
	 getListByLine(showLine,url+getPram("zid"));
	 
});
 
 
 function getListByLine(showLine,url){
	 $.getJSON(url,function(result){
	 
		//初始化数据
		init();
  		list = new Array();
		var canvas = document.getElementById("drawing");
		
		var step = 100; //每个元素左右间隔
		
		var heightArray = new Array(); //存储每代 人的个数  决定x盒y值
		var lineArray = new Array();  //储存行元素
		var columnArray = new Array();	//储存列元素
		var cheight;  //最高画布高度
		var numPerLine = Math.floor(canvas.width/step) -1;   //每一行最多几个元素 
		var maxLine = 0;	
			
		//获取每一代有多少人  决定每一代y坐标起点
		for(var i = 0;i<result.length;i++){
			line = result[i].line;
			if(line && line <= showLine){
				if(heightArray[line]){
					heightArray[line]++;
					
				}
				else{
					heightArray[line] = 1;
				}
				
				if(line > maxLine){
					maxLine = line;
				}
				
				
			}
			
			
		}		
				
		// 获取每一代y坐标起点  存储到columnArray
		for(var i = 1;i<heightArray.length;i++){
			
			if(i == 1){
				columnArray[1] = 50;
			}
			else if(i < (heightArray.length - 1)){
				columnArray[i] = columnArray[i-1] +Math.ceil(step*heightArray[i-1]/canvas.width)*step+step;
			}else{
				columnArray[i] = columnArray[i-1] +Math.ceil(step*heightArray[i-1]/canvas.width)*step+step;
				cheight = columnArray[i] + Math.ceil(step*heightArray[i]/canvas.width)*step+step;
			}
			
			
		}		
		//如果元素够大  设定画布高度
		if(cheight){
			canvas.height = cheight;
		}
				
				
		for(var i = 0;i < result.length;i++){
			line = result[i].line;
			if(line && line <= showLine){
				if(!lineArray[line]){//初始化每代的第一个位置
					lineArray[line] = 1;
				}
				//每行数目超过最大行限定 x设置为1，y坐标加一个间隔step
				if(lineArray[line] > numPerLine){ 
					lineArray[line] = 1;
					columnArray[line] +=  step;
				}
				
				var obj = new Object();
				obj.x = 50+lineArray[line]*step
				obj.y = columnArray[line];
				obj.id = result[i].panda.zid;
				obj.name = result[i].panda.name;
				obj.father = result[i].panda.father;
				obj.mother = result[i].panda.mother;
				obj.son = result[i].son;
				obj.panda = result[i].panda;
				obj.line = line;
				list.push(obj);
				
				lineArray[line] += 1;
			}
			
		}
		//document.write(JSON.stringify(list));
		//根据url的json中是否有ancestry参数判断是画子女图谱还是祖先族谱
		//alert(JSON.stringify(result[0]));
		//alert(result[0].ancestry);
		if(result[0].ancestry){ //查祖先模式（ANCESTRY）的ancestry都为“”，有值说明是子女族谱 ，url为url_son   
 			//url = url_ancestry;
 			drawPandaByUrlAndList(null,url_son);
 		}
 		else{
 			//url = url_son;
 			drawPandaByUrlAndList(null,url_ancestry);
 			
 		}		
				
	 });	
	}

 function getList(url){
	 
	 /**添加参数data后result[0].panda.name会出现乱码，使用$.getJSON(url,function(result){})后解决 
     $.ajax({
        type: "POST",
        url: url,
        data: {},
        dataType: "json",
        async:true,
        success:function(result) */
	 $.getJSON(url,function(result){
     	if(result && result[0]){
     		console.log(url);
     		line = result[0].line;
     		//alert(JSON.stringify(result));
     		//修改删减参数  实现连接
     		var canvas = document.getElementById("drawing");
     		
     		//初始化数据
     		list = new Array();
     		
     		var line;
     		
     		var n1=0,n2=0,n3=0,n4=0;
     		var xy = new Object();
     		
     		var step = 100;//圆之间间隔
     		//每一层初始位置x
     		xy.l1 = 1;
     		xy.l2 = 1;
     		xy.l3 = 1;
     		xy.l4 = 1;
     		
     		//每一列初始位置x   问题在不知道每一层有多少行，不好判断y间隔
     		xy.c1 = 50;
     		xy.c2 = 150;
     		xy.c3 = 250;
     		xy.c4 = 350;
     		
     		//第一遍循环给每一列初始位置赋值 为了页面自适应
     		for(var i = 0;i<result.length;i++){
     			line = result[i].line;
     			if(line ==1){
     				n1++;
     			}
     			else if(line ==2){
     				n2++;
     			}
     			else if(line ==3){
     				n3++;
     			}
     			else if(line ==4){
     				n4++;
     			}
     		}
     		
     		
     		var heightArray = new Array();
     		var lineArray = new Array();
			var columnArray = new Array();
     		var cheight =50,cwidth=50;
			
			
     		var numPerLine = Math.ceil(canvas.width/100)-1;
     		var maxLine = 0;
     		for(var i = 0;i<result.length;i++){
     			line = result[i].line;
     			if(heightArray[line]){
     				heightArray[line]++;
     				
     			}
     			else{
     				heightArray[line] = 2;
     			}
     			
     			if(line > maxLine){
     				maxLine = line;
     			}
     			
     		}
     		
     		
     		for(var i = 1;i<heightArray.length;i++){
     			
     			if(i == 1){
     				columnArray[1] = 50;
     			}
     			else{
     				columnArray[i] = columnArray[i-1] +Math.ceil(step*heightArray[i-1]/canvas.width)*step+step;
     				cheight = columnArray[i] + Math.ceil(step*heightArray[i]/canvas.width)*step+step;
     			}
     			
     			
     		}
     		
     		for(var i = 0;i<result.length;i++){
     			line = result[i].line;
     			if(line){
     				if(!lineArray[line]){//初始化每代的第一个位置
         				lineArray[line] = 1;
         			}
         			
     				if(lineArray[line] > numPerLine){
     					lineArray[line] = 1;
     					columnArray[line] = columnArray[line] + 100;
     				}
     				
     				
         			var obj = new Object();
     				obj.y = xy.c1;
     				obj.id = result[i].genealogy.zid;
     				obj.name = result[i].genealogy.name;
     				obj.father = result[i].genealogy.father;
     				obj.mother = result[i].genealogy.mother;
     				obj.son = result[i].son;
     				obj.panda = result[i].genealogy;
     				obj.line = 1;
     				list.push(obj);
     				
     				lineArray[line] += 1;
     				
     				
     			}
     			
     			
     			
     			
     		}
     		
     		
     		n2==0?canvas.height = xy.c2:"";
     		n3==0?canvas.height = xy.c3:"";
     		n4==0?canvas.height = xy.c4:"";
     		
     		xy.c1 = 50;
			xy.c2 = xy.c1+Math.ceil(step*n1/canvas.width)*step+step;
			xy.c3 = xy.c2+Math.ceil(step*n2/canvas.width)*step+step;
			xy.c4 = xy.c3+Math.ceil(step*n3/canvas.width)*step+step;
			
     		//alert("每层的y元素："+"**"+xy.c1+"**"+xy.c2+"**"+xy.c3+"**"+xy.c4);
     		
     		//alert("result.length"+result.length);
			
			
			var lineArray = new Array();
			var columnArray = new Array();
			
			
     		for(var i = 1;i<result.length;i++){
     			line = result[i].line;
     			if(result[i].line){
     				if(line == 1){
     					
     					obj.x = canvas.width/2;
     					
     				}
     				else{
     					
     					obj.x = lineArray[line]*step;;
     				}
     				
     				
     				
     				var obj = new Object();
     				
     				
     				
     				obj.y = xy.c1;
     				obj.id = result[i].genealogy.zid;
     				obj.name = result[i].genealogy.name;
     				obj.father = result[i].genealogy.father;
     				obj.mother = result[i].genealogy.mother;
     				obj.son = result[i].son;
     				obj.panda = result[i].genealogy;
     				obj.line = 1;
     				list.push(obj);
     				
     				 lineArray[line] += 1;
     			}
     			
     			
     			
     			if(result[i].line ==1){
     				if(xy.l1*step > (canvas.width - 50 )){
     					xy.l1 = 1;
     					xy.c1 += 100;
     					
     				}
     				//drawPandaNewCanvasAndLine(result[i].panda,canvas.width/2,xy.c1,line);
     				
     				var obj = new Object();
     				if(n1==1){
     					obj.x = canvas.width/2;
     				}
     				else{
     					
     					obj.x = xy.l1*step;;
     				}
     				
     				
     				obj.y = xy.c1;
     				obj.id = result[i].genealogy.zid;
     				obj.name = result[i].genealogy.name;
     				obj.father = result[i].genealogy.father;
     				obj.mother = result[i].genealogy.mother;
     				obj.son = result[i].son;
     				obj.panda = result[i].genealogy;
     				obj.line = 1;
     				list.push(obj);
     				
     				xy.l1 += 1
         		}
         		else if(result[i].line ==2){
         			if(canvas.height < xy.c2+step){//700第一行第二行第一个会消失？
         				//alert("height:"+canvas.height+"小于900 增大到900");
         				canvas.height = xy.c2+step*2;
         				
         			}
         			if(xy.l2*step > (canvas.width - 50 )){
     					//alert(result[i].panda.zid+"换行"+xy.x+"**"+(xy.c1*line+step));
     					xy.l2 = 1;
     					xy.c2 +=100;
     				}
         			//drawPandaNewCanvasAndLine(result[i].panda,xy.l2*step,xy.c2,line);
         			var obj = new Object();
         			obj.x = xy.l2*step;
     				obj.y = xy.c2;
     				obj.id = result[i].genealogy.zid;
     				obj.name = result[i].genealogy.name;
     				obj.father = result[i].genealogy.father;
     				obj.mother = result[i].genealogy.mother;
     				obj.panda = result[i].genealogy;
     				obj.line = 2;
     				list.push(obj);
         			
         			xy.l2 += 1
         		}
         		else if(result[i].line ==3){
         			
         			if(canvas.height < xy.c3+step){//700第一行第二行第一个会消失？
         				//alert("height:"+canvas.height+"小于900 增大到900");
         				canvas.height = xy.c3+step*2;
         				
         			}
         			if(xy.l3*step > (canvas.width - 50 )){
     					//alert(xy.l3*step+"***"+canvas.width);
     					xy.l3 = 1;
     					xy.c3 += 100;
     				}
         			//drawPandaNewCanvasAndLine(result[i].panda,xy.l3*step,xy.c3,line);
         			var obj = new Object();
         			obj.x = xy.l3*step;
     				obj.y = xy.c3;
     				obj.id = result[i].genealogy.zid;
     				obj.name = result[i].genealogy.name;
     				obj.father = result[i].genealogy.father;
     				obj.mother = result[i].genealogy.mother;
     				obj.panda = result[i].genealogy;
     				obj.line = 3;
     				list.push(obj);
     				
         			xy.l3 += 1
         		}
         		else if(result[i].line ==4){
         			if(canvas.height < xy.c4+step){//700第一行第二行第一个会消失？
         				//alert("height:"+canvas.height+"小于900 增大到900");
         				canvas.height = xy.c4+step*2+50;
         				
         			}
         			if(xy.l4*step > (canvas.width - 50 )){
     					
     					xy.l4 = 1;
     					xy.c4 += 100;
     				}
         			
         			//drawPandaNewCanvasAndLine(result[i].panda,xy.l4*step,xy.c4,line);
         			var obj = new Object();
         			obj.x = xy.l4*step;
     				obj.y = xy.c4;
     				obj.id = result[i].genealogy.zid;
     				obj.name = result[i].genealogy.name;
     				obj.father = result[i].genealogy.father;
     				obj.mother = result[i].genealogy.mother;
     				obj.panda = result[i].genealogy;
     				obj.line = 4;
     				
     				list.push(obj);
     				
         			xy.l4 += 1
         		}
     		}
     		//alert(list.length); 
     		//通过list先画图，再画线
     		//alert("pre drawing"+list[0].x+"**"+list[0].y+"长度"+list.length);
     		
     		console.log("change url("+url+") by father:"+result[0].father);
     		
     		if(result[0].ancestry){
     			url = url_ancestry;
     			drawPandaByUrlAndList(null,url_ancestry);
     		}
     		else{
     			url = url_son;
     			drawPandaByUrlAndList(null,url_son);
     			
     		}
     		console.log("changed url("+url+")");
     		//修改为以上的添加url判断
     		//draw1(null);
     		
     		
    		//drawEarByArr(null);
    		//drawLineByArr(list);
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
	 
	 
 }
  		 

 function getPram(name)
 {
      var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
      var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
      if(r!=null)return  unescape(r[2]); return null;
 }
  
        
        
        
       