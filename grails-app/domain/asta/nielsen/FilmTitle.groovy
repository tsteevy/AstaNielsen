package asta.nielsen

class FilmTitle {
    Date dateCreated
    Date lastUpdated

    String title
    Country country
    Language language

    static belongsTo = [film: Film]

    static constraints = {
        title nullable: false
        country nullable: true
        language nullable: true
    }

    @Override
    String toString() {
        return title
    }
}
