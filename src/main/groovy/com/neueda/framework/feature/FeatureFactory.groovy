package com.neueda.framework.feature

import com.neueda.framework.feature.model.Feature
import com.neueda.framework.feature.model.Scenario
import groovy.text.SimpleTemplateEngine

class FeatureFactory {
    static final featurePath = 'src/cukes/resources/'
    final featureExtension = '.feature'
    final engine = new SimpleTemplateEngine()
    final scenarioTemplate
    final List<Feature> features
    def featureCountWithSameName = 1

    FeatureFactory(features) {
        def templateText = this.getClass().getClassLoader().getResource('calculatorScenario.template').text
        this.scenarioTemplate = engine.createTemplate(templateText)
        this.features = features
    }

    def create() {
        def featureGroups = features.groupBy { it.featureName }

        featureGroups.values().each { createFeatures(it) }
    }

    private createFeatures(features) {
        dropCounter()
        features.each { createFeature(it) }
    }

    private createFeature(Feature feature) {
        def featureFile = createFeatureFile(feature.featureName)

        feature.scenarios.each { featureFile << write(it) }
    }

    private write(Scenario scenario) {
        scenarioTemplate.make(scenario.scenarioDefinition).toString()
    }

    private createFeatureFile(String featureName) {
        def featureFile = new File(createFeatureName(featureName))
        featureFile.text = 'Feature: ' + featureName + featureCountWithSameName + '\n'
        increaseCounter()
        featureFile
    }

    private String createFeatureName(String featureName) {
        featurePath + featureName.replace(' ', '') + featureCountWithSameName + featureExtension
    }

    private dropCounter() {
        featureCountWithSameName = 1
    }

    private increaseCounter() {
        featureCountWithSameName++
    }

}
