<template>
<div class="container">
  <v-container fluid class="mx-5 mt-5 pt-5">
    <h1>내 관심 강아지</h1>
    {{ interestData }}
    <v-row>
      <v-col v-for="interest in interestData" :key="interest.id" class="my-5 pt-5">
        {{ dogData }}
        <v-card class="d-inline-block mx-auto">
          <v-container>
            <v-row justify="space-between">
              <v-col cols="auto">
              <v-hover v-slot:default="{ hover }">
                <v-img height="300" width="300" :src="interest.desertionno" >
                  <!-- <v-expand-transition> -->
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out brown lighten-5 v-card--reveal display-3 white--text"
                      style="height: 100%;"
                    >갱얼쥐 dog</div>
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
import { mapState } from 'vuex'
import axios from "axios";

export default {
  name: "like",
  computed:{
      ...mapState(['profileData']),
    },
  created() {
    this.getInterest()
  },
  methods: {
    getInterest() {
      axios.get(`http://localhost:8080/care/interestList` , { params:{
        uid : this.$store.state.profileData.nickName,
      }
     })
     .then(response => {
       console.log(response.data.object)
        this.interestData = response.data.object
        this.getInformation()
     })
     .catch(err=>console.log(err))
    },
    getInformation(){
      axios.get(`http://localhost:8080/care/detail`, { 
        params : {
          desertionNo: this.interestData.desertionno,
          uid: this.$store.state.profileData.nickName
        }
      })
      .then(response => {
          console.log(response.data.object)
          this.dogData = response.data.object
      })
      .catch( error => {
          console.log(error)
      })
    },
  },
  data() {
    return {
      interestData: {},
      dogData:{},
    }
  }
};
</script>

<style scoped>
</style>