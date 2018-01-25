<template lang="pug">
.vue-app-root
  h1.title GPBL 20180122 Application
  hr
  form.form(@submit.prevent="submit")
    div
      label ID
        input-box._ml-10(type="number" placeholder="0000" v-model="id")
      submit-button._ml-10(:disabled="this.submitting")
  hr
  div {{this.response}}
  hr
  graph-box(:graph-data="graphData")
</template>

<script>
import InputBox from './input-box.vue';
import SubmitButton from './submit-button.vue';
import GraphBox from './graph-box.vue';
import * as api from '../modules/api';
import * as xmlParser from '../modules/xml-parser';

const mock = [
  {
    range: 'under 19',
    number: 20,
    predicted: 10,
  }, {
    range: '20-29',
    number: 100,
    predicted: 30,
  }, {
    range: '30-39',
    number: 80,
    predicted: 10,
  }, {
    range: '40-49',
    number: 65,
    predicted: 37,
  }, {
    range: '50-59',
    number: 30,
    predicted: 20,
  }, {
    range: '60+',
    number: 10,
    predicted: 10,
  },
];

export default {
  components: {
    InputBox,
    SubmitButton,
    GraphBox,
  },
  data: () => ({
    submitting: false,
    id: 6338,
    // response: "",
  }),
  methods: {
    async submit() {
      this.submitting = true;
      const [error, xml] = await api.get(this.id);
      if (error) {
        console.error(error);
      } else {
        this.response = xml;
        // this.graphData = await xmlParser.parse(xml);
        this.graphData = mock;
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
  }
}
</style>
