<template>
    <!--内容部分-->
    <div class="con left">
        <!--数据总概-->
        <div class="con_div">
            <div class="con_div_text left">
                <div class="con_div_text01 left">
                    <img src="../../assets/images/info_2.png" height="200" width="200" class="left text01_img"/>
                    <div class="left text01_div">
                        <p>当月采集数据量(G)</p>
                        <p v-text="monthCountData"></p>
                    </div>
                </div>
                <div class="con_div_text01 right">
                    <img src="../../assets/images/info_2.png" class="left text01_img"/>
                    <div class="left text01_div">
                        <p>今日采集数据量(G)</p>
                        <p v-text="dayCountData"></p>
                    </div>
                </div>
            </div>
            <div class="con_div_text left">
                <div class="con_div_text01 left">
                    <img src="../../assets/images/icon/area_default.png" class="left text01_img"/>
                    <div class="left text01_div">
                        <p>当月发酵生数(L)</p>
                        <p class="sky">12356</p>
                    </div>
                </div>
                <div class="con_div_text01 right">
                    <img src="../../assets/images/icon/area_default.png" class="left text01_img"/>
                    <div class="left text01_div">
                        <p>今日发酵生疏数(L)</p>
                        <p class="sky">12356</p>
                    </div>
                </div>
            </div>
            <div class="con_div_text left">

                <div class="con_div_text01 left">
                    <!--<img src="../../assets/images/info_5.png" class="left text01_img"/>-->
                    <img src="../../assets/images/icon/line_org.png" class="left text01_img"/>
                    <div class="left text01_div">
                        <p>当年原料用量(t)</p>
                        <p class="org">12356</p>
                    </div>
                </div>
                <div class="con_div_text01 right">
                    <img src="../../assets/images/icon/line_org.png" class="left text01_img"/>
                    <div class="left text01_div">
                        <p>累计原料用量(t)</p>
                        <p class="org">12356</p>
                    </div>
                </div>
            </div>
        </div>
        <!--统计分析图-->
        <div class="div_any">
            <div class="left div_any01">
                <div class="div_any_child">
                    <div class="div_any_title"><img src="../../assets/images/title_1.png">设备温度</div>
                    <p id="all_temperature_line" class="p_chart"></p>
                </div>
                <div class="div_any_child">
                    <div class="div_any_title"><img src="../../assets/images/title_2.png">CO2浓度</div>
                    <p id="all_carbonDioxide_line" class="p_chart"></p><!-- style="width:90%;height:90%;"-->
                    <!--<dv-capsule-chart :config="capsuleConfig" style="width:90%;height:90%; margin-top: 5%; margin-left: 5%;" />-->
                </div>
            </div>
            <div class="div_any02 left ">
                <div class="div_any_child div_height"><!-- style="background-color: #272c38;"-->
                    <div class="div_any_title any_title_width"><img src="../../assets/images/title_0.png">种植基地</div>
                    <div  id="mapChart" style="width:97.5%;height:96%;display: inline-block;padding-left: 1.25%;padding-top:3%">
                        <!-- :mapStyle="mapStyle"-->
                        <baidu-map map-type="BMAP_NORMAL_MAP" class="map" :mapStyle="{style:'midnight'}" :center="centerStr" :zoom="zoom" :scroll-wheel-zoom="true">
                            <bm-marker v-for="(item, key) in baseList" :key="key" :position="item" :dragging="true" @click="clickMarker(item)"></bm-marker><!--animation="BMAP_ANIMATION_BOUNCE"-->
                            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
                        </baidu-map>
                        <!-- 三维地图：BMAP_PERSPECTICE_MAP，混合：BMAP_HYBRID_MAP，卫星：BMAP_SATELLITE_MAP，地图：BMAP_NORMAL_MAP -->
                    </div>

                </div>
            </div>
            <div class="right div_any01">
                <div class="div_any_child">
                    <div class="div_any_title"><img src="../../assets/images/title_3.png">罐内液位</div>
                    <p id="levelChart" class="p_chart"></p>
                </div>
                <div class="div_any_child">
                    <div class="div_any_title"><img src="../../assets/images/title_4.png">报警信息</div>
                   <!-- <p id="test" class="p_chart" style="width:90%;height:90%; margin-left: 5%;"></p>-->
                    <div class="table_p">
                        <table>
                            <thead><tr>
                                <th>序号</th>
                                <th>内容</th>
                                <th>时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr><td>1</td><td>温度过低警报</td><td>2020-12-08</td></tr>
                            <tr><td>2</td><td>温度过低警报</td><td>2020-12-07</td></tr>
                            <tr><td>3</td><td>温度过高警报</td><td>2020-12-07</td></tr>
                            <tr><td>4</td><td>温度过高警报</td><td>2020-12-05</td></tr>
                            <tr><td>5</td><td>温度过高警报</td><td>2020-12-05</td></tr>
                            <tr><td>6</td><td>温度过高警报</td><td>2020-12-05</td></tr>
                            <tr><td>7</td><td>温度过低警报</td><td>2020-12-05</td></tr>
                            <tr><td>8</td><td>温度过低警报</td><td>2020-12-01</td></tr>
                            <tr><td>9</td><td>温度过低警报</td><td>2020-12-01</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!--窗口-->
        <div id="el-dialog" class="" v-if="baseFlag">
          <div class="xc_layer"></div>
          <div class="popBox" id="printView">
            <div class="ttBox"><span class="tt" id="reportTitle" v-text="baseInfo.baseName"></span><img src="../../assets/images/close.png" style="width: 30px;float: right;cursor: pointer;" title="关闭弹窗" @click="closeBaseFlag" class="close" /></div>
            <div class="txtBox" id="el-dialog_body" style="margin: 0px;">
              <div style="height:100%;width: 98%;margin-left: 1%;">
                <div class="left div_any01" style="width: 64%;">
                  <div class="box_div_any_child">
                    <div class="div_any_title"><div type="text" class="demo-input" id="date1" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="../../assets/images/title_4.png">板块1</div>
                    <p id="lineChart3" class="p_chart"></p>
                  </div>
                  <div class="box_div_any_child">
                    <div class="div_any_title"><div type="text" class="demo-input" id="date2" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="../../assets/images/title_7.png">板块3</div>
                    <p id="lineChart4" class="p_chart"></p>
                  </div>
                </div>
                <div class="left div_any01"  style="width: 32%;">
                  <div class="box_div_any_child">
                    <div class="div_any_title"><div type="text" class="demo-input" id="date3" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="../../assets/images/title_18.png">板块2</div>
                    <p id="pieChart2" class="p_chart"></p>
                  </div>
                  <div class="box_div_any_child">
                    <div class="div_any_title"><div type="text" class="demo-input" id="date4" style="display: inline-block;cursor: pointer;margin-right: 16px;"></div><img src="../../assets/images/title_20.png">板块4</div>
                    <p id="pieChart3" class="p_chart"></p>
                  </div>
                </div>

              </div>
            </div>
          </div>
        </div>

    </div>
</template>

<script>
    import { getRequest } from '../../request/request'

    export default {
        name: "CenterMap",
        data() {
            return {
                baseInfo: {},
                baseFlag: false,
                baseList: [],
                mapStyle: {style:'midnight'},
                zoom: 19,
                centerStr: {
                    lng: 119.703567, lat: 29.777242
                },
                capsuleConfig: {
                    data: [
                        {
                            name: '南阳',
                            value: 80
                        },
                        {
                            name: '周口',
                            value: 80
                        },
                        {
                            name: '漯河',
                            value: 80
                        },
                        {
                            name: '郑州',
                            value: 80
                        },
                        {
                            name: '西峡',
                            value: 80
                        },{
                            name: '南阳',
                            value: 80
                        },
                        {
                            name: '周口',
                            value: 80
                        },
                        {
                            name: '漯河',
                            value: 80
                        },
                        {
                            name: '郑州',
                            value: 80
                        },
                        {
                            name: '西峡',
                            value: 80
                        },{
                            name: '西峡',
                            value: 80
                        },
                    ],
                    colors: ['#fc97af', '#87f7cf', '#f7f494', '#72ccff', '#f7c5a0', '#d4a4eb', '#d2f5a6', '#76f2f2', '#2ec7c9', '#ffb980', '#d87a80'],
                    unit: '%',
                    showValue: true
                },
                option: {
                    /*backgroundColor: 'rgba(51,51,51,1)',*/
                    //图标位置
                    grid: {
                        top: "5%",
                        left: "20%",
                        bottom: "2%"
                    },
                    xAxis: {
                        show: false
                    },
                    yAxis: [
                        {
                            show: true,
                            data: ["发酵罐1", "发酵罐2", "发酵罐3", "发酵罐4","发酵罐5", "发酵罐6", "发酵罐7", "发酵罐8","发酵罐9", "发酵罐10", "发酵罐11"],
                            inverse: true,
                            axisLine: {
                                show: false
                            },
                            splitLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            axisLabel: {
                                color: "#fff",

                                rich: {
                                    lg: {
                                        backgroundColor: "#339911",
                                        color: "#fff",
                                        borderRadius: 15,
                                        // padding: 5,
                                        align: "center",
                                        width: 15,
                                        height: 15
                                    }
                                }
                            }
                        },
                        {
                            show: true,
                            inverse: true,
                            data: [100,100,100,100,100,100,100,100,100,100,100],
                            axisLabel: {
                                textStyle: {
                                    fontSize: 12,
                                    color: "#fff"
                                }
                            }
                        }
                    ],
                    series: [
                        {
                            name: "条",
                            type: "bar",
                            yAxisIndex: 0,
                            data: [80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80],
                            barCategoryGap: 50,
                            barWidth: 10,
                            itemStyle: {
                                normal: {
                                    barBorderRadius: 20,
                                    color: function(params) {
                                        var myColor = ['#d87c7c', '#919e8b', '#d7ab82', '#6e7074', '#61a0a8', '#efa18d', '#787464', '#cc7e63', '#724e58', '#4b565b', '#27727b'];
                                        var num = myColor.length;
                                        return myColor[params.dataIndex % num];
                                    }
                                }
                            },
                            label: {
                                normal: {
                                    show: true,
                                    position: "inside",
                                    formatter: "{c}%"
                                }
                            }
                        },
                        {
                            name: "框",
                            type: "bar",
                            yAxisIndex: 1,
                            barCategoryGap: 50,
                            data: [100, 100, 100, 100,100, 100, 100, 100,100, 100, 100],
                            barWidth: 15,
                            itemStyle: {
                                normal: {
                                    color: "none",
                                    borderColor: "#00c1de",
                                    borderWidth: 3,
                                    barBorderRadius: 15
                                }
                            }
                        }
                    ]
                },
                temperatureOption: {
                    legend: {
                        /*color:['#FF8CA1','#239EE7','#D0E4E7','#044563','#046289','#FF8CA1','#4C76C9','#CCD6D8',
                            '#FF2E43','#26FF26','#26FF26'],*/
                        data: [],
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    grid:{
                        left: '20%',
                        bottom: 20,
                        top: 80
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    xAxis: {
                        type: 'category',
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                        show: true,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#fff',  //更改坐标轴文字颜色
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                width: 2,
                                color: '#0188B5'
                            }
                        },
                        splitLine: {    //网格线
                            lineStyle: {
                                type: 'dashed',    //设置网格线类型 dotted：虚线   solid:实线
                                color: '#034c6a'
                            },
                            show: false //隐藏或显示
                        }
                    },
                    yAxis: {
                        type: 'value',
                        show: true,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#fff',  //更改坐标轴文字颜色
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                width: 2,
                                color: '#0188B5'
                            }
                        },
                        splitLine: {    //网格线
                            lineStyle: {
                                type: 'dashed',    //设置网格线类型 dotted：虚线   solid:实线
                                color: '#034c6a'
                            },
                            show: false //隐藏或显示
                        }
                    },
                    series: [{
                        name: '',
                        data: [],
                        color: '#fc97af',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#87f7cf',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#f7f494',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#72ccff',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#f7c5a0',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#d4a4eb',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#d2f5a6',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#76f2f2',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#2ec7c9',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#ffb980',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#d87a80',
                        type: 'line'
                    }]
                },
                carbonDioxideOption: {
                    legend: {
                        /*color:['#FF8CA1','#239EE7','#D0E4E7','#044563','#046289','#FF8CA1','#4C76C9','#CCD6D8',
                            '#FF2E43','#26FF26','#26FF26'],*/
                        data: [],
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    grid:{
                        left: '20%',
                        bottom: 20,
                        top: 80
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    xAxis: {
                        type: 'category',
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                        show: true,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#fff',  //更改坐标轴文字颜色
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                width: 2,
                                color: '#0188B5'
                            }
                        },
                        splitLine: {    //网格线
                            lineStyle: {
                                type: 'dashed',    //设置网格线类型 dotted：虚线   solid:实线
                                color: '#034c6a'
                            },
                            show: false //隐藏或显示
                        }
                    },
                    yAxis: {
                        type: 'value',
                        show: true,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#fff',  //更改坐标轴文字颜色
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                width: 2,
                                color: '#0188B5'
                            }
                        },
                        splitLine: {    //网格线
                            lineStyle: {
                                type: 'dashed',    //设置网格线类型 dotted：虚线   solid:实线
                                color: '#034c6a'
                            },
                            show: false //隐藏或显示
                        }
                    },
                    series: [{
                        name: '',
                        data: [],
                        color: '#fc97af',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#87f7cf',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#f7f494',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#72ccff',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#f7c5a0',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#d4a4eb',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#d2f5a6',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#76f2f2',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#2ec7c9',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#ffb980',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    },{
                        name: '',
                        data: [],
                        color: '#d87a80',
                        type: 'line',
                        smooth: 0.4,
                        areaStyle: {}
                    }]
                },
                bashOption: {
                   /* backgroundColor: '#0E1327',*/
                    tooltip: {
                        formatter: "{a} <br/>{b} : {c}%"
                    },

                    series: [{
                        name: "最外部进度条",
                        type: "gauge",
                        radius: '70%',
                        splitNumber: 100,
                        axisLine: {
                            lineStyle: {
                                color: [
                                    [1, '#33507A']
                                ],
                                width: 10,
                                shadowColor: 'rgba(145,207,255,.5)',
                                shadowBlur: 6,
                                shadowOffsetX: 0,
                            }
                        },
                        axisLabel: {
                            show: false,
                        },
                        axisTick: {
                            show: false,

                        },
                        splitLine: {
                            show: false,
                        },
                        itemStyle: {
                            show: false,
                        },
                        detail: {
                            show: false
                        },
                        title: { //标题
                            show: false,
                        },
                        data: [{
                            name: "title",
                            value: 44,
                        }],
                        pointer: {
                            show: false,
                        },
                        animationDuration: 4000,
                    },
                        {
                            name: "CO2浓度",
                            type: "gauge",
                            // center: ['50%', '50%'],
                            radius: '54%',
                            z: 4,
                            splitNumber: 10,
                            axisLine: {
                                lineStyle: {
                                    color: [
                                        [44 / 100, new this.$echarts.graphic.LinearGradient(
                                            0, 1, 0, 0, [{
                                                offset: 0,
                                                color: 'rgba(0,191,194,.1)',
                                            }, {
                                                offset: 0.5,
                                                color: 'rgba(0,191,194,0.4)',
                                            },
                                                {
                                                    offset: 1,
                                                    color: 'rgba(0,191,194,0.8)',
                                                }
                                            ]
                                        )],
                                        [
                                            1, 'rgba(28,128,245,.0)'
                                        ]
                                    ],
                                    width: 170
                                }
                            },
                            axisLabel: {
                                show: false,
                            },
                            axisTick: {
                                show: false,

                            },
                            splitLine: {
                                show: false,
                            },
                            itemStyle: {
                                show: false,
                            },
                            detail: {
                                formatter: function(value) {
                                    if (value !== 0) {
                                        var num = Math.round(value);
                                        return parseInt(num).toFixed(0) + "";
                                    } else {
                                        return 0;
                                    }
                                },
                                offsetCenter: [0, 50],
                                textStyle: {
                                    padding: [0, 0, 0, 0],
                                    fontSize: 30,
                                    fontWeight: '700',
                                    color: '#468EFD'
                                }
                            },
                            title: { //标题
                                show: false,
                                offsetCenter: [0, 46], // x, y，单位px
                                textStyle: {
                                    color: "rgba(46, 143, 255, 1)",
                                    fontSize: 30, //表盘上的标题文字大小
                                    fontWeight: 700,
                                    fontFamily: 'PingFangSC'
                                }
                            },
                            data: [{
                                name: 44,
                                value: 44,
                            }],
                            // eslint-disable-next-line no-dupe-keys
                            itemStyle: {
                                normal: {
                                    color: 'rgba(46, 143, 255, 1)'
                                }
                            },
                            pointer: {
                                show: true,
                                length: '80%',
                                radius: '20%',
                                width: 3, //指针粗细
                            },
                            animationDuration: 4000,
                        },
                        // 内圆
                        {
                            "name": '内圆',
                            "type": 'pie',
                            "hoverAnimation": false,
                            "legendHoverLink": false,
                            "radius": '4%',
                            "z": 4,

                            "labelLine": {
                                "normal": {
                                    "show": false
                                }
                            },
                            "data": [{
                                "value": 0,
                            }, {
                                "value": 10,

                                itemStyle: {
                                    normal: {
                                        color: "#0E1327"
                                    },
                                    emphasis: {
                                        color: "#0E1327"
                                    }
                                }
                            }]
                        },
                        // 圆环
                        {
                            "name": '小圆形',
                            "type": 'pie',
                            "hoverAnimation": false,
                            "legendHoverLink": false,
                            "radius": ['4%', '3%'],
                            "z": 5,
                            "labelLine": {
                                "normal": {
                                    "show": false
                                }
                            },
                            "data": [{
                                "value": 0,
                            }, {
                                "value": 10,

                                itemStyle: {
                                    normal: {
                                        color: "rgba(46, 143, 255, 1)",
                                    },

                                }
                            }]
                        },
                        {
                            name: '外部刻度',
                            type: 'gauge',
                            //  center: ['20%', '50%'],
                            radius: '60%',
                            min: 0, //最小刻度
                            max: 100, //最大刻度
                            splitNumber: 10, //刻度数量
                            startAngle: 225,
                            endAngle: -45,
                            axisLine: {
                                show: false,
                                lineStyle: {
                                    width: 1,
                                    color: [
                                        [1, 'rgba(0,0,0,0)']
                                    ]
                                }
                            }, //仪表盘轴线
                            axisLabel: {
                                show: true,
                                color: 'rgba(255,255,255,.5)',
                                distance: 25,
                                formatter: function(v) {
                                    switch (v + '') {
                                        case '0':
                                            return '0';
                                        case '10':
                                            return '10';
                                        case '20':
                                            return '20';
                                        case '30':
                                            return '30';
                                        case '40':
                                            return '40';
                                        case '50':
                                            return '50';
                                        case '60':
                                            return '60';
                                        case '70':
                                            return '70';
                                        case '80':
                                            return '80';
                                        case '90':
                                            return '90';
                                        case '100':
                                            return '100';
                                    }
                                }
                            }, //刻度标签。
                            axisTick: {
                                show: true,
                                splitNumber: 7,
                                lineStyle: {
                                    color: '#42E5FB', //用颜色渐变函数不起作用
                                    width: 2,
                                },
                                length: 8
                            }, //刻度样式
                            splitLine: {
                                show: true,
                                length: 15,
                                lineStyle: {
                                    color: '#42E5FB', //用颜色渐变函数不起作用
                                }
                            }, //分隔线样式
                            detail: {
                                show: false
                            },
                            pointer: {
                                show: true
                            }
                        },
                    ]
                },
                url: {
                    topTen: '/system/mbpDevEquipment/queryAllDevEquipmentList',
                    queryBaseList: '/system/mbpPlantationBase/listAll',
                    dayCountData: '/system/mbpMqttData/dayCountData',
                    monthCountData: '/system/mbpMqttData/monthCountData'
                },
                dayCountData: 0,
                monthCountData: 0,
                DefaultSeries: {
                    arr: [{
                        name: '',
                        data: [],
                        color: '#fc97af',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#87f7cf',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#f7f494',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#72ccff',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#f7c5a0',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#d4a4eb',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#d2f5a6',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#76f2f2',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#2ec7c9',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#ffb980',
                        type: 'line'
                    },{
                        name: '',
                        data: [],
                        color: '#d87a80',
                        type: 'line'
                    }]
                },
                DefaultSeriesArea: {
                    arr: [{
                        name: '',
                        data: [],
                        color: '#fc97af',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#87f7cf',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#f7f494',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#72ccff',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#f7c5a0',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#d4a4eb',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#d2f5a6',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#76f2f2',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#2ec7c9',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#ffb980',
                        type: 'line',
                        smooth: 0.4,
                    },{
                        name: '',
                        data: [],
                        color: '#d87a80',
                        type: 'line',
                        smooth: 0.4,
                    }]
                }
            }
        },
        methods: {
            /*当月数据采集量*/
            monthCountDataFunction(){
                getRequest(this.url.monthCountData, null).then(res => {
                    if (res.data.success) {
                        this.monthCountData = res.data.result
                    }
                }).catch(exc => {
                    console.log('查询当月数据采集量发生异常！异常信息：' + exc)
                })
            },
            /*今日数据采集量*/
            dayCountDataFunction(){
                getRequest(this.url.dayCountData, null).then(res => {
                    if (res.data.success) {
                        this.dayCountData = res.data.result
                    }
                }).catch(exc => {
                    console.log('查询当月数据采集量发生异常！异常信息：' + exc)
                })
            },
            closeBaseFlag() {
                this.baseFlag = false
            },
            clickMarker(item) {
                this.baseInfo = item
                this.baseFlag = true
                // alert(JSON.stringify(item))
            },
            /*种植基地*/
            queryBaseList(){
                getRequest(this.url.queryBaseList, null).then(res => {
                    if (res.data.success) {
                        this.baseList.splice(0)
                        var result = res.data.result
                        this.baseList = result
                        this.centerStr = {
                            lng: this.baseList[0].lng,
                            lat: this.baseList[0].lat
                        }
                        /*for (let i = 0; i < result.length; i++) {
                            this.baseList.push({
                                lng: result[i].lng,
                                lat: result[i].lat
                            })
                        }*/
                    }
                }).catch(exc => {
                    console.log('查询种植基地发生异常！异常信息：' + exc)
                })
            },
            /*温度变化*/
            drawTemperatureLine(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('all_temperature_line'))
                getRequest(this.url.topTen, null).then(res => {
                    if (res.data.success) {
                        this.temperatureOption.xAxis.data.splice(0) // x轴坐标替换
                        this.temperatureOption.series.splice(0) // 值替换
                        this.temperatureOption.series = this.DefaultSeries.arr
                        /*CO2*/
                        this.carbonDioxideOption.xAxis.data.splice(0) // x轴坐标替换
                        this.carbonDioxideOption.series.splice(0) // 值替换
                        this.carbonDioxideOption.series = this.DefaultSeriesArea.arr
                        /*CO2*/
                        var result = res.data.result;
                        this.temperatureOption.legend.data.splice(0)
                        /*CO2*/
                        this.carbonDioxideOption.legend.data.splice(0)
                        /*CO2*/
                        for (let i = 0; i <result.length; i++) {
                            for (let j = 0; j < result[i].length; j++) {
                                if (i == 0) {
                                    this.temperatureOption.xAxis.data.push(result[i][j].createTime.substring(11,19))

                                    /*CO2*/
                                    this.carbonDioxideOption.xAxis.data.push(result[i][j].createTime.substring(11,19))
                                    /*CO2*/
                                }
                                if (j == 0) {
                                    this.temperatureOption.legend.data.push(result[i][j].equipmentName)
                                    this.temperatureOption.series[i].name = result[i][j].equipmentName
                                    /*CO2*/
                                    this.carbonDioxideOption.legend.data.push(result[i][j].equipmentName)
                                    this.carbonDioxideOption.series[i].name = result[i][j].equipmentName
                                    /*CO2*/
                                }
                                this.temperatureOption.series[i].data.push(result[i][j].varValue)
                                /*CO2*/
                                //this.carbonDioxideOption.series[i].data.push(result[i][j].varValue)
                                //this.carbonDioxideOption.series[i].data.push(Math.floor(Math.random() * (3 - 1 + 1) + 1))
                                this.carbonDioxideOption.series[i].data.push(Math.floor(Math.random() * (2 - 1 + 1)))
                                /*CO2*/
                            }
                        }
                        // 绘制图表
                        myChart.setOption(this.temperatureOption);
                        this. drawCarbonDioxideLine()
                    }
                }).catch(exc => {
                    console.log('查询温度变化记录发生异常！异常信息：' + exc)
                })
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            },
            /*CO2*/
            drawCarbonDioxideLine(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('all_carbonDioxide_line'))
                // 绘制图表
                myChart.setOption(this.carbonDioxideOption);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            },
            /*胶囊图*/
            drawChar() {
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('levelChart'))

                // 绘制图表
                myChart.setOption(this.option);

                window.addEventListener("resize", function () {
                    myChart.resize();
                });
            },
            /*CO2浓度*/
            drawBash(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('bash'))
                // 绘制图表
                myChart.setOption(this.bashOption);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            },
            drawTest(){

                var TP_value = 30;
                var kd = [];
                var Gradient = [];
                var leftColor = '';
                var showValue = '';
                var boxPosition = [30, 0];
                var TP_txt = ''
                // 刻度使用柱状图模拟，短设置1，长的设置3；构造一个数据
                for (var i = 0, len = 135; i <= len; i++) {
                    if (i < 10 || i > 130) {
                        kd.push('')
                    } else {
                        if ((i - 10) % 20 === 0) {
                            kd.push('-3');
                        } else if ((i - 10) % 4 === 0) {
                            kd.push('-1');
                        } else {
                            kd.push('');
                        }
                    }

                }
                //中间线的渐变色和文本内容
                if (TP_value > 20) {
                    TP_txt = '温度偏高';
                    Gradient.push({
                        offset: 0,
                        color: '#93FE94'
                    }, {
                        offset: 0.5,
                        color: '#E4D225'
                    }, {
                        offset: 1,
                        color: '#E01F28'
                    })
                } else if (TP_value > -20) {
                    TP_txt = '温度正常';
                    Gradient.push({
                        offset: 0,
                        color: '#93FE94'
                    }, {
                        offset: 1,
                        color: '#E4D225'
                    })
                } else {
                    TP_txt = '温度偏低';
                    Gradient.push({
                        offset: 1,
                        color: '#93FE94'
                    })
                }
                if (TP_value > 62) {
                    showValue = 62
                } else {
                    if (TP_value < -60) {
                        showValue = -60
                    } else {
                        showValue = TP_value
                    }
                }
                if (TP_value < -10) {
                    boxPosition = [65, -120];
                }
                leftColor = Gradient[Gradient.length - 1].color;
                // 因为柱状初始化为0，温度存在负值，所以加上负值60和空出距离10
                var option = {
                   /* backgroundColor: '#0C2F6F',*/
                    title: {
                        text: '温度计',
                        show: false
                    },
                    grid: {
                        left: '35%',
                        borderColor: '#fff'
                    },
                    yAxis: [{
                        show: false,
                        data: [],
                        min: 0,
                        max: 135,
                        axisLine: {
                            show: false
                        }
                    }, {
                        show: false,
                        min: 0,
                        max: 50,
                    }, {
                        type: 'category',
                        data: ['', '', '', '', '', '', '', '', '', '', '°C'],
                        position: 'left',

                        offset: -60,
                        axisLabel: {
                            fontSize: 10,
                            color: 'white'
                        },
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                    }],
                    xAxis: [{
                        show: false,
                        min: -10,
                        max: 80,
                        data: []
                    }, {
                        show: false,
                        min: -10,
                        max: 80,
                        data: []
                    }, {
                        show: false,
                        min: -10,
                        max: 80,
                        data: []
                    }, {
                        show: false,
                        min: -5,
                        max: 80,

                    }],
                    series: [{
                        name: '条',
                        type: 'bar',
                        // 对应上面XAxis的第一个对)象配置
                        xAxisIndex: 0,
                        data: [{
                            value: (showValue + 70),
                            label: {
                                normal: {
                                    show: true,
                                    position: boxPosition,
                                    backgroundColor: {
                                        image: 'plugin/subway_beijing/images/power/bg5Valuebg.png', //文字框背景图
                                    },
                                    width: 100,
                                    height: 50,
                                    formatter: '{back| ' + TP_value + ' }{unit|°C}\n{downTxt|' + TP_txt + '}',
                                    rich: {
                                        back: {
                                            align: 'center',
                                            lineHeight: 25,
                                            fontSize: 20,
                                            fontFamily: 'digifacewide',
                                            color: leftColor
                                        },
                                        unit: {
                                            fontFamily: '微软雅黑',
                                            fontSize: 10,
                                            lineHeight: 25,
                                            color: leftColor
                                        },
                                        downTxt: {
                                            lineHeight: 25,
                                            fontSize: 16,
                                            align: 'center',
                                            color: '#fff'
                                        }
                                    }
                                }
                            }
                        }],
                        barWidth: 6,
                        itemStyle: {
                            normal: {
                                color: new this.$echarts.graphic.LinearGradient(0, 1, 0, 0, Gradient)
                            }
                        },
                        z: 2
                    }, {
                        name: '白框',
                        type: 'bar',
                        xAxisIndex: 1,
                        barGap: '-100%',
                        data: [134],
                        barWidth: 10,
                        itemStyle: {
                            normal: {
                                color: '#0C2E6D',
                                barBorderRadius: 25,
                            }
                        },
                        z: 1
                    }, {
                        name: '外框',
                        type: 'bar',
                        xAxisIndex: 2,
                        barGap: '-100%',
                        data: [135],
                        barWidth: 16,
                        itemStyle: {
                            normal: {
                                color: '#4577BA',
                                barBorderRadius: 25,
                            }
                        },
                        z: 0
                    }, {
                        name: '圆',
                        type: 'scatter',
                        hoverAnimation: false,
                        data: [0],
                        xAxisIndex: 0,
                        symbolSize: 20,
                        itemStyle: {
                            normal: {
                                color: '#93FE94',
                                opacity: 1,
                            }
                        },
                        z: 2
                    }, {
                        name: '白圆',
                        type: 'scatter',
                        hoverAnimation: false,
                        data: [0],
                        xAxisIndex: 1,
                        symbolSize: 25,
                        itemStyle: {
                            normal: {
                                color: '#0C2E6D',
                                opacity: 1,
                            }
                        },
                        z: 1
                    }, {
                        name: '外圆',
                        type: 'scatter',
                        hoverAnimation: false,
                        data: [0],
                        xAxisIndex: 2,
                        symbolSize: 30,
                        itemStyle: {
                            normal: {
                                color: '#4577BA',
                                opacity: 1,
                            }
                        },
                        z: 0
                    }, {
                        name: '刻度',
                        type: 'bar',
                        yAxisIndex: 0,
                        xAxisIndex: 3,
                        label: {
                            normal: {
                                show: true,
                                position: 'left',
                                distance: 10,
                                color: 'white',
                                fontSize: 14,
                                formatter: function(params) {
                                    if (params.dataIndex > 130 || params.dataIndex < 10) {
                                        return '';
                                    } else {
                                        if ((params.dataIndex - 10) % 20 === 0) {
                                            return params.dataIndex - 70;
                                        } else {
                                            return '';
                                        }
                                    }
                                }
                            }
                        },
                        barGap: '-100%',
                        data: kd,
                        barWidth: 1,
                        itemStyle: {
                            normal: {
                                color: 'white',
                                barBorderRadius: 120,
                            }
                        },
                        z: 0
                    }]
                }

                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('test'))
                // 绘制图表
                myChart.setOption(option);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            }
        },
        mounted() {
            this.drawTemperatureLine()
            this.drawChar()
            this.queryBaseList()
            this.monthCountDataFunction()
            this.dayCountDataFunction()
            // this.drawBash()

            // this.drawTest()
        }
    }
</script>

<style scoped>
    .map {
        width: 99%;
        height: 100%;
    }
    .left{
        float: left;
    }
    .right{
        float: right;
    }
    /*.BMap_cpyCtrl {
        display: none;
    }

    .anchorBL {
        display: none;
    }*/
    .yui-toast-mask {
        position: fixed;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0);
        top: 0;
        left: 0;
        z-index: 1000;
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        -webkit-box-align: center;
        -webkit-align-items: center;
        align-items: center;
    }
    .clear{
        clear: both;
    }
    .con{
        width: 100%;
        background-color: #081832;
        padding-top: 20px;
        padding-bottom: 20px;
    }
    .con1{
        width: 100%;
        background-color: #081832cc;
        /*padding-bottom: 4px;*/
        box-sizing: border-box;
        overflow: auto;

    ;
    }
    .find_expend{
        display: none;
    }
    .con1::before{
        content: "";
        display: block;
        clear: both;
        visibility: hidden;
        height: 0;
    }
    .select_time{
        width: 140px;
        height: 36px;

        margin-bottom: 25px;
        margin-left: 1%;
        padding-left: 20px;
    ;

    }
    .select_time img{
        height: 18px;
        margin-top: 9px;
    }
    .select_time input{
        border: none;
        background-color: transparent;
        width: 80px;
        height: 20px;
        top:-5px;
        margin-left: 10px;
        position: relative;
        text-indent: 1em;
        outline: none;
    }
    .con_div{
        height: 110px;
        width: 98%;
        margin-left: 1%;
        margin-bottom: 25px;
    }
    .con_div_text{
        height: 100%;
        background-color: #034c6a;
        width: 32%;
        margin-right: 1.3%;
    }
    .con_div_text01{
        width: 50%;
        height: 100%;
    }
    .text01_img{
        width: 40px;
        height: 40px;
        margin-left: 5%;
        margin-top: 35px;
    }
    .text01_div{
        margin-top: 15px;
        margin-left: 5%;
        text-align: center;

    }
    .text01_div p{
        line-height: 35px;
    }
    .text01_div p:nth-child(1){
        font-size: 13px;
        color: #ffffff;
    }
    .text01_div p:nth-child(2){
        font-size: 28px;
        color: #ffff43;
        font-weight: 600;

    }
    .red{
        color: red !important;
    }
    .sky{
        color: #25f3e6 !important;
    }
    .org{
        color: #ff4e4e !important;
    }
    .div_any{
        width: 98%;
        margin-left: 1%;
        margin-bottom: 25px;
        height: 610px;
    }
    .div_any01{
        width: 23%;
        margin-right: 2%;
    }
    .div_any02{
        width: 48%;
        margin-right: 2%;
    }
    .div_any03{
        width: 98%;
        margin: 15px auto;

    }
    .div_any_child{
        width: 100%;
        height: 330px;
        box-shadow: -10px 0px 15px #034c6a inset, /*å·¦è¾¹é˜´å½±*/
        0px -10px 15px #034c6a inset, /*ä¸Šè¾¹é˜´å½±*/
        10px 0px 15px #034c6a inset,
        0px 10px 15px #034c6a inset;
        border: 1px solid #034c6a;
        box-sizing: border-box;
        position: relative;
        margin-top: 25px;
    }
    .div_any_child01{
        width: 48%;

        box-shadow: -10px 0px 15px #034c6a inset, /*å·¦è¾¹é˜´å½±*/
        0px -10px 15px #034c6a inset, /*ä¸Šè¾¹é˜´å½±*/
        10px 0px 15px #034c6a inset,
        0px 10px 15px #034c6a inset;
        border: 1px solid #034c6a;
        box-sizing: border-box;
        position: relative;
        margin-right: 2%;

    }
    .div_any_child01_wh{
        width: 98% !important;
    }
    .div_height01{
        height: auto !important;
        padding: 5px;
    }
    .char_table{
        height: 200px;
    }
    .p_chart{
        height: 288px;

        padding: 5px 10px;

        margin-top: 15px;

    }
    #map_div{
        width: 96%;
        height: 94%;
    }
    .div_height{
        height:685px !important;
    }
    .div_any_title{
        background-color: #034c6a;
        border-radius: 18px;
        position: absolute;
        height: 35px;
        width: 60%;
        top:-15px;
        color: #ffffff;
        font-weight: bold;
        font-size: 16px;
        left: 20%;
        line-height: 35px;
        text-align: center;
    }
    .div_any_title img{
        width: 18px;
        height: 18px;
        position: relative;
        top:2px;
        margin-right: 5px;
    }
    .any_title_width{
        width: 30% !important;
        left: 35% !important;
    }
    .div_table{
        width: 98%;
        margin-left: 1%;
        margin-bottom: 25px;
        height: 280px;
    }
    .div_table_box{
        width: 23%;
        margin-right: 2%;
    }
    .table_p{
        height: 93%;
        /*margin-top: 7%;*/
        margin-top: 3%;
        position: relative;


    }.table_p01{
         height:auto !important;
         margin-top: 0!important;
         position: relative;


     }
    .table_p01 table td{
        padding: 6px 0;
    }
    .table_p table{
        width: 100%;
        height: 100%;
        border-collapse: collapse;

        position: absolute;
        text-align: center;
    }
    .table_p table thead th{
        color: #61d2f7;
        font-size: 14px;
        font-weight: 600;
        padding-top: 5px;
        padding-bottom: 5px;
    }
    .table_p table tbody{
        color: #ffffff;
        font-size: 13px;
    }
    .table_p table tbody tr:nth-child(2n+1){
        background-color: #072951;
        box-shadow: -10px 0px 15px #034c6a inset,
        10px 0px 15px #034c6a inset;
    }

    .car_left{
        width: 18%;
        height: 100%;
        background-color:  #081832;
    }
    .car_center{

        height: 100%;
        background-color:  #081832;
        width: 50.5%;
        margin-left: 0.5%;
    }
    .car_center video{
        width: 49.5%;
        height: 49%;


        box-sizing: border-box;
        box-shadow: -10px 0px 15px #034c6a inset,
        10px 0px 15px #034c6a inset;
        float: left;
    }
    .magin_top{
        margin-top: 1%;
    }
    .magin_left{
        margin-left: 1%;
    }
    .car_right{
        width: 31%;
        height: 100%;
    }
    .text_center{
        text-align: center;
    }
    .carNo_input{
        width: 75%;
        height: 25px;
        margin-top: 5px;
        margin-bottom: 5px;
        text-indent: 2em;
        margin-left: 5%;

        border:1px solid #072951;
        border-radius: 3px;
    }
    .find_but{
        height: 25px;
        background-image: url("../../assets/images/find.png");
        width: 25px;
        background-repeat: no-repeat;
        background-size: 100% 100%;
        position: relative;
        top:8px;
        border:none;
        background-color: transparent;
    }
    .find_but1{
        height: 25px;
        background-image: url("../../assets/images/find.png");
        width: 25px;
        background-repeat: no-repeat;
        background-size: 100% 100%;
        position: relative;
        top:8px;
        border:1px solid #4b8df8;
        background-color: transparent;
    }
    .set_list{
        padding: 10px 5%;
        color: #eee;
        font-size: 14px;
    }
    .list_i{
        width: 16px;
        height: 16px;
        display: inline-block;

        background-image: url("../../assets/images/nav_1.png");
        top:3px;
        position: relative;
    }
    .left_up{

        height: 58%;

    }
    .left_table{

        height: 98%;

        border-radius: 5px;
        width:18%!important;
        background-color:  #081832;


    }
    .right_table{
        height: 98%;

        border-radius: 5px;
        width:81.5%!important;
        background-color:  #081832;
        margin-left: 0.5%;
    }
    .center_table{
        height: 100%;
        border-radius: 5px;
        width:100%;
        background-color:  #081832;
        margin: 0 auto;
    }
    .bow_shadow{
        box-shadow: -8px 0px 10px #034c6a inset,
        8px 0px 10px #034c6a inset;
        width: 100%;
        box-sizing: border-box;
    }

    .ztree li a{
        color: #FFFFff !important;
    }
    .left_down{
        height: 40%;
        margin-top: 2%;
    }
    .tab_a{
        width: 45%;
        height: 30px;
        line-height: 30px;
        color: #FFFFff;
        background-color: #667fa7;
        display: inline-block;
        margin-right: 1%;
        margin-top: 5px;
        font-size: 14px;
    }
    .tab_aActive{
        background-color:#034c6a !important;
        font-weight: 600;
    }
    .car_content{
        margin-left: 5%;

        font-size: 13px;
        color: #eeeeee;
    }
    .car_content p{
        padding-top: 5px;
        padding-bottom: 5px;
    }
    .table2_find{
        background-color: #072951;
        box-shadow: -10px 0px 15px #034c6a inset, 10px 0px 15px #034c6a inset;
        width: 100%;
        padding: 5px 0;
        color: #FFFFff;
    }


    .table2_find input[type=text]{
        background-color: transparent;
        width: 16%;
        height: 24px;
        border-radius: 3px;
        text-indent: 1em;
        border:1px solid #7EC4CC;

    }


    .table_find{
        width: 100%;
        padding: 8px 0;
        border-bottom: 2px solid #a5c5f83b;
        font-size: 13px;
    }
    .more_find{

        cursor: pointer;
        position: relative;
        top:5px;
    }
    .table_find .glyphicon{
        margin-right: 5px;
    }
    .table_find button{
        padding: 4px 10px;
    }
    .table_find p{
        margin-top: 8px;
    }
    .table_find label{

        color: #eeeeee;

        width: 8%;
        display: inline-block;
        text-align: right;

    }
    .table_find input[type=text],.table_find input[type=date]{
        width: 16%;
        height: 24px;
        border-radius: 3px;
        text-indent: 1em;
        border:1px solid#4b8df8;
        background-color: #cccccc4f;
    }
    .table_find select{
        width: 12%;
        height: 24px;
        border-radius: 3px;
        text-indent: 1em;
        border:1px solid #4b8df8;
        background-color: #cccccc4f;
    }
    .table_find input[type=checkbox]{
        width: 14px;
        height: 14px;
        position: relative;
        top:3px;
        margin-right: 5px;
    }
    .table_find span{
        color: #b2cbf3;

        margin-right: 2%;
    }
    .find_button{
        width: 5%;
        height: 26px;
        border: none;
        border-radius: 2px;
        background-color: #4b8df8;
        color: #FFFFff;
        font-size: 14px;

    }
    .table2_style{
        color: #FFFFff;
        border: none !important;
        font-size: 13px;
    }
    .table2_style a{
        margin-right: 10px;
    }
    .table2_style tbody tr:nth-child(2n+1){
        background-color: #072951;
        border-left: 4px solid #4b8df8;;
    }
    .table2_style thead tr{
        color: #FFFFff;
        background-color:#4b8df8;
    }
    .table2_style td{
        border: none !important;
    }
    .table2_style thead th{
        border-bottom: none !important;
    }
    .table2_style tbody tr:nth-child(2n):hover{
        /*color: #072951;*/
        background-color: rgba(75, 141, 248, 0.65);

    }
    .table_style{
        color: #FFFFff;
        box-shadow: -8px 0px 10px #034c6a inset,
        8px 0px 10px #034c6a inset;
        border: none !important;
        font-size: 13px;
    }
    .table_style a{
        margin-right: 10px;
    }
    .table_style td{
        border: none !important;
    }
    .table_style tbody tr:nth-child(2n+1){
        background-color: #072951;
        box-shadow: -10px 0px 15px #034c6a inset,
        10px 0px 15px #034c6a inset;
    }
    .table_style thead tr{
        color: #4b8df8;
    }
    .table_style thead th{
        border-bottom: none !important;
    }
    .table_style tbody tr:nth-child(2n):hover{
        /*color: #072951;*/
        background-color: rgba(75, 141, 248, 0.12);
    }
    .table_div{
        margin-top: 10px;
        color: #FFFFff;
    }
    .table_but{
        text-align: right;
        font-size: 13px;
        padding-bottom: 5px;
        padding-top: 10px;

    }
    .table_but a{
        margin-right: 15px;
        color: #FFFFff;
    }
    .table_but a i{
        margin-right: 5px;
    }
    .table_del{
        color: orangered;
    }
    .table_edit{
        color: #0D8BBD;
    }
    .span_left{
        padding: 5px;
    }
    /*地图界面样式*/
    .map_con{
        width: 100%;
        padding: 0;
        background-color: #081832cc;
    }
    .map_left{
        width: 79%;
        height: 100%;


    }
    .map_right{
        width: 20.5%;
        height: 100%;
        background-color: #081832;
    }
    .map_box{
        width: 100%;
        height: 100%;

    }
    .map_top{
        position: absolute;
        top:80px;
        left: 0;
        height: 35px;
        width: 79%;
        border-bottom: 1px solid #4b8df8;
        background-color: rgba(255, 255, 255, 0.84);
        z-index: 99;
    }
    .map_top>ul{
        list-style: none;
        font-size: 14px;
        float: right;
    }
    .map_top>ul>li{
        display: inline-block;
        line-height: 35px;
        padding: 0 10px;
    }
    .map_top>ul>li i{
        margin-right: 5px;
    }
    .map_top>ul>li a{
        color: #2E2D3C;
    }
    .map_top>ul .active,.map_top>ul>li .active{
        color: #0D8BBD;
    }
    .map_select{
        position: fixed;
        top:160px;
        right: 21.5%;
        box-shadow: -10px 0px 15px #034c6a inset, /*å·¦è¾¹é˜´å½±*/
        0px -10px 15px #034c6a inset, /*ä¸Šè¾¹é˜´å½±*/
        10px 0px 15px #034c6a inset,
        0px 10px 15px #034c6a inset;
        border: 1px solid #034c6a;
        width: 120px;
        height: 180px;
        border-radius: 5px;
        background-color: #081832e3;
    }
    .map_select p{
        padding: 8px 15px;
        color: #FFFFff;
        font-size: 14px;
    }
    .map_select p input[type=checkbox]{
        width: 15px;
        height: 15px;
        position: relative;
        top:3px;
        margin-right: 10px;
    }
    .map_right_top{
        width: 100%;
        height: 35px;

        background-color: #034c6a;
    }
    .map_right_top>ul>li{
        list-style: none;
        display: inline-block;
        line-height: 35px;
        font-size: 14px;
        text-align: center;
        width: 32%;
        color: #FFFFff;
        cursor: pointer;
    }
    .map_right_top>ul>li.li_active{
        background-color: #0D8BBD;
    }
    .map_chart{
        width: 100%;
        height: 250px;



        box-sizing: border-box;
        position: relative;
        margin-top: 25px;
    }
    .map_con{
        width: 100%;
    }
    .map_con_div{
        display: none;
    }
    .map_work{
        margin-top: 5px;
        border-top:1px solid #0D8BBD;;
    }
    .map_work>ul>li{
        list-style: none;
        display: inline-block;
        width: 32%;
        text-align: center;
        margin-top: 10px;
        margin-bottom: 10px;
        cursor: pointer;
        padding: 10px 0;

    }
    .map_work>ul>li:hover{
        box-shadow: -10px 0px 15px #034c6a inset, /*å·¦è¾¹é˜´å½±*/
        0px -10px 15px #034c6a inset, /*ä¸Šè¾¹é˜´å½±*/
        10px 0px 15px #034c6a inset,
        0px 10px 15px #034c6a inset;
    }
    .map_work>ul>li .img_div{
        width: 28px;
        height: 24px;
        margin: 0 auto;

    }
    .img_div01{

        /*background: url(../assets/images/map_photo.png) no-repeat;*/
        background-position: -126px -73px;
    }
    .img_div02{
        /* background: url(../assets/images/map_photo.png) no-repeat;*/
        background-position:  -98px -122px;

    }
    .img_div_text{
        color: #FFFFff;
        margin-top: 8px;
        font-size: 13px;
    }
    .back_i{
        position: relative;
        top:2px;
        margin-right: 5px;
    }
    .map_resList{
        width: 96%;
        margin: 0 auto;
        font-size: 13px;
        display: none;
    }
    .map_resList>ul>li{
        list-style: none;
        margin-top: 8px;
    }
    .map_resList>ul>li p{
        padding: 5px 0;
        color: #eeeeee;
    }
    .map_resList>ul>li a{
        margin-right: 6px;
    }
    .map_resList>ul>li a img{
        width: 16px;

    }
    .map_resList>ul>li:nth-child(2n+1){
        background-color: #0d8bbd36;
    }
    /*统计分析界面样式*/
    .static_top{
        width: 140px;
        height: 36px;
        line-height: 36px;
        background-color: #04425f;
        margin-bottom: 25px;
        margin-left: 1%;
        padding-left: 20px;
        border-radius: 1px;
        box-shadow: 0px 3px 3px #61d2f7;

    }
    .static_top i{
        width: 16px;
        height: 16px;
        display: inline-block;
        position: relative;
        background-image: url("../../assets/images/nav_1.png");
        background-repeat: no-repeat;
        margin-right: 6px;
        top:1px;
    }
    .static_top span{
        color: #25f3e6;
    }
    .stiatic_top_con{
        background-color: #034c6a;
        width: 96%;
        margin-left: 1%;
        margin-bottom: 25px;
        padding: 10px 1%;
        border-radius: 5px;
    }
    .stiatic_top_con table{
        width: 100%;
        border-collapse: collapse;
    }
    .stiatic_top_con table td{
        color: #FFFFff;
        padding: 5px;
        font-size: 13px;
        border:1px solid #485f849c;
    }
    .stiatic_top_con table .labe_td{
        color: #61d2f7;
        font-size: 14px;
        width: 120px;
        text-align: right;
    }
    .layui-layer-title{
        /*box-shadow: -8px 0px 10px #034c6a inset,*/
        /*8px 0px 10px #034c6a inset;*/
        background-color: #081832d4 !important;
        color: #FFFFff !important;
        border-bottom: 3px solid #4b8df8 !important;
        box-sizing: border-box;
    }


    .hide{
        display: none !important;
    }
    .xc_layer{position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: #666666; opacity: 0.5; z-index: 1000;}
    .popBox{position: fixed; left: 45%; top: 10%; background-color: #081832; z-index: 1001; width: 90%; max-height: 84%; margin-left: -40%; border-radius: 5px; font-weight: bold; color: #ffffff;height: 100%;}
    .popBox .ttBox{height: 30px; line-height: 30px; padding: 14px 30px; border-bottom: solid 1px #eef0f1;text-align: center;-webkit-box-sizing: content-box;  -moz-box-sizing: content-box;box-sizing: content-box;}
    .popBox .ttBox .tt{font-size: 20px; display: inline-block;  height: 30px;}
    .popBox .txtBox{height: calc(100% - 80px);overflow: auto;padding: 10px 0;}
    .box_div_any_child{
        width: 100%;
        height: 315px;
        box-shadow: -10px 0px 15px #034c6a inset, /*å·¦è¾¹é˜´å½±*/
        0px -10px 15px #034c6a inset, /*ä¸Šè¾¹é˜´å½±*/
        10px 0px 15px #034c6a inset,
        0px 10px 15px #034c6a inset;
        border: 1px solid #034c6a;
        box-sizing: border-box;
        position: relative;
        margin-top: 25px;
    }

</style>
