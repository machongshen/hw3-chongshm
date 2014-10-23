

/* First created by JCasGen Wed Oct 15 23:53:56 EDT 2014 */
package edu.cmu.lti.f14.hw3_chongshm.typesystems;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Oct 22 21:07:18 EDT 2014
 * XML source: /Users/machongshen/git/hw3-chongshm/hw3-chongshm/src/main/resources/descriptors/retrievalsystem/DocumentVectorAnnotator.xml
 * @generated */
public class Info extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Info.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  public void Info() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Info(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Info(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Info(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public int getId() {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Info_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(int v) {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    jcasType.ll_cas.ll_setIntValue(addr, ((Info_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Info_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    jcasType.ll_cas.ll_setStringValue(addr, ((Info_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: Rel

  /** getter for Rel - gets 
   * @generated
   * @return value of the feature 
   */
  public int getRel() {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_Rel == null)
      jcasType.jcas.throwFeatMissing("Rel", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Info_Type)jcasType).casFeatCode_Rel);}
    
  /** setter for Rel - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRel(int v) {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_Rel == null)
      jcasType.jcas.throwFeatMissing("Rel", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    jcasType.ll_cas.ll_setIntValue(addr, ((Info_Type)jcasType).casFeatCode_Rel, v);}    
   
    
  //*--------------*
  //* Feature: cosine

  /** getter for cosine - gets 
   * @generated
   * @return value of the feature 
   */
  public double getCosine() {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_cosine == null)
      jcasType.jcas.throwFeatMissing("cosine", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Info_Type)jcasType).casFeatCode_cosine);}
    
  /** setter for cosine - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCosine(double v) {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_cosine == null)
      jcasType.jcas.throwFeatMissing("cosine", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Info_Type)jcasType).casFeatCode_cosine, v);}    
   
    
  //*--------------*
  //* Feature: rank

  /** getter for rank - gets 
   * @generated
   * @return value of the feature 
   */
  public int getRank() {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_rank == null)
      jcasType.jcas.throwFeatMissing("rank", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Info_Type)jcasType).casFeatCode_rank);}
    
  /** setter for rank - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRank(int v) {
    if (Info_Type.featOkTst && ((Info_Type)jcasType).casFeat_rank == null)
      jcasType.jcas.throwFeatMissing("rank", "edu.cmu.lti.f14.hw3_chongshm.typesystems.Info");
    jcasType.ll_cas.ll_setIntValue(addr, ((Info_Type)jcasType).casFeatCode_rank, v);}    
  }

    