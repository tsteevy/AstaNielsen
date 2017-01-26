import asta.nielsen.Country
import asta.nielsen.Film
import asta.nielsen.Language

class BootStrap {

    def init = { servletContext ->


        def german = new Language(name: "German", isoCode: "de").save()
        new Language(name: "English", isoCode: "en").save()
        def french = new Language(name: "French", isoCode: "fr").save()
        def flemish = new Language(name: "Flemish", isoCode: "be").save()

        new Country(name: "Germany").save()
        new Country(name: "France").save()
        new Country(name: "Russia").save()

        new Film(originalTitle: "The Stand", originalLanguage: french).save()
        new Film(originalTitle: "The Shining", originalLanguage: flemish).save()

    }

    def destroy = {
    }
}
