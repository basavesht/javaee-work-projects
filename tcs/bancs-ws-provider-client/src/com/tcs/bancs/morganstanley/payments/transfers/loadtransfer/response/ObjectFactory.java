
package com.tcs.bancs.morganstanley.payments.transfers.loadtransfer.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.tcs.bancs.morganstanley.payments.transfers.TransferResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tcs.bancs.morganstanley.payments.transfers.loadtransfer.response package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LoadTransferResponseParam_QNAME = new QName("http://tcs.com/bancs/morganstanley/payments/transfers/loadTransfer/response", "LoadTransferResponseParam");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tcs.bancs.morganstanley.payments.transfers.loadtransfer.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tcs.com/bancs/morganstanley/payments/transfers/loadTransfer/response", name = "LoadTransferResponseParam")
    public JAXBElement<TransferResponse> createLoadTransferResponseParam(TransferResponse value) {
        return new JAXBElement<TransferResponse>(_LoadTransferResponseParam_QNAME, TransferResponse.class, null, value);
    }

}
