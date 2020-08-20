<template>
  <div style="margin-top:4rem;">
    <v-stepper v-model="e1">
      <v-stepper-header style="background-color:white; position:fixed; width:100%; z-index:1">
        <v-stepper-step :complete="e1 > 1" step="1">정보 입력</v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="e1 > 2" step="2">인증번호 확인</v-stepper-step>
  
        <v-divider></v-divider>

        <v-stepper-step step="3">완료</v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card
            class="my-15" 
          >
            <v-container style="width:50%">
              <div>
                <form>
                  1.Email:
                  <v-text-field v-model="signupData.email" id="email" placeholder="이메일을 입력해주세요" type="text"></v-text-field>
                  2.Name:
                  <v-text-field v-model="signupData.name" id="name" placeholder="이름을 입력해주세요" type="text"></v-text-field>
                  
                  2.NickName:
                  <v-text-field
                    v-model="signupData.nickName"
                    id="nickname"
                    placeholder="닉네임을 입력해주세요"
                    type="text"
                  ></v-text-field>
                  3.Password:
                  <v-text-field
                    v-model="signupData.password"
                    id="password"
                    :type="signupData.passwordType"
                    placeholder="비밀번호를 입력해주세요"
                  >
                    <span :class="{active : signupData.passwordType==='text'}"></span>
                  </v-text-field>
                  4.Password Confirm:
                  <v-text-field
                    v-model="signupData.passwordConfirm"
                    id="password-confirm"
                    :type="signupData.passwordConfirmType"
                    placeholder="비밀번호를 한번 더 입력해주세요"
                  >
                    <span :class="{active : signupData.passwordConfirmType==='text'}"></span>
                  </v-text-field>

                  <!-- 수정 시작 -->
                  <!-- 핸드폰 번호 -->
                  5.Phone Number:
                  <v-text-field
                    v-model="signupData.phone"
                    id="phone"
                    type="text"
                    placeholder="휴대번호를 입력해주세요"
                  ></v-text-field>

                  <!-- 직업 -->
                  6.Job:
                  <v-text-field v-model="signupData.job" id="job" type="text" placeholder="직업을 적어주세요"></v-text-field>

                  <!-- 결혼여부 -->
                  7.결혼 여부를 선택해 주세요.
                  <v-container fluid>
                    <!-- <p>{{ radios || 'null' }}</p> -->
                    <v-radio-group v-model="signupData.marriaged" :mandatory="false" row>
                      <v-radio label="미혼" value="0"></v-radio>
                      <v-radio label="기혼" value="1"></v-radio>
                    </v-radio-group>
                  </v-container>

                  <!-- 성별 -->
                  8.성별을 선택해 주세요.
                  <v-container fluid>
                    <!-- <p>{{ radios || 'null' }}</p> -->
                    <v-radio-group v-model="signupData.sex" :mandatory="false" row>
                      <v-radio label="남" value="0"></v-radio>
                      <v-radio label="여" value="1"></v-radio>
                    </v-radio-group>
                  </v-container>

                  <!-- 생년월일 -->
                  9.생년월일을 선택해주세요.
                  <!-- <v-row justify="space-around">
                    <v-date-picker v-model="picker" color="green lighten-1" header-color="primary"></v-date-picker>
                  </v-row>-->
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-menu
                        ref="menu"
                        v-model="menu"
                        :close-on-content-click="false"
                        :return-value.sync="date"
                        transition="scale-transition"
                        offset-y
                        min-width="290px"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="signupData.birthdate"
                            label="Picker in birthdate"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker v-model="signupData.birthdate" no-title scrollable>
                          <v-spacer></v-spacer>
                          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                          <v-btn text color="primary" @click="$refs.menu.save(signupData.birthdate)">OK</v-btn>
                        </v-date-picker>
                      </v-menu>
                    </v-col>
                    <v-spacer></v-spacer>
                  </v-row>
                </form>
                <label>
                  <input type="checkbox" id="term" v-model="term"/>
                  <span>약관에 동의합니다</span>
                </label>
                <v-btn class="ml-5 mb-1" text @click="goTerm = true">약관 보기</v-btn>
              </div>
              <v-dialog v-model="goTerm" width="500px">
                <v-card height="600px">
                  <v-card-title class="font-weight-black">약 관</v-card-title>
                  <v-divider></v-divider>
                  <v-card-text class="mt-4">
                    <p>동물을 사랑하셔야 합니다.</p>
                  </v-card-text>
                  <v-card-actions style="position: absolute; bottom: 5px; right: 5px">
                    <v-btn @click="goTerm = false" text class="font-weight-black" color="primary">확 인</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-container>
          </v-card>

          <v-btn
            color="primary"
            @click="signup"
          >
            작성완료
          </v-btn>

          <v-progress-circular
            v-if="nowLoading"
            indeterminate
            color="primary"
          ></v-progress-circular>
          
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card
            class="mb-12 d-flex justify-center"
            color="grey lighten-1"
            height="500px"
          >
            <v-card class="mt-5" height="400px" width="340px">
              <v-card-title>이메일 인증</v-card-title>
              <v-divider></v-divider>
              <div>
                <v-card-subtitle>인증번호를 입력해 주세요</v-card-subtitle>
                <v-row class="justify-center">
                  <input id="num1" type="text" class="mx-1 input-background-color" maxlength="1" v-model="confirmNo1" autofocus>
                  <input id="num2" type="text" class="mx-1 input-background-color" maxlength="1" v-model="confirmNo2">
                  <input id="num3" type="text" class="mx-1 input-background-color" maxlength="1" v-model="confirmNo3">
                  <input id="num4" type="text" class="mx-1 input-background-color" maxlength="1" v-model="confirmNo4">
                  <input id="num5" type="text" class="mx-1 input-background-color" maxlength="1" v-model="confirmNo5">
                  <input id="num6" type="text" class="mx-1 input-background-color" maxlength="1" v-model="confirmNo6">
                </v-row>
              </div>
              <v-divider class="mt-4"></v-divider>
              <v-card-text>
                <p>입력하신 이메일로 인증 번호가 발송 되었습니다.</p>
              </v-card-text>
              <v-card-actions class="d-flex justify-center">
                <v-btn color="primary" @click="signup">이메일 재전송</v-btn>
                <v-progress-circular
                  v-if="nowLoading"
                  indeterminate
                  color="primary"
                ></v-progress-circular>
              </v-card-actions>
            </v-card>
          </v-card>

          <v-btn
            class="d-flex justify-center"
            color="primary"
            @click="confirmJoin"
          >
            확인
          </v-btn>

        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card class="mb-5" outlined height="400px">
            <h1 style="text-align: center;">가입이 완료 되었습니다.</h1>
            <h3 class="mt-3" style="text-align: center;">{{ signupData.nickName }}님 환영합니다.</h3>
            <v-btn
              color="primary"
              style="position: absolute; bottom: 10px; left: 50%; transform: translate(-50%, 0);"
              @click="goMain"
            >
              메인으로
            </v-btn>
          </v-card>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </div>
</template>

<script>
import axios from "axios";
import swal from 'sweetalert';


import constants from "../../lib/constants";

import { mapState, mapActions } from "vuex";

export default {
  components: {},
  created() {},
  computed: {
    ...mapState(["signupData"]),
    nickName: {
      get() {
        return this.$store.state.signupData.nickName;
      },
      set(value) {
        this.$store.commit("setNickName", value);
      },
    },
    name: {
      get() {
        return this.$store.state.signupData.name;
      },
      set(value) {
        this.$store.commit("setName", value);
      },
    },
    email: {
      get() {
        return this.$store.state.signupData.email;
      },
      set(value) {
        this.$store.commit("setEmail", value);
      },
    },
    password: {
      get() {
        return this.$store.state.signupData.password;
      },
      set(value) {
        this.$store.commit("setPassword", value);
      },
    },
    passwordConfirm: {
      get() {
        return this.$store.state.signupData.passwordConfirm;
      },
      set(value) {
        this.$store.commit("setPasswordConfirm", value);
      },
    },
    phone: {
      get() {
        return this.$store.state.signupData.phone;
      },
      set(value) {
        this.$store.commit("setPhone", value);
      },
    },
    job: {
      get() {
        return this.$store.state.signupData.job;
      },
      set(value) {
        this.$store.commit("setJob", value);
      },
    },
    marriaged: {
      get() {
        return this.$store.state.signupData.marriaged;
      },
      set(value) {
        this.$store.commit("setMarriaged", value);
      },
    },
    sex: {
      get() {
        return this.$store.state.signupData.sex;
      },
      set(value) {
        this.$store.commit("setSex", value);
      },
    },
    birthdate: {
      get() {
        return this.$store.state.signupData.birthdate;
      },
      set(value) {
        this.$store.commit("setBirthDate", value);
      },
    },
  },
  methods: {
    goMain(){
      this.$router.push({name: constants.URL_TYPE.MAIN})
    },
    signup(){
      if(!this.term){
        swal({
          title:'약관에 동의해주세요.',
          icon: "warning",
          button: "OK"
          })
      }else{

        this.nowLoading = true
        const info = {
          data: this.signupData,
          location: constants.URL_TYPE.USER.JOIN
        }
        var reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        var passwordReg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
        if(!info.data.nickName){
          swal('닉네임을 입력하세요')
          this.nowLoading = false
        }else if(!info.data.email){
          swal('이메일을 입력하세요')
          this.nowLoading = false
        }else if(!reg.test(info.data.email)){
          swal({
            title:'이메일 형식을 확인하세요',
            icon: "warning",
            buttons: "OK"
            })
          this.nowLoading = false
        }else if(!info.data.password){
          swal('비밀번호를 입력하세요')
          this.nowLoading = false
        }else if(!info.data.passwordConfirm){
          swal('비밀번호 확인을 입력하세요')
          this.nowLoading = false
        }else if(info.data.password !== info.data.passwordConfirm){
          swal({
            title:'비밀번호가 일치하지 않습니다',
            icon: "warning",
            button: "OK"
            })
          this.nowLoading = false
        }else if(false === passwordReg.test(info.data.password)) {
          swal({
            title: "비밀번호" ,
            text: "8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.", 
            icon: "warning",
            button: "OK"
          });
          this.nowLoading = false
        }else if(/(\w)\1\1\1/.test(info.data.password)){
          swal({
            title:'같은 문자를 4번 이상 사용하실 수 없습니다.',
            icon: "warning",
            button: "OK"
            });
          this.nowLoading = false
        }else if(info.data.password.search(info.data.email) > -1){
          swal({
            title:"비밀번호에 아이디가 포함되었습니다.",
            icon: "info",
            button: "OK"
          });
          this.nowLoading = false
        }else if(info.data.password.search(/\s/) != -1){
          swal({
            title:"비밀번호는 공백 없이 입력해주세요.",
            icon: "warning",
            button: "OK"
          });
          this.nowLoading = false
        }else{
          axios.post(constants.SERVER_URL + '/account/signup', {
            email: info.data.email,
            name: info.data.name,
            password: info.data.password,
            uid: info.data.nickName,
            phone: info.data.phone,
            job: info.data.job,
            marriaged: info.data.marriaged,
            sex: info.data.sex,
            birthdate: info.data.birthdate
          })
          .then(res=>{
            console.log(res)
            if(res.data.data === 'emailexist'){
                swal('이미 있는 이메일입니다.')
            }else if(res.data.data === 'nicknameexist'){
                swal('이미 있는 닉네임입니다.')
            }else if(res.data.data === 'emailfail'){
                swal({
                  title: '이메일 전송 실패',
                  icon: "warning",
                  button: "OK"
                  })
            }else{
              if(this.e1 === 1)
              this.confirmData = res.data.data
              this.e1 = 2
            }
            this.nowLoading = false
          })
          .catch(err=>console.log(err))
        }
      }
    },
    confirmJoin(){
      const info = {
        data: this.signupData,
        location: constants.URL_TYPE.USER.JOIN
      }
      var confirmNumber = this.confirmNo1 + this.confirmNo2 + this.confirmNo3 + this.confirmNo4 + this.confirmNo5 + this.confirmNo6
      if(confirmNumber === this.confirmData){
        axios.post(constants.SERVER_URL + '/account/emailkey', {
          email: info.data.email,
          name: info.data.name,
          password: info.data.password,
          uid: info.data.nickName,
          phone: info.data.phone,
          job: info.data.job,
          marriaged: info.data.marriaged,
          sex: info.data.sex,
          birthdate: info.data.birthdate
        }).then(response => {
          if(response.data.data === 'success'){
            this.e1 = 3
          }else{
            swal({
              title:'가입 실패',
              icon: "warning",
              button: "OK"
            })
          }
        }).catch(error => {
        })
      }else{
        swal({
          title:'인증 번호를 다시 확인해 주세요',
          icon: "info",
          button: "OK"
          })
      }
    },
  },
  watch: {
    confirmNo1(newVal, oldVal){
      if(newVal.length > 0){
        document.getElementById('num2').focus()
      }
    },
    confirmNo2(newVal, oldVal){
      if(newVal.length > 0){
        document.getElementById('num3').focus()
      }
    },
    confirmNo3(newVal, oldVal){
      if(newVal.length > 0){
        document.getElementById('num4').focus()
      }
    },
    confirmNo4(newVal, oldVal){
      if(newVal.length > 0){
        document.getElementById('num5').focus()
      }
    },
    confirmNo5(newVal, oldVal){
      if(newVal.length > 0){
        document.getElementById('num6').focus()
      }
    },
  },
  data: () => {
    return {
      constants,
      radios1: "radio-1",
      radios2: "radio-2",
      picker: new Date().toISOString().substr(0, 10),
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      e1: 1,
      nowLoading: false,
      confirmNo1: '',
      confirmNo2: '',
      confirmNo3: '',
      confirmNo4: '',
      confirmNo5: '',
      confirmNo6: '',
      confirmData: '',
      term: false,
      goTerm: false,
    };
  },
};
</script>

<style>
.input-background-color {
  border: 0;
  outline: 0;
  border-bottom: 2px solid black;
  height: 40px;
  width: 30px;
  font-size: 20pt;
  text-align: center;
}
</style>