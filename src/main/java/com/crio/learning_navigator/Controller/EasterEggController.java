package com.crio.learning_navigator.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/easter-egg")
public class EasterEggController {

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<Map<String, String>> getHiddenFeature(@PathVariable int number) {
        // Generate a fun fact about the random number
        String randomFact = "This is a fun fact about number " + number + ".";

        // Prepare the response body according to requirements
        Map<String, String> response = new HashMap<>();
        response.put("message", "Great! You have found the hidden number fact");
        response.put("response", randomFact);

        // Return the response with a 200 OK status
        return ResponseEntity.ok(response);
    }
}
