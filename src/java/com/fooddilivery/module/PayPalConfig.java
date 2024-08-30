package com.fooddilivery.module;

import java.util.HashMap;
import java.util.Map;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

public class PayPalConfig {

		private String clientId = "AcKVqCJmmC_24akM6HmZ8udw9_KIFYWZoptXsb9zDMSUbkPTeprjSnULdTAm6KYfQGJV3WBiiERlex6v";
	    private String clientSecret ="EAsPF05PXK3LHIYIueJ4EuYewn6hJSg2URqXn7kcrDMONlykEnCMvACtIDjhc6MoeD_kwzLh695pHg4k";
	    private String mode = "sandbox";
      //sb-r7iaj32291864@personal.example.com
	    public PayPalConfig() {	   }

	    public Map<String, String> getConfig() {
	        Map<String, String> configMap = new HashMap<>();
	        configMap.put("mode", mode);
	        return configMap;
	    }

	    public APIContext getAPIContext() throws PayPalRESTException {
	        @SuppressWarnings("deprecation")
			APIContext context = new APIContext(new OAuthTokenCredential(clientId, clientSecret, getConfig()).getAccessToken());
	        context.setConfigurationMap(getConfig());
	        return context;
	    }
	
	
}
