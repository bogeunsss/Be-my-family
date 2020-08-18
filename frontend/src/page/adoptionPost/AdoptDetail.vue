<template>
  <v-form>
    <v-container class="pa-0">
      <h1>입양후기</h1>
      <v-card>
        <v-toolbar flat color="white">
          <v-list-item>
            <v-list-item-avatar color="grey"></v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title class="headline">{{Adoptdata.title}}</v-list-item-title>
              <v-list-item-subtitle>{{Adoptdata.uid}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-toolbar>
        <v-card-subtitle class="mx-auto" style="width: 60%">
          <v-img
            src="http://www.animal.go.kr/files/shelter/2014/02/201403010903285_s.jpg"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            max-height="100%"
            height="auto"
          ></v-img>
        </v-card-subtitle>

        <v-card-actions class="d-flex justify-end mb-3">
          <v-btn icon large v-if="!this.likegood" @click="like">
            <v-icon large>mdi-heart</v-icon>
          </v-btn>
          <v-btn icon large style="color:red;" v-if="this.likegood" @click="like">
            <v-icon large>mdi-heart</v-icon>
          </v-btn>
        </v-card-actions>

        <v-divider></v-divider>

        <v-card-text>
          <p><span style="font-weight:bold; font-size:1.2rem;">제목 : </span><span>{{Adoptdata.title}}</span></p>
          <p><span style="font-weight:bold; font-size:1.2rem;">지역 : </span><span>{{Adoptdata.sido}} {{Adoptdata.gugun}}</span></p>
          <p><span style="font-weight:bold; font-size:1.2rem;">품종 : </span><span>{{Adoptdata.kind}}</span></p>
          <p><span style="font-weight:bold; font-size:1.2rem;">내용 : </span><span>{{Adoptdata.content}}</span></p>
  
        </v-card-text>

        <v-card-actions class="d-flex justify-end">
          <v-btn text color="deep-purple accent-4" v-if="this.profileData.nickName == this.Adoptdata.uid" @click="postupdate">수정</v-btn>
          <v-btn text color="red accent-4" v-if="this.profileData.nickName == this.Adoptdata.uid"  @click="postdelete">삭제</v-btn>
        </v-card-actions>
      </v-card>


      <v-card v-for="comment in comments" :key="comment.commentno" class="mx-auto mt-5" max-width="100%" min-height="3rem" outlined style="border-top:none; border-right:none; border-left:none;">
        <div style="line-height:3rem;"> 
           <span>{{comment.uid}} : {{comment.content}}</span>
          <v-btn text color="deep-purple accent-4" v-if="profileData.nickName == comment.uid"  @click="changeupdate(comment.commentno)" >수정</v-btn>
          <v-btn text color="red accent-4" v-if="profileData.nickName == comment.uid"  @click="commentdelete(comment.commentno)">삭제</v-btn>
          <div class="d-flex justify-left" v-if="isupdate">
          <v-textarea
          v-if="comment.commentno == cid"
          auto-grow
          outlined
          rows="1"
          row-height="15"
          v-model="updatecomment.content"
        ></v-textarea>
        <v-btn v-if="comment.commentno == cid" class="mt-2" text color="success accent-4" @click="commentupdate(comment.commentno)">수정완료</v-btn>
          </div>
        </div>
      </v-card>

      <div class="d-flex justify-left mt-5">
         <v-textarea
          label="Comment"
          auto-grow
          outlined
          rows="1"
          row-height="15"
          v-model="commentData.content"
        ></v-textarea>
        <!-- <v-textarea class="mx-2" label="Comment" rows="1"></v-textarea> -->
        <v-btn class="ma-2 mt-0" outlined medium fab color="indigo" style="border:none;" @click="createComment">
          <v-icon medium >mdi-comment</v-icon>
        </v-btn>
      </div>

      <v-card-actions class="d-flex justify-center">
        <v-btn @click="adoptlist" class="ma-2" outlined color="indigo">목록</v-btn>
      </v-card-actions>
    </v-container>
  </v-form>
</template>

<script>
import constants from "@/lib/constants";
import axios from 'axios'
import { mapState,  mapActions} from 'vuex'

export default {
  created(){
    this.adoptdetail()
    this.commentData.postscriptno = this.$route.params.ID
    // this.likecheck()
  },
  computed:{
    ...mapState(['profileData','loginData' ]),
    param()
    {
      return this.$route.params.ID
    }
  },
  methods: {
    ...mapActions(['find']),
    adoptlist() {
      this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTLIST });
    },
    adoptdetail(){
      setTimeout(()=>{
        axios.get(constants.SERVER_URL + `/postscript/detail?postscriptno=${this.$route.params.ID}&uid=${this.profileData.nickName}`)
      .then((res) =>{
      console.log(this.$route.params.ID, this.profileData.nickName)
        this.Adoptdata = res.data.object
        this.comments = res.data.comments
        this.likegood = res.data.isGood
        console.log(this.likegood)
        // console.log(this.Adoptdata)
        // console.log(this.comments)
      })
      .catch((error) =>{
        console.log(error)
      })},100)
    },
    postdelete(){ 
      axios.delete(constants.SERVER_URL + `/postscript/Delete?postscriptno=${this.$route.params.ID}&uid=${this.profileData.nickName}`)
      .then(()=>{
        alert("삭제되었습니다.")
         this.adoptlist()
      })
      .catch((error)=>{
        console.log(error)
      })
    },
    postupdate(){
      this.$router.push( {name: constants.URL_TYPE.ADOPTIONPOST.ADOPTUPDATE , params:{ ID: this.$route.params.ID}})
    },
    createComment(){
      if(!this.$cookies.isKey("auth-token")){
          alert('로그인해주세요')
      }else{
        if(this.$cookies.get('auth-token').uid !== undefined){
          var flag = 0
          if(this.commentData.content == ""){
            alert("댓글을 입력해주세요.")
            flag = 1
          }
          if(flag == 0){
            this.commentData.uid = this.profileData.nickName
            axios.post(constants.SERVER_URL + "/comment/add", this.commentData)
            .then((res)=>{
              console.log(this.commentData)
              this.commentData.content = "";
              alert("댓글이 등록되었습니다.")
              this.$router.go()
            })
            .catch((error) =>{
              console.log(error)
            })
          }
        }else{
          alert('죄송합니다. 매니저는 댓글을 다실 수 없습니다.')
          this.commentData.content = ''
        }
      }

    },
    commentupdate(Commentno){
      axios.put(constants.SERVER_URL + '/comment/modify',{
        uid: this.profileData.nickName,
        commentno : Commentno,
        content : this.updatecomment.content,
        postscriptno: this.$route.params.ID
      })  
      .then(() =>{
        alert("수정완료!")
        this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTDETAIL, params:{ ID: this.$route.params.ID} })
        this.$router.go()
      })
      .catch((error)=>{
        console.log(error)
      })
    },
    commentdelete(Commentno){
      axios.delete(constants.SERVER_URL + `/comment/delete?commentno=${Commentno}&uid=${this.profileData.nickName}`)
      .then(() => {
        alert("삭제완료")
        this.$router.go()

      })
      .catch((error) =>{
        console.log(error)
      })
    },
    changeupdate(commentno){
      this.isupdate = !this.isupdate
      this.cid = commentno
      console.log(this.cid)
    },
    like(){
      axios.post(constants.SERVER_URL + `/postscript/good/add?postscriptno=${this.$route.params.ID}&uid=${this.profileData.nickName}`)
      .then((res)=>{
        console.log(res.data.isGood)
        // this.Like = res.data.isGood
        if(res.data.isGood == true){
          this.likegood = true
          console.log(this.likegood)
        }else{
          this.likegood = false
          console.log(this.likegood)
        }
        // console.log(this.Like)
      })
      .catch((error)=>{
        console.log(error)
      })
    },
    // likecheck(){
    //   axios.post(constants.SERVER_URL + `/postscript/good/add?postscriptno=${this.$route.params.ID}&uid=${this.profileData.nickName}`)
    //   .then((res)=>{
    //     this.Like = res.data.isGood
    //   })
    // },
  },
  data() {
    return {
      Adoptdata: {},
      comments: [],
      likegood:'',
      updatecomment:{},
      isupdate: false,
      cid:"",
      commentData:{
        uid:"",
        postscriptno:"",
        content:"",
        commentno:""
      },
    };
  },
};
</script>


<style>
</style>