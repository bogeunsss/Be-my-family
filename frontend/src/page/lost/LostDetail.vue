<template>
  <div style="margin-top:7rem;">
    <v-container class="p-4">
        <h2>제보를 기다립니다</h2>
      <v-row>
        <v-col class="detailcol1" cols="12" lg="6">
          <v-card>
            <v-row>
              <v-card-subtitle class="mx-auto" style="position:relative;width: 100%">
                <v-carousel hide-delimiters height="auto" class="white--text float-center">
                    <v-carousel-item
                    v-for="(getImage,i) in getImages"
                    :key="i"
                    :src="'http://i3b201.p.ssafy.io/file/'+getImage"
                    ></v-carousel-item>
                </v-carousel>
              <v-chip v-if="lostType=='실종'" class="ma-2 font-weight-bold" color="red" text-color="white" style="position:absolute;top:4%;left:4%;">{{ lostType }}</v-chip>
              <v-chip v-if="lostType=='보호'" class="ma-2 font-weight-bold" color="green" text-color="white" style="position:absolute;top:4%;left:4%;">{{ lostType }}</v-chip>
              <v-chip v-if="lostType=='목격'" class="ma-2 font-weight-bold" color="orange" text-color="white" style="position:absolute;top:4%;left:4%;">{{ lostType }}</v-chip>
              </v-card-subtitle>
            </v-row>
            <v-row>
              <v-card-text class="py-0">
                <v-row>
                  <v-col cols="6" v-for="(subTitle, i) in subTitles" :key="i" dense class="pl-5">
                    <div class="d-flex px-5">
                      <i v-if="i==0" style="font-size:25px;" class="fas fa-dog"></i>
                      <i v-if="i==1" style="font-size:25px;" class="fas fa-venus-mars"></i>
                      <i v-if="i==2" style="font-size:25px;" class="fas fa-calendar-week"></i>
                      <i v-if="i==3" style="font-size:25px;" class="fas fa-map-marked-alt"></i>
                      <p class="font-weight-black mr-3 pl-1 pt-1">{{subTitle}}</p>
                    </div>
                    <p class="px-5">{{subContents[subTitle]}}</p>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-row>
          </v-card>
        </v-col>
        <v-col class="detailcol2" cols="12" lg="6">
          <v-card style="min-height:570px;height:100%;position:relative;" class="ml-0 pa-9">
            <!-- <div style="height:100%;max-width:100%;"> -->
            <div class="d-flex">
              <p class="font-weight-bold">작성자 : {{writer}}</p>
              <i class="fas fa-arrow-circle-left ml-auto" @click="goList" style="font-size:30px;"></i>
              <!-- <v-btn @click="goList">목록</v-btn> -->
            </div>
            <div style="position:absolute;top:20%;" class="pr-9">
              <p class="font-weight-bold">내용</p>
              <p>{{ content }}</p>
            </div>
            <div
              style="position:absolute;top:67%;width:100%;height:100%;"
              v-if="madeTags.length>0"
              class="pr-9"
            >
              <div class="d-flex pr-9" style="width:100%;">
                <i class="mr-1 my-auto fas fa-tags font-weight-bold"></i>
                <p class="m-0 my-auto font-weight-bold">태그</p>
              </div>
              <div class="pr-9" style="width:100%;">
                <v-chip class="ma-1" v-for="(madeTag, i) in madeTags" :key="'tag'+i">{{ madeTag }}</v-chip>
              </div>
            </div>
            <div style="position:absolute;bottom:5%;right:5%;">
              <v-card-actions class="d-flex justify-center">
                <v-spacer></v-spacer>
                <!-- <v-btn v-if="isWriter">수정</v-btn> -->
                <div v-if="isWriter">
                  <v-dialog v-model="dialog" scrollable max-width="700px">
                    <template v-slot:activator="{ on, attrs }">
                      <!-- 수정버튼 -->
                      <i
                        v-bind="attrs"
                        v-on="on"
                        v-if="isWriter"
                        class="fas fa-edit"
                        style="font-size:30px;"
                      ></i>
                    </template>
                    <v-card>
                      <v-card-title>글 수정하기</v-card-title>
                      <v-divider></v-divider>
                      <v-card-text style="height: 500px;">
                        <v-list>
                          <v-list-item-subtitle>카테고리</v-list-item-subtitle>
                          <v-list-item class="mt-3 d-flex justify-space-between">
                            <v-radio-group
                              v-model="lostType"
                              v-for="category in categories"
                              :key="category"
                            >
                              <v-radio :label="category" :value="category" :selected="lostType"></v-radio>
                            </v-radio-group>
                          </v-list-item>
                          <v-list-item-subtitle>성별</v-list-item-subtitle>
                          <v-list-item class="mt-3 d-flex justify-space-between">
                            <v-radio-group v-model="lostSex" v-for="gd in gender" :key="gd">
                              <v-radio :label="gd" :value="gd" :selected="lostSex"></v-radio>
                            </v-radio-group>
                          </v-list-item>
                          <v-list-item-subtitle>품종</v-list-item-subtitle>
                          <v-list-item class="mt-3">
                            <v-select v-model="lostBreed" :items="kinds" label="선택해주세요" solo></v-select>
                          </v-list-item>
                          <v-row>
                            <v-col>
                              <v-list-item-subtitle>시/도</v-list-item-subtitle>
                              <v-list-item class="mt-3">
                                <v-select
                                  v-model="lostSido"
                                  :items="sido_states"
                                  label="선택해주세요"
                                  solo
                                ></v-select>
                              </v-list-item>
                            </v-col>
                            <v-col>
                              <div v-if="lostSido.length > 0">
                                <v-list-item-subtitle>구/군</v-list-item-subtitle>
                                <v-list-item class="mt-3">
                                  <v-select
                                    v-model="lostGugun"
                                    :items="gugun_states[lostSido]"
                                    label="선택해주세요"
                                    solo
                                  ></v-select>
                                </v-list-item>
                              </div>
                            </v-col>
                          </v-row>

                          <v-row>
                            <v-col>
                              <v-list-item-subtitle>상세주소</v-list-item-subtitle>
                              <v-list-item>
                                <v-text-field v-model="lostPlace"></v-text-field>
                              </v-list-item>
                            </v-col>
                            <v-col>
                              <v-list-item-subtitle>발생날짜</v-list-item-subtitle>
                              <v-list-item>
                                <v-text-field
                                  color="primary"
                                  @click="dialog2 = !dialog2"
                                  v-model="lostDate"
                                  label="발생 날짜를 선택해주세요"
                                  readonly
                                ></v-text-field>
                              </v-list-item>
                            </v-col>
                          </v-row>
                          <v-list-item>
                            <p class="font-weght-black mr-3">태그:</p>
                            <div class="d-flex flex-column">
                              <v-text-field v-model="lostTagText" @keyup.enter="makeApplyTag"></v-text-field>
                              <div class="d-flex flex-row">
                                <v-list v-for="(madeTag, index) in madeTags" :key="index">
                                  <v-chip
                                    v-if="madeTag"
                                    close
                                    color="teal"
                                    text-color="white"
                                    @click:close="closeTag(index)"
                                  >{{ madeTag }}</v-chip>
                                </v-list>
                              </div>
                            </div>
                          </v-list-item>
                          <v-list-item>
                            <v-textarea v-model="content" outlined label="내용">{{content}}</v-textarea>
                          </v-list-item>
                        </v-list>
                      </v-card-text>
                      <v-divider></v-divider>
                      <v-card-actions class="d-flex justify-space-between">
                        <input
                          @change="onChangeImages"
                          type="file"
                          id="inputFiles"
                          multiple="multiple"
                          accept="image/*"
                        />
                        <div>
                          <v-btn type="button" color="blue darken-1" text @click="submit">Save</v-btn>
                          <v-btn color="blue darken-1" text @click="closeDialog">Close</v-btn>
                        </div>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </div>
                <v-dialog
                  ref="dialog"
                  v-model="dialog2"
                  :return-value.sync="lostDate"
                  persistent
                  width="290px"
                >
                  <v-date-picker v-model="lostDate" scrollable>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" @click="dialog2 = false">취소</v-btn>
                    <v-btn color="primary" @click="$refs.dialog.save(lostDate)">확인</v-btn>
                  </v-date-picker>
                </v-dialog>
                <!-- 삭제버튼 -->
                <i
                  v-if="isWriter"
                  @click="deleteLost"
                  class="fas fa-trash-alt pl-2"
                  style="font-size:30px;"
                ></i>
                <!-- <v-btn v-if="isWriter" @click="deleteLost" color="red">삭제</v-btn> -->
              </v-card-actions>
            </div>
            <!-- </div> -->
          </v-card>
        </v-col>
      </v-row>
    </v-container>

    <v-container class="mb-10">
      <v-card>
        <div style="position: relative">
          <v-subheader class="font-weight-black">댓글</v-subheader>
          <v-textarea
            v-model="comment"
            :label="comment.length+'/100'"
            auto-grow
            outlined
            rows="3"
            row-height="30"
            shaped
          ></v-textarea>
          <v-btn color="indigo" outlined class="write-btn" @click="commentCreate">작성</v-btn>
        </div>
        <v-row class="px-4">
          <v-col cols="1" class="text-center font-weight-black">번호</v-col>
          <v-col cols="9" class="text-center font-weight-black">내용</v-col>
          <v-col cols="1" class="text-end font-weight-black">작성자</v-col>
          <v-col cols="1"></v-col>
        </v-row>
        <v-list v-for="(lostReply, i) in lostReplies" :key="i" class="pa-0">
          <v-list-item>
            <v-row>
              <v-col cols="1" class="text-center">{{ i+1 }}</v-col>
              <v-col cols="9" class="pl-5">{{ lostReply.lostreplycontent }}</v-col>
              <v-col cols="1" class="text-end">{{ lostReply.uid }}</v-col>
              <!-- <v-col cols="1"><v-icon @click="modifyTooltip = !modifyTooltip">mdi-pencil-circle-outline</v-icon><v-icon @click="commentDelete(lostReply.lostreplyno)" v-if="lostReply.uid === profileData.nickName" color="red">mdi-close-circle-outline</v-icon></v-col>
                        <v-tooltip v-model="modifyTooltip">
                            <v-text-field v-model="modifiedComment" @keyup.enter="commentCreate"></v-text-field>
              </v-tooltip>-->
              <v-col cols="1" class="d-flex">
                <v-menu top :close-on-content-click="closeOnContentClick">
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon v-bind="attrs" v-on="on">mdi-pencil-circle-outline</v-icon>
                  </template>
                  <v-card class="d-flex">
                    <input type="text" v-model="lostReply.lostreplycontent" />
                    <v-chip
                      label
                      @click="commentModify(lostReply.lostreplycontent, lostReply.lostreplyno)"
                    >수정</v-chip>
                  </v-card>
                </v-menu>
                <!-- <v-speed-dial direction="top" open-on-hover="false" transition="scale-transition">
                                <template v-slot:activator>
                                    <v-icon>mdi-pencil-circle-outline</v-icon>
                                </template>
                                <v-text-field v-model="modifiedComment" @keyup.enter="commentCreate"></v-text-field>
                </v-speed-dial>-->

                <!-- 수정 간지나는거 찾아보기 -->
                <v-icon
                  @click="commentDelete(lostReply.lostreplyno)"
                  v-if="lostReply.uid === profileData.nickName"
                  color="red"
                >mdi-close-circle-outline</v-icon>
              </v-col>
            </v-row>
          </v-list-item>
          <v-divider></v-divider>
        </v-list>
      </v-card>
    </v-container>
    <div class="float-window">
      <v-card id="create">
        <v-speed-dial :direction="direction" :open-on-hover="hover" :transition="transition">
          <template v-slot:activator>
            <v-btn color="blue darken-2" large dark>
              <div class="d-flex flex-column">
                <p class="ma-0 pa-0">
                  <v-icon small>mdi-magnify</v-icon>이 강아지를
                </p>
                <p class="ma-0 pa-0">찾으셨나요?</p>
              </div>
            </v-btn>
          </template>
          <div v-for="(m, i) in matched" :key="i">
            <v-img
              src="http://www.animal.go.kr/files/shelter/2014/02/201403010903285_s.jpg"
              @click="goDetail(m.lostno)"
            ></v-img>
          </div>
        </v-speed-dial>
      </v-card>
    </div>
    <v-snackbar v-model="snackbar" timeout="2000">수정되었습니다.</v-snackbar>
  </div>
</template>

<script>
import constants from '@/lib/constants'
import axios from 'axios'
import { mapState } from 'vuex'
import swal from 'sweetalert';

export default {
    created(){
        console.log(this.$route.params.articleNo)
        axios.get(constants.SERVER_URL + `/lost/detail?lostno=${this.lostno}`)
            .then(response => {
                console.log(response)
                let result = response.data.object
                this.lostReplies = response.data.lostReply
                this.subContents.견종 = result.lostbreed
                this.subContents.성별 = result.lostsex
                this.subContents.날짜 = result.lostdate
                this.subContents.장소 = result.lostsido + ' ' + result.lostgugun + ' ' + result.lostplace
                this.lostType = result.losttype
                this.lostBreed = result.lostbreed
                this.lostSido = result.lostsido
                this.lostGugun = result.lostgugun
                this.lostDate = result.lostdate
                this.lostPlace = result.lostplace
                this.lostSex = result.lostsex
                this.lostAge = result.lostage
                this.lostTags = []
                this.madeTags = []
                var temp = []
                if(response.data.madetag){
                    for(var x=0;x<response.data.madetag.length;x++){
                        temp.push(response.data.madetag[x].tagname)
                    }
                }
                this.madeTags = temp
                var temp2 = []
                var temp3 = []
                if(response.data.tag){
                    for(var x=0;x<response.data.tag.length;x++){
                        if(this.madeTags.indexOf(response.data.tag[x].tagname) < 0){
                            temp2.push(response.data.tag[x].tagname)
                        }
                        temp3.push(response.data.tag[x].tagname)
                    }
                }
                this.madeTags = temp2
                this.lostTags = temp3
                this.images = result.lostpic1
                this.content = result.lostcontent
                this.writer = result.uid
                if(this.$cookies.isKey('auth-token')){
                    if(this.writer === this.$cookies.get('auth-token').uid){
                        this.isWriter = true
                    }
                }
                if(result.lostpic1){
                    this.getImages.push(result.lostpic1)
                }
                if(result.lostpic2){
                    this.getImages.push(result.lostpic2)
                }
                if(result.lostpic3){
                    this.getImages.push(result.lostpic3)
                }
            }).catch(error => {
                console.log(error)
            })
        axios.get(constants.SERVER_URL + `/lost/match?lostno=${this.lostno}`)
            .then(response => {
                // console.log(response)
                if(response.data.math){
                    if(response.data.match.length){
                        this.matched = response.data.match
                    }
                }
            }).catch(error => {
                console.log(error)
            })
    },
    watch:{
    },
    computed:{
        param(){
            return this.$route.params.articleNo
        },
        ...mapState(['sido_states', 'gugun_states', 'profileData']),
    },
    data(){
        return {
            images: [],
            subTitles:['견종', '성별', '날짜', '장소'],
            subContents:{
                '견종': '',
                '성별': '',
                '날짜': '',
                '장소': '',
            },
            content: '',
            writer: '',
            isWriter: false,
            dialog: false,
            dialog2: false,
            kinds:['골든 리트리버', '그레이 하운드', '그레이트 덴', '그레이트 피레니즈', '기타', '꼬똥 드 뚤레아', '네오폴리탄 마스티프', '노르포크 테리어', '노리치 테리어', '뉴펀들랜드', '닥스훈트', '달마시안',
                '댄디 딘몬트 테리어', '도고 까니리오', '도고 아르젠티노', '도고 아르젠티노', '도베르만', '도사', '동경견', '라브라도 리트리버', '라사 압소', '라이카', '래빗 닥스훈드', '랫 테리어', '레이크랜드 테리어', 
                '로디지안 리즈백 ', '로트바일러', '롯트와일러', '마리노이즈', '마스티프', '말라뮤트', '말티즈', '맨체스터테리어', '미니어쳐 닥스훈트', '미니어쳐 불 테리어', '미니어쳐 슈나우저', '미니어쳐 푸들', '미니어쳐 핀셔',
                '미디엄 푸들', '미텔 스피츠', '믹스견', '바센지', '바셋 하운드', '버니즈 마운틴 독', '베들링턴 테리어', '벨기에 그로넨달', '벨기에 쉽독', '벨기에 테뷰런', '벨지안 셰퍼드 독', '보더 콜리', '보르조이', '보스턴 테리어',
                '복서', '볼로네즈', '부비에 데 플랑드르', '불 테리어', '불독', '브뤼셀그리펀', '브리타니 스파니엘', '블랙 테리어', '비글', '비숑 프리제', '비어디드 콜리', '비즐라', '빠삐용', '사모예드', '살루키', '삽살개', '샤페이', 
                '세인트 버나드', '센트럴 아시안 오브차카', '셔틀랜드 쉽독', '셰퍼드', '슈나우져', '스코티쉬 테리어', '스코티시 디어하운드', '스탠다드 푸들', '스테포드셔불테리어', '스피츠', '시바', '시베리안 허스키', '시베리안라이카', 
                '시잉프랑세즈', '시츄', '시코쿠', '실리햄 테리어', '실키테리어', '아나톨리안 셰퍼드', '아메리칸 불독', '아메리칸 스태퍼드셔 테리어', '아메리칸 아키다', '아메리칸 에스키모', '아메리칸 코카 스파니엘', '아메리칸 핏불 테리어', 
                '아메리칸불리', '아이리쉬 레드 앤 화이트 세터', '아이리쉬 세터', '아이리쉬 울프 하운드', '아이리쉬소프트코튼휘튼테리어', '아키다', '아프간 하운드', '알라스칸 말라뮤트', '에어델 테리어', '오브차카', '오스트랄리안 셰퍼드 독', 
                '오스트랄리안 캐틀 독', '올드 잉글리쉬 불독', '올드 잉글리쉬 쉽독', '와이마라너', '와이어 폭스 테리어', '요크셔 테리어', '울프독', '웨스트 시베리언 라이카', '웨스트하이랜드화이트테리어', '웰시 코기 카디건', '웰시 코기 펨브로크', 
                '웰시 테리어', '이탈리안 그레이 하운드', '잉글리쉬 세터', '잉글리쉬 스프링거 스파니엘', '잉글리쉬 코카 스파니엘', '잉글리쉬 포인터', '자이언트 슈나우져', '재패니즈 스피츠', '잭 러셀 테리어', '저먼 셰퍼드 독', '저먼 와이어헤어드 포인터', 
                '저먼 포인터', '제주개', '제페니즈칭', '진도견', '차우차우', '차이니즈 크레스티드 독', '치와와', '카레리안 베어독', '카이훗', '캐벌리어 킹 찰스 스파니엘', '케니스펜더', '케리 블루 테리어', '케언 테리어', '케인 코르소', '코리아 트라이 하운드', 
                '코리안 마스티프', '코카 스파니엘', '코카 푸', '코카시안오브차카', '콜리', '클라인스피츠', '키슈', '키스 훈드', '토이 맨체스터 테리어', '토이 푸들', '티베탄 마스티프', '파라오 하운드', '파슨러셀 테리어', '팔렌', '퍼그', '페키니즈', '페터데일테리어', 
                '포메라니안', '포인터', '폭스테리어', '푸들', '풀리', '풍산견', '프레사까나리오', '프렌치 불독', '프렌치 브리타니', '플랫 코티드 리트리버', '플롯하운드', '피레니안 마운틴 독', '필라 브라질레오', '핏불테리어', '허배너스', '화이트리트리버', '화이트테리어', '휘펫'
            ],
            categories: ['실종', '보호', '목격'],
            gender:['암컷', '수컷', '알 수 없음'],
            lostno: this.$route.params.articleNo,
            lostBreed: '',
            lostSido: '',
            lostGugun: '',
            images: [],
            getImages: [],
            lostType: '',
            lostDate: null,
            lostPlace: '',
            lostSex: '',
            lostAge: '',
            lostTagText: '',
            lostTags: [],
            madeTags: [],
            matched: [],
            direction: 'bottom',
            hover: true,
            transition: 'scale-transition',
            comment: '',
            lostReplies: [],
            modifyDialog: false,
            modifiedComment: '',
            closeOnContentClick: false,
            snackbar: false,
        }
    },
    methods:{
        deleteLost(){
            axios.delete(constants.SERVER_URL + `/lost/delete?lostno=${this.$route.params.articleNo}&uid=${this.writer}`)
                .then(response => {
                    swal({
                      title:'삭제되었습니다.',
                      icon: "success",
                      button: "OK"
                      })
                    this.$router.push({name: constants.URL_TYPE.LOST.LOSTLIST})
                }).catch(error => {
                    console.log(error)
                })
        },
        goList(){
            this.$router.push({name: constants.URL_TYPE.LOST.LOSTLIST})
        },
        goDetail(No){
            this.$router.push({name: constants.URL_TYPE.LOST.LOSTDETAIL, params: {articleNo: No}})
            this.$router.go()
        },
        closeDialog(){
            this.dialog = false
            this.$router.go()
        },
        onChangeImages(event){
            
            if(event.target.files.length > 3){
            swal({
              title:'파일은 3개까지 저장 가능합니다.',
              icon: "warning",
              button: "OK"
              })
                document.getElementById('inputFiles').value = '';
                console.log(document.getElementById('inputFiles').files.length)
            }else{
                this.images = event.target.files
            }
        },
        submit(){
            if(this.lostTags.indexOf(this.lostBreed)>=0){
                this.lostTags.splice(this.lostTags.indexOf(this.lostBreed),1)
            }
            if(this.lostTags.indexOf(this.lostSido)>=0){
                this.lostTags.splice(this.lostTags.indexOf(this.lostSido),1)
            }
            if(this.lostTags.indexOf(this.lostGugun)>=0){
                this.lostTags.splice(this.lostTags.indexOf(this.lostGugun),1)
            }
            if(this.lostTags.indexOf(this.lostType)>=0){
                this.lostTags.splice(this.lostTags.indexOf(this.lostType),1)
            }
            const formData = new FormData()
            formData.append('uid', this.profileData.nickName)
            formData.append('lostbreed', this.lostBreed)
            formData.append('losttype', this.lostType)
            formData.append('lostdate', this.lostDate)
            formData.append('lostsido', this.lostSido)
            formData.append('lostgugun', this.lostGugun)
            formData.append('lostplace', this.lostPlace)
            formData.append('lostcontent', this.content)
            formData.append('lostsex', this.lostSex)
            formData.append('lostphone', this.profileData.phone)
            formData.append('lostno', this.$route.params.articleNo)
            formData.append('losttagtext', this.madeTags)
            if(this.images){
                for(var x=0;x<this.images.length;x++){
                    formData.append('files', this.images[x])
                }
            }
            

            // const imageForm = new FormData()
            // imageForm.append('files', this.images)
            
            // FormData 객체를 log 찍어보려면
            // FormData.entries()를 이용하여 key : value 쌍을 뽑아야 함.
            
            console.log(this.images)
            axios.post(constants.SERVER_URL + '/lost/add', formData, {
                headers: {
                'Content-Type': 'multipart/form-data'
                }
            }).then(response => {
                console.log(response)
            }).catch(error => {
                console.log("에러다!!!!!")
                console.log(error)
            })
            
            this.dialog = false
            this.$router.go()
        },
        closeTag(index){
            this.madeTags.splice(index, 1)
            console.log(this.lostTags)
        },
        getCommentList(){
            if(this.$cookies.isKey('auth-token')){
                if(this.$cookies.get('auth-token').mid !== undefined){
                    swal({
                      title:'매니저는 댓글을 다실 수 없습니다.',
                      text: '죄송합니다',
                      icon: "warning",
                      button: "OK"
                      })
                }
            }else{
                axios.get(constants.SERVER_URL + `/lost/detail?lostno=${this.lostno}`)
                    .then(response => {
                        this.lostReplies = response.data.lostReply
                    }).catch(error => {
                        console.log(error)
                    })
            }
        },
        commentCreate(){
            if(this.$cookies.isKey('auth-token')){
                axios.post(constants.SERVER_URL + '/lost/reply/add', {
                    uid: this.profileData.nickName,
                    lostno: this.$route.params.articleNo,
                    lostcontent: this.comment,
                    }).then(response => {
                        if(response.data.data === 'success'){
                            this.getCommentList()
                        }
                    }).catch(error => {
                        console.log(error)
                    })
            }else{
                swal({
                  title: '로그인 후 이용 가능합니다.',
                  icon: "error",
                  button: "OK" 
                })
            }
            this.comment = ''
        },
        commentModify(content, contentNo){
            axios.post(constants.SERVER_URL + '/lost/reply/add', {
                uid: this.profileData.nickName,
                lostno: this.$route.params.articleNo,
                lostcontent: content,
                lostreplyno: contentNo,
                }).then(response => {
                    if(response.data.data === 'success'){
                        this.getCommentList()
                    }
                    this.snackbar = !this.snackbar
                }).catch(error => {
                    console.log(error)
                })
        },
        commentDelete(replyno){
            axios.delete(constants.SERVER_URL + `/lost/reply/delete?lostreplyno=${replyno}&uid=${this.profileData.nickName}`)
                .then(response => {
                    if(response.data.data === 'success'){
                        this.getCommentList()
                    }
                }).catch(error => {
                    console.log(error)
                })
        },
        makeApplyTag(){
            if(this.lostTagText.length > 0){
                if(this.lostTagText[0] === "#"){
                this.madeTags.push(this.lostTagText.substring(1,this.lostTagText.length))
                this.lostTagText = ''
                }
            }
        },
    },
}
</script>

<style>
.detailcol1 {
  padding-right: 0;
}
.detailcol2 {
  padding-left: 0;
}
@media (max-width: 760px) {
  .detailcol1 {
    padding-right: 12px;
  }
  .detailcol2 {
    padding-left: 12px;
  }
}
.float-window {
    position: fixed;
    bottom: 40vw;
    right: 5vw;
}
.write-btn {
    position: absolute;
    bottom: 60px;
    right: 10px;
}
.comment-header {
    position: absolute;
    display: flex;
    flex-direction: row;
    justify-content: center;
    bottom: 3px;
    width: 100%;
}
</style>