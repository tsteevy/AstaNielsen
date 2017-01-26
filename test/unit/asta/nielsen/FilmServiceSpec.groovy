package asta.nielsen

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(FilmService)
@Mock([Film])
class FilmServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "save returns the saved film"() {
        given:
        def givenFilmInstance = new Film(originalTitle: "title")

        when:
        def actualFilm = service.saveFilm(givenFilmInstance)

        then:
        givenFilmInstance.originalTitle == actualFilm.originalTitle
        actualFilm.id
    }

    void "remove title removes the title with the given id of given film"() {
        given:
        def film = new Film(originalTitle: "a title").save()
        def anotherFilmTitle = new FilmTitle(title: "another title")
        film.addToDistributionTitles(anotherFilmTitle).save()
        film.addToDistributionTitles(new FilmTitle(title: "second alternative title")).save()
        anotherFilmTitle.save()

        when:
        service.deleteTitle(film, anotherFilmTitle.id)

        then:
        film.getDistributionTitles().size() == 1
    }

    void "saveFilm returns the filmInstance again"() {
        given:
        def film = new Film(originalTitle: "A title")

        when:
        def returnedFilm = service.saveFilm(film)

        then:
        returnedFilm == film
    }

    void "addFilmTitles returns the filmInstance again"() {
        given:
        def film = new Film(originalTitle: "A title")
        def filmTitle = new FilmTitle(title: "Another filmtitle")

        when:
        def returnedFilm = service.addFilmTitles(film, filmTitle)

        then:
        returnedFilm == film
    }


    void "addFilmTitles adds the given title to the given film"() {
        given:
        def film = new Film(originalTitle: "A title")
        def filmTitle = new FilmTitle(title: "Another filmtitle")

        when:
        film = service.addFilmTitles(film, filmTitle)

        then:
        film.getDistributionTitles()[0] == filmTitle
    }
}
