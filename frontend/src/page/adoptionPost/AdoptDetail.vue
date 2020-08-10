<template>
  <v-form>
    <v-container class="pa-0">
      <h1>입양후기</h1>
      <v-card>
        <v-toolbar flat color="white">
          <v-list-item>
            <v-list-item-avatar color="grey"></v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title class="headline">입양후기</v-list-item-title>
              <v-list-item-subtitle>작성자(닉네임)</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-toolbar>
        <v-card-subtitle class="mx-auto" style="width: 60%">
          <v-img
            src="http://www.animal.go.kr/files/shelter/2014/02/201403010903285_s.jpg"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            max-height="100%"
            height="auto"
          ></v-img>
        </v-card-subtitle>

        <v-card-actions class="d-flex justify-end mb-3">
          <v-btn icon large>
            <v-icon large color="pink">mdi-heart</v-icon>
          </v-btn>
          <v-btn icon large>
            <v-icon large>mdi-share-variant</v-icon>
          </v-btn>
        </v-card-actions>

        <v-divider></v-divider>

        <v-card-text>
          <p><span style="font-weight:bold; font-size:1.2rem;">제목 : </span><span>{{Adoptdata.title}}</span></p>
          <p><span style="font-weight:bold; font-size:1.2rem;">지역 : </span><span>{{Adoptdata.sido}} {{Adoptdata.gugun}}</span></p>
          <p><span style="font-weight:bold; font-size:1.2rem;">품종 : </span><span>{{Adoptdata.kind}}</span></p>
          <p><span style="font-weight:bold; font-size:1.2rem;">내용 : </span><span>{{Adoptdata.content}}</span></p>
  
        </v-card-text>

        <v-card-actions class="d-flex justify-end">
          <v-btn text color="deep-purple accent-4" v-if="this.$cookies.isKey('auth-token')">수정</v-btn>
          <v-btn text color="red accent-4" v-if="this.$cookies.isKey('auth-token')">삭제</v-btn>
        </v-card-actions>
      </v-card>


      <v-card class="mx-auto mt-5" max-width="100%" min-height="3rem" outlined style="border-top:none; border-right:none; border-left:none;">
        <div style="line-height:3rem;">
          <span class="mb-1 mr-5" style="font-weight:bold;">작성자</span>
          <span>댓글 내용</span>
        </div>
      </v-card>

      <div class="d-flex justify-left mt-5">
         <v-textarea
          label="Comment"
          auto-grow
          outlined
          rows="1"
          row-height="15"
        ></v-textarea>
        <!-- <v-textarea class="mx-2" label="Comment" rows="1"></v-textarea> -->
        <v-btn class="ma-2 mt-0" outlined medium fab color="indigo" style="border:none;">
          <v-icon medium>mdi-comment</v-icon>
        </v-btn>
      </div>

      <v-card-actions class="d-flex justify-center">
        <v-btn @click="adoptlist" class="ma-2" outlined color="indigo">목록</v-btn>
      </v-card-actions>
    </v-container>
  </v-form>
</template>

<script>
import constants from "@/lib/constants";
import axios from 'axios'

export default {
  created(){
    this.adoptdetail()
  },
  methods: {
    adoptlist() {
      this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTLIST });
    },
    adoptdetail(){
      axios.get(`http://localhost:8080/postscript/detail?postscriptno=`+this.$route.params.ID)
      .then((res) =>{
        this.Adoptdata = res.data.object
        console.log(this.Adoptdata)
      })
      .catch((error) =>{
        console.log(error)
      })
    }
  },
  data() {
    return {
      Adoptdata: {},
      
    };
  },
};
</script>


<style>
</style>