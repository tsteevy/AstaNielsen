package asta.nielsen

class FilmController {

    def index() {
        [films: Film.getAll()]
    }

    def create() {

    }

    def edit(Film filmInstance) {
        respond filmInstance
    }

    def delete(Film filmInstance) {
        respond filmInstance
    }

    def save() {
        def originalTitle = new FilmTitle(title: params['originalFilmTitle'] )
        originalTitle.save()
        def filmInstance = new Film(originalTitle: originalTitle)
        filmInstance.save flush:true
        redirect(action: "index")
    }
}
