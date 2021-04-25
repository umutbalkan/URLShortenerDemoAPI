package com.example.demo;

class UrlNotFoundException extends RuntimeException {

    UrlNotFoundException(long id) {
      super("Could not find URL " + id);
    }
  }