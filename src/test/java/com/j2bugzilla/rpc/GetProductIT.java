package com.j2bugzilla.rpc;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
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
		
		return Arrays.asList(new Object[][] { 
				{ "3.6", "https://landfill.bugzilla.org/bugzilla-3.6-branch/" }, 
				{ "4.0", "https://landfill.bugzilla.org/bugzilla-4.0-branch/" }, 
				{ "4.2", "https://landfill.bugzilla.org/bugzilla-4.2-branch/" },                  
				{ "4.4", "https://landfill.bugzilla.org/bugzilla-4.4-branch/" } });
	
	}
	
	private String version;
	
	private String url;
	
	public GetProductIT(String version, String url) {
		this.url = url;
		this.version = version;
	}
	
	@Test
	public void testGetProduct() throws ConnectionException, BugzillaException {
		BugzillaConnector conn = new BugzillaConnector();
		conn.connectTo(url);
		
		GetProduct product = new GetProduct(1);
		conn.executeMethod(product);
		
		assertThat("Product should not be null", product.getProduct(), notNullValue());
		if (versionGreaterThanFourTwo(version)) {
			assertThat("Versions should not be empty", product.getProduct().getProductVersions().size(), 
					not(0));
		}
	}
	
	private boolean versionGreaterThanFourTwo(String version) {
		String[] pieces = version.split(".");
		int major = Integer.parseInt(pieces[0]);
		int minor = Integer.parseInt(pieces[1]);
		return major >= 4 && minor >= 2;
	}

}
