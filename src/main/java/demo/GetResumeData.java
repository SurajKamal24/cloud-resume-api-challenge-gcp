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
    // Logger to log information and errors
    private static final Logger logger = Logger.getLogger(GetResumeData.class.getName());
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        // Initialize Firestore service
        Firestore service = FirestoreOptions.getDefaultInstance().getService();
        try{
            // Asynchronously retrieve the document from the "resumes" collection with ID "1"
            ApiFuture<DocumentSnapshot> resumes = service.collection("resumes").document("1").get();
            DocumentSnapshot documentSnapshot = resumes.get();

            // Check if the document exists
            if (documentSnapshot.exists()) {
                // Retrieve data from the document and write it to the response as JSON
                Map<String, Object> data = documentSnapshot.getData();
                httpResponse.setContentType("application/json");
                httpResponse.getWriter().write(data.get("data").toString());
            } else {
                // Document does not exist, write a message to the response
                httpResponse.getWriter().write("No such document!");
            }
        } catch (Exception e) {
            // Log the error and write an error message to the response
            logger.severe("Error accessing Firestore: " + e.getMessage());
            httpResponse.getWriter().write("Error: " + e.getMessage());
        }
    }
}
