package com.uvocab.api.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles(["test"])
class VocabularyRepositorySpec extends Specification{

	@Autowired
	VocabularyRepository repository

	def "should find all vocabulary with pageable"(){
		when: "find all with page request"
		def vocabularies = repository.findAll(PageRequest.of(pageNumber, 10))

		then: "check values"
		vocabularies.totalElements == 30
		vocabularies.content.size() == 10
		vocabularies.pageable.getPageSize() == 10
		vocabularies.pageable.getPageNumber() == pageNumber

		where:
		pageNumber << [0, 1, 2]
	}
}
