package sample.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import sample.dto.PatientDto;
import sample.service.PatientService;

public class PatientController extends Controller {

 /**
  * Service object that will be used to call CRUD functions to datastore
  */
 PatientService patientService = new PatientService();


 /**
  * For now, used to insert a 'patient' entity to the datastore
  */
 @Override
 protected Navigation run() throws Exception {
  System.out.println("PatientController.run start");

  JSONObject json = new JSONObject();
  String action = request.getParameter("action");
 
  
        /** 
         * Used to retrieve the JSON equivalent data
         */
        BufferedReader br = request.getReader();
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jObj = new JSONObject(sb.toString());

        /**
         * Used to store the information from the request and send to the
         * service class.
         */
        PatientDto patientDto = new PatientDto(jObj.getString("fname"),
            jObj.getString("lname"),
            jObj.getString("address"),
            jObj.getString("email"),
            jObj.getString("number"),
            jObj.getString("birthday"),
            jObj.getString("username"),
            jObj.getString("password")
           );

        if (action.equals("registerPatient")) {


            if (patientService.insertPat(patientDto) == false) {

                json.put("message", "duplicated");

            } else {

                json.put("message", true);

            }

        } else if (action.equals("updatePatient")) {


            if (PatientService.updatePatient(patientDto)) {
                json.put("message", "The Profil of  " + jObj.getString("lname") + " was updated ");
            } else {
                json.put("message", false);
            }

        } else if (action.equals("deletePatient")) {
            if (PatientService.deletePatient(patientDto)) {

                json.put("message", "The Profil of " + jObj.getString("lname") + " was deleted ");
            } else {
                json.put("message", false);
            }

        } else if (action.equals("getPatient")) {

            json.put("patients", PatientService.getPatient(Long.parseLong(jObj.getString("id"))));
        } else if (action.equals("getPatients")) {
            json.put("patients", PatientService.getPatients());
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //print JSon
        out.print(json.toString());

        System.out.println("PatientController.run end");
        //screen redirection.
        return null;
    }
}