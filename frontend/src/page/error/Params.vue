<template>
  <div class="text-center">
    <h1>요청하신 페이지를 찾을 수 없습니다.</h1>
    <h2 v-if="$route.params.name!=null">{{ $route.params.name }}</h2>
        <br>
    <a v-if="$route.params.name==null" href="http://i3b201.p.ssafy.io/api">메인으로</a>
  </div>
</template>