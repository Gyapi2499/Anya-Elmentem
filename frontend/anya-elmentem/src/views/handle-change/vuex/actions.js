import changeApi from '../../../api/change-api'

export default {
  getAll ({ commit }, params) {
    changeApi.getChange(params).then(response => {
      commit('setChanges', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  accept ({ commit }, params) {
    changeApi.accept(params).then(() => {
      commit('splitChanges', params.id)
    }).catch(error => {
      console.log(error)
    })
  },
  decline ({ commit }, params) {
    changeApi.accept(params).then(() => {
      commit('splitChanges', params.id)
    }).catch(error => {
      console.log(error)
    })
  }
}
