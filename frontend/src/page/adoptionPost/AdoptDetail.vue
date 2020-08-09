<template>
  <v-form>
    <v-container class="pa-0">
      <h1>입양후기</h1>
      <v-card>
        <v-toolbar flat color="white">
          <v-list-item>
            <v-list-item-avatar color="grey"></v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title class="headline">입양후기</v-list-item-title>
              <v-list-item-subtitle>작성자(닉네임)</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-toolbar>
        <v-card-subtitle class="mx-auto" style="width: 60%">
          <v-img
            src="http://www.animal.go.kr/files/shelter/2014/02/201403010903285_s.jpg"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            max-height="100%"
            height="auto"
          ></v-img>
        </v-card-subtitle>
          
        <v-card-actions class="d-flex justify-end mb-3" >
          <v-btn icon large>
            <v-icon large>mdi-heart</v-icon>
          </v-btn>
          <v-btn icon large>
            <v-icon large>mdi-share-variant</v-icon>
          </v-btn>
        </v-card-actions>

          <v-divider></v-divider>

        <v-card-text>
          <v-list v-for="(subTitle, i) in subTitles" :key="i" dense>
            <v-list-item>
              <p class="font-weight-black mr-3">{{subTitle}}:</p>
              <p>{{subContents[subTitle]}}</p>
            </v-list-item>
          </v-list>
        </v-card-text>

        <v-card-actions class="d-flex justify-end">
          <v-btn text color="deep-purple accent-4">수정</v-btn>
          <v-btn text color="red accent-4">삭제</v-btn>
        </v-card-actions>
      </v-card>
       <v-card-actions class="d-flex justify-center">
      <v-btn>목록</v-btn>
       </v-card-actions>
    </v-container>
  </v-form>
</template>

<script>
export default {
  data() {
    return {
      subTitles: ["품종", "지역", "내용"],
      subContents: {
        지역: "대전광역시 유성구",
        품종: "보스턴 테리어",
        내용: "입양후기",
      },
    };
  },
};
</script>


<style>
</style>