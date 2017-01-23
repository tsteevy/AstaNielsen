package asta.nielsen

class CountryController {

    def index() { }

    def create() {
        respond new Country(params)
    }

    def save(Country countryInstance) {
        countryInstance.validate()

        if (countryInstance.hasErrors()) {
            respond countryInstance, view:'create'
            return
        }

        countryInstance.save flush:true
        redirect(action: "index", controller: "film")
    }
}
