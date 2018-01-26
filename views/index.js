import Vue from 'vue';
import VueRouter from 'vue-router'
import VueAppRoot from './components/vue-app-root.vue';
import RootPage from './components/root-page.vue';
import GraphPage from './components/graph-page.vue';
import TablePage from './components/table-page.vue';

Vue.use(VueRouter);

const app = new Vue({
  el: '#vue-app-root',
  template: '<vue-app-root></vue-app-root>',
  components: {
    VueAppRoot,
  },
  router: new VueRouter({routes: [
    { name: 'root', path: '/', component: RootPage },
    { name: 'graph', path: '/graph', component: GraphPage },
    { name: 'table', path: '/table/:range', component: TablePage },
  ]}),
});
