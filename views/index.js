import Vue from 'vue';
import VueAppRoot from './components/vue-app-root.vue';

const app = new Vue({
  el: '#vue-app-root',
  template: '<vue-app-root></vue-app-root>',
  components: {
    VueAppRoot,
  },
});
