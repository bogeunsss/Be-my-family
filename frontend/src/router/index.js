import Vue from 'vue'
import Router from 'vue-router'
import Vuex from 'vuex'

import Store from '@/store/index'
import constants from '@/lib/constants'

import Login from '@/page/user/Login.vue'
import Join from '@/page/user/Join.vue'
import Profile from '@/page/user/Profile.vue'
import Update from '@/page/user/Update.vue'
import JoinSuccess from '@/page/user/JoinSuccess.vue'
import Like from '@/page/user/Like.vue'
import Survey from '@/page/user/Survey.vue'
import Params from '@/page/error/Params.vue'
import Main from '@/page/post/MainList.vue'
import List from '@/page/post/List.vue'
import Detail from '@/page/post/Detail.vue'
import AdoptList from '@/page/adoptionPost/AdoptList.vue'
import AdoptCreate from '@/page/adoptionPost/AdoptCreate.vue'
import LostList from '@/page/lost/LostList.vue'
import LostDetail from '@/page/lost/LostDetail.vue'
import Manager from '@/page/user/Manager.vue'

Vue.use(Router)
Vue.use(Vuex)

export default new Router({
  routes: [
    {
      path: '/user/login',
      name: constants.URL_TYPE.USER.LOGIN,
      component: Login
    },
    {
      path: '/user/join',
      name: constants.URL_TYPE.USER.JOIN,
      component: Join
    },
    {
      path: '/user/join/success',
      name: constants.URL_TYPE.USER.JOINSUCCESS,
      component: JoinSuccess
    },
    {
      path: '/user/profile',
      name: constants.URL_TYPE.USER.PROFILE,
      component: Profile,
      props: true,
      beforeEnter(to, from, next){
        if(Store.state.isLoggedIn){
          next()
        }else{
          alert('로그인이 필요합니다.')
        }
      }
    },
    {
      path: '/user/update',
      name: constants.URL_TYPE.USER.UPDATE,
      component: Update,
      beforeEnter(to, from, next){
        if(Store.state.isLoggedIn){
          next()
        }else{
          alert('로그인이 필요합니다.')
        }
      }
    },
    {
      path: '/',
      name: constants.URL_TYPE.MAIN,
      component: Main
    },
    {
      path: '/list',
      name: constants.URL_TYPE.POST.LIST,
      component: List
    },
    {
      path: '/AdoptList',
      name: constants.URL_TYPE.ADOPTIONPOST.ADOPTLIST,
      component: AdoptList
    },
    {
      path: '/AdoptCreate',
      name: constants.URL_TYPE.ADOPTIONPOST.ADOPTCREATE,
      component: AdoptCreate
    },
    {
      path: '/detail',
      name: constants.URL_TYPE.POST.DETAIL,
      component: Detail
    },
    {
      path: '/user/like',
      name: constants.URL_TYPE.USER.LIKE,
      component: Like,
      beforeEnter(to, from, next){
        if($cookies.get('auth-token')){
          next()
        }else{
          alert('로그인이 필요합니다.')
        }
      }
    },
    {
      path: '/user/survey',
      name: constants.URL_TYPE.USER.SURVEY,
      component: Survey,
      beforeEnter(to, from, next){
        if(Store.state.isLoggedIn){
          next()
        }else{
          alert('로그인이 필요합니다.')
        }
      }
    },
    {
      path: '/lost',
      name: constants.URL_TYPE.LOST.LOSTLIST,
      component: LostList
    },
    {
      path: '/lost/:articleNo',
      name: constants.URL_TYPE.LOST.LOSTDETAIL,
      component: LostDetail
    },
    {
      path: '/user/manager',
      name: constants.URL_TYPE.USER.MANAGER,
      component: Manager
    },
    {
      path: '/params',
      component: Params,
      name: 'Params',
      props: true
    }
  ]
})
