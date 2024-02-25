import ApiService from './ApiService'

class EmailService {
  static instance

  static getInstance() {
    if (!EmailService.instance) {
      EmailService.instance = new EmailService()
    }
    return EmailService.instance
  }

  async sendEmail(formdata) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${formdata.get('token')}`,
      'Content-Type': 'application/json'
    }

    return await service.makeRequest('email/send', 'POST', formdata, false, {})
  }
  // receivers
  async deleteEmail(token, id) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }

    return await service.makeRequest(`email/delete?email_id=${id}`, 'DELETE', null, true, headers)
  }

  async draftEmail(formdata) {
    const service = ApiService.getInstance()

    return await service.makeRequest('draftemail', 'POST', formdata, false, {})
  }

  async updateDraftEmail(formdata) {
    const service = ApiService.getInstance()

    return await service.makeRequest('updatedraft', 'PUT', formdata, false, {})
  }

  async submitDraftEmail(formdata) {
    const service = ApiService.getInstance()

    return await service.makeRequest('submitdraft', 'POST', formdata, false, {})
  }

  async moveMail(token, id, foldername) {
    const service = ApiService.getInstance()

    return await service.makeRequest('movemails', 'PUT', {
      token,
      id,
      foldername
    })
  }

  async restoreMail(token, id) {
    const service = ApiService.getInstance()

    return await service.makeRequest('restore', 'PUT', {
      token,
      id
    })
  }
}

export default EmailService
