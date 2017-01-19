package asta.nielsen

class Film {
    Date dateCreated
    Date lastUpdated

    Date date
    FilmTitle originalTitle

    static hasMany = [distributionTitles: FilmTitle]

    static constraints = {
        originalTitle nullable: false
        distributionTitles nullable: true
        date nullable: true
    }
}
