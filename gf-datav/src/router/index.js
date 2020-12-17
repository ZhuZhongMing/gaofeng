import Vue from 'vue'
import Router from 'vue-router'
import CenterBody1 from '../view/component/CenterBody.vue'
import CenterBody2 from '../view/component/CenterBody1.vue'
import CenterBody3 from '../view/component/CenterBody2.vue'
import CenterBody4 from '../view/component/CenterBody3.vue'
import CenterMap from '../view/component/CenterMap.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '1',
      component: CenterMap
    },{
      path: '/CenterBody1:id',
      name: '3',
      component: CenterBody1
    },{
      path: '/CenterBody2',
      name: '4',
      component: CenterBody2
    },{
      path: '/CenterBody4',
      name: '2',
      component: CenterBody4,
    },{
      path: '/CenterBody3',
      name: '5',
      component: CenterBody3
    }
  ]
})
