<template lang="pug">
.vue-app-root
  h1.title GPBL 20180122 Application
  hr
  navigation-box
  hr
  router-view
</template>

<script>
import NavigationBox from './navigation-box.vue';

export default {
  components: {
    NavigationBox,
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
}
</style>
