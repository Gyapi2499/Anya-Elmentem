<template>
<div>
  <label class="typo__label">Simple select / dropdown</label>
  <multiselect v-model="value" :options="options" :multiple="true" :close-on-select="false" :clear-on-select="false" :preserve-search="true" placeholder="Pick some" label="name" track-by="name" :preselect-first="true">
    <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">{{ values.length }} options selected</span></template>
  </multiselect>
  <pre class="language-json"><code>{{ value  }}</code></pre>
</div>
</template>

<script>
import Multiselect from 'vue-multiselect'
import { mapActions, mapState } from 'vuex'

export default {
  components: {
    Multiselect
  },
  data () {
    return {
      options: [],
      value: []
    }
  },
  created () {
    this.getUsers({ token: this.token })
  },
  watch: {
    users () {
      this.options = this.users
    }
  },
  computed: {
    ...mapState(['token']),
    ...mapState('addGroup', ['users'])
  },
  methods: {
    submit () {
      this.addGroup({ token: this.token, memberList: this.selected })
    },
    ...mapActions('addGroup', ['addGroup', 'getUsers'])
  }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

<style scoped>
</style>
