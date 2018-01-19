package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AuthenticateController {

    @RequestMapping(value = "/security/authenticate", method = RequestMethod.GET)
    public Boolean authenticate(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "password", required = false) String password)
    {
        return Boolean.TRUE;
    }
}
