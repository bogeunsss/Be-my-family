<template>
  <div class="container">
    <v-container fluid class="mx-5 mt-5 pt-5">
      <h1>내 관심 강아지</h1>
      <v-row>
        <v-col v-for="interest in interestData" :key="interest.id" class="my-5 pt-5">
          <!-- {{ dogData }} -->
          <v-card v-if="interest.desertionno" class="d-inline-block mx-auto">
            <v-btn @click="deleteLike(interest)"></v-btn>
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
                  <v-card-title>Top 10 Australian beaches</v-card-title>
                </v-col>

                <v-col cols="auto" class="text-center pl-0">
                  <v-row class="flex-column ma-0 fill-height" justify="center">
                    <v-col class="px-0">
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
                    </v-col>
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
    this.getInterest();
  },
  methods: {
    getInterest() {
      axios
        .get(`http://localhost:8080/care/interestList`, {
          params: {
            uid: this.$store.state.profileData.nickName,
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
    getInformation(desertion_no) {
      axios
        .get(`http://localhost:8080/care/detail`, {
          params: {
            desertionNo: desertion_no,
            uid: this.$store.state.profileData.nickName,
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
      this.$router.push({ name: constants.URL_TYPE.POST.DETAIL });
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
        .delete(`http://localhost:8080/care/interestDelete`, { params:{
          uid: uidd,
          desertionno: index.desertionno,
        }})
        .then((response) => {
          console.log(response);
          console.log("성공");
          index.desertionno = null;
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