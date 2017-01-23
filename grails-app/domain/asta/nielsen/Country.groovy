package asta.nielsen

class Country {
    Date dateCreated
    Date lastUpdated

    String name

    @Override
    String toString() {
        return name
    }

    static constraints = {
        name nullable: false
    }
}
