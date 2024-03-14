package savitarna.siuntusavitarna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BasicController
{
    @GetMapping("/test")
    public String getTest()
    {
        System.out.println("Test Works!!!");
        return "Test Works!!!";
    }
}