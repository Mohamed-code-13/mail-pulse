import ApiService from './ApiService'

class ContactsService {
  static instane

  static getInstance() {
    if (!ContactsService.instane) {
      ContactsService.instane = new ContactsService()
    }
    return ContactsService.instane
  }

  async getContacts(token) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }

    return await service.makeRequest('contacts/getcontacts', 'GET', null, true, headers)
    // return await service.makeRequest(`contacts?token=${token}&sort=0`, 'GET')
  }

  async createContact(token, name, emails) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
    const body = {
      name,
      emails
    }

    return await service.makeRequest('contacts/createcontact', 'POST', body, true, headers)
    // return await service.makeRequest('createcontact', 'POST', {
    //   token,
    //   name,
    //   emails
    // })
  }

  async deleteContact(token, contactids) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }

    return await service.makeRequest(
      `contacts/deletecontact?contactids=${contactids}`,
      'DELETE',
      null,
      true,
      headers
    )
    // return await service.makeRequest('deletecontacts', 'DELETE', {
    //   token,
    //   contactIds
    // })
  }

  async updateContact(token, contactId, name, emails) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    }
    const body = {
      contactId,
      name,
      emails
    }

    return await service.makeRequest('contacts/updatecontact', 'PUT', body, true, headers)
    // return await service.makeRequest('updatecontact', 'PUT', {
    //   token,
    //   contactId,
    //   name,
    //   emails
    // })
  }
}

export default ContactsService
