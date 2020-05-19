export default {
  setChanges (state, changes) {
    state.changes = changes
  },
  splitChanges (state, change) {
    console.log(change)
    state.changes = state.changes.filter(el => el.id.toString() !== change.toString())
  }
}
