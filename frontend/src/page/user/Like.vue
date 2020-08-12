<template>
  <div class="container">
    <v-container fluid class="mx-5 mt-5 pt-5">
      <h1>내 관심 강아지</h1>
      <v-row>
        <v-col v-for="interest in interestData" :key="interest.id" class="my-5 pt-5">
          <!-- {{ dogData }} -->
          <v-card v-if="interest.desertionno" class="d-inline-block mx-auto" style="width:400px">
            <i @click="deleteLike(interest)" class="fas fa-backspace float-right" style="font-size:40px; cursor:pointer"></i>
            <v-container>
              <v-row justify="space-between">
                <v-col cols="auto">
                  <v-hover v-slot:default="{ hover }">
                    <v-img
                      height="300"
                      width="300"
                      :src="dogData.popfile"
                      @click="goDetail(interest)"
                    >
                      <!-- <v-expand-transition> -->
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out brown lighten-5 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >dog dog dog dog</div>
                      <!-- </v-expand-transition> -->
                    </v-img>
                  </v-hover>
                  <v-card-title>강아지입니다</v-card-title>
                </v-col>

                <v-col cols="auto" class="text-center pl-0">
                  <v-row class="flex-column ma-0 fill-height" justify="center">
                    <!-- <v-col class="px-0">
                      <v-btn icon>
                        <v-icon>mdi-heart</v-icon>
                      </v-btn>
                    </v-col>

                    <v-col class="px-0">
                      <v-btn icon>
                        <v-icon>mdi-bookmark</v-icon>
                      </v-btn>
                    </v-col>

                    <v-col class="px-0">
                      <v-btn icon>
                        <v-icon>mdi-share-variant</v-icon>
                      </v-btn>
                    </v-col> -->
                  </v-row>
                </v-col>
              </v-row>
            </v-container>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>


<script>
import constants from "@/lib/constants";
import SERVER from "@/lib/constants";
import { mapState, mapActions } from "vuex";
import axios from "axios";

export default {
  name: "like",
  computed: {
    ...mapState(["profileData"]),
  },
  created() {
    if(this.$cookies.isKey('auth-token')){
      this.getInterest()
    }
  },
  methods: {
    ...mapActions(['find' ,"isLoggedInChecker"]),
    getInterest() {
      axios
        .get(`http://i3b201.p.ssafy.io/api/care/interestList`, {
          params: {
            uid: this.$cookies.get('auth-token').uid,
          },
        })
        .then((response) => {
          this.interestData = response.data.object;
          for (var i = 0; i < this.interestData.length; i++) {
            this.getInformation(this.interestData[i].desertionno);
          }
        })
        .catch((err) => console.log(err));
    },
    getInformation(desertionno_no) {
      console.log(desertionno_no)
      axios
        .get(`http://i3b201.p.ssafy.io/api/care/detailUser`, {
          params: {
            desertionno: desertionno_no,
            uid: this.profileData.nickName,
          },
        })
        .then((response) => {
          this.dogData = response.data.object;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    goDetail(index) {
      console.log(this.$store.state.profileData.nickName);
      this.$cookies.set("desertionno", {
        desertionno: index.desertionno,
      });
      this.$router.push({ name: constants.URL_TYPE.POST.DETAIL , params : { uuid : this.$store.state.profileData.nickName } });
    },
    deleteLike(index) {
      // let formData = new FormData();
      console.log(this.$store.state.profileData.nickName);
      // formData.append('uid', this.$store.state.profileData.nickName)
      // formData.append('desertionno', index.desertionno)
      // console.log(index.desertionno)
      // console.log(formData)
      const uidd = this.$store.state.profileData.nickName
      axios
        .delete(`http://i3b201.p.ssafy.io/api/care/interestDelete`, { params:{
          uid: uidd,
          desertionno: index.desertionno,
        }})
        .then((response) => {
          console.log(response);
          console.log("성공");
          index.desertionno = null;
          this.getInterest()
          // console.log(index.desertionno)
        })
        .catch((error) => {
          console.log("실패");
        });
    },
  },
  data() {
    return {
      interestData: {},
      dogData: {},
      isLiked: true,
    };
  },
};
</script>

<style scoped>
</style>