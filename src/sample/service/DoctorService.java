package sample.service;

import java.util.List;

import sample.dao.DoctorDao;
import sample.dto.DoctorDto;
import sample.model.DoctorModel;



public class DoctorService {
    /**
     * Used to access the DAO functions for the DoctorModel
     */
    static DoctorDao doctorDao = new DoctorDao();

    public String insertDoc(DoctorDto inputDoc){
        System.out.println("DoctorService.insertDoc start");
        
        String status;
        DoctorModel doctorModel;
        
        doctorModel = new DoctorModel(inputDoc);
        
        status = doctorDao.validateDoctor(inputDoc);
        
        try { 
            if(status.equals("")){
                doctorDao.insertDoc(doctorModel);
                
                //EmailService.send(inputDoc.getEmail(), "Your account is Created");
                //   System.out.println(inputDoc.getEmail()+"Email sent!");              
            } else {
                System.out.println("Doctor Already Exists!");
            }
            
        }catch (Exception e){
            System.out.println("Exception in inserting doctor: "+e.toString());
        }
        
        System.out.println("DoctorService.insertDoc end");
        return status;
    }
    
    public String updateDoctor(DoctorDto doctorDto) {
        System.out.println("DoctorService.updateRecord " + "start");
    
        String status;
        DoctorModel doctorModel, resultModel;
        
        doctorModel = new DoctorModel(doctorDto);
        doctorModel.setId(doctorDto.getId());
        status = "";
        
        try {
            if(doctorDao.checkDoctorUpdateEmail(doctorDto.getEmail(), doctorDto.getId())){
                status += "email"; 
            } else {
                resultModel = doctorDao.getDoctorById(doctorDto.getId());
                
                if(resultModel != null) {
                    doctorModel.setKey(resultModel.getKey());
                    DoctorService.doctorDao.updateDoctor(doctorModel);   
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return status;
    }
    
    public String deleteDoctor(DoctorDto doctorDto) {
        System.out.println("DoctorService.deleteRecord " + "start");

        String state;
        DoctorModel resultModel;
        
        DoctorModel doctorModel = new DoctorModel(doctorDto);
        doctorModel.setId(doctorDto.getId());
        state = "";
        
        try {
            if(doctorDao.checkHold(doctorModel.getId())) {
                resultModel = doctorDao.getDoctorById(doctorDto.getId());
                
                if (resultModel != null) {
                    doctorModel.setKey(resultModel.getKey());
                    DoctorService.doctorDao.deleteDoctor(doctorModel);
                    System.out.println("Deleted Doctor");
                } else {
                    state = "failed";
                    System.out.println("There is no item with the same id.");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("DoctorService.deleteRecord " + "end");
        
        return state;
    }

    public DoctorModel loginDoctor(String username, String password){
        System.out.println("DoctorService.loginDoctor");
        return doctorDao.getDoctorByEmailandPassword(username, password);
    }

    public List<DoctorModel> getDoctors() {
        return doctorDao.getDoctors();
    }
    
    public DoctorModel getDoctor(Long id) {
        return doctorDao.getDoctorById(id);
    }
    
    public String generateToken(Long id){
        String token = null;    
        return  token;
    }
}
