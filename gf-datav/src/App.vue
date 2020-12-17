<template>
  <div id="app">
   <!-- <index/>-->
    <top ref="top" @getActive="getActive"/>
    <router-view ref="child"/>
  </div>
</template>

<script>
  import top from './view/component/Top.vue'
export default {
  name: 'App',
  components: {
    top
  },
  data() {
    return {
      activeComponent: 1
    }
  },
  methods: {
    getActive(active) {
      this.activeComponent = active
      this.$router.push({
        name: active
      })
    },
    changeAPPActive(active) {
        this.$refs.top.active = active
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center;*/
  color: #2c3e50;
  margin: 0px;
  padding: 0px;
  font-size: 100%;
  height: 100%;
  background-color: #081832;
  clear: both;
}
</style>
