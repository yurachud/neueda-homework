package com.neueda.framework.feature

import com.neueda.framework.feature.model.Feature

class MindMapParser {

    static Feature parse(String xmlMap) {
        def testsRootNode = getTestsRootNode(xmlMap)

        extractFeatureFrom(testsRootNode)
    }

    private static extractFeatureFrom(testsRootNode) {
        def feature = new Feature(featureName: featureName(testsRootNode))
        testSuitesNodes(testsRootNode).each {
            feature.extractScenariosFrom(it)
        }
        feature
    }

    private static getTestsRootNode(String xmlMap) {
        def mindMap = new XmlSlurper().parseText(xmlMap)
        mindMap.node[0]
    }

    private static testSuitesNodes(testsRootNode) {
        testsRootNode.node
    }

    private static String featureName(testsRootNode) {
        testsRootNode.@TEXT as String
    }

}
