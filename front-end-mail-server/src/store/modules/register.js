import api from '@/api'

const state = {
  user: null,
  token: null
}

const mutations = {
  login(state, { user, token }) {
    state.user = user
    state.token = token
  },
  signup(state, { user, token }) {
    state.user = user
    state.token = token
  }
}

const actions = {
  async login({ commit }, { email, password }) {
    const token = await api.auth.login(email, password)
    const user = await api.auth.getUser(token)

    commit('login', { user, token })
  },
  async signup({ commit }, { name, email, password }) {
    const token = await api.auth.signup(name, email, password)
    const user = await api.auth.getUser(token)

    commit('signup', { user, token })
  }
}

const getters = {
  user: (state) => state.user,
  token: (state) => state.token,
  isAuthenticated: (state) => state.token != null
}

export default {
  state,
  mutations,
  actions,
  getters
}
