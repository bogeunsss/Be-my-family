<template>
    <v-container fluid id="scollDetect">
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
                                <v-card @click="goDetail(j,i)" width="300px" height="400px" style="position: relative">
                                    <v-chip class="recommend-list" v-if="dog.recommend" color="red" text-color="white">추천</v-chip>
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
        <v-btn class="topBtn" @click="moveTop">
            <i class="fas fa-arrow-up"></i>
        </v-btn>
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
        if(this.$cookies.isKey('auth-token')){
            if(this.$cookies.get('auth-token').uid !== undefined){
                this.userInfo = this.$cookies.get('auth-token').uid
            }
        }
        var paramInfo = {
            pageno : this.pageno,
            userInfo : this.userInfo
        }
        console.log('페이지 ==> '+paramInfo.pageno)
        this.mainList(paramInfo)
        window.addEventListener('scroll', this.handleScroll)
        // console.log(this.dogData)
    },
    beforeDestroy(){
        window.removeEventListener('scroll', this.handleScroll)
    },
    methods: {
        ...mapActions(['mainList', 'setSearchDogs']),
        goDetail(j,index){
            // this.$router.push({name:constants.URL_TYPE.POST.DETAIL, params: {desertionno:this.dogData.desertionno}})
            // this.$cookies.set('desertionno', {desertionno:this.dogData[j][index].desertionno})
            this.$router.push({name:constants.URL_TYPE.POST.DETAIL, params:{desertionno: this.dogData[j][index].desertionno}})
        },
        search(){
            var paramInfo = {
                pageno: this.pageno,
                userInfo: this.userInfo
            }
            if(this.searchText === ""){
                    paramInfo.pageno = 0
                    this.mainList(paramInfo)
            }else{
                if(!this.isSearched){
                    paramInfo.pageno = 0
                }
                axios.get(constants.SERVER_URL + `/care/search?category=${this.category}&searchText=${this.searchText}&pageno=${paramInfo.pageno}`)
                .then((response) =>{
                    if(response.data.object.empty){
                        alert('검색 결과가 없습니다.')
                        paramInfo.pageno = 0
                        this.mainList(paramInfo)
                        this.searchText = ""
                    }else{
                        this.setSearchDogs(response.data)
                        this.isSearched = true
                    }
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
                    var paramInfo = {
                        pageno: this.pageno,
                        userInfo: this.userInfo
                    }
                    if(this.isSearched){
                        this.search()
                    }else{
                        this.mainList(paramInfo)
                    }
                }
            }
        },
        moveTop() {
            window.scrollTo(0, 0);
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
            contacts: [],
            pageno: 0,
            isSearched: false,
            scrollY: 0,
            timer: null,
            userInfo: null,
        }
    }
}
</script>

<style>
.topBtn {
  position: fixed;
  bottom: 50px;
  right: 30px;
}
.recommend-list {
    z-index: 1;
    position: absolute;
    top: 0;
    left: 0;
}
</style>