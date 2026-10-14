Basestat = function(){};

/**
 * 饼图和地图的数据模板(按机构)
 */
Basestat.datavalue = [
                      {name: '南京市',value: Math.round(Math.random()*1000)},
                      {name: '苏州市',value: Math.round(Math.random()*1000)},
                      {name: '常州市',value: Math.round(Math.random()*1000)},
                      {name: '无锡市',value: Math.round(Math.random()*1000)},
                      {name: '镇江市',value: Math.round(Math.random()*1000)},
                      {name: '南通市',value: Math.round(Math.random()*1000)},
                      {name: '泰州市',value: Math.round(Math.random()*1000)},
                      {name: '扬州市',value: Math.round(Math.random()*1000)},
                      {name: '盐城市',value: Math.round(Math.random()*1000)},
                      {name: '淮安市',value: Math.round(Math.random()*1000)},
                      {name: '宿迁市',value: Math.round(Math.random()*1000)},
                      {name: '连云港市',value: Math.round(Math.random()*1000)},
                      {name: '徐州市',value: Math.round(Math.random()*1000)}
                  ];

Basestat.dataname = ['南京市', '苏州市', '常州市', '无锡市','镇江市','南通市','泰州市','扬州市','盐城市','淮安市','宿迁市','连云港市','徐州市'];

/**
 * 折线图
 * @param id
 * @param text
 * @param subtext
 * @param name
 * @param data_x
 * @param data_y
 * @returns
 */
Basestat.createLine = function(id,text,subtext,name,data_x,data_y,xname,yname){
	var myChart = echarts.init(document.getElementById(id)); 
	var  option = {
		    title : {
		        text: text,
		        subtext:subtext,
		        x :'center',
		        textStyle :{
		            fontSize: 20,
		            fontWeight: 'bolder'
		        }
		    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {         
		            type : 'shadow'
		        }
		    },
		    legend: {
		        data:[name],
		        x:'right',
		        selectedMode:false
		    },
		    toolbox: {
		        show : false
		    },
		    calculable : false,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : true,
		            splitLine : false,
					name:xname,
		            data : data_x

		        }
		    ],
		    grid: {
		    	x: 40,
		    	x2: 20,
		    	y2: 100,
		    	},
		    yAxis : [
		        {
		            type : 'value',
					name:yname

		        }
		    ],
		    series : [
		        {
		            name:name,
		            type:'line',
		            data:data_y,
		            itemStyle:{
		            	normal:{color : '#3398DB'}
		            }
		        }
		    ]
		};    
	 myChart.setOption(option); 
	 window.onresize = function(){
		 myChart.resize();
	 }
	 return myChart;
};

/**
 * 柱形图
 * @param id
 * @param text
 * @param subtext
 * @param name
 * @param data_x
 * @param data_y
 * @returns
 */
Basestat.createBar = function(id,text,subtext,name,lengend,data_x,data_y,xname,yname,color){
	var myChart = echarts.init(document.getElementById(id)); 
	var option = {
		    title : {
		        text: text,
		        subtext: subtext,
		        x:'center'
		    },
		    color: [color],
		    legend: {
		        data:lengend,
		    	x:'right'
		    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
			grid: {
		        left: '3%',
		        right: '3%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
					name:xname,
		            data : data_x,
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
					name:yname
		        }
		    ],
		    series : [
		        {
		            name:lengend,
		            type:'bar',
		            barWidth: '30%',
		            data:data_y
		        }
		    ]
		};  
	 myChart.setOption(option); 
	 window.onresize = function(){
		 myChart.resize();
	 }
	 return myChart;
};



/**
 * 多组柱形图
 * @param id
 * @param text
 * @param subtext
 * @param name
 * @param data_x
 * @param data_y
 * @returns
 */
Basestat.createBarMore = function(id,year1,year2,data_x,data_y1,data_y2){
	var myChart = echarts.init(document.getElementById(id));
	option = {
		title: {
			text: '',
			subtext: ''
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},
		legend: {
			data: [year1, year2]
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: {
			type: 'category',
			data: data_x
		},
		yAxis: {

			type: 'value',
			boundaryGap: [0, 0.01]
		},
		series: [
			{
				name: year1,
				type: 'bar',
				itemStyle:{
					normal:{
						color:'#76d4cf'
					}
				},
				data: data_y1
			},
			{
				name: year2,
				type: 'bar',
		        itemStyle:{
				normal:{
					color:'#8eb1f1'
				  }
			    },
				data: data_y2
			}
		]
	};
	myChart.setOption(option);
	window.onresize = function(){
		myChart.resize();
	}
	return myChart;
};




/**
 * 横向柱形图
 * @param id
 * @param text
 * @param subtext
 * @param name
 * @param data_x
 * @param data_y
 * @returns
 */
Basestat.createBarH = function(id,text,subtext,lengend,data_x,data_y,xname,yname){
	var myChart = echarts.init(document.getElementById(id));
	option = {
		title: {
			text: text,
			subtext: subtext
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},

		grid: {
			left: '3%',
			right: '14%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: {
			type: 'value',
			name:xname,
			boundaryGap: [0, 0.01]
		},
		yAxis: {
			type: 'category',
			name:yname,
			data: data_y
		},
		series: [
			{
				name: lengend,
				type: 'bar',
				data: data_x
			},

		],
		itemStyle:{
			normal:{
				color:'#5fcc7b'
			}
		}
	};
	myChart.setOption(option);
	window.onresize = function(){
		myChart.resize();
	}
	return myChart;
};



/**
 * 环形图
 * @param id
 * @param text
 * @param subtext
 * @param name
 * @param data_x
 * @param data_y
 * @returns
 */
Basestat.createPieMe = function(id,text,subtext,lengend,data_x){
    var myChart = echarts.init(document.getElementById(id));
	var i=0;
	var colors=[ '#4b9bec', '#f5823e', '#8d3ef5', '#30bb27','#282eea'];
	option = {
		tooltip: {
			trigger: 'item'
		},
		legend: {
			top: '5%',
			left: 'center'
		},
		series: [
			{
				name: text,
				type: 'pie',
				radius: ['40%', '70%'],
				itemStyle : {
					normal : {
						color:function(){
							return colors[i++];
						},
						label : {
							show : false
						},
						labelLine : {
							show : false
						}
					},
					emphasis : {
						label : {
							show : true,
							position : 'center',
							textStyle : {
								fontSize : '16',
								fontWeight : 'bold'
							}
						}
					}
				},
				avoidLabelOverlap: false,
				label: {
					show: false,
					position: 'center'
				},
				emphasis: {
					label: {
						show: true,
						fontSize: '40',
						fontWeight: 'bold'
					}
				},
				labelLine: {
					position: 'inner',
					show: false
				},
				data: data_x
			}
		]
	};
    myChart.setOption(option);
    window.onresize = function(){
        myChart.resize();
    }
    return myChart;
};






/**
 * 饼图
 * @param id
 * @param text
 * @param subtext
 * @param name
 * @param dataname
 * @param datavalue
 * @returns
 */
Basestat.createPie = function(id,title,subtitle,name,data_legend,data_value){
	var myChart = echarts.init(document.getElementById(id)); 
	var option = {
		    title : {
		        text: title,
		        subtext: subtitle,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'right',
		        data:data_legend
		    },
		    calculable : true,
		    series : [
		        {
		            name:name,
		            type:'pie',
		            radius : '55%',
		            itemStyle : {
		                normal : {
		                    label : {
		                        show : true,
		                        formatter: '{b} : {c} ({d}%)'
		                    },
		                    labelLine : {
		                        show : true
		                    }
		                },
		                emphasis : {
		                    label : {
		                        show : true,
		                        position : 'center',
		                        textStyle : {
		                            fontSize : '30',
		                            fontWeight : 'bold'
		                        }
		                    }
		                }
		            },
		            data:data_value
		        }
		    ]
		};
	 myChart.setOption(option); 
	 window.onresize = function(){
		 myChart.resize();
	 }
	 return myChart;	                    
}

/**
 * 饼图
 * @param id
 * @param text
 * @param subtext
 * @param name
 * @param dataname
 * @param datavalue
 * @returns
 */
Basestat.createAnnulus = function(id,title,subtitle,sname,data_legend,data_value){
	var myChart = echarts.init(document.getElementById(id)); 
	var option = {
		    title : {
		        text: title,
		        subtext: subtitle,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		    	 orient : 'vertical',
			        x : 'right',
		        data:data_legend
		    },
		    calculable : true,
		    series : [
		        {
		            name:sname,
		            type:'pie',
		            radius : ['50%', '70%'],
		            itemStyle : {
		                normal : {
		                    label : {
		                        show : true,
		                        formatter: '{b} : {c} ({d}%)'
		                    },
		                    labelLine : {
		                        show : true,
		                        position: 'outer'
		                    }
		                },
		                emphasis : {
		                    label : {
		                        show : true,
		                        position : 'center',
		                        textStyle : {
		                            fontSize : '30',
		                            fontWeight : 'bold'
		                        }
		                    }
		                }
		            },
		            data:data_value
		        }
		    ]
		};
	 myChart.setOption(option); 
	 window.onresize = function(){
		 myChart.resize();
	 }
	 return myChart;	                    
}

/**
 * 地图
 * @param id
 * @param text
 * @param subtext
 * @param name
 * @param datavalue
 * @param min
 * @param max
 * @returns
 */
Basestat.createMap = function(id,text,data,minvalue,maxvalue){
	var myChart = echarts.init(document.getElementById(id)); 
	var option = {
            title: {
                text: text,
                left: 'center'
            },
            tooltip : {
		        trigger: 'item'
		    },
		    dataRange: {
		        min: minvalue,
		        max: maxvalue,
		        x: 'left',
		        y: 'bottom',
		        color:['red','yellow'],
		        text:['高','低'],// 文本，默认为数值文本
		        calculable : true
		    },
            series: [
                {
                    type: 'map',
                    mapType: '重庆',
                    label: {
                        emphasis: {
                            textStyle: {
                                color: '#fff'
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            borderColor: '#389BB7',
                            areaColor: '#fff'
                        },
                        emphasis: {
                            areaColor: '#389BB7',
                            borderWidth: 0
                        }
                    },
                    animation: false,
                    data:data
                }
            ]
        };
	 myChart.setOption(option); 
	 window.onresize = function(){
		 myChart.resize();
	 }
	 return myChart;	                    
}
