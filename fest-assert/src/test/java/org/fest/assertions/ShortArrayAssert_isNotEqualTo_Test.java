/*
 * Created on Feb 14, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.shortArray;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ShortArrayAssert#isNotEqualTo(short[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ShortArrayAssert_isNotEqualTo_Test implements Assert_isNotEqualTo_TestCase {

  private static short[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = shortArray(8, 6);
  }

  @Test
  public void should_pass_if_actual_and_expected_are_not_equal() {
    new ShortArrayAssert(array).isNotEqualTo(shortArray(8));
  }

  @Test
  public void should_fail_if_actual_and_expected_are_equal() {
    expectAssertionError("actual value:<[8, 6]> should not be equal to:<[8, 6]>").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).isNotEqualTo(shortArray(8, 6));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("[A Test] actual value:<[8, 6]> should not be equal to:<[8, 6]>").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).as("A Test")
                                   .isNotEqualTo(shortArray(8, 6));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).overridingErrorMessage("My custom message")
                                   .isNotEqualTo(shortArray(8, 6));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).as("A Test")
                                   .overridingErrorMessage("My custom message")
                                   .isNotEqualTo(shortArray(8, 6));
      }
    });
  }
}