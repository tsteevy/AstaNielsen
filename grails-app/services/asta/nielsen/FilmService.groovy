package asta.nielsen

import grails.transaction.Transactional

@Transactional
class FilmService {

    def serviceMethod() {

    }

    void deleteTitle(Film filmInstance, titleId) {
        FilmTitle.find {id==titleId}.delete()
        filmInstance.save flush:true
    }
}
