# codingsolutions

Small, self-contained examples for Java, Python, C#, and SQL.  
These samples demonstrate basic CRUD operations, backend APIs, ML model serving, JSON persistence, and database queries.

---

## 📂 Contents

- **Java/StudentManager.java** — CLI CRUD using CSV file (file I/O, collections).
- **Python/train_model.py** — ML training script using scikit-learn.
- **Python/app.py** — FastAPI prediction endpoint using the trained model.
- **CSharp/TodoApp.cs** — Console Todo app with JSON storage.
- **SQL/schema_and_queries.sql** — Normalized schema, joins, aggregation, index, stored procedure.

---

## 🚀 How to Run

### Java
javac Java/StudentManager.java  
java -cp Java StudentManager  

---

### Python (ML + FastAPI)

Install required packages:
pip install scikit-learn joblib fastapi uvicorn

Train the model:
python Python/train_model.py

Run FastAPI server:
uvicorn Python.app:app --reload --port 8000

Send POST request to:
http://127.0.0.1:8000/predict

Sample JSON:
{
  "sepal_length": 5.1,
  "sepal_width": 3.5,
  "petal_length": 1.4,
  "petal_width": 0.2
}

---

### C#

Compile:
csc CSharp/TodoApp.cs

Run:
TodoApp.exe

---

### SQL

Run this file in your database:
SQL/schema_and_queries.sql

It includes:
- Table creation  
- Sample inserts  
- JOIN queries  
- Aggregation  
- Index  
- Stored procedure  

---

## 📘 Summary

This repository contains small, practical examples demonstrating:
- Java CLI application development  
- Python ML training + FastAPI backend  
- C# console application with JSON storage  
- SQL schema design and querying  

These samples are ideal for coding portfolio and placement assessments.
