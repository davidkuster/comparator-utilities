package net.talldave.comparator.util

import spock.lang.Specification
import net.talldave.comparator.util.IntersectComparator as IC

class IntersectComparatorSpec extends Specification {


    def "should check intersect functionality"() {
        given: "test data"
            def a = [1,2,3,4,5]
            def b = [2,3,5]
            def c = [1,2,3]
            def d = [3]

        when: "find intersections"
            def aUb = IC.intersectLists( a, b )
            def aUc = IC.intersectLists( a, c )
            def aUd = IC.intersectLists( a, d )
            def aUa = IC.intersectLists( a, a )
            def aUnull = IC.intersectLists( a, null )
            def aUempty = IC.intersectLists( a, [] )

        then: "verify results"
            // intersect currently only does string comparisons,
            // which is probably a bad idea...
            assert aUb == [2,3,5]
            assert aUc == [1,2,3]
            assert aUd == [3]
            assert aUa == [1,2,3,4,5]
            assert aUnull == []
            assert aUempty == []
    }


    def "should check string intersect functionality"() {
        given: "test data"
            def a = [1,2,3,4,5]
            def b = [2,3,5]
            def c = [1,2,3]
            def d = [3]

        when: "find intersections"
            def aUb = IC.intersectAsStrings( a, b )
            def aUc = IC.intersectAsStrings( a, c )
            def aUd = IC.intersectAsStrings( a, d )
            def aUa = IC.intersectAsStrings( a, a )
            def aUnull = IC.intersectAsStrings( a, null )
            def aUempty = IC.intersectAsStrings( a, [] )

        then: "verify results"
            // intersect currently only does string comparisons,
            // which is probably a bad idea...
            assert aUb == ['2','3','5']
            assert aUc == ['1','2','3']
            assert aUd == ['3']
            assert aUa == ['1','2','3','4','5']
            assert aUnull == []
            assert aUempty == []
    }


    def "should check multiple intersect functionality"() {
        given: "test data"
            def a = [1,2,3,4,5]
            def b = [2,3,5]
            def c = [1,2,3]
            def d = [3]
            def e = [1000]

        when: "find intersections"
            def aUbUc = IC.multipleIntersect( a, b, c )
            def aUcUd = IC.multipleIntersect( a, c, d )
            def bUcUnull = IC.multipleIntersect( b, c, null )
            def aUbUempty = IC.multipleIntersect( a, b, [] )
            def aUbUcUe = IC.multipleIntersect( a, b, c, e )
            def aUb = IC.multipleIntersect( a, b )
            def dOnly = IC.multipleIntersect( d )

        then: "verify results"
            assert aUbUc == [2,3]
            assert aUcUd == [3]
            assert bUcUnull == []
            assert aUbUempty == []
            assert aUbUcUe == []
            assert aUb == [2,3,5]
            assert dOnly == [3] // yeah, exposes a problem
    }


    def "should verify exception on bad data input"() {
        given: "test data"
            def a = [1,2,3,4,5]
            def b = [2,3,5]

        when: "pass bad data"
            def e
            try {
                IC.multipleIntersect( a, b, 1 )
            }
            catch ( t ) {
                e = t
            }

        then: "verify UnsupportedOperationException caught"
            //assert e instanceof UnsupportedOperationException
            assert e instanceof MissingMethodException
    }

}