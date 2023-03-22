def call(
    String stateParameterName
) {
  countryList = getConuntryList() // another groovy file to define a list of Countries.
  countryList = """[
        'usa',
        'china',
        'unitedkingdom',
        'argentina',
        'others'
    ]"""
  
  usaStates = []
  chinaStates = []
  statesList.each { repo ->
    switch (repo) {
      case ~/^usa-.*$/:
        usaStates.add("\"${repo}\"")
        break
      case ~/^china-.*$/:
        chinaStates.add("\"${repo}\"")
        break
      case ~/^argentina-.*$/:
        argentinaStates.add("\"${repo}\"")
        break
      default:
        otherRepos.add("\"${repo}\"")
        break
  }
    
    countryParameterName = 'countryName'
    choiceType = 'PT_SINGLE_SELECT' // Reference: https://javadoc.jenkins.io/plugin/extended-choice-parameter/constant-values.html
    className = 'GroovyScript'
    fallbackReturn = """['###Script error###']"""
  
    defineProperties(stateParameterName)
}
  
def defineProperties(String stateParameterName) {
    return [[
        $class:'ChoiceParameter',
        name:countryParameterName,
        choiceType:choiceType,
        script:[
            $class:className,
            script:[sandbox:true, script:"""
                return ${projectsList}
            """
            ],
            fallbackScript:[sandbox:true, script:"""return ${fallbackReturn}"""],
        ],
    ],
            [
        $class:'CascadeChoiceParameter',
        name:stateParameterName,
        randomName:'choice-parameter-repoName',
        choiceType:choiceType,
        filterLength:1,
        filterable:false,
        script:[
            $class:className,
            fallbackScript:[sandbox:true, script:"""return ${fallbackReturn}"""],
            script:[sandbox:true, script:"""
                switch(${countryParameterName}) {
                    case 'usa':
                        return ${usaStates}
                    case 'china':
                        return ${chinaStates}
                        }
                        """
                    ],
          ],
          referencedParameters:countryParameterName,
              ],]
}
