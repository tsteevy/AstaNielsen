package asta.nielsen

class Person {

    String title
    String prename
    String surname
    Date birthdate
    Date deathdate

    static constraints = {
    }

    static mapping = {
        autoTimestamp true
    }
}
