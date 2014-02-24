package com.neueda.framework.feature.model

import spock.lang.Specification

class FeatureTest extends Specification {
    static final String featureName = 'feature'
    def feature = new Feature(featureName: featureName)

    def "should extract 2 scenarios from test suite node"() {
        when:
        feature.extractScenariosFrom(loadTestSuiteNode('full'))

        then:
        feature.featureName == featureName
        feature.scenarios.size() == 2
    }

    def "should extract 0 scenarios from test suite node without tests description"() {
        when:
        feature.extractScenariosFrom(loadTestSuiteNode('empty'))

        then:
        feature.featureName == featureName
        feature.scenarios.size() == 0
    }

    def loadTestSuiteNode(type) {
        def testSuiteNode = this.getClass().getClassLoader().getResource(type + 'TestSuiteNode.xml')
        new XmlSlurper().parseText(testSuiteNode.text)
    }
}
