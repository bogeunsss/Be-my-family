<template>
<div class="container">
  <v-container fluid class="mx-5 mt-5 pt-5">
    <h1>내 관심 강아지</h1>
    <v-row>
      <v-col v-for="n in 3" :key="n" class="my-5 pt-5">
        <v-card class="d-inline-block mx-auto">
          <v-container>
            <v-row justify="space-between">
              <v-col cols="auto">
              <v-hover v-slot:default="{ hover }">
                <v-img height="300" width="300" src="https://picsum.photos/200/300/" >
                  <!-- <v-expand-transition> -->
                    <div
                      v-if="hover"
                      class="d-flex transition-fast-in-fast-out brown lighten-5 v-card--reveal display-3 white--text"
                      style="height: 100%;"
                    >갱얼쥐 dog</div>
                  <!-- </v-expand-transition> -->
                </v-img>
                </v-hover>
                <v-card-title>Top 10 Australian beaches</v-card-title>
              </v-col>

              <v-col cols="auto" class="text-center pl-0">
                <v-row class="flex-column ma-0 fill-height" justify="center">
                  <v-col class="px-0">
                    <v-btn icon>
                      <v-icon>mdi-heart</v-icon>
                    </v-btn>
                  </v-col>

                  <v-col class="px-0">
                    <v-btn icon>
                      <v-icon>mdi-bookmark</v-icon>
                    </v-btn>
                  </v-col>

                  <v-col class="px-0">
                    <v-btn icon>
                      <v-icon>mdi-share-variant</v-icon>
                    </v-btn>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </v-col>
    </v-row>
  </v-container>

  </div>
</template>


<script>
import constants from "@/lib/constants";
import SERVER from "@/lib/constants";
import axios from "axios";

export default {
  name: "like",
  methods: {},
};
</script>

<style scoped>
</style>