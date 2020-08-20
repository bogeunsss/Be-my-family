<template>
  <div style="margin-top:5rem;">
    <v-form>
      <v-container class="pa-0">
        <h1 class="mx-5">입양후기</h1>
        <v-card class="ma-5">
          <v-toolbar flat color="white">
            <v-list-item class="d-flex pt-5">
              <v-list-item-content>
                <v-list-item-title class="headline">{{Adoptdata.title}}</v-list-item-title>
                <v-list-item-subtitle>{{Adoptdata.uid}}</v-list-item-subtitle>
              </v-list-item-content>
              <v-card-actions v-if="!isManager" class="d-flex ml-auto px-0 mt-auto">
                <v-btn small icon v-if="!this.likegood" @click="like">
                  <v-icon>mdi-heart</v-icon>
                </v-btn>
                <v-btn small icon style="color:red;" v-if="this.likegood" @click="like">
                  <v-icon>mdi-heart</v-icon>
                </v-btn>
                <p class="ma-0 my-auto">{{good}}</p>
              </v-card-actions>
            </v-list-item>
          </v-toolbar>

          <v-divider class="mt-5"></v-divider>

          <v-row>
            <v-col cols="12" md="4" class="px-5">
              <v-card-subtitle class="mx-auto" style="width: 100%">
                <v-carousel hide-delimiters style="width:500px; height:400px;">
                  <v-carousel-item
                    v-for="(image,i) in images"
                    :key="i"
                    :src="'http://i3b201.p.ssafy.io/file/'+image.postpath"
                  ></v-carousel-item>
                </v-carousel>
              </v-card-subtitle>
            </v-col>
            <v-col cols="12" md="8">
              <v-card-text>
                <p>
                  <span style="font-weight:bold; font-size:1.2rem;">지역 :</span>
                  <span>{{Adoptdata.sido}} {{Adoptdata.gugun}}</span>
                </p>
                <p>
                  <span style="font-weight:bold; font-size:1.2rem;">품종 :</span>
                  <span>{{Adoptdata.kind}}</span>
                </p>
                <p>
                  <span style="font-weight:bold; font-size:1.2rem;">내용 :</span>
                  <span>{{Adoptdata.content}}</span>
                </p>
              </v-card-text>
            </v-col>
          </v-row>

          <v-card-actions class="d-flex justify-end">
            <v-btn
              text
              color="deep-purple accent-4"
              v-if="this.profileData.nickName == this.Adoptdata.uid"
              @click="postupdate"
            >수정</v-btn>
            <v-btn
              text
              color="red accent-4"
              v-if="this.profileData.nickName == this.Adoptdata.uid"
              @click="postdelete"
            >삭제</v-btn>
          </v-card-actions>
        </v-card>

       <v-card class="mx-5">

        <v-card 
          v-for="comment in comments"
          :key="comment.commentno"
          class="mx-5 pt-3"
          max-width="100%"
          min-height="3rem"
          outlined
          style="border-top:none; border-right:none; border-left:none;"
        >
          <div class="px-5" style="line-height:3rem;">
            <span>{{comment.uid}} : {{comment.content}}</span>
            <v-btn
              class="float-right px-0"
              text
              small
              color="red accent-4"
              v-if="profileData.nickName == comment.uid"
              @click="commentdelete(comment.commentno)"
            > <v-icon
                >mdi-close-circle-outline</v-icon></v-btn>
            <v-btn
              class="float-right px-0"
              text
              small
              color="deep-purple accent-4"
              v-if="profileData.nickName == comment.uid"
              @click="changeupdate(comment.commentno)"
            ><v-icon >mdi-pencil-circle-outline</v-icon></v-btn>
            
            <div class="d-flex justify-left" v-if="isupdate">
              <v-textarea
                v-if="comment.commentno == cid"
                auto-grow
                outlined
                rows="1"
                row-height="15"
                v-model="updatecomment.content"
              ></v-textarea>
              <v-btn
                v-if="comment.commentno == cid"
                class="mt-2"
                text
                color="success accent-4"
                @click="commentupdate(comment.commentno)"
              >수정완료</v-btn>
            </div>
          </div>
        </v-card>

        <div class="mx-5 d-flex justify-left mt-5">
          <v-textarea
            label="Comment"
            auto-grow
            outlined
            rows="1"
            row-height="15"
            v-model="commentData.content"
          ></v-textarea>
          <!-- <v-textarea class="mx-2" label="Comment" rows="1"></v-textarea> -->
          <v-btn
            class="ma-2 mt-0"
            outlined
            medium
            fab
            color="indigo"
            style="border:none;"
            @click="createComment"
          >
            <v-icon medium>mdi-comment</v-icon>
          </v-btn>
        </div>
      </v-card>
        <v-card-actions class="d-flex justify-center">
          <v-btn @click="adoptlist" class="ma-2" outlined color="indigo">목록</v-btn>
        </v-card-actions>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import constants from "@/lib/constants";
import axios from "axios";
import { mapState, mapActions } from "vuex";
import swal from 'sweetalert';

export default {
  created() {
    this.adoptdetail();
    this.commentData.postscriptno = this.$route.params.ID;
    if (this.$cookies.isKey("auth-token")) {
      if (this.$cookies.get("auth-token").mid !== undefined) {
        this.isManager = true;
      }
    }
    // this.likecheck()
  },
  computed: {
    ...mapState(["profileData", "loginData"]),
    param() {
      return this.$route.params.ID;
    },
  },
  methods: {
    ...mapActions(["find"]),
    adoptlist() {
      this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTLIST });
    },
    adoptdetail(){
      setTimeout(()=>{
        axios.get(constants.SERVER_URL + `/postscript/detail?postscriptno=${this.$route.params.ID}&uid=${this.profileData.nickName}`)
      .then((res) =>{
      console.log(res)
        this.Adoptdata = res.data.object
        this.comments = res.data.comments
        this.likegood = res.data.isGood
        this.good = res.data.good
        this.images = res.data.postpic
      })
      .catch((error) =>{
        console.log(error)
      })},100)
    },
    postdelete() {
      axios
        .delete(
          constants.SERVER_URL +
            `/postscript/Delete?postscriptno=${this.$route.params.ID}&uid=${this.profileData.nickName}`
        )
        .then(() => {
          swal({
            title: "삭제되었습니다.",
            icon: "success",
            button: "OK"
          });
          this.adoptlist();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    postupdate() {
      window.scrollTo(0, 0)
      this.$router.push({
        name: constants.URL_TYPE.ADOPTIONPOST.ADOPTUPDATE,
        params: { ID: this.$route.params.ID },
      });
    },
    createComment() {
      if (!this.$cookies.isKey("auth-token")) {
        swal({
          title: "로그인해주세요",
          icon: "warning",
          button: "OK"
          });
      } else {
        if (this.$cookies.get("auth-token").uid !== undefined) {
          var flag = 0;
          if (this.commentData.content == "") {
            swal({
              title: "댓글을 입력해주세요",
              icon: "warning",
              button: "OK"
            });
            flag = 1;
          }
          if (flag == 0) {
            this.commentData.uid = this.profileData.nickName;
            axios
              .post(constants.SERVER_URL + "/comment/add", this.commentData)
              .then((res) => {
                console.log(this.commentData);
                this.commentData.content = "";
                swal({
                  title:"댓글이 등록되었습니다.",
                  icon: "success",
                  button:"OK"
                }).then(()=>{
                    this.$router.go()
                });
              })
              .catch((error) => {
                console.log(error);
              });
          }
        } else {
          swal({
            title:"죄송합니다. 매니저는 댓글을 다실 수 없습니다.",
            icon: "warning",
            button: "OK"
          })
          this.commentData.content = "";
        }
      }
    },
    commentupdate(Commentno) {
      axios
        .put(constants.SERVER_URL + "/comment/modify", {
          uid: this.profileData.nickName,
          commentno: Commentno,
          content: this.updatecomment.content,
          postscriptno: this.$route.params.ID,
        })
        .then(() => {
          swal({
            title:"수정완료!",
            icon: "success",
            button:"OK"
          }).then(()=>{
              this.$router.go()
          });
          this.$router.push({
            name: constants.URL_TYPE.ADOPTIONPOST.ADOPTDETAIL,
            params: { ID: this.$route.params.ID },
          });
      
        })
        .catch((error) => {
          console.log(error);
        });
    },
    commentdelete(Commentno) {
      axios
        .delete(
          constants.SERVER_URL +
            `/comment/delete?commentno=${Commentno}&uid=${this.profileData.nickName}`
        )
        .then(() => {
            swal({
            title:"삭제완료!",
            icon: "success",
            button:"OK"
          }).then(()=>{
              this.$router.go()
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    changeupdate(commentno) {
      this.isupdate = !this.isupdate;
      this.cid = commentno;
      console.log(this.cid);
    },
    like() {
      axios
        .post(
          constants.SERVER_URL +
            `/postscript/good/add?postscriptno=${this.$route.params.ID}&uid=${this.profileData.nickName}`
        )
        .then((res) => {
          console.log(res.data.isGood);
          // this.Like = res.data.isGood
          if (res.data.isGood == true) {
            this.likegood = true;
            console.log(this.likegood);
          } else {
            this.likegood = false;
            console.log(this.likegood);
          }
          this.adoptdetail();
          // console.log(this.Like)
        })
        .catch((error) => {
          console.log(error);
        });
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
      likegood: "",
      updatecomment: {},
      isupdate: false,
      cid: "",
      commentData: {
        uid: "",
        postscriptno: "",
        content: "",
        commentno: "",
      },
      isManager: false,
      good: '',
      images: [],
    };
  },
};
</script>


<style>
</style>