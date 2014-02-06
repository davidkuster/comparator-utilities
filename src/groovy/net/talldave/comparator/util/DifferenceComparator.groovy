package net.talldave.comparator.util

/**
 * This is a utility class to determine when a list of
 * objects (IDs, Strings, etc) have been updated.
 */
class DifferenceComparator {

    def removedList
    def addedList
    //TODO: change to 'matchedList' or something like that
    def notUpdatedList


    public DifferenceComparator( existingList, updatedList )
    {
        // findAll{it} removes null or empty string values, but also removes 0...
        // thus...sticking with collect for now
        // (collect creates a new list so params aren't mutated)
        /*this.removedList = existingList?.collect { it } ?: []
        this.addedList = updatedList?.collect { it } ?: []

        //set up intersected list for convenience
        this.notUpdatedList = removedList.intersect( addedList )

        // remove elements from existing list in updated list - whatever's left has been removed
        if ( addedList ) removedList?.removeAll( addedList )

        // remove existing elements from updated list - whatever's left has been added
        if ( existingList ) addedList?.removeAll( existingList ) // ?.collect { it }
*/
        if ( existingList == null ) existingList = []
        if ( updatedList == null ) updatedList = []

        removedList = existingList - updatedList
        addedList = updatedList - existingList
        notUpdatedList = existingList.intersect( updatedList )
    }


    String toString() {
        "added: $addedList, removed: $removedList"
    }

}