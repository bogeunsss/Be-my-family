<template>
  <div class="container">
    <v-container fluid class="mx-5 mt-5 pt-5">
      <h1>내 관심 강아지</h1>
      <v-row>
        <v-col cols="4" v-for="(dogData, i) in dogDatas" :key="i">
          <v-hover v-slot:default="{ hover }">
            <v-card
              :elevation="hover ? 12 : 2"
              @click="goDetail(dogData.desertionno)"
            >
              <p class="text-center ma-0">{{ dogData.kindcd }}</p>
              <v-img
                :src="dogData.popfile"
                width="400px"
                height="400px"
              >
                <div :class="{ 'show-btns': hover }">
                  <v-card-title class="title" v-if="hover">
                    <v-row class="fill-height flex-column" justify="space-between">
                      
                      <div>                            
                          <p col="6" class="font-weight-black text-left font-italic ma-2">성별: {{ dogData.sexcd}}</p>
                          <p col="6" class="font-weight-black text-left font-italic ma-2">연령: {{ dogData.age }}</p>
                          <p col="6" class="font-weight-black text-left font-italic ma-2">모색: {{ dogData.colorcd }}</p>
                          <p col="6" class="font-weight-black text-left font-italic ma-2">체중: {{ dogData.weight }}</p>
                          <p col="12" class="font-weight-black text-left font-italic ma-2">특징: {{ dogData.specialmark }}</p>
                      </div>
                    </v-row>
                  </v-card-title>
                </div>
              </v-img>
            </v-card>
          </v-hover>
          <!-- <v-card v-if="interest.desertionno" class="d-inline-block mx-auto" style="width:400px">
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
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out brown lighten-5 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >
                        <v-row>
                          <v-col col="12" class="font-weight-black">구조일</v-col>
                          {{ dogData.noticesdt }}
                        </v-row>
                        <v-row>
                          <v-col col="12" class="font-weight-black">구조장소</v-col>
                          {{ dogData.happenplace }}
                        </v-row>
                        <v-row>
                          <v-col col="6" class="font-weight-black">견종</v-col>
                          {{ dogData.kindcd }}
                          <v-col col="6" class="font-weight-black">성별</v-col>
                          {{ dogData.sexcd}}
                        </v-row>
                        <v-row>
                          <v-col col="6" class="font-weight-black">연령</v-col>
                          {{ dogData.age }}
                          <v-col col="6" class="font-weight-black">모색</v-col>
                          {{ dogData.colorcd }}
                        </v-row>
                        <v-row>
                          <v-col col="6" class="font-weight-black">중성화 여부</v-col>
                          {{ dogData.neuteryn }}
                          <v-col col="6" class="font-weight-black">체중</v-col>
                          {{ dogData.weight }}
                        </v-row>
                        <v-row>
                          <v-col col="12" class="font-weight-black">특징</v-col>
                          {{ dogData.specialmark }}
                        </v-row>
                      </div>
                    </v-img>
                  </v-hover>
                  <v-card-title>강아지입니다</v-card-title>
                </v-col>

                <v-col cols="auto" class="text-center pl-0">
                  <v-row class="flex-column ma-0 fill-height" justify="center">
                    
                  </v-row>
                </v-col>
              </v-row>
            </v-container>
          </v-card> -->
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
        .get(constants.SERVER_URL + `/care/interestList`, {
          params: {
            uid: this.$cookies.get('auth-token').uid,
          },
        })
        .then((response) => {
          console.log(response)
          this.interestData = response.data.object;
          for (var i = 0; i < this.interestData.length; i++) {
            axios
              .get(constants.SERVER_URL + `/care/detailUser`, {
                params: {
                  desertionno: this.interestData[i].desertionno,
                  uid: this.profileData.nickName,
                },
              })
              .then((response) => {
                var tempData = Object
                tempData = response.data.object
                if(response.data.object.sexcd === 'F'){
                  tempData.sexcd = '암컷'
                }else if(response.data.object.sexcd === 'M'){
                  tempData.sexcd = '수컷'
                }
                console.log(response)
                this.dogDatas.push(tempData)
              })
              .catch((error) => {
                console.log(error);
              });
          }
        })
        .catch((err) => console.log(err));
    },
    getInformation(desertionno_no) {
      
    },
    goDetail(desertionNo) {
      console.log(this.$store.state.profileData.nickName);
      this.$cookies.set("desertionno", {
        desertionno: desertionNo,
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
        .delete(constants.SERVER_URL + `/care/interestDelete`, { params:{
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
      dogDatas: [],
      isLiked: true,
    };
  },
};
</script>

<style scoped>
.v-card {
  transition: .4s ease-in-out;
}

.v-card:hover {
  opacity: 0.6;
}

.show-btns {
  color: rgba(255,255,255) !important;
}
</style>