<template>
  <div>
    <v-container >
      <form>
        <v-row class="d-flex align-center mx-auto">
          <v-col></v-col>
          <v-col class="d-flex">
            <v-text-field
              label="검색어를 입력하세요"
              v-model="searchText"
              class="mx-2"
            ></v-text-field>
            <v-btn large @click="search" type="submit">
              검색
            </v-btn>
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
    <v-btn class="float-btn" color="success" fab x-large dark>
      <v-icon>mdi-pencil</v-icon>
    </v-btn>
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
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 3 },
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
.float-btn {
  position: fixed;
  bottom: 5vh;
  right: 5vw;
}
</style>