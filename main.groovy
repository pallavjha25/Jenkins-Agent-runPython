
def defineParameters() {
    properties([
        parameters([
            string(
                name:'artifactoryScanPeriod',
                description:'Enter the number of days, upto the artifactory items needs to be scanned',
                defaultValue:'60',
                trim:true
            )
        ])
    ])
}
