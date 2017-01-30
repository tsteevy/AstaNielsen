package asta.nielsen

import grails.test.mixin.TestFor
import grails.test.spock.Integration
import grails.test.spock.IntegrationSpec
import grails.transaction.Rollback

@Integration
@Rollback
@TestFor(FilmService)
class FilmServiceIntegrationSpec extends IntegrationSpec {

    void "findAll returns all saved films"() {
        given:
        def film = new Film(originalTitle: "title").save()

        expect:
        [film] == service.findAll()
    }

    void "deleteFilm removes the given film"() {
        given:
        def film = new Film(originalTitle: "title").save()

        when:
        service.deleteFilm(film)

        then:
        [] == service.findAll()
    }

    void "deleteTitle removes the given title for that film"() {
        given:
        def film = new Film(originalTitle: "title")
        def filmTitle = new FilmTitle(title: "another title")
        film.addToDistributionTitles(filmTitle).save()

        when:
        service.deleteTitle(film, filmTitle.id)

        then:
        [] == film.getDistributionTitles()
    }

    void "saveFilm saves the given film to database"() {
        given:
        def film = new Film(originalTitle: "title")

        when:
        service.saveFilm(film)

        then:
        [film] == service.findAll()
    }


    void "addFilmTitles adds a title with given properties to given film"() {
        given:
        def film = new Film(originalTitle: "title").save()

        when:
        service.addFilmTitles(film, [title: 'another title'])

        then:
        'another title' == film.getDistributionTitles()[0].title
    }

    void "createFilmTitle creates a filmtitle by given properties" () {
        given:
        def properties = [title: 'a filmtitle']

        expect:
        'a filmtitle' == service.createFilmTitle(properties).title
    }
}