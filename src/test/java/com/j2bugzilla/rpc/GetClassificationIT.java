package com.j2bugzilla.rpc;

import com.j2bugzilla.base.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class GetClassificationIT {

    @Parameterized.Parameters
    public static List<Object[]> getUrls() {
        List<Object[]> urls = new ArrayList<Object[]>();

        urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-3.6-branch/"});
        urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.0-branch/"});
        urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.2-branch/"});
        urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.4-branch/"});

        return urls;
    }

    private String url;

    public GetClassificationIT(String url) {
        this.url = url;
    }

    @Test
    public void testGetClassification() throws ConnectionException, BugzillaException {
        BugzillaConnector conn = new BugzillaConnector();
        conn.connectTo(url);

        GetClassification classification = new GetClassification(1);
        conn.executeMethod(classification);

        Classification classes =  classification.getClassification();
        assertThat("Classification should not be null", classes, notNullValue());
    }
}
