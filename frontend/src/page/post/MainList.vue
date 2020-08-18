<template>
  <div>
    <div id="main_img">
      <div id="main_txt">
        <v-container>
        <v-row>
          <v-col cols="6" class="tables text-left" style="padding-top:40vh;">
            <h2>유기견들의 가족이 되어주세요.</h2>
            <h2>Be My Family</h2>
            <h2>Be Your Family</h2>
          </v-col>
          <v-col cols="12" sm="4" class="ml-auto text-center" style=" padding-top:30vh;">
            <h2 class="">Wating for your choice</h2>
            <div>
              <img src="../../assets/images/그림1.png" style="width:50%" alt="">

            </div>
            <h2>
              <ICountUp
                :delay="delay"
                :endVal="endVal"
                :options="options"
                @ready="onReady"
              /> 마리
            </h2>
            
          </v-col>
        </v-row>
        <div style="margin:10rem;" @click="scrollDown">
          <i class="fas fa-chevron-down"></i>
        </div>
        </v-container>
      </div>
    </div>
    <div>
    </div>
    
    <v-container>

      <v-row class="mainlist" style="position:relative;">
        <v-col col="12" md="6" class="my-auto">
          <h1 style="color:#4ba5cd;font-family: 'GmarketSansBold';">유기동물 보호소</h1>
          <h3 style="color:gray;">유기동물 보호소 있는 강아지들의 가족이 되어주세요</h3>
          <v-btn class="my-5">GO</v-btn>
        </v-col>

        <v-col col="12" md="6" style="padding:0rem 4rem;">
          <v-row>
            <v-col v-for="item in careList" :key="item.id" col="6" md="6">
              <v-card style="border-radius:10px;">
                <v-img
                  class="white--text align-end"
                  height="10rem"
                  :src="item.popfile"
                >
                </v-img>
                <v-card-subtitle class="pb-0">보호소 : {{ item.carenm }}</v-card-subtitle>
                <v-card-text class="text--primary">
                  <div>{{ item.kindcd}}</div>
                  <br />
                  <span class="comment">특징 : {{ item.specialmark }}</span>
                  <br>
                  <span class="comment">나이 : {{ item.age }}</span>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
        
          <div style="position:absolute;bottom:0%;left:50%;" @click="scrollDown2">
            <i class="fas fa-chevron-down"></i>
          </div>
      </v-row>


      <v-row class="mainlist2" style="position:relative;">
        <v-col col="12" md="6" style="padding:0rem 4rem;" >
          <v-row>
            <v-col v-for="item in lostList" :key="item.id" col="6" md="6">
              <v-card style="">
                <v-img
                  class="white--text align-end"
                  height="10rem"
                  src=""
                >
                </v-img>
                <v-card-subtitle class="pb-0">{{ item.losttype }}</v-card-subtitle>
                <v-card-text class="text--primary">
                  <div>{{ item.lostbread}}</div>
                  <br />
                  <span class="comment">실종지역 : {{ item.lostsido }}</span>
                  <br>
                  <span class="comment">특징 : {{ item.lostcontent }}</span> 
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
        <v-col col="12" md="6" class="my-auto text-right">
          <h1 style="color:#f2cc59;font-family: 'GmarketSansBold';">실종 / 보호 / 목격</h1>
          <h3 style="color:gray;">강아지를 잃어버리신 분들이나</h3>
          <h3 style="color:gray;">보호하거나 목격하신 분들은 알려주세요.</h3>
          <v-btn class="my-5">GO</v-btn>
        </v-col>
          <div style="position:absolute;bottom:0%;left:50%;" @click="scrollDown3">
            <i class="fas fa-chevron-down"></i>
          </div>
      </v-row>
      



      <v-row class="mainlist3" style="position:relative;">
        <v-col col="12" md="6" class="my-auto">
          <h1 style="color:#4ba5cd;font-family: 'GmarketSansBold';">입양 후기</h1>
          <h3 style="color:gray;">유기동물 보호소에서 입양한 강아지들의 이야기를 들려주세요.</h3>
          <v-btn class="my-5">GO</v-btn>
        </v-col>
        <v-col col="12" md="6" style="padding:0rem 4rem;">
          <v-row>
            <v-col v-for="item in postscriptList" :key="item.id" col="6" md="6">
              <v-card style="">
                <v-img
                  class="white--text align-end"
                  height="10rem"
                  :src="item.image"
                >
                </v-img>
                <v-card-subtitle class="pb-0">보호소 : {{ item.carenm }}</v-card-subtitle>
                <v-card-text class="text--primary">
                  <div>{{ item.kindcd}}</div>
                  <br />
                  <span class="comment">특징 : {{ item.specialmark }}</span>
                  <br>
                  <span class="comment">나이 : {{ item.age }}</span>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      
    </v-container>

    <v-btn class="topBtn" @click="moveTop">
      <i class="fas fa-arrow-up"></i>
    </v-btn>
  </div>
</template>
 
<script>
import InfiniteLoading from "vue-infinite-loading";
import ICountUp from 'vue-countup-v2';
import constants from '../../lib/constants';
import axios from 'axios'

export default {
  name: "Main",
  components: {
    InfiniteLoading,
    ICountUp
  },
  watch: {},
  created() {
    this.getAllList()
    // window.addEventListener("scroll", this.handleScroll);
  },
  beforeDestroy: function () {
    // window.removeEventListener("scroll", this.handleScroll);
  },
  methods: {
    scrollDown(){
      var location = document.querySelector(".mainlist").offsetTop;
      window.scrollTo({
        top: location-72,
        behavior: 'smooth'
      })
    },
    scrollDown2(){
      var location2 = document.querySelector(".mainlist2").offsetTop;
      window.scrollTo({
        top: location2-72,
        behavior: 'smooth'
      })
    },
     scrollDown3(){
      var location3 = document.querySelector(".mainlist3").offsetTop;
      window.scrollTo({
        top: location3-70,
        behavior: 'smooth'
      })
     },
    // 나중에 window 크기 맞춰서 offset 조정해줘야함
    handleScroll() {
      // var d = document.documentElement;
      // var offset = d.scrollTop + window.innerHeight + 16;
      // var height = d.offsetHeight;
      //   console.log(d)
      //   console.log(offset)
      //   console.log( height )

      // if (offset >= height) {
      //   this.scrolled += 8;
      // }
      //   console.log(this.scrolled)
    },
    moveTop() {
      window.scrollTo(0, 0);
    },
    onReady: function(instance, CountUp) {
        const that = this;
        instance.update(that.endVal);
      },
    getAllList() {
      axios.get(constants.SERVER_URL + '/mainpage')
      .then((res)=>{
        console.log(res.data)
        this.careList = res.data.careList
        console.log(this.careList)
        this.lostList = res.data.lostList
        this.postscriptList = res.data.postscriptList
      }
      )
    },
  },
  data: () => {
    return {
      scrolled: 12,
      timer: null,
      careList:{},
      lostList:{},
      postscriptList:{},
      delay: 1000,
        endVal: 320,
        options: {
          useEasing: true,
          useGrouping: true,
        }
    };
  },
};
</script>

<style>
#main_img{
  position: absolute;
  top:0;
  left:0;
  z-index:1;
  background-size:cover;
  background-image:url('../../assets/images/friends-3042751_1920.jpg');
  width:100%;
  height: 100vh;
  /* line-height: 100vh; */
}
#main_txt{
  background-color:rgba(0,0,0,0.6);
  height:100vh;
  text-align: center;
  color:rgba(255,255,255,0.7);
 
  /* height: 100vh; */
}
.mainlist{
  position: relative;
  margin-top:100vh;
  height: 100%;

}
.topBtn {
  position: fixed;
  bottom: 50px;
  right: 30px;
}
@media ( max-width:760px){
  .tables{
    display:none;
  }
}
</style>