import net.talldave.comparator.USState

class BootStrap {

    def init = { servletContext ->
        if ( ! USState.count() ) {
            populateStates()
        }
    }

    def destroy = {
    }



    private void populateStates() {
        def states = [
            ["AL", "Alabama"],
            ["AK", "Alaska"],
            ["AZ", "Arizona"],
            ["AR", "Arkansas"],
            ["CA", "California"],
            ["CO", "Colorado"],
            ["CT", "Connecticut"],
            ["DE", "Delaware"],
            ["FL", "Florida"],
            ["GA", "Georgia"],
            ["HI", "Hawaii"],
            ["ID", "Idaho"],
            ["IL", "Illinois"],
            ["IN", "Indiana"],
            ["IA", "Iowa"],
            ["KS", "Kansas"],
            ["KY", "Kentucky"],
            ["LA", "Louisiana"],
            ["ME", "Maine"],
            ["MD", "Maryland"],
            ["MA", "Massachusetts"],
            ["MI", "Michigan"],
            ["MN", "Minnesota"],
            ["MS", "Mississippi"],
            ["MO", "Missouri"],
            ["MT", "Montana"],
            ["NE", "Nebraska"],
            ["NV", "Nevada"],
            ["NH", "New Hampshire"],
            ["NJ", "New Jersey"],
            ["NM", "New Mexico"],
            ["NY", "New York"],
            ["NC", "North Carolina"],
            ["ND", "North Dakota"],
            ["OH", "Ohio"],
            ["OK", "Oklahoma"],
            ["OR", "Oregon"],
            ["PA", "Pennsylvania"],
            ["RI", "Rhode Island"],
            ["SC", "South Carolina"],
            ["SD", "South Dakota"],
            ["TN", "Tennessee"],
            ["TX", "Texas"],
            ["UT", "Utah"],
            ["VT", "Vermont"],
            ["VA", "Virginia"],
            ["WA", "Washington"],
            ["WV", "West Virginia"],
            ["WI", "Wisconsin"],
            ["WY", "Wyoming"],
            // Commonwealth/Territory
            ["AS", "American Samoa"],
            ["DC", "District of Columbia"],
            ["FM", "Federated States of Micronesia"],
            ["GU", "Guam"],
            ["MH", "Marshall Islands"],
            ["MP", "Northern Mariana Islands"],
            ["PW", "Palau"],
            ["PR", "Puerto Rico"],
            ["VI", "Virgin Islands"]
            /*,
            // Military
            ["AE", "Armed Forces Africa"],
            ["AA", "Armed Forces Americas"],
            ["AE", "Armed Forces Canada"],
            ["AE", "Armed Forces Europe"],
            ["AE", "Armed Forces Middle East"],
            ["AP", "Armed Forces Pacific"]*/
            // removing these - non-unique state codes ???
        ]
        states.each { state ->
            new USState(code:state[0], name:state[1]).save(failOnError:true)
        }
    }

}
