package com.github.jolymatt.playground.userpref;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc

/**
 * This IHMUserPrefApplication
 * @author Joly Mathew
 */
@Slf4j
public class IHMUserPrefApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication springApplication = new SpringApplication(IHMUserPrefApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);
    }
//    @Bean
//    CommandLineRunner init(UserPreferenceRepository userPreferenceRepository) {

//        return args -> {
//            UserPreference userPreference = JSONData.getUPPreferenceData();
//            String userId = userPreference.getUserId();
//            userPreferenceRepository.save(userPreference);
//
//            try {
//                log.info("Searching with  {} ",userId);
//                UserPreference preferences1 = userPreferenceRepository.findByUserId(userId).orElse(null) ;// userPreferenceRepository.findByUserIdAndApplicationPreferencesApplicationIdAndApplicationPreferencesDevicePreferencesDeviceId(userId, applicationId, deviceId);
//                log.info("{}",preferences1);
//            }catch(RuntimeException dataAccessException) {
//                dataAccessException.printStackTrace();
//            }

//        };

//}

}