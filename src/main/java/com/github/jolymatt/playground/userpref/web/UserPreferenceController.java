package com.github.jolymatt.playground.userpref.web;

import com.github.jolymatt.playground.userpref.domain.*;
import com.github.jolymatt.playground.userpref.util.RestUtil;
import com.github.jolymatt.playground.userpref.web.exception.RestServiceException;

import com.github.jolymatt.playground.userpref.repo.UserPreferenceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The User preference controller
 *
 * @author joly mathew
 */
@Slf4j
@RestController
@RequestMapping(value = RestUtil.USER_PREF_ROOT)
@Api(value = "iHM User Preference Service ", description = "Operations pertaining to User Preference Management")
public class UserPreferenceController {
    @Autowired
    UserPreferenceRepository userPreferenceRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ApiOperation(value = "Create User Preference including Global Preferences", response = UserPreference.class)
    @ResponseBody
    public ResponseEntity<UserPreference> createUserPreferences(@RequestBody UserPreference userPref) {
        userPreferenceRepository.save(userPref);
        return new ResponseEntity<>(userPref, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update User Preference including Global Preferences ", response = UserPreference.class)
    @ResponseBody
    public ResponseEntity<UserPreference> updateUserPreferences(@ApiParam(required = true, name = "userPref", value = "User Preference") @RequestBody UserPreference userPref) {
        userPreferenceRepository.save(userPref);
        return new ResponseEntity<>(userPref, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve the User Preference by User Id ", response = UserPreference.class)
    @ResponseBody
    public ResponseEntity<UserPreference> retrieveUserPreferencesByUserId(@PathVariable("userId") String userId) {
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);
        if (null != userPreference) {
            return new ResponseEntity<>(userPreference, HttpStatus.OK);
        } else {
            throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
        }
    }

    @RequestMapping(value = "/user/{userId}/app", method = RequestMethod.POST)
    @ApiOperation(value = "Create User preferences for an application ", response = ApplicationPreference.class)
    @ResponseBody
    public ResponseEntity<ApplicationPreference> createApplicationPreference(@PathVariable("userId") String userId, @RequestBody ApplicationPreference applicationPreference) {
        //Not a good way to upate.
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);

        if (null != userPreference) {
            List<ApplicationPreference> applicationPreferencesList = userPreference.getApplicationPreferences();
            if (null == applicationPreferencesList) {
                applicationPreferencesList = new ArrayList<>();
            }
            applicationPreferencesList.add(applicationPreference);
            userPreferenceRepository.save(userPreference);
            return new ResponseEntity<>(applicationPreference, HttpStatus.CREATED);
        } else {
            throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
        }

    }

    @RequestMapping(value = "/user/{userId}/app/{appId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update the user preferences for an application", response = ApplicationPreference.class)
    @ResponseBody
    public ResponseEntity<ApplicationPreference> updateApplicationPreference(@PathVariable("userId") String userId, @PathVariable("appId") UUID appId, @RequestBody ApplicationPreference applicationPreference) {
        //WARNING: Not a good way to update the json node.. This is only for demonstration
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);

        if (null != userPreference) {
            ApplicationPreference existingApplicationPreferences = DomainDataUtil.findAppPreferenceInUserPreferences(userPreference, appId).orElse(null);
            if (null == existingApplicationPreferences) {
                throw new RestServiceException(String.format("No UserPreference found for Application with id %s", appId));
            }
            existingApplicationPreferences = applicationPreference;
            userPreferenceRepository.save(userPreference);
            return new ResponseEntity<>(applicationPreference, HttpStatus.OK);
        } else {
            throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
        }
    }

    @RequestMapping(value = "/user/{userId}/app/{appId}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve User Preference for an Application by User Id and Application Id ", response = ApplicationPreference.class)
    @ResponseBody
    public ResponseEntity<ApplicationPreference> retrieveApplicationPreferenceByUserIdAndAppId(@PathVariable("userId") String userId, @PathVariable("appId") UUID appId) {

        try {
            UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);
            if (null != userPreference) {
                ApplicationPreference applicationPreference = DomainDataUtil.findAppPreferenceInUserPreferences(userPreference, appId).orElse(null);
                return new ResponseEntity<>(applicationPreference, HttpStatus.OK);
            } else {
                throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
            }
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
            log.error(dataAccessException.getMessage());
            throw new RestServiceException(String.format("Could not retrieve Application preferences due to exception  %s", dataAccessException.getMessage()));
        }

    }

    @RequestMapping(value = "/user/{userId}/app/{appId}/device", method = RequestMethod.POST)
    @ApiOperation(value = "Create  User Preference for an  Application for a specific  device environment ", response = DevicePreference.class)
    @ResponseBody
    public ResponseEntity<DevicePreference> createDevicePreference(@PathVariable("userId") String userId, @PathVariable("appId") UUID appId, @RequestBody DevicePreference devicePreference) {
        //Not a good way to create.
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);

        if (null != userPreference) {

                ApplicationPreference applicationPreference = DomainDataUtil.findAppPreferenceInUserPreferences(userPreference, appId).orElse(null);
                if (null != applicationPreference) {
                    List<DevicePreference> devicePreferenceList = applicationPreference.getDevicePreferences();
                    if (null == devicePreferenceList) {
                        devicePreferenceList = new ArrayList<>();
                    }
                    devicePreferenceList.add(devicePreference);
                    userPreferenceRepository.save(userPreference);
                    return new ResponseEntity<>(devicePreference, HttpStatus.CREATED);
                } else {
                    throw new RestServiceException(String.format("No Application preference found for user with  User id %s", userId));
                }

        } else {
            throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
        }
    }

    @RequestMapping(value = "/user/{userId}/app/{appId}/device/{deviceId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update User Preference for an  Application in a specific device environment  ", response = DevicePreference.class)
    @ResponseBody
    public ResponseEntity<DevicePreference> updateDevicePreferences
            (@PathVariable("userId") String userId, @PathVariable("appId") UUID appId, @PathVariable("deviceId") UUID deviceId, @RequestBody DevicePreference devicePreference) {

        //WARNING: Not a good way to update the json node.. This is only for demonstration
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);

        if (null != userPreference) {

                ApplicationPreference applicationPreference = DomainDataUtil.findAppPreferenceInUserPreferences(userPreference, appId).orElse(null);
                if (null != applicationPreference) {
                    DevicePreference existingDevicePreference = DomainDataUtil.findDevicePreferenceInApplicationPreferences(applicationPreference, deviceId).orElse(null);
                    if (null != existingDevicePreference) {
                        existingDevicePreference = devicePreference;
                        userPreferenceRepository.save(userPreference);
                        return new ResponseEntity<>(devicePreference, HttpStatus.OK);
                    } else {
                        throw new RestServiceException(String.format("No Device preference found for user with  Device id %s", deviceId));
                    }

                } else {
                    throw new RestServiceException(String.format("No Application preference found for user with  User id %s", userId));
                }

        } else {
            throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
        }
    }

    @RequestMapping(value = "/user/{userId}/app/{appId}/device/{deviceId}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve User Preference for an Application in a specific device environment by UserId ,Application Id and Device/Env Id ", response = DevicePreference.class)
    @ResponseBody
    public ResponseEntity<DevicePreference> retrieveDevicePreferenceByUserIdAndAppIdAndDeviceId (@PathVariable("userId") String userId, @PathVariable("appId") UUID appId, @PathVariable("deviceId") UUID deviceId) {

        try {
            UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);
            if (null != userPreference) {
                ApplicationPreference applicationPreference = DomainDataUtil.findAppPreferenceInUserPreferences(userPreference, appId).orElse(null);
                if (null != applicationPreference) {
                    DevicePreference devicePreference = DomainDataUtil.findDevicePreferenceInApplicationPreferences(applicationPreference, deviceId).orElse(null);
                    return new ResponseEntity<>(devicePreference, HttpStatus.OK);
                } else {
                    throw new RestServiceException(String.format("No UserPreference found for application with id %s", appId));
                }
            } else {
                throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
            }
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
            log.error(dataAccessException.getMessage());
            throw new RestServiceException(String.format("Could not retrieve application preferences in a device environment due to exception %s", dataAccessException.getMessage()));
        }

    }

    @RequestMapping(value = "/user/{userId}/app/{appId}/device/{deviceId}/component", method = RequestMethod.POST)
    @ApiOperation(value = "Create  Component Preferences for an  Application in a specific device environment ", response = ComponentPreference.class)
    @ResponseBody
    public ResponseEntity<ComponentPreference> createComponentPreference(@PathVariable("userId") String userId, @PathVariable("appId") UUID appId, @PathVariable("deviceId") UUID deviceId,@RequestBody ComponentPreference componentPreference) {

        //Not a good way to create.
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);

        if (null != userPreference) {
                ApplicationPreference applicationPreference = DomainDataUtil.findAppPreferenceInUserPreferences(userPreference, appId).orElse(null);
                if (null != applicationPreference) {
                   DevicePreference devicePreference = DomainDataUtil.findDevicePreferenceInApplicationPreferences(applicationPreference,deviceId).orElse(null);
                    if (null != devicePreference) {
                        List<ComponentPreference > listOfComponentPreferences = devicePreference.getComponentPreferences();
                        if(null==listOfComponentPreferences){
                            listOfComponentPreferences = new ArrayList<>();
                        }
                        listOfComponentPreferences.add(componentPreference);
                        userPreferenceRepository.save(userPreference);
                        return new ResponseEntity<>(componentPreference, HttpStatus.CREATED);
                    }else{
                        throw new RestServiceException(String.format("No Device preference found for the application with application id %s", appId));
                    }

                } else {
                    throw new RestServiceException(String.format("No Application preference found for user with  User id %s", userId));
                }

        } else {
            throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
        }
    }

    @RequestMapping(value = "/user/{userId}/app/{appId}/device/{deviceId}/component/{componentId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Component Preferences for an  Application in a specific device environment ", response = ComponentPreference.class)
    @ResponseBody
    public ResponseEntity<ComponentPreference> updateComponentPreference(@PathVariable("userId") String userId, @PathVariable("appId") UUID appId, @PathVariable("deviceId") UUID deviceId,@PathVariable("componentId") UUID componentId,@RequestBody ComponentPreference componentPreference) {

        //WARNING: Not a good way to update the json node.. This is only for demonstration
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);

        if (null != userPreference) {

            ApplicationPreference applicationPreference = DomainDataUtil.findAppPreferenceInUserPreferences(userPreference, appId).orElse(null);
            if (null != applicationPreference) {
                DevicePreference devicePreference = DomainDataUtil.findDevicePreferenceInApplicationPreferences(applicationPreference, deviceId).orElse(null);
                if (null != devicePreference) {
                    ComponentPreference existingComponentPreference = DomainDataUtil.findComponentPreferencesInDevicePreference(devicePreference,componentId).orElse(null);
                    if(null!= existingComponentPreference){
                        existingComponentPreference = componentPreference;
                        userPreferenceRepository.save(userPreference);
                        return new ResponseEntity<>(componentPreference, HttpStatus.OK);
                    }else{
                        throw new RestServiceException(String.format("No Component preference found for user with  Component id %s", deviceId));
                    }
                } else {
                    throw new RestServiceException(String.format("No Device preference found for user with  Device id %s", deviceId));
                }

            } else {
                throw new RestServiceException(String.format("No Application preference found for user with  User id %s", userId));
            }

        } else {
            throw new RestServiceException(String.format("No UserPreference found for user with id %s", userId));
        }

    }

    @RequestMapping(value = "/user/{userId}/app/{appId}/device/{deviceId}/component/{componentId}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve the User Preferences for a specific Component by User Id ,Application Id, Device Id and Component Id", response = ComponentPreference.class)
    @ResponseBody
    public ResponseEntity<ComponentPreference> retrieveComponentPreferenceyUserIdAndAppIdAndDeviceIdAndComponentId
            (@PathVariable("userId") String userId, @PathVariable("appId") UUID appId, @PathVariable("deviceId") UUID
                    deviceId, @PathVariable("componentId") UUID componentId) {

        try {
            UserPreference userPreference = userPreferenceRepository.findByUserId(userId).orElse(null);
            if (null != userPreference) {
                ApplicationPreference applicationPreference = DomainDataUtil.findAppPreferenceInUserPreferences(userPreference, appId).orElse(null);
                if (null != applicationPreference) {
                    DevicePreference devicePreference = DomainDataUtil.findDevicePreferenceInApplicationPreferences(applicationPreference, deviceId).orElse(null);
                    if (null != devicePreference) {
                        ComponentPreference componentPreference = DomainDataUtil.findComponentPreferencesInDevicePreference(devicePreference, componentId).orElse(null);
                        return new ResponseEntity<>(componentPreference, HttpStatus.OK);
                    } else {
                        throw new RestServiceException(String.format("No device preferences found for device with id  %s", deviceId));
                    }
                } else {
                    throw new RestServiceException(String.format("No  application preferences found for application with id  %s", deviceId));
                }
            } else {
                throw new RestServiceException(String.format("No User Preference found for user with id %s", userId));
            }
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
            log.error(dataAccessException.getMessage());
            throw new RestServiceException(String.format("Could not retrieve component preferences due to exception %s", dataAccessException.getMessage()));
        }

    }
}
