<template>
  <div>
    <v-container>
      <h2 class="text-align:left">My Profile</h2>
      <v-card class="d-inline-block mx-auto">
        <v-container>
          <v-row justify="space-between">
            <v-col cols="auto">
              <v-img height="300" width="300" src="https://picsum.photos/200/300" alt></v-img>
            </v-col>

<<<<<<< HEAD
                </v-col>
            </v-row>
            </v-container>
            <router-link v-bind:to="{name:constants.URL_TYPE.USER.UPDATE}" class="update-btn">
                <v-btn style="float:right">회원정보 수정</v-btn>
            </router-link>  
            <v-btn @click="userAccountDelete" style="float:right">회원탈퇴</v-btn>
        </v-card>
        <v-list>
            <v-list-item>

            </v-list-item>
        </v-list>
=======
            <v-col cols="auto" class="text-center pl-0">
              <v-row class="flex ma-0 fill-height" justify="center">
                <div>UserID : {{ profileData.email }}</div>
                <!-- <button @click="test">aaa</button> -->
                <div>UserName : {{ profileData.nickName }}</div>
              </v-row>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
      <div></div>
      <!-- <v-btn @click="userUpdate" style="float:right">회원정보 수정</v-btn> -->
      <router-link v-bind:to="{name:constants.URL_TYPE.USER.UPDATE}" class="update-btn">
        <v-btn style="float:right">회원정보 수정</v-btn>
      </router-link>
      <v-btn @click="userAccountDelete" style="float:right">회원탈퇴</v-btn>
>>>>>>> 9837d476902c24a09f48daa81f4ba23764a5971a
    </v-container>
  </div>
</template>


<script>
import constants from "../../lib/constants";
import SERVER from "@/lib/constants";

import { mapState, mapActions } from "vuex";
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";

<<<<<<< HEAD
    import { mapState, mapActions } from 'vuex'
    import axios from 'axios'
    import VueJwtDecode from 'vue-jwt-decode'
    
    export default {
        name:'profile',
        // props:{
        //     uemail:String
        // },
        created(){
            // this.getUser()
            
            // this.isLoggedIn = this.$cookies.isKey('auth-token')
            var token = this.$cookies.get('auth-token')
            this.find(token.email)
            axios.get('http://localhost:8080/account/adoptionList?uid='+this.profileData.nickName)
                .then(response => {
                    console.log(response)
                }).catch(error => {
                    console.log(error)
                })
        },
        computed: {
          ...mapState(['profileData', 'loginData']),
        },
        methods: {
            ...mapActions(['find', 'userDelete', 'logout']),
            userAccountDelete(){
                this.userDelete(this.profileData.nickName)
                this.logout()
            }
            // userDelete() {
            //     console.log(this.nickName)
            //     axios.delete(`http://localhost:8080/account/delete?uid=${this.nickName}`)
            //     .then(function(res){
            //         alert("회원탈퇴 되었습니다.");
            //         this.$router.push({name:constants.URL_TYPE.POST.MAIN})
            //     })
            //     .catch((err)=> {console.log(err)});
                
            // },
            // userUpdate() {
            //     axios.put("http://localhost:8080/account/update")
            //     .then(function(res){
            //         this.$router.push({name:constants.URL_TYPE.USER.UPDATE})
            //     })
            // },
            // test(){
            //     console.log(this.nickName)
            // },
            // getUser(){
            //     this.email = this.$cookies.get('email')
            //     this.nickName = this.$cookies.get('uid')
            //     var password = this.$cookies.get('password')
            // }
        },
        data: function() {
            return {
                constants,
                email: '',
                nickName:'',
                token:'',
                item : [],
                // profileData:{}
            }
        },
=======
export default {
  name: "profile",
  // props:{
  //     uemail:String
  // },
  created() {
    // this.getUser()
>>>>>>> 9837d476902c24a09f48daa81f4ba23764a5971a

    // this.isLoggedIn = this.$cookies.isKey('auth-token')
    var token = this.$cookies.get("auth-token");
    this.find(token.email);
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
    // userDelete() {
    //     console.log(this.nickName)
    //     axios.delete(`http://localhost:8080/account/delete?uid=${this.nickName}`)
    //     .then(function(res){
    //         alert("회원탈퇴 되었습니다.");
    //         this.$router.push({name:constants.URL_TYPE.POST.MAIN})
    //     })
    //     .catch((err)=> {console.log(err)});

    // },
    // userUpdate() {
    //     axios.put("http://localhost:8080/account/update")
    //     .then(function(res){
    //         this.$router.push({name:constants.URL_TYPE.USER.UPDATE})
    //     })
    // },
    // test(){
    //     console.log(this.nickName)
    // },
    // getUser(){
    //     this.email = this.$cookies.get('email')
    //     this.nickName = this.$cookies.get('uid')
    //     var password = this.$cookies.get('password')
    // }
  },
  data: function () {
    return {
      constants,
      email: "",
      nickName: "",
      token: "",
      item: [],
      // profileData:{}
    };
  },
};
</script>