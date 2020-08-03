<template>
    <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>회원가입</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <input v-model="signupData.nickName"
                            id="nickname"
                            placeholder="닉네임을 입력해주세요" type="text"/>
                    </div>

                    <div class="input-wrap">
                        <input v-model="signupData.email" 
                            id="email"
                            placeholder="이메일을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="signupData.password"
                            id="password" 
                            :type="signupData.passwordType"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : signupData.passwordType==='text'}"></span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="signupData.passwordConfirm" 
                            id="password-confirm"
                            :type="signupData.passwordConfirmType"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : signupData.passwordConfirmType==='text'}"></span>
                    </div>
                </div>

                <label>
                    <input type="checkbox" id="term"/>
                    <span>약관에 동의합니다</span>
                </label>

                <span class="go-term">약관 보기</span>
                <button class="btn" @click="join">
                    작성완료
                </button>
            </div>
        </div> 
    </div>
</template>

<script>
    import axios from 'axios'

    import constants from '../../lib/constants' 

    import { mapState, mapActions } from 'vuex'

    export default {
        components: {
        },
        created() {
        },
        computed: {
            ...mapState(['signupData']),
            nickName: {
                get(){return this.$store.state.signupData.nickName},
                set(value){this.$store.commit('setNickName', value)}
            },
            email: {
                get(){return this.$store.state.signupData.email},
                set(value){this.$store.commit('setEmail', value)}
            },
            password: {
                get(){return this.$store.state.signupData.password},
                set(value){this.$store.commit('setPassword', value)}
            },
            passwordConfirm: {
                get(){return this.$store.state.signupData.passwordConfirm},
                set(value){this.$store.commit('setPasswordConfirm', value)}
            },
        },
        methods: {
            ...mapActions(['signup']),
            join(){
                this.signup(this.$store.state.signupData)
            },
        },
        watch: {
        },
        data: () => {
            return {
                constants,  
            }
        }
    }

</script>


