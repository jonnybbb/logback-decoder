/**
 * Copyright (C) 2012, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.decoder.regex;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.util.StatusPrinter;
import ch.qos.logback.decoder.PatternNames;

/**
 * Tests the {@link PatternLayoutRegexUtil} class
 * 
 * @author Anthony Trinh
 */
public class PatternLayoutRegexUtilTest {
  private PatternLayoutRegexUtil regexifier;
  static private ContextBase context;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    context = new ContextBase();
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    regexifier = new PatternLayoutRegexUtil(context);
  }

  @After
  public void tearDown() throws Exception {
    StatusPrinter.printInCaseOfErrorsOrWarnings(context);
  }

  @Test
  public void testDatePatternToRegex() {
    final String REGEX = "(?<" + PatternNames.DATE + ">" + RegexPatterns.Common.DATE_ISO8601_REGEX + ")";
    
    for (String p : Arrays.asList("%d", "%date")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
    
    // TODO: How do we test different locales?
  }

  @Test
  public void testDatePatternToRegex2() {
    final String REGEX = "(?<" + PatternNames.DATE + ">\\d{2}:\\d{2}:\\d{2})";
    
    assertEquals(REGEX, regexifier.toRegex("%d{HH:mm:ss}"));
  }
  
  @Test
  public void testLineOfCallerPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.LINE_OF_CALLER + ">" + RegexPatterns.LINE_OF_CALLER_REGEX + ")";
    
    for (String p : Arrays.asList("%L", "%line")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testFileOfCallerPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.FILE_OF_CALLER + ">" + RegexPatterns.FILE_OF_CALLER_REGEX + ")";
    
    for (String p : Arrays.asList("%F", "%file")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }

  @Test
  public void testRelativeTimePatternToRegex() {
    final String REGEX = "(?<" + PatternNames.RELATIVE_TIME + ">" + RegexPatterns.RELATIVE_TIME_REGEX + ")";
    
    for (String p : Arrays.asList("%r", "%relative")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testLevelPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.LEVEL + ">" + RegexPatterns.LEVEL_REGEX + ")";
    
    for (String p : Arrays.asList("%le", "%level", "%p")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testThreadPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.THREAD_NAME + ">" + RegexPatterns.THREAD_NAME_REGEX + ")";
    
    for (String p : Arrays.asList("%t", "%thread")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testLoggerPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.LOGGER_NAME + ">" + RegexPatterns.LOGGER_NAME_REGEX + ")";
    
    for (String p : Arrays.asList("%lo", "%logger", "%c")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
    
    // TODO: Need to test for different patterns based on length specifier (%c{10})
  }
  
  @Test
  public void testMessagePatternToRegex() {
    final String REGEX = "(?<" + PatternNames.MESSAGE + ">" + RegexPatterns.MESSAGE_REGEX + ")";
    
    for (String p : Arrays.asList("%msg", "%message", "%m")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testClassOfCallerPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.CLASS_OF_CALLER + ">" + RegexPatterns.CLASS_OF_CALLER_REGEX + ")";
    
    for (String p : Arrays.asList("%C", "%class")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
    
    // TODO: Need to test for different patterns based on length specifier (%C{10})
  }
  
  @Test
  public void testMethodOfCallerPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.METHOD_OF_CALLER + ">" + RegexPatterns.METHOD_OF_CALLER_REGEX + ")";
    
    for (String p : Arrays.asList("%M", "%method")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testMDCPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.MDC + ">" + RegexPatterns.MDC_REGEX + ")";
    
    for (String p : Arrays.asList("%X", "%mdc")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testThrowableProxyPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.EXCEPTION + ">" + RegexPatterns.EXCEPTION_REGEX + ")";

    for (String p : Arrays.asList("%ex", "%exception", "%throwable")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testExtendedExceptionPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.EXT_EXCEPTION + ">" + RegexPatterns.EXCEPTION_REGEX + ")";

    for (String p : Arrays.asList("%xEx", "%xException", "%xThrowable")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testRootExceptionPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.ROOT_EXCEPTION + ">" + RegexPatterns.EXCEPTION_REGEX + ")";

    for (String p : Arrays.asList("%rEx", "%rootException")) {
      assertEquals(REGEX, regexifier.toRegex(p));
    }
  }
  
  @Test
  public void testMarkerPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.MARKER + ">" + RegexPatterns.MARKER_REGEX + ")";
    
    assertEquals(REGEX, regexifier.toRegex("%marker"));
  }
  
  @Test
  public void testCallerDataPatternToRegex() {
    final String REGEX = "(?<" + PatternNames.CALLER_STACKTRACE + ">" + RegexPatterns.CALLER_STACKTRACE_REGEX + ")";
    
    assertEquals(REGEX, regexifier.toRegex("%caller"));
  }
  
  @Test
  public void testMixedPatternsToRegex() {
    final String REGEX = "(?<" + PatternNames.DATE + ">" + RegexPatterns.Common.DATE_ISO8601_REGEX + ") " + 
        "(?<" + PatternNames.FILE_OF_CALLER + ">" + RegexPatterns.FILE_OF_CALLER_REGEX + "):" + 
        "(?<" + PatternNames.LINE_OF_CALLER + ">" + RegexPatterns.LINE_OF_CALLER_REGEX + ") " + 
        "(?<" + PatternNames.DATE + ">" + RegexPatterns.Common.DATE_ISO8601_REGEX + ")";
    
    assertEquals(REGEX, regexifier.toRegex("%d %F:%L %d"));
  }
  
  @Test
  public void testLevelPatternToRegexWithRegexCharsInLiteral() {
    final String REGEX = "\\[(?<" + PatternNames.LEVEL + ">" + RegexPatterns.LEVEL_REGEX + ")\\] \\.\\.\\.";
    assertEquals(REGEX, regexifier.toRegex("[%le] ..."));
  }
  
}
