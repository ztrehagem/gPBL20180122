<template lang="pug">
.graph-page
  div(v-show="error") {{error}}
  graph-box(v-show="graphData" :graph-data="graphData")
</template>

<script>
import * as api from '../modules/api';
import * as xmlParser from '../modules/xml-parser';
import GraphBox from './graph-box.vue';

export default {
  components: {
    GraphBox,
  },
  data: () => ({
    error: null,
    graphData: null,
  }),
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      this.error = null;
      const [error, xml] = await api.getGraph();
      if (error) {
        this.error = error;
      } else {
        this.showGraphFromXml(xml);
      }
    },
    async showGraphFromXml(xml) {
      try {
        this.graphData = await xmlParser.parse(xml);
      } catch (e) {
        this.error = e;
      }
    },
  },
}
</script>

<style lang="stylus">
</style>
