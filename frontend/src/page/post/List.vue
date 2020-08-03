<template>
    <v-container fluid>
        <h1 v-if="isSearched">검색 결과가 없습니다.</h1>

        <div class="container">

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

        <v-row>
            <v-col cols="12">
                <v-row>
                    <v-col col="6" md="3">
                        <v-card @click="goDetail">

                            <v-img
                                class="white--text align-end"
                                height="200px"
                                :src="dogData.popfile"
                            >
                            <v-card-title>{{ this.dogData.carenm }}</v-card-title>
                            </v-img>

                            <v-card-subtitle class="pb-0">{{ this.dogData.kindcd }}</v-card-subtitle>

                            <v-card-text class="text--primary">

                            <div>{{ this.dogData.careaddr }}</div>
                            <br>
                            <span class="date">2020-06-19ㆍ</span>  
                            <span class="comment">댓글 0개</span>
                            <br>
                            <a>userID</a><span>ㆍ ♥ 2</span>
                            </v-card-text>

                            <v-card-actions>
                            <v-btn
                                color="orange"
                                text >
                            Share
                            </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
        </div>

    </v-container>

</template>
 
<script>
import constants from '../../lib/constants'
import axios from 'axios'

import { mapState, mapActions } from 'vuex'

export default {
    name:"Post",
    components:{
    },
    watch: {
    },
    computed:{
        ...mapState(['dogData'])
    },
    created() {
        this.mainList()
        console.log("여기=>")
        console.log(this.dogData)
    },
    methods: {
        ...mapActions(['mainList']),
        goDetail(){
            this.$router.push({name:constants.URL_TYPE.POST.DETAIL, params: {desertionno:this.dogData.desertionno}})
        },
        search(){
            console.log(this.category)
            console.log(this.searchText)
            if(this.searchText === ""){
                    alert("검색어를 입력하세요.")
            }else{
                axios.get(`http://localhost:8080/care/search?category=${this.category}&searchText=${this.searchText}`)
                .then((response) =>{
                    this.contacts = response.data.object
                    if(!this.contacts[0]){this.isSearched=true}
                    else{this.isSearched=false}
                    this.searchText = ""
                    console.log(response)

                })
                .catch(() =>{
                    alert("올바른 값을 입력하세요")
                })
            }

        },
    },
    data: () => {
        return {   
            items: [
                { state: '품종', abbr: 'kindCd' },
                { state: '보호구역', abbr: 'careAddr' },
            ],
            category: {},
            searchText: '',
            contacts: {},
            isSearched: false,
        }
    }
}
</script>