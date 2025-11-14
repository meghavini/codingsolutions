# File: Python/app.py
from fastapi import FastAPI
import joblib
from pydantic import BaseModel
import uvicorn

class IrisRequest(BaseModel):
    sepal_length: float
    sepal_width: float
    petal_length: float
    petal_width: float

app = FastAPI()
try:
    model = joblib.load("iris_model.joblib")
except:
    model = None

@app.get("/")
def root():
    return {"message": "FastAPI ML demo. Train model first with train_model.py"}

@app.post("/predict")
def predict(r: IrisRequest):
    if model is None:
        return {"error": "Model not found. Run train_model.py first."}
    x = [[r.sepal_length, r.sepal_width, r.petal_length, r.petal_width]]
    pred = int(model.predict(x)[0])
    return {"prediction": pred}
