package se.atg.service.harrykart.java.rest.service;

public interface HostMatch<I,O> {

    public O computeResult(I inputRequest) throws Exception;
}
