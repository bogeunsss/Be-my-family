<template>
  <div class="mt-5 ml-5 pt-5">
    <v-container>
    <div class="d-flex mb-5">
    <i class="fas fa-pencil-alt" style="font-size:30px"></i><h2 class="ml-3">설문을 작성해주세요.</h2>
      </div> 
    <form>
    1. 신청인 성명: {{ profileData.name }}
    <v-text-field
      v-model="name"
      label="Name"
    ></v-text-field>
    2. 성별: 
    <v-text-field
      v-model="profileData.sex"
    ></v-text-field>
    3. 연령:
    <v-text-field
      v-model="profileData.birthdate"
    ></v-text-field>
    4. 전화번호:
    <v-text-field
      v-model="profileData.phone"
    ></v-text-field>
    5. Email:
    <v-text-field
      v-model="profileData.email"
      ></v-text-field>
    6. 결혼여부:
    <v-text-field
      v-model="profileData.marriaged"
    ></v-text-field>
    7. 신청인 직업:
    <v-text-field
      v-model="profileData.job"
      style="margin-bottom:4rem;"
    ></v-text-field>
    
    1. 국적
    <v-radio-group  v-model="survey.nation">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="한국"
        value="korea"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="외국"
        value="foreign"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.nation}}
      </div>
    </v-radio-group>
    2. 거주 지역
    <v-container fluid>
    <v-row align="center">
      <v-col cols="12" sm="2">
        <v-subheader v-text="'시/도'"></v-subheader>
      </v-col>
      <v-col cols="12" sm="4">
        <v-select
          v-model="sido"
          :items="sido_states"
          :menu-props="{ maxHeight: '400' }"
          label="Select"
          hint="시/도를 선택하세요"
          persistent-hint
        ></v-select>
      </v-col>

      <v-col cols="12" sm="2">
        <v-subheader v-text="'지역'"></v-subheader>
      </v-col>

      <v-col cols="12" sm="4">
        <v-select
          v-model="gugun"
          :items="gugun_states[sido]"
          label="Select"
          hint="거주 지역을 선택하세요"
          persistent-hint
        ></v-select>
      </v-col>
    </v-row>
  </v-container>

    3. 강아지 키울 장소
     <v-radio-group  v-model="survey.place">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="가정 집 안"
        value="home"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="마당(펜스 O)"
        value="garden_o"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="마당(펜스 X)"
        value="garden_x"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="그 외 사업장"
        value="business"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.place}}
      </div>
    </v-radio-group>

    4. 이전에 반려견을 키운 경험이 있습니까?
    <v-radio-group  v-model="survey.beforeover">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="예"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아니오"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.beforeover}}
      </div>
    </v-radio-group>

    5. 유기견을 입양한 경험이 있습니까?
    <v-radio-group  v-model="survey.beforeadopt">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="예"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아니오"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.beforeadopt}}
      </div>
    </v-radio-group>

    6. 현재 집에서 키우고 있는 동물은 몇 마리 입니까?
    <div class="d-flex justify-content-start" style="margin-top:16px;">
      <v-col cols="12" sm="4">
      <v-text-field
        v-model="survey.presentanimal"
        outlined
        clearable
      ></v-text-field>
      </v-col>
    <v-subheader v-text="'마리'" style="padding-left:1px; margin-top:16px"></v-subheader>
    {{ survey.presentanimal }}
    </div>

    7. 가족 구성원
    <v-form>
    <v-container>
      <v-row>

        <v-col cols="12" sm="4">
         <v-subheader v-text="'3세 미만'" style="padding-left:1px;"></v-subheader>
          <v-text-field
            v-model="survey.familyunder"
            label="몇 명"
            outlined
            clearable
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="1"></v-col>

        <v-col cols="12" sm="4">
        <v-subheader v-text="'3세이상 ~ 8살 미만'" style="padding-left:1px;"></v-subheader>
          <v-text-field
            v-model="survey.familymiddle"
            label="몇 명"
            outlined
            clearable
          ></v-text-field>
        </v-col>

      </v-row>
      {{survey.familyunder}}
      {{survey.familymiddle}}
    </v-container>
  </v-form>

  8. 모든 가족들이 동의 하였습니까?
    <v-radio-group  v-model="survey.familyagree">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="예"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아니오"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.familyagree}}
      </div>
    </v-radio-group>

    9. 동물 파양 횟수 
    <v-radio-group  v-model="survey.dissolution">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="1회"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="2회 이상"
        :value="2"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="없음"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.dissolution}}
      </div>
    </v-radio-group>



  10. 알레르기 및 우울증?
    <v-radio-group  v-model="survey.sickness">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="예"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아니오"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.sickness}}
      </div>
    </v-radio-group>
  
  11. 집에 사람이 없는 시간
    <div class="d-flex justify-content-start" style="margin-top:16px;">
      <v-col cols="12" sm="4">
      <v-text-field
        v-model="survey.alone"
        outlined
        clearable
      ></v-text-field>
      </v-col>
    <v-subheader v-text="'시간'" style="padding-left:1px; margin-top:16px"></v-subheader>
    {{ survey.alone }}
    </div>

  12. 장기 외출 시 반려견을 보호해 줄 장소 
    <v-radio-group  v-model="survey.temp">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="가족"
        value="family"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="호텔"
        value="hotel"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="지인"
        value="friend"
        color="success"
      ></v-radio>
        <v-radio  class="mb-2 ml-4"
        label="없음"
        value="0"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.temp}}
      </div>
    </v-radio-group>

    13. 거주형태 
    <v-radio-group  v-model="survey.house">
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="원룸"
        value="room"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="주택"
        value="housing"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아파트"
        value="apt"
        color="success"
      ></v-radio>
        <v-radio  class="mb-2 ml-4"
        label="다세대/빌라"
        value="villa"
        color="success"
      ></v-radio>
      </v-row>
      {{survey.house}}
      </div>
    </v-radio-group>

  14. 반려견 한달 양육 비용
    <v-form>
    <v-container>
      <v-row>

        <v-col cols="12" sm="4">
         <!-- <v-subheader v-text="'식비:'" style="padding-left:1px;"></v-subheader> -->
          <v-text-field
            v-model="survey.eatmoney"
            label="식비"
            outlined
            clearable
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="1"></v-col>

        <v-col cols="12" sm="4">
        <!-- <v-subheader v-text="'관리 비용:'" style="padding-left:1px;"></v-subheader> -->
          <v-text-field
            v-model="survey.caremoney"
            label="관리 비용"
            outlined
            clearable
          ></v-text-field>
        </v-col>

      </v-row>
      {{survey.eatmoney}}
      {{survey.caremoney}}
    </v-container>
  </v-form>

  <h2>-------------------- 동의 서약 --------------------</h2>
     1. 반려견과 평생을 함께 하실 수 있으십니까? 
      <v-radio-group >
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="예"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아니오"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
      </div>
    </v-radio-group>
     
     2. 양육할 여견이 되지 않을 시 보호소로 돌려 보내실 것에 동의합니까?
      <v-radio-group >
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="예"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아니오"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
     
      </div>
    </v-radio-group>
     
     3. 마이크로 칩 / 중성화 수술 / 입양비에 동의 하십니까?
      <v-radio-group  >
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="예"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아니오"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
     
      </div>
    </v-radio-group>
     
     4. 가정 방문에 동의 하십니까? 
      <v-radio-group>
      <div>
      <v-row class="justify-content-start">
      <v-radio class="ml-2"
        label="예"
        :value="1"
        color="success"
      ></v-radio>
      <v-radio  class="mb-2 ml-4"
        label="아니오"
        :value="0"
        color="success"
      ></v-radio>
      </v-row>
    
      </div>
    </v-radio-group>

    <h2>-------------------- 주관식 --------------------</h2>
    1. 입양을 원하시는 이유는 무엇인가요? 
    <v-textarea label="구체적으로 적어주세요" v-model="survey.reason"></v-textarea>
    2. 입양동물이 짖음, 배변실수, 분리불안, 어지럽히기, 기존강아지와의 트러블을 일으킬 수 있습니다. 
       이점에 대해서 어떻게 생각하시나요? 
    <v-textarea label="구체적으로 적어주세요" v-model="survey.think"></v-textarea>


    <v-checkbox
      v-model="checkbox"
      label="Do you agree?"
    ></v-checkbox>
    {{checkbox}}

    <v-btn class="mr-4" @click="submit">submit</v-btn>
    <!-- <v-btn @click="clear">clear</v-btn> -->
  </form>

 

  </v-container>
  </div>
  
</template>

<script>
import constants from "../../lib/constants";
import SERVER from "@/lib/constants";
import axios from "axios";
import { mapState, mapActions } from 'vuex'
import VueJwtDecode from 'vue-jwt-decode'

export default {
    name: "survey",
    created(){
      var token = this.$cookies.get('auth-token')
      this.find(token.email)
      axios.get("http://localhost:8080/care/survey?uid="+this.profileData.nickName)
      .then((response) =>{
        console.log(response)
      })
    },
    computed: {
          ...mapState(['profileData', 'loginData']),
    },
    methods: {
        ...mapActions(['find']),
        submit(){
          if(this.checkbox) {
            console.log('된다')
            // axios
            //   .post(`${URL}`)
            //   .then((response) =>{
            //     console.log(response.data);
            //   }).catch((error) =>{
            //     console.log(error)
            //   })
          }else{
            alert('동의해주세요!')
            console.log('자자')
          }
        }
    },
    data(){
        return {
          token:'',
          name:"",
          sex:"",
          birthdate:"",
          phone:"",
          email:"",
          marriaged:"",
          job:"",
          survey:{
            nation:"korea",
            place:"home",
            beforeover:1,
            beforeadopt:1,
            presentanimal:"",
            familyunder:"",
            familymiddle:"",
            familyagree:1,
            dissolution:0,
            sickness:0,
            alone:"",
            temp:"family",
            house:"room",
            eatmoney:"",
            caremoney:"",
            reason:"",
            think:"", 
          },
          sido: [],
          gugun: [],
          sido_states: [
            '서울특별시', '부산광역시', '인천광역시', '대전광역시',
            '대구광역시', '울산광역시', '광주광역시', '세종특별자치시',
            '경기도', '강원도', '충청북도','충청남도', '전라북도', '전라남도', 
            '경상북도','경상남도'
          ],
          gugun_states: {
            // 서울
            '서울특별시':['강서구', '양천구', '구로구', '영등포구','동작구','금천구','관악구',
            '서초구', '강남구', '송파구', '강동구','마포구','용산구','성동구','광진구',
            '서대문구', '중구', '동대문구','중랑구', '성북구', '종로구','은평구', 
            '강북구','노원구','도봉구'],
            // 부산
            '부산광역시':['해운대구','남구','동구','서구','북구','동래구','사하구'],
            // 인천
            '인천광역시':['서구','계양구','동구','미추홀구','연수구','부평구','중구','강화군'],
            // 대전
            '대전광역시':['서구','중구','동구','유성구','대덕구'],
            // 대구
            '대구광역시':['남구','수성구','달서구','북구'],
            // 울산
            '울산광역시':['북구','동구','중구','남구'],
            // 광주
            '광주광역시':['서구','남구','동구','광산구'],
            // 경기도
            '경기도':['수원시','성남시','의정부시','안양시','부천시','광명시','평택시',
            '동두천시','안산시','고양시','과천시','구리시','남양주시','오산시',
            '시흥시','군포시','의왕시','하남시','용인시','파주시','이천시','김포시',
            '광주시','양주시','포천시','안성시'],
            // 강원도
            '강원도':['철원군','화천구','양구군','고성군','춘천시','인제군','속초시',
            '홍천군','양양군','원주시','횡성군','평창군','강릉시','영월군',
            '정선군','동해시','태백시','삼척시'],
            // 충청북도
            '충청북도':['청주시','충추시','제천시','보은군','옥천군','영동군','증편군',
            '진천군','괴산군','음성군','단양군'],
            // 충청남도
            '충청남도':['태안군','서산시','당진시','아산시','천안시','홍성군','예산군',
            '보령시','청양군','공주시','서천군','부여군','논산시','계룡시','금산군'],
            // 전라북도
            '전라북도':['군산시','익산시','김제시','전주시','남원시','정읍시','무주군','장수군',
            '부안군','진안군','임실군','고창군','순창군','완주군'],
            // 전라남도
            '전라남도':['목포시','여수시','순천시','나주시','광양시','담양군','곡성군','구례군',
            '고흥군','보성군','화순군','장흥군','강진군','해남군','영암군','무안군',
            '함평군','영광군','장성군','완도군','진도군','신안군'],
            // 경상북도
            '경상북도':['포항시','경주시','김천시','안동시','구미시','영주시','영천시','상주시',
            '문경시','경산시','군위군','의성군','청송군','영양군','영덕군','예천군',
            '봉화군','울진군','울룽군'],
            //경상남도
            '경상남도':['창원시','진주시','통영시','사천시','김해시','밀양시','거제시','양산시',
            '의령군','함안군','창녕군','고성군','남해군','하동군','산청군','함양군',
            '거창군','합천군'],
          },
          checkbox:"",
        }
    },
}
</script>

<style>



</style>