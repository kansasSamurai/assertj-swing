/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.swing.driver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.core.MouseButton.RIGHT_BUTTON;

import org.assertj.swing.core.MouseButton;
import org.assertj.swing.test.recorder.ClickRecorder;
import org.assertj.swing.test.recorder.ClickRecorderManager;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link JTreeDriver#clickRow(javax.swing.JTree, int, org.assertj.swing.core.MouseButton)}.
 * 
 * @author Alex Ruiz
 */
public class JTreeDriver_clickRow_withMouseButton_Test extends JTreeDriver_clickCell_TestCase {
  @Rule
  public ClickRecorderManager clickRecorder = new ClickRecorderManager();

  @Test(expected = NullPointerException.class)
  public void should_Throw_Error_If_MouseButton_Is_Null() {
    MouseButton button = null;
    driver.clickRow(tree, 1, button);
  }

  @Test
  public void should_Click_Row() {
    showWindow();
    ClickRecorder recorder = clickRecorder.attachDirectlyTo(tree);
    driver.clickRow(tree, 1, RIGHT_BUTTON);
    recorder.clicked(RIGHT_BUTTON).timesClicked(1);
    assertThat(rowAt(recorder.pointClicked())).isEqualTo(1);
  }

  @Test
  public void should_Throw_Error_If_Row_Is_Out_Of_Bounds() {
    showWindow();
    thrown.expectIndexOutOfBoundsException("The given row <1000> should be between <0> and <6>");
    driver.clickRow(tree, 1000, RIGHT_BUTTON);
  }

  @Test
  public void should_Throw_Error_If_JTree_Is_Disabled() {
    disableTree();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.clickRow(tree, 1, RIGHT_BUTTON);
  }

  @Test
  public void should_Throw_Error_If_JTree_Is_Not_Showing_On_The_Screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.clickRow(tree, 1, RIGHT_BUTTON);
  }
}
