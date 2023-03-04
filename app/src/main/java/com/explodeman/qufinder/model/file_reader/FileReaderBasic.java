package com.explodeman.qufinder.model.file_reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderBasic implements FileReader {
  private final String fileName;

  public FileReaderBasic(String fileName) {
    this.fileName = fileName;
  }

  public List<String> read() {
    List<String> strings = new ArrayList<>();
    try {
      java.io.FileReader fr = new java.io.FileReader(fileName);
      Scanner scan = new Scanner(fr);
      while (scan.hasNextLine()) {
        strings.add(scan.nextLine());
      }
      fr.close();
    } catch (IOException ex) {
      throw new IllegalArgumentException("filed read file: " + fileName);
    }

    return strings;
  }

}
