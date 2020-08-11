<template>
  <div>
    <v-container style="width:80%">
      <h1>회원정보 수정</h1>
      <br />
      <br />
      <form>
        <p>Email:</p>
        <v-text-field v-if="profileData.email" v-model="profileData.email"></v-text-field>
        <v-text-field v-if="managerInfo.email" v-model="managerInfo.email"></v-text-field>
        
        <p>Password:</p>
        <v-text-field v-model="password" :type="passwordType" placeholder="비밀번호를 입력해주세요">
          <span :class="{active : passwordType==='text'}">
            <i class="fas fa-eye"></i>
          </span>
        </v-text-field>
       
        
        <p>Password Confirm:</p>
        <v-text-field
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          placeholder="비밀번호를 한번 더 입력해주세요"
        >
          <span :class="{active : passwordConfirmType==='text'}">
            <i class="fas fa-eye"></i>
          </span>
        </v-text-field>

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
      this.getAdoption();
    } else {
      this.getManagerFind(token.email);
    }
  },
  computed: {
    ...mapState(["profileData"]),
  },
  methods: {
    ...mapActions(["find", "userUpdate"]),
    userDataUpdate() {
      this.profileData.password = this.password;
      this.userUpdate(this.profileData);
    },

    getManagerFind() {
      axios
        .get("http://localhost:8080/manager/find", {
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
        axios.put('http://localhost:8080/manager/modify', {
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
    };
  },
};
</script>


