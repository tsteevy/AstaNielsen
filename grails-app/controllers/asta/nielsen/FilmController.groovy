package asta.nielsen

class FilmController {

    def index() {
        films: Film.getAll()
    }

    def create() {
        def originalTitle = new FilmTitle(title: params['originalFilmTitle'] )
        originalTitle.save()
        def filmInstance = new Film(originalTitle: originalTitle)
        filmInstance.save flush:true
        render filmInstance?.originalTitle
        render params['originalFilmTitle']
        //l['originalFilmTitle':'Hallo Welt', 'otherTitles':'', 'controller':'film', 'format':null, 'action':'create/']
        // respond filmInstance, view:'index'
    }
}
