<template>
  <div>
    <v-container >
      <form>
        <v-row class="d-flex align-center mx-auto">
          <v-col cols="2" sm="2" md="2">
            <v-select
              v-model="category"
              :items="items"
              label="카테고리"
              item-text="state"
              item-value="abbr"
              solo
            ></v-select>
          </v-col>
          <v-col cols="8" sm="8" md="8">
            <v-text-field
              label="검색어를 입력하세요"
              v-model="searchText"
            ></v-text-field>
          </v-col>
          <v-col class="mb-4 pa-0" cols="2" sm="2" md="2">
            <v-btn large @click="search" type="submit">
              검색
            </v-btn>
            <div class="v-messages theme--light">
              <div class="v-messages__wrapper"></div>
            </div>
          </v-col>
        </v-row>
      </form>
      <v-row dense>
        <v-col
          v-for="(card, i) in cards"
          :key="i"
          :cols="card.flex"
        >
          <v-card @click="goDetail(i+1)">
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
            >
              <v-card-title v-text="card.title"></v-card-title>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import constants from '../../lib/constants'
export default {
  data(){
    return {
      cards: [
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
      ],
    }
  },
  methods: {
    goDetail(index){
      this.$router.push({name: constants.URL_TYPE.LOST.LOSTDETAIL, params: { articleNo: index }})
    }
  }
}
</script>

<style>

</style>