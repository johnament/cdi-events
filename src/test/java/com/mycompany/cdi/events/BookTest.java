/*******************************************************************************
 * Copyright (c) 2014 Sparta Systems, Inc
 ******************************************************************************/

package com.mycompany.cdi.events;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by John.Ament on 3/18/14.
 */
@RunWith(Arquillian.class)
public class BookTest {
    @Deployment
    public static JavaArchive create() {
        return ShrinkWrap.create(JavaArchive.class,"bookTest.jar")
                .addClasses(Book.class, BookReceiver.class, BookType.class, SomeQual.class)
                .addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
    }

    @Inject
    @SomeQual
    @BookType("Fiction")
    private Event<Book> bookEvent;

    @Inject
    private BookReceiver bookReceiver;

    @Before
    public void init() {
        bookReceiver.reset();
    }

    @Test
    public void testFiredAndCount() {
        bookEvent.fire(new Book());
        Assert.assertEquals(1, bookReceiver.getAnyCount());
        Assert.assertEquals(1, bookReceiver.getUnqualifiedCount());
        Assert.assertEquals(1, bookReceiver.getSomeQual());
        Assert.assertEquals(1, bookReceiver.getFictionCount());
        Assert.assertEquals(1, bookReceiver.getBothQual());
    }

    @Test
    public void testFiredAndCountBook() {
        bookEvent.fire(new Book());
        Assert.assertEquals(1, bookReceiver.getAnyCount());
        Assert.assertEquals(0, bookReceiver.getUnqualifiedCount());
        Assert.assertEquals(0, bookReceiver.getSomeQual());
        Assert.assertEquals(0, bookReceiver.getFictionCount());
        Assert.assertEquals(1, bookReceiver.getBothQual());
    }
}
