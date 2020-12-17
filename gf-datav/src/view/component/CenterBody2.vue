<template>
    <div>
        <!--内容部分-->
        <div class="con left" style="width:50%;">
            <!--统计分析图-->
            <div class="div_any">
                <div class="left div_any01" style="width:100%;">
                    <div class="div_any_child" style="width:98%;position:relative;height: 420px;">
                        <div class="div_any_title"><img src="../../assets/images/title_13.png">温度</div>
                        <!--<div id="histogramChart1"  style="width: 45%;display: inline-block;height: 400px;margin-top: 15px;"></div>
                        <div id="lineChart1"  style="width: 52%;height: 66%;display: inline-block;"></div>
                        <div style="width: 50%;height: 20%;display: inline-block;color:red;position: absolute;right: 2%;top: 8%;">
                            <div style="display: inline-block;width:33%;text-align:center;">
                                <div style="color:#fff;font-size: 18px;line-height: 32px;">病例报告数</div>
                                <div style="color:#87cefa;font-size: 30px;line-height: 42px;">2354</div>
                            </div><div style="display: inline-block;width:33%;text-align:center;">
                            <div style="color:#fff;font-size: 18px;line-height: 32px;">死亡数</div>
                            <div style="color:red;font-size: 30px;line-height: 42px;">26</div>
                        </div><div style="display: inline-block;width:33%;text-align:center;">
                            <div style="color:#fff;font-size: 18px;line-height: 32px;">同比发病数趋势</div>
                            <div style="color:#ff7f50;font-size: 30px;line-height: 42px;">5.36%</div>
                        </div>
                        </div>-->
                        <!--<dv-conical-column-chart :config="config" style="width:75%;height:300px; margin: 0px auto; margin-top: 30px;" />-->
                        <div id="myLine"  style="width: 80%;height: 90%;display: inline-block; margin: 0px auto; margin-top: 3%;"></div>
                    </div>
                </div>
                <div class="left div_any01" style="width:48%;">
                    <div class="div_any_child" style="height: 420px;">
                        <div class="div_any_title"><img src="../../assets/images/title_14.png">糖度</div>
                        <div id="myPie"  style="width: 80%;height: 90%;display: inline-block; margin: 0px auto; margin-top: 3%;"></div>
                    </div>
                </div>
                <div class="left div_table_box" style="width: 48%;">
                    <div class="div_any_child" style="height: 420px;">
                        <div class="div_any_title"><img src="../../assets/images/title_16.png">预警信息</div>
                        <div class="table_p">
                        <table>
                            <thead><tr>
                                <th>序号</th>
                                <th>内容</th>
                                <th>发布时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr><td>1</td><td>9月21日感染性腹泻发病：123人次</td><td>2018-11-05</td></tr>
                            <tr><td>2</td><td>9月20日流行性感冒：57人次</td><td>2018-11-03</td></tr>
                            <tr><td>3</td><td>9月19日:手足口病发病同比增长220%</td><td>2018-11-01</td></tr>
                            <tr><td>4</td><td>9月18日登革热死亡:2人</td><td>2018-10-29</td></tr>
                            <tr><td>5</td><td>9月17日流行性感冒：157人次</td><td>2018-10-27</td></tr>
                            <tr><td>6</td><td>9月15日全区传染病发病人数较低</td><td>2018-10-25</td></tr>
                            <tr><td>7</td><td>9月14日流行性感冒：157人次</td><td>2018-10-23</td></tr>
                            <tr><td>8</td><td>9月13日全区传染病发病人数较低</td><td>2018-10-21</td></tr>
                            <tr><td>9</td><td>9月12日流行性感冒：157人次</td><td>2018-10-20</td></tr>
                            </tbody>
                        </table>
                    </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="con right" style="width:50%;">
            <div class="div_any">
                <div class="left div_any01" style="width:100%;">
                    <div class="div_any_child" style="width:98%;position:relative;height: 420px;">
                        <div class="div_any_title"><img src="../../assets/images/title_17.png">压力</div>
                        <!--<div id="histogramChart3"  style="width: 45%;display: inline-block;height: 400px;margin-top: 15px;"></div>-->
                        <div id="myBar"  style="width: 80%;height: 90%;display: inline-block; margin: 0px auto; margin-top: 3%;"></div>

                    </div>
                </div>
                <div class="left div_any01" style="width:48%;">
                    <div class="div_any_child" style="height: 420px;">
                        <div class="div_any_title"><img src="../../assets/images/title_18.png">冰水温度</div>
                        <div id="bar1"  style="width: 80%;height: 90%;display: inline-block; margin: 0px auto; margin-top: 5%;"></div>
                    </div>
                </div>
                <div class="right div_any01" style="width:48%;">
                    <div class="div_any_child" style="height: 420px;">
                        <div class="div_any_title"><img src="../../assets/images/title_19.png">进度</div>
                        <div id="bash" style="width: 80%;height: 90%;display: inline-block; margin: 0px auto; margin-top: 5%;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        name: "CenterBody2",
        data() {
            return {
                lineOption: {
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
                        data: [820, 932, 901, 934, 1290, 1330, 1320],
                        type: 'line'
                    }]
                },
                barOption: {
                   /*title: {
                       text: '在Vue中使用echarts',
                       textStyle: {
                           color: '#fff'
                       }
                   },*/
                   tooltip: {},
                   xAxis: {
                       data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"],
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
                       name: '销量',
                       type: 'bar',
                       data: [5, 20, 36, 10, 10, 20],
                       color: '#0385b5'
                   }]
               },
                pieOption: {
                    /*title: {
                        text: 'Customized Pie',
                        left: 'center',
                        top: 20,
                        textStyle: {
                            color: '#ccc'
                        }
                    },*/
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    visualMap: {
                        show: false,
                        min: 80,
                        max: 600,
                        inRange: {
                            colorLightness: [0, 1]
                        }
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '50%'],
                            data: [
                                {value: 335, name: '直接访问'},
                                {value: 310, name: '邮件营销'},
                                {value: 274, name: '联盟广告'},
                                {value: 235, name: '视频广告'},
                                {value: 400, name: '搜索引擎'}
                            ].sort(function (a, b) { return a.value - b.value; }),
                            roseType: 'radius',
                            label: {
                                color: '#fff'
                            },
                            labelLine: {
                                lineStyle: {
                                    color: '#fff'
                                },
                                smooth: 0.2,
                                length: 10,
                                length2: 20
                            },
                            itemStyle: {
                                color: '#c23531',
                                shadowBlur: 200,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            },

                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function () {
                                return Math.random() * 200;
                            }
                        }
                    ]
                },
                bar1Option: {
                    angleAxis: {
                        axisLine: {
                            lineStyle: {
                                color: '#fff'
                            }
                        }
                    },
                    radiusAxis: {
                        type: 'category',
                        data: ['周一', '周二', '周三', '周四'],
                        z: 10,
                        nameTextStyle: {
                            color: '#fff'
                        },
                        axisLine: {
                            lineStyle: {
                                color: '#fff'
                            }
                        }
                    },
                    polar: {
                    },
                    series: [{
                        type: 'bar',
                        data: [1, 2, 3, 4],
                        coordinateSystem: 'polar',
                        name: 'A',
                        stack: 'a',
                        color: '#c23531'
                    }, {
                        type: 'bar',
                        data: [2, 4, 6, 8],
                        coordinateSystem: 'polar',
                        name: 'B',
                        stack: 'a',
                        color: '#d76662'
                    }, {
                        type: 'bar',
                        data: [1, 2, 3, 4],
                        coordinateSystem: 'polar',
                        name: 'C',
                        stack: 'a',
                        color: '#6c1d1b'
                    }],
                    legend: {
                        show: true,
                        data: ['A', 'B', 'C'],
                        textStyle: {
                            color: '#fff'
                        }
                    }
                },
                bashOption: {
                    backgroundColor: '#0E1327',
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
                            name: "内部进度条",
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
                }
            }
        },
        mounted() {
            this.drawLine()
            this.drawBar()
            this.drawPie()
            this.drawBar1()
            this.drawBash()
        },
        methods: {
            drawLine(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('myLine'))
                // 绘制图表
                myChart.setOption(this.lineOption);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            },
            drawBar(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('myBar'))
                // 绘制图表
                myChart.setOption(this.barOption);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            },
            drawPie(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('myPie'))
                // 绘制图表
                myChart.setOption(this.pieOption);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            },
            drawBar1(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('bar1'))
                // 绘制图表
                myChart.setOption(this.bar1Option);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            },
            drawBash(){
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('bash'))
                // 绘制图表
                myChart.setOption(this.bashOption);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            }
        }
    }
</script>

<style scoped>
    .left{
        float: left;
    }
    .right{
        float: right;
    }
    .BMap_cpyCtrl {
        display: none;
    }

    .anchorBL {
        display: none;
    }
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
        margin-top: 7%;
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

</style>
