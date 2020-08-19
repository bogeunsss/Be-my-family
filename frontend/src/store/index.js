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
      name:null,
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
      password: null,
      isManager:null,
      mid: null,
    },
    profileData: {
      email: null,
      nickName: null,
      password: null,
      name:null,
      phone:null,
      job:null,
      marriaged:null,
      sex:null,
      birthdate:null,
      flag:0,
    },
    dogData: [],
    isLast: false,
    adoptionData:{},
    sido_states: [
      '서울특별시', '부산광역시', '인천광역시', '대전광역시',
      '대구광역시', '울산광역시', '광주광역시', '세종특별자치시',
      '경기도', '강원도', '충청북도','충청남도', '전라북도', '전라남도', 
      '경상북도','경상남도'
    ],
    gugun_states: {
      // 서울
      '서울특별시':['강서구', '양천구', '구로구', '영등포구','동작구','금천구','관악구',
      '서초구', '강남구', '송파구', '강동구','마포구','용산구','성동구','광진구',
      '서대문구', '중구', '동대문구','중랑구', '성북구', '종로구','은평구', 
      '강북구','노원구','도봉구'],
      // 부산
      '부산광역시':['해운대구','남구','동구','서구','북구','동래구','사하구'],
      // 인천
      '인천광역시':['서구','계양구','동구','미추홀구','연수구','부평구','중구','강화군'],
      // 대전
      '대전광역시':['서구','중구','동구','유성구','대덕구'],
      // 대구
      '대구광역시':['남구','수성구','달서구','북구'],
      // 울산
      '울산광역시':['북구','동구','중구','남구'],
      // 광주
      '광주광역시':['서구','남구','동구','광산구'],
      // 경기도
      '경기도':['수원시','성남시','의정부시','안양시','부천시','광명시','평택시',
      '동두천시','안산시','고양시','과천시','구리시','남양주시','오산시',
      '시흥시','군포시','의왕시','하남시','용인시','파주시','이천시','김포시',
      '광주시','양주시','포천시','안성시'],
      // 강원도
      '강원도':['철원군','화천구','양구군','고성군','춘천시','인제군','속초시',
      '홍천군','양양군','원주시','횡성군','평창군','강릉시','영월군',
      '정선군','동해시','태백시','삼척시'],
      // 충청북도
      '충청북도':['청주시','충추시','제천시','보은군','옥천군','영동군','증편군',
      '진천군','괴산군','음성군','단양군'],
      // 충청남도
      '충청남도':['태안군','서산시','당진시','아산시','천안시','홍성군','예산군',
      '보령시','청양군','공주시','서천군','부여군','논산시','계룡시','금산군'],
      // 전라북도
      '전라북도':['군산시','익산시','김제시','전주시','남원시','정읍시','무주군','장수군',
      '부안군','진안군','임실군','고창군','순창군','완주군'],
      // 전라남도
      '전라남도':['목포시','여수시','순천시','나주시','광양시','담양군','곡성군','구례군',
      '고흥군','보성군','화순군','장흥군','강진군','해남군','영암군','무안군',
      '함평군','영광군','장성군','완도군','진도군','신안군'],
      // 경상북도
      '경상북도':['포항시','경주시','김천시','안동시','구미시','영주시','영천시','상주시',
      '문경시','경산시','군위군','의성군','청송군','영양군','영덕군','예천군',
      '봉화군','울진군','울룽군'],
      //경상남도
      '경상남도':['창원시','진주시','통영시','사천시','김해시','밀양시','거제시','양산시',
      '의령군','함안군','창녕군','고성군','남해군','하동군','산청군','함양군',
      '거창군','합천군'],
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
    setName(state, name){
      state.signupData.name = name
    },
    setPassword(state, password){
      state.signupData.password = password
    },
    setPasswordConfirm(state, passwordConfirm){
      state.signupData.passwordConfirm = passwordConfirm
    },
    setPhone(state, phone){
      state.signupData.phone = phone
    },
    setJob(state, job){
      state.signupData.job = job
    },
    setMarriaged(state, marriaged){
      state.signupData.marriaged = marriaged
    },
    setSex(state, sex){
      state.signupData.sex = sex
    },
    setBirthDate(state, birthdate){
      state.signupData.birthdate = birthdate
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
    setSearchDogs(state, newDogData){
      if(!newDogData.isSearched){
        state.dogData = []
      }
      console.log(newDogData)
      state.dogData.push(newDogData.data.object)
      if(!newDogData.data.hasNext){
        state.isLast = true
      }else{
        state.isLast = false
      }
    },
    checkLoggedIn(state, check){
      state.isLoggedIn = check
    },
    // adoptionDataList(state, adoptionData){
    //   state.adoptionData = adoptionData
    // },

    getProfileData(state){
      return state.profileData
    }

    // isLoggedInChanged(state){
    //   if(cookies.isKey('auth-token')) return state.isLoggedIn = true
    //   else return state.isLoggedIn = false
    // }
  },

  actions: {
    login({ commit, state }, paramData){
      let formData = new FormData()
      var reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if(paramData.loginData.isManager === 'radio-1'){
        formData.append('email', paramData.loginData.email)
        formData.append('password', paramData.loginData.password)
        if (!paramData.loginData.email) {
          console.log(paramData.loginData.email)
          alert("이메일을 입력하세요");
        }
        else if (!reg.test(paramData.loginData.email)) {
          alert("이메일 형식을 확인하세요");
        }
        else {
        axios.post(SERVER.SERVER_URL +'/account/login ', formData)
          .then(response => {
            if(response.status == 200){
                state.dialog = true
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
                router.go()
              }
            })
            .catch((error)=>{
              alert("로그인 실패");
              console.log(error)
              state.dialog = false;
            })
        }
      }else if(paramData.loginData.isManager === 'radio-2'){
        console.log(paramData.loginData.mid)
        console.log(paramData.loginData.password)
        formData.append('mid', paramData.loginData.mid)
        formData.append('password', paramData.loginData.password)
        axios.post(SERVER.SERVER_URL +'/manager/login ', formData)
          .then(response => {
            if(response.status == 200){
                state.dialog = true
                console.log(response)
                alert("로그인 성공");
                state.dialog = false
                state.isLoggedIn = true
                cookies.set('auth-token', {
                  token:response.data.object.accessToken,
                  email:response.data.email,
                  uid:response.data.uid,
                  mid:response.data.mid
                })
                state.authToken = cookies.get('auth-token')                
                router.go()
              }
            })
            .catch((error)=>{
              alert("로그인 실패");
              console.log(error)
              state.dialog = false;
            })
      }
    },
    logout({ commit, state }, path){
      state.authToken = null
      state.isLoggedIn = false
      cookies.remove('auth-token')
      if (path !== constants.URL_TYPE.MAIN){
        router.push({name: constants.URL_TYPE.MAIN})
      }else{
        router.go()
      }
    },
    // 데이터 조회할때 유저 null 값 나옴
    find({commit, state}, email){
      axios.get(SERVER.SERVER_URL + '/account/find?email=' + email)
      .then(response=>{
        state.profileData.email = response.data.object.email
        state.profileData.name = response.data.name
        state.profileData.job = response.data.object.job
        state.profileData.phone = response.data.object.phone
        if(response.data.object.marriaged){
          state.profileData.marriaged = "기혼"
        }else{
          state.profileData.marriaged = "미혼"
        }
        
        if(response.data.object.sex){
          state.profileData.sex = "여자"
        }else{
          state.profileData.sex = "남자"
        }
        // state.profileData.sex = response.data.object.sex
        // state.profileData.birthdate = response.data.object.birthdate
        
        var data = new Date();
        var year = data.getFullYear();
        var count = new Date(response.data.object.birthdate);
        var year2 = count.getFullYear();
        var result = year - year2 + 1
        state.profileData.birthdate = result.toString()
        console.log(response.data.adoptions)
        state.profileData.nickName = response.data.object.uid
        state.profileData.password = response.data.object.password
        state.adoptionData = response.data.adoptions
        state.profileData.flag = response.data.object.flag
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
    userUpdate({commit, state}, userData){
      var marriaged = ''
      if(userData.password === ''){
        userData.password = state.profileData.password
      }
      if(userData.marriaged === '기혼'){
        marriaged = 1
      }else{
        marriaged = 0
      }
      axios.put(SERVER.SERVER_URL + `/account/update`,{
          uid: state.profileData.nickName,
          name: state.profileData.name,
          email: state.profileData.email,
          password: userData.password,
          phone: userData.phone,
          job: userData.job,
          marriaged: marriaged,
          birthdate: state.profileData.birthdate
      }).then(res=>{
          alert('수정 성공')
      }).catch(err=>{
          alert('수정 실패')
          console.log(err)
      })
    },
    mainList({commit, state}, paramInfo){
      if(paramInfo.pageno === 0){
        state.dogData = []
      }
      var parameterInfo = '?pageno=' + paramInfo.pageno
      if(paramInfo.userInfo !== undefined){
        parameterInfo = parameterInfo + '&uid=' + paramInfo.userInfo
      }
      console.log(parameterInfo)
      axios
        .get(SERVER.SERVER_URL + "/care/list" + parameterInfo)
        .then((res) =>{
          console.log(res)
            if(!res.data.hasNext){
              state.isLast = true
            }else{
              state.isLast = false
            }
            if(state.currentPage !== paramInfo.pageno){
              state.dogData.push(res.data.object)
            }
        })
        .catch((err) =>{
            console.log(err)
        })
    },
    setSearchDogs({commit}, payload){
      commit('setSearchDogs', payload)
    },

    isLoggedInChecker({commit}, payload){
      commit('checkLoggedIn', payload)
    },
    isManagerChecker({commit}, payload){
      commit('checkManager', payload)
    },
    getProfile({commit}){
      let pro = commit('getProfileData')
      console.log("액션에서 ")
      console.log(pro)
      return commit('getProfileData')
    }
  },

  modules: {
  }
})
