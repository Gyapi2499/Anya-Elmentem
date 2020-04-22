<template>
  <div class="newTodo">
    <h2>Melyik csoportomnak szerétnék feladatot adni?</h2>
     <br>
            <select v-model="selected" :options='cats' >
              <option disabled value="">Please select one</option>
              <option v-for="i in group" v-bind:key='i' v-bind:value='i'>{{i.id}}</option>           
            </select>
    <br>
    <h2>Feladat nekem</h2>
    <form id="nekem" @submit="onSubmit()">
        <a>Feladat megnevezése:</a>
        <br>
        <!--<input v-model="message" placeholder="Add todo...">   --> <input type= "text " name= "title" placeholder="Add todo..."><br>
        <a> Feladat kezdete:</a> 
        <br>
        <datetime type="datetime" v-model="datetimeTheming" ></datetime>
        <br>
        <a> Feladat vége:</a> 
        <datetime type="datetime" v-model="datetime13" format="yyyy-MM-dd HH:mm:ss"></datetime> 
        <br>
        <!--<a>A feladat el van végezve?</a>        
        <input type="checkbox" id="checkbox" v-model="checked">
        <br>-->
        <input type='submit' value='submit'>
      
    </form>
    <!-- ez csak akkor ha admin-->
    <form id="masnak" @submit="onSubmit()" >
        <h2>Feladat másnak:</h2>
        <a>Feladat megnevezése:</a>
        <br>
        <input type= "text " name= "title" placeholder="Add todo..."> <br>
        <a> Feladat kezdete:</a> <datetime type="datetime" v-model="datetimeTheming" ></datetime>
        <br>
        <a> Feladat vége:</a>  <datetime type="datetime" v-model="datetimeTheming" ></datetime>
        <br>
        <a> Kit választok:</a>
        <br>
            <select v-model="selected" :options='cats' >
              <option disabled value="">Please select one</option>
              <option v-for="cat in memberList" v-bind:key='cat' v-bind:value='cat'>{{cat.email}}</option>           
            </select>
        <br>
        <!--<a>A feladat el van végezve?</a>        
        <input type="checkbox" id="checkbox" v-model="checked">
        <br>-->
        <input type='submit' value='submit'>
    </form>
  </div>
</template>

<script>
import {mapActions, mapState} from 'vuex'


export default {
  name: "newTodo",//ugynaz mint a file neve
  methods:{
    ...mapActions('newTodo',['getMemberList','addTodo','getGroup']),
    async save(){
      
    },
    onSubmit(){

    }
  },
  created(){
    this.getmemberList({user:this.user})

  },
  computed:{
    ...mapState(['user','Http']),//ez se tiszta
    ...mapState('newTodo',['memberList','group'])
  },
  
  
  
    
}
</script>

<style scoped>
#masnak{
    text-align: center;
    padding: 15px
}
h2{
  color:red;
}
h1{
  color:red;
  text-align:left;
}
a{
  color:white;
}
div{
  background-color: black;
}
</style>