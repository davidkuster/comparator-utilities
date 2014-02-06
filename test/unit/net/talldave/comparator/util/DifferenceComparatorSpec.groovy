package net.talldave.comparator.util

import spock.lang.Specification
import net.talldave.comparator.util.DifferenceComparator as DC

class DifferenceComparatorSpec extends Specification {


    def "should check difference functionality - removed"() {
        given: "test data"
            def a = [1,2,3,4,5]
            def b = [2,3,5]
            def c = [1,2,3]
            def d = [3]

        when: "find differences"
            def ab = new DC( a, b )
            def ac = new DC( a, c )
            def ad = new DC( a, d )
            def aa = new DC( a, a )
            def anull = new DC( a, null )
            def aempty = new DC( a, [] )

        then: "verify results"
            assert ab.removedList == [1,4]
            assert ab.addedList == []

            assert ac.removedList == [4,5]
            assert ac.addedList == []

            assert ad.removedList == [1,2,4,5]
            assert ad.addedList == []

            assert aa.removedList == []
            assert aa.addedList == []

            assert anull.removedList == [1,2,3,4,5]
            assert anull.addedList == []

            assert aempty.removedList == [1,2,3,4,5]
            assert aempty.addedList == []
    }


    def "should check difference functionality - added"() {
        given: "test data"
            def a = [1,2,3,4,5]
            def b = [2,3,5]
            def c = [1,2,3]
            def d = [3]

        when: "find differences"
            def ba = new DC( b, a )
            def ca = new DC( c, a )
            def da = new DC( d, a )
            def nulla = new DC( null, a )
            def emptya = new DC( [], a )

        then: "verify results"
            assert ba.removedList == []
            assert ba.addedList == [1,4]

            assert ca.removedList == []
            assert ca.addedList == [4,5]

            assert da.removedList == []
            assert da.addedList == [1,2,4,5]

            assert nulla.removedList == []
            assert nulla.addedList == [1,2,3,4,5]

            assert emptya.removedList == []
            assert emptya.addedList == [1,2,3,4,5]
    }

}