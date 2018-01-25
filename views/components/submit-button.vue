<template lang="pug">
button.submit-button(type="submit" :disabled="disabled") submit
</template>

<script>
export default {
  props: {
    disabled: { type: Boolean, default: false },
  },
}
</script>

<style lang="stylus">
.submit-button {
  padding-left: 3rem;
  padding-right: @padding-left;
}
</style>
