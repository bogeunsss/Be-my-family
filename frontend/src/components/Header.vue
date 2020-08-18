<template>
  <div v-if="isHeader">
    <div class="mb-5">
    <v-sheet>
      <v-btn class="hambuger" color="cyan" dark @click.stop="drawer = !drawer">
        <i class="lg fas fa-bars" style="font-size:30px"></i>
      </v-btn>
      <v-navigation-drawer v-model="drawer" absolute temporary height="400">
        <v-list-item class="pl-0">
          <v-list-item-avatar v-if="isLoggedIn">
            <v-img src="https://picsum.photos/78.jpg"></v-img>
          </v-list-item-avatar>


          <!-- <v-list-item-content> -->
          <v-dialog v-if="!isLoggedIn" v-model="$store.state.dialog" persistent max-width="600">
            <template v-slot:activator="{ on, attrs }" v-if="!isLoggedIn">
              <v-btn style="transform: translateX(120px) translateY(-50px);" dark v-bind="attrs" v-on="on" class="loginBtn">로그인</v-btn>
            </template>
            <!-- 수정 필요! 로그인 버튼 내려감 -->
            <v-card style="height:50vh;">
              <v-card-title>
                <span class="headline">User Login</span>
              </v-card-title>
              <v-card-text>
                  <!-- <v-row>
                    <v-col cols="12"> -->
                      <v-text-field v-if="radios === 'radio-1'" v-model="loginData.email" label="Email*" required></v-text-field>
                      <v-text-field v-if="radios === 'radio-2'" v-model="loginData.mid" label="Center name*" required></v-text-field>
                    <!-- </v-col>
                    <v-col cols="12"> -->
                      <v-text-field
                        v-model="loginData.password"
                        label="Password*"
                        type="password"
                        required
                      ></v-text-field>
                    <!-- </v-col>
                  </v-row> -->
                    <!-- <v-container fluid> -->
                    <v-radio-group v-model="radios" :mandatory="false">
                      <v-radio label="user" value="radio-1"></v-radio>
                      <v-radio label="manager" value="radio-2"></v-radio>
                    </v-radio-group>
                    <!-- </v-container> -->
                <p >* 아직 회원이 아니신가요?</p>
                <v-btn text v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" color="primary">회원가입</v-btn>
                <v-btn text color="primary" @click="PWDialog = true">pw찾기</v-btn>
                
              </v-card-text>
              <div class="d-flex float-right mr-3">
                <v-btn color="blue darken-1" text @click="setDialog">Close</v-btn>
                <v-btn color="blue darken-1" text @click="Login(radios)">Login</v-btn>

              </div>
            </v-card>
          <v-dialog v-model="PWDialog" max-width="300px">
              <v-card>
                <v-card-title>비밀번호 찾기</v-card-title>
                <v-card-text class="pb-0">
                  <v-text-field label="Email*" required v-model="PWEmail"></v-text-field>
                  <v-text-field label="NickName*" required v-model="PWNickName"></v-text-field>
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions>
                  <v-btn @click="findPW">완료</v-btn>
                  <v-btn @click="PWDialog = false">취소</v-btn>
                  <v-progress-circular
                    class="ml-3"
                    v-if="nowLoading"
                    indeterminate
                    color="primary"
                  ></v-progress-circular>
                </v-card-actions>
              </v-card>
          </v-dialog>
          </v-dialog>
        <!-- </v-list-item-content> -->
          <p class="mb-0" v-if="isLoggedIn && !isManager">{{ profileData.nickName }}</p>
          <p class="mb-0" v-else-if="isLoggedIn && isManager ">{{ $cookies.get('auth-token').mid }}</p>
          <!-- {{$store.state.profileData}} -->
          <!-- </v-list-item-content> -->
        </v-list-item>

        <v-divider></v-divider>

        <v-list dense>
          <div class="ml-5 d-flex inline">
            <v-list-item-icon>
              <router-link v-bind:to="{name:constants.URL_TYPE.MAIN}">
                <i class="fas fa-home black--text" style="font-size:30px"></i>
              </router-link>
            </v-list-item-icon>
            <!-- 홈 글자 눌렀을때 메인으로 이동하는거 고치기 -->
            <v-list-item-title @click="goHome" style="cursor: pointer;">Home</v-list-item-title>
          </div>
          <div class="ml-5 d-flex inline">
            <v-list-item-icon style="cursor: pointer;">
              <i
                class="fas fa-id-card"
                v-if="isLoggedIn"
                @click="userProfile"
                style="font-size:30px"
              ></i>
            </v-list-item-icon>

            <v-list-item-title v-if="isLoggedIn" @click="userProfile" style="cursor: pointer;">My page</v-list-item-title>
          </div>
          <div class="ml-5 d-flex inline" v-if="isLoggedIn && !isManager">
            <!-- like 아이콘, 글자 눌렀을 때 like 페이지로 이동 -->
            <v-list-item-icon style="cursor: pointer;">
              <i
                class="fas fa-paw"
                v-if="isLoggedIn"
                @click="userLike"
                style="font-size:30px"
              ></i>
            </v-list-item-icon>
            <v-list-item-title v-if="isLoggedIn" @click="userLike" style="cursor: pointer;">Like</v-list-item-title>
          </div>
          <div class="ml-5 d-flex inline" v-if="isLoggedIn && !isManager">
            <!-- survey 아이콘, 글자 눌렀을 때 survey 페이지로 이동 -->
            <v-list-item-icon style="cursor: pointer;">
              <i
                class="far fa-file-alt"
                v-if="isLoggedIn"
                @click="userSurvey"
                style="font-size:30px"
              ></i>
            </v-list-item-icon>
            <v-list-item-title v-if="isLoggedIn" @click="userSurvey" style="cursor: pointer;">Survey</v-list-item-title>
          </div>
          <div class="ml-5 d-flex inline" v-if="isLoggedIn && isManager">
            <!-- survey 아이콘, 글자 눌렀을 때 survey 페이지로 이동 -->
            <v-list-item-icon style="cursor: pointer;">
              <i
               class="fas fa-clipboard-list"
                @click="goManager"
                style="font-size:30px"
              ></i>
            </v-list-item-icon>
            <v-list-item-title @click="goManager" style="cursor: pointer;">Manager</v-list-item-title>
          </div>
          <div class="ml-5 d-flex inline">
            <v-list-item-icon style="cursor: pointer;">
              <i
                class="fas fa-sign-out-alt"
                v-if="isLoggedIn"
                @click="Logout"
                style="font-size:30px"
              ></i>
            </v-list-item-icon>
            <v-list-item-title v-if="isLoggedIn" @click="Logout" style="cursor: pointer;">Logout</v-list-item-title>
          </div>
        </v-list>
      </v-navigation-drawer>
    </v-sheet>
    <router-link class="text-decoration-none" v-bind:to="{name:constants.URL_TYPE.MAIN}">
      <h1 class="text-center cyan--text text--lighten-2" >Dog Dog!</h1>
    </router-link>
    </div>
    <div class="container mb-5">
      <v-btn outlined @click="goHome">메인</v-btn>
      <v-btn outlined @click="goList">보호소</v-btn>
      <v-btn outlined @click="goLost">실종/보호/목격</v-btn>
      <v-btn outlined @click="adoptList">입양후기</v-btn>
    </div>
  </div>
</template>

<script>
import constants from "@/lib/constants";
import axios from "axios";

import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "Header",
  components: {
    constants,
  },
  props: ["isHeader"],
  computed: {
    ...mapState(["dialog", "loginData", "profileData", "isLoggedIn"]),
    email: {
      get() {
        return loginData.email;
      },
      set(value) {
        this.$store.commit("loginEmail", value);
      },
    },
    password: {
      get() {
        return loginData.password;
      },
      set(value) {
        this.$store.commit("loginPassword", value);
      },
    },
  },
  watch: {},
  created() {
    var login = this.$cookies.isKey("auth-token");
    if(login){
      var token = this.$cookies.get('auth-token')
      this.isLoggedInChecker(login)
      if(this.$cookies.get('auth-token').mid !== undefined){
        this.isManager = true
      }else{
        this.isManager = false
        this.find(token.email)
        console.log('프로필 ==> ' + this.profileData.nickName)
      }
    //   this.isLoggedInChecker(login)
    //   this.find(token.email)
    // }
    // this.isLoggedIn = this.$cookies.isKey("auth-token");
    // if(this.$cookies.isKey("auth-token")){
      // var token = this.$cookies.get('auth-token')\
    }
  },
  methods: {
    ...mapActions(["login", "logout", "find", "isLoggedInChecker"]),
    ...mapMutations(["setDialog"]),
    Login(radios) {
      this.$store.state.loginData.isManager = radios
      var paramData = {
        loginData: this.loginData,
        page: this.$route.name
      }
      this.login(paramData);
    },
    Logout() {
      this.logout(this.$route.name);
    },
    userProfile() {
      if(this.$route.name !== constants.URL_TYPE.USER.PROFILE){
        this.$router.push({ name: constants.URL_TYPE.USER.PROFILE })
      }else{
        this.$router.go()
      }

    },
    goHome() {
      if(this.$route.name !== constants.URL_TYPE.MAIN){
        this.$router.push({ name: constants.URL_TYPE.MAIN });
      }
    },
    userLike() {
      if(this.$route.name !== constants.URL_TYPE.USER.LIKE){
        this.$router.push({ name: constants.URL_TYPE.USER.LIKE })
      }else{
        this.$router.go()
      }
    },
    userSurvey() {
      if(this.$route.name !== constants.URL_TYPE.USER.SURVEY){
        this.$router.push({ name: constants.URL_TYPE.USER.SURVEY })
      }else{
        this.$router.go()
      }
    },
    goList(){
      if(this.$route.name !== constants.URL_TYPE.POST.LIST){
        this.$router.push({ name: constants.URL_TYPE.POST.LIST })
      }
    },
    adoptList(){
      if(this.$route.name !== constants.URL_TYPE.ADOPTIONPOST.ADOPTLIST){
        this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTLIST })
      }
    },
    goLost(){
      if(this.$route.name !== constants.URL_TYPE.LOST.LOSTLIST){
        this.$router.push({ name: constants.URL_TYPE.LOST.LOSTLIST })
      }
    },
    goManager() {
      if(this.$route.name !== constants.URL_TYPE.USER.MANAGER){
        this.$router.push( { name: constants.URL_TYPE.USER.MANAGER })
      }
    },
    findPW(){
      this.nowLoading = true
      var formData = new FormData()
      formData.append('email', this.PWEmail)
      formData.append('uid', this.PWNickName)
      axios.post(constants.SERVER_URL + '/account/findpassword', formData
      ).then(response => {
        if(response.data.data === 'success'){
          this.PWDialog = false
          alert('이메일로 임시 비밀번호가 발송 되었습니다.')
        }else{
          alert('비밀번호 찾기에 실패하였습니다.')
        }
        this.nowLoading = false
      }).catch(error => {
        console.log(error)
      })
    }
  },

  data: function () {
    return {
      constants,
      drawer: null,
      radios: 'radio-1',
      isManager: false,
      PWDialog: false,
      PWEmail: '',
      PWNickName: '',
      nowLoading: false,
    };
  },
}

</script>

<style scoped>
.hambuger{
  top: 50px;
  left: 50px;
}
.v-navigation-drawer {
  margin-top: 50px;
  top: 50px;
  left: 50px;
}
.loginBtn {
  position: relative;
  top: 50px;
}
.v-list-item-icon {
  display: flex;
  cursor: pointer;
}
.v-list-item-title {
  display: flex;
  cursor: pointer;
}
/* .v-btn{
  transform: translateY(-30px);
} */
</style>
