package asta.nielsen

class Person {
    Date dateCreated
    Date lastUpdated

    String title
    String prename
    String surname
    Date birthdate
    Date deathdate

    static constraints = {
        title nullable: true
        prename nullable: true
        surname nullable: false
        birthdate nullable: true
        deathdate nullable: true
    }
}
