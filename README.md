# Storely File Service – Backend

A beginner-friendly backend project built using **Spring Boot** that demonstrates how file upload and file serving work in a real application.

The project focuses on understanding **server-side file handling**, **HTTP streaming**, and **basic backend architecture**.

---

## 🚀 Features

- Upload single or multiple files
- Store files on the server (local directory)
- Serve files using HTTP response
- View PDFs and images directly in browser
- Secure filename handling (prevents path traversal)
- Basic Spring Security configuration
- CORS enabled for frontend integration

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Java I/O (`Path`, `Files`, `InputStream`, `OutputStream`)

---

## 📁 Project Structure

```
StorelyBackend
│
├── controller
│   └── FileUploadController.java
│
├── service
│   ├── FileService.java
│   └── FileServiceImpl.java
│
├── config
│   ├── SecurityConfig.java
│   └── CorsConfig.java
│
├── uploads/               # Stored files
│
└── StorelyBackendApplication.java
```

---

## 📤 Upload Files

**Endpoint**

```
POST /files/uploads
```

**Request Type**

```
multipart/form-data
```

**Form Parameter**

```
files : MultipartFile[]
```

Example using Postman or frontend:

```
files → select one or more files
```

---

## 📥 View / Download File

**Example**

```

[http://localhost:8080/files/uploads/sample.pdf]

```

Supported in browser:

- Images (`.png`, `.jpg`)
- PDF files

---

## ⚙️ How Files Are Stored

- Files are saved inside the `uploads/` directory
- Each file is stored with a unique UUID prefix
- Original filenames are sanitized for security

## 🔐 Security Notes

- Path traversal protection applied
- Unsafe filenames are sanitized
- Spring Security configured to allow:
  - file uploads
  - file downloads
- No database is used

---

## ⚠️ Current Limitations

- Local file storage only
- No database
- No user authentication
- No file ownership control
- Encryption & decryption not implemented yet

---

## 🎯 Learning Outcomes

This project helped me understand:

- Multipart file upload flow
- Server-side file storage
- Streaming files via HTTP
- Difference between InputStream and OutputStream
- Spring Security request handling
- Backend–frontend communication

---

## 🔜 Future Enhancements

- File encryption and decryption
- User-based access control
- Secure private file downloads
- File metadata management

---

## ▶️ Run Locally

```bash
git clone https://github.com/swatishaw1/Storely
cd storely-backend
```

```bash
mvn spring-boot:run
```

Server runs at:

```
http://localhost:8080
```

---

## 👤 Author

**Swati Shaw**
Computer Science Student
Backend Development Learner
