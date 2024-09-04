package com.uvocab.api.auth;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;

public class KeyGenerator {

  private static final KeyPair KEY_PAIR;

  static {
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
      keyPairGenerator.initialize(256);
      KEY_PAIR = keyPairGenerator.generateKeyPair();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao gerar chaves: ", e);
    }
  }

  public static ECPrivateKey getPrivateKey() {
    return (ECPrivateKey) KEY_PAIR.getPrivate();
  }

  public static ECPrivateKey getPublicKey() {
    return (ECPrivateKey) KEY_PAIR.getPublic();
  }
}
