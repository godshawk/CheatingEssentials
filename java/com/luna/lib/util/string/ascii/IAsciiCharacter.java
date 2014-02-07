package com.luna.lib.util.string.ascii;

/**
 * Created with IntelliJ IDEA.
 * User: audrey
 * Date: 9/26/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IAsciiCharacter {
    public int getLineCount();
    public int getWidth();
    public String getLineAt(int index);
}
