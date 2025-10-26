package cl.duoc.android.serviciosweb;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@org.junit.runner.RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class ApplicationTest {
    @org.junit.Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext();
        org.junit.Assert.assertEquals("cl.duoc.android.serviciosweb", appContext.getPackageName());
    }
}