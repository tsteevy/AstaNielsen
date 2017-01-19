package asta.nielsen

class Film {

    Date date
    FilmTitle orginalTitle
    FilmTitle[] oterDistributionTitles
    Person[] actors

    static mapping = {
        autoTimestamp true
    }

    static constraints = {
    }
}
