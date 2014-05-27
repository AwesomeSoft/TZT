/*
 * Copyright (c) 2014.
 *
 * This software is copyrighted by the AwesomeSoft group.
 * Misuse of this software will be with concesquences
 */

package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.SSLMailer;
import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.Person;
import com.awesomesoft.tzt.service.domain.TrainCourier;
import com.awesomesoft.tzt.service.exception.AuthenticationException;
import com.awesomesoft.tzt.service.exception.GenerationException;
import com.awesomesoft.tzt.service.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by student on 1/14/14.
 */
@ManagedBean  //zorgt ervoor dat de personcontroller in JSF beschikbaar is
@SessionScoped  //zorgt ervoor dat de personcontroller in JSF beschikbaar is
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Inject
    TZTRepository repository;

    protected Person person;

    protected TrainCourier trainCourier;

    protected PersonInfo personInfo;

    @PostConstruct // dit zorgt ervoor dat er een person object is wanneer deze controler aangeroepen is. Deze person is leeg.
    //De person kan in faces gevuld worden door een getter in de controller
    public void init() {
        personInfo = new PersonInfo();
    }

    public void updateProfile() {
        System.out.println("sad");
        logger.info("Updaten profiel voor {}", person.getEmailAddress());
        try {
            validatePostalCode(true);
            validateHouseNumber(true);
          // moet nog code bij.
          //  validateEmailAddress(false);
            repository.updatePerson(person);
        } catch (ValidationException e) {
            ControllerHelper.message(e.getMessage(), "changePasswordForm:submitChange", "ERROR");
        }
    }

    public String register() {
        logger.info("Registering {} {}", personInfo.getFirstName(), personInfo.getLastName());

        try {
            person = new Person(personInfo);
            validatePostalCode(true);
            validateHouseNumber(true);
            validateDateofBirth(true);
            validateEmailAddress(true);
            validatePassword();
            person.setDateCreated(new Date());
            Long id = repository.insertPerson(person);  // Hier insertPerson hij de person in de database. Dit levert een ID op.
            generateActivationUrl(id, person.getEmailAddress(), person.getPassword());
            SSLMailer.send(person.getEmailAddress(), "Activate your account", "Awesome! Here's your activation link: " + person.getActivationUrl());
            return "confirmation.xhtml";
        } catch (ValidationException | GenerationException e) {
            ControllerHelper.message(e.getMessage(), "registrationForm:submitRegistration", "ERROR");
            return "";
        }
    }

    public String registerTrainCourier() {
        logger.info("Registering {} {}", personInfo.getFirstName(), personInfo.getLastName());

        try {
            trainCourier = new TrainCourier(personInfo);
            person = trainCourier;
            validatePostalCode(true);
            validateHouseNumber(true);
            validateDateofBirth(true);
            validateEmailAddress(true);
            validatePassword();
            trainCourier.setDateCreated(new Date());
            Long id = repository.insertTrainCourier(trainCourier);  // Hier insertPerson hij de person in de database. Dit levert een ID op.
            generateActivationUrl(id, trainCourier.getEmailAddress(), trainCourier.getPassword());
            SSLMailer.send(trainCourier.getEmailAddress(), "Activate your account", "Awesome! Here's your activation link: " + person.getActivationUrl());
            return "confirmation.xhtml";
        } catch (ValidationException | GenerationException e) {
            ControllerHelper.message(e.getMessage(), "registrationForm:submitRegistration", "ERROR");
            return "";
        }
    }
    /*Added by Erwin*/

    private void validatePostalCode(boolean register) throws ValidationException {
        String postalCode = person.getAddress().getPostalCode();
        logger.info("Validating postcalCode \"{}\"", postalCode);

        final Pattern pattern = Pattern.compile("^[1-9][0-9]{3}\\s?[a-zA-Z]{2}$");

        Matcher matcher = pattern.matcher(postalCode);

        if (!matcher.matches()) {
            throw new ValidationException("postcalCode invalid");
        }
    }

    private void validateHouseNumber(boolean register) throws ValidationException {
        String houseNumber = person.getAddress().getHouseNumber();
        logger.info("Validating houseNumber \"{}\"", houseNumber);

        final Pattern pattern = Pattern.compile("(?:^|\\s)[0-9_.](\\w+)\\b");

        Matcher matcher = pattern.matcher(houseNumber);

        if (!matcher.matches()) {
            throw new ValidationException("houseNumber invalid");
        }
    }

    private void validateDateofBirth(boolean register) throws ValidationException {
        String dateofBirth = person.getDateofBirth();
        logger.info("Validating dateofBirth \"{}\"", dateofBirth);

       final Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$");
        /* Hier moet nog wat bij, zoals geen datum in de toekomst.*/
        Matcher matcher = pattern.matcher(dateofBirth);

        if (!matcher.matches()) {
            throw new ValidationException("dateofBirth invalid");

        }
    }
    /*Added by Erwin*/

    /**
     * Requires a full @domain.com email address, username plus suffix
     * This method validates the address using a regular expression
     *
     * @param register If true, this method also requires a unique address which is not already registered
     *
     * @throws ValidationException
     */
    private void validateEmailAddress(boolean register) throws ValidationException {
        String mail = person.getEmailAddress();
        logger.info("Validating email address \"{}\"", mail);

        final Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(mail);

        if (!matcher.matches()) {
            throw new ValidationException("Email address invalid");
        }

        if (register && repository.checkPersonExistsByEmailAddress(person.getEmailAddress())) {
            throw new ValidationException("Email address already exists");
        } else if (!register && !repository.checkPersonExistsByEmailAddress(person.getEmailAddress())) {
            throw new ValidationException("Email address unknown");
        }
    }

    /**
     * Requirements
     * minimum one [a-z]
     * minimum one [A-Z]
     * minimum one [0-9]
     * minimum one [!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]
     * 8 or more length
     *
     * @throws ValidationException
     */
    private void validatePassword() throws ValidationException {
        logger.info("Validating password");
        String password = person.getPassword();

        final Pattern pattern = Pattern.compile("^(?=[\\x21-\\x7E]*[0-9])(?=[\\x21-\\x7E]*[A-Z])(?=[\\x21-\\x7E]*[a-z])(?=[\\x21-\\x7E]*[\\x21-\\x2F|\\x3A-\\x40|\\x5B-\\x60|\\x7B-\\x7E])[\\x21-\\x7E]{8,}$");

        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new ValidationException("Password does not meet the specified requirements");
        }

        if (!password.equals(person.getConfirmedPassword())) {
            throw new ValidationException("Passwords do not match");
        }

        person.setPassword(digestPassword(password));
    }

    private void generateActivationUrl(Long id, String emailAddress, String password) throws GenerationException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            person.setActivationUrl(new URL(
                    request.isSecure() ? "https" : "http",
                    externalContext.getRequestServerName(),
                    externalContext.getRequestServerPort(),
                    externalContext.getRequestContextPath() + "/register/activate.xhtml?id=" + id + "&code=" + generateActivationCode(emailAddress, password)));
        } catch (MalformedURLException | GenerationException e) {
            throw new GenerationException("Failed to generate activation URL. Please contact your system administrator");
        }
        logger.info("Generated URL = {}", person.getActivationUrl());
    }

    private String generateActivationCode(String emailAddress, String password) throws GenerationException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update((emailAddress + password).getBytes("UTF-8"));
            Formatter formatter = new Formatter();
            for (byte b : md.digest()) {
                formatter.format("%02x", b);
            }
            String result = formatter.toString();
            formatter.close();
            logger.info("Generated code = {}", result);
            return result;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new GenerationException("Failed to generate activation code");
        }
    }

    /**
     * Activating account using an URL, formatted like:
     * http://[host]/[context-path]/register/activate.xhtml?id=61&code=d41e213f750e478ab92d393d8069905a531296a8
     *
     * When id or code are invalid or missing, this method redirects to /index
     *
     * @throws IOException
     */
    public void activateAccount() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        logger.info("Account activation");

        String stringId = externalContext.getRequestParameterMap().get("id");

        if (stringId != null) {
            stringId = stringId.replaceAll("[^0-9]","");
        }

        Long id = stringId != null && stringId.length() > 0 ? Long.parseLong(stringId) : -1l;

        if (repository.checkPersonExistsById(id)) {
            Person p = repository.getPersonById(id);
            String activationCode = externalContext.getRequestParameterMap().get("code");

            logger.info("   {}", p.getEmailAddress());
            logger.info("   {}", activationCode);

            logger.info("Activating...");

            try {
                if (generateActivationCode(p.getEmailAddress(), p.getPassword()).equals(activationCode)) {
                    p.setActivated(true);
                    repository.updatePerson(p);
                    logger.info("Account with id {} has been activated", p.getId());
                } else {
                    ControllerHelper.redirect("Codes do not match","");
                }
            } catch (GenerationException e) {
                ControllerHelper.redirect(e.getMessage());
            }
        } else {
            ControllerHelper.redirect("No person found with id " + id);
        }
    }

    private String digestPassword(String password) {
        final String salt = "518498bd76e3830421f8c6f31eaaee817b62ee8b";
        final String pepper = "839586c94bc175e5ccdc4019a76f972d4e8b8376";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update((salt + password + pepper).getBytes("UTF-8"));
            Formatter formatter = new Formatter();
            for (byte b : md.digest()) {
                formatter.format("%02x", b);
            }
            String result = formatter.toString();
            formatter.close();
            return result;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e); // todo handle exception
        }
    }

    /**
     * Validate email address, generate new password and send an email containing the last mentioned
     * This method will also encrypt the new password and save it to the database
     */
    public String resetPassword() {
        logger.info("Requesting new password");

        try {
            validateEmailAddress(false);
            String newPassword = generatePassword(8);
            person = repository.getPersonByEmailAddress(person.getEmailAddress());
            person.setPassword(digestPassword(newPassword));
            repository.updatePerson(person);
            SSLMailer.send(person.getEmailAddress(), "Your new password", "Great, try again with this one: " + newPassword);
            return "confirmation.xhtml";
        } catch (ValidationException e) {
            ControllerHelper.message(e.getMessage(), "resetPasswordForm:submitReset", "ERROR");
            return "";
        }
    }

    /**
     * Generates a random password using the static password requirements
     * CAUTION! This password needs to be send to the user to log in, but it needs to be digested before persisting
     *
     * @param len Amount of characters the new password must contain
     * @return New, non-encrypted password
     */
    private String generatePassword(int len) {
        String str = "0123456789" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz" +
                "!@#$%^&*(){}:<>?_+~[].-=";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(str.charAt(rnd.nextInt(str.length())));
        }
        return sb.toString();
    }

    public String login(boolean requireAdmin) {
        try {
            authenticatePerson(requireAdmin);
            person.setLastLogin(new Date());
            repository.updatePerson(person);
            return requireAdmin ? "/admin/panel.xhtml" : "/profile/index.xhtml";
        } catch (AuthenticationException e) {
            ControllerHelper.message(e.getMessage(), "loginForm:submitLogin", "ERROR");
            return "";
        }
    }

    /**
     * Authenticate person using email address and password
     * When only a prefix is entered, the suffix is automatically added to create a valid email address
     * Finally the method checks if the account is activated before a person is authenticated
     *
     * @throws AuthenticationException
     */
    protected void authenticatePerson(boolean requireAdmin) throws AuthenticationException {
        String mail = person.getEmailAddress();
        logger.info("Authenticating \"{}\"", mail);

        if (!mail.toLowerCase().contains("@")) {
            mail = mail + "@mail.com";
            logger.info("Added suffix as a service, authenticating \"{}\"", mail);
        }

        if (!repository.checkPersonExistsByEmailAddress(mail)) {
            logger.info("Email address does not exist");
            throw new AuthenticationException("Ongeldige login");
        }

        Person temp = repository.getPersonByEmailAddress(mail);

        if (temp.getLockedOut().getTime() > new Date().getTime()) {
            throw new AuthenticationException("Account locked out until " + new SimpleDateFormat("HH:mm:ss").format(temp.getLockedOut()));
        }

        if (!temp.getPassword().equals(digestPassword(person.getPassword()))) {
            person.addFailedAttempt();
            if (person.getFailedAttempts() == 3) {
                temp.setLockedOut(new Date(new Date().getTime() + 1000 * 60 * 10)); // current date + 10 minutes
                repository.updatePerson(temp);
                throw new AuthenticationException("Account locked out for 10 minutes");
            }
            logger.info("Password does not match");
            throw new AuthenticationException("Ongeldige login");
        }

        if (!temp.isActivated()) {
            throw new AuthenticationException("Je account is nog niet geactiveerd");
        }

        if (requireAdmin && !temp.isAdmin()) {
            throw new AuthenticationException("You are not authorized to view this page");
        }

        person = temp;
        person.setAuthenticated(true);
        logger.info("Success");
    }

    public String changePassword() {
        logger.info("Changing password for {}", person.getEmailAddress());

        try {
            validatePassword();
            repository.updatePerson(person);
            return "confirmation.xhtml";
        } catch (ValidationException e) {
            ControllerHelper.message(e.getMessage(), "changePasswordForm:submitChange", "ERROR");
            return "";
        }
    }

    public void logout() throws IOException {
        Long id = person.getId();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ControllerHelper.redirect("Person with id " + id + " logged out");
    }

    public Person getPerson() {
        return person;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}