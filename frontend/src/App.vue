<template>
  <v-app >
    <Header :isHeader="isHeader"/>
    <router-view />
  </v-app>
</template>

<script> 
import Header from '@/components/Header'
import constants from '@/lib/constants' 

export default {
  name: 'App',
  components: { 
    Header
  },
  created() {
      let url = this.$route.name;
  
      this.checkUrl(url);

      console.log('App.vue')
    let token = window.$cookies.get('FootballDiary')
    if (token) {
      this.$store.commit('setToken', token)
      console.log('App.vue.afterSetToken')
      this.$store.dispatch('getUserFromServer')
    } else {
      // this.onLogout()
  }
  },
  watch: {
      $route (to){
          this.checkUrl(to.name);
      }
  },
  methods : {
      checkUrl(url) { 

          let array = [
              constants.URL_TYPE.USER.LOGIN,
              // constants.URL_TYPE.USER.JOIN,
          ];

          let isHeader = true;
          array.map(path => {
              if (url === path)
                  isHeader = false;
          })
          this.isHeader = isHeader;

      },
  },
  data: function () {
        return {
            isHeader: true,
            constants
        } 
    }
}
</script>

<style>
.v-application--wrap{
  overflow: hidden!important;
}
</style>
