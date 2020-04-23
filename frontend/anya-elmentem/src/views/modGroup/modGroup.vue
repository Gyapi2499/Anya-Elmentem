<template>
<div class="modGroup">
  <h1>Módosítsd a csoportodat!</h1>
  <br>
  <br>
  <h2>Melyik csoportodat szeretnéd módosítani?</h2>
    <select v-model="selected" :options='group' >
      <option disabled value="">Válaszd ki:</option>
      <option v-for="i in group" v-bind:key='i' v-bind:value='i'  >{{i}}</option>
    </select>
    <br>

    <h2>Adj hozzá egy új csoporttagot!</h2>
    <br>
    <select name="" id="" multiple>
      <option disabled value="">Válaszd ki, kit akarsz hozzáadni a csoportodhoz!</option>
      <option>...</option>
      <option><button>Add</button></option>
      </select>
      <br>
      <h2>Törölj egy csoporttagot!</h2>
      <br>
    <select name="" id="" multiple>
      <option disabled value="">Válaszd ki, kit szeretnél törölni!</option>
      <option>...</option>
      <option><button>Delete</button></option>
     </select>
        <br>
    <h2>Adj másnak is admmin jogot!</h2>
    <br>
    <select name="" id="" multiple>
      <option disabled value="">Válaszd ki, kinek akarsz admin jogot adni!</option>
       <option>...</option>
       <option><button>Submit</button></option>
    </select>
</div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'modGroup',

  computed: {
    ...mapState(['token']),
    ...mapState('modGroup', ['group', 'users'])
  },

  methods: {
    ...mapActions('modGroup', ['getGroup', 'getUsers']),
    submit () {
    }
  },

  data () {
    return {
      selected: '',
      select: ''
    }
  },
  created () {
    this.getGroup({ token: this.token })
    this.getUsers({ token: this.token })
  },

  watch: {
    selected () {
      this.getGroup({ id: this.selected, token: this.token })
      this.getUsers({ id: this.selected, token: this.token })
    }
  }
}
</script>
