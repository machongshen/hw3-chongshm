package edu.cmu.lti.f14.hw3_chongshm.casconsumers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
import edu.cmu.lti.f14.hw3_chongshm.utils.StanfordLemmatizer;
import edu.cmu.lti.f14.hw3_chongshm.utils.Utils;
import edu.cmu.lti.f14.hw3_chongshm.casconsumers.Storage;

public class RetrievalEvaluator extends CasConsumer_ImplBase {
	/**
	 * Description: A CasConsumer that generate the consumer. By using the
	 * equation provided in the pdf, I realized the function which could
	 * calculate the cosine-similarity. In addition, I also wrote the function
	 * to calculate Jaccard coefficient and Tversky coefficient.
	 * 
	 * @author machongshen
	 * 
	 */
	public static final String PARAM_INPUTDIR = "./src/main/resources/stopwords.txt";

	private ArrayList<String> stopwords;
	/** query id number **/
	public ArrayList<Integer> qIdList;
	/** query and text relevant values **/
	public ArrayList<Integer> relList;
	private double cos;
	private int ra;
	private int qid;
	private int rel;
	private String txt;
	public Map<String, Integer> docVector = new HashMap<String, Integer>();
	public Map<Double, Integer> cosVector = new HashMap<Double, Integer>();
	public Map<String, Integer> queryVector = new HashMap<String, Integer>();
	public List<Storage> relinfo = new ArrayList<Storage>();
	public Map<Integer, List<Storage>> questionmap = new HashMap<Integer, List<Storage>>();
	public static final String PARAM_OUTPUTDIR = "./report.txt";
	public String query = null;
	public String sentence = null;

	/**
	 * Description: Name of configuration parameter that must be set to the path
	 * of a directory containing input files.
	 * 
	 * @param PARAM_INPUTDIR
	 *            The path of input material
	 */
	public void initialize() throws ResourceInitializationException {
		String sentence = PARAM_OUTPUTDIR;
		File f = new File(sentence);
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String stopword = (PARAM_INPUTDIR).trim();

		try {
			// System.out.println("good");
			stopwords = linereadfile(stopword);
		} catch (Exception e) {
		}
		qIdList = new ArrayList<Integer>();

		relList = new ArrayList<Integer>();

	}

	/**
	 * 1. construct the global word Map 2. keep the word frequency for each
	 * sentence Compute Cosine Similarity, store the result in hashmap.
	 */
	@Override
	public void processCas(CAS aCas) throws ResourceProcessException {

		JCas jcas;
		try {
			jcas = aCas.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		FSIterator it = jcas.getAnnotationIndex(Document.type).iterator();
		// TODO :: compute the cosine similarity measure
		if (it.hasNext()) {
			Document doc = (Document) it.next();
			sentence = null;
			// Make sure that your previous annotators have populated this in
			// CAS
			docVector.clear();
			if (doc.getRelevanceValue() == 99) {
				queryVector.clear();
				query = null;
			}

			FSList fsTokenList = doc.getTokenList();
			ArrayList<Token> tokenList = Utils.fromFSListToCollection(
					fsTokenList, Token.class);

			if (doc.getRelevanceValue() == 99) {
				query = doc.getText();
				for (Token a : tokenList) {
					queryVector.put(a.getText(), a.getFrequency());
				}
			} else {
				sentence = doc.getText();
				for (Token a : tokenList) {
					docVector.put(a.getText(), a.getFrequency());
				}
			}
			if (doc.getRelevanceValue() == 1) {
				List<Storage> info = new ArrayList<Storage>();
				// double cosine_similarity =
				// computeCosineSimilarity(queryVector,
				// docVector);
				double cosine_similarity = tversky(sentence, query);
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

				// double cosine_similarity =
				// computeCosineSimilarity(queryVector,
				// docVector);
				double cosine_similarity = tversky(sentence, query);
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

	}

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 * 
	 */
	@SuppressWarnings("resource")
	public void collectionProcessComplete(ProcessTrace arg0)
			throws ResourceProcessException, IOException {
		super.collectionProcessComplete(arg0);

		// TODO :: compute the rank of retrieved sentences

		// TODO :: compute the metric:: mean reciprocal rank
		List<Storage> output = new ArrayList<Storage>();
		try {
			String sentence = PARAM_OUTPUTDIR;
			FileWriter fw = new FileWriter(sentence, true);
			BufferedWriter outputwrite = new BufferedWriter(fw);
			for (int i = 1; i <= questionmap.size(); i++) {
				List<Storage> temp1 = questionmap.get(i);
				double[] comp = new double[temp1.size()];
				for (int j = 0; j < temp1.size(); j++) {
					Storage ddt = temp1.get(j);
					if (ddt.getRel() == 1) {
						output.add(ddt);
					}
					comp[j] = ddt.getCosine();

				}
				Arrays.sort(comp);
				Storage dt = output.get(i - 1);
				for (int k = 0; k < comp.length; k++) {
					if (comp[k] == dt.getCosine()) {
						dt.setRank(comp.length - k);
						System.out.println("cosine="
								+ String.format("%.4f", dt.getCosine())
								+ " rank=" + dt.getRank() + " qid="
								+ dt.getQid() + " rel=" + dt.getRel() + " "
								+ dt.getText());
						outputwrite.append("cosine="
								+ String.format("%.4f", dt.getCosine())
								+ " rank=" + dt.getRank() + " qid="
								+ dt.getQid() + " rel=" + dt.getRel() + " "
								+ dt.getText());
						outputwrite.newLine();
						outputwrite.flush();
						break;

					}
				}

			}

			double metric_mrr = compute_mrr(output);
			outputwrite.newLine();
			outputwrite.newLine();
			System.out.println("(MRR) Mean Reciprocal Rank ::"
					+ String.format("%.4f", metric_mrr));
			outputwrite.append("(MRR) Mean Reciprocal Rank ::"
					+ String.format("%.4f", metric_mrr));

			outputwrite.flush();
			fw.close();
			// outputwrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * According to the equation in the instruction of homework3, this function
	 * will return the value after calculating.
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
	 * This function is designed for Mean Reciprocal Rank based on the equation.
	 * 
	 * @return mrr
	 */

	private double compute_mrr(List<Storage> list) {
		double metric_mrr = 0.0;
		int q = list.size();

		for (int i = 0; i < q; i++) {
			if (list.get(i).getRank() != 0) {
				metric_mrr += 1.0 / list.get(i).getRank();
			}
		}

		metric_mrr = metric_mrr / q;
		// TODO :: compute Mean Reciprocal Rank (MRR) of the text collection

		return metric_mrr;
	}

	/**
	 * This function is designed for caculating Jaccard coefficient based on the
	 * equation.
	 * 
	 * @return Jaccard index
	 */
	public double jc(String s, String t) {
		StanfordLemmatizer k = new StanfordLemmatizer();
		s = s.replaceAll("[" + ",;\".?':!" + "]+", "");
		s = s.replace("-", "");
		s = k.stemText(s);
		t = t.replaceAll("[" + ",;\".?':!" + "]+", "");
		t = t.replace("-", "");
		t = k.stemText(t);
		String[] sSplit = s.split(" ");
		String[] tSplit = t.split(" ");

		// calculate intersection
		List<String> intersection = new ArrayList<String>();
		for (int i = 0; i < sSplit.length; i++) {
			for (int j = 0; j < tSplit.length; j++) {
				if (!intersection.contains(sSplit[i])
						&& (!stopwords.contains(sSplit[i]))) // no duplicate
					if (sSplit[i].equals(tSplit[j])) // has intersection
					{
						intersection.add(sSplit[i]);
						break;
					}
			}
		}

		// calculate union
		List<String> union = new ArrayList<String>();
		if (sSplit.length > tSplit.length) // calculate big tupple first
		{
			for (int i = 0; i < sSplit.length; i++)
				if (!union.contains(sSplit[i])
						&& (!stopwords.contains(sSplit[i])))
					union.add(sSplit[i]);
			for (int i = 0; i < tSplit.length; i++)
				if (!union.contains(tSplit[i])
						&& (!stopwords.contains(tSplit[i])))
					union.add(tSplit[i]);
		} else {
			for (int i = 0; i < tSplit.length; i++)
				if (!union.contains(tSplit[i])
						&& (!stopwords.contains(tSplit[i])))
					union.add(tSplit[i]);
			for (int i = 0; i < sSplit.length; i++)
				if (!union.contains(sSplit[i])
						&& (!stopwords.contains(sSplit[i])))
					union.add(sSplit[i]);

		}
		return ((double) intersection.size()) / ((double) union.size());
	}

	/**
	 * This function is designed for caculating Tversky coefficient based on the
	 * equation.
	 * 
	 * @return Tversky index
	 */
	public double tversky(String s, String t) {

		double abc = 0.0;
		double Sum1 = 0.0;
		double Sum2 = 0.0;
		double intersectionSum = 0.0;
		StanfordLemmatizer k = new StanfordLemmatizer();
		s = s.replaceAll("[" + ",;\".?':!" + "]+", "");
		s = s.replace("-", "");
		s = k.stemText(s);
		t = t.replaceAll("[" + ",;\".?':!" + "]+", "");
		t = t.replace("-", "");
		t = k.stemText(t);
		String[] sSplit = s.split(" ");
		String[] tSplit = t.split(" ");
		List<String> intersection = new ArrayList<String>();
		for (int i = 0; i < sSplit.length; i++) {
			for (int j = 0; j < tSplit.length; j++) {
				if (!intersection.contains(sSplit[i])
						&& (!stopwords.contains(sSplit[i]))) // no duplicate
					if (sSplit[i].equals(tSplit[j])) // has intersection
					{
						Sum1 += Math.abs((i - j));
						Sum2 += Math.abs((j - i));
						intersection.add(sSplit[i]);
						break;
					}
			}
		}
		return (double) intersection.size()
				/ (double) (intersection.size() + Sum2+ Sum1);
	}

	/**
	 * This function is designed for caculating Dice coefficient based on the
	 * equation.
	 * 
	 * @return Dice index
	 */
	public double dice(String s, String t) {

		double abc = 0.0;
		double Sum1 = 0.0;
		double Sum2 = 0.0;
		double intersectionSum = 0.0;
		StanfordLemmatizer k = new StanfordLemmatizer();
		s = s.replaceAll("[" + ",;\".?':!" + "]+", "");
		s = s.replace("-", "");
		s = k.stemText(s);
		t = t.replaceAll("[" + ",;\".?':!" + "]+", "");
		t = t.replace("-", "");
		t = k.stemText(t);
		String[] sSplit = s.split(" ");
		String[] tSplit = t.split(" ");
		List<String> intersection = new ArrayList<String>();
		for (int i = 0; i < sSplit.length; i++) {
			for (int j = 0; j < tSplit.length; j++) {
				if (!intersection.contains(sSplit[i])
						&& (!stopwords.contains(sSplit[i]))) // no duplicate
					if (sSplit[i].equals(tSplit[j])) // has intersection
					{
						Sum1 += Math.abs((i - j));
						Sum2 += Math.abs((j - i));
						intersection.add(sSplit[i]);
						break;
					}
			}
		}
		return (double) 2 * intersection.size()
				/ (sSplit.length + tSplit.length);
	}

	public ArrayList<String> linereadfile(String sentence) throws Exception {

		String line = null;
		stopwords = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader(sentence));

		while ((line = in.readLine().trim()) != null) {

			stopwords.add(line);
		}
		in.close();
		return stopwords;
	}
}
