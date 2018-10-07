var context,panda,canvas;
$(document).ready(function(){
	//var url = "index.do?display=SON&ancestryId=12";
	
	context = getContextById("drawing");
	
	canvas.addEventListener('click', function(e) {　
	    p = getEventPosition(e);
	    console.log("onclick url("+url+")");
		//alert("onclick:"+url);  //不知道为什么list可以获取  url还是不变
	   
	　　    drawPandaByUrlAndList(p,url);}, false);
	
	
	canvas.addEventListener('dbclick', function(e){
		　　p = getEventPosition(e);
		　　drawPandaByUrlAndList(p,url);
		　　}, false);
	
});



function getContextById(id){
	canvas = document.getElementById(id);
	
	//alert(canvas.getContext);
	if(canvas.getContext){
		var context = canvas.getContext("2d");
		return context;
	}
	return null;
}

//通过id和前缀长度截取出panda.zid，并给每个canvas添加clik事件
function getContextByIdAndIndex(id,idStartIndex){
	var canvas = document.getElementById(id);
	canvas.addEventListener('click', function(e){
		  location.href="/SSH/showPanda.do?zid="+this.id.substring(idStartIndex);
		}, false);
	//alert(canvas.getContext);
	if(canvas.getContext){
		var context = canvas.getContext("2d");
		return context;
	}
	return null;
}

//测试画熊猫圈  
function drawPanda(panda,x,y){
	
	context.beginPath();
	context.arc(x,y,40,0,2*Math.PI,false);
	context.closePath();
	context.strokeStyle="black";
	context.stroke();
	
	context.fillStyle = "black";
	context.font = "bold 30px";
	context.textAlign="center";
	context.textBaseline="bottom";
	context.fillText(panda.zid,x,y,200);
	context.textBaseline="top";
	context.fillText(panda.name,x,y,200);
	
}

//每个panda创建一个新的canvas元素承载，方便click事件  对应zupu1.js ？
function drawPandaNewCanvas(panda,x,y){
	//从第六个字符开始截取到末尾为panda。zid
	context = getContextByIdAndIndex("canvas"+panda.zid,"canvas".length);
	
	drawPanda(panda,x,y)
}

//
function drawPandaNewCanvasAndLine(panda,x,y,line){
	//从第六个字符开始截取到末尾为panda。zid
	//context = getContextByIdAndIndex("canvas"+panda.zid,"canvas".length);
	
	context.beginPath();
	context.arc(x,y,40,0,2*Math.PI,false);
	context.closePath();
	context.strokeStyle="black";
	context.stroke();
	
	context.fillStyle = "black";
	context.font = "40px Arial";
	context.textAlign="center";
	context.textBaseline="bottom";
	context.fillText(line+"/"+panda.zid,x,y,200);
	context.textBaseline="top";
	context.fillText(panda.name,x,y,200);
}




function drawLine(startX,startY,endX,endY){
	context.beginPath();
	context.moveTo(startX,startY);
	context.lineTo(endX,endY);
	context.closePath();
	context.lineWidth=2;
	context.strokeStyle = "red";
	context.stroke();
}

//数据库查询等待期间显示等待中
function drawWaiting(){
	context.fillStyle = "black";
	context.font = "40pt Microsoft JhengHei";
	context.textAlign="center";
	context.textBaseline="bottom";
	if(canvas){//canvas不为空  在图片中间画图  不然在300、300位置画图
		context.fillText("waiting",canvas.width/2,300,200);
	}
	else{
		context.fillText("waiting",300,300,200);
	}
	
	//context.textBaseline="top";
	
}




function drawLineByArr(list){
	
	for(var i = 0;i<list.length;i++){
		obj = list[i];
		
		if(obj.mother || obj.father){
			for(var j = 0;j<list.length;j++){
				//alert(obj.father.trim()+"||"+list[j].id.trim());
    			if(obj.mother.trim() == list[j].id.trim() || obj.father.trim() == list[j].id.trim()){
    				
    				drawLine(obj.x,(obj.y-40),list[j].x,(list[j].y+40));
    				continue;
    			}
    		}
			
			
		}
		
	}
	
}

function drawLineByArrAndAncestry(list){
	
	for(var i = 0;i<list.length;i++){
		obj = list[i];
		if(obj.mother || obj.father){
			for(var j = i;j<list.length;j++){//j=0改为j=1  节省了1半时间，不过如果辈分低的在list后面会造成遗漏，list顺序要求不能改动
				//如果找到父亲id，画线  这个trim为了保证
    			if(obj.mother.trim() == list[j].id.trim() || obj.father.trim() == list[j].id.trim()){
    				drawLine(obj.x,(obj.y+40),list[j].x,(list[j].y-40));
    			
    			}
    		}
			
			
		}
		
	}
	
}


function drawEarByArr(p){
	
	for(var i = 0;i<list.length;i++){
		var x,y,z,r;
		
		obj = list[i];
		if(obj.father != "99999"){
			 x= obj.x;
			 y = obj.y;
			 z = 28
			 r = 18;//半径
			 x = x - z;//左耳朵原点
			 y = y - z;
			 context.beginPath();
			context.arc(x,y,r,2*Math.PI/4,8*Math.PI/4,false);
			context.closePath();
			context.fillStyle="black";
			context.fill();	
			//alert("x:"+p.x+"y"+p.y+"结果："+context.isPointInPath(p.x, p.y));
			if(p && context.isPointInPath(p.x, p.y)){
				　　//如果传入了事件坐标，就用isPointInPath判断一下
				　　//如果当前环境覆盖了该坐标，就将当前环境的index值放到数组里
				//alert("father:"+list[i].father);
				if(list[i].father != "99999")
				getList(url_ancestry+list[i].father);
				return;
				//alert(list[i].father);
			}
		}	
			x = x + 2*z;//右耳朵原点
		if(obj.mother != "99999"){	
			context.beginPath();
			context.arc(x,y,r,2*Math.PI/4,4*Math.PI/4,true);
			context.closePath();
			context.fillStyle="black";
			context.fill();
			
			//alert("mother:"+list[i].mother);
			if(p && context.isPointInPath(p.x, p.y)){
				　　//如果传入了事件坐标，就用isPointInPath判断一下
				　　//如果当前环境覆盖了该坐标，就将当前环境的index值放到数组里
				if(list[i].mother != "99999")
				getList(url_ancestry+list[i].mother);
				return;
				//alert(list[i].mother);
			}
			
		}
		
	}
	
	
	
	
}


function getEventPosition(ev){
	　　var x, y;
	　　if (ev.layerX || ev.layerX == 0) {
		　　x = ev.layerX;
		　　y = ev.layerY;
	　　} else if (ev.offsetX || ev.offsetX == 0) { // Opera
		　　x = ev.offsetX;
		　　y = ev.offsetY;
	　　}
	　　return {x: x, y: y};

}

//触发事件  花id的族谱图
function draw(p){
	　　var who = [];
	
	　　context.clearRect(0, 0, canvas.width, canvas.height);
	　　list.forEach(function(v, i){
		
		
	　　context.beginPath();
	context.arc(v.x,v.y,40,0,2*Math.PI,false);
	context.closePath();
	context.fillStyle="yellow";
	context.fill();
	
	context.fillStyle = "black";
	context.font = "20pt Microsoft JhengHei";
	context.textAlign="center";
	context.textBaseline="bottom";
	context.fillText(v.id,v.x,v.y,200);
	context.textBaseline="top";
	context.fillText(v.name,v.x,v.y,200);
	
	
	　　if(p && context.isPointInPath(p.x, p.y)){
	　　//如果传入了事件坐标，就用isPointInPath判断一下
	　　//如果当前环境覆盖了该坐标，就将当前环境的index值放到数组里
		
		location.href="/SSH/showPanda.do?zid="+v.id;
		
	　　	who.push(i);
	　　}
	　　});

	　　//根据数组中的index值，可以到arr数组中找到相应的元素。
	　　return who;
}
//单击后触发事件   通过点击未知的熊猫id选择进入id的族谱图或者father的族谱图
function draw1(p){
	　　var who = [];
	　　context.clearRect(0, 0, canvas.width, canvas.height);
		//alert(context);
	　　list.forEach(function(v, i){
		//alert("drawing"+v.x+"jiojio"+v.y)
		context.beginPath();
		context.arc(v.x,v.y,40,0,2*Math.PI,false);
		context.closePath();
		
		//根据性别设置熊猫圆颜色
		if(list[i].panda.sex == 0){
			context.fillStyle="pink";
		}
		else if(list[i].panda.sex == 1){
			context.fillStyle="yellow";
		}
		else{
			context.fillStyle="white";
		}
		
		context.fill();
		
		context.fillStyle = "black";
		context.font = "20pt Microsoft JhengHei";
		context.textAlign="center";
		context.textBaseline="bottom";
		context.fillText(v.id,v.x,v.y,200);
		context.textBaseline="top";
		context.fillText(v.name,v.x,v.y,200);
		
		　　if(p && context.isPointInPath(p.x, p.y)){
		　　//如果传入了事件坐标，就用isPointInPath判断一下
		　　//如果当前环境覆盖了该坐标，就将当前环境的index值放到数组里
			//alert(v.line+"****"+v.father);
			if(v.line == 1 && v.mother !="99999"){
				//alert(url+v.father);
				getList(url_ancestry+v.mother);
				return;
			}
			else{
				//alert(url+v.id);
				getList(url_ancestry+v.id);
				return;
			}
			
		　　	who.push(i);
		　　}
		});
	drawLineByArr(list);
	drawEarByArr(p);
		　　//根据数组中的index值，可以到arr数组中找到相应的元素。
		return who;
}

function drawPandaByUrlAndList(p,url_display){
	console.log("drawPanda"+url_display+"canvas.width:"+canvas.width+" canvas.height"+ canvas.height);
	　　var who = [];
	　　context.clearRect(0, 0, canvas.width, canvas.height);
		//alert(context);
	
	　　//list.forEach(function(v, i){
		for(var i=0;i<list.length;i++){
		//alert("drawing"+v.x+"jiojio"+v.y)
		context.beginPath();
		context.arc(list[i].x,list[i].y,40,0,2*Math.PI,false);
		context.closePath();
		
		//根据性别设置熊猫圆颜色
		if(list[i].panda.sex == 0){
			context.fillStyle="pink";
		}
		else if(list[i].panda.sex == 1){
			context.fillStyle="yellow";
		}
		else{
			context.fillStyle="white";
		}
		
		context.fill();
		
		context.fillStyle = "black";
		context.font = "20pt Microsoft JhengHei";
		context.textAlign="center";
		context.textBaseline="bottom";
		context.fillText(list[i].id,list[i].x,list[i].y,200);
		context.textBaseline="top";
		context.fillText(list[i].name,list[i].x,list[i].y,200);
		
		　　if(p && context.isPointInPath(p.x, p.y)){
		　　//如果传入了事件坐标，就用isPointInPath判断一下
		　　//如果当前环境覆盖了该坐标，就将当前环境的index值放到数组里
			//alert(v.line+"****"+v.father);
			context.clearRect(0, 0, canvas.width, canvas.height);//需要加入一个等待按钮
			drawWaiting();
			if(showLine){
				getListByLine(showLine,url_son+list[i].id);
			}
			else{
				getList(url_son+v.id);
				console.log("showLine is null");
			}
			
			p=null; //解决圆圈和猫耳朵交界处点击会触发2次事件  list会出现bug
			return;  //foreach方法里使用return，结束的是foreach方法  需要换成循环
			/**   url不能被getList变化  计划失败
			if(v.line == 1 && v.mother !="99999"){
				//alert(url+v.father);
				getList(url_display+v.mother);
				return;
			}
			else{
				//alert(url+v.id);
				getList(url_display+v.id);
				return;
			}
			*/
		　　	who.push(i);
		　　}
		//});
		}
	
	var drawLineFlag = drawEarByUrlAndList(p,url_display);  //画线没有点击事件 放在最后
	//alert(drawLineFlag +"||"+ list[0].son);
	if(!drawLineFlag)//触发耳朵点击事件  不需要再重复画线
		return;
	console.log("list:("+list.length+")"+JSON.stringify(list));
	if(list[0].son){ 
		drawLineByArrAndAncestry(list); //如果是查祖先族谱 高辈分在下 应该低辈份元素竖向坐标加半径为起点
	}
	else{
		drawLineByArr(list); //如果查子女族谱 高辈分在上  应该低辈分元素竖向坐标加半径为起点
	}
	
	
		　　//根据数组中的index值，可以到arr数组中找到相应的元素。
	return who;
}

function drawEarByUrlAndList(p,url_display){
	console.log("drawEar"+url_display);
	for(var i = 0;i<list.length;i++){
		var x,y,z,r;
		
		obj = list[i];
		console.log(obj.id+"的父母是：father:"+obj.father+"|| mother：" +obj.mother+"画左耳:"+(obj.father !='' && obj.father != "99999")+"画右耳："+(obj.mother!='' && obj.mother != "99999"));
		
		
		 x= obj.x;
		 y = obj.y;
		 z = 33//距离圈圈中心距离
		 r = 18;//半径
		 x = x - z;//左耳朵原点
		 y = y - z;
		
		
		if(obj.father !='' && obj.father != "99999" ){
			
			console.log("drawingLeftEar");
			 context.beginPath();
			context.arc(x,y,r,2*Math.PI/4,8*Math.PI/4,false);
			context.closePath();
			context.fillStyle="black";
			context.fill();	
			//alert("x:"+p.x+"y"+p.y+"结果："+context.isPointInPath(p.x, p.y));
			if(p && context.isPointInPath(p.x, p.y)){
				　　//如果传入了事件坐标，就用isPointInPath判断一下
				　　//如果当前环境覆盖了该坐标，就将当前环境的index值放到数组里
				//alert("father:"+list[i].father);
				
				context.clearRect(0, 0, canvas.width, canvas.height);//需要加入一个等待按钮
				drawWaiting();
				if(showLine){
					getListByLine(showLine,url_ancestry+list[i].id);
				}
				else{
					getList(url_ancestry+list[i].id);
				}
				
				/** url不能改变
				if(url_son == url_display){
					//alert("earUrlF"+url_display+list[i].father);
					getList(url_display+list[i].id);
				}
				else{
					getList(url_display+list[i].father);
				}
				*/
				return false;
				//alert(list[i].father);
			}
		}	
			x = x + 2*z;//右耳朵原点
		if(obj.mother !='' && obj.mother != "99999" ){	
			console.log("drawingRightEar");
			context.beginPath();
			context.arc(x,y,r,2*Math.PI/4,4*Math.PI/4,true);
			context.closePath();
			context.fillStyle="black";
			context.fill();
			
			//alert("mother:"+list[i].mother);
			if(p && context.isPointInPath(p.x, p.y)){
				　　//如果传入了事件坐标，就用isPointInPath判断一下
				　　//如果当前环境覆盖了该坐标，就将当前环境的index值放到数组里
				if(showLine){
					getListByLine(showLine,url_ancestry+list[i].id);
				}
				else{
					getList(url_ancestry+list[i].id);
				}
				
				
				/** url不能改变  不然可以实现  什么模式决定新方法   如果添加新模式  url不能变会很死板
				if(url_son == url_display){
					//alert("earUrlM"+url_display+list[i].mother);
					getList(url_display+list[i].id);
					
				}
				else{
					getList(url_display+list[i].mother);	
				}
				*/
				return false;
				//alert(list[i].mother);
			}
			
		}
		
	}
	
	return true;//没有p点击画线
	
	
}