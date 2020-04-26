<template>
<div class="modGroup">
  <h1>Módosítsd a csoportodat!</h1>
  <br>
  <br>
  <h2>Melyik csoportodat szeretnéd módosítani?</h2>
    <select v-model="selected" :options='group' >
      <option disabled value="">Válaszd ki a csoportot:</option>
      <option v-for="i in group" v-bind:key='i' v-bind:value='i'  >{{i}}</option>
    </select>
    <br>

    <h2>Adj hozzá egy új csoporttagot!</h2>
    <br>
    <select v-model="selected" multiple>
      <option disabled value="" >Válaszd ki, kiket akarsz hozzáadni a csoportodhoz!</option>
      <option v-for="i in users" v-bind:key='i' v-bind:value='i'  >{{i.email}}</option>
    </select>
    <br>
    <form @submit="submit1()" onsubmit = "return false">
      <button type="submit">Ember hozzáadása</button>
    </form>

      <h2>Törölj egy csoporttagot!</h2>
      <br>
    <select v-model="selected" multiple>
      <option disabled value="">Válaszd ki, kit szeretnél törölni!</option>
      <option v-for="i in members" v-bind:key='i' v-bind:value='i'  >{{i.email}}</option>
    </select>
    <br>
    <form @submit="submit2()" onsubmit = "return false">
      <button type="submit">Ember törlése</button>
    </form>

    <h2>Adj másnak is admmin jogot!</h2>
    <br>
    <select name="" id="" multiple>
      <option disabled value="">Válaszd ki, kinek akarsz admin jogot adni!</option>
       <option>...</option>
    </select>
        <form @submit="submit3()" onsubmit = "return false">
      <button type="submit">Felruházás admin joggal</button>
    </form>
    <br>
    <h2>AVedd el valaki admin jogát!</h2>
    <br>
    <select name="" id="" multiple>
      <option disabled value="">Válaszd ki, kinek az admin jogát akarod elvenni!</option>
      <option>...</option>
    </select>
    <form @submit="submit4()" onsubmit = "return false">
      <button type="submit">Admin jog elvétele</button>
    </form>
</div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'modGroup',

  computed: {
    ...mapState(['token']),
    ...mapState('modGroup', ['group', 'users', 'members', 'admins'])
  },

  methods: {
    ...mapActions('modGroup', ['getGroup', 'getUsers', 'addMembers', 'delMembers', 'addAdmin', 'delAdmin']),
    submit1 () {
      this.addMembers({ token: this.token, memberList: this.selected })
    },
    submit2 () {
      this.delMembers({ token: this.token, memberList: this.selected })
    },
    submit3 () {
      this.addAdmin({ token: this.token, adminList: this.selected })
    },
    submit4 () {
      this.delAdmin({ token: this.token, adminList: this.selected })
    }
  },

  data () {
    return {
      selected: []
      // select: ''
    }
  },
  created () {
    this.getGroup({ token: this.token })
    this.getUsers({ token: this.token })
    this.addMembers({ token: this.token })
    this.delMembers({ token: this.token })
    this.addAdmin({ token: this.token })
    this.delAdmin({ token: this.token })
  },

  watch: {
    selected () {
      this.getGroup({ id: this.selected, token: this.token })
      // this.getUsers({ id: this.selected, token: this.token })
    }
  }
}
</script>
