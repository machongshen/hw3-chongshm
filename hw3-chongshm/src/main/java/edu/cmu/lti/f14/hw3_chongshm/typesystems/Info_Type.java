
/* First created by JCasGen Wed Oct 15 23:53:56 EDT 2014 */
package edu.cmu.lti.f14.hw3_chongshm.typesystems;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Oct 16 00:10:18 EDT 2014
 * @generated */
public class Info_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Info_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Info_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Info(addr, Info_Type.this);
  			   Info_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Info(addr, Info_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Info.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return ll_cas.ll_getIntValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, int v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    ll_cas.ll_setIntValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Rel;
  /** @generated */
  final int     casFeatCode_Rel;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getRel(int addr) {
        if (featOkTst && casFeat_Rel == null)
      jcas.throwFeatMissing("Rel", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Rel);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRel(int addr, int v) {
        if (featOkTst && casFeat_Rel == null)
      jcas.throwFeatMissing("Rel", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    ll_cas.ll_setIntValue(addr, casFeatCode_Rel, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cosine;
  /** @generated */
  final int     casFeatCode_cosine;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getCosine(int addr) {
        if (featOkTst && casFeat_cosine == null)
      jcas.throwFeatMissing("cosine", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_cosine);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCosine(int addr, double v) {
        if (featOkTst && casFeat_cosine == null)
      jcas.throwFeatMissing("cosine", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_cosine, v);}
    
  
 
  /** @generated */
  final Feature casFeat_rank;
  /** @generated */
  final int     casFeatCode_rank;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getRank(int addr) {
        if (featOkTst && casFeat_rank == null)
      jcas.throwFeatMissing("rank", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return ll_cas.ll_getIntValue(addr, casFeatCode_rank);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRank(int addr, int v) {
        if (featOkTst && casFeat_rank == null)
      jcas.throwFeatMissing("rank", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    ll_cas.ll_setIntValue(addr, casFeatCode_rank, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Info_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Integer", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_Rel = jcas.getRequiredFeatureDE(casType, "Rel", "uima.cas.Integer", featOkTst);
    casFeatCode_Rel  = (null == casFeat_Rel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Rel).getCode();

 
    casFeat_cosine = jcas.getRequiredFeatureDE(casType, "cosine", "uima.cas.Double", featOkTst);
    casFeatCode_cosine  = (null == casFeat_cosine) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cosine).getCode();

 
    casFeat_rank = jcas.getRequiredFeatureDE(casType, "rank", "uima.cas.Integer", featOkTst);
    casFeatCode_rank  = (null == casFeat_rank) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rank).getCode();

  }
}



    