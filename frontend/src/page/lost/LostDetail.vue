<template>
  <div>
      <v-container class="pa-0">
          <h1>상세페이지</h1>
          <v-card>
            <v-card-title> ㅎㅇㅎㅇ </v-card-title>
            <v-card-subtitle>
                <v-img
                src="http://www.animal.go.kr/files/shelter/2014/02/201403010903285_s.jpg"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="500px"
                >
                </v-img>
            </v-card-subtitle>
            <v-card-text>
                ㅎㅇㅎㅇ
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              
            </v-card-actions>
          </v-card>
      </v-container>
  </div>
</template>

<script>
export default {
    computed:{
        param(){
            return this.$route.params.articleNo
        },
    },
    data(){
        return {
        }
    }
}
</script>

<style>

</style>