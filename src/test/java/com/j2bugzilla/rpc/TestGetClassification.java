package com.j2bugzilla.rpc;

import com.j2bugzilla.base.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;

@RunWith(MockitoJUnitRunner.class)
public class TestGetClassification {

    @Mock
    private BugzillaConnector conn;

    @Test
    public void test() throws BugzillaException {
        GetClassification classification = new GetClassification(1);

        doAnswer(new Answer<Void>(){

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                GetClassification rpcMethod = (GetClassification)invocation.getArguments()[0];

                Map<Object,Object> hash = new HashMap<Object,Object>();
                Object[] classificationArray = new Object[1];

                Map<Object, Object> classification = new HashMap<Object, Object>();
                classification.put("id",1);
                classification.put("name","Test");

                classificationArray[0] = classification;

                Map<Object, Object> product = new HashMap<Object, Object>();
                product.put("id",17);
                product.put("name","myproduct");

                Object[] products = {product};
                classification.put("products",products);

                hash.put("classifications", classificationArray);
                rpcMethod.setResultMap(hash);

                return null;
            }
        }).when(conn).executeMethod(classification);

        conn.executeMethod(classification);

        assertEquals("Classification ID is incorrect", 1, classification.getClassification().getID());
        assertEquals("Classification name is incorrect", "Test", classification.getClassification().getName());
        assertEquals("product number is incorrect", 1, classification.getClassification().getProducts().size());

        Product product = classification.getClassification().getProducts().get(0);
        assertEquals("Product ID is incorrect", 17, product.getID());
        assertEquals("Product name is incorrect", "myproduct", product.getName());

    }
}
