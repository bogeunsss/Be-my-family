<template>
<div class="container" style="width:40%;">
  <v-card
    class="mx-auto"
    width="95%"
    height="100%"
  >
    <v-toolbar
      flat
      color="blue-grey"
      dark
    >
      <v-toolbar-title>상세게시판</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <img :src="dogData.popfile" alt="dog images" width="100%" height="400px">

      <!-- <v-text-field filled label="Title" value="My new post"></v-text-field> -->
      <v-row>
          <v-col col="12" class="font-weight-black">구조일</v-col>
          {{ this.dogData.noticesdt }}
      </v-row>
      <v-row>
          <v-col col="12" class="font-weight-black">구조장소</v-col>
          {{ this.dogData.happenplace }}
      </v-row>
      <v-row>
          <v-col col="6" class="font-weight-black">견종</v-col>
          {{ this.dogData.kindcd }}
          <v-col col="6" class="font-weight-black">성별</v-col>
          {{ this.dogData.sexcd}}
      </v-row>
      <v-row>
          <v-col col="6" class="font-weight-black">연령</v-col>
          {{ this.dogData.age }}
          <v-col col="6" class="font-weight-black">모색</v-col>
          {{ this.dogData.colorcd }}
      </v-row>
      <v-row>
          <v-col col="6" class="font-weight-black">중성화 여부</v-col>
          {{ this.dogData.neuteryn }}
          <v-col col="6" class="font-weight-black">체중</v-col>
          {{ this.dogData.weight }}
      </v-row>
      <v-row>
          <v-col col=12 class="font-weight-black">특징</v-col>
          {{ this.dogData.specialmark }}
      </v-row>

      <v-divider class="my-2"></v-divider>
    </v-card-text>

    <v-card-actions class="d-flex justify-center">
      
      <v-btn
        color="success"
        depressed
      >
        관심이써여~
      </v-btn>
      <v-btn
        color="primary"
        depressed
      >
        입양할래여~
      </v-btn>
    </v-card-actions>
  </v-card>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'

export default {
    name:'detail',
    data(){
        return {
          dogData: {
            age: '',
            careaddr:'',
            carenm: '',
            caretel: '',
            chargenm: '',
            colorcd: '',
            desertionno: '',
            filename: '',
            happendt: '',
            happenplace: '',
            kindcd: '',
            neuteryn: '',
            noticecomment: '',
            noticeno: '',
            noticesdt: '',
            numofrows: '',
            officetel: '',
            orgnm: '',
            pageno: '',
            popfile: '',
            processstate: '',
            resultcode: '',
            resultmsg: '',
            sexcd: '',
            specialmark: '',
            totalcount: '',
            weight: '',
          }
        }
    },
    computed:{
      ...mapState(['profileData']),
    },
    created(){
      console.log(this.$route.params.desertionno)
      // console.log(this.$store.state.profileData)
      axios.get(`http://localhost:8080/care/detail`, { 
        params : {
          desertionNo: this.$route.params.desertionno,
          uid: this.$store.state.profileData.nickName
        }
      })
          .then(response => {
              // this.dogs = response.data.message
              console.log(response.data.object)
              this.dogData = response.data.object
          })
          .catch( error => {
              console.log(error)
          })
    },
}
</script>

<style>

</style>