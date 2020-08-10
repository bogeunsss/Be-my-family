<template>
  <div class="container">
      <h1 class="mb-3 pb-3">Manager</h1>
      <p>관리자 : {{ $cookies.get('auth-token').mid }}</p>
      <h2>입양신청 목록</h2>
        <v-row>
          <v-col
            v-for="i in 5"
            :key="i"
            cols="12"
            sm="6"
            md="4"
            lg="3"
          >
            <v-card>
              <v-card-title class="subheading font-weight-bold">유기견입니다</v-card-title>

              <v-divider></v-divider>

              <v-list dense>
                <v-list-item>
                  <v-list-item-content>Calories:</v-list-item-content>
                  <v-list-item-content class="align-end">강아지</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Fat:</v-list-item-content>
                  <v-list-item-content class="align-end">강아지</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Carbs:</v-list-item-content>
                  <v-list-item-content class="align-end">강아지</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Protein:</v-list-item-content>
                  <v-list-item-content class="align-end">강아지</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Sodium:</v-list-item-content>
                  <v-list-item-content class="align-end">강아지</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Calcium:</v-list-item-content>
                  <v-list-item-content class="align-end">강아지</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Iron:</v-list-item-content>
                  <v-list-item-content class="align-end">강아지</v-list-item-content>
                </v-list-item>
              </v-list>
            </v-card>
          </v-col>
        </v-row>

  
  </div>
</template>

<script>
import constants from "@/lib/constants";
import axios from "axios";

export default {
    name: 'Manager',
    data() {
        return {
          
       
        
        }
    },
}
</script>

<style>

</style>