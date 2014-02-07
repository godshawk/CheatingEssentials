package com.luna.lib.event.handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.luna.lib.annotations.EventListener;
import com.luna.lib.event.EventBase;
import com.luna.lib.event.enums.EnumEventPriority;

public final class EventManager {
    private static volatile EventManager				instance	= new EventManager( );

    private volatile Map< Object, LinkedList< Method >>	eventListeners;

    private EventManager( ) {
        eventListeners = new ConcurrentHashMap<>( );
        eventListeners.clear( );
    }

    public static final EventManager getInstance( ) {
        return instance;
    }

    public final void addListener( final Object o ) {
        final Method[ ] declared = o.getClass( ).getDeclaredMethods( );
        final LinkedList< Method > listeners = new LinkedList<>( );

        for( final Method e : declared ) {
            if( e.isAnnotationPresent( EventListener.class ) ) {
                listeners.add( e );
            }
        }

        synchronized( eventListeners ) {
            eventListeners.put( o, listeners );
        }
    }

    public final void removeListener( final Object o ) {
        synchronized( eventListeners ) {
            for( final Map.Entry< Object, LinkedList< Method >> e : eventListeners.entrySet( ) ) {
                if( e.getKey( ).equals( o ) ) {
                    eventListeners.remove( e.getKey( ) );
                }
            }
        }
    }

    public final Map< Object, LinkedList< Method >> getEventListeners( ) {
        synchronized( eventListeners ) {
            return eventListeners;
        }
    }

    public final void fire( final EventBase event ) {
        synchronized( eventListeners ) {
            for( final EnumEventPriority pr : EnumEventPriority.values( ) ) {
                for( final Map.Entry< Object, LinkedList< Method >> e : getEventListeners( )
                        .entrySet( ) ) {
                    final List< Method > f = e.getValue( );
                    for( final Method g : f ) {
                        final EventListener h = g.getAnnotation( EventListener.class );
                        if( h.event( ).equals( event.getClass( ) ) ) {
                            if( !h.priority( ).equals( pr ) ) {
                                continue;
                            }
                            g.setAccessible( true );
                            try {
                                if( g.getParameterAnnotations( ).length == 0 ) {
                                    g.invoke( e.getKey( ) );
                                }
                                if( g.getParameterAnnotations( ).length == 1 ) {
                                    g.invoke( e.getKey( ), event );
                                }
                            } catch( final IllegalAccessException | InvocationTargetException e1 ) {
                                e1.printStackTrace( );
                            }
                        }
                    }
                }
            }
        }
    }
}