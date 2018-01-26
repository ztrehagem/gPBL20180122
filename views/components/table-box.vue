<template lang="pug">
table.table-box
  thead
    tr
      th will quit
      th ID
      th age
      th address
  tbody
    tr(v-for="e in employees")
      td {{e.quit}}
      td {{e.id}}
      td {{e.age}}
      td {{e.addr}}
</template>

<script>
export default {
  props: {
    tableData: { default: null },
  },
  computed: {
    employees() {
      return this.tableData && this.tableData.root.employee;
    }
  },
}
</script>

<style lang="stylus">
@require "../variables";

.table-box {
  border-collapse: collapse;
  border: 2px solid $black;

  td, th {
    padding: 0.5rem;
    border: 1px solid $gray;
  }

  th {
    border-bottom: 2px solid $black;
  }
}
</style>
