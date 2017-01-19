package asta.nielsen

class Publication {
    Date dateCreated
    Date lastUpdated

    String name
    TextType textType

    static constraints = {
        name nullable: false
        textType nullable: false
    }
}
