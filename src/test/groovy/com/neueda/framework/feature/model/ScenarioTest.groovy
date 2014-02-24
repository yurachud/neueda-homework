package com.neueda.framework.feature.model

import spock.lang.Specification

class ScenarioTest extends Specification {

    def "should create Scenario from test and request nodes"() {
        setup:
        def testNode = loadTestSuiteNode('testDescriptionNode.xml')
        def requestNode = loadTestSuiteNode('requestDescriptionNode.xml')

        when:
        def scenario = new Scenario(testNode, requestNode)

        then:
        assertScenarioDefinition(scenario)
    }

    private void assertScenarioDefinition(scenario) {
        scenario.scenarioDefinition['Path'] == '/rest/multiply'
        scenario.scenarioDefinition['scenarioTitle'] == 'Test: simple multiplication'
        scenario.scenarioDefinition['variableOne'] == '5'
        scenario.scenarioDefinition['variableTwo'] == '9'
        scenario.scenarioDefinition['result'] == '45'
    }

    def loadTestSuiteNode(name) {
        def testSuiteNode = this.getClass().getClassLoader().getResource(name)
        new XmlSlurper().parseText(testSuiteNode.text)
    }
}
