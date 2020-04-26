<template>
  <div>
    <b-card id="error_message" bg-variant="danger" text-variant="white">
    <b-icon-exclamation-circle></b-icon-exclamation-circle> {{message}}
    </b-card>
  </div>
</template>

<script>
export default {
  name: 'ErrorMessage',
  props: {
    message: String
  }
}
</script>

<style scoped>
.card-body{
  padding: 10px;
}

#error_message{
  margin-bottom: 20px;
}

</style>
