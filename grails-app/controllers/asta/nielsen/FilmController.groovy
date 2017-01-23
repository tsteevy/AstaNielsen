package asta.nielsen

class FilmController {

    def filmService

    def index() {
        [films: Film.getAll()]
    }

    def create() {
        respond new Film(params)
    }

    def edit(Film filmInstance) {
        respond filmInstance
    }

    def update(Film filmInstance) {
        filmInstance.validate()

        if (filmInstance.hasErrors()) {
            respond filmInstance, view:'edit'
            return
        }

        filmInstance.save flush:true
        redirect(action: "index")
    }

    def remove(Film filmInstance) {
        filmInstance?.delete flush:true

        redirect(action: "index")
    }

    def delete(Film filmInstance) {
        respond filmInstance
    }

    def save(Film filmInstance) {
        filmInstance.validate()

        if (filmInstance.hasErrors()) {
            respond filmInstance, view:'create'
            return
        }

        filmInstance.save flush:true
        redirect(action: "edit", id: filmInstance.id)
    }

    def addFilmTitle(Film filmInstance) {
        filmInstance.addToDistributionTitles(params).save flush: true

        redirect(action: "edit", id: filmInstance.id)
    }

    def delete_title(Film filmInstance) {
        filmService.deleteTitle(filmInstance, params.get("title_id"))

        redirect(action: "edit", id: filmInstance.id)
    }
}
