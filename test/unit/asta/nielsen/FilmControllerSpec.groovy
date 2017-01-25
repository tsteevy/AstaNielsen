package asta.nielsen

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(FilmController)
@Mock([Film])
class FilmControllerSpec extends Specification {

    def setup() {
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
        controller.filmService = Mock(FilmService)

        when:
        controller.update(film)

        then:
        1 * controller.filmService.saveFilm(film)
    }

    void "update redirects to the index view after a successful save"() {
        given:
        def film = new Film(originalTitle: "aTitle")
        controller.filmService = Mock(FilmService)

        when:
        controller.update(film)

        then:
        response.redirectedUrl == "/film/index"
    }

    void "remove redirects to the index view after a successful deletion"() {
        given:
        def film = new Film(originalTitle: "aTitle")
        controller.filmService = Mock(FilmService)

        when:
        controller.remove(film)

        then:
        response.redirectedUrl == "/film/index"
    }

    void "remove uses filmService method to remove the given film"() {
        given:
        def film = new Film(originalTitle: "aTitle")
        controller.filmService = Mock(FilmService)

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
        controller.filmService = Mock(FilmService)

        when:
        controller.save(film)

        then:
        1 * controller.filmService.saveFilm(film)
    }

    void "save shows create view again, when created film contains errors"() {
        given:
        def film = new Film(originalTitle: null)
        controller.filmService = Mock(FilmService)

        when:
        controller.save(film)

        then:
        view == "create"
    }

    void "save redirects to edit view, when created film does not contain any errors"() {
        given:
        def film = new Film(originalTitle: "a Title")
        controller.filmService = Mock(FilmService)

        when:
        controller.save(film)

        then:
        response.redirectedUrl == "/film/edit"
    }

    void "addFilmTitle uses filmService to add a filmtitle to an existing Film"() {
        given:
        def film = new Film(originalTitle: "a title").save()
        params.title = "another Title"
        controller.filmService = Mock(FilmService)

        when:
        controller.addFilmTitle(film)

        then:
        1 * controller.filmService.addFilmTitles(film, new FilmTitle(title: "another Title"))
    }
}
