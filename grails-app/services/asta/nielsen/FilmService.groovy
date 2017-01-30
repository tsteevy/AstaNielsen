package asta.nielsen

import org.hibernate.sql.JoinType

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

    def findAll() {
        return Film.findAll()
    }

    def saveFilm(Film filmInstance) {
        filmInstance.save flush:true
        return filmInstance
    }

    Film addFilmTitles(Film filmInstance, Map filmTitleProperties) {
        def newFilmTitle = createFilmTitle(filmTitleProperties)
        filmInstance.addToDistributionTitles(newFilmTitle).save flush: true
        return filmInstance
    }

    def createFilmTitle(properties) {
        FilmTitle filmTitle = new FilmTitle(properties)
        filmTitle.save()
        if (filmTitle.hasErrors()){
            log.error(filmTitle.errors)
        }

        return filmTitle
    }

    def findFilmsWithTitleLike(titleSearchValue) {
        return Film.findAllByOriginalTitleIlike(titleSearchValue)
    }


    def findFilmsWithLanguagesLikeWithCriteria(languageSearchValue) {
        def criteria = Film.createCriteria()
        def extendedSearchValue = languageSearchValue + "%"

        def results = criteria.list {
            createAlias("distributionTitles", "dt", JoinType.LEFT_OUTER_JOIN)
            createAlias("dt.language", "l", JoinType.LEFT_OUTER_JOIN)
            createAlias("originalLanguage", "ol")

            or {
                ilike("l.name", extendedSearchValue)
                ilike("ol.name", extendedSearchValue)
            }
        }

        return results
    }

    def findFilmsWithTitleLikeWithHql(titleSearchValue) {
        def extendedSearchValue = titleSearchValue + "%"
        def results =
                Film.findAll("from Film as f " +
                        "where f.originalTitle like :search",
                        [search: extendedSearchValue])
        return results
    }
}
