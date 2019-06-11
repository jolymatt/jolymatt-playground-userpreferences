package com.github.jolymatt.playground.userpref.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * The Domain Data Util class
 * <p> Contains utility method to search in Json data store. Usage is restricted for Demonstration only</p>
 * @author Joly Mathew
 */
public class DomainDataUtil {

    /**
     * This method find Application Preferences in a given User Preference Json String by Application Id
     * @param userPreference UserPreference
     * @param applicationId UUID
     * @return ApplicationPreferences
     */
    public static Optional<ApplicationPreference> findAppPreferenceInUserPreferences(UserPreference userPreference, UUID applicationId){
        ApplicationPreference applicationPreference = null;
        if (null!= userPreference && null!= userPreference.getApplicationPreferences() && userPreference.getApplicationPreferences().size()>0){
            for(ApplicationPreference appPreferences:userPreference.getApplicationPreferences()){
                if(null!= appPreferences && appPreferences.getId().equals(applicationId)){
                    applicationPreference= appPreferences;
                    break;
                }
            }
        }
        return Optional.ofNullable(applicationPreference);
    }

    /**
     * This method find DevicePreference in ApplicationPreference by DeviceId
     * @param applicationPreference ApplicationPreference
     * @param deviceId UUID
     * @return DevicePreference
     */
    public static Optional<DevicePreference> findDevicePreferenceInApplicationPreferences(ApplicationPreference applicationPreference, UUID deviceId){
        DevicePreference devicePreference = null;
        if (null!= applicationPreference && null!= applicationPreference.getDevicePreferences() && applicationPreference.getDevicePreferences().size()>0){
            for(DevicePreference devPreferences:applicationPreference.getDevicePreferences()){
                if(null!= devPreferences && devPreferences.getId().equals(deviceId)){
                    devicePreference= devPreferences;
                    break;
                }
            }
        }
        return Optional.ofNullable(devicePreference);
    }

    /**
     * This method find ComponentPreference in DevicePreference by componentId
     * @param devicePreference DevicePreference
     * @param componentId UUID
     * @return ComponentPreference
     */
    public static Optional<ComponentPreference> findComponentPreferencesInDevicePreference(DevicePreference devicePreference, UUID componentId){
        ComponentPreference componentPreference = null;
        if (null!= devicePreference && null!= devicePreference.getComponentPreferences() && devicePreference.getComponentPreferences().size()>0){
            for(ComponentPreference preference:devicePreference.getComponentPreferences()){
                if(null!= preference && preference.getId().equals(componentId)){
                    componentPreference= preference;
                    break;
                }
            }
        }
        return Optional.ofNullable(componentPreference);
    }
}
