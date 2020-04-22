import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/home/Home.vue'
import addGroup from '../views/addGroup/addGroup.vue'
import newTodo from '../views/newTodo/newTodo.vue'
import Signup from '../views/registration/registration.vue'
import Login from '../views/login/login.vue'



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/addGroup',
    name: 'addGroup',
    
    component: addGroup
  },
  {
    path: '/newTodo',
    name: 'newTodo',
    
    component: newTodo
  },
  {
    path: '/signup',
    name: 'signup',
    components: {
      default: Signup
    }
  },
  {
    path: '/login',
    name: 'login',
    components: {
      default: Login
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
