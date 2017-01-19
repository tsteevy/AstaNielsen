package asta.nielsen

class FilmAdvert {

    Date publicationDate
    static belongsTo = [Film, Publication]
    String pathToScan
    Language publicationLanguage
    Town publicatedAt

    static mapping = {
        autoTimestamp true
    }

    static constraints = {
    }
}
