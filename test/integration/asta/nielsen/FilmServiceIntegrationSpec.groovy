package asta.nielsen

import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec

@TestFor(FilmService)
class FilmServiceIntegrationSpec extends IntegrationSpec {
    def existingFilms

    void setup() {
        existingFilms = Film.findAll()
    }

    void "findAll returns all saved films"() {
        given:
        def film = new Film(originalTitle: "title").save()

        expect:
        existingFilms << film == service.findAll()
    }

    void "deleteFilm removes the given film"() {
        given:
        def film = new Film(originalTitle: "title").save()

        when:
        service.deleteFilm(film)

        then:
        existingFilms == Film.findAll()
    }

    void "deleteTitle removes the given title for that film"() {
        given:
        def film = new Film(originalTitle: "title")
        def filmTitle = new FilmTitle(title: "another title")
        film.addToDistributionTitles(filmTitle).save()

        when:
        service.deleteTitle(film, filmTitle.id)

        then:
        [] == film.getDistributionTitles().toArray()
    }

    void "saveFilm saves the given film to database"() {
        given:
        def film = new Film(originalTitle: "title")

        when:
        service.saveFilm(film)

        then:
        existingFilms << film == Film.findAll()
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

    void "findFilmsWithTitleLike returns all films with a title corresponding to the search term"() {
        given:
        def film = new Film(originalTitle: "title").save()
        new Film(originalTitle: "not matching title").save()

        expect:
        [film] == service.findFilmsWithTitleLike("ti%")
    }

    void "findFilmsWithTitleLike returns no films when the search term does not match any films"() {
        given:
        new Film(originalTitle: "title").save()

        expect:
        [] == service.findFilmsWithTitleLike("a title which is unlikely to be found at anytime, because it is so long and specific")
    }

    void "findFilmsWithLanguagesLikeWithCriteria returns all films with a corresponding language or language for any alternative title"() {
        given:
        def matchingLanguage = new Language(name: "matching Language", isoCode: "mL").save()
        def notMatchingLanguage = new Language(name: "not matching Language", isoCode: "nmL").save()
        def film = new Film(originalTitle: "title", originalLanguage: matchingLanguage).save()

        def anotherFilm = new Film(originalTitle: "another title", originalLanguage: notMatchingLanguage)
                .addToDistributionTitles(new FilmTitle(title: "another distribution Title", language: matchingLanguage)).save()

        expect:
        [film, anotherFilm] == service.findFilmsWithLanguagesLikeWithCriteria("match")
    }

    void "findFilmsWithTitleLikeWithHql returns all films with a title corresponding to the search term"() {
        given:
        def film = new Film(originalTitle: "title").save()
        new Film(originalTitle: "not matching title").save()

        expect:
        [film] == service.findFilmsWithTitleLikeWithHql("ti%")
    }


}