<template>
  <div class="index">

    <CenterBody1/>
    <!--<CenterBody2 v-if="activeComponent == 2"/>
    <CenterBody3 v-if="activeComponent == 3"/>
    <CenterBody4 v-if="activeComponent == 4"/>-->
  </div>
</template>

<script>

import CenterBody1 from './component/CenterBody.vue'

export default {
  name: 'index',
  components:{
    CenterBody1,
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .index{
    background-color: #081832;
  }
</style>
