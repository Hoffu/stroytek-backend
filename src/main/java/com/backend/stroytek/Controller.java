package com.backend.stroytek;

import com.backend.stroytek.model.Object;
import com.backend.stroytek.model.*;
import com.backend.stroytek.repository.*;
import com.kosprov.jargon2.api.Jargon2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;
import static com.kosprov.jargon2.api.Jargon2.jargon2Verifier;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class Controller {
    private ObjectRepository objectRepository;
    private OrderRepository orderRepository;
    private BlogRepository blogRepository;
    private UserRepository userRepository;
    private MaterialRepository materialRepository;

    @Autowired
    public Controller(ObjectRepository objectRepository, OrderRepository orderRepository,
                      BlogRepository blogRepository, UserRepository userRepository,
                      MaterialRepository materialRepository) {
        this.objectRepository = objectRepository;
        this.orderRepository = orderRepository;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.materialRepository = materialRepository;
    }

    @GetMapping("/objects")
    public ResponseEntity<Iterable<Object>> getObjects(){
        return ResponseEntity.ok(objectRepository.findAll());
    }

    @GetMapping("/image/{file_name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String file_name) throws IOException {
        var imgFile = new ClassPathResource("image/" + file_name + ".jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @GetMapping("/objects/{id}")
    public ResponseEntity<Optional<Object>> getObject(@PathVariable int id) {
        return ResponseEntity.ok(objectRepository.findById(id));
    }

    @PutMapping("/order")
    public void putOrder(@Validated @RequestBody Order order, Errors errors) {
        if (!errors.hasErrors()) {
            orderRepository.save(order);
        }
    }

    @GetMapping("/docs/{file_name}")
    public ResponseEntity<byte[]> getDoc(@PathVariable String file_name) throws IOException {
        String fileExtension = file_name.substring(file_name.length() - 4);
        String mediaType = switch (fileExtension) {
            case ".doc" -> "application/msword";
            case ".zip" -> "application/zip";
            default -> "";
        };
        var docFile = new ClassPathResource("docs/" + file_name);
        byte[] bytes = StreamUtils.copyToByteArray(docFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(mediaType))
                .body(bytes);
    }

    @GetMapping("/blog")
    public ResponseEntity<Iterable<Blog>> getBlog(){
        return ResponseEntity.ok(blogRepository.findAll());
    }

    @PutMapping("/user")
    public void putUser(@Validated @RequestBody User user, Errors errors) {
        if (!errors.hasErrors()) {
            byte[] password = user.getPassword().getBytes();
            Jargon2.Hasher hasher = jargon2Hasher()
                    .type(Jargon2.Type.ARGON2d) // Data-dependent hashing
                    .memoryCost(65536)  // 64MB memory cost
                    .timeCost(3)        // 3 passes through memory
                    .parallelism(4)     // use 4 lanes and 4 threads
                    .saltLength(16)     // 16 random bytes salt
                    .hashLength(16);    // 16 bytes output hash

            String encodedHash = hasher.password(password).encodedHash();
            user.setPassword(encodedHash);
            userRepository.save(user);
        }
    }

    @PostMapping("/verify")
    public String verifyUser(@Validated @RequestBody UserValidation userValidation, Errors errors) {
        if(!errors.hasErrors()) {
            Optional<User> optional = userRepository.findByEmail(userValidation.getEmail());
            if(optional.isPresent()) {
                User user = optional.get();
                Jargon2.Verifier verifier = jargon2Verifier();
                boolean matches = verifier
                        .hash(user.getPassword())
                        .password(userValidation.getPassword().getBytes())
                        .verifyEncoded();
                if(matches) return UUID.randomUUID().toString();
            }
        }
        return "";
    }

    @GetMapping("/materials")
    public ResponseEntity<Iterable<Material>> getMaterials(){
        return ResponseEntity.ok(materialRepository.findAll());
    }

    @GetMapping("/materials/{id}")
    public ResponseEntity<Optional<Material>> getMaterial(@PathVariable int id){
        return ResponseEntity.ok(materialRepository.findById(id));
    }
}
