package ga.skif.task;

import ga.skif.task.client.MytaskTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class MytaskSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for Mytask");
    suite.addTestSuite(MytaskTest.class);
    return suite;
  }
}
