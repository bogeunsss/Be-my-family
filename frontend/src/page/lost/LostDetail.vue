<template>
  <div>
      <v-container class="pa-0">
          <h1>상세페이지</h1>
          <v-card>
            <v-toolbar flat color="pink" dark>
                <v-toolbar-title>ㅎㅇㅎㅇ</v-toolbar-title>
            </v-toolbar>
            <v-card-subtitle class="mx-auto" style="width: 60%">
                <v-img
                src="http://www.animal.go.kr/files/shelter/2014/02/201403010903285_s.jpg"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                max-height="100%"
                height="auto"
                >
                </v-img>
            </v-card-subtitle>
            <v-card-text>
                <v-list v-for="(subTitle, i) in subTitles" :key="i" dense>
                    <v-list-item>
                        <p class="font-weight-black mr-3">{{subTitle}}: </p>
                        <p> {{subContents[subTitle]}} </p>
                    </v-list-item>
                </v-list>
            
                <v-divider></v-divider>
                <p>요놈 진짜 못생겼어요! ㅎㅎㅎ</p>
            </v-card-text>
            <v-card-actions class="d-flex justify-center">
              <v-spacer></v-spacer>
              <v-btn>수정</v-btn>
              <v-btn>삭제</v-btn>
              <v-btn>목록</v-btn>
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
            subTitles:['견종', '날짜', '장소', '특징'],
            subContents:{
                '견종': '보스턴 테리어',
                '날짜': '2020-08-06',
                '장소': '대전광역시 유성구',
                '특징': '못생김',
            }
        }
    }
}
</script>

<style>

</style>