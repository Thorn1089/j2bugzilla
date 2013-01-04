package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ConnectionException;

@RunWith(Parameterized.class)
public class GetProductIT {

	@Parameters
	public static List<Object[]> getUrls() {
		List<Object[]> urls = new ArrayList<Object[]>();
		
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-3.6-branch/"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.0-branch/"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.2-branch/"});
		urls.add(new Object[]{"https://landfill.bugzilla.org/bugzilla-4.4-branch/"});
		
		return urls;
	}
	
	private String url;
	
	public GetProductIT(String url) {
		this.url = url;
	}
	
	@Test
	public void testGetProduct() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
		GetProduct product = new GetProduct(1);
		conn.executeMethod(product);
		
		assertThat("Product should not be null", product.getProduct(), notNullValue());
	}

}
