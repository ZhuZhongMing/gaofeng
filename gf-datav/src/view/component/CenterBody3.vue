<template>
    <!--内容部分-->
    <div class="mainClass con left" style="width: 98%;margin-left: 1%;margin-bottom: 25px;">
        <!--<input type="text" placeholder="请输入姓名身份证" style="width: 180px;height: 28px;border-radius: 3px;text-indent: 1em;border: 1px solid#4b8df8;color: #fff;" /><button class="btn btn-primary btn-sm" style="margin-left:20px"><span class="glyphicon glyphicon-search"></span>查询</button>-->
        <div style="color: #fff; float: left;">
            <span style="font-size: 18px; margin-right: 15px;">设备(<b v-text="devEquipment.sum"></b>)</span>
            <span style="font-size: 18px; margin-right: 15px;"><span style="color: #2abf57; margin-right: 10px;"><a-icon type="play-circle"  style="fontSize: 20px;"/></span>运行<span style="margin-left: 10px;" v-text="devEquipment.startSum"></span></span>
            <span style="font-size: 18px; margin-right: 15px;"><span style="color: #6D6D6D; margin-right: 10px;"><a-icon type="pause-circle" style="fontSize: 20px;"/></span>停机<span style="margin-left: 10px;" v-text="devEquipment.stopSum"></span></span>
        </div>

        <div class="div_any_child" style="margin-top: 60px;">
            <div class="table_p" style="height: 96%;margin-top: 20px;">
                <table v-if="dataSourse.length > 0">
                    <thead><tr>
                        <th>序号</th>
                        <th>设备名称</th>
                        <th>液位</th>
                        <th>温度</th>
                        <th>CO2浓度</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(item, key) in dataSourse" :key="key">
                        <td>
                            <span v-if="item.equipmentStatus==1" v-text="(key+1)"></span>
                            <span style="color: #6D6D6D;" v-if="item.equipmentStatus==0" v-text="(key+1)"></span>
                        </td>
                        <td>
                            <span v-if="item.equipmentStatus==1" v-text="item.equipmentName"></span>
                            <span style="color: #6D6D6D;" v-if="item.equipmentStatus==0" v-text="item.equipmentName"></span>
                        </td>
                        <td>
                            <span v-if="item.equipmentStatus==1" v-text="item.liquidLevel"></span>
                            <span style="color: #6D6D6D;" v-if="item.equipmentStatus==0" v-text="item.liquidLevel"></span>
                        </td>
                        <td>
                            <span v-if="item.equipmentStatus==1" v-text="item.temperature"></span>
                            <span style="color: #6D6D6D;" v-if="item.equipmentStatus==0" v-text="item.temperature"></span>
                        </td>
                        <td>
                            <span v-if="item.equipmentStatus==1" v-text="item.carbonDioxide"></span>
                            <span style="color: #6D6D6D;" v-if="item.equipmentStatus==0" v-text="item.carbonDioxide"></span>
                        </td>
                        <td>
                            <span style="color: #2abf57;" v-if="item.equipmentStatus==1"><a-icon type="play-circle" style="fontSize: 30px;"/></span>
                            <span style="color: #6D6D6D;" v-if="item.equipmentStatus==0"><a-icon type="pause-circle" style="fontSize: 30px;" /></span>
                        </td>
                        <td>
                            <button class="btn btn-primary btn-sm" v-if="item.equipmentStatus==1" @click="lookDevEquipment(item.id)">查看</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="box">
                <div id="pagination" class="page fl"></div>
            </div>
        </div>


    </div>
</template>

<script>
    import { getRequest } from '../../request/request'
    export default {
        name: "CenterBody3",
        data() {
            return {
                dataSourse: [],
                devEquipment:{
                    sum: 0,
                    stopSum: 0,
                    startSum: 0
                },
                url: {
                    queryDevEquipmentList: '/system/mbpDevEquipment/queryDevEquipmentList'
                }
            }
        },
        computed: {
        },
        methods: {
            /*查询生产设备温度*/
            queryDevEquipmentList() {
                getRequest(this.url.queryDevEquipmentList, null).then(res => {
                    if (res.data.success) {
                        this.dataSourse.splice(0)
                        this.dataSourse = res.data.result;
                        var sum = this.dataSourse.length;
                        var startSum = 0;
                        var stopSum = 0;
                        var result = this.dataSourse
                        for (let i = 0; i < result.length; i++) {
                            if (result[i].equipmentStatus == 1) {
                                startSum++
                            }
                            if (result[i].equipmentStatus == 0) {
                                stopSum++
                            }
                        }
                        this.devEquipment.sum = sum
                        this.devEquipment.startSum = startSum
                        this.devEquipment.stopSum = stopSum
                    }
                }).catch(exc => {
                    console.log('查询生产设备温度发生异常！异常信息：' + exc)
                })
            },
            /*点击查看*/
            lookDevEquipment(id) {
                this.$parent.changeAPPActive(3);
                this.$router.push({
                    name: 3,
                    params: {id: id}
                })
            }
        },
        mounted() {
            this.queryDevEquipmentList()
        }
    }
</script>

<style scoped>
    .mainClass{
        margin: 0px;
        padding: 0px;
    }
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
        height: 650px;/*height: 330px;*/
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
        font-size: 18px;
        font-weight: 600;
        padding-top: 5px;
        padding-bottom: 20px;
    }
    .table_p table tbody{
        color: #ffffff;
        font-size: 16px;
    }
    .table_p table tbody tr td{
        height: 40px;
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
        //color: #FFFFff;
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
    .ui-pagination-container {
        height: 34px;
        line-height: 34px;
    }

    .ui-pagination-container .ui-pagination-page-item {
        font-size: 14px;
        padding: 4px 10px;
        background: #ffffff1a;
        border: 1px solid #c5b7b7;
        color: #fff;
        margin: 0 3px;
        text-decoration: none;
    }

    .ui-pagination-container .ui-pagination-page-item:hover {
        border-color: #081832;
        color: #FFF;
        text-decoration: none;
    }

    .ui-pagination-container .ui-pagination-page-item.active {
        background: #072951;
        border-color: #4b8df8;
        color: #fff;
        cursor: default;
    }

    .box {
        width: 800px;
        margin: 32px auto 0;
        height: 34px;
    }
    .page {
        width: 100%;
        text-align: center;
    }

    .fl {
        float: left;
    }

</style>
