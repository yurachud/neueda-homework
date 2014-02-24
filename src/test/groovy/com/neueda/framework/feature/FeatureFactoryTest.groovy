package com.neueda.framework.feature

import spock.lang.Specification

class FeatureFactoryTest extends Specification {
    def feature
    def expectedContent

    void setup() {
        feature = MindMapParser.parse(loadMindMap())
        expectedContent = expectedContent()
    }

    void cleanup() {
        new File(createFeatureName()).delete()
    }

    def "should create feature file with expected content"() {
        when:
        new FeatureFactory([feature]).create()

        then:
        def featureFile = new File(createFeatureName())
        featureFile.exists()
        featureFile.text == expectedContent
    }

    private String createFeatureName() {
        FeatureFactory.featurePath + 'NeuedaTestFeature1.feature'
    }

    def loadMindMap() {
        this.getClass().getClassLoader().getResource('calc_tests.xml').text
    }

    def expectedContent() {
        this.getClass().getClassLoader().getResource('expectedFeatureContent.txt').text
    }
}
