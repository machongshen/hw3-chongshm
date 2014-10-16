package edu.cmu.lti.f14.hw3_chongshm.casconsumers;

import java.util.Comparator;

class SortByCosine implements Comparator<Storage>{     
	public int compare(Storage obj1,Storage obj2){     
	  Storage point1=obj1;     
	  Storage point2=obj2;     
	  if(point1.getCosine()>point2.getCosine())     
	   return 1;     
	  else    
	   return 0;     
	}
}