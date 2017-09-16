package sample.dao;

import java.util.ArrayList;
import org.slim3.datastore.Datastore;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import sample.dto.DoctorDto;
import sample.meta.DoctorModelMeta;
import sample.model.DoctorModel;

public class DoctorDao{
    
    /**
     * Used to get only a single 'Doctor' from the datastore with the same 'name'
     * @param inputDoc - contains the information for verification
     * @return DoctorModel returned by the query
     */
    public DoctorModel getDoctorById(Long id){
        System.out.println("DoctorDao.getDoc start");
        DoctorModel doctor =    DoctorModelMeta.get().entityToModel(
            Datastore.query("DoctorModel")
                     .filter("id", FilterOperator.EQUAL, id)
                     .asSingleEntity()) ;
        if(doctor != null){
 
            System.out.println("DoctorDao.getDoc end(success)");
            return doctor;
        }else{
            System.out.println("DoctorDao.getDoc end(failed)");
            return null;
        }
    }
    
    public String validateDoctor(DoctorDto inputs){
        System.out.println("DoctorDao.getDoc start");
        String msg= "";
        
        if(checkDoctorExistsByEmail(inputs.getEmail())){
            
            System.out.println("DoctorDao.getDoc end(success)");
            msg += "email";
           
        }
        
        if(checkDoctorExistsByUsername(inputs.getUsername())){
            
            System.out.println("DoctorDao.getDoc end(success)");
            if(!msg.equals("")){
                msg += ",";
            }
            msg += "username";
        }
        
        return msg;
    }
    
    public boolean checkDoctorExistsByEmail(String email){
        System.out.println("DoctorDao.getDoc start");
      
        if(
            Datastore.query(DoctorModel.class)
            .filter("email", FilterOperator.EQUAL, email.toLowerCase())
            .asSingleEntity() != null){
 
            System.out.println("DoctorDao.getDoc end(success)");
            return true;
        }else{
            System.out.println("DoctorDao.getDoc end(failed)");
            return false;
        }
    }
    
    public boolean checkDoctorExistsByUsername(String username){
        System.out.println("DoctorDao.getDoc start");
        
        boolean ret;
      
        if(
            Datastore.query(DoctorModel.class)
            .filter("username", FilterOperator.EQUAL, username.toLowerCase())
            .asSingleEntity() != null){
 
            System.out.println("DoctorDao.getDoc end(success)");
            ret = true;
        }else{
            System.out.println("DoctorDao.getDoc end(failed)");
            ret = false;
        }
        
        return ret;
    }
    
    
    public DoctorModel getDoctorByEmailandPassword(String email,String password){
        
        System.out.println("DoctorDao.getDoc start");
       
        DoctorModel doctor = DoctorModelMeta.get().entityToModel(Datastore.query(DoctorModel.class)
            .filter(CompositeFilterOperator.and(new FilterPredicate("userName", FilterOperator.EQUAL, email.toLowerCase()),
                new FilterPredicate("password", FilterOperator.EQUAL, password.toLowerCase())))
            .asSingleEntity());
        
        if(doctor!=null){
            System.out.println("DoctorDao.getDoc end(success)");
        }else{
            DoctorModel doctorUser = DoctorModelMeta.get().entityToModel(Datastore.query(DoctorModel.class)
                .filter(CompositeFilterOperator.and(new FilterPredicate("userName", FilterOperator.EQUAL, email.toLowerCase()),
                    new FilterPredicate("password", FilterOperator.EQUAL, password.toLowerCase())))
                .asSingleEntity());
            if(doctorUser==null){
                System.out.println("DoctorDao.getDoc end(failed)");  
            } else {
                doctor = doctorUser;
                System.out.println("DoctorDao.getDoc end(success)");
            }
           // System.out.println("DoctorDao.getDoc end(failed)");   
        }
        return doctor;
        
    }
    
    public  Object getDoctors(){
        com.google.appengine.api.datastore.DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
                ArrayList<DoctorModel> results =  new ArrayList<DoctorModel>();

               Query query = new Query("DoctorModel");
                java.util.List<Entity> entities = datastore.prepare(query).asList(
                 FetchOptions.Builder.withDefaults());

               for (Entity entity : entities) {
                 results.add(DoctorModelMeta.get().entityToModel(entity));
               }
               
            return results ;
        
    }
    public void updateDoctor(DoctorModel inputDoctor) {
        System.out.println("DoctorDao.updateDoctor " + "start");
        // TODO: Implement this function.
        Transaction trans = Datastore.beginTransaction();
        
        Datastore.put(trans, inputDoctor);
        
        trans.commit();
        System.out.println("DoctorDao.updateDoctor " + "end");
    }
    /**
     * Used to insert the 'Doctor' to the datastore
     * @param inputDoc - the item to be inserted
     */
    public void insertDoc(DoctorModel inputDoc){
        System.out.println("DoctorDao.insertDoc start");
        
        Transaction trans = Datastore.beginTransaction();
        
        System.out.println(inputDoc.getUsername());
        
        //creating key and ID for the new entity
        Key parentKey = KeyFactory.createKey("Doctor", inputDoc.getUsername());
        Key key = Datastore.allocateId(parentKey, DoctorModel.class);
        
        System.out.println("After key");
        System.out.println(inputDoc.getFirstName());
        
        //Setting the 'key' and 'id' of the model
        inputDoc.setKey(key);
        inputDoc.setId(key.getId());
        inputDoc.setEmail(inputDoc.getEmail().toLowerCase());
        inputDoc.setUsername(inputDoc.getUsername().toLowerCase());
        inputDoc.setFirstName(processName(inputDoc.getFirstName()));
        inputDoc.setLastName(processName(inputDoc.getLastName()));
        
        System.out.println(inputDoc.getFirstName() + " " + inputDoc.getLastName());
        
        //inserting the item to the datastore
        Datastore.put(inputDoc);
        trans.commit();
        System.out.println("DoctorDao.insertDoc end");
    }
    
    public static String processName(String name){
        String retName;
        String[] names;
        int i;
        
        names = name.split(" ");
        retName = "";
        
        for(i = 0; i < names.length; i++){
            names[i] = Character.toUpperCase(names[i].charAt(0)) + names[i].substring(1).toLowerCase();
            retName += names[i];
            if(i + 1 != names.length){
                retName += " ";
            }
        }
        
        return retName;     
    }


    public void deleteDoctor(DoctorModel inputDoc) {
        // TODO Auto-generated method stub

            System.out.println("DoctorDao.deleteDoctor " + "start");
            // TODO: Implement this function.
            Transaction trans = Datastore.beginTransaction();
            Datastore.put(inputDoc);
            trans.commit();
            System.out.println("DoctorDao.deleteDoctor " + "end");
        
        
    }

}
