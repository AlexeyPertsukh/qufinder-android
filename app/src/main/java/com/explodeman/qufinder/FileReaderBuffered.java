package com.explodeman.qufinder;

import com.explodeman.qufinder.model.file_reader.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderBuffered implements FileReader {
  private final InputStream file;

  public FileReaderBuffered(InputStream file) {
    this.file = file;
  }

  public List<String> read() {
    List<String> list = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        list.add(line);
      }
      reader.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("read file error");
    }

    return list;
  }

}
