package server.services;

import org.apache.commons.codec.digest.DigestUtils;

public class UserUtil {
    public static String calculateHash(String password){
        return DigestUtils.sha256Hex(password);
    }
}
