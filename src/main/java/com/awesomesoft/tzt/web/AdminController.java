package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Erwin on 1-6-2014.
 */


    @ManagedBean
    @SessionScoped
    public class AdminController {
    private List<Person> empResultData;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private Person admin;
    private Person personToEdit;
    private Long lastID = new Long(0);

    @Inject
    TZTRepository repository;

    @PostConstruct
    public void init() {
        /*
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        PersonController personController = (PersonController) externalContext.getSessionMap().get("personController");
        if(personController != null){
            if(personController.getPerson != null{
                if(personController.getPerson().isAuthenticated() && personController.getPerson().getRole() == 10){
                    admin = personController.getPerson();
                }
            }
            //todo throw exception ofzo
        }else {
            admin = null;
        }
        */
    }

    public List<Person> getEmpResultData() {
        return repository.listUsersAdminView();
    }


    public Person getUserDetails(Long id){
        if(
            (id != null && lastID != id || this.personToEdit == null)){
            System.out.println(id);
            this.personToEdit = repository.getPersonById(id);
            lastID = id;
        }
        return this.personToEdit;
    }



    public String updateUser(){
        if(personToEdit != null) {
            if (repository.checkPersonExistsById(personToEdit.getId())) {
                //Dit moet dan straks in de if(admin != null)
                repository.updatePerson(personToEdit);
                return "";
                //done
            } else {
                ControllerHelper.message("Kan " + personToEdit.getFirstName() + " niet verwijderen", "adminUserPanel:updateUser", "ERROR");
                return "";
            }
        }else {
            ControllerHelper.message("Geen gebruiker gevonden", "adminUserPanel:updateUser", "ERROR");
            return "";
        }
     }



    public boolean currentUser(){
        return true;
    }


}
