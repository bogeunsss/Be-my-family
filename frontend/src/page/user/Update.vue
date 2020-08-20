<template>
  <div style="margin-top:4rem;">
    <v-container style="width:80%">
      <h1 class="mb-6">회원정보 수정</h1>
      <form>
        <p v-if="!isUser">이메일:</p>
        <v-text-field v-if="!isUser" v-model="managerInfo.email" placeholder="이메일을 입력해주세요"></v-text-field>

        <p>비밀번호:</p>
        <v-text-field v-model="password" :type="passwordType" placeholder="비밀번호를 입력해주세요">
          <span :class="{active : passwordType==='text'}">
            <i class="fas fa-eye"></i>
          </span>
        </v-text-field>
       
        
        <p>비밀번호 확인:</p>
        <v-text-field
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          placeholder="비밀번호를 한번 더 입력해주세요"
        >
          <span :class="{active : passwordConfirmType==='text'}">
            <i class="fas fa-eye"></i>
          </span>
        </v-text-field>

        <p>전화번호:</p>
        <v-text-field v-if="isUser" v-model="profileData.phone"></v-text-field>
        <v-text-field v-if="!isUser" v-model="managerInfo.phone" placeholder="전화번호를 입력해주세요"></v-text-field>

        <p v-if="isUser">직업:</p>
        <v-text-field v-if="isUser" v-model="profileData.job"></v-text-field>

        <p v-if="isUser">결혼 유무:</p>
        <v-radio-group v-if="isUser" v-model="profileData.marriaged">
          <v-radio label="기혼" value="기혼"></v-radio>
          <v-radio label="미혼" value="미혼"></v-radio>
        </v-radio-group>

        <v-btn v-if="isUser" class="btn" @click="userDataUpdate">수정완료</v-btn>
        <v-btn v-if="!isUser" class="btn" @click="managerDateUpdate">수정완료</v-btn>
      </form>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import constants from "../../lib/constants";
import axios from "axios";

export default {
  components: {},
  created() {
    if(this.$cookies.isKey('auth-token')){
      var token = this.$cookies.get("auth-token");
      if (this.$cookies.get("auth-token").uid !== undefined) {
        this.find(token.email);
        this.isUser = true
      }else {
        this.getManagerFind(token.email);
      }
    }
  },
  computed: {
    ...mapState(["profileData"]),
  },
  methods: {
    ...mapActions(["find", "userUpdate"]),
    userDataUpdate() {
      var passwordReg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      if(this.password === this.passwordConfirm){
        if(this.password !== ''){
          if(!passwordReg.test(this.password)) {
            alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
          }else if(/(\w)\1\1\1/.test(this.password)){
            alert('같은 문자를 4번 이상 사용하실 수 없습니다.');
          }else if(this.password.search(this.profileData.email) > -1){
            alert("비밀번호에 아이디가 포함되었습니다.");
          }else{
            this.profileData.password = this.password;
            this.userUpdate(this.profileData);
          }
        }else{
          this.userUpdate(this.profileData);
        }
      }else{
        alert('비밀번호가 일치하지 않습니다.')
      }
        
    },

    getManagerFind() {
      axios
        .get(constants.SERVER_URL + "/manager/find", {
          params: {
            mid: this.$cookies.get("auth-token").mid,
          },
        })
        .then((res) => {
          this.managerInfo = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    managerDateUpdate() {
      if(this.password === this.passwordConfirm){
        if(this.password !== ''){
          if(!passwordReg.test(this.password)) {
            alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
          }else if(/(\w)\1\1\1/.test(this.password)){
            alert('같은 문자를 4번 이상 사용하실 수 없습니다.');
          }else if(this.password.search(this.managerInfo.email) > -1){
            alert("비밀번호에 아이디가 포함되었습니다.");
          }else{
            this.managerInfo.password = this.password;
            axios.put(constants.SERVER_URL + '/manager/modify', {
              email : this.managerInfo.email,
              mid : this.managerInfo.mid,
              password : this.managerInfo.password,
              phone: this.managerInfo.phone
            }).then((res)=>{
              alert('수정 성공!')
            this.$router.push({name:constants.URL_TYPE.MAIN})
            }).catch((err)=>{
              console.log(err)
            })
          }
        }else{
          axios.put(constants.SERVER_URL + '/manager/modify', {
            email : this.managerInfo.email,
            mid : this.managerInfo.mid,
            password : this.managerInfo.password,
            phone: this.managerInfo.phone
          }).then((res)=>{
            alert('수정 성공!')
          this.$router.push({name:constants.URL_TYPE.MAIN})
          }).catch((err)=>{
            console.log(err)
          })
        }
      }else{
        alert('비밀번호가 일치하지 않습니다.')
      }
    }

  },
  watch: {},
  data: () => {
    return {
      constants,
      isUser: false,
      email: "",
      nickName: "",
      password: "",
      passwordConfirm: "",
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password",
      managerInfo: [],
      phone: "",
      job: "",
      marriaged: "",
    };
  },
};
</script>


