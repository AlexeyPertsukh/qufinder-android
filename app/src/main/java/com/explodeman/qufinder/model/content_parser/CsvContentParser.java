package com.explodeman.qufinder.model.content_parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CsvContentParser implements ContentParser {

    private final String delimiter;
    private final int minArrayContentSize;
    private final int maxArrayContentSize;

    public CsvContentParser(String delimiter, int arrayContentSize) {
        this(delimiter, arrayContentSize, arrayContentSize);
    }

    public CsvContentParser(String delimiter, int minArrayContentSize, int maxArrayContentSize) {
        this.delimiter = delimiter;
        this.minArrayContentSize = minArrayContentSize;
        this.maxArrayContentSize = maxArrayContentSize;
    }

    @Override
    public List<String[]> parse(List<String> strings) {
        List<String[]> result = new ArrayList<>();
        for (String string : strings) {
            String[] arrayContent = string.split(delimiter);
            for (int i = 0; i < arrayContent.length; i++) {
                arrayContent[i] = arrayContent[i].trim();
            }
            int arrayContentSize = arrayContent.length;

            if (arrayContentSize < minArrayContentSize || arrayContentSize > maxArrayContentSize) {
                throw new IllegalArgumentException("Illegal array content size: " + arrayContentSize);
            }

            result.add(arrayContent);
        }
        return result;
    }
}
