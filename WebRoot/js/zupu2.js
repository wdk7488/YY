 var url = "index.do?display=ANCESTRY&ancestryId=";

var panda;
var list = new Array();
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
	 function init(){
		 
	   // mScreenWidth =  window.innerWidth;;
	   // mScreenHeight = window.innerHeight;
	   mScreenWidth = document.documentElement.clientWidth;
	   mScreenHeight = document.documentElement.clientHeight;
	   var c = document.getElementById("drawing");
	   // c.style.width = mScreenWidth + "px";
	   // c.style.height = mScreenHeight + "px";
	   c.width = mScreenWidth;
	   c.height = mScreenHeight;
	   
	 }
	 getList(url+getPram("zid"));
});

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
     		
     		//修改删减参数  实现连接
     		var canvas = document.getElementById("drawing");
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
     		//alert(canvas.width+"每层的个数："+"**"+n1+"**"+n2+"**"+n3+"**"+n4);
     		
     		n2==0?canvas.height = xy.c2:"";
     		n3==0?canvas.height = xy.c3:"";
     		n4==0?canvas.height = xy.c4:"";
     		
     		xy.c1 = 50;
			xy.c2 = xy.c1+Math.ceil(step*n1/canvas.width)*step+step;
			xy.c3 = xy.c2+Math.ceil(step*n2/canvas.width)*step+step;
			xy.c4 = xy.c3+Math.ceil(step*n3/canvas.width)*step+step;
			
     		//alert("每层的y元素："+"**"+xy.c1+"**"+xy.c2+"**"+xy.c3+"**"+xy.c4);
     		
     		//alert("result.length"+result.length);
     		for(var i = 0;i<result.length;i++){
     			line = result[i].line;
     			
     			if(result[i].line ==1){
     				if(xy.l1*step > canvas.width){
     					xy.l1 = 1;
     					xy.c1 += 100;
     					
     				}
     				//drawPandaNewCanvasAndLine(result[i].panda,canvas.width/2,xy.c1,line);
     				
     				var obj = new Object();
     				obj.x = canvas.width/2;
     				obj.y = xy.c1;
     				obj.id = result[i].panda.zid;
     				obj.name = result[i].panda.name;
     				obj.father = result[i].panda.father;
     				obj.mother = result[i].panda.mother;
     				obj.panda = result[i].panda;
     				obj.line = 1;
     				list.push(obj);
     				
     				xy.l1 += 1
         		}
         		else if(result[i].line ==2){
         			if(canvas.height < xy.c2+step){//700第一行第二行第一个会消失？
         				//alert("height:"+canvas.height+"小于900 增大到900");
         				canvas.height = xy.c2+step*2;
         				
         			}
         			if(xy.l2*step > canvas.width){
     					//alert(result[i].panda.zid+"换行"+xy.x+"**"+(xy.c1*line+step));
     					xy.l2 = 1;
     					xy.c2 +=100;
     				}
         			//drawPandaNewCanvasAndLine(result[i].panda,xy.l2*step,xy.c2,line);
         			var obj = new Object();
         			obj.x = xy.l2*step;
     				obj.y = xy.c2;
     				obj.id = result[i].panda.zid;
     				obj.name = result[i].panda.name;
     				obj.father = result[i].panda.father;
     				obj.mother = result[i].panda.mother;
     				obj.panda = result[i].panda;
     				obj.line = 2;
     				list.push(obj);
         			
         			xy.l2 += 1
         		}
         		else if(result[i].line ==3){
         			
         			if(canvas.height < xy.c3+step){//700第一行第二行第一个会消失？
         				//alert("height:"+canvas.height+"小于900 增大到900");
         				canvas.height = xy.c3+step*2;
         				
         			}
         			if(xy.l3*step > canvas.width){
     					//alert(xy.l3*step+"***"+canvas.width);
     					xy.l3 = 1;
     					xy.c3 += 100;
     				}
         			//drawPandaNewCanvasAndLine(result[i].panda,xy.l3*step,xy.c3,line);
         			var obj = new Object();
         			obj.x = xy.l3*step;
     				obj.y = xy.c3;
     				obj.id = result[i].panda.zid;
     				obj.name = result[i].panda.name;
     				obj.father = result[i].panda.father;
     				obj.mother = result[i].panda.mother;
     				obj.panda = result[i].panda;
     				obj.line = 3;
     				list.push(obj);
     				
         			xy.l3 += 1
         		}
         		else if(result[i].line ==4){
         			if(canvas.height < xy.c4+step){//700第一行第二行第一个会消失？
         				//alert("height:"+canvas.height+"小于900 增大到900");
         				canvas.height = xy.c4+step*2;
         				
         			}
         			if(xy.l4*step > canvas.width){
     					
     					xy.l4 = 1;
     					xy.c4 += 100;
     				}
         			
         			//drawPandaNewCanvasAndLine(result[i].panda,xy.l4*step,xy.c4,line);
         			var obj = new Object();
         			obj.x = xy.l4*step;
     				obj.y = xy.c4;
     				obj.id = result[i].panda.zid;
     				obj.name = result[i].panda.name;
     				obj.father = result[i].panda.father;
     				obj.mother = result[i].panda.mother;
     				obj.panda = result[i].panda;
     				obj.line = 4;
     				
     				list.push(obj);
     				
         			xy.l4 += 1
         		}
     		}
     		//alert(list.length); 
     		//通过list先画图，再画线
     		//alert("pre drawing"+list[0].x+"**"+list[0].y+"长度"+list.length);
     		
     		draw1(null);
     		//drawLineByArr(list);
     		//drawEarByArr(list);
    		drawEarByArr(null);
    		drawLineByArr(list);
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
  
        
        
        
       