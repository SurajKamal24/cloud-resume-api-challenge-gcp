package demo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import java.util.Map;
import java.util.logging.Logger;

public class GetResumeData implements HttpFunction {
    private static final Logger logger = Logger.getLogger(GetResumeData.class.getName());
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        Firestore service = FirestoreOptions.getDefaultInstance().getService();
        try{
            ApiFuture<DocumentSnapshot> resumes = service.collection("resumes").document("1").get();
            DocumentSnapshot documentSnapshot = resumes.get();
            if (documentSnapshot.exists()) {
                Map<String, Object> data = documentSnapshot.getData();
                httpResponse.setContentType("application/json");
                httpResponse.getWriter().write(data.get("data").toString());
            } else {
                httpResponse.getWriter().write("No such document!");
            }
        } catch (Exception e) {
            logger.severe("Error accessing Firestore: " + e.getMessage());
            httpResponse.getWriter().write("Error: " + e.getMessage());
        }
    }
}
