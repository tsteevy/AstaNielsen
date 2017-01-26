package asta.nielsen

import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec

@TestFor(FilmService)
class FilmServiceIntegrationSpec extends IntegrationSpec {

    void "findAll returns all saved films"() {
        given:
        def film = new Film(originalTitle: "title").save()

        expect:
        [film] == service.findAll()
    }
}