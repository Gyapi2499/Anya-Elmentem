import groupApi from '../../../api/group-api'
import userApi from '../../../api/user-api'

export default {
    getUserList({commit},params){
        console.log(params)
        groupApi.getUserList(params).then(response=>{
            commit('setUserList',response.data);
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
    addGroup(commit,params){
        return groupApi.add(params)
    }
}
    