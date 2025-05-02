# db.py
from pymongo import MongoClient

#  Reemplaza esto con tu string real de conexión
# CONNECTION_STRING = "mongodb+srv://<username>:<password>@<cluster>.mongodb.net/?retryWrites=true&w=majority"
CONNECTION_STRING = "mongodb://localhost:27017/book_trade"
client = MongoClient(CONNECTION_STRING)
db = client["book_trade"]
