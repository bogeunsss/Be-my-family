<template>
  <div class="container" style="margin-top:5rem;">
    <h2>입양신청 희망자 상세정보</h2>

    <v-card class="mx-auto" style="width:80%;">
      <v-card-text class="d-flex">
        <img :src="dogData.popfile" alt="dog images" width="50%" height="400px" />
        <v-container>
          <div class="mr-auto">
            <!-- <v-text-field filled label="Title" value="My new post"></v-text-field> -->
            <v-row>
              <v-col col="12"><span  class="font-weight-black">구조일 : </span>{{ dogData.noticesdt }}</v-col>   
            </v-row>
            <v-row>
              <v-col col="12"> <span class="font-weight-black">구조장소 : </span>{{ dogData.happenplace }}</v-col>        
            </v-row>
            <v-row>
              <v-col col="6"> <span class="font-weight-black">견종 : </span> {{ dogData.kindcd }}</v-col>
              
              <v-col col="6"> <span class="font-weight-black">성별 : </span>{{ dogData.sexcd}}</v-col>
              
            </v-row>
            <v-row>
              <v-col col="6"> <span class="font-weight-black">연령 : </span>{{ dogData.age }}</v-col>
              
              <v-col col="6"> <span class="font-weight-black">모색 : </span>{{ dogData.colorcd }}</v-col>
              
            </v-row>
            <v-row>
              <v-col col="6"> <span class="font-weight-black">중성화 여부 : </span>{{ dogData.neuteryn }}</v-col>
              
              <v-col col="6"> <span class="font-weight-black">체중 : </span> {{ dogData.weight }}</v-col>
             
            </v-row>
            <v-row>
              <v-col col="12"> <span class="font-weight-black">특징 : </span>{{ dogData.specialmark }}</v-col>
              
            </v-row>
          </div>
          <v-divider class="my-2"></v-divider>
        </v-container>
      </v-card-text>
    </v-card>

    <v-card class="mx-auto mt-5 pt-5" max-width="80%">
      <v-list-item two-line>
        <v-list-item-content>
          <v-list-item-title class="headline">입양희망자 상세정보</v-list-item-title>
          <v-list-item-subtitle ><span style="color: #4ba5cd;" >보호소 No : </span>{{adoption.mid}}</v-list-item-subtitle>

        </v-list-item-content>
      </v-list-item>

      <v-list class="transparent">
        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Name : </span>{{ adoption.name}}</v-list-item-title>
            </v-col>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Email :</span> {{ adoption.email}}</v-list-item-title>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Phone :</span> {{ adoption.phone}}</v-list-item-title>
            </v-col>
            <v-col>
              <v-list-item-title> <span class="font-weight-black"> Nation : </span> :{{ adoption.nation}}</v-list-item-title>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Marriaged : </span>{{ adoption.marriaged}} </v-list-item-title>
            </v-col>
            <v-col>
              <v-list-item-title><span class="font-weight-black">sex : </span>{{ adoption.sex}}</v-list-item-title>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Job : </span>{{ adoption.job}}</v-list-item-title>
            </v-col>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Address : </span>{{ adoption.sido}} / {{ adoption.gugun}}</v-list-item-title>
  
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Place : </span>{{ adoption.place}}</v-list-item-title>
            </v-col>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Alone : </span>{{ adoption.alone}}</v-list-item-title>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Eatmoney : </span>{{ adoption.eatmoney}}</v-list-item-title>
            </v-col>
            <v-col>
              <v-list-item-title><span class="font-weight-black">Dissolution : </span>{{ adoption.dissolution}}</v-list-item-title>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-list-item-title><span class="font-weight-black">Think : </span>{{ adoption.think}}</v-list-item-title>
        </v-list-item>

        <v-list-item>
          <v-list-item-title><span class="font-weight-black">Experience : </span>{{ adoption.house}}</v-list-item-title>
        </v-list-item>

        <v-list-item>
          <v-list-item-title><span class="font-weight-black">Reason : </span>{{ adoption.reason}}</v-list-item-title>
        </v-list-item>

        <v-list-item>
          <v-list-item-title><span class="font-weight-black">Reservation : </span>{{ adoption.fixdate}}.{{ adoption.fixtime}}</v-list-item-title>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-card-actions>
        <div class="d-flex ml-auto">
        <v-btn @click="dogApproval" class="mr-2" color="primary">승인완료</v-btn>
        <v-btn @click="dogApprovalRefuse" color="error">승인거절</v-btn>
        </div>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import constants from "@/lib/constants";
import axios from "axios";

export default {
  name: "adoptiondetail",
  created() {
    this.getAdoptionList();
    // this.getDetail()
  },
  computed:{
    param(){
      return this.$route.params.adoptionno
    },
  },
  methods: {
    getAdoptionList() {
      axios
        .get(constants.SERVER_URL + "/manager/adoptionList", {
          params: {
            mid: this.$cookies.get("auth-token").mid,
          },
        })
        .then((response) => {
          console.log(response)
          this.adoptionData = response.data.adoptions.filter(
            (item) => item.adoptionno === this.$route.params.adoptionno
          );
          // console.log(this.adoption);
          // this.adoption.mid = this.adoptionData[0].mid
          // this.adoption.name = this.adoptionData[0].name
          // this.adoption.phone = this.adoptionData[0].phone
          // this.adoption.alone = this.adoptionData[0].alone
          // this.adoption.eatmoney = this.adoptionData[0].eatmoney
          // this.adoption.dissolution = this.adoptionData[0].dissolution
          // this.adoption.think = this.adoptionData[0].think
          // this.adoption.house = this.adoptionData[0].house
          // this.adoption.reason = this.adoptionData[0].reason
          this.adoption = this.adoptionData[0]
          // console.log(this.mid)
          this.getDetail(this.adoption.desertionno);
        })
        .catch((err) => console.log(err));
    },

    getDetail(no) {
      // console.log(this.$route.params.adoptionno)
      axios
        .get(constants.SERVER_URL + "/care/detailUser", {
          params: {
            desertionno: no,
            uid: this.$cookies.get('auth-token').uid
          },
        })
        .then((response) => {
          //   console.log(response)
          this.dogData = response.data.object;
          // console.log(this.dogData);
          // console.log(this.adoption[0]);
          // console.log(this.adoption[0].mid);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    dogApproval() {
      console.log(this.adoption.adoptionno)
        axios.patch(constants.SERVER_URL + '/manager/adoptionList/approve?adoptionno=' + this.adoption.adoptionno
        ).then((res)=> {
        console.log(res)
        alert('승인완료 되었습니다.')
        }).catch((err)=>{
          console.log9err
        })
    },
    dogApprovalRefuse() {
      axios.patch(constants.SERVER_URL + '/manager/adoptionList/reject?adoptionno=' + this.adoption.adoptionno
        ).then((res)=> {
        console.log(res)
        alert('승인거절 되었습니다.')
        }).catch((err)=>{
          console.log9err
        })

    },

  },
  data() {
    return {
      adoptionData: Array,
      adoption: {
        name: '',
        email: '',
        phone: '',
        nation: '',
        marriaged: '',
        sex: '',
        job: '',
        sido: '',
        gugun: '',
        place: '',
        alone: '',
        eatmoney: '',
        dissolution: '',
        think: '',
        house: '',
        reason: '',
        fixdate: '',
        fixtime: '',
        mid: '',
      },
      dogData: [],
      isApproval:false,
    };
  },
};
</script>

<style>
</style>