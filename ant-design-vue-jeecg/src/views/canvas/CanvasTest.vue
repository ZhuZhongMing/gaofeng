<template>
  <div>
    <canvas id="myCanvas" width="500px" height="500px" style="border: 1px solid red;">
     您的浏览器不支持canvas标签。
     </canvas>
    <img src="../../../public/avatar2.jpg" height="500" width="500"/></div>
</template>

<script>
  export default {
    name: "CanvasTest",
    data(){
      return {}
    },
    methods: {
      mydrawImage() {
        //获取Canvas对象(画布)
        var canvas = document.getElementById("myCanvas");
        //简单地检测当前浏览器是否支持Canvas对象，以免在一些不支持html5的浏览器中提示语法错误
        if(canvas.getContext){
          //获取对应的CanvasRenderingContext2D对象(画笔)
          var ctx = canvas.getContext("2d");

          //创建新的图片对象
          var img = new Image();
          //指定图片的URL
          //img.src = "../../../public/avatar2.jpg";
          img.src = "https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3004965690,4089234593&fm=26&gp=0.jpg";
          //浏览器加载图片完毕后再绘制图片
          img.onload = function(){
            //以Canvas画布上的坐标(10,10)为起始点，绘制图像
            ctx.drawImage(img, 10, 10);
          };
        }
      }
    },
    mounted() {
      this.mydrawImage()
    }
  }
</script>
<style scoped>

</style>