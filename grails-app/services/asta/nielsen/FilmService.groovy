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

    Film addFilmTitles(Film filmInstance, def filmTitleProperties) {
        def newFilmTitle = createFilmTitle(filmTitleProperties)
        filmInstance.addToDistributionTitles(newFilmTitle).save flush: true
        return filmInstance
    }

    def createFilmTitle(properties) {
        new FilmTitle(properties).save()
    }

    def findFilmsWithTitleLike(titleSearchValue) {
        return Film.findAllByOriginalTitleIlike(titleSearchValue)
    }

    def findFilmsWithLanguagesLike(languageSearchValue) {
        def languages = Language.findAllByNameIlike(languageSearchValue + "%")
        return Film.findAllByOriginalLanguage(languages)
    }
}
