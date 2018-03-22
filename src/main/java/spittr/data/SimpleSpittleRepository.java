package spittr.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import spittr.Spittle;
@Repository
public class SimpleSpittleRepository implements SpittleRepository {
	
	private static List<Spittle> spittles = new ArrayList<Spittle>();

	public List<Spittle> findRecentSpittles() {
	 
		return   Collections.unmodifiableList(spittles);
	}

	public List<Spittle> findSpittles(long max, int count) {
		
	 List<Spittle> rtn = new ArrayList<Spittle>();
	 
	  for (int i=0; i < 20; i++) {
		  rtn.add(new Spittle("Spittle " + i, new Date()));
	    }
	  return rtn;
 		
//		if(spittles.isEmpty()){
//			  for (int i=0; i < 20; i++) {
//			      spittles.add(new Spittle("Spittle " + i, new Date()));
//			    }
//		}
//		
//		
//		return spittles;
		
//		if(spittles.size()>=count){
//			return   Collections.unmodifiableList(spittles.subList( spittles.size()-count, spittles.size()-1));
//		}else{
//			return   Collections.unmodifiableList(spittles);
//		}
		 
	}

	public Spittle findOne(long id) {
		for(Spittle spittle:spittles){
			if(spittle.getId()==id){
				return spittle; 
			}
		}
		return null;
	}

	public void save(Spittle spittle) {
		if(spittle.getId()!=0){
			Spittle find = this.findOne(spittle.getId());
			if(find!=null){
				int index = spittles.indexOf(find);
				spittles.remove(index);
				spittles.add(index, spittle);
				return;
			} 
		} 
		
		spittles.add(spittle);
	}

}
