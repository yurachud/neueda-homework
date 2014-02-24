package com.neueda.framework.feature.model

class Feature {

    String featureName
    def scenarios = []

    def extractScenariosFrom(testSuiteNode) {
        def testsDescriptions = findScenariosDescriptions(testSuiteNode)
        def requestDescription = findRequestDescription(testSuiteNode)

        createScenarios(requestDescription, testsDescriptions)
    }

    private createScenarios(requestDescription, testsDescriptions) {
        testsDescriptions.each { scenarios << new Scenario(it, requestDescription) }
    }

    private static findRequestDescription(testSuiteNode) {
        testSuiteNode.node.find {
            it.@TEXT == 'Request'
        }
    }

    private static findScenariosDescriptions(testSuiteNode) {
        testSuiteNode.node.findAll {
            (it.@TEXT as String).startsWith('Test')
        }
    }
}
