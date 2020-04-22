<template>
    <div class="modGroup">
        <h2>Melyik csoportodat szeretnéd módosítani?</h2>
        <br>
        <select v-model="selected" :options='cats' >
            <option disabled value="">Válaszd ki:</option>
            <option v-for="i in group" v-bind:key='i' v-bind:value='i'>{{i.id}}</option>           
        </select>
        <br>

        <h2>Válaszd ki a csoportod a tagjait!</h2>
        <br>
        <select name="" id="" multiple>
	        <!-- <option disabled value="">Válaszd ki kit szeretnél hozzáadni a csoportodat!</option> -->
            <option>...</option>
            <option><button>Add</button></option>
        </select>
        <br>
            
        <h2>Törölj egy csoporttagot!</h2>
        <br>
        <select name="" id="" multiple>
	        <option disabled value="">Válaszd ki kit szeretnél törölni!</option>
            <option>...</option>
            <option><button>Delete</button></option>
        </select>
        <br>

         <h2>Adj másnak is admmin jogot!</h2>
        <br>
        <select name="" id="" multiple>
	        <option disabled value="">Válaszd ki kinek akarsz admin jogot adni!</option>
            <option>...</option>
            <option><button>Submit</button></option>
        </select>
    </div>
</template>
