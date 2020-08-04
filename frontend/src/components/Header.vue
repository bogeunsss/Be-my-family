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
                      <v-text-field v-model="loginData.email" label="Email*" required></v-text-field>
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
                <p >* 아직 회원이 아니신가요?</p>
                <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn--text">회원가입</router-link>
              </v-card-text>
              <div class="d-flex float-right mr-3">
                <v-btn color="blue darken-1" text @click="setDialog">Close</v-btn>
                <v-btn color="blue darken-1" text @click="Login">Login</v-btn>

              </div>
            </v-card>
          </v-dialog>
        <!-- </v-list-item-content> -->
          <p class="mb-0" v-if="isLoggedIn">{{ $store.state.profileData.nickName }}</p>
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
            <v-list-item-title style="cursor: pointer;">Home</v-list-item-title>
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
          <div class="ml-5 d-flex inline">
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
          <div class="ml-5 d-flex inline">
            <!-- survey 아이콘, 글자 눌렀을 때 survey 페이지로 이동 -->
            <v-list-item-icon style="cursor: pointer;">
              <i
                class="far fa-file-alt"
                v-if="isLoggedIn"
                @click="userSurvey"
                style="font-size:30px"
              ></i>
            </v-list-item-icon>
            <v-list-item-title v-if="isLoggedIn"  @click="userSurvey" style="cursor: pointer;">Survey</v-list-item-title>
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
      <v-btn outlined @click="goList">List</v-btn>
      <v-btn outlined>실종/보호/목격</v-btn>
      <v-btn outlined>입양후기</v-btn>
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
    ...mapState(["dialog", "loginData", "profileData"]),
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
    this.isLoggedIn = this.$cookies.isKey("auth-token");
    if(this.isLoggedIn){
      var token = this.$cookies.get('auth-token')
      this.find(token.email)
    }
  },
  methods: {
    ...mapActions(["login", "logout", "find"]),
    ...mapMutations(["setDialog"]),
    Login() {
      this.login(this.$store.state.loginData);
    },
    Logout() {
      this.logout();
      this.$router.push({ name: constants.URL_TYPE.MAIN });
    },
    userProfile() {
      console.log(this.$store.state.loginData);
      this.$router
        .push({ name: constants.URL_TYPE.USER.PROFILE })
        .catch((err) => {
          err;
        });
    },
    userLike() {
      this.$router
        .push({ name: constants.URL_TYPE.USER.LIKE })
        .catch((err) => {
          err;
        });
    },
    userSurvey() {
      // console.log(this.$store.state.loginData);
      this.$router
        .push({ name: constants.URL_TYPE.USER.SURVEY })
        .catch((err) => {
          err;
        });
    },
    goList(){
      this.$router
        .push({ name: constants.URL_TYPE.POST.LIST })
    }
  },

  data: function () {
    return {
      constants,
      drawer: null,
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
