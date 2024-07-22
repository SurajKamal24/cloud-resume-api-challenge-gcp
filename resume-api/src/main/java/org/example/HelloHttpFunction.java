package org.example;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.util.logging.Logger;

public class HelloHttpFunction implements HttpFunction {
    private static final Logger logger = Logger.getLogger(HelloHttpFunction.class.getName());
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        Firestore service = FirestoreOptions.getDefaultInstance().getService();
        try{
            ApiFuture<DocumentSnapshot> resumes = service.collection("resumes").document("1").get();
            DocumentSnapshot documentSnapshot = resumes.get();
            if (documentSnapshot.exists()) {
                httpResponse.getWriter().write(documentSnapshot.getData().toString());
            } else {
                httpResponse.getWriter().write("No such document!");
            }
        } catch (Exception e) {
            logger.severe("Error accessing Firestore: " + e.getMessage());
            httpResponse.getWriter().write("Error: " + e.getMessage());
        }
    }
}
