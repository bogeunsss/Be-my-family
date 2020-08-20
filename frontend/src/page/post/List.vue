<template>
<div style="margin-top:4rem;"> 
    <v-container fluid id="scollDetect" style="padding:0;" >
        <v-row>
        <v-col cols="12" style="padding:0;">
        <div class="back-img">
       

        <div class="container">
        <form >
            <div class="search">
            <v-row>   
            <v-col cols="12">
            <v-row class="d-flex align-center">      
                <v-col cols="12" sm="2"  md="2">
                    <v-select
                        v-model="category"
                        :items="items"
                        label="카테고리"
                        item-text="state"
                        item-value="abbr"
                        style="border-radius: 12px;"
                        solo
                    ></v-select>
                </v-col>
                <v-col cols="8"  md="4">
                    <v-text-field
                        label="검색어를 입력하세요"
                        v-model="searchText"
                        class="mb-5"
                    ></v-text-field>
                </v-col>
                <v-col class="mb-4 pa-0" cols="4" md="2">
                    <v-btn large style="background: #4ba5cd; color:white; border-radius: 12px;" @click="search" type="submit">
                        검색
                    </v-btn>
                    <div class="v-messages theme--light">
                        <div class="v-messages__wrapper"></div>
                    </div>
                </v-col>
            </v-row>
        </v-col>
        </v-row>
        </div>
        </form>
        </div>
       </div>
        </v-col>
        </v-row>

   

    <div class="bmg2">
    <v-container>
        <div class="container" >

        <v-row >
            <v-col cols="12">
                <v-row>
                    <v-col cols="12" v-for="(dogs, j) in dogData" :key="'dog'+j">


                        <v-row>
                            <v-col  col="6" md="3" v-for="(dog, i) in dogs" :key="i">

                                <div class="polaroid">
                                <v-card  @click="goDetail(j,i)" width="300px" height="400px" style="position: relative">
                                    <v-chip class="recommend-list" v-if="dog.recommend" color="red" text-color="white">추천</v-chip>
                                    <v-img
                                        class="white--text align-end visi"
                                        height="200px"
                                        :src="dog.popfile"
                                    >
                                    <v-card-title class="hide">{{ dog.carenm }}</v-card-title>
                                    </v-img>

                                    <v-card-subtitle class="pb-0">견종: {{ dog.kindcd }}</v-card-subtitle>

                                    <v-card-text class="text--primary" style="height:7rem;">

                                    <div>구조 장소: {{  dog.happenplace }}</div>
                                    <br>
                                    <div>특징: {{ elipsis(dog.specialmark) }}</div>
                                    </v-card-text>

                                    <v-card-actions class="d-flex justify-end" >
                                    <v-btn style="font-weight: bold;"
                                        color="orange"
                                        text>
                                        더 보기
                                    </v-btn>
                                    </v-card-actions>
                                </v-card>
                                </div>

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
    </div>
    </v-container>
</div>
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
            window.scrollTo(0, 0)
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
                    this.isSearched = false
            }else{
                if(this.lastSearchText !== this.searchText){
                    this.lastSearchText = this.searchText
                    this.isSearched = false
                }
                if(!this.isSearched){
                    paramInfo.pageno = 0
                }
                axios.get(constants.SERVER_URL + `/care/search?category=${this.category}&searchText=${this.searchText}&pageno=${paramInfo.pageno}`)
                .then((response) =>{
                    console.log(response)
                    if(!response.data.totalData){
                        alert('검색 결과가 없습니다.')
                        paramInfo.pageno = 0
                        this.searchText = ""
                        this.isSearched = false
                        this.mainList(paramInfo)
                    }else{
                        this.setSearchDogs({data: response.data, isSearched: this.isSearched})
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
        elipsis (temp) {
            var length = 25;
            if (temp.length > length) {
                temp = temp.substr (0, length-2) + '...';
                return temp
            }else{
                return temp
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
            lastSearchText: '',
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
<style scoped>
.back-img{
    background: url('../../assets/배경3.png') no-repeat;
    width: 100%;
    height: 35rem;
    background-size: cover;
    background-position: left top;

}
.search{
    position: relative;
    top: 200px;
}

div.polaroid{
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19) !important;
   
}

.visi .hide{
    visibility: hidden;
}

.visi :hover .hide{
    visibility: visible;
}


</style>