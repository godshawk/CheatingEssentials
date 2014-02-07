package com.luna.lib.util.string.ascii;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: audrey
 * Date: 9/26/13
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractAsciiCharacter implements IAsciiCharacter {
    private List<String> lines = new ArrayList<>(25);
    private int maxWidth = 0;

    public void addLine(String line) {
        if (line != null && !line.isEmpty()) {
            lines.add(line);
            maxWidth = Math.max(line.length(), maxWidth);
        }
    }

    @Override
    public String getLineAt(int index) {
        return index >= 0 && index < lines.size() ? lines.get(index) : null;
    }

    @Override
    public int getLineCount() {
        return lines.size();
    }

    @Override
    public int getWidth() {
        return maxWidth;
    }
}
