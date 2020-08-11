<template>
  <div>
    <v-container style="width:80%">
      <div>
        <div class="d-flex">
          <h1 class="mb-4 text-align:left">My Profile</h1>
          <div class="ml-auto my-auto">
            <router-link v-bind:to="{name:constants.URL_TYPE.USER.UPDATE}" class="update-btn">
              <v-btn style="float:right">회원정보 수정</v-btn>
            </router-link>
            <v-btn v-if="!managerInfo.mid" @click="userAccountDelete" style="float:right">회원탈퇴</v-btn>
          </div>
        </div>
        <v-card class="d-inline-block mx-auto" style="width:100%">
          <v-container>
            <v-row justify="space-between">
              <v-col>
                <v-img height="300" width="300" src="https://picsum.photos/200/300" alt></v-img>
              </v-col>

              <v-col class="text-center pl-0">
                  <div class="w-100" v-if="!managerInfo.mid">
                  <h3 class='my-3'>UserID : {{ profileData.email }}</h3>
                  </div>
                  <br>
                  <div class="w-100" v-if="!managerInfo.mid">
                  <h3 class='my-3'>NickName : {{ profileData.nickName }}</h3>
                  </div>
                  <br>
                  <div class="w-100" v-if="!managerInfo.mid">
                  <h3 class='my-3'>Phone : {{ profileData.phone }}</h3>
                  </div>
                  <br>
                  <div class="w-100" v-if="!managerInfo.mid">
                  <h3 class='my-3'>Job : {{ profileData.job }}</h3>
                  </div>
                  <div class="w-100" v-if="!profileData">
                  <h3 class='my-3'>보호소 Name : {{ managerInfo.mid }}</h3>
                  </div>
                  <br>
                  <div class="w-100" v-if="!profileData">
                  <h3 class='my-3'>Manager ID : {{ managerInfo.email }}</h3>
                  </div>
                  <br>
                  <div class="w-100" v-if="!profileData">
                  <h3 class='my-3'>Name : {{ managerInfo.name }}</h3>
                  </div>
                  <br>
                  <!-- <button @click="test">aaa</button> -->

              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </div>
      <div v-if="!$cookies.get('auth-token').mid">
        <h2>유기견 신청 목록</h2>

        <v-row class="mb-5">
          <v-col v-for="adoption in adoptionList" :key="adoption.id">
            <v-card @click="goDetail">
              <v-card-text class="d-flex">
                <div>유기견 번호 : {{adoption.desertionno}}</div>
                <div class="ml-auto">
                  {{adoption.fixdate}}
                  {{adoption.fixtime}}
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </div>
    </v-container>
  </div>
</template>


<script>
import constants from "../../lib/constants";
import SERVER from "@/lib/constants";

import { mapState, mapActions } from "vuex";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

export default {
  name: "profile",
  // props:{
  //     uemail:String
  // },
  created() {
    // this.getUser()

    // this.isLoggedIn = this.$cookies.isKey('auth-token')
    var token = this.$cookies.get("auth-token");
    if(this.$cookies.get('auth-token').uid){
      this.find(token.email);
      this.getAdoption();
    }else{
      this.getManagerFind(token.email)
    }
  },
  computed: {
    ...mapState(["profileData", "loginData"]),
  },
  methods: {
    ...mapActions(["find", "userDelete", "logout"]),
    userAccountDelete() {
      this.userDelete(this.profileData.nickName);
      this.logout();
    },
    getAdoption() {
      if(this.$cookies.get('auth-token').uid){
        axios
        .get("http://localhost:8080/account/adoptionList", {
          params: { email: this.$cookies.get("auth-token").email },
        })
        .then((res) => {
          console.log(res.data.object);
          this.adoptionList = res.data.object;
        })
        .catch((err) => console.log(err));
      }
    },
    goDetail(){
      this.$router.push({name:constants.URL_TYPE.POST.DETAIL})
    },
    getManagerFind() {
      axios.get('http://localhost:8080/manager/find', {params : {
        email : this.$cookies.get('auth-token').email
      }})
      .then((res) => {
        console.log(res.data)
        this.managerInfo = res.data
        console.log(this.managerInfo)
      })
      .catch((err)=>{
        console.log(err)
      }) 
    },
  },
  data: function () {
    return {
      constants,
      email: "",
      nickName: "",
      token: "",
      item: [],
      adoptionList: [],
      managerInfo:[],
      // profileData:{}
    };
  },
};
</script>
