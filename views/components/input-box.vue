<template lang="pug">
input(:type="type" :placeholder="placeholder")
</template>

<script>
export default {
  props: {
    type: { type: String, default: "text" },
    placeholder: { type: String, default: "" },
  },
}
</script>

<style lang="stylus">
</style>
