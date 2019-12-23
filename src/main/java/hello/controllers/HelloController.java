package hello.controllers;

import hello.exceptions.MyResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class HelloController {

    @GetMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /*	@GetMapping(value = "/jsonobject", produces = "application/json")
        public String jsonobject () throws Exception {
            JSONObject obj = new JSONObject();

            obj.put("name", "MyName");
            obj.put("num", new Integer(100));
            obj.put("balance", new Double(1000.21));
            obj.put("is_vip", new Boolean(true));

            return obj.toString();
        }*/
    @GetMapping(value = "/jsonobject/list", produces = "application/json")
    public ResponseEntity<List<User>> jsonobjectList() {
        List objectsList = new ArrayList();
        User user1 = new User();
        user1.setAge(11);
        user1.setName("user1");
        user1.setId(22);
        User user2 = new User();
        user2.setAge(33);
        user2.setName("user2");
        user2.setId(44);
        objectsList.add(user1);
        objectsList.add(user2);
        return new ResponseEntity(objectsList, HttpStatus.OK);
    }

    @GetMapping(value = "/jsonobject/error", produces = "application/json")
    public ResponseEntity<List<User>> simpleError() throws Exception {
        List objectsList = new ArrayList();
        User user1 = new User();
        user1.setAge(11);
        user1.setName("user1");
        user1.setId(22);
        User user2 = new User();
        user2.setAge(33);
        user2.setName("user2");
        user2.setId(44);
        objectsList.add(user1);
        objectsList.add(user2);
        if (objectsList.size() == 2) {
            throw new MyResourceNotFoundException("что то пошло не так 1");

            //throw new ClassNotFoundException("что то пошло не так 2");
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        //productService.deleteProduct(id);
        User user = new User();
        user.setAge(33);
        user.setName("Myname");
        user.setId(id);

        ResponseEntity responseEntity = new ResponseEntity(user, HttpStatus.OK);
        //return new ResponseEntity("Product deleted successfully "+id, HttpStatus.OK);
        return responseEntity;

    }

    @PostMapping(value = "/user"/*, consumes = "application/json"*/)
    //@ResponseBody
    public ResponseEntity<User> user(@RequestBody User user) {
        //productService.deleteProduct(id);
        User userResp = new User();
        userResp.setAge(user.getAge());
        userResp.setName(user.getName());
        userResp.setId(user.getId());
        ResponseEntity responseEntity = new ResponseEntity(userResp, HttpStatus.OK);
        //return new ResponseEntity("Product deleted successfully "+id, HttpStatus.OK);
        return responseEntity;

    }

    @PostMapping(value = "/token")
    public ResponseEntity<String> token(@RequestHeader String token) {

        ResponseEntity responseEntity = new ResponseEntity(token, HttpStatus.OK);
        return responseEntity;

    }

    @PostMapping(value = "/requestParam")
    public ResponseEntity<String> requestParam(@RequestParam String id,
                                               @RequestParam(required = false) String idFalse,
                                               @RequestParam(name = "id_ID") String fooId) {

        ResponseEntity responseEntity = new ResponseEntity(id + " " + idFalse + " " + fooId, HttpStatus.OK);
        return responseEntity;

    }


}
