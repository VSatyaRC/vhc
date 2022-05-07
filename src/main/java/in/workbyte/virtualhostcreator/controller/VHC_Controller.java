package in.workbyte.virtualhostcreator.controller;

import in.workbyte.virtualhostcreator.HostWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VHC_Controller {

    @Autowired
    HostWriter hostWriter;

    /**/
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createVh(@RequestBody Map<String, String> vhDetails) {
        return ResponseEntity.ok(hostWriter.createVhFiles(vhDetails));
    }
}
