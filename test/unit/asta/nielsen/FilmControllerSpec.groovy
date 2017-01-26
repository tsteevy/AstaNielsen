package asta.nielsen

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(FilmController)
@Mock([Film])
class FilmControllerSpec extends Specification {

    def setup() {
        controller.filmService = Mock(FilmService)
    }

    def cleanup() {
    }

    void "index returns the list of films in the model"() {
        given:
        def expectedFilms = [new Film(originalTitle: "LaLaLand").save()]

        when:
        def model = controller.index()

        then:
        expectedFilms == model.films
    }

    void "create returns a new instance of the Film class with properties defined by params"() {
        given:
        def expectedTitle = "bla"
        params.originalTitle = expectedTitle
        when:
        controller.create()

        then:
        expectedTitle == model["filmInstance"].originalTitle
    }

    void "edit returns the instance of Film given by its parameter"() {
        given:
        def filmInstance = new Film(originalTitle: "bla").save()
        when:
        controller.edit(filmInstance)
        then:
        filmInstance == model["filmInstance"]
    }

    void "update uses filmService method to save the given film"() {
        given:
        def film = new Film(originalTitle: "aTitle")

        when:
        controller.update(film)

        then:
        1 * controller.filmService.saveFilm(film)
    }

    void "update redirects to the index view after a successful save"() {
        given:
        def film = new Film(originalTitle: "aTitle")

        when:
        controller.update(film)

        then:
        response.redirectedUrl == "/film/index"
    }

    void "remove redirects to the index view after a successful deletion"() {
        given:
        def film = new Film(originalTitle: "aTitle")

        when:
        controller.remove(film)

        then:
        response.redirectedUrl == "/film/index"
    }

    void "remove uses filmService method to remove the given film"() {
        given:
        def film = new Film(originalTitle: "aTitle")

        when:
        controller.remove(film)

        then:
        1 * controller.filmService.deleteFilm(film)
    }

    void "delete returns the instance of the Film given by its parameter"() {
        given:
        def filmInstance = new Film(originalTitle: "bla").save()
        when:
        controller.delete(filmInstance)
        then:
        filmInstance == model["filmInstance"]
    }

    void "save uses filmService method to save the given film"() {
        given:
        def film = new Film(originalTitle: "aTitle")

        when:
        controller.save(film)

        then:
        1 * controller.filmService.saveFilm(film)
    }

    void "save shows create view again, when created film contains errors"() {
        given:
        def film = new Film(originalTitle: null)

        when:
        controller.save(film)

        then:
        "create" == view
    }

    void "save redirects to edit view, when created film does not contain any errors"() {
        given:
        def film = new Film(originalTitle: "a Title")

        when:
        controller.save(film)

        then:
        "/film/edit" == response.redirectedUrl
    }

    void "addFilmTitle uses filmService to add a filmtitle to an existing Film"() {
        given:
        def film = new Film(originalTitle: "a title").save()
        params.title = "another Title"

        when:
        controller.addFilmTitle(film)

        then:
        1 * controller.filmService.addFilmTitles(film, _)
    }

    void "addFilmTitle renders the filmtitlelist after a successful save"() {
        given:
        def film = new Film(originalTitle: "a title").save()
        views['/film/_filmtitlelist.gsp'] = 'film title list for a given film'
        params.title = "another Title"

        when:
        controller.addFilmTitle(film)

        then:
        "film title list for a given film" == response.text
    }

    void "deleteTitle uses filmService to delete an existing filmtitle from a Film"() {
        given:
        def film = new Film(originalTitle: "a title").save()
        def anotherFilmTitle = new FilmTitle(title: "another title")
        film.addToDistributionTitles(anotherFilmTitle)
        params.title_id = anotherFilmTitle.id

        when:
        controller.deleteTitle(film)

        then:
        1 * controller.filmService.deleteTitle(film, anotherFilmTitle.id)
    }

    void "deleteTitle redirects to edit view after successfully removing a title"() {
        given:
        def film = new Film(originalTitle: "a title").save()
        def anotherFilmTitle = new FilmTitle(title: "another title")
        film.addToDistributionTitles(anotherFilmTitle)
        params.title_id = anotherFilmTitle.id
        def expectedUrl = "/film/edit/" + film.id

        when:
        controller.deleteTitle(film)

        then:
        expectedUrl == response.redirectedUrl
    }
}
