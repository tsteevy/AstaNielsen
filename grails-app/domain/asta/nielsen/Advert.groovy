package asta.nielsen

class Advert {

    Date dateCreated
    Date lastUpdated

    String pathToScan
    Publication publication
    Date publicationDate
    Language publicationLanguage
    Town publicationTown
    Film film

    static constraints = {
        film nullable: true
        publication nullable: true
        publicationDate nullable: true
        publicationLanguage nullable: true
        publicationTown nullable: true
    }
}
