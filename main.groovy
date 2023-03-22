
def defineParameters() {
    
    //Suppose you have two dropdown fields as Jenkins Parameters, and there is certain one to many relations between value of those fields. e.g. Country & states.
    // In that case you can use below method to create two Active Choice parameters that will dynamically change dropdown options of second field based on the first one.
    (countryNameParameter, stateNameParameter) = addActiveChoicesDeployableReposParameters('world')
    //Now define another groovy file addActiveChoicesDeployableReposParameters.groovy, where you have specify different list of Countries.
    
    properties([
        parameters([
            string(
                name:'buildsScanPeriod',
                description:'Enter the number of days, upto the builds items needs to be scanned',
                defaultValue:'60',
                trim:true
            ),
            countryNameParameter,
            stateNameParameter,
            
        ])
    ])
}
