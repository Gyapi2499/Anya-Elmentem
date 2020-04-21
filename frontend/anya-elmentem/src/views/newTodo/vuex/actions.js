import todoApi from '../../../api/todo-api'
import groupApi from '../../../api/group-api'
import userApi from '../../../api/user-api'

export default {
    getMemberList({commit},params){
        console.log(params)
        groupApi.getMemberList(params).then(response=>{
            commit('setMemberList',response.data);
        }).catch(error=>{
            console.log(error);
        });
    },
   getGroups({commit},params){
        console.log(params)
        userApi.getGroup(params).then(response=>{
            commit('setMemberList',response.data);
        }).catch(error=>{
            console.log(error);
        });
    },
    addTodo(commit,params){
        return todoApi.add(params)
    }

    

    



}