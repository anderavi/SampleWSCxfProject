package com.sample.client;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.model.iface.Operation;

public class WsdlAnalyzer {

    public static void main(String[] args) throws Exception {
        WsdlProject project = new WsdlProject();
        WsdlInterface[] wsdls = WsdlImporter.importWsdl(project, "http://localhost:9090/EchoService?wsdl");
        WsdlInterface wsdl = wsdls[0];
        for (Operation operation : wsdl.getOperationList()) {
            WsdlOperation op = (WsdlOperation) operation;
            System.out.println("OP:"+op.getName());
            System.out.println(op.createRequest(true));
            System.out.println("Response:");
            System.out.println(op.createResponse(true));
        }
    }
}