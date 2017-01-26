package asta.nielsen

import grails.transaction.Transactional

@Transactional
class FilmService {
    void deleteTitle(Film filmInstance, titleId) {
        FilmTitle filmTitle = FilmTitle.find {id==titleId}
        filmInstance.removeFromDistributionTitles(filmTitle)
        filmTitle.delete()
        filmInstance.save flush:true
    }

    def deleteFilm(Film filmInstance) {
        filmInstance?.delete flush:true
    }

    def saveFilm(Film filmInstance) {
        filmInstance.save flush:true
        return filmInstance
    }

    @Transactional
    Film addFilmTitles(Film filmInstance, FilmTitle filmTitle) {
        filmInstance.addToDistributionTitles(filmTitle.save()).save flush: true
        return filmInstance
    }
}
