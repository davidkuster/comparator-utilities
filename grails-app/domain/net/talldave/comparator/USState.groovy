package net.talldave.comparator

class USState {

    String code
    String name

    static constraints = {
        code( maxSize:2, blank:false, unique:true )
        name( maxSize:50, blank:false, unique:true )
    }

    static mapping = {
        table 'us_state'
    }


    String toString() {
        "$code: $name"
    }

}
