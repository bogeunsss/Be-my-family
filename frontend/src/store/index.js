import Vue from 'vue'
import Vuex from 'vuex'

import cookies from 'vue-cookies'
import axios from 'axios'

import router from '@/router'
import SERVER from '@/lib/constants'
import constants from '@/lib/constants'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // auth
    authToken: null,
    dialog : false,
    isLoggedIn:false,
    isDeleted:false,
    signupData:{
      email: null,
      nickName: null,
      password: null,
      passwordConfirm: null, 
      passwordType: 'password',
      passwordConfirmType: 'password',
      // join 수정
      phone: null,
      job: null,
      marriaged: null,
      sex: null,
      birthdate: null,
    },
    loginData: {
      token:'',
      email: null,
      password: null
    },
    profileData: {
      email: null,
      nickName: null,
      password: null
    },
    dogData: {
      careaddr : '',
      carenm: '',
      kindcd: '',
      popfile: '',
      desertionNo: ''
    },
  },
  getters: {
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    setDialog(state){
      if(state.dialog) return state.dialog = false
      return state.dialog = true
    },
    setNickName(state, nickName){
      state.signupData.nickName = nickName
    },
    setEmail(state, email){
      state.signupData.email = email
    },
    setPassword(state, password){
      state.signupData.password = password
    },
    setPasswordConfirm(state, passwordConfirm){
      state.signupData.passwordConfirm = passwordConfirm
    },
    loginEmail(state, email){
      state.loginData.email = email
    },
    loginPassword(state, password){
      state.loginData.password = password
    },
    profileEmail(state, email){
      state.profileData.email = email
    },
    profileNickName(state, nickName){
      state.profileData.nickName = nickName
    },

    // isLoggedInChanged(state){
    //   if(cookies.isKey('auth-token')) return state.isLoggedIn = true
    //   else return state.isLoggedIn = false
    // }
  },

  actions: {
    signup({ commit }, signupData){
      const info = {
        data: signupData,
        location: SERVER.URL_TYPE.USER.JOIN
      }
      console.log(info.data.nickName)
      var reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      var passwordReg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      if(!info.data.nickName){
        alert('닉네임을 입력하세요')
      }else if(!info.data.email){
          alert('이메일을 입력하세요')
      }
      else if(!reg.test(info.data.email)){
          alert('이메일 형식을 확인하세요')
      }
      else if(!info.data.password){
          alert('비밀번호를 입력하세요')
      }else if(!info.data.passwordConfirm){
          alert('비밀번호 확인을 입력하세요')
      }else if(info.data.password !== info.data.passwordConfirm){
          alert('비밀번호가 일치하지 않습니다')
      }else if(false === passwordReg.test(info.data.password)) {
          alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
      }else if(/(\w)\1\1\1/.test(info.data.password)){
          alert('같은 문자를 4번 이상 사용하실 수 없습니다.');
      }else if(info.data.password.search(info.data.email) > -1){
          alert("비밀번호에 아이디가 포함되었습니다.");
      }else if(info.data.password.search(/\s/) != -1){
          alert("비밀번호는 공백 없이 입력해주세요.");
      }else{
        axios.post(SERVER.SERVER_URL + '/account/signup', {
          email: info.data.email,
          password: info.data.password,
          uid: info.data.nickName
        })
        .then(res=>{
          if(res.data.data === 'emailexist'){
              alert('이미 있는 이메일입니다.')
          }else if(res.data.data === 'nicknameexist'){
              alert('이미 있는 닉네임입니다.')
          }else if(res.data.data === 'emailsuccess'){
              router.push({name:SERVER.URL_TYPE.POST.MAIN})
          }else if(res.data.data === 'emailfail'){
              alert('이메일 전송 실패')
          }
        })
        .catch(err=>console.log(err))
      }
    },
    login({ commit, state }, loginData){
      let formData = new FormData()
      formData.append('email', loginData.email)
      formData.append('password', loginData.password)
      console.log(loginData.email)
      var reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (!loginData.email) {
        console.log(loginData.email)
        alert("이메일을 입력하세요");
      }
      else if (!reg.test(loginData.email)) {
        alert("이메일 형식을 확인하세요");
      }
      else {
      axios.post(SERVER.SERVER_URL +'/account/login ', formData)
      .then(response => {
        if(response.status == 200){
            // commit('SET_TOKEN',response.data.key)
            state.dialog = true
            // var jwt = require("jsonwebtoken");
            // var token = jwt.sign({ sub: loginData.email }, loginData.password);
            console.log(response)
            alert("로그인 성공");
            state.dialog = false
            state.isLoggedIn = true
            cookies.set('auth-token', {
              token:response.data.object.accessToken,
              email:response.data.email,
              uid:response.data.uid
            })
            state.authToken = cookies.get('auth-token')
            router.push({name:constants.URL_TYPE.POST.MAIN})
            router.go()
          }
        })
        .catch((error)=>{
          alert("로그인 실패");
          console.log(error)
          state.dialog = false;
          // router.push({name: 'Params', params: {name: error.response.status}});
        })
      } 
    },
    logout({ commit, state }){
      state.authToken = null
      cookies.remove('auth-token')
      router.go()
    },
    // 데이터 조회할때 유저 null 값 나옴
    find({commit, state}, email){
      // console.log(loginData)
      axios.get(SERVER.SERVER_URL + '/account/find?email=' + email)
      .then(response=>{
        state.profileData.email = response.data.object.email
        state.profileData.nickName = response.data.object.uid
        state.profileData.password = response.data.object.password
        console.log(response)
      })
      .catch(err=>console.log(err))
    },

    userDelete({ commit, dispatch }, userNickName) {
      console.log(userNickName)
      axios.delete(SERVER.SERVER_URL + `/account/delete?uid=${userNickName}`)
      .then(response => {
        alert('탈퇴 성공')
      }).catch(error => {
        alert('탈퇴 실패')
      })
    },
    userUpdate({commit}, userData){
      console.log(userData)
      axios.put(`http://localhost:8080/account/update`,{
          uid:userData.nickName,
          email:userData.email,
          password:userData.password
      }).then(res=>{
          alert('수정 성공')
      }).catch(err=>{
          alert('수정 실패')
          console.log(err)
      })
    },
    mainList({commit, state}){
      axios
        .get("http://localhost:8080/care/list")
        .then((res) =>{
            state.dogData = res.data.object[0]
        })
        .catch((err) =>{
            console.log(err)
        })
    },
  },

  modules: {
  }
})
