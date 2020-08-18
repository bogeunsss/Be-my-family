<template>
  <div>
    <v-container>
      <v-row>
        <v-col v-for="(n,i) in 200" :key="n" md="3" sm="4">
          <v-card v-if="i<`${scrolled}`">
            <v-img
              class="white--text align-end"
              height="200px"
              src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
            >
              <v-card-title>제목</v-card-title>
            </v-img>

            <v-card-subtitle class="pb-0">subtitle</v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Content : Whitsunday Island, Whitsunday Islands</div>
              <br />
              <span class="date">2020-06-19ㆍ</span>
              <span class="comment">댓글 0개</span>
              <br />
              <a>userID</a>
              <span>ㆍ ♥ 2</span>
            </v-card-text>

            <v-card-actions>
              <v-btn color="orange" text>Share</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <v-btn class="topBtn" @click="moveTop">
      <i class="fas fa-arrow-up"></i>
    </v-btn>
  </div>
</template>
 
<script>
import InfiniteLoading from "vue-infinite-loading";

export default {
  name: "Main",
  components: {
    InfiniteLoading,
  },
  watch: {},
  created() {
    window.addEventListener("scroll", this.handleScroll);
  },
  beforeDestroy: function () {
    window.removeEventListener("scroll", this.handleScroll);
  },
  methods: {
    // 나중에 window 크기 맞춰서 offset 조정해줘야함
    handleScroll() {
      var d = document.documentElement;
      var offset = d.scrollTop + window.innerHeight + 16;
      var height = d.offsetHeight;
      //   console.log(d)
      //   console.log(offset)
      //   console.log( height )

      if (offset >= height) {
        this.scrolled += 8;
      }
      //   console.log(this.scrolled)
    },
    moveTop() {
      window.scrollTo(0, 0);
    },
  },
  data: () => {
    return {
      scrolled: 12,
    };
  },
};
</script>

<style>
#container {
  margin: 40px;
}
.topBtn {
  position: fixed;
  bottom: 50px;
  right: 30px;
}
</style>