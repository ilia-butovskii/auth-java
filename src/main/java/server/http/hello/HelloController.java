package server.http.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.services.auth.JwtTokens;

import javax.xml.ws.Response;
import java.security.*;
import java.util.Base64;

@RestController
public class HelloController {

// helper

//    @RequestMapping("/hello")
//    public ResponseEntity hello(){
//        KeyPairGenerator generator = null;
//
//        try {
//            generator = KeyPairGenerator.getInstance("RSA");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//
//        generator.initialize(2048);
//
//        KeyPair pair = generator.generateKeyPair();
//
//        PrivateKey prvKey = pair.getPrivate();
//        PublicKey pubKey = pair.getPublic();
//
//        String pubkey = Base64.getEncoder().encodeToString(pubKey.getEncoded());
//        String prvkey = Base64.getEncoder().encodeToString(prvKey.getEncoded());
//
//        return ResponseEntity.ok().body(new JwtTokens(pubkey, prvkey));
//    }

}
