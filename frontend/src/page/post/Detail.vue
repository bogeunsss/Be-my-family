<template>
  <div class="container" style="width:40%;">
    
    <v-card class="mx-auto" width="95%" height="100%">
      <v-toolbar flat color="blue-grey" dark>
        <v-toolbar-title>상세게시판</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <img :src="dogData.popfile" alt="dog images" width="100%" height="400px" />

        <!-- <v-text-field filled label="Title" value="My new post"></v-text-field> -->
        <v-row>
          <v-col col="12" class="font-weight-black">구조일</v-col>
          {{ dogData.noticesdt }}
        </v-row>
        <v-row>
          <v-col col="12" class="font-weight-black">구조장소</v-col>
          {{ dogData.happenplace }}
        </v-row>
        <v-row>
          <v-col col="6" class="font-weight-black">견종</v-col>
          {{ dogData.kindcd }}
          <v-col col="6" class="font-weight-black">성별</v-col>
          {{ dogData.sexcd}}
        </v-row>
        <v-row>
          <v-col col="6" class="font-weight-black">연령</v-col>
          {{ dogData.age }}
          <v-col col="6" class="font-weight-black">모색</v-col>
          {{ dogData.colorcd }}
        </v-row>
        <v-row>
          <v-col col="6" class="font-weight-black">중성화 여부</v-col>
          {{ dogData.neuteryn }}
          <v-col col="6" class="font-weight-black">체중</v-col>
          {{ dogData.weight }}
        </v-row>
        <v-row>
          <v-col col="12" class="font-weight-black">특징</v-col>
          {{ dogData.specialmark }}
        </v-row>

        <v-divider class="my-2"></v-divider>
      </v-card-text>

      <v-card-actions class="d-flex justify-center">
        <div v-if="isLoggedIn  && !isManager">
        <v-btn color="success" depressed v-if="!isLikeDog" @click="likeDog">관심이써여~</v-btn>
        <v-btn color="success" depressed v-if="isLikeDog" @click="deleteLike">관심업서여</v-btn>
        <v-btn color="primary" class="ma-2" v-if="!isAdoption" dark @click="goModal(dialog = true)">입양신청</v-btn>
        <v-btn color="primary" class="ma-2" dark v-if="isAdoption">신청대기중</v-btn>
        </div>
        <!-- total 보내야 할 데이터 : email, 상담날짜, 상담시간, 강아지id, url: /account/adoptionList -->
        <v-dialog v-model="dialog" max-width="500px">
          <v-card>
            <v-card-title>입양신청서</v-card-title>
            <v-card-text>
              <!-- 개인 정보 template -->

              <v-list outlined>
                <h3 class="mb-3">사용자 정보</h3>
                <v-list-item class="d-flex flex-column align-start">
                  <p>이름: {{user.name}}</p>
                  <p>성별: {{user.sex}}</p>
                  <p>나이: {{userAge}}세</p>
                  <p>이메일: {{user.email}}</p>
                  <p>전화번호: {{user.phone}}</p>
                  <p>직업: {{user.job}}</p>
                  <p>결혼여부: {{user.marriaged}}</p>
                </v-list-item>
              </v-list>
              <v-list outlined>
                <h3 class="mb-3">설문 정보</h3>
                <v-list-item class="d-flex flex-column align-start">
                  <p>국적: {{ survey.nation }}</p>
                  <p>양육 장소: {{ survey.place }}</p>
                  <p>양육 경험: {{ survey.beforeover }}</p>
                  <p>입약 경험: {{ survey.beforeadopt }}</p>
                  <p>보유 동물: {{ survey.presentanimal }}마리</p>
                  <p>가족구성원</p>
                  <p>3세 미만: {{ survey.familyunder }}명, 3~8세: {{ survey.familymiddle }}명</p>
                  <p>가족 동의: {{ survey.familyagree }}</p>
                  <p>파양 횟수: {{ survey.dissolution }}회</p>
                  <p>알레르기 및 우울증 여부: {{ survey.sickness }}</p>
                  <p>집에 사람이 없는 시간: {{ survey.alone }}시간</p>
                  <p>장기 외출 시 반려견 보호 장소: {{ survey.temp }}</p>
                  <p>거주 형태: {{ survey.house }}</p>
                  <p>최대 가능 사료 비용: {{ survey.eatmoney }}원</p>
                  <p>최대 가능 관리 비용: {{ survey.caremoney }}원</p>
                  <p>입양을 원하는 이유: {{ survey.reason }}</p>
                  <p>반려견과의 트러블에 대한 생각: {{ survey.think }}</p>
                </v-list-item>
              </v-list>

              <v-text-field
                color="primary"
                @click="dialog2 = !dialog2"
                v-model="date"
                label="상담 날짜를 선택해주세요"
                readonly
              ></v-text-field>
              <!-- 상담 시간 template -->
              <v-text-field
                color="primary"
                @click="dialog3 = !dialog3"
                v-model="selectedTime"
                label="상담 시간을 선택해주세요"
                readonly
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <!-- @click 에다가 신청서 보내는 비동기 요청 함수 달기. -->
              <v-btn color="primary" @click="requestComplete" text>신청완료</v-btn>
              <v-btn color="primary" text @click="dialog = false">취소</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!-- 날짜 입력 v-date-picker -->
        <v-dialog ref="dialog" v-model="dialog2" :return-value.sync="date" persistent width="290px">
          <v-date-picker v-model="date" scrollable>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="dialog2 = false">취소</v-btn>
            <v-btn color="primary" @click="$refs.dialog.save(date)">확인</v-btn>
          </v-date-picker>
        </v-dialog>
        <!-- 시간 입력 -->
        <v-dialog
          ref="dialog2"
          v-model="dialog3"
          :return-value.sync="selectedTime"
          persistent
          width="290px"
        >
          <v-list>
            <v-list-item-group>
              <v-list-item v-for="time in times" :key="time">
                <v-list-item-content @click="$refs.dialog2.save(time+'시')">{{ time }}시</v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-dialog>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import constants from '@/lib/constants'
import axios from 'axios'
import { mapState, mapActions } from 'vuex'

export default {
    data(){
        return {
          dogData: {
            popfile: '',
          },
          dialog: false,
          dialog2: false,
          dialog3: false,
          menu: false,
          date: null,
          selectedTime: null,
          times: [
            '9','10','11','12','13','14','15','16','17','18'
          ],
          email: '',
          isLikeDog: false,
          isLoggedIn: false,
          isManager: false,
          isAdoption:false,
          user: Object,
          userAge: 0,
          survey: Object,
        }
    },
    computed:{
      param(){
        return this.$route.params.desertionno
      },
      ...mapState(['profileData','loginData','adoptionData'])
    },
    created(){
      this.getDetail()

    },
    methods:{
      ...mapActions(['find']),
      getDetail(){
        if(this.$cookies.isKey("auth-token")){
          var token = this.$cookies.get('auth-token')
          this.email = token.email
          this.isLoggedIn = true

          if(this.$cookies.get('auth-token').uid !== undefined){
              this.find(token.email)
              console.log(this.$route.params.desertionno)
              setTimeout(()=>{
                axios.get(constants.SERVER_URL + '/care/detailUser', {
                  params: {
                    desertionno: this.$route.params.desertionno,
                    uid: this.profileData.nickName
                  }
                })
                .then( response => {
                    this.dogData = response.data.object
                    for (var i = 0; i < this.adoptionData.length; i++){
                      if(this.adoptionData[i].desertionno === this.$route.params.desertionno){
                        this.isAdoption = true
                      }
                    }
                    if(this.$route.params.uuid === undefined){
                      this.isLikeDog = response.data.interest
                    }else{
                      this.isLikeDog = true
                    }
                })
                .catch( error => {
                    console.log(error)
                })
              }, 100)
      
          }else{
              this.isManager = true
              axios.get(constants.SERVER_URL + '/care/detailUser', {
                params: {
                  desertionno: this.$route.params.desertionno,
                }
              })
              .then( response => {
                  console.log(response)
                  this.dogData = response.data.object
              })
              .catch( error => {
                  console.log(error)
              })
             }
            }
        else{
          axios.get(constants.SERVER_URL + '/care/detailUser', {
                params: {
                  desertionno: this.$route.params.desertionno,
                }
              })
              .then( response => {
                  console.log(response)
                  this.dogData = response.data.object
              })
              .catch( error => {
                  console.log(error)
              })
             }
          
      },

      requestComplete(){
        let st = ""
        if(this.selectedTime.length > 2){
          st = this.selectedTime.slice(0,2)
        }else{
          st = this.selectedTime.slice(0,1)
        }
        var formData = new FormData()
        formData.append('fixdate', this.date)
        formData.append('fixtime', st)
        formData.append('uid', this.profileData.nickName)
        formData.append('desertionno', this.$route.params.desertionno)
        // formData.append('mid',this.survey.carenm)

        axios.post(constants.SERVER_URL + '/adoption/Success', formData)
          .then(response => {
            console.log(response)
            // this.$cookies.set('isadoption')
            this.isAdoption = true
            alert('성공')

          }).catch(error => {
            console.log('실패')

          })
        this.dialog = false
      },
    likeDog() {
      let formData = new FormData();
      formData.append("uid", this.$store.state.profileData.nickName);
      formData.append("desertionno", this.dogData.desertionno);
      console.log(this.$store.state.profileData.nickName);
      axios
        .post(constants.SERVER_URL + "/care/interestAdd", formData)
        .then((response) => {
          console.log(response.data);
          console.log(response.data.interest)
          this.isLikeDog = response.data.interest;
          // this.dogData = response.data.object;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteLike() {
    console.log(this.$store.state.profileData.nickName);
    console.log(this.$cookies.get('nickName'))
    axios
      .delete(constants.SERVER_URL + `/care/interestDelete`, { params:{
        // uid: this.$cookies.get('nickName'),
        uid : this.$store.state.profileData.nickName,
        desertionno: this.$route.params.desertionno
      }})
      .then((response) => {
        console.log(response);
        console.log("성공");
        // index.desertionno = null;
        this.isLikeDog = false
        // this.getInterest()
        // console.log(index.desertionno)
      })
      .catch((error) => {
        console.log("실패");
      });
    },
    getSurvey() {
      let formData = new FormData();
        formData.append('email', this.$cookies.get('auth-token').email)
        formData.append('desertionno', this.$route.params.desertionno)
        console.log(this.$cookies.get('auth-token').email)
        console.log(this.$route.params.desertionno)
        axios.post(constants.SERVER_URL + '/adoption/Application', formData)
          .then(response => {
            console.log(response.data)  
            this.user = response.data.user
            this.survey = response.data.survey
            if(response.data.user.sex){
              this.user.sex = '여자'
            }else{
              this.user.sex = '남자'
            }

            if(response.data.user.marriaged){
              this.user.marriaged = '기혼'
            }else{
              this.user.marriaged = '미혼'
            }

            var data = new Date();
            var year = data.getFullYear();
            var count = new Date(response.data.user.birthdate);
            var year2 = count.getFullYear();
            var result = year - year2 + 1
            this.userAge = result.toString()

            if(response.data.survey.nation === 'korea'){
              this.survey.nation = '한국'
            }else{
              this.survey.nation = '외국'
            }

            if(response.data.survey.place === 'home'){
              this.survey.place = '가정 집 안'
            }else if(response.data.survey.place === 'garden_o'){
              this.survey.place = '마당(펜스 O)'
            }else if(response.data.survey.place === 'garden_x'){
              this.survey.place = '마당(펜스 X)'
            }else{
              this.survey.place = '그 외 사업장'
            }

            if(response.data.survey.beforeover){
              this.survey.beforeover = '예'
            }else{
              this.survey.beforeover = '아니오'
            }

            if(response.data.survey.beforeadopt){
              this.survey.beforeadopt = '예'
            }else{
              this.survey.beforeadopt = '아니오'
            }

            if(response.data.survey.familyagree){
              this.survey.familyagree = '예'
            }else{
              this.survey.familyagree = '아니오'
            }

            if(response.data.survey.dissolution === 1){
              this.survey.dissolution = '1회'
            }else if(response.data.survey.dissolution === 2){
              this.survey.dissolution = '2회 이상'
            }else{
              this.survey.dissolution = '없음'
            }

            if(response.data.survey.sickness){
              this.survey.sickness = '예'
            }else{
              this.survey.sickness = '아니오'
            }

            if(response.data.survey.temp === 'family'){
              this.survey.temp = '가족'
            }else if(response.data.survey.temp === 'hotel'){
              this.survey.temp = '호텔'
            }else if(response.data.survey.temp === 'friend'){
              this.survey.temp = '지인'
            }else{
              this.survey.temp = '없음'
            }

            if(response.data.survey.house === 'room'){
              this.survey.house = '원룸'
            }else if(response.data.survey.house === 'housing'){
              this.survey.house = '주택'
            }else if(response.data.survey.house === 'apt'){
              this.survey.house = '아파트'
            }else{
              this.survey.house = '다세대/빌라'
            }
          })
          .catch(error => {
              console.log(error)
          })
    },
    goModal() {
      this.getSurvey()
    },
  }
}
</script>

<style>
</style>