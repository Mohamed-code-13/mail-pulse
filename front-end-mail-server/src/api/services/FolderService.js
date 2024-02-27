import ApiService from './ApiService'

class FolderService {
  static instane

  static getInstance() {
    if (!FolderService.instane) {
      FolderService.instane = new FolderService()
    }
    return FolderService.instane
  }

  async getInbox(token, sort, page) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`
    }

    return await service.makeRequest(
      `home/inbox?sort=${sort}&page=${page}`,
      'GET',
      null,
      true,
      headers
    )
    // return await service.makeRequest(`inbox?token=${token}&sort=${sort}&required=${page}`, 'GET')
  }

  async getSent(token, sort, page) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`
    }

    return await service.makeRequest(
      `home/sent?sort=${sort}&page=${page}`,
      'GET',
      null,
      true,
      headers
    )
    // return await service.makeRequest(`sent?token=${token}&sort=${sort}&required=${page}`, 'GET')
  }

  async getTrash(token, sort, page) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`
    }

    return await service.makeRequest(
      `home/trash?sort=${sort}&page=${page}`,
      'GET',
      null,
      true,
      headers
    )
    // return await service.makeRequest(`trash?token=${token}&sort=${sort}&required=${page}`, 'GET')
  }

  async getDraft(token, sort, page) {
    const service = ApiService.getInstance()
    return await service.makeRequest(`draft?token=${token}&sort=${sort}&required=${page}`, 'GET')
  }

  async getFolders(token) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`
    }

    return await service.makeRequest(`folders/getfolders`, 'GET', null, true, headers)
    // return await service.makeRequest(`folders/getfolders?token=${token}`, 'GET')
  }

  async createFolder(token, foldername) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`,

      'Content-Type': 'application/json'
    }
    const body = {
      foldername
    }

    return await service.makeRequest(`folders/createfolder`, 'POST', body, true, headers)
    // return await service.makeRequest('createfolder', 'POST', {
    //   token,
    //   foldername
    // })
  }

  async getFolderEmails(token, sort, foldername, page) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`
    }

    return await service.makeRequest(
      `folders/folderemails?token=${token}&foldername=${foldername}&sort=${sort}&page=${page}`,
      'GET',
      null,
      true,
      headers
    )
    // return await service.makeRequest(
    //   `folderemails?token=${token}&foldername=${foldername}&sort=${sort}&required=${page}`,
    //   'GET'
    // )
  }

  async deleteFolder(token, foldername) {
    const service = ApiService.getInstance()

    const headers = {
      Authorization: `Bearer ${token}`
    }
    const body = {
      foldername
    }

    return await service.makeRequest(
      `folders/deletefolder?foldername=${foldername}`,
      'DELETE',
      null,
      true,
      headers
    )
    // return await service.makeRequest('deletefolder', 'DELETE', {
    //   token,
    //   foldername
    // })
  }
}

export default FolderService
