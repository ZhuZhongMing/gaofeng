import Vue from 'vue'
import Antd from 'ant-design-vue';
import App from './App';
import 'ant-design-vue/dist/antd.css';
import router from './router'
// 将自动注册所有组件为全局组件
import dataV from '@jiaminghi/data-view'
// 将Viser全局注册
import Viser from 'viser-vue'
// 引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts
Vue.use(Viser)
Vue.use(dataV)
Vue.config.productionTip = false;

Vue.use(Antd);
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// 注册为全局组件
Vue.use(BootstrapVue)

import Axios from 'axios'

// 将axios挂载到原型上
Vue.prototype.$axios = Axios

import BaiduMap from 'vue-baidu-map';
//import geoutils from 'geoutils'
Vue.use(BaiduMap, {
  /* Visit http://lbsyun.baidu.com/apiconsole/key for details about app key. */
  ak: 'hVdxRa5AMxSP0yYrn5eB3gsyOjb21N5e'
})

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
