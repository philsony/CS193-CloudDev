package sample.dao;

import java.util.ArrayList;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Transaction;

import sample.meta.PatientModelMeta;
import sample.model.PatientModel;


public class PatientDao{
    
    /**
     * Used to get only a single 'Patient' from the datastore with the same 'name'
     * @param inputPat - contains the information for verification
     * @return PatientModel returned by the query
     */
    public static PatientModel getPat(PatientModel inputPat){
        System.out.println("PatientDao.getPat start");
        
        if(Datastore.query("PatientModel")
                .filter("firstName", 
                         FilterOperator.EQUAL, 
                         inputPat.getFirstName()
                        ).asSingleEntity() == null){
            
            System.out.println("PatientDao.getPat end(success)");
            return null;
        }else{
            System.out.println("PatientDao.getPat end(failed)");
            return inputPat;
        }
    }
    
    /**
     * Used to get only a single 'Patient' from the datastore with the same 'name'
     * @param inputPat - contains the information for verification
     * @return PatientModel returned by the query
     */
    public PatientModel getPatientById(Long id){
        System.out.println("PatientDao.getPat start");
        PatientModel patient =    PatientModelMeta.get().entityToModel(
            Datastore.query("PatientModel")
                     .filter("id", FilterOperator.EQUAL, id)
                     .asSingleEntity()) ;
        if(patient != null){
 
            System.out.println("PatientDao.getPat end(success)");
            return patient;
        }else{
            System.out.println("PatientDao.getPat end(failed)");
            return null;
        }
    }
    
    public boolean getPatientByEmail(String email){
        System.out.println("PatientDao.getPat start");
      
        if(
            Datastore.query("PatientModel")
            .filter("email", FilterOperator.EQUAL, email)
            .asSingleEntity() != null){
 
            System.out.println("PatientDao.getPat end(success)");
            return true;
        }else{
            System.out.println("PatientDao.getPat end(failed)");
            return false;
        }
    }
    
    public  Object getPatients(){
        com.google.appengine.api.datastore.DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
                ArrayList<PatientModel> results =  new ArrayList<PatientModel>();

               Query query = new Query("PatientModel");
                java.util.List<Entity> entities = datastore.prepare(query).asList(
                 FetchOptions.Builder.withDefaults());

               for (Entity entity : entities) {
                 results.add(PatientModelMeta.get().entityToModel(entity));
               }
               
            return results ;
        
    }
    public void updatePatient(PatientModel inputPatient) {
        System.out.println("PatientDao.updatePatient " + "start");
        // TODO: Implement this function.
        Transaction trans = Datastore.beginTransaction();
        
        Datastore.put(trans, inputPatient);
        
        trans.commit();
        System.out.println("PatientDao.updatePatient " + "end");
    }
    
    /**
     * Used to insert the 'Patient' to the datastore
     * @param inputPat - the item to be inserted
     */
    public static void insertPat(PatientModel inputPat){
        System.out.println("PatientDao.insertPat start");
        Transaction trans = Datastore.beginTransaction();
        
        //creating key and ID for the new entity
        Key parentKey = KeyFactory.createKey("Patient", inputPat.getFirstName()+inputPat.getLastName());
        Key key = Datastore.allocateId(parentKey, "PatientModel");
        
        //Setting the 'key' and 'id' of the model
        inputPat.setKey(key);
        inputPat.setId(key.getId());
        
        //inserting the item to the datastore
        Datastore.put(inputPat);
        trans.commit();
        System.out.println("PatientDao.insertPat end");
    }
    public void deletePatient(PatientModel inputPat) {
        // TODO Auto-generated method stub

            System.out.println("PatientDao.deletePatient " + "start");
            // TODO: Implement this function.
            Transaction trans = Datastore.beginTransaction();
            Datastore.put(inputPat);
            trans.commit();
            System.out.println("PatientDao.deletePatient " + "end");
        
        
    }
}
