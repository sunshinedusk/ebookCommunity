import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Login from '@/views/Login.vue'

import Register from '@/views/Register.vue';
import AppIndex from '@/components/AppIndex.vue'
import LibraryIndex from '@/components/library/LibraryIndex.vue'
import Personal from '@/components/personal/Personal.vue'
import BookInfo from '@/components/library/BookInfo.vue'

Vue.use(VueRouter)

export default new VueRouter({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
  
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/',
      name: 'Default',
      redirect: '/home',
      component: Home
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      redirect:'/index',
      children:[
        {
          path:'/index',name:'AppIndex',component: AppIndex
        },
        {
          path: '/jotter',
          name: 'Jotter',
          component: () => import('../components/jotter/Articles')
        },
  
        {
          path: '/jotter/article',
          name: 'Article',
          component: () => import('../components/jotter/ArticleDetails')
        },
        {
          path: '/library',
          name: 'Library',
          component: LibraryIndex,
        },
        {
          path: '/book/:id',
          name: 'BookInfo',
          component: BookInfo,
          props: true
        },

        {
          path: '/personal',
          name: 'Personal',
          component: Personal,
        },
      ]
    }
  
  ]
})