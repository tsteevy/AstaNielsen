import asta.nielsen.Country
import asta.nielsen.Film
import asta.nielsen.Language

class BootStrap {

    def init = { servletContext ->
        new Film(originalTitle: "The Stand").save()
        new Film(originalTitle: "The Shining").save()

        new Language(name: "German", isoCode: "de").save()
        new Language(name: "English", isoCode: "en").save()
        new Language(name: "French", isoCode: "fr").save()

        new Country(name: "Germany").save()
        new Country(name: "France").save()
        new Country(name: "Russia").save()

    }

    def destroy = {
    }
}
