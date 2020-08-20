<template>
  <div class="footer" style="padding:10px 100px;width:100%; margin-top:5rem; height:10vh; box-shadow:2px 2px 3px rgba(0,0,0,0.3); background-color:#FFF8DC;" v-if="isFooter">
          <v-row>
      <div class="justify" style="transform:translatex(600px); margin-top:0.5rem;">
      <h3><i class="fas fa-paw pl-3"></i><span class="ml-2">Be My Family</span></h3>
      <h5 style="color:#808080; margin-left:12px;">강아지 사지말고 입양하세요</h5>
      </div>

      <v-col>
      <div  class="d-flex justify-end" >
      <h5 style="color:#808080;">@hunsung</h5>
      <h5 class="pl-1" style="color:#808080;">@bogeun</h5>
      </div>
      <div  class="d-flex justify-end">
      </div>
      <div  class="d-flex justify-end">
      <h5 style="color:#808080;">@junki</h5>
      <h5 class="pl-1" style="color:#808080;">@jinsil</h5>
      <h5 class="pl-1" style="color:#808080;">@minju</h5>
      </div>      
      <div  class="d-flex justify-end">
      </div>
      <div  class="d-flex justify-end">
      </div>
      </v-col>
          </v-row>
  </div>
</template>

<script>
import constants from '@/lib/constants';

export default {
    name:'Footer',
    component: {
        constants,
    },
    props: ["isFooter"],
    data:function () {
        return {
            constants,
        }
    }
}
</script>

<style>
.footer{
  /* height: 80px; */
  /* position:absolute; */
   bottom:0;
  z-index:1;
}
</style>