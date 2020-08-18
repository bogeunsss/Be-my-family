<template>
  <div class="container">
    <!-- <h3>어돕션디테이이이이일</h3>
    <div class='card border' style="border:1px solid red;">
        <h3>hashah</h3>
    </div>-->
    <!-- {{adoption}}
    {{dogData}} -->

    <v-card class="mx-auto" style="width:80%;">
      <v-card-text class="d-flex">
        <img :src="dogData.popfile" alt="dog images" width="50%" height="400px" />
        <v-container>
          <div class="mr-auto">
            <!-- <v-text-field filled label="Title" value="My new post"></v-text-field> -->
            <v-row>
              <v-col col="12" class="font-weight-black">구조일</v-col>
              {{ dogData.noticesdt }}
            </v-row>
            <v-row>
              <v-col col="12" class="font-weight-black">구조장소</v-col>
              {{ dogData.happenplace }}
            </v-row>
            <v-row>
              <v-col col="6" class="font-weight-black">견종</v-col>
              {{ dogData.kindcd }}
              <v-col col="6" class="font-weight-black">성별</v-col>
              {{ dogData.sexcd}}
            </v-row>
            <v-row>
              <v-col col="6" class="font-weight-black">연령</v-col>
              {{ dogData.age }}
              <v-col col="6" class="font-weight-black">모색</v-col>
              {{ dogData.colorcd }}
            </v-row>
            <v-row>
              <v-col col="6" class="font-weight-black">중성화 여부</v-col>
              {{ dogData.neuteryn }}
              <v-col col="6" class="font-weight-black">체중</v-col>
              {{ dogData.weight }}
            </v-row>
            <v-row>
              <v-col col="12" class="font-weight-black">특징</v-col>
              {{ dogData.specialmark }}
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
          <v-list-item-subtitle>보호소 No : {{adoption.mid}}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-list class="transparent">
        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title>Name :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.name}}</v-list-item-subtitle>
            </v-col>
            <v-col>
              <v-list-item-title>Email :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.email}}</v-list-item-subtitle>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title>Phone :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.phone}}</v-list-item-subtitle>
            </v-col>
            <v-col>
              <v-list-item-title>Nation :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.nation}}</v-list-item-subtitle>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title>Marriaged :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.marriaged}}</v-list-item-subtitle>
            </v-col>
            <v-col>
              <v-list-item-title>sex :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.sex}}</v-list-item-subtitle>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title>Job :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.job}}</v-list-item-subtitle>
            </v-col>
            <v-col>
              <v-list-item-title>Address :</v-list-item-title>
              <v-list-item-subtitle
                class="text-right"
              >{{ adoption.sido}} / {{ adoption.gugun}}</v-list-item-subtitle>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title>Place :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.place}}</v-list-item-subtitle>
            </v-col>
            <v-col>
              <v-list-item-title>Alone :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.alone}}</v-list-item-subtitle>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-row>
            <v-col>
              <v-list-item-title>Eatmoney :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.eatmoney}}</v-list-item-subtitle>
            </v-col>
            <v-col>
              <v-list-item-title>Dissolution :</v-list-item-title>
              <v-list-item-subtitle class="text-right">{{ adoption.dissolution}}</v-list-item-subtitle>
            </v-col>
          </v-row>
        </v-list-item>

        <v-list-item>
          <v-list-item-title>Think :</v-list-item-title>
          <v-list-item-subtitle class="text-right">{{ adoption.think}}</v-list-item-subtitle>
        </v-list-item>

        <v-list-item>
          <v-list-item-title>Experience :</v-list-item-title>
          <v-list-item-subtitle class="text-right">{{ adoption.house}}</v-list-item-subtitle>
        </v-list-item>

        <v-list-item>
          <v-list-item-title>Reason :</v-list-item-title>
          <v-list-item-subtitle class="text-right">{{ adoption.reason}}</v-list-item-subtitle>
        </v-list-item>

        <v-list-item>
          <v-list-item-title>Reservation :</v-list-item-title>
          <v-list-item-subtitle class="text-right">{{ adoption.fixdate}}.{{ adoption.fixtime}}</v-list-item-subtitle>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-card-actions>
        <!-- <div class="d-flex float-right mr-3"> -->
        <v-btn @click="dogApproval" class="float-right">승인완료</v-btn>
        <v-btn @click="dogApproval" class="float-right">승인거절</v-btn>
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
      return this.$route.params.desertionno
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
          // console.log(response)
          this.adoptionData = response.data.adoptions.filter(
            (item) => item.desertionno == this.$route.params.desertionno
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
          this.getDetail();
        })
        .catch((err) => console.log(err));
    },

    getDetail() {
      // console.log(this.$route.params.desertionno)
      axios
        .get(constants.SERVER_URL + "/care/detailUser", {
          params: {
            desertionno: this.$route.params.desertionno,
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
        axios.patch(constants.SERVER_URL + '/manager/adoptionList/approve',
        {
          adoptionno : this.adoption.adoptionno,
          uid : this.adoption.uid,
          desertionno :this.adoption.desertionno,
          fixdate : this.adoption.fixdate,
          fixtime : this.adoption.fixtime,
          name : this.adoption.name,
          mid : this.adoption.mid,
          email : this.adoption.email,
          phone : this.adoption.phone
        }).then((res)=> {
        console.log(res)
        alert('승인완료 되었습니다.')
        }).catch((err)=>{
          console.log9err
        })
    },
    dogApprovalRefuse() {
      axios.patch(constants.SERVER_URL + '/manager/adoptionList/reject',
        {
          adoptionno : this.adoption.adoptionno,
          uid : this.adoption.uid,
          desertionno :this.adoption.desertionno,
          fixdate : this.adoption.fixdate,
          fixtime : this.adoption.fixtime,
          name : this.adoption.name,
          mid : this.adoption.mid,
          email : this.adoption.email,
          phone : this.adoption.phone
        }).then((res)=> {
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