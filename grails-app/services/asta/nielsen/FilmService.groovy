package asta.nielsen

import grails.transaction.Transactional
import org.hibernate.FetchMode
import org.hibernate.criterion.CriteriaSpecification
import org.hibernate.sql.JoinType

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

    def findFilmsWithLanguagesLike(languageSearchValue) {
        def languages = Language.findAllByNameIlike(languageSearchValue + "%")
        return Film.findAllByOriginalLanguageIn()
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
}

//select this_.id as id1_2_3_, this_.version as version2_2_3_, this_.date as date3_2_3_, this_.date_created as date_cre4_2_3_,
// this_.last_updated as last_upd5_2_3_, this_.original_country_id as original6_2_3_, this_.original_language_id as original7_2_3_,
// this_.original_title as original8_2_3_, dt1_.id as id1_3_0_, dt1_.version as version2_3_0_, dt1_.country_id as country_3_3_0_,
// dt1_.date_created as date_cre4_3_0_, dt1_.film_id as film_id5_3_0_, dt1_.language_id as language6_3_0_, dt1_.last_updated as last_upd7_3_0_,
// dt1_.title as title8_3_0_, l2_.id as id1_4_1_, l2_.version as version2_4_1_, l2_.iso_code as iso_code3_4_1_, l2_.name as name4_4_1_,
// ol3_.id as id1_4_2_, ol3_.version as version2_4_2_, ol3_.iso_code as iso_code3_4_2_, ol3_.name as name4_4_2_
//
// from film this_ inner join film_title dt1_ on this_.id=dt1_.film_id inner join language l2_ on dt1_.language_id=l2_.id
// inner join language ol3_ on this_.original_language_id=ol3_.id where (ol3_.name like ? or l2_.name like ?)


