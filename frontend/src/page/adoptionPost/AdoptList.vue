<template>
<div>
<div class="bmg-review" style="margin-top:4rem;">
</div>
  <div style="">
  <v-container>
    <v-col cols="10" style="margin:0 auto;">
      <!-- <div class="d-flex mb-5">
        <i class="fas fa-dog" style="font-size:30px"></i>
        <h2 class="ml-3">입양후기</h2>
      </div> -->

      <v-row align="center">
        <v-col cols="12" sm="4"></v-col>
        <v-col cols="12" sm="3" class="mt-5">
          <v-select
            v-model="category"
            :items="items"
            label="카테고리"
            item-text="state"
            item-value="abbr"
            solo
            class="ml-5"
            style="margin-left:auto;"
          ></v-select>
        </v-col>

        <v-col cols="12" sm="5">
          <v-text-field
            v-model="searchText"
            @keypress.enter="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
            class="mb-5"
            style="width:400px; margin-left: auto;"
          ></v-text-field>
        </v-col>
      </v-row>

      <div class="d-flex" v-if="!isManager">
        <!-- <v-btn @click="create" class="mt-5" style="margin-left:auto">후기작성</v-btn> -->
        <v-btn @click="create" style="margin-left:auto" outlined color="blue">
          <v-icon left>mdi-pencil</v-icon>후기작성
        </v-btn>
      </div>

      <!-- <v-simple-table 
        class="mt-5" 
        page.sync="page">

        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left">No.</th>
              <th class="text-left">Title</th>
              <th class="text-left">Writer</th>
              <th class="text-left">Data</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(adopt, index) in adoptData" :key="index" @click="adoptdetail(adopt.postscriptno)">
              <th scope="row">{{ adoptData.length - index }}</th>
              <td>{{ adopt.title }}</td>
              <td>{{ adopt.uid}}</td>
              <td>{{ nowdate(adopt.createdate)}}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table> -->

      <v-card class="my-3" style="width:1000px;" v-for="(adopt, index) in adoptData" :key="index" @click="adoptdetail(adopt.postscriptno)">
        <v-row>
          <v-col cols="0" md="3" style="">
            <v-img
            :src="'http://i3b201.p.ssafy.io/file/' + postPic[index].postpath"
            style="max-height:150px;"
            class="cardmobile ml-3">
              </v-img> 
          </v-col>
          <v-col cols="12" md="9" class="px-5">
            <!-- <div>
              # {{ adoptData.length - index }}
            </div> -->
            <div class="d-flex pr-3">
              <p class="mb-0"> #{{ adopt.postscriptno }} {{ adopt.title }}</p>
              <small class="cardmobile ml-auto mb-0">{{ nowdate(adopt.createdate)}}</small>
            </div>
            <div class="d-flex">
              <div class="mt-10">
                <small>{{ elipsis(adopt.content) }}</small>
              </div>
              <small class="cardmobile ml-auto pr-3 mt-0">{{ adopt.uid}}</small>
            </div>
          </v-col>
        </v-row>
      </v-card>

      <v-pagination v-model="page" :length="pageCount"  :total-visible="5" circle class="mt-5"></v-pagination>
    </v-col>
  </v-container>
  </div>
  </div>
</template>

<script>  
import constants from "@/lib/constants";
import axios from "axios";

import { mapState, mapActions } from "vuex";

import swal from 'sweetalert';

export default {
  name: "Adoptlist",
  computed:{

  },
  created() {
    if(this.$cookies.isKey('auth-token')){
      if(this.$cookies.get('auth-token').mid !== undefined){
        this.isManager = true
      }
    }
    this.adoptList();
  },
  data() {
    return {
      page: 1,
      pageCount: 0,
  
      items: [                
          { state: '글제목', abbr: 'title' },
          { state: '작성자', abbr: 'uid' }],
      category: {},
      searchText: '',
      adoptData: {
        postscriptno: "",
        uid: "",
        title: "",
        content: "",
        image: "",
        sido: "",
        gugun: "",
        kind: "",
        createdate: "",
      },
      postPic:[],
      isManager: false,
    };
  },
  methods: {
    
    elipsis (temp) {
      var lth =80;
      var temp = "" + temp
      if (temp.length > lth) {
        temp = temp.substr(0, lth-2) + '...';
        return temp
      }else{
        return temp
      }
    },
    adoptList(){
      axios
          .get(constants.SERVER_URL + "/postscript/List?pageno="+this.page)
          .then((res) =>{
            console.log(res)
            this.adoptData = res.data.object
            this.pageCount = res.data.totalPage
            this.postPic = res.data.postPic
            console.log(this.pageCount)
            console.log(this.adoptData)
          })
          .catch((err)=>{
            console.log(err)
          })
    },
    create() {
      if(!this.$cookies.isKey("auth-token")){
            swal({
              title: '로그인해주세요',
              icon: "warning",
              button: "OK"
              })
        }else{
          window.scrollTo(0, 0)
          this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTCREATE })}
    },
    adoptdetail(postscriptno) {
      window.scrollTo(0, 0)
      this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTDETAIL, params:{ ID: postscriptno} })
    },
    nowdate(createdate){
        var nowdate = createdate+""
        return nowdate.substring(0,10)
    },
    search(){
        console.log(this.category)
        console.log(this.searchText)
        console.log(this.page)
        if(this.searchText === ""){
                this.adoptList()
        }else{
            axios.get(constants.SERVER_URL + `/postscript/Search?category=${this.category}&searchText=${this.searchText}&pageno=${this.page}`)
            .then((response) =>{
                this.adoptData = response.data.object
                this.searchText = ""
                console.log(response)

            })
            .catch(() =>{
                swal({
                  title:"올바른 값을 입력하세요",
                  icon: "error",
                  button: "OK" 
                })
            })
        }

    },
    checkPage(){
      axios.get(constants.SERVER_URL + "/postscript/List?pageno="+this.page)
          .then((res) =>{
            this.adoptData = res.data.object
            console.log(this.adoptData)
          })
          .catch((err)=>{
            console.log(err)
          })
    }
  },
  watch :{
    page: function(v){
      this.checkPage()
    }
  }
};
</script>

<style scoped>
@media (max-width:760px){
  .cardmobile {
    display:none;
  }
}
.bmg-review{
    background: url('../../assets/후기3.png') no-repeat;
    width: 100%;
    height: 35rem;
    background-size: cover;
    background-position: center;
}

</style>