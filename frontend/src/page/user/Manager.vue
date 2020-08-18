<template>
  <div class="container">
      <h1 class="mb-3 pb-3">Manager</h1>
      <h2>입양신청 목록</h2>
        <v-row>
          <v-col
            v-for="adoption in adoptions"
            :key="adoption.id"
            cols="12"
            sm="6"
            md="4"
            
          >
            <v-card style="width:350px" @click="goAdoptionDetail(adoption.desertionno)">
              <div class="d-flex inline">
              <v-card-title class="subheading font-weight-bold">i dont know what to do</v-card-title>

              <v-chip label class='d-flex flex-column' color="pink" text-color="white" large>
                승인<br>{{ approveState[adoption.state] }}
              </v-chip>
              </div>
              <v-divider></v-divider>

              <v-list dense>
                <v-list-item>
                  <v-list-item-content>Name :</v-list-item-content>
                  <v-list-item-content class="align-end">{{ adoption.name }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Email :</v-list-item-content>
                  <v-list-item-content class="align-end">{{ adoption.email }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Phone :</v-list-item-content>
                  <v-list-item-content class="align-end">{{ adoption.phone }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Nation :</v-list-item-content>
                  <v-list-item-content class="align-end">{{ adoption.nation }}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Reservation :</v-list-item-content>
                  <v-list-item-content class="align-end">{{ adoption.fixdate }}.{{ adoption.fixtime }} </v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>DesertionNo:</v-list-item-content>
                  <v-list-item-content class="align-end">{{ adoption.desertionno }}</v-list-item-content>
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
    created(){
      this.getAdoptionList()
    },
    methods: {
      getAdoptionList() {
        axios.get(constants.SERVER_URL + '/manager/adoptionList', {params :{ 
          mid : this.$cookies.get('auth-token').mid
        }})
        .then((response)=>{
          this.adoptions = response.data.adoptions
        }).catch((err) => console.log(err))
      },
      goAdoptionDetail(desertionNo){
        this.$router.push({name:constants.URL_TYPE.USER.ADOPTIONDETAIL, params:{desertionno: desertionNo}})
      }
    },
    data() {
        return {
          adoptions : [],
          approveState: ['대기', '완료', '거절']
        }
    },
}
</script>

<style>

</style>