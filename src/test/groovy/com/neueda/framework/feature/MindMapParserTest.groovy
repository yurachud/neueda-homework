package com.neueda.framework.feature

import spock.lang.Specification

class MindMapParserTest extends Specification {
    def "should create Feature with 9 scenarios from xml mindMap"() {
        when:
        def feature = MindMapParser.parse(loadMindMap())

        then:
        feature.featureName == 'Calculator tests'
        feature.scenarios.size() == 9
    }

    def loadMindMap() {
        this.getClass().getClassLoader().getResource('calc_tests.xml').text
    }
}
