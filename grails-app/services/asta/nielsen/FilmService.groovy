package asta.nielsen

import grails.transaction.Transactional

@Transactional
class FilmService {
    void deleteTitle(Film filmInstance, titleId) {
        FilmTitle.find {id==titleId}.delete()
        filmInstance.save flush:true
    }

    def deleteFilm(Film filmInstance) {
        filmInstance?.delete flush:true
    }

    def saveFilm(Film filmInstance) {
        filmInstance.save flush:true
    }

    def addFilmTitles(Film filmInstance, FilmTitle filmTitle) {
        filmInstance.addToDistributionTitles(filmTitle).save flush: true
    }
}
