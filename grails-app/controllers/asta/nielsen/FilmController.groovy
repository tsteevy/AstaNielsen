package asta.nielsen

class FilmController {

    def index() {
        [films: Film.getAll()]
    }

    def create() {
        def originalTitle = new FilmTitle(title: params['originalFilmTitle'] )
        originalTitle.save()
        def filmInstance = new Film(originalTitle: originalTitle)
        filmInstance.save flush:true
        redirect(action: "index")
    }
}
