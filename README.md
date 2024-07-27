# GCP Cloud Resume API
This repository contains the resources for my submission to the Cloud Resume API Challenge using GCP as the cloud service provider. The challenge involves building a serverless function that fetches resume data stored in a NoSQL database and returns it in JSON format. Additionally, it integrates GitHub Actions to automatically deploy updates to your cloud serverless function whenever you push to your repository.

## High-Level Design
This design leverages the power of Google Cloud Platform services to manage API requests, handle authentication, execute backend logic, and retrieve data efficiently. The integration with GitHub and GitHub Actions ensures a seamless development and deployment workflow, enhancing productivity while maintaining high standards of security and performance.

![Architecture Diagram](./images/gcp-hld.png)

## Key Components
### **User Interaction**:
   - **API Request & Response**: Users send API requests and receive responses through the Apigee Proxy.
### **API Management with Apigee Proxy**:
   - **Apigee Proxy**: Acts as a facade for the serverless function, providing a secure and scalable API endpoint for external users. This is crucial because exposing the cloud function to public users can overwhelm the system, causing it to go down and increasing costs. 
   - **Quota Check**: Ensures that the API usage complies with defined quotas.
   - **Token Generation & Assignment** : Generates and assigns an ID token to the request URL invoking the cloud function, as the function requires authentication to execute the code. 
### **Cloud Functions**:
   - **Cloud Function**: The serverless function that fetches resume data from Firestore and returns it in JSON format. It is triggered by an HTTP request and requires an ID token for authentication.
   - **Deployment**: Cloud Functions are deployed using the gcloud functions deploy command, initiated by GitHub Actions.
### **Cloud IAM (Identity and Access Management)**:
   - **Token Authentication**: Ensures that only authorized users can access the cloud function by validating the ID token in the request URL.
### **Cloud Firestore**:
   - **Data Storage:**: Stores the resume data in a structured format, enabling efficient retrieval by the cloud function.
### **Development & Deployment Process**:
   - **GitHub Repository**: Contains the source code for the cloud function and the GitHub Actions workflow.
   - **GitHub Actions**: Automatically deploys the cloud function whenever changes are pushed to the repository, ensuring a continuous integration and deployment pipeline.