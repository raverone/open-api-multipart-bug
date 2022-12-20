package org.example.bug.http

import org.example.bug.http.model.SomeObject
import org.example.bug.http.model.SomeResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BugControllerSpec extends Specification {

  @Autowired
  TestRestTemplate restTemplate

  def 'Should pass'() {
    given:
    def headers = new HttpHeaders()
    headers.setContentType(MediaType.MULTIPART_FORM_DATA)

    def body = new LinkedMultiValueMap<>()
    body.add('someObject', new SomeObject().description("Hello from test."))
    body.add('image', new FileSystemResource(new File('src/test/resources/pic.png')))

    def request = new HttpEntity<>(body, headers)

    when:
    def response = restTemplate.postForEntity('/upload-file-and-json', request, SomeResponse)

    then:
    response.statusCode == HttpStatus.OK
    response.body
    response.body.type == SomeResponse.TypeEnum.SUCCESS
  }

}