<template>
    <div>
    <h1>회원정보 수정</h1>
    <br><br>
    <form>
        Email: 
    <v-text-field v-model="profileData.email">
    </v-text-field>

        Password:
    <v-text-field
        v-model="password"
        :type="passwordType"
        placeholder="비밀번호를 입력해주세요">
        <span :class="{active : passwordType==='text'}">
                                <i class="fas fa-eye"></i>
                            </span>
    </v-text-field>
        Password Confirm:
      <v-text-field
        v-model="passwordConfirm" 
        :type="passwordConfirmType"
        placeholder="비밀번호를 한번 더 입력해주세요">
        <span :class="{active : passwordConfirmType==='text'}">
                                <i class="fas fa-eye"></i> 
                            </span>
    </v-text-field>


     <v-btn class="btn" @click="userDataUpdate">수정완료</v-btn>
  </form>
</div>

</template>

<script>
    import { mapState, mapActions } from 'vuex'

    import axios from 'axios'

    export default {
        components: {
        },
        created(){
            this.find(this.$cookies.get('auth-token').email)
        },
        computed: {
            ...mapState(['profileData'])
        },
        methods: {
            ...mapActions(['find' ,'userUpdate']),
            userDataUpdate(){
                this.profileData.password = this.password
                this.userUpdate(this.profileData)
            },
        },
        watch: {
        },
        data: () => {
            return {

                email: '',
                nickName: '',
                password: '',
                passwordConfirm: '',
                isTerm: false,
                passwordType:"password",
                passwordConfirmType:"password",
                
            }
        }

    }

</script>


