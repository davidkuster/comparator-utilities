package net.talldave.comparator.util

/**
 * Utility class to determine when two (or more) lists intersect.
 *
 * Initially created due to the belief that Groovy's .intersect method
 * will fail if one of lists is a PersistentSet and one is an ArrayList,
 * or if one contains _javassist proxy objects and one contains actual
 * domain classes.  However, that may have been due to a lack of defined
 * equals() or Comparable interface defined on the classes in question.
 *
 * Regardless of that, the multipleIntersect() method has proven itself
 * to be useful.
 */
class IntersectComparator {

    static def intersectLists( List first, List second ) {
        if ( first && second )
            return first.intersect( second )
        else
            return []
    }

    static def intersectAsStrings( firstList, secondList ) {
        def first = firstList?.collect { it as String }
        def second = secondList?.collect { it as String }
        intersectLists( first, second )
    }



    // TODO: look at getting this working

    // multiple intersect helper method, to avoid need to use this notation: multipleIntersect( *list )
    /*static def multipleIntersect( List list ) {
        if (list.every { it instanceof List} )
            multipleIntersect( *list )
        else
            return list
    }*/

        //if ( lists.size() == 1 && lists[0] instanceof List && lists[0].size() > 1 ) {
        //  lists = lists[0]
        //}


    // recursively finds the intersection of multiple lists.
    // ie. multipleIntersect( list1, list2, list3, etc )
    // also possible to pass a list of lists, but must be done as:
    // multipleIntersect( *list )
    static def multipleIntersect( List... lists ) {
        def result

        // validate input
        if ( lists ) {

            // check for any null inputs & return [] (no intersection possible)
            if ( lists.any { it == null } )
                result = []
            // check for any empty lists & return [] (no intersection possible)
            else if ( lists.any { it.size() == 0 } )
                result = []
            // otherwise process list
            else {
                // convert "lists" to a list of lists, so pop() will work
                def lol = lists as List
                def size = lol.size()

                // if only one list passed in, return it
                if ( size == 1 )
                    result = lol[0]
                // if two lists passed in, just do intersection of those
                else if ( size == 2 )
                    result = intersectLists( lol[0], lol[1] )
                // otherwise recursively process intersection of all lists
                else {
                    // seed recursion with intersection of first two lists
                    result = multIntersectImpl.call( lol, intersectLists(lol.pop(), lol.pop()) )
                    // note that .call() is necessary because of .trampoline()
                }
            }
        }

        result
    }


    // private function to hide recursion && so error checking is only done once
    private static def multIntersectImpl = { lists, result ->
        if ( lists.size() == 0 )
            // base case
            return result
        else if ( ! result )
            // we've gotten to an empty list, so quit recursing
            // ('shut yo mouth!' 'hey, i'm only talking about recursing.' 'then we can dig it.')
            return []
        else {
            // "To understand recursion, we must first understand recursion."
            multIntersectImpl.trampoline( lists, intersectLists(result, lists.pop()) )
        }
    }.trampoline()

}