package com.github.jolymatt.playground.userpref.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * The ApplicationPreference class
 * @author Joly Mathew
 */
@Setter
@Getter
public class ApplicationPreference extends PropertyBundle {
    List<DevicePreference> devicePreferences;

}
