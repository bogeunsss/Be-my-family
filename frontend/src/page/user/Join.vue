<template>
  <div>
    <v-container style="width:50%">
      <h1>회원가입</h1>
      <div>
        <form>
          1.Email:
          <v-text-field v-model="signupData.email" id="email" placeholder="이메일을 입력해주세요" type="text"></v-text-field>2.NickName:
          <v-text-field
            v-model="signupData.nickName"
            id="nickname"
            placeholder="닉네임을 입력해주세요"
            type="text"
          ></v-text-field>3.Password:
          <v-text-field
            v-model="signupData.password"
            id="password"
            :type="signupData.passwordType"
            placeholder="비밀번호를 입력해주세요"
          >
            <span :class="{active : signupData.passwordType==='text'}"></span>
          </v-text-field>4.Password Confirm:
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
            v-model="signupData.passwordConfirm"
            id="password-confirm"
            :type="signupData.passwordConfirmType"
            placeholder="휴대번호를 입력해주세요"
          ></v-text-field>

          <!-- 직업 -->
          6.Job:
          <v-text-field
            v-model="signupData.passwordConfirm"
            id="password-confirm"
            :type="signupData.passwordConfirmType"
            placeholder="직업을 적어주세요"
          ></v-text-field>

          <!-- 결혼여부 -->
          7.결혼 여부를 선택해 주세요.
          <v-container fluid>
            <!-- <p>{{ radios || 'null' }}</p> -->
            <v-radio-group v-model="radios" :mandatory="false">
                <div class="d-flex inline">
              <v-radio label="미혼" value="미혼"></v-radio>
              <v-radio label="기혼" value="기혼"></v-radio>
                </div>
            </v-radio-group>
          </v-container>

          <!-- 성별 -->
          8.성별을 선택해 주세요.
          <v-container fluid>
            <!-- <p>{{ radios || 'null' }}</p> -->
            <v-radio-group v-model="radios" :mandatory="false">
                <div class="d-flex inline">
              <v-radio label="남" value="남"></v-radio>
              <v-radio label="여" value="여"></v-radio>
                </div>
            </v-radio-group>
          </v-container>

          <!-- 생년월일 -->
          9.생년월일을 선택해주세요.
          <!-- <v-row justify="space-around">
            <v-date-picker v-model="picker" color="green lighten-1" header-color="primary"></v-date-picker>
          </v-row> -->
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
            v-model="date"
            label="Picker in birthdate"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>
    <v-spacer></v-spacer>
           </v-row>
        </form>
      <label>
        <input type="checkbox" id="term" />
        <span>약관에 동의합니다</span>
      </label>
      <span class="go-term ml-5 pl-5">약관 보기</span>
      </div>

      <v-btn class="btn mt-5" @click="join">작성완료</v-btn>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";

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
  },
  methods: {
    ...mapActions(["signup"]),
    join() {
      this.signup(this.$store.state.signupData);
    },
  },
  watch: {},
  data: () => {
    return {
      constants,
      radios: "radio-1",
      picker: new Date().toISOString().substr(0, 10),
      date: new Date().toISOString().substr(0, 10),
      menu: false,
    };
  },
};
</script>


