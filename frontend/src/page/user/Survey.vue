<template>
  <div class="mt-5 ml-5 pt-5">
    <v-container>
    <div class="d-flex mb-5">
    <i class="fas fa-pencil-alt" style="font-size:30px"></i><h2 class="ml-3">설문을 작성해주세요.</h2>
      </div> 
    <form>
      1. 성명:
    <v-text-field
      v-model="name"
      :error-messages="nameErrors"
      :counter="10"
      label="Name"
      required
      @input="$v.name.$touch()"
      @blur="$v.name.$touch()"
    ></v-text-field>
    2. Email:
    <v-text-field
      v-model="email"
      :error-messages="emailErrors"
      label="E-mail"
      required
      @input="$v.email.$touch()"
      @blur="$v.email.$touch()"
    ></v-text-field>
    3. Address:
    <v-text-field
      v-model="email"
      :error-messages="emailErrors"
      label="Address"
      required
    ></v-text-field>
    4. Age:
    <v-text-field
      v-model="email"
      :error-messages="emailErrors"
      label="Age"
      required
    ></v-text-field>
    5. Opinoin
    <v-textarea label="자유롭게 의견을 적어주세요"></v-textarea>
    6. 강아지를 키우신 경험이 있으신가요? (구체적으로 적어주세요)
    <v-textarea label="구체적으로 적어주세요"></v-textarea>
    7. 입양을 원하시는 이유가 있으신가요? (구체적으로 적어주세요)
    <v-textarea label="구체적으로 적어주세요"></v-textarea>
    <v-checkbox
      v-model="checkbox"
      :error-messages="checkboxErrors"
      label="Do you agree?"
      required
      @change="$v.checkbox.$touch()"
      @blur="$v.checkbox.$touch()"
    ></v-checkbox>

    <v-btn class="mr-4" @click="submit">submit</v-btn>
    <v-btn @click="clear">clear</v-btn>
  </form>
  </v-container>
  </div>
  
</template>

<script>
import constants from "@/lib/constants";
import SERVER from "@/lib/constants";
import axios from "axios";

export default {
    name: "survey"
}
</script>

<style>

</style>