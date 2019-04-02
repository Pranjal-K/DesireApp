package com.pranjal.customnavigationdrawer;

/**
 * Created by Belal on 10/18/2017.
 */


public class formDisplayClass {

    private String name;
    private String noOfCopies;
    private String custnm,custaddr,custmob,custemail;

    public formDisplayClass()
    {

    }

    public String getCustnm() {
        return custnm;
    }

    public String getCustaddr() {
        return custaddr;
    }

    public String getCustmob() {
        return custmob;
    }

    public String getCustemail() {
        return custemail;
    }

    public formDisplayClass(String name, String noOfCopies, String custnm, String custaddr, String custmob, String custemail) {
        this.name = name;
        this.noOfCopies = noOfCopies;
        this.custnm = custnm;
        this.custaddr = custaddr;
        this.custmob = custmob;
        this.custemail = custemail;
    }

    public String getName() {
        return name;
    }

    public String  getnoOfCopies() {
        return noOfCopies;
    }

}