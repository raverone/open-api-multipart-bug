package org.example.bug.http;

import org.example.bug.http.api.TestApiApi;
import org.example.bug.http.model.SomeObject;
import org.example.bug.http.model.SomeResponse;
import org.example.bug.http.model.SomeResponse.TypeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class BugController implements TestApiApi {

  @Override
  public ResponseEntity<SomeResponse> uploadFileAndJson(SomeObject someObject,
      MultipartFile image) {
    System.out.println("Received " + someObject + " and " + image.getOriginalFilename());
    return ResponseEntity.ok(new SomeResponse().type(TypeEnum.SUCCESS));
  }
}
