package edu.cmu.lti.f14.hw3_chongshm.annotators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.lti.f14.hw3_chongshm.typesystems.Document;
import edu.cmu.lti.f14.hw3_chongshm.typesystems.Token;
import edu.cmu.lti.f14.hw3_chongshm.utils.Utils;

public class DocumentVectorAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		FSIterator<Annotation> iter = jcas.getAnnotationIndex().iterator();
		if (iter.isValid()) {
			iter.moveToNext();
			Document doc = (Document) iter.get();
			createTermFreqVector(jcas, doc);
		}

	}

	/**
	 * 
	 * @param jcas
	 * @param doc
	 */
	public List<String> tokenize(String doc) {
		List<String> res = new ArrayList<String>();

		for (String s : doc.split("\\s+"))
			res.add(s);
		return res;
	}

	private void createTermFreqVector(JCas jcas, Document doc) {
		/**
		 * calculate term frequency and give it to the token frequency.
		 * 
		 */
		String docText = doc.getText();
		int query = doc.getRelevanceValue();
		// TO DO: construct a vector of tokens and update the tokenList in CAS
		List<String> doctext = tokenize(docText);
		Map<String, Integer> queryVector = new HashMap<String, Integer>();
		Collection<Token> aCollection = new ArrayList<Token> ();
		
		
		
			for (String a : doctext) {
				if (!queryVector.containsKey(a)) {
					queryVector.put(a, 1);
	
				} else {
					int x = queryVector.get(a);
					x++;
					queryVector.put(a, x);					
					}
			}

		FSList fs = new FSList(jcas);
		Iterator <String> it= queryVector.keySet().iterator();
		while (it.hasNext()) { 		
			Token b = new Token(jcas);
			String key = it.next();
		    b.setText(key);
			b.setFrequency(queryVector.get(key));
			aCollection.add(b);
		} 
		
		fs = Utils.fromCollectionToFSList (jcas, aCollection);
		doc.setTokenList(fs);
	}

}
