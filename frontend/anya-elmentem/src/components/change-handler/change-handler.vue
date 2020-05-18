<template>
    <div>
        <p> {{change.user1.email}} </p>
        <p> from: {{change.todo1.fromDate}} to: {{change.todo1.toDate}}</p>
        <p> {{change.todo1.chores}} </p>
        <p> from: {{change.todo2.fromDate}} to: {{change.todo2.toDate}}</p>
        <p> {{change.todo2.chores}} </p>
        <img src="../../assets/img_254317.png" @click="deleteThis()">
        <img src="../../assets/accept.png" @click="acceptThis()">
    </div>
</template>
<script>
export default {
  name: 'changeHandler',
  props: {
    change: {
      type: Object,
      required: true
    }

  },
  methods: {
    deleteThis () {
      this.$emit('delete')
    },
    acceptThis () {
      this.$emit('accept')
    }
  }
}
</script>
<style>
img{
  max-width: 32px;
  max-height: 32px;
}
</style>
