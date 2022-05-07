package in.workbyte.virtualhostcreator.controller;

import in.workbyte.virtualhostcreator.HostWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class VHC_Controller {

    @Autowired
    HostWriter hostWriter;

    /**/
    @PostMapping(value = "/create")
    public ResponseEntity<Resource> createVh(@RequestBody Map<String, String> vhDetails) {

        try {
            Resource vhzip = hostWriter.createVhFiles(vhDetails);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vh.zip");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(vhzip.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(vhzip);
        } catch (IOException e) {
            return ResponseEntity.ok(null);
        }
    }
}
