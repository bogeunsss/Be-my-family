<template>
    <v-container>
    <v-col cols="10" style="margin:0 auto;">
    <div class="d-flex mb-5">
    <i class="fas fa-dog" style="font-size:30px"></i><h2 class="ml-3">입양후기</h2>
    </div> 

    <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
        class="mb-5"
        style="width:400px;  margin-left:auto"
    ></v-text-field>

    <div class="d-flex">
    <!-- <v-btn @click="create" class="mt-5" style="margin-left:auto">후기작성</v-btn> -->
    <v-btn @click="create"  class="mt-5" style="margin-left:auto" outlined color="blue">
      <v-icon left>mdi-pencil</v-icon> 후기작성
    </v-btn>
    </div>
    

     <v-simple-table fixed-header height="300px" 
        class="mt-5"
        page.sync="page" 
        >
        <template v-slot:default>
        <thead>
            <tr>
                <th class="text-left">No.</th>
                <th class="text-left">Title</th>
                <th class="text-left">Writer</th>
                <th class="text-left">Data</th>
            </tr>
        </thead>
        <tbody>
            <!-- <tr v-for="item in desserts" :key="item.name">
            <td>{{ item.name }}</td>
            <td>{{ item.calories }}</td>
            </tr> -->
            <tr>
                <th scope="row">1</th>
                <td>입양 후기</td>
                <td>jin</td>
                <td>2020-08-6</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>우리 강아지 입양후기</td>
                <td>park</td>
                <td>2020-08-6</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>잘 키우고 있습니다!!</td>
                <td>jon</td>
                <td>2020-08-6</td>
            </tr>
            <tr>
                <th scope="row">4</th>
                <td>입양 하길 너무 잘한거 같아요 ㅠㅠㅠ</td>
                <td>maria</td>
                <td>2020-08-6</td>
            </tr>
        </tbody>
        </template>
    </v-simple-table>

    <v-pagination v-model="page" :length="pageCount"></v-pagination>

    </v-col>
    </v-container>
</template>

<script>
import constants from "@/lib/constants";

export default {
    data(){
        return{
            page: 1,
            pageCount: 1,
        }
    },
    methods:{
        create(){
            this.$router
            .push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTCREATE })
        }
    }

}
</script>

<style scoped>


</style>