<template>
  <div>
    <v-container >
      <!-- <form>
        <v-row class="d-flex align-center mx-auto">
          <v-col></v-col>
          <v-col class="d-flex">
            <v-text-field
              label="검색어를 입력하세요"
              v-model="searchText"
              class="mx-2"
            ></v-text-field>
            <v-btn large @click="search" type="submit">
              검색
            </v-btn>
          </v-col>
        </v-row>
      </form> -->
      <v-row dense>
        <v-col
          v-for="(card, i) in cards"
          :key="i"
          :cols="card.flex"
        >
          <v-card @click="goDetail(i+1)">
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
            >
              <v-card-title v-text="card.title"></v-card-title>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

  <v-dialog v-model="dialog" scrollable max-width="700px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        color="success"
        fab
        dark
        v-bind="attrs"
        v-on="on"
        class="float-btn"
      >
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-card-title>글 작성하기</v-card-title>
      <v-divider></v-divider>
      <v-card-text style="height: 500px;">
        <v-list>
          <v-list-item-subtitle>품종</v-list-item-subtitle>
          <v-list-item class="mt-3">
            <v-select
              v-model="selectKinds"
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
                  v-model="selectSido"
                  :items="sido_states"
                  label="선택해주세요"
                  solo
                ></v-select>
              </v-list-item>
            </v-col>
            <v-col>
              <div v-if="selectSido.length > 0">
                <v-list-item-subtitle>구/군</v-list-item-subtitle>
                <v-list-item class="mt-3">
                  <v-select
                    v-model="selectGugun"
                    :items="gugun_states[selectSido]"
                    label="선택해주세요"
                    solo
                  ></v-select>
                </v-list-item>
              </div>
            </v-col>
          </v-row>
          <v-list-item>
            <p class="font-weght-black mr-3">태그</p>
            <div id="characteristic"></div>
          </v-list-item>
          <v-list-item>
            <v-textarea outlined label="내용" v-model="content"></v-textarea>
          </v-list-item>
        </v-list>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions class="d-flex justify-end">
        <v-btn color="blue darken-1" text @click="dialog = false; selectSido = ''">Close</v-btn>
        <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
  </div>
</template>

<script>
import constants from '../../lib/constants'
import { mapState } from 'vuex'

export default {
  computed:{
    ...mapState(['sido_states', 'gugun_states']),
  },
  watch:{
    content(newVal, oldVal){
      console.log(this.status)
      console.log(newVal[newVal.length-1])
      if(!this.status && newVal[newVal.length-1] === '#'){
        this.status = true
      }else if(this.status && (newVal[newVal.length-1] === ' ' || newVal[newVal.length-1] === '\n')){
        this.status = false
        const icon = document.createElement('v-chip');
        // icon.className = "material-icons icon";
        icon.innerHTML = this.sampleText.trim();
        var main = document.getElementById('characteristic');
        main.appendChild(icon);
      }
      if(this.status){
        this.sampleText = this.sampleText + newVal[newVal.length-1]
      }
    },
  },
  data(){
    return {
      cards: [
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
      ],
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
      dialog:false,
      selectKinds: '',
      selectSido: '',
      selectGugun: '',
      content: '',
      status: false,
      sampleText: '',
    }
  },
  methods: {
    goDetail(index){
      this.$router.push({name: constants.URL_TYPE.LOST.LOSTDETAIL, params: { articleNo: index }})
    },
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