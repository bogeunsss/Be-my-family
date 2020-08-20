<template>
  <div style="margin-top:7rem;">
    <v-container style="width:80%">
      <div>
        <div class="d-flex">
          <h1 class="mb-4 text-align:left"  style="color: #4ba5cd;">My Profile</h1>
          <div class="ml-auto my-auto">
            <router-link v-bind:to="{name:constants.URL_TYPE.USER.UPDATE}" class="update-btn">
              <v-btn style="float:right" color="info" >회원정보 수정</v-btn>
            </router-link>
          </div>
        </div>
        <v-card class="d-inline-block mx-auto" style="width:100% ">
          <v-container>
            <div class="profilebg">
            <v-row justify="space-between" style="height:27rem;">
              <v-col>
                <!-- <v-img height="300" width="300" src="https://picsum.photos/200/300" alt></v-img> -->
              </v-col>

              <v-col class="text-center pl-0 my-auto">
                <div v-if="!managerInfo.mid">
                  <div class="w-100">
                  <h3 class='my-3' style="color: #2caead;" > <span style="color: black;">UserID : </span>  {{ profileData.email }}</h3>
                  </div>
                  <br>
                  <div class="w-100">
                  <h3 class='my-3' style="color: #2caead;"> <span style="color: black;"> NickName : </span>  {{ profileData.nickName }}</h3>
                  </div>
                  <br>  
                  <div class="w-100">
                  <h3 class='my-3' style="color: #2caead;"> <span style="color: black;">Phone : </span>  {{ profileData.phone }}</h3>
                  </div>
                  <br>
                  <div class="w-100">
                  <h3 class='my-3' style="color: #2caead;"> <span  style="color: black;"> Job : </span>  {{ profileData.job }}</h3>
                  </div>
                </div>
                
                <div v-if="managerInfo.mid">
                  <div class="w-100">
                  <h3 class='my-3'  style="color: #2caead;"><span style="color: black;">보호소명 : </span>{{ managerInfo.mid }}</h3>
                  </div>
                  <br>
                  <div class="w-100">
                  <h3 class='my-3'  style="color: #2caead;"><span style="color: black;">보호소 이메일 : </span>{{ managerInfo.email }}</h3>
                  </div>
                  <br>
                </div>
                  <!-- <button @click="test">aaa</button> -->
              </v-col>
            </v-row>
            </div>
          </v-container>
        </v-card>
      </div>
      <br>
      
    <div style="margin-top: 5rem;">      
      <div v-if="!$cookies.get('auth-token').mid">
        <h2 style="color: #4ba5cd;">유기견 신청 목록</h2>
        <v-row class="mt-5">     
          <v-col v-for="adoption in adoptionList" :key="adoption.id" >
            <div class="d-flex justify-end"> 
                <v-btn tile icon @click="deleteAdoption(adoption.desertionno)" small color="error">
                  <v-icon>fas fa-times</v-icon>
                </v-btn>
              <!-- <v-btn @click="deleteAdoption(adoption.desertionno)" small color="error">x</v-btn> -->
            </div>
            <v-card @click="goDetail">
              <v-card-text class="d-flex">
                <diV><p>유기견 번호 : {{adoption.desertionno}}</p>
                  <span>날짜 : 
                  {{adoption.fixdate}}
                  {{adoption.fixtime}}시
                  </span>
                </div>
                <div class="my-auto ml-auto">
                  <v-btn class='my-auto' v-if="adoption.state === 0"  color="warning">승인대기</v-btn>
                  <v-btn class='my-auto' v-if="adoption.state === 1" color="primary">승인완료</v-btn>
                  <v-btn class='my-auto' v-if="adoption.state === 2" color="error">승인거절</v-btn>
                </div>
              </v-card-text>
            </v-card>

          </v-col>
         
        </v-row>
      </div>
    </div>

  <v-btn  v-if="!managerInfo.mid" @click="userAccountDelete" style="float:right; margin-top:3rem;" color="error">회원탈퇴</v-btn>
    </v-container>
  </div>
</template>


<script>
import constants from "../../lib/constants";
import SERVER from "@/lib/constants";

import { mapState, mapActions, mapMutations } from "vuex";
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
    if(this.$cookies.get('auth-token').uid !== undefined){
      this.find(token.email);
      this.getAdoptionList()
      // this.getAdoptionList()
      console.log(this.adoptionData)
      // this.getAdoption();
    }else{
      this.getManagerFind()
    }
  },
  computed: {
    ...mapState(["profileData", "loginData","adoptionData"]),
    // ...mapMutations(["adoptionDataList"])
  },
  methods: {
    ...mapActions(["find", "userDelete", "logout"]),
    // ...mapMutations(["adoptionDataList"]),
    userAccountDelete() {
      this.userDelete(this.profileData.nickName);
      this.logout();
    },
    goDetail(){
      this.$router.push({name:constants.URL_TYPE.POST.DETAIL})
    },
    getManagerFind() {
      axios.get(constants.SERVER_URL + '/manager/find', {params : {
        mid : this.$cookies.get('auth-token').mid
      }})
      .then((res) => {
        // console.log(res.data)
        this.managerInfo = res.data
        // console.log(this.managerInfo)
      })
      .catch((err)=>{
        console.log(err)
      }) 
    },
    deleteAdoption(desertion_no) {
      console.log(desertion_no)
      axios.delete(constants.SERVER_URL + `/adoption/delete?uid=${this.profileData.nickName}&desertionno=${desertion_no}`
      ).then(res=>{console.log(res)
        this.getAdoptionList()
        console.log(this.adoptionList)
        console.log(this.adoptionData)
      })
      .catch(err=>console.log(err))
    },
  getAdoptionList() {
      axios
        .get(constants.SERVER_URL + `/account/find`, { params: {
          email : this.$cookies.get('auth-token').email}
        })
        .then((response) => {
          this.adoptionList = response.data.adoptions;
        })
        .catch((err) => console.log(err));
    },
  },
  data: function () {
    return {
      constants,
      email: "",
      nickName: "",
      token: "",
      item: [],
      adoptionList: {},
      managerInfo:[],
      // profileData:{}
    };
  },
};
</script>
<style scoped>
.profilebg{
    background: url('../../assets/pro44.png') no-repeat;
    height: 27rem;
    background-size: cover;

}


</style>