package edu.cmu.lti.f14.hw3_chongshm.casconsumers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;
import java.awt.*;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

import edu.cmu.lti.f14.hw3_chongshm.typesystems.Document;
import edu.cmu.lti.f14.hw3_chongshm.typesystems.Info;
import edu.cmu.lti.f14.hw3_chongshm.typesystems.Token;
import edu.cmu.lti.f14.hw3_chongshm.utils.Utils;

public class RetrievalEvaluator extends CasConsumer_ImplBase {

	/** query id number **/
	public ArrayList<Integer> qIdList;
	/** query and text relevant values **/
	public ArrayList<Integer> relList;
	private double cos;
	private int ra;
	private int qid;
	private int rel;
	private String txt;
	public static int count1 = 0;
	public Map<String, Integer> docVector = new HashMap<String, Integer>();
	public Map<Double, Integer> cosVector = new HashMap<Double, Integer>();
	public Map<String, Document> dVector = new HashMap<String, Document>();
	public Map<String, Integer> queryVector = new HashMap<String, Integer>();

	public Object obj11;
	public List<Storage> relinfo = new ArrayList<Storage>();
	public Map<Integer, List<Storage>> questionmap = new HashMap<Integer, List<Storage>>();

	public void initialize() throws ResourceInitializationException {

		qIdList = new ArrayList<Integer>();

		relList = new ArrayList<Integer>();

	}

	/**
	 * TODO :: 1. construct the global word Map 2. keep the word frequency for
	 * each sentence
	 */
	@Override
	public void processCas(CAS aCas) throws ResourceProcessException {

		JCas jcas;
		try {
			jcas = aCas.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}
		// List<Storage> info = new ArrayList<Storage>();

		FSIterator it = jcas.getAnnotationIndex(Document.type).iterator();
		// TODO :: compute the cosine similarity measure
		if (it.hasNext()) {
			Document doc = (Document) it.next();

			// Make sure that your previous annotators have populated this in
			// CAS
			docVector.clear();
			if (doc.getRelevanceValue() == 99) {
				queryVector.clear();

			}

			FSList fsTokenList = doc.getTokenList();
			ArrayList<Token> tokenList = Utils.fromFSListToCollection(
					fsTokenList, Token.class);

			if (doc.getRelevanceValue() == 99) {

				for (Token a : tokenList) {
					queryVector.put(a.getText(), a.getFrequency());
				}
			} else {
				for (Token a : tokenList) {
					docVector.put(a.getText(), a.getFrequency());
				}
			}
			if (doc.getRelevanceValue() == 1) {
				List<Storage> info = new ArrayList<Storage>();
				double cosine_similarity = computeCosineSimilarity(queryVector,
						docVector);
				System.out.println("cosine=" + cosine_similarity + " rank="
						+ " " + "qid=" + doc.getQueryID() + " rel="
						+ doc.getRelevanceValue() + " " + doc.getText());
				Storage abc = new Storage(cos, ra, qid, rel, txt);
				abc.setCosine(cosine_similarity);
				abc.setRel(1);
				abc.setRank(0);
				abc.setQid(doc.getQueryID());
				abc.setText(doc.getText());
				info.add(abc);
				cosVector.put(cosine_similarity, 0);
				questionmap.put(doc.getQueryID(), info);
			} else if (doc.getRelevanceValue() != 99) {

				double cosine_similarity = computeCosineSimilarity(queryVector,
						docVector);
				if (questionmap.containsKey(doc.getQueryID())) {
					List<Storage> info = questionmap.get(doc.getQueryID());
					Storage abc = new Storage(cos, ra, qid, rel, txt);
					abc.setCosine(cosine_similarity);
					abc.setRel(0);
					abc.setRank(0);
					abc.setQid(doc.getQueryID());
					abc.setText(doc.getText());
					info.add(abc);
					cosVector.put(cosine_similarity, 0);
					questionmap.put(doc.getQueryID(), info);
				} else {
					List<Storage> info = new ArrayList<Storage>();
					Storage abc = new Storage(cos, ra, qid, rel, txt);
					abc.setCosine(cosine_similarity);
					abc.setRel(0);
					abc.setRank(0);
					abc.setQid(doc.getQueryID());
					abc.setText(doc.getText());
					info.add(abc);
					cosVector.put(cosine_similarity, 0);
					questionmap.put(doc.getQueryID(), info);
				}

			}

			//

		}

		/*
		 * qIdList.add(doc.getQueryID()); relList.add(doc.getRelevanceValue());
		 * if (!qidVector.containsKey(doc.getQueryID())) {
		 * 
		 * qidVector.put(doc.getQueryID(), dVector);
		 * 
		 * } else {
		 * 
		 * dVector.put(doc.getText(), doc); }
		 */
		// Do something useful here
	}

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 */
	public void collectionProcessComplete(ProcessTrace arg0)
			throws ResourceProcessException, IOException {
		super.collectionProcessComplete(arg0);

		// TODO :: compute the rank of retrieved sentences

		// TODO :: compute the metric:: mean reciprocal rank

		Set<Integer> qSet = questionmap.keySet();
		Iterator<Integer> it = qSet.iterator();
		while (it.hasNext()) {
			int key = it.next();
			//System.out.println(key);
			List<Storage> temp1 = questionmap.get(key);
			for (Storage tmp : temp1) {
				double[] cosSim = new double[temp1.size()];
				for (int j = 0; j < temp1.size(); j++) {
					cosSim[j] = tmp.getCosine();

				}
				Arrays.sort(cosSim);
				
		        for (int k = 0; k < cosSim.length; k++) {
		          if (cosSim[k] == tmp.getCosine()) {
		            tmp.setRank(cosSim.length-k);
		            System.out.println(cosSim.length-k);
		            break;
		          }
		        }
			}
		}
		// questionmap
		Storage abc2 = new Storage(cos, ra, qid, rel, txt);
		Storage s = null;

		double metric_mrr = compute_mrr();
		System.out.println(" (MRR) Mean Reciprocal Rank ::" + metric_mrr);
	}

	/**
	 * 
	 * @return cosine_similarity
	 */
	// TODO :: compute cosine similarity between two sentences

	private double computeCosineSimilarity(Map<String, Integer> queryVector,
			Map<String, Integer> docVector) {
		double cosine_similarity = 0.0, numerator = 0.0, denominator1 = 0.0, denominator2 = 0.0;
		int temp1, temp2;
		Map<String, Integer> Map1 = new HashMap<String, Integer>(queryVector);
		Map<String, Integer> Map2 = new HashMap<String, Integer>(docVector);
		if ((Map1.size() < 1) || (Map2.size() < 1)) {
			return 0.0;
		}
		Set<String> qSet = Map1.keySet();
		Iterator<String> it = qSet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			temp1 = Map1.get(key);
			if (Map2.containsKey(key)) {
				temp2 = Map2.get(key);
			} else {
				temp2 = 0;
			}
			Map2.remove(key);
			numerator += temp1 * temp2;
			denominator1 += temp1 * temp1;
			denominator2 += temp2 * temp2;
		}
		Set<String> dSet = Map2.keySet();
		Iterator<String> it1 = dSet.iterator();
		while (it1.hasNext()) {
			String key = it1.next();
			temp2 = Map2.get(key);
			denominator2 += temp2 * temp2;
		}
		cosine_similarity = numerator
				/ (Math.sqrt(denominator1) * Math.sqrt(denominator2));

		return cosine_similarity;
	}

	/**
	 * 
	 * @return mrr
	 */

	private double compute_mrr() {
		double metric_mrr = 0.0;

		// TODO :: compute Mean Reciprocal Rank (MRR) of the text collection

		return metric_mrr;
	}
}
// for(Token a : tokenList)
// {
// System.out.println(a);
// }
