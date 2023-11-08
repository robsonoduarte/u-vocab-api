package com.uvocab.api.controller

import com.uvocab.api.domain.Vocab
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

	def "should return the vocab with word ok"(){
		def response = restTemplate.exchange('/v1/uvocab', GET, null, Vocab)
		expect:
		response.statusCode == HttpStatus.OK
		response.body.word == "OK"
	}
}
