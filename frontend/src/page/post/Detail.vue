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
        <div v-if="isLoggedIn && !isManager">
        <v-btn color="success" depressed v-if="!isLikeDog" @click="likeDog">관심이써여~</v-btn>
        <v-btn color="success" depressed v-if="isLikeDog" @click="deleteLike">관심업서여</v-btn>
        <v-btn color="primary" class="ma-2" dark @click="dialog = true">입양신청</v-btn>
        </div>
        <!-- total 보내야 할 데이터 : email, 상담날짜, 상담시간, 강아지id, url: /account/adoptionList -->
        <v-dialog v-model="dialog" max-width="500px">
          <v-card>
            <v-card-title>입양신청서</v-card-title>
            <v-card-text>
              <!-- 개인 정보 template -->
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
              <v-btn color="primary" text @click="requestComplete">신청완료</v-btn>
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
import axios from 'axios'
import { mapState, mapActions } from 'vuex'

export default {
    name:'detail',
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
        }
    },
    computed:{
      ...mapState(['profileData'])
    },
    created(){
      this.getDetail()
      console.log( this.$route.params.uuid )
      console.log(this.isLoggedIn)
    },
    methods:{
      ...mapActions(['find']),
      getDetail(){
        if(this.$cookies.isKey("auth-token")){
          var token = this.$cookies.get('auth-token')
          this.email = token.email
          this.isLoggedIn = true
          if(this.$cookies.get('auth-token').mid == null){
              this.find(token.email)
              setTimeout(()=>{
                axios.get('http://localhost:8080/care/detailUser', {
                  params: {
                    desertionno: this.$cookies.get('desertionno').desertionno,
                    uid: this.profileData.nickName
                  }
                })
                .then( response => {
                    console.log(response)
                    this.dogData = response.data.object
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
              axios.get('http://localhost:8080/care/detailUser', {
                params: {
                  desertionno: this.$cookies.get('desertionno').desertionno,
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
          axios.get('http://localhost:8080/care/detailUser', {
                params: {
                  desertionno: this.$cookies.get('desertionno').desertionno,
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
        // console.log(this.date + " " + st + " " + this.dogData.desertionno + " " + this.email)

        axios.get(`http://localhost:8080/account/adoptionList?`)
          .then(response => {

          })
          .catch(error => {
              console.log(error)
          })
        this.dialog = false
      },
      likeDog() {
        let formData = new FormData();
        formData.append("uid", this.$store.state.profileData.nickName);
        formData.append("desertionno", this.dogData.desertionno);
        console.log(this.$store.state.profileData.nickName);
        axios
          .post("http://localhost:8080/care/interestAdd", formData)
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
        .delete(`http://localhost:8080/care/interestDelete`, { params:{
          // uid: this.$cookies.get('nickName'),
          uid : this.$store.state.profileData.nickName,
          desertionno: this.$cookies.get('desertionno').desertionno
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
    destroyed(){
      this.$cookies.remove('desertionno')
    },
    }
}
</script>

<style>
</style>