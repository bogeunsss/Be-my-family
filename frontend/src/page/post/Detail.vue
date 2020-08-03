<template>
<div class="container" style="width:40%;">
  <v-card
    class="mx-auto"
    width="95%"
    height="100%"
  >
    <v-toolbar
      flat
      color="blue-grey"
      dark
    >
      <v-toolbar-title>상세게시판</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <img :src="dogData.popfile" alt="dog images" width="100%" height="400px">

      <!-- <v-text-field filled label="Title" value="My new post"></v-text-field> -->
      <v-row>
          <v-col col="12" class="font-weight-black">구조일</v-col>
          {{ this.dogData.noticesdt }}
      </v-row>
      <v-row>
          <v-col col="12" class="font-weight-black">구조장소</v-col>
          {{ this.dogData.happenplace }}
      </v-row>
      <v-row>
          <v-col col="6" class="font-weight-black">견종</v-col>
          {{ this.dogData.kindcd }}
          <v-col col="6" class="font-weight-black">성별</v-col>
          {{ this.dogData.sexcd}}
      </v-row>
      <v-row>
          <v-col col="6" class="font-weight-black">연령</v-col>
          {{ this.dogData.age }}
          <v-col col="6" class="font-weight-black">모색</v-col>
          {{ this.dogData.colorcd }}
      </v-row>
      <v-row>
          <v-col col="6" class="font-weight-black">중성화 여부</v-col>
          {{ this.dogData.neuteryn }}
          <v-col col="6" class="font-weight-black">체중</v-col>
          {{ this.dogData.weight }}
      </v-row>
      <v-row>
          <v-col col=12 class="font-weight-black">특징</v-col>
          {{ this.dogData.specialmark }}
      </v-row>

      <v-divider class="my-2"></v-divider>
    </v-card-text>

    <v-card-actions class="d-flex justify-center">
      
      <v-btn
        color="success"
        depressed
      >
        관심이써여~
      </v-btn>
      <v-btn
        color="primary"
        class="ma-2"
        dark
        @click="dialog = true"
      >
        입양신청
      </v-btn>
      <v-dialog
        v-model="dialog"
        max-width="500px"
      >
        <v-card>
          <v-card-title>
            입양신청서
          </v-card-title>
          <v-card-text>
            <v-btn
              color="primary"
              dark
              @click="dialog2 = !dialog2"
            >
              Open Dialog 3
            </v-btn>
          </v-card-text>
          <v-card-actions>
            <v-btn
              color="primary"
              text
              @click="dialog = false"
            >
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog
        v-model="dialog2"
        max-width="500px"
      >
        <v-card>
          <v-card-title>
            <span>Dialog 3</span>
            <v-spacer></v-spacer>
            <v-menu
              bottom
              left
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  icon
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon>mdi-dots-vertical</v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item
                  v-for="(item, i) in items"
                  :key="i"
                  @click="() => {}"
                >
                  <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-card-title>
          <v-card-actions>
            <v-btn
              color="primary"
              text
              @click="dialog2 = false"
            >
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-card-actions>

  </v-card>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState, mapActions } from 'vuex'

export default {
    name:'detail',
    data(){
        return {
          dogData: {
            age: '',
            careaddr:'',
            carenm: '',
            caretel: '',
            chargenm: '',
            colorcd: '',
            desertionno: '',
            filename: '',
            happendt: '',
            happenplace: '',
            kindcd: '',
            neuteryn: '',
            noticecomment: '',
            noticeno: '',
            noticesdt: '',
            numofrows: '',
            officetel: '',
            orgnm: '',
            pageno: '',
            popfile: '',
            processstate: '',
            resultcode: '',
            resultmsg: '',
            sexcd: '',
            specialmark: '',
            totalcount: '',
            weight: '',
          },
          dialog: false,
          dialog2: false,
          items: [
            {
              title: 'Click Me',
            },
            {
              title: 'Click Me',
            },
            {
              title: 'Click Me',
            },
            {
              title: 'Click Me 2',
            },
          ],
          select: [
            { text: 'State 1' },
            { text: 'State 2' },
            { text: 'State 3' },
            { text: 'State 4' },
            { text: 'State 5' },
            { text: 'State 6' },
            { text: 'State 7' },
          ],
        }
    },
    computed:{
      ...mapState(['profileData'])
    },
    created(){
      var token = this.$cookies.get('auth-token')
      this.find(token.email)
      axios.get(`http://localhost:8080/care/detail?desertionNo=${this.$route.params.desertionno}`)
          .then( response => {
              // this.dogs = response.data.message
              console.log(response)
              this.dogData = response.data.object
          })
          .catch( error => {
              console.log(error)
          })
    },
    methods:{
      ...mapActions(['find'])
    }
}
</script>

<style>

</style>