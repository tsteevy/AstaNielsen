package asta.nielsen

class Country {
    Date dateCreated
    Date lastUpdated

    String name

    static constraints = {
        name nullable: false
    }
}
