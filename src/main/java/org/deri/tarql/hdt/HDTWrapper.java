package org.deri.tarql.hdt;

import java.util.Iterator;

import org.apache.jena.graph.Triple;
import org.rdfhdt.hdt.triples.TripleString;

public class HDTWrapper implements Iterator<TripleString> {
	private Iterator<Triple> it;
	
	public HDTWrapper(Iterator<Triple> it) {
		this.it = it;
	}
	
	public boolean hasNext() {
		return it.hasNext();
	}
	
	public TripleString next() {
		Triple t = it.next();
		
		return new TripleString(
			t.getSubject().toString(),
			t.getPredicate().toString(),
			t.getObject().toString());
	}
}