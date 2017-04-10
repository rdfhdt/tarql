
package org.deri.tarql.hdt;

import java.io.IOException;
import java.util.Iterator;

import org.apache.jena.graph.Triple;
import org.rdfhdt.hdt.exceptions.ParserException;
import org.rdfhdt.hdt.hdt.HDT;
import org.rdfhdt.hdt.hdt.HDTManager;
import org.rdfhdt.hdt.options.HDTSpecification;

public class HDTWriter {
	private final String out;
	private final Iterator<Triple> triples;
	private final String baseURI;

	public HDTWriter(String out, Iterator<Triple> triples, String baseURI) {
		this.out = out;
		this.triples = triples;
		this.baseURI = baseURI;
	}

	public void writeHDT() throws IOException {
		HDTWrapper it = new HDTWrapper(triples);

		try {
			HDT hdt = HDTManager.generateHDT(it, baseURI, new HDTSpecification(), null);
			hdt.saveToHDT(out, null);
		} catch (ParserException e) {
			throw new IOException(e);
		}
	}
}