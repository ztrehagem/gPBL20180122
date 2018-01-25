<template lang="pug">
.vue-app-root
  h1.title The Application
  hr
  form.form(@submit.prevent="submit")
    div
      label ID
        input-box._ml-10(type="number" placeholder="0000" v-model="id")
      submit-button._ml-10(:disabled="this.submitting")
  hr
  div {{this.response}}
</template>

<script>
import xml2js from 'xml2js';
import InputBox from './input-box.vue';
import SubmitButton from './submit-button.vue';
import * as api from '../modules/api';

export default {
  components: {
    InputBox,
    SubmitButton,
  },
  data: () => ({
    submitting: false,
    id: 6338,
    response: "",
  }),
  methods: {
    async submit() {
      this.submitting = true;
      const [error, xml] = await api.get(this.id);
      if (error) {
        console.error(error);
      } else {
        console.log(xml);
      }
      this.submitting = false;
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
