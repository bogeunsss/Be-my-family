<template>
  <v-app >
    <Header :isHeader="isHeader"/>
    <router-view />
    <Footer :isFooter="isFooter"/>
  </v-app>
</template>

<script> 
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import constants from '@/lib/constants' 
import { mapActions } from 'vuex'

export default {
  name: 'App',
  components: { 
    Header,
    Footer
  },
  created() {
      let url = this.$route.name;
  
      this.checkUrl(url);

      console.log('App.vue')

  },
  watch: {
      $route (to){
          this.checkUrl(to.name);
      }
  },
  methods : {
    ...mapActions(['find']),
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

          let isFooter = true;
          array.map(path => {
              if (url === path)
                  isFooter = false;
          })
          this.isFooter = isFooter;

      },
  },
  data: function () {
        return {
            isHeader: true,
            isFooter: true,
            constants
        } 
    }
}
</script>

<style>

 @font-face {
    font-family: 'Recipekorea';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
            
            @font-face {
    font-family: 'ImcreSoojin';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.3/ImcreSoojin.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
            
@font-face {
    font-family: 'TmoneyRoundWindExtraBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
      
  @font-face {
    font-family: 'GmarketSansBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }
            
*{
  /* font-family: 'ImcreSoojin'; */
  /* font-family: 'Recipekorea'; */
  /* font-family: 'GmarketSansBold'; */
  font-family: 'TmoneyRoundWindExtraBold';
}
.v-application--wrap{
  overflow: hidden!important;
}
</style>
