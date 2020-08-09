<template>
  <v-form> 
      <v-container >
        <v-col cols="10" style="margin:0 auto;">
        <div class="d-flex">
        <h2 style="margin:0 auto;"><i class="fas fa-pencil-alt mr-2" style="font-size:30px"></i>입양후기 작성</h2>
        </div>
        <v-row>
        <v-file-input
            class="mt-5"
            :rules="rules"
            accept="image/png, image/jpeg, image/bmp"
            placeholder="Pick an avatar"
            prepend-icon="mdi-camera"
            multiple
            label="사진"
        ></v-file-input>
        </v-row>
          <v-row class="mt-5"> 제목 </v-row> 
            <v-row> 
            <v-text-field 
                :counter="50" 
                label="제목" 
                name="title" 
                required 
                maxlength="50" 
            ></v-text-field> 
            </v-row> 
       
                <v-row align="center">
                    <v-col cols="10" sm="1">
                        시,도
                    </v-col>
                    <v-col cols="10" sm="4">
                        <v-select
                        v-model="sido"
                        :items="sido_states"
                        hint="시/도를 선택하세요"
                        persistent-hint
                        ></v-select>
                    </v-col>

                    <v-col cols="10" sm="1">
                        지역
                    </v-col>

                    <v-col cols="10" sm="4">
                        <v-select
                        v-model="gugun"
                        :items="gugun_states[sido]"
                        hint="거주 지역을 선택하세요"
                        persistent-hint
                        ></v-select>
                    </v-col>
                    </v-row>
   

            <v-row align="center">
               <v-col cols="12" sm="1">
                        품종:
                </v-col>
                <v-col cols="12" sm="4">
                <v-select
                    :items="items"
                    :menu-props="{ bottom: true, offsetY: true }"
                    hint="품종을 선택하세요"
                    persistent-hint
                ></v-select>
                </v-col>
            </v-row>

              <v-row class="mt-5"> 내용 </v-row> 
                <v-row> 
                <v-textarea 
                    filled name="context" 
                    hint="내용을 입력해주세요." 
                     
                ></v-textarea> 
                </v-row> 

            
            
            <v-row>          
            <v-btn outlined color="red" style="margin-left: auto;"> 수정 </v-btn> 
            <v-btn outlined color="blue" style="margin-left: 5px;"> 등록 </v-btn> 
            </v-row> 
            </v-col>
        </v-container> 
    </v-form> 
</template>


<script>
export default {
    data () {
        return{
            rules: [
              // value => !value || value.size < 7000000000000 || 'Avatar size should be less than 2 MB!',
            ],
          sido: [],
          gugun: [],
          sido_states: [
            '서울특별시', '부산광역시', '인천광역시', '대전광역시',
            '대구광역시', '울산광역시', '광주광역시', '세종특별자치시',
            '경기도', '강원도', '충청북도','충청남도', '전라북도', '전라남도', 
            '경상북도','경상남도'
          ],
          gugun_states: {
            // 서울
            '서울특별시':['강서구', '양천구', '구로구', '영등포구','동작구','금천구','관악구',
            '서초구', '강남구', '송파구', '강동구','마포구','용산구','성동구','광진구',
            '서대문구', '중구', '동대문구','중랑구', '성북구', '종로구','은평구', 
            '강북구','노원구','도봉구'],
            // 부산
            '부산광역시':['해운대구','남구','동구','서구','북구','동래구','사하구'],
            // 인천
            '인천광역시':['서구','계양구','동구','미추홀구','연수구','부평구','중구','강화군'],
            // 대전
            '대전광역시':['서구','중구','동구','유성구','대덕구'],
            // 대구
            '대구광역시':['남구','수성구','달서구','북구'],
            // 울산
            '울산광역시':['북구','동구','중구','남구'],
            // 광주
            '광주광역시':['서구','남구','동구','광산구'],
            // 경기도
            '경기도':['수원시','성남시','의정부시','안양시','부천시','광명시','평택시',
            '동두천시','안산시','고양시','과천시','구리시','남양주시','오산시',
            '시흥시','군포시','의왕시','하남시','용인시','파주시','이천시','김포시',
            '광주시','양주시','포천시','안성시'],
            // 강원도
            '강원도':['철원군','화천구','양구군','고성군','춘천시','인제군','속초시',
            '홍천군','양양군','원주시','횡성군','평창군','강릉시','영월군',
            '정선군','동해시','태백시','삼척시'],
            // 충청북도
            '충청북도':['청주시','충추시','제천시','보은군','옥천군','영동군','증편군',
            '진천군','괴산군','음성군','단양군'],
            // 충청남도
            '충청남도':['태안군','서산시','당진시','아산시','천안시','홍성군','예산군',
            '보령시','청양군','공주시','서천군','부여군','논산시','계룡시','금산군'],
            // 전라북도
            '전라북도':['군산시','익산시','김제시','전주시','남원시','정읍시','무주군','장수군',
            '부안군','진안군','임실군','고창군','순창군','완주군'],
            // 전라남도
            '전라남도':['목포시','여수시','순천시','나주시','광양시','담양군','곡성군','구례군',
            '고흥군','보성군','화순군','장흥군','강진군','해남군','영암군','무안군',
            '함평군','영광군','장성군','완도군','진도군','신안군'],
            // 경상북도
            '경상북도':['포항시','경주시','김천시','안동시','구미시','영주시','영천시','상주시',
            '문경시','경산시','군위군','의성군','청송군','영양군','영덕군','예천군',
            '봉화군','울진군','울룽군'],
            //경상남도
            '경상남도':['창원시','진주시','통영시','사천시','김해시','밀양시','거제시','양산시',
            '의령군','함안군','창녕군','고성군','남해군','하동군','산청군','함양군',
            '거창군','합천군'],
          },
          items: ['믹스', '포메', '푸들', '치와와'],
        }
        
    },

}
</script>

<style>

</style>