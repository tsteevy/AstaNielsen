package asta.nielsen

class FilmTitle {
    Date dateCreated
    Date lastUpdated

    String title
    Country country
    Language language

    static constraints = {
        title nullable: false
        country nullable: true
        language nullable: false
    }
}
