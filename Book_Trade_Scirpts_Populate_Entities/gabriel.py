# gabrielgm.py
# Gabriel García Márquez
from db import db
from bson import ObjectId
from datetime import datetime

def create_gabriel_user_and_books():
    users = db["users"]
    libros = db["books"]

    #  Crear el usuario
    user = {
        "username": "gabrielgm",
        "email": "gabrielgm@example.com",
        "favoritos": [],
        "libros": []
    }

    user_result = users.insert_one(user)
    user_id = user_result.inserted_id

    #  Libros de Gabriel García Márquez
    books = [
        {
            "titulo": "Cien años de soledad",
            "autor": "Gabriel García Márquez",
            "año": 1967,
            "estado": "Bueno",
            "etiquetas": ["realismo mágico", "Macondo", "literatura latina"],
            "categoria": "Ficción",
            "disponible": True,
            "propietario_id": user_id,
            "intercambios": []
        },
        {
            "titulo": "El amor en los tiempos del cólera",
            "autor": "Gabriel García Márquez",
            "año": 1985,
            "estado": "Muy bueno",
            "etiquetas": ["amor", "narrativa", "latinoamérica"],
            "categoria": "Romance",
            "disponible": True,
            "propietario_id": user_id,
            "intercambios": []
        },
        {
            "titulo": "Crónica de una muerte anunciada",
            "autor": "Gabriel García Márquez",
            "año": 1981,
            "estado": "Aceptable",
            "etiquetas": ["misterio", "crimen", "colombia"],
            "categoria": "Ficción",
            "disponible": True,
            "propietario_id": user_id,
            "intercambios": []
        }
    ]

    book_result = libros.insert_many(books)

    #  Agregar IDs de libros al usuario
    users.update_one(
        {"_id": user_id},
        {"$set": {"libros": list(book_result.inserted_ids)}}
    )

    print(f"Usuario y libros de Gabriel creados con ID: {user_id}")

if __name__ == "__main__":
    create_gabriel_user_and_books()
