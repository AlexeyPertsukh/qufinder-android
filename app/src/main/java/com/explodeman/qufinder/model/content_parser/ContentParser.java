package com.explodeman.qufinder.model.content_parser;

import java.util.List;

public interface ContentParser {
    List<String[]> parse(List<String> strings);
}
