<template>
  <div class="container">
    <v-container fluid class="mx-5 mt-5 pt-5">
      <h1>내 관심 강아지</h1>
      <v-row>
        <v-col cols="4" v-for="(interest, i) in interestData" :key="i">
          <v-hover v-slot:default="{ hover }">
            <v-card
              :elevation="hover ? 12 : 2"
              @click="goDetail(interest.desertionno)"
            >
              <p class="text-center ma-0">{{ interest.kindcd }}</p>
              <v-img
                :src="interest.popfile"
                width="400px"
                height="400px"
              >
                <div :class="{ 'show-btns': hover }">
                  <v-card-title class="title" v-if="hover">
                    <v-row class="fill-height flex-column" justify="space-between">
                      
                      <div>                            
                          <p col="6" class="font-weight-black text-left font-italic ma-2">성별: {{ interest.sexcd}}</p>
                          <p col="6" class="font-weight-black text-left font-italic ma-2">연령: {{ interest.age }}</p>
                          <p col="6" class="font-weight-black text-left font-italic ma-2">모색: {{ interest.colorcd }}</p>
                          <p col="6" class="font-weight-black text-left font-italic ma-2">체중: {{ interest.weight }}</p>
                          <p col="12" class="font-weight-black text-left font-italic ma-2">특징: {{ interest.specialmark }}</p>
                      </div>
                    </v-row>
                  </v-card-title>
                </div>
              </v-img>
            </v-card>
          </v-hover>
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
      console.log(this.$store.state.profileData.nickName);
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