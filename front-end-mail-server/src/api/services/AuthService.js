import ApiService from './ApiService'

class AuthService {
  static instane

  static getInstance() {
    if (!AuthService.instane) {
      AuthService.instane = new AuthService()
    }
    return AuthService.instane
  }

  async login(email, password) {
    const service = ApiService.getInstance()
    return await service.makeRequest('register/login', 'POST', { email, password })
  }

  async signup(name, email, password) {
    const service = ApiService.getInstance()
    await service.makeRequest('register/signup', 'POST', { name, email, password })
    return await this.login(email, password)
  }

  async logout(token) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`register/logout?token=${token}`, 'DELETE')
  }

  async getUser(token) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }

    return await service.makeRequest('home/getuser', 'GET', null, true, headers)
  }
}

export default AuthService
