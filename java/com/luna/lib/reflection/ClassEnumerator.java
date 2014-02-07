package com.luna.lib.reflection;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.luna.lib.loggers.BasicLogger;
import com.luna.lib.loggers.enums.EnumLogType;

public class ClassEnumerator {
	/**
	 * Singleton instance
	 */
	private static volatile ClassEnumerator	instance;
	
	/**
	 * Returns the singleton instance creates one if the instance is null
	 * 
	 * @return instance
	 */
	public static ClassEnumerator getInstance() {
		if( instance == null ) {
			BasicLogger.getInstance().setDebug( true );
			instance = new ClassEnumerator();
		}
		return instance;
	}
	
	/**
	 * Parses a directory for jar files and class files
	 * 
	 * Recurses through if necessary
	 * 
	 * @param directory
	 *            directory to parse
	 * @return class array
	 */
	public List< Class< ? >> getClassesFromExternalDirectory( final File directory ) {
		final List< Class< ? >> classes = new ArrayList< Class< ? >>();
		for( final File file : directory.listFiles() ) {
			try {
				final ClassLoader classLoader = new URLClassLoader( new URL[ ] {
					file.toURI().toURL()
				}, /* this */directory.getClass().getClassLoader() );
				if( file.getName().toLowerCase().trim().endsWith( ".class" ) ) {
					BasicLogger.getInstance().log( EnumLogType.DEBUG, file.getName() );
					classes.add( classLoader.loadClass( file.getName().replace( ".class", "" )
							.replace( "/", "." ) ) );
				}
				if( file.getName().toLowerCase().trim().endsWith( ".jar" ) ) {
					classes.addAll( getClassesFromJar( file, classLoader ) );
				}
				if( file.isDirectory() ) {
					classes.addAll( getClassesFromExternalDirectory( file ) );
				}
			} catch( final MalformedURLException e ) {
				e.printStackTrace();
			} catch( final ClassNotFoundException e ) {
				e.printStackTrace();
			}
		}
		return classes;
	}
	
	/**
	 * Returns the class array of all classes within a package
	 * 
	 * @param classe
	 *            class to get code source location for
	 * 
	 * @return class array
	 */
	public Class< ? >[ ] getClassesFromPackage( final Class< ? > classe ) {
		final List< Class< ? >> classes = new ArrayList< Class< ? >>();
		URI uri = null;
		try {
			uri = classe.getProtectionDomain().getCodeSource().getLocation().toURI();
		} catch( final URISyntaxException e ) {
			e.printStackTrace();
		}
		if( uri == null ) {
			throw new RuntimeException( "No uri for "
					+ classe.getProtectionDomain().getCodeSource().getLocation() );
		}
		BasicLogger.getInstance().log( EnumLogType.DEBUG, "URI: " + uri.toString() );
		
		classes.addAll( processDirectory( new File( uri ), "" ) );
		return classes.toArray( new Class[ classes.size() ] );
	}
	
	/**
	 * Returns all class files inside a jar
	 * 
	 * @param file
	 *            jar file
	 * @param classLoader
	 *            classloader created previously using the jar file
	 * @return class list
	 */
	public List< Class< ? >> getClassesFromJar( final File file, final ClassLoader classLoader ) {
		final List< Class< ? >> classes = new ArrayList< Class< ? >>();
		try {
			final JarFile jarFile = new JarFile( file );
			final Enumeration< JarEntry > enumeration = jarFile.entries();
			while( enumeration.hasMoreElements() ) {
				final JarEntry jarEntry = enumeration.nextElement();
				if( jarEntry.isDirectory() || !jarEntry.getName().toLowerCase().trim().endsWith( ".class" ) ) {
					continue;
				}
				classes.add( classLoader.loadClass( jarEntry.getName().replace( ".class", "" )
						.replace( "/", "." ) ) );
			}
			jarFile.close();
		} catch( final IOException e ) {
			e.printStackTrace();
		} catch( final ClassNotFoundException e ) {
			e.printStackTrace();
		}
		return classes;
	}
	
	/**
	 * Processes a directory and retrieves all classes from it and its
	 * subdirectories
	 * 
	 * Recurses if necessary
	 * 
	 * @param directory
	 *            directory file to traverse
	 * @return list of classes
	 */
	private List< Class< ? >> processDirectory( final File directory, final String append ) {
		final List< Class< ? >> classes = new ArrayList< Class< ? >>();
		final String[ ] files = directory.list();
		for( final String fileName : files ) {
			String className = null;
			if( fileName.endsWith( ".class" ) ) {
				className = append + '.' + fileName.replace( ".class", "" );
			}
			if( className != null ) {
				classes.add( loadClass( className.substring( 1 ) ) );
			}
			final File subdir = new File( directory, fileName );
			if( subdir.isDirectory() ) {
				classes.addAll( processDirectory( subdir, append + "." + fileName ) );
			}
		}
		return classes;
	}
	
	/**
	 * Loads a class based upon the name
	 * 
	 * @param className
	 *            name of class (.class is pre removed)
	 * @return Class if it was loaded properly
	 */
	private Class< ? > loadClass( final String className ) {
		try {
			return Class.forName( className );
		} catch( final ClassNotFoundException e ) {
			throw new RuntimeException( "Error loading class '" + className + "'" );
		}
	}
}
