<!DOCTYPE html>
<html>
	<meta charset="utf-8">
	<head>
		<title></title>
	</head>
	<body>
		<div id="charts" style="height:400px;">

		</div>
  	<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
  	<script type="text/javascript" src="../js/echarts.js""></script>
  	
		<!-- <script type="text/javascript" src="http://echarts.baidu.com/build/dist/echarts.js"></script> -->
		<script type="text/javascript">
			function createRandomItemStyle(d) { 
				d = d || Math.floor((Math.random()*20)+1);
				var colors=['#EDD8CB','#BFC0C3','#F59595','#8ECA51','#DFE0E1','#F6CBAD','#BAD4AD','#75839D','#89A9C8','#BFC0C3','#F59595','#8ECA51','#DFE0E1','#f3f9f1',  '#BAD4AD','#75839D','#89A9C8','#BFC0C3'];  
				if(d>colors.length){
        			d=0;  
        		} 
				return { normal: { color:colors[d] } }; 
			} 

			
			var mapKeyValue = [];
		    
		    $.post("../WordCloudServlet", "", function(data){
				mapKeyValue.length=0;
				for(var i=0; i < data.length; i++){
					mapKeyValue.push({"value":data[i].num, "name": data[i].ismobile});
				}
				 console.log(data);
			  
		    }, 'json');
		    
		    
		    
			require.config({
				paths:{
					echarts: "../js"
				}
			})
			require(["echarts/echarts","echarts/chart/wordCloud"],function(ec){
				
				var myEchart = ec.init(document.getElementById("charts"));
				var option = {
			    tooltip: {
			        show: true
			    },
			    grid:{ x:20,  y:0,  y2:4  },
			    series: [{
			        name: 'aaa',
			        type: 'wordCloud',
			        size: ['90%', '40%'],  
					textRotation : [0,10, 90, -10],//[0, 90],  
					textPadding: 6,  
					autoSize: { 
						enable: true,  
						minSize: 20  
					},
			        data: mapKeyValue,
			    }]
			};
			myEchart.setOption(option);
			});
		</script>

	</body>
</html>