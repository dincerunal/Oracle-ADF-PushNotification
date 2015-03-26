package application;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.event.Event;
import oracle.adfmf.framework.event.EventListener;
import oracle.adfmf.framework.exception.AdfException;

public class NativePushNotificationListener implements EventListener {
    public NativePushNotificationListener() {
        super();
    }

    public void onMessage(Event event) {
        //Parsing the payload JSON string
        JSONBeanSerializationHelper jsonHelper = new JSONBeanSerializationHelper();
        try {
            PayloadServiceResponse serviceResponse =
                (PayloadServiceResponse)jsonHelper.fromJSON(PayloadServiceResponse.class, event.getPayload());
            //Can access the variables like below
            serviceResponse.getCustomMessage();
            // To do 
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void onError(AdfException adfException) {
    }

    public void onOpen(String string) {
        // Storing the regisration id send by GCM in #{applicationScope.deviceToken} for further usage
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.deviceToken}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), string);
    }
}
