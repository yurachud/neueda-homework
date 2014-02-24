package feature.steps

import cucumber.api.groovy.EN
import groovy.json.JsonBuilder
import groovyx.net.http.HTTPBuilder

import static groovyx.net.http.ContentType.JSON

this.metaClass.mixin(EN)

When(~'^I call service by path: "([^"]*)" with values \\["([^"]*)", "([^"]*)"\\], then result is "([^"]*)"$') { String path, String variableOne, String variableTwo, String expectedResult ->
    def http = new HTTPBuilder('http://neueda.jelastic.dogado.eu/calculator' + path)
    def builder = new JsonBuilder([variableOne: variableOne, variableTwo: variableTwo])

    http.post(body: builder.toString(), requestContentType: JSON) {
        resp, resultFromServer ->
            assert resultFromServer == expectedResult
    }
}