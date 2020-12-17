<template>
    <!--顶部-->
    <header class="header left">
        <div class="left nav">
            <ul v-if="active == 1">
                <li class="nav_active" @click="changeActive(1)"><i class="nav_1"></i><a>首页</a> </li>
                <li @click="changeActive(2)"><i class="nav_2"></i><a>设备列表</a> </li>
                <li @click="changeActive(3)"><i class="nav_3"></i><a>实时监测</a> </li>
                <!--<li @click="changeActive(4)"><i class="nav_3"></i><a>设备报警</a> </li>-->
                <!--<li><i class="nav_4"></i><a>慢病病人列表</a> </li>-->
                <!--<li class="nav_active"><i class="nav_1"></i><a href="index.html">采集概况</a> </li>
                <li><i class="nav_2"></i><a href="quota.html">指标分析</a> </li>
                <li><i class="nav_3"></i><a href="trend.html">趋势分析</a> </li>
                <li><i class="nav_4"></i><a href="chronic.html">慢病病人列表</a> </li>-->
            </ul>
            <ul v-if="active == 2">
                <li @click="changeActive(1)"><i class="nav_1"></i><a>首页</a> </li>
                <li class="nav_active" @click="changeActive(2)"><i class="nav_2"></i><a>设备列表</a> </li>
                <li @click="changeActive(3)"><i class="nav_3"></i><a>实时监测</a> </li>
                <!--<li @click="changeActive(4)"><i class="nav_3"></i><a>设备报警</a> </li>-->
                <!--<li><i class="nav_4"></i><a>慢病病人列表</a> </li>-->
                <!--<li class="nav_active"><i class="nav_1"></i><a href="index.html">采集概况</a> </li>
                <li><i class="nav_2"></i><a href="quota.html">指标分析</a> </li>
                <li><i class="nav_3"></i><a href="trend.html">趋势分析</a> </li>
                <li><i class="nav_4"></i><a href="chronic.html">慢病病人列表</a> </li>-->
            </ul>
            <ul v-if="active == 3">
                <li @click="changeActive(1)"><i class="nav_1"></i><a>首页</a> </li>
                <li @click="changeActive(2)"><i class="nav_2"></i><a>设备列表</a> </li>
                <li class="nav_active" @click="changeActive(3)"><i class="nav_3"></i><a>实时监测</a> </li>
                <!--<li @click="changeActive(4)"><i class="nav_3"></i><a>设备报警</a> </li>-->
                <!--<li><i class="nav_4"></i><a>慢病病人列表</a> </li>-->
                <!--<li class="nav_active"><i class="nav_1"></i><a href="index.html">采集概况</a> </li>
                <li><i class="nav_2"></i><a href="quota.html">指标分析</a> </li>
                <li><i class="nav_3"></i><a href="trend.html">趋势分析</a> </li>
                <li><i class="nav_4"></i><a href="chronic.html">慢病病人列表</a> </li>-->
            </ul>
            <!--<ul v-if="active == 4">
                <li @click="changeActive(1)"><i class="nav_1"></i><a>首页</a> </li>
                <li @click="changeActive(2)"><i class="nav_2"></i><a>设备列表</a> </li>
                <li @click="changeActive(3)"><i class="nav_3"></i><a>实时监测</a> </li>
                <li class="nav_active" @click="changeActive(4)"><i class="nav_3"></i><a>设备报警</a> </li>
                &lt;!&ndash;<li><i class="nav_4"></i><a>慢病病人列表</a> </li>&ndash;&gt;
                &lt;!&ndash;<li class="nav_active"><i class="nav_1"></i><a href="index.html">采集概况</a> </li>
                <li><i class="nav_2"></i><a href="quota.html">指标分析</a> </li>
                <li><i class="nav_3"></i><a href="trend.html">趋势分析</a> </li>
                <li><i class="nav_4"></i><a href="chronic.html">慢病病人列表</a> </li>&ndash;&gt;
            </ul>-->
        </div>
        <div class="header_center left" style="position:relative">

            <h2><strong style="color: white;">杭州高峰蓝莓种植有限公司</strong></h2>

        </div>
        <div class="right nav text_right" style="text-align: right;">
            <div style="color: white; margin-top: 30px; margin-left: 250px;">当前时间 ： <span v-text="nowTime"></span></div>
        </div>
    </header>
</template>

<script>
    export default {
        name: "Top",
        data() {
            return {
                active: 1,
                nowTime: ''
            }
        },
        methods: {
            changeActive(active) {
                if (active == 3) {
                    this.$message.warning('请前往设备列表页查看指定设备')
                    return
                }
                this.active = active
                this.$emit('getActive',active)
            },
            getNowTime() {
                var date = new Date()
                var y = date.getFullYear();
                var m = date.getMonth() + 1;
                var d = date.getDate()
                var h = date.getHours()
                var mm = date.getMinutes()
                var s = date.getSeconds()
                this.nowTime = y + '年' + m + '月' + d + '日 ' + h + '时' + mm + '分' + s + '秒'
            }
        },
        mounted() {
           this.active = this.$route.name
            this.getNowTime()
            setInterval(()=>{
                this.getNowTime()
            },1000)
        }
    }
</script>

<style scoped>
    .header{
        width: 100%;
        height: 80px;
        background-color: #030829;
    }
    .text_right{
        text-align: right;
    }
    .header_center{
        width: 30%;
        margin: 0px auto;
        color: #FFFFff;
        text-align: center;
        height: 80px;
        background-image: url("../../assets/images/logoBg.png");
        background-size: 100% 100%;
        font-family: "微软雅黑"!important;
    }
    .header_center h2{
        margin-top: 16px !important;
        font-size: 22px !important;
    }
    .color_font{
        color: #e8f7fe !important;
        font-size: 12px !important;
    }
    .header_logo{
        margin-left: 1%;
        margin-top: 12px;

    }
    .header_logo img{
        height: 56px;
    }
    .nav{
        width: 35%;
        text-align: left;
    }
    .nav>ul{

    }
    .nav>ul>li{
        display: inline-block;
        width: 120px;
        text-align: center;
        height: 50px;
        position: relative;
        line-height: 50px;
        margin-top: 15px;
        box-sizing: border-box;
        /*box-shadow: -5px 0px 5px #034c6a inset, !*å·¦è¾¹é˜´å½±*!*/
        /*0px -5px 15px #034c6a inset, !*ä¸Šè¾¹é˜´å½±*!*/
        /*0px 5px 15px #034c6a inset;*/
        border-radius: 5px;
        margin-left: 5px;
    }
    .nav>ul>li:hover{
        box-shadow: -10px 0px 15px #034c6a inset, /*å·¦è¾¹é˜´å½±*/
        0px -10px 15px #034c6a inset, /*ä¸Šè¾¹é˜´å½±*/
        10px 0px 15px #034c6a inset,
        0px 10px 15px #034c6a inset;
        box-sizing: border-box;
    }
    .nav>ul>li i{
        width: 16px;
        height: 16px;
        display: inline-block;
        position: relative;
        top:3px;
    }
    .nav>ul>li>a{
        color: #ffffff;
        /*display: inline-block;*/
        /*padding: 0 15px 0 5px;*/
        font-size: 14px;
    }
    .li_ul{
        position: absolute;
        background-color: #030829;
        width: 100%;
        /*border-top:4px solid #4b8df8;*/
        display: none;
        z-index: 999;

    }
    .li_ul li{
        line-height: 40px !important;
    }
    .li_ul li:hover{
        background-color: #4b8df8;
    }
    .li_ul li a{
        color: #ffffff;
        font-size: 13px;
    }
    .nav_1{
        background: url("../../assets/images/nav_1.png");
    }
    .nav_2{
        background-image: url("../../assets/images/nav_2.png");
    }
    .nav_3{
        background-image: url("../../assets/images/nav_3.png");

    }
    .nav_4{
        background-image: url("../../assets/images/nav_1.png");
    }
    .left{
        float: left;
    }
    .right{
        float: right;
    }
    .nav_active{
        border-bottom: 4px solid #4b8df8;
        box-shadow: -10px 0px 15px #034c6a inset,
        0px -10px 15px #034c6a inset,
        10px 0px 15px #034c6a inset,
        0px 10px 15px #034c6a inset;
        box-sizing: border-box;
    }
</style>
