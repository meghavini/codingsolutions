# File: Python/train_model.py
from sklearn.datasets import load_iris
from sklearn.linear_model import LogisticRegression
import joblib

def train_and_save():
    data = load_iris()
    X, y = data.data, data.target
    model = LogisticRegression(max_iter=200)
    model.fit(X, y)
    joblib.dump(model, "iris_model.joblib")
    print("Model trained and saved: iris_model.joblib")

if __name__ == "__main__":
    train_and_save()
