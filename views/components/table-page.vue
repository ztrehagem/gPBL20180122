<template lang="pug">
.table-page
  .nav
    router-link.link-button(:class="{'-active': $route.params.range == 'all'}" :to="{ name: 'table', params: { range: 'all' } }") all
    router-link.link-button(:class="{'-active': $route.params.range == 'young'}" :to="{ name: 'table', params: { range: 'young' } }") under 20
    router-link.link-button(:class="{'-active': $route.params.range == 'mid'}" :to="{ name: 'table', params: { range: 'mid' } }") 20-29
    router-link.link-button(:class="{'-active': $route.params.range == 'old'}" :to="{ name: 'table', params: { range: 'old' } }") 30+
  table-box(v-show="tableData" :table-data="tableData")
</template>

<script>
import TableBox from './table-box.vue';
import * as xmlParser from '../modules/xml-parser';
import * as api from '../modules/api';

export default {
  components: {
    TableBox,
  },
  data: () => ({
    error: null,
    tableData: null,
  }),
  computed: {
    isHere() {
      return this.$route.name == 'table';
    }
  },
  watch: {
    $route() {
      this.fetchData();
    },
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      if (!this.isHere) return;
      this.error = null;
      const [error, xml] = await api.getTable(this.$route.params.range);
      if (error) {
        this.error = error;
      } else {
        this.showTableFromXml(xml);
      }
    },
    async showTableFromXml(xml) {
      try {
        this.tableData = await xmlParser.parse(xml);
      } catch (e) {
        this.error = e;
      }
    },
  },
}
</script>

<style lang="stylus">
.table-page {
  > .table-box {
    margin: 1rem;
  }
}
</style>
