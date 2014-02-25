/*
 * Created on Nov 18, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.assertj.swing.fixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Arrays.array;
import static org.assertj.swing.edt.GuiActionRunner.execute;

import javax.swing.JComboBox;

import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.exception.ComponentLookupException;
import org.assertj.swing.test.core.RobotBasedTestCase;
import org.assertj.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for {@link JComboBoxFixture#JComboBoxFixture(org.assertj.swing.core.Robot, String)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JComboBoxFixture_constructor_withRobotAndName_Test extends RobotBasedTestCase {
  private MyWindow window;

  @Override
  protected void onSetUp() {
    window = MyWindow.createNew();
  }

  @Test
  public void should_lookup_showing_JComboBox_by_name() {
    robot.showWindow(window);
    JComboBoxFixture fixture = new JComboBoxFixture(robot, "comboBox");
    assertThat(fixture.robot()).isSameAs(robot);
    assertThat(fixture.target()).isSameAs(window.comboBox);
  }

  @Test(expected = ComponentLookupException.class)
  public void should_throw_error_if_JComboBox_with_matching_name_is_not_showing() {
    new JComboBoxFixture(robot, "comboBox");
  }

  @Test(expected = ComponentLookupException.class)
  public void should_throw_error_if_a_JComboBox_with_matching_name_is_not_found() {
    new JComboBoxFixture(robot, "other");
  }

  private static class MyWindow extends TestWindow {
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    final JComboBox comboBox = new JComboBox(array("One"));

    private MyWindow() {
      super(JComboBoxFixture_constructor_withRobotAndName_Test.class);
      comboBox.setName("comboBox");
      addComponents(comboBox);
    }
  }
}