## Overview
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

## Getting Started
### **Prerequisites**:
1. Google Cloud Platform account with billing enabled.
2. `gcloud` CLI installed and configured with your GCP account.
3. Apigee account for managing APIs (Optional) - You can use the Cloud Functions URL directly if you don't want to use Apigee.
4. GitHub account for version control and CI/CD.
### **Project Setup**:
1. **Create a new project in GCP**.
   - Open the GCP Console and navigate to the project selector.
   - Click on `New Project` and enter a name for your project.
   - Click on `Create` to create the project.
   - Note down the Project ID, as you will need it later.
   - Enable billing for the project.
2. **Enable APIs**.
   - Navigate to the `APIs & Services` section in the GCP Console.
   - Click on `Enable APIs and Services`.
   - Enable the following APIs:
     - Cloud Functions API
     - Cloud Run Admin API
     - Firestore API
     - Cloud Resource Manager API
     - Apigee API (Optional)
3. **Create Service Accounts**.
    - Navigate to the `IAM & Admin` section in the GCP Console.
    - Click on `Service Accounts` and create the following service accounts.
        - `firestore-reader` for reading data from Firestore with following roles. To be used by the cloud function.
            - `Cloud Datastore User`
        - `github-actions` for deploying the cloud function using GitHub Actions.
            - `Cloud Functions Admin, Cloud Run Admin, Service Account User`
        - `apigee-proxy` for invoking the cloud function using Apigee (Optional).
            - `Cloud Functions Invoker, Cloud Run Invoker`
4. **Create a Firestore Database**.
   - Navigate to the `Firestore` section in the GCP Console.
   - Click on `Create Database`.
   - Choose the `Native Mode` and click on `Next`.
   - Select a location for your database and click on `Create`.
5. **Load Data into Firestore**.
   - Create a new collection in Firestore and add the resume data in JSON format.
   - Refer schema in `resume.json` for the resume data structure.
### **How to run it?**:
### **How to test it locally?**:
