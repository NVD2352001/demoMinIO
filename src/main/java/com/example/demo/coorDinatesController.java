package com.example.demo;
import com.example.demo.model.coordinates;
import com.example.demo.service.coorDinatesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coordinates")
public class coorDinatesController {
    @Autowired
    private coorDinatesSevice sevice;
    @PostMapping("/add")
    public coordinates add(@RequestBody coordinates add){
        return sevice.adds(add);
    }
}
