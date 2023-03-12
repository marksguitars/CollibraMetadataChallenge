package com.collibra.codingchallenge.demo;

public class AssetNotFoundException extends RuntimeException {

  public AssetNotFoundException(String id) {
    super("Could not find Asset " + id);
  }
}
