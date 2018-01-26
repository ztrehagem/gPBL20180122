<template lang="pug">
.navigation-box
  router-link.link-button(:class="{'-active': $route.name == 'root'}" :to="{ name: 'root' }") HOME
  router-link.link-button(:class="{'-active': $route.name == 'graph'}" :to="{ name: 'graph' }") GRAPH
  router-link.link-button(:class="{'-active': $route.name == 'table'}" :to="{ name: 'table', params: { range: 'all' } }") TABLE
</template>

<script>
export default {
}
</script>

<style lang="stylus">
</style>
