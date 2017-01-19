package asta.nielsen

class Town {
    Date dateCreated
    Date lastUpdated

    String name
    Country country

    static constraints = {
        name nullable: false
        country nullable: false
    }
}
