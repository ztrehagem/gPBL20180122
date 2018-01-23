<template lang="pug">
input(
  :value="value"
  @input="input($event.target.value)"
  :type="type"
  :placeholder="placeholder")
</template>

<script>
export default {
  props: {
    value: { default: null },
    type: { type: String, default: "text" },
    placeholder: { type: String, default: "" },
  },
  methods: {
    input(value) {
      this.$emit('input', value);
    },
  },
}
</script>

<style lang="stylus">
</style>
