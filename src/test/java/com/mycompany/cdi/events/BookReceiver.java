/*******************************************************************************
 * Copyright (c) 2014 Sparta Systems, Inc
 ******************************************************************************/

package com.mycompany.cdi.events;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;

/**
 * Created by John.Ament on 3/18/14.
 */
@ApplicationScoped
public class BookReceiver {
    private int anyCount;
    private int fictionCount;
    private int unqualifiedCount;
    private int someQual;
    private int bothQual;
    public void any(@Observes @Any Book book) {
        anyCount++;
    }
    public void fiction(@Observes @BookType("Fiction") Book book) {
        fictionCount++;
    }
    public void unqualified(@Observes Book book) {
        unqualifiedCount++;
    }
    public void someQual(@Observes @SomeQual Book book) {
        someQual++;
    }
    public void someQualFiction(@Observes @SomeQual @BookType("Fiction") Book book) {
        bothQual++;
    }

    public void reset() {
        anyCount = 0;
        fictionCount = 0;
        unqualifiedCount = 0;
        someQual = 0;
        bothQual = 0;
    }

    public int getAnyCount() {
        return anyCount;
    }

    public int getFictionCount() {
        return fictionCount;
    }

    public int getUnqualifiedCount() {
        return unqualifiedCount;
    }

    public int getSomeQual() {
        return someQual;
    }

    public int getBothQual() {
        return bothQual;
    }
}
