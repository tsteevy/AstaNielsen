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
        filmService.saveFilm(filmInstance)

        if (filmInstance.hasErrors()) {
            respond filmInstance, view:'edit'
            return
        }

        redirect(action: "index")
    }

    def remove(Film filmInstance) {
        filmService.deleteFilm(filmInstance)

        redirect(action: "index")
    }

    def delete(Film filmInstance) {
        respond filmInstance
    }

    def save(Film filmInstance) {
        filmService.saveFilm(filmInstance)

        if (filmInstance.hasErrors()) {
            respond filmInstance, view:'create'
            return
        }

        redirect(action: "edit", id: filmInstance.id)
    }

    def addFilmTitle(Film filmInstance) {
        filmInstance = filmService.addFilmTitles(filmInstance, new FilmTitle(params))

        render(template: "filmtitlelist", model: [filmInstance: filmInstance])
    }

    def deleteTitle(Film filmInstance) {
        filmService.deleteTitle(filmInstance, params.get("title_id"))

        redirect(action: "edit", id: filmInstance.id)
    }
}
