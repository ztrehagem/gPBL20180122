<template lang="pug">
.vue-app-root
  h1.title The Application
  hr
  form.form
    div
      label ID
        input-box._ml-10(type="number" placeholder="00000")
    div
      submit-button
</template>

<script>
import InputBox from './input-box.vue';
import SubmitButton from './submit-button.vue';

export default {
  components: {
    InputBox,
    SubmitButton,
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

    > * {
      margin-bottom: 1rem;
    }
  }
}
</style>
