package asta.nielsen

class Language {
    String name
    String isoCode

    static constraints = {
        name nullable: false
        isoCode nullable: false
    }

    @Override
    String toString() {
        return name + "(" + isoCode + ")"
    }
}
