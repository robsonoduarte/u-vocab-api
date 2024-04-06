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
class ActuatorControllerSpec extends Specification{

	@Autowired
	TestRestTemplate restTemplate

	def "should check if api is up"(){
		when:"get the /actuator/health"
		def response = restTemplate.exchange('/actuator/health', GET, null, String)
		then:"check if api is up (200)"
		response.statusCode == HttpStatus.OK
	}
}
