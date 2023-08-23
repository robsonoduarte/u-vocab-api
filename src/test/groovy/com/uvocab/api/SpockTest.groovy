package com.uvocab.api

import spock.lang.Specification


class SpockTest extends Specification {
    def "should be a simple assertion" () {
        expect:
        1 == 1
        
    }

}
