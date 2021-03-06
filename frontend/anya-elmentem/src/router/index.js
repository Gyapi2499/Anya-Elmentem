import Vue from 'vue'
import VueRouter from 'vue-router'
import home from '../views/home/home.vue'
import addGroup from '../views/addGroup/addGroup.vue'
import modGroup from '../views/modGroup/modGroup.vue'
import newTodo from '../views/newTodo/newTodo.vue'
import myTodos from '../views/myTodos/myTodos.vue'
import newMyTodo from '../views/newMyTodo/newMyTodo.vue'
import Signup from '../views/registration/registration.vue'
import Login from '../views/login/login.vue'
import addChange from '../views/add-change/add-change.vue'
import handleChange from '../views/handle-change/handle-change.vue'
import groupTodos from '../views/groupTodos/groupTodos.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: home
  },
  {
    path: '/myTodos',
    name: 'myTodos',
    component: myTodos
  },
  {
    path: '/groupTodos',
    name: 'groupTodos',
    component: groupTodos
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
    path: '/modGroup',
    name: 'modGroup',
    component: modGroup
  },
  {
    path: '/newTodo',
    name: 'newTodo',
    component: newTodo
  },
  {
    path: '/newMyTodo',
    name: 'newMyTodo',
    component: newMyTodo
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
  },
  {
    path: '/addChange',
    name: 'addChange',
    components: {
      default: addChange
    }
  },
  {
    path: '/handleChange',
    name: 'handleChange',
    components: {
      default: handleChange
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
