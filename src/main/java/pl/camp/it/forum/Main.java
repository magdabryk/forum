package pl.camp.it.forum;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

    public static void main(String[] args) {
        String a = "user1";
        System.out.println(DigestUtils.md5Hex(a));
    }
}
