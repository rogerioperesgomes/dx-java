package com.mercadopago;

import com.mercadopago.exceptions.MPConfException;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * Mercado Pago SDK
 * MPConf Class
 *
 * Created by Eduardo Paoletta on 11/1/16.
 */
public class MPConf {

    private static final String DEFAULT_BASE_URL = "https://api.mercadopago.com";

    private static String clientSecret = null;
    private static String clientId = null;
    private static String accessToken = null;
    private static String appId = null;
    private static String baseUrl = DEFAULT_BASE_URL;

    /**
     * Getter/Setter for ClientSecret
     */
    public static String getClientSecret() {
        return clientSecret;
    }

    public static void setClientSecret(String value) throws MPConfException {
        if (StringUtils.isNotEmpty(clientSecret))
            throw new MPConfException("clientSecret setting can not be changed");
        clientSecret = value;
    }

    /**
     * Getter/Setter for ClientId
     */
    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String value) throws MPConfException {
        if (StringUtils.isNotEmpty(clientId))
            throw new MPConfException("clientId setting can not be changed");
        clientId = value;
    }

    /**
     * Getter/Setter for AccessToken
     */
    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String value) throws MPConfException {
        if (StringUtils.isNotEmpty(accessToken))
            throw new MPConfException("accessToken setting can not be changed");
        accessToken = value;
    }

    /**
     * Getter/Setter for AppId
     */
    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String value) throws MPConfException {
        if (StringUtils.isNotEmpty(appId))
            throw new MPConfException("appId setting can not be changed");
        appId = value;
    }

    /**
     * Getter/Setter for BaseUrl
     * (FOR TESTING ONLY)
     */
    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String value) {
        baseUrl = value;
    }

    /**
     * Set configuration params with a hashmap.
     * Valid keys are: clientSecret, clientId, accessToken, appId
     * @param hashConfigurationParams a <String, String> hashmap with the configuration params
     * throws MPConfException
     */
    public static void setConfiguration(HashMap<String, String> hashConfigurationParams) throws MPConfException {
        if (hashConfigurationParams == null)
            throw new IllegalArgumentException("Invalid hashConfigurationParams parameter");

        setClientSecret(getValueFromHashMap(hashConfigurationParams, "clientSecret"));
        setClientId(getValueFromHashMap(hashConfigurationParams, "clientId"));
        setAccessToken(getValueFromHashMap(hashConfigurationParams, "accessToken"));
        setAppId(getValueFromHashMap(hashConfigurationParams, "appId"));
    }

    /**
     * Extract a value from a HashMap and validate that is not null or empty
     * @param hashMap a <String, String> hashmap with the configuration params.
     * @param key value key
     * @return the configuration param
     */
    private static String getValueFromHashMap(HashMap<String, String> hashMap, String key) {
        if (hashMap.containsKey(key) &&
                StringUtils.isNotEmpty(hashMap.get(key)))
            return hashMap.get(key);
        else
            throw new IllegalArgumentException(String.format("Invalid %s value", key));
    }

    /**
     * Set configuration params with a properties file
     * @param filePath the path of the properties file
     * throws MPConfException
     */
    public static void setConfiguration(String filePath) throws MPConfException {
        if (StringUtils.isEmpty(filePath))
            throw new IllegalArgumentException("File path can not be empty");

        InputStream inputStream = null;
        try {
            Properties properties = new Properties();

            inputStream = MPConf.class.getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null)
                throw new IllegalArgumentException("File not found");

            properties.load(inputStream);

            setClientSecret(getValueFromProperties(properties, "clientSecret"));
            setClientId(getValueFromProperties(properties, "clientId"));
            setAccessToken(getValueFromProperties(properties, "accessToken"));
            setAppId(getValueFromProperties(properties, "appId"));

        } catch (IllegalArgumentException iaException) {
            throw iaException;
        } catch (Exception exception) {
            throw new MPConfException(exception);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception ex) {
                // Do nothing
            }
        }
    }

    /**
     * Extract a value from a Properties object and validate that is not null or empty
     * @param properties Properties object
     * @param key value key
     * @return the configuration param
     */
    private static String getValueFromProperties(Properties properties, String key) {
        if (properties.containsKey(key) &&
                StringUtils.isNotEmpty(properties.getProperty(key)))
            return properties.getProperty(key);
        else
            throw new IllegalArgumentException(String.format("Invalid %s value", key));
    }

    /**
     * Clean all the configuration variables
     * (FOR TESTING ONLY)
     */
    public static void cleanConfiguration() {
        clientSecret = null;
        clientId = null;
        accessToken = null;
        appId = null;
        baseUrl = DEFAULT_BASE_URL;
    }

}
