package com.baizhi.test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;
import java.util.Map;

public class JWTtest {

  public static void main(String[] args) {
    Map<String, Object> map = new HashMap<>();
    map.put("hhz","123456");
    String token = createToken(map);
    System.out.println(token);
  }
  private static String createToken(Map<String, Object> claims) {
    String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "abcd")
        .compact();
    return token;
  }
}
