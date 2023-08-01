package rest.api

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ValidationExceptionSpec extends Specification implements DomainUnitTest<ValidationException> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
