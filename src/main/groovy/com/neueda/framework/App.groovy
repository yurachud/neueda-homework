package com.neueda.framework

import com.neueda.framework.feature.FeatureFactory
import com.neueda.framework.feature.MindMapParser

import static com.neueda.framework.feature.FeatureFactory.getFeaturePath
import static groovy.io.FileType.FILES

class App {

    static final mindMaps = { new File('mindMaps') }

    public static void main(String... args) {
        cleanFeatureFolder()

        createFeatures()
    }

    private static createFeatures() {
        new FeatureFactory(featureDefinitions()).create()
    }

    private static featureDefinitions() {
        def featureDefinitions = []
        mindMaps().eachFile {
            featureDefinitions << MindMapParser.parse(it.text)
        }
        featureDefinitions
    }

    private static cleanFeatureFolder() {
        new File(featurePath).eachFileRecurse(FILES) { it.delete() }
    }

}
