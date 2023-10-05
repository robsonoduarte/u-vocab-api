package com.uvocab.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static org.springframework.http.HttpMethod.GET


@ActiveProfiles(['test'])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UvocabControllerSpec extends Specification{

	@Autowired
	TestRestTemplate restTemplate

	def "should return the string as ok"(){
		def response = restTemplate.exchange('/v1/uvocab', GET, null, String)
		expect:
		response.statusCode == HttpStatus.OK
		response.body == "OK"
	}
}
