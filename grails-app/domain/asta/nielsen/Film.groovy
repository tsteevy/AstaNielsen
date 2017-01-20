package asta.nielsen

class Film {
    Date dateCreated
    Date lastUpdated

    Date date

    String originalTitle
    Country originalCountry
    Language originalLanguage

    static hasMany = [distributionTitles: FilmTitle]

    static constraints = {
        originalTitle nullable: false
        distributionTitles nullable: true
        date nullable: true
        originalLanguage nullable: true
        originalCountry nullable: true
    }
}
