# isabelallende.py
# Isabel Allende
from db import db
from datetime import datetime

def create_isabel_user_and_books():
    users = db["users"]
    libros = db["books"]

    #  Crear usuario Isabel Allende
    user = {
        "username": "isabelallende",
        "email": "isabelallende@example.com",
        "favoritos": [],
        "libros": []
    }

    user_result = users.insert_one(user)
    user_id = user_result.inserted_id

    #  Insertar libros de Isabel Allende
    books = [
        {
            "titulo": "La casa de los espíritus",
            "autor": "Isabel Allende",
            "anio": 1982,
            "estado": "Muy bueno",
            "etiquetas": ["realismo mágico", "familia", "latinoamérica"],
            "categoria": "Ficción",
            "disponible": True,
            "propietario_id": user_id,
            "intercambios": []
        },
        {
            "titulo": "Paula",
            "autor": "Isabel Allende",
            "anio": 1994,
            "estado": "Bueno",
            "etiquetas": ["autobiografía", "familia", "memorias"],
            "categoria": "No ficción",
            "disponible": True,
            "propietario_id": user_id,
            "intercambios": []
        },
        {
            "titulo": "Eva Luna",
            "autor": "Isabel Allende",
            "anio": 1987,
            "estado": "Bueno",
            "etiquetas": ["cuentos", "mujeres", "latinoamérica"],
            "categoria": "Ficción",
            "disponible": True,
            "propietario_id": user_id,
            "intercambios": []
        }
    ]

    result = libros.insert_many(books)

    #  Asociar libros al usuario
    users.update_one(
        {"_id": user_id},
        {"$set": {"libros": list(result.inserted_ids)}}
    )

    print(f"Isabel Allende creada con {len(result.inserted_ids)} libros.")

if __name__ == "__main__":
    create_isabel_user_and_books()
