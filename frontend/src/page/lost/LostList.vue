<template>
  <div style="margin-top:7rem;">
    <v-container >
      <h2 class="mb-5">실종 / 보호 / 목격</h2>
      <form>
        <v-row class="d-flex align-center mx-auto">
          <v-col class="d-flex">
            
          <v-text-field
            id="searchInput"
            label="검색어를 입력하세요"
            v-model="searchText"
            class="ma-0 pa-0"
            @keyup.enter="makeSearchTag"
          ></v-text-field>
          <v-btn fab depressed style="background: transparent" small @click="search">
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
          </v-col>
          <v-col class="d-flex flex-row">
            <v-list v-for="(searchTag, index) in searchTags" :key="index">
              <v-chip close color="pink" text-color="white" @click:close="closeSearchTag(index)">{{ searchTag }}</v-chip>
            </v-list>
          </v-col>
        </v-row>
      </form>
      <v-row dense>
        <v-col
          v-for="(card, i) in cards"
          :key="i"
          cols="12"
          sm="6"
          md="4"
          lg="3"
        >
        <!-- 기존코드 -->
          <!-- <v-card v-if="i<scrolled">
            <v-img
              :src="card.lostpic1"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="300px"
              @click="goDetail(card.lostno)"
            >
              <v-card-title v-text="card.lostsido"></v-card-title>
            </v-img>

            <v-spacer></v-spacer>
            <v-card-actions class="d-flex justify-space-between">
              <v-chip :color="myColors[card.losttype]" text-color="white">{{ card.losttype }}</v-chip>
              <div>
                <v-btn icon>
                  <v-icon @click="copyUrl(card.lostno)">mdi-share-variant</v-icon>
                </v-btn>
              </div>
            </v-card-actions>
          </v-card> -->


          <v-card elevation="24" max-width="444" class="mx-auto"  v-if="card.lostpic2==null">
            <v-system-bar class="d-flex" lights-out style="height:40px;width:100%;">
              <v-chip :color="myColors[card.losttype]" text-color="white">{{ card.losttype }}</v-chip>
              <v-btn icon class="ml-auto">
                <v-icon @click="copyUrl(card.lostno)">mdi-share-variant</v-icon>
              </v-btn>
            </v-system-bar>
            <v-carousel
              :continuous="true"
              cycle="cycle"
              :show-arrows="false"
              hide-delimiter-background
              delimiter-icon="mdi-minus"
              height="300"
            >
              <v-carousel-item  @click="goDetail(card.lostno)"
                v-for="(item, j) in [card.lostpic1]"
                :src="item"
                :key="i+'key'+j"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              >
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                >
                </v-row>
              </v-carousel-item>
            </v-carousel>
            <v-list>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    <small style="font-size:1rem;color:black;">
                    {{card.lostsido}}
                    </small>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card>

          <v-card elevation="24" max-width="444" class="mx-auto"  v-if="card.lostpic3==null  && card.lostpic2!=null">
            <v-system-bar class="d-flex" lights-out style="height:40px;width:100%;">
              <v-chip :color="myColors[card.losttype]" text-color="white">{{ card.losttype }}</v-chip>
              <v-btn icon class="ml-auto">
                <v-icon @click="copyUrl(card.lostno)">mdi-share-variant</v-icon>
              </v-btn>
            </v-system-bar>
            <v-carousel
              :continuous="true"
              cycle="cycle"
              :show-arrows="false"
              hide-delimiter-background
              delimiter-icon="mdi-minus"
              height="300"
            >
              <v-carousel-item  @click="goDetail(card.lostno)"
                v-for="(item, j) in [card.lostpic1, card.lostpic2]"
                :src="item"
                :key="i+'key'+j"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              >
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                >
                </v-row>
              </v-carousel-item>
            </v-carousel>
            <v-list>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    <small style="font-size:1rem;color:black;">
                    {{card.lostsido}}
                    </small>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card>

          <v-card elevation="24" max-width="444" class="mx-auto" v-if="card.lostpic3!=null">
            <v-system-bar class="d-flex" lights-out style="height:40px;width:100%;">
              <v-chip :color="myColors[card.losttype]" text-color="white">{{ card.losttype }}</v-chip>
              <v-btn icon class="ml-auto">
                <v-icon @click="copyUrl(card.lostno)">mdi-share-variant</v-icon>
              </v-btn>
            </v-system-bar>
            <v-carousel
              :continuous="true"
              cycle="cycle"
              :show-arrows="false"
              hide-delimiter-background
              delimiter-icon="mdi-minus"
              height="300"
            >
              <v-carousel-item  @click="goDetail(card.lostno)"
                v-for="(item, j) in [card.lostpic1, card.lostpic2, card.lostpic3]"
                :src="item"
                :key="i+'key'+j"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              >
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                >
                </v-row>
              </v-carousel-item>
            </v-carousel>
            <v-list>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    <small style="font-size:1rem;color:black;">
                    {{card.lostsido}}
                    </small>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card>







        </v-col>
      </v-row>
    </v-container>

  <v-dialog v-if="!isManager" v-model="dialog" scrollable max-width="700px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        color="#4ba5cd"
        fab
        dark
        v-bind="attrs"
        v-on="on"
        class="float-btn"
        v-if="isLoggedIn"
      >
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-card-title>글 작성하기</v-card-title>
      <v-divider></v-divider>
      <v-card-text style="height: 500px;">
        <v-list>
          <v-list-item-subtitle>카테고리</v-list-item-subtitle>
          <v-list-item class="mt-3 d-flex justify-space-between">
            <v-radio-group v-model="lostType" v-for="category in categories" :key="category">
              <v-radio :label=category :value=category></v-radio>
            </v-radio-group>
          </v-list-item>
          <v-list-item-subtitle>성별</v-list-item-subtitle>
          <v-list-item class="mt-3 d-flex justify-space-between">
            <v-radio-group v-model="lostSex" v-for="gd in gender" :key="gd">
              <v-radio :label=gd :value=gd></v-radio>
            </v-radio-group>
          </v-list-item>
          <v-list-item-subtitle>품종</v-list-item-subtitle>
          <v-list-item class="mt-3">
            <v-select
              v-model="lostBreed"
              :items="kinds"
              label="선택해주세요"
              solo
            ></v-select>
          </v-list-item>
          <v-row>
            <v-col>
              <v-list-item-subtitle>시/도</v-list-item-subtitle>
              <v-list-item class="mt-3">
                <v-select
                  v-model="lostSido"
                  :items="sido_states"
                  label="선택해주세요"
                  solo
                ></v-select>
              </v-list-item>
            </v-col>
            <v-col>
              <div v-if="lostSido.length > 0">
                <v-list-item-subtitle>구/군</v-list-item-subtitle>
                <v-list-item class="mt-3">
                  <v-select
                    v-model="lostGugun"
                    :items="gugun_states[lostSido]"
                    label="선택해주세요"
                    solo
                  ></v-select>
                </v-list-item>
              </div>
            </v-col>
          </v-row>

          <v-row>
            <v-col>
              <v-list-item-subtitle>상세주소</v-list-item-subtitle>
              <v-list-item>
                <v-text-field v-model="lostPlace"></v-text-field>
              </v-list-item>
            </v-col>
            <v-col>
              <v-list-item-subtitle>발생날짜</v-list-item-subtitle>
              <v-list-item>
                <v-text-field
                  color="primary"
                  @click="dialog2 = !dialog2"
                  v-model="lostDate"
                  label="발생 날짜를 선택해주세요"
                  readonly
                ></v-text-field>
              </v-list-item>
            </v-col>
          </v-row>
          
          <v-list-item>
            <p class="font-weght-black mr-3">태그:</p>
            <div class="d-flex flex-column">
              <v-text-field v-model="lostTagText" @keyup.enter="makeApplyTag"></v-text-field>
              <div class="d-flex flex-row">
                <v-list v-for="(lostTag, index) in lostTags" :key="index">
                  <v-chip close color="teal" text-color="white" @click:close="closeTag(index)">{{ lostTag }}</v-chip>
                </v-list>
              </div>
            </div>
            <v-tooltip bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-icon
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
                >mdi-alert-circle-outline</v-icon>
              </template>
              <span>태그 생성 시 #을 입력하세요.</span>
            </v-tooltip>
          </v-list-item>
          <v-list-item>
            <v-textarea v-model="lostContent" outlined label="내용"></v-textarea>
          </v-list-item>
        </v-list>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions class="d-flex justify-space-between">
        <input @change="onChangeImages" type="file" id="inputFiles" multiple="multiple" accept="image/*">
        <div>
          <v-btn type="button" color="blue darken-1" text @click="submit">Save</v-btn>
          <v-btn color="blue darken-1" text @click="closeDialog">Close</v-btn>
        </div>
      </v-card-actions>
    </v-card>
  </v-dialog>

  <v-dialog ref="dialog" v-model="dialog2" :return-value.sync="lostDate" persistent width="290px">
    <v-date-picker v-model="lostDate" scrollable>
      <v-spacer></v-spacer>
      <v-btn color="primary" @click="dialog2 = false">취소</v-btn>
      <v-btn color="primary" @click="$refs.dialog.save(lostDate)">확인</v-btn>
    </v-date-picker>
  </v-dialog>

  <v-snackbar
    v-model="snackbar"
    :timeout="2000"
  >
    복사 되었습니다.

    <template v-slot:action="{ attrs }">
      <v-btn
        color="blue"
        text
        v-bind="attrs"
        @click="snackbar = false"
      >
        Close
      </v-btn>
    </template>
  </v-snackbar>
  </div>
</template>

<script>
import constants from '../../lib/constants'

import axios from 'axios'
import { mapState } from 'vuex'

export default {
  created(){
    if(this.$cookies.isKey('auth-token')){
      if(this.$cookies.get('auth-token').mid !== undefined){
        this.isManager = true
      }
    }
    window.addEventListener("scroll", this.handleScroll);
    this.getList()
  },
  beforeDestroy(){
    window.removeEventListener("scroll", this.handleScroll);
  },
  computed:{
    ...mapState(['sido_states', 'gugun_states', 'profileData', 'isLoggedIn']),
  },
  watch:{
  },
  data(){
    return {
      cards: [],
      kinds:['골든 리트리버', '그레이 하운드', '그레이트 덴', '그레이트 피레니즈', '기타', '꼬똥 드 뚤레아', '네오폴리탄 마스티프', '노르포크 테리어', '노리치 테리어', '뉴펀들랜드', '닥스훈트', '달마시안',
        '댄디 딘몬트 테리어', '도고 까니리오', '도고 아르젠티노', '도고 아르젠티노', '도베르만', '도사', '동경견', '라브라도 리트리버', '라사 압소', '라이카', '래빗 닥스훈드', '랫 테리어', '레이크랜드 테리어', 
        '로디지안 리즈백 ', '로트바일러', '롯트와일러', '마리노이즈', '마스티프', '말라뮤트', '말티즈', '맨체스터테리어', '미니어쳐 닥스훈트', '미니어쳐 불 테리어', '미니어쳐 슈나우저', '미니어쳐 푸들', '미니어쳐 핀셔',
        '미디엄 푸들', '미텔 스피츠', '믹스견', '바센지', '바셋 하운드', '버니즈 마운틴 독', '베들링턴 테리어', '벨기에 그로넨달', '벨기에 쉽독', '벨기에 테뷰런', '벨지안 셰퍼드 독', '보더 콜리', '보르조이', '보스턴 테리어',
        '복서', '볼로네즈', '부비에 데 플랑드르', '불 테리어', '불독', '브뤼셀그리펀', '브리타니 스파니엘', '블랙 테리어', '비글', '비숑 프리제', '비어디드 콜리', '비즐라', '빠삐용', '사모예드', '살루키', '삽살개', '샤페이', 
        '세인트 버나드', '센트럴 아시안 오브차카', '셔틀랜드 쉽독', '셰퍼드', '슈나우져', '스코티쉬 테리어', '스코티시 디어하운드', '스탠다드 푸들', '스테포드셔불테리어', '스피츠', '시바', '시베리안 허스키', '시베리안라이카', 
        '시잉프랑세즈', '시츄', '시코쿠', '실리햄 테리어', '실키테리어', '아나톨리안 셰퍼드', '아메리칸 불독', '아메리칸 스태퍼드셔 테리어', '아메리칸 아키다', '아메리칸 에스키모', '아메리칸 코카 스파니엘', '아메리칸 핏불 테리어', 
        '아메리칸불리', '아이리쉬 레드 앤 화이트 세터', '아이리쉬 세터', '아이리쉬 울프 하운드', '아이리쉬소프트코튼휘튼테리어', '아키다', '아프간 하운드', '알라스칸 말라뮤트', '에어델 테리어', '오브차카', '오스트랄리안 셰퍼드 독', 
        '오스트랄리안 캐틀 독', '올드 잉글리쉬 불독', '올드 잉글리쉬 쉽독', '와이마라너', '와이어 폭스 테리어', '요크셔 테리어', '울프독', '웨스트 시베리언 라이카', '웨스트하이랜드화이트테리어', '웰시 코기 카디건', '웰시 코기 펨브로크', 
        '웰시 테리어', '이탈리안 그레이 하운드', '잉글리쉬 세터', '잉글리쉬 스프링거 스파니엘', '잉글리쉬 코카 스파니엘', '잉글리쉬 포인터', '자이언트 슈나우져', '재패니즈 스피츠', '잭 러셀 테리어', '저먼 셰퍼드 독', '저먼 와이어헤어드 포인터', 
        '저먼 포인터', '제주개', '제페니즈칭', '진도견', '차우차우', '차이니즈 크레스티드 독', '치와와', '카레리안 베어독', '카이훗', '캐벌리어 킹 찰스 스파니엘', '케니스펜더', '케리 블루 테리어', '케언 테리어', '케인 코르소', '코리아 트라이 하운드', 
        '코리안 마스티프', '코카 스파니엘', '코카 푸', '코카시안오브차카', '콜리', '클라인스피츠', '키슈', '키스 훈드', '토이 맨체스터 테리어', '토이 푸들', '티베탄 마스티프', '파라오 하운드', '파슨러셀 테리어', '팔렌', '퍼그', '페키니즈', '페터데일테리어', 
        '포메라니안', '포인터', '폭스테리어', '푸들', '풀리', '풍산견', '프레사까나리오', '프렌치 불독', '프렌치 브리타니', '플랫 코티드 리트리버', '플롯하운드', '피레니안 마운틴 독', '필라 브라질레오', '핏불테리어', '허배너스', '화이트리트리버', '화이트테리어', '휘펫'
      ],
      categories: ['실종', '보호', '목격'],
      gender:['암컷', '수컷', '알 수 없음'],
      myColors:{
        '실종': 'red',
        '보호': 'green',
        '목격': 'orange',
      },
      dialog:false,
      dialog2:false,
      lostBreed: '',
      lostSido: '',
      lostGugun: '',
      lostContent: '',
      images: [],
      lostType: '',
      lostDate: null,
      lostPlace: '',
      lostSex: '',
      lostAge: '',
      lostTagText: '',
      lostTags: [],
      tagState: false,
      searchText: '',
      searchTags: [],
      tempCards: [],
      scrolled: 12,
      snackbar: false,
      url: '',
      isManager: false,
    }
  },
  methods: {
    getList(){
      axios.get(constants.SERVER_URL + '/lost/list')
      .then(response => {
        // console.log(response.data.object[0].lostpic1)
        this.cards = response.data.object
        this.tempCards = this.cards
      }).catch(error => {
        console.log(error)
      })
    },
    goDetail(index){
      this.$router.push({name: constants.URL_TYPE.LOST.LOSTDETAIL, params: { articleNo: index }})
    },
    closeDialog(){
      this.dialog = false
      this.lostSido = ''
      this.lostGugun = ''
      this.lostBreed = ''
    },
    onChangeImages(event){
      if(event.target.files.length > 3){
        alert('파일은 3개까지 저장 가능합니다.')
        document.getElementById('inputFiles').value = '';
      }else{
        this.images = event.target.files
      }
    },
    submit(){
      const formData = new FormData()
      formData.append('uid', this.profileData.nickName)
      formData.append('lostbreed', this.lostBreed)
      formData.append('losttype', this.lostType)
      formData.append('lostdate', this.lostDate)
      formData.append('lostsido', this.lostSido)
      formData.append('lostgugun', this.lostGugun)
      formData.append('lostplace', this.lostPlace)
      formData.append('lostcontent', this.lostContent)
      formData.append('lostsex', this.lostSex)
      formData.append('lostphone', this.profileData.phone)
      formData.append('losttagtext', this.lostTags)
      for(var x=0;x<this.images.length;x++){
        formData.append('files', this.images[x])
      }
      

      // const imageForm = new FormData()
      // imageForm.append('files', this.images)
      
      // FormData 객체를 log 찍어보려면
      // FormData.entries()를 이용하여 key : value 쌍을 뽑아야 함.
      
      axios.post(constants.SERVER_URL + '/lost/add', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(response => {
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
      
      this.dialog = false
      // this.$router.go(-1)
    },
    closeTag(index){
      this.lostTags.splice(index, 1)
    },
    closeSearchTag(index){
      this.searchTags.splice(index, 1)
      
      if(this.searchTags.length === 0){
        this.getList()
      }else{
        this.search()
      }
    },
    makeSearchTag(){
      if(this.searchText.length > 0){
        if(this.searchText[0] === '#'){
          this.searchTags.push(this.searchText.substring(1,this.searchText.length))
          this.searchText = ''
          this.search()
        }
      }
    },
    makeApplyTag(){
      if(this.lostTagText.length > 0){
        if(this.lostTagText[0] === "#"){
          this.lostTags.push(this.lostTagText.substring(1,this.lostTagText.length))
          this.lostTagText = ''
        }
      }
    },
    search(){
      axios.get(constants.SERVER_URL + '/lost/search?tags='+this.searchTags)
        .then(response => {
          var temp = []
          for(var x in response.data.object){
            for(var y in this.tempCards){
              if(response.data.object[x].lostno === this.tempCards[y].lostno){
                temp.push(this.tempCards[y])
              }
            }
          }
          this.cards = temp
        }).catch(error => {
          console.log(error)
        })
    },
    handleScroll(){
      if(window.scrollY + document.documentElement.clientHeight >= document.documentElement.scrollHeight - 1){
        this.scrolled += 8
      }
    },
    copyUrl(no){
      var temp = document.createElement('textarea')
      document.body.appendChild(temp)
      temp.value = window.document.location.href + '/' + no
      temp.select()
      document.execCommand('copy')
      document.body.removeChild(temp)
      this.snackbar = true
    }
  }
}
</script>

<style>
.float-btn {
  position: fixed;
  bottom: 5vh;
  right: 5vw;
}
</style>