/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author sailfish
 */
public class StaticUtility {
    
    private static AtomicLong idCounter = new AtomicLong();

    public static String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }

    private StaticUtility() {
    }
}
