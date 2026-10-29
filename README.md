# HTTPWebService – Android Application

 

## 1. Experiment Overview

 

**Aim:** To implement an Android application that accesses web services using HTTP requests, retrieves data from a remote server, and displays the response in the application.

 

### Description

 

This experiment demonstrates how an Android application communicates with a web server using an HTTP GET request. The application connects to a REST API, retrieves data in JSON format, parses the response, and displays the information through a user-friendly interface. The application also displays the request status and handles network errors.

 

## 2. Concepts and Technologies Used

 

### HTTP Requests

 

HTTP (Hypertext Transfer Protocol) enables communication between a client and a server.

 

Common HTTP methods include:

 

- **GET:** Retrieves data from a server.

- **POST:** Sends data to a server.

- **PUT:** Updates existing data.

- **DELETE:** Deletes data from a server.

 

This experiment uses the GET method to retrieve information from a REST API.

 

### REST API

 

A REST API allows applications to communicate with remote servers through defined endpoints.

 

API endpoint: `https://jsonplaceholder.typicode.com/posts/1`

 

### JSON Parsing

 

JSON (JavaScript Object Notation) is a lightweight format for exchanging data. The application parses the JSON response and extracts these fields:

 

- `id` – Post ID

- `userId` – User ID

- `title` – Post title

- `body` – Post content

 

### Technologies Used

 

- Android Studio

- Kotlin

- XML

- HttpURLConnection

- JSON parsing using `JSONObject`

- JSONPlaceholder REST API

 

## 3. Scenario Demonstrated

 

The application demonstrates retrieving a post from a remote web service.

 

### Execution Flow

 

1. The user launches the HTTPWebService application.

2. The application displays the home screen with a **Fetch Data** button.

3. The user clicks the button.

4. The application sends an HTTP GET request to the API endpoint.

5. The server returns a JSON response.

6. The application parses the response.

7. The Post ID, User ID, title, and body are displayed on the screen.

8. If the request fails, an appropriate error message is displayed.

 

### API Details

 

| Property | Value |

|---|---|

| API Endpoint | `https://jsonplaceholder.typicode.com/posts/1` |

| HTTP Method | GET |

| Response Format | JSON |

| Expected Success Status | HTTP 200 |

 

## 4. Project Folder and File Structure

 

```text

HTTPWebService/

├── app/

│   ├── src/

│   │   └── main/

│   │       ├── java/

│   │       │   └── com/example/httpwebservice/

│   │       │       └── MainActivity.kt

│   │       ├── res/

│   │       │   ├── drawable/

│   │       │   │   ├── bg_header.xml

│   │       │   │   ├── bg_card.xml

│   │       │   │   └── bg_fetch_button.xml

│   │       │   ├── layout/

│   │       │   │   └── activity_main.xml

│   │       │   └── values/

│   │       └── AndroidManifest.xml

│   ├── build.gradle.kts

│   └── ...

├── gradle/

├── build.gradle.kts

├── settings.gradle.kts

├── gradle.properties

├── README.md

└── Screenshots/

    ├── Output 1.png

    └── Output 2.png

```

 

**Note:** The folder structure may vary slightly depending on the package name and resources configured in Android Studio.

 

### Important Files

 

| File | Purpose |

|---|---|

| `MainActivity.kt` | Implements the HTTP request, processes the JSON response, and updates the UI. |

| `activity_main.xml` | Defines the application's user interface. |

| `bg_header.xml` | Defines the gradient header background. |

| `bg_card.xml` | Defines the rounded card background. |

| `bg_fetch_button.xml` | Defines the custom button background. |

| `AndroidManifest.xml` | Declares application components and internet permission. |

| `build.gradle.kts` | Configures application dependencies and SDK versions. |

 

## 5. Output Screenshots

 

### Output 1: Application Home Screen

 

The initial screen displays the HTTP Web Service heading, API request details, Fetch Data button, and response section.

 

![Output 1 - Application Home Screen](Screenshots/Output%201.png)

 

### Output 2: Successful API Response

 

After clicking the Fetch Data button, the application retrieves the JSON response and displays the Post ID, User ID, title, body, and request status.

 

![Output 2 - API Response](Screenshots/Output%202.png)

 

## 6. How to Run the Application

 

1. Clone or download the project from GitHub.

2. Open the project in Android Studio.

3. Allow Gradle synchronization to complete.

4. Ensure that the required Android SDK is installed.

5. Verify that internet permission is present in `AndroidManifest.xml`.

6. Run the application on an emulator or Android device with internet access.

7. Click the **Fetch Data** button.

8. Verify that the server response appears on the screen.

 

### Internet Permission

 

Declare the following permission in `AndroidManifest.xml`, outside the `<application>` element:

 

```xml

<uses-permission android:name="android.permission.INTERNET" />

```

 

## 7. Test Cases

 

| Test Case | Action | Expected Result |

|---|---|---|

| 1 | Launch the application | Home screen is displayed. |

| 2 | Click Fetch Data with internet access | JSON response is retrieved and displayed. |

| 3 | Disable internet and click Fetch Data | Network error is displayed. |

| 4 | Restore internet and retry | Data is retrieved successfully. |

| 5 | Receive an unsuccessful HTTP response | Error status is displayed. |

 

## 8. Result

 

Thus, an Android application was implemented successfully to access a web service using an HTTP GET request. The application retrieves JSON data from a remote server, processes the response, and displays the information in a user-friendly interface.
