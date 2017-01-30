package asta.nielsen

class FilmController {

    def filmService

    def index() {
        [films: Film.getAll()]
    }

    def viewList() {
        [films: Film.getAll()]
    }

    def create() {
        respond new Film(params)
    }

    def edit(Film filmInstance) {
        respond filmInstance
    }

    def view(Film filmInstance) {
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
        log.info(filmInstance)
        if (filmInstance == null)
            return
        filmInstance = filmService.addFilmTitles(filmInstance, [title: params.title, language: params.language, country: params.country])

        render(template: "filmtitlelist", model: [filmInstance: filmInstance])
    }

    def deleteTitle(Film filmInstance) {
        filmService.deleteTitle(filmInstance, params.get("title_id"))

        redirect(action: "edit", id: filmInstance.id)
    }

    def findTitlesLike() {
        render(view: 'index', model: [films: filmService.findFilmsWithTitleLike(params.searchQuery)])
    }

    def findLanguagesLike() {
        render(view: 'index', model: [films: filmService.findFilmsWithLanguagesLikeWithCriteria(params.searchQuery)])
    }

    def findTitlesLikeHql() {
        render(view: 'index', model: [films: filmService.findFilmsWithTitleLikeWithHql(params.searchQuery)])
    }
}
