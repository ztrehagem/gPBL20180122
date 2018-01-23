<template lang="pug">
.vue-app-root
  h1.title The Application
  hr
  form.form(@submit.prevent="submit")
    div
      label ID
        input-box._ml-10(type="number" placeholder="00000" v-model="id")
    div
      submit-button
  hr
  div {{this.response}}
</template>

<script>
import axios from 'axios';
import InputBox from './input-box.vue';
import SubmitButton from './submit-button.vue';

export default {
  components: {
    InputBox,
    SubmitButton,
  },
  data: () => ({
    submitting: false,
    id: 2,
    response: "",
  }),
  methods: {
    async submit() {
      this.submitting = true;
      console.log('request to get about', this.id);
      try {
        this.response = await axios.get(`/hello?id=${this.id}`);
      } catch (e) {
        this.response = e.response.data;
      }
    },
  },
}
</script>

<style lang="stylus">
@require "../variables.styl";

.vue-app-root {
  color: $black;

  > .title {
    padding: 0.5rem 1rem;
  }

  > .form {
    padding: 1rem;

    > * {
      margin-bottom: 1rem;
    }
  }
}
</style>
