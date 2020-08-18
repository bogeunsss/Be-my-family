<template>
  <div>
    <v-container style="width:80%">
      <h1 class="mb-6">회원정보 수정</h1>
      <form>
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
        <v-text-field v-model="profileData.phone"></v-text-field>

        <p>직업:</p>
        <v-text-field v-model="profileData.job"></v-text-field>

        <p>결혼 유무:</p>
        <v-radio-group v-model="profileData.marriaged">
          <v-radio label="기혼" value="기혼"></v-radio>
          <v-radio label="미혼" value="미혼"></v-radio>
        </v-radio-group>

        <v-btn v-if="profileData.email" class="btn" @click="userDataUpdate">수정완료</v-btn>
        <v-btn v-if="managerInfo.email" class="btn" @click="managerDateUpdate">수정완료</v-btn>
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
    var token = this.$cookies.get("auth-token");
    if (this.$cookies.get("auth-token").uid) {
      this.find(token.email);
    }else {
      this.getManagerFind(token.email);
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
            email: this.$cookies.get("auth-token").email,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.managerInfo = res.data;
          console.log(this.managerInfo);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    managerDateUpdate() {
        axios.put(constants.SERVER_URL + '/manager/modify', {
            email : this.managerInfo.email,
            mid : this.managerInfo.mid,
            password : this.password
        }).then((res)=>{
        console.log(res)
        this.$router.push({name:constants.URL_TYPE.MAIN})
        }).catch((err)=>{
            console.log(err)
        })
    }

  },
  watch: {},
  data: () => {
    return {
      constants,
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


