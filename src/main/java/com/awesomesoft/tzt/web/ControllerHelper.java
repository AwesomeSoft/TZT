package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by student on 5/13/14.
 */
public class ControllerHelper {

    private static final Logger logger = LoggerFactory.getLogger(ControllerHelper.class);

    /**
     * Redirect to /index.html
     * @param reason Reason for redirecting
     */
    public static void redirect(String reason) {
        redirect(reason, "/");
    }

    /**
     * Redirect to destination, in case of full URL validate it before redirecting
     * @param reason Reason for redirecting
     * @param destination Page or URL to redirect to
     */
    public static void redirect(String reason, String destination) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        logger.info("Redirecting to {} (reason: {})", destination, reason);
        try {
            URL url = null;
            if (destination.startsWith("http")) {
                url = new URL(destination);
                validateUrl(url);
            } else if (!destination.startsWith("/")) {
                destination = "/" + destination;
            }
            ec.redirect(url != null ? url.toString() : ec.getRequestContextPath() + destination);
        } catch (IOException | ValidationException e) {
            logger.info("Failed to redirect (reason: {})", e.getMessage());
        }
    }

    /**
     * Validate URL by opening a connection and matching the response code
     * @param url The URL that needs validation
     * @throws ValidationException
     * @throws IOException
     */
    private static void validateUrl(URL url) throws ValidationException, IOException {
        logger.info("Validating URL \"{}\"", url.toString());
        HttpURLConnection huc =  (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("HEAD");
        huc.connect();
        if (huc.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new ValidationException("Invalid URL");
        }
    }

    /**
     * Create a message and place it on the screen
     * @param message The message
     * @param location Where to place the message (id:id)
     */
    public static void message(String message, String location) {
        message(message, location, "INFO");
    }

    /**
     * Create a message and place it on the screen using style to set a severity
     * @param message The message
     * @param location Where to place the message (id:id)
     * @param style Which class the message belongs to
     */
    public static void message(String message, String location, String style) {
        logger.info("Adding message \"{}\" to {}", message, location);
        FacesMessage.Severity severity;
        switch (style.toUpperCase()) {
            case "ERROR":
                severity = FacesMessage.SEVERITY_ERROR;
                break;
            case "FATAL":
                severity = FacesMessage.SEVERITY_FATAL;
                break;
            case "INFO":
                severity = FacesMessage.SEVERITY_INFO;
                break;
            case "WARN":
                severity = FacesMessage.SEVERITY_WARN;
                break;
            default:
                severity = FacesMessage.SEVERITY_INFO;
        }
        FacesContext.getCurrentInstance().addMessage(location, new FacesMessage(severity, message, null));
    }
}
