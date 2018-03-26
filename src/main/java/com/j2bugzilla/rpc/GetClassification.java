package com.j2bugzilla.rpc;

import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.Classification;
import com.j2bugzilla.base.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code GetClassification} class provides access to information on {@link Classification Classifications} active in a particular
 * Bugzilla installation.
 *
 * @author grafuls
 *
 */
public class GetClassification implements BugzillaMethod {

    /**
     * The method Bugzilla will execute via XML-RPC
     */
    private static final String METHOD_NAME = "Classification.get";

    private Map<Object, Object> params = new HashMap<Object, Object>();

    private Map<Object, Object> hash = Collections.emptyMap();

    /**
     * Creates a new {@link GetClassification}, which can be used to retrieve the {@link Classification} associated with the
     * specified ID number.
     * @param id A unique integer ID.
     */
    public GetClassification(int id) { params.put("ids", new Integer[] { id }); }

    /**
     * Creates a new {@link GetClassification}, which can be used to retrieve the collection of {@link Classification} associated
     * with the specified ID numbers.
     * @param ids A collection of unique integer product IDs.
     */
    public GetClassification(int[] ids){
        if(ids == null){
            throw new IllegalArgumentException("ids array cannot be null.");
        }
        final Integer[] idsParam = new Integer[ids.length];
        int ind = 0;
        for (int id : ids) {
            idsParam[ind++] = id;
        }
        params.put("ids", idsParam);
    }

    /**
     * Creates a new {@link GetClassification}, which can be used to retrieve the {@link Classification} associated with the
     * classification name.
     * @param name A classification name.
     */
    public GetClassification(String name) { params.put("names", new String[] { name }); }

    /**
     * Creates a new {@link GetClassification}, which can be used to retrieve the collection of {@link Classification} associated
     * with the specified classification names.
     * @param names A collection of classification names.
     */
    public GetClassification(String[] names) {
        if(names == null){
            throw new IllegalArgumentException("names array cannot be null.");
        }
        final String[] namesParam = new String[names.length];
        int ind = 0;
        for (String name : names){
            namesParam[ind++] = name;
        }
        params.put("names", namesParam);
    }

    /**
     * Returns the classification found in the Bugzilla installation matching the provided name or ID.
     * @return A new {@link Classification}, or null if there are no results to return.
     */
    public Classification getClassification() {
        Object classifications = hash.get("classifications");
        if (classifications == null) { return null; }

        Object[] arr = (Object[])classifications;
        if(arr.length == 0) { return null; }

        Map<Object, Object> classMap = (Map<Object, Object>)arr[0];
        Classification classification = new Classification((Integer)classMap.get("id"), (String)classMap.get("name"));
        String desc = (String)classMap.get("description");
        classification.setDescription(desc);

        if (classMap.get("products") != null) {
            Object[] products = (Object[]) classMap.get("products");
            for (Object product : products){
                Map<Object,Object> productMap = (Map<Object, Object>) product;
                Product prod = new Product((Integer)productMap.get("id"),(String)productMap.get("name"));
                prod.setDescription((String)productMap.get("description"));
                classification.addProduct(prod);
            }
        }
        return classification;
    }

    @Override
    public void setResultMap(Map<Object, Object> hash) { this.hash = hash; }

    @Override
    public Map<Object, Object> getParameterMap() { return Collections.unmodifiableMap(params); }

    @Override
    public String getMethodName() { return METHOD_NAME; }
}
