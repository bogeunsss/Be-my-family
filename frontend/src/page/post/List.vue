<template>
    <v-container fluid id="scollDetect">
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
                    <v-col cols="12" v-for="(dogs, j) in dogData" :key="'dog'+j">
                        <v-row>
                            <v-col col="6" md="3" v-for="(dog, i) in dogs" :key="i">
                                <v-card @click="goDetail(j,i)">

                                    <v-img
                                        class="white--text align-end"
                                        height="200px"
                                        :src="dog.popfile"
                                    >
                                    <v-card-title>{{ dog.carenm }}</v-card-title>
                                    </v-img>

                                    <v-card-subtitle class="pb-0">{{ dog.kindcd }}</v-card-subtitle>

                                    <v-card-text class="text--primary">

                                    <div>{{ dog.careaddr }}</div>
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
        ...mapState(['dogData', 'isLast'])
    },
    created() {
        this.mainList(this.pageno)
        window.addEventListener('scroll', this.handleScroll)
        console.log(this.dogData)
    },
    beforeDestroy(){
        window.removeEventListener('scroll', this.handleScroll)
    },
    methods: {
        ...mapActions(['mainList', 'setSearchDogs']),
        goDetail(j,index){
            // this.$router.push({name:constants.URL_TYPE.POST.DETAIL, params: {desertionno:this.dogData.desertionno}})
            this.$cookies.set('desertionno', {desertionno:this.dogData[j][index].desertionno})
            this.$router.push({name:constants.URL_TYPE.POST.DETAIL})
        },
        search(){
            console.log(this.category)
            console.log(this.searchText)
            if(this.searchText === ""){
                    this.mainList()
            }else{
                axios.get(constants.SERVER_URL + `/care/search?category=${this.category}&searchText=${this.searchText}`)
                .then((response) =>{
                    this.setSearchDogs(response.data.object)
                    if(!this.dogData){this.isSearched=true}
                    else{this.isSearched=false}
                    this.searchText = ""
                    console.log(response)

                })
                .catch(() =>{
                    alert("올바른 값을 입력하세요")
                })
            }

        },
        handleScroll(){
            if(window.scrollY + document.documentElement.clientHeight >= document.documentElement.scrollHeight - 1){
                if(!this.isLast){
                    this.pageno += 1
                    this.mainList(this.pageno)
                }
            }
        }
    },
    data: () => {
        return {   
            items: [
                { state: '품종', abbr: 'kindCd' },
                { state: '보호구역', abbr: 'careAddr' },
            ],
            category: {},
            searchText: '',
            contacts: [],
            isSearched: false,
            pageno: 0,
            scrollY: 0,
            timer: null,
        }
    }
}
</script>
