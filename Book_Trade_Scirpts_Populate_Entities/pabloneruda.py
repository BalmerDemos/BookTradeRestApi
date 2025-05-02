# pabloneruda.py
# Pablo Neruda
from db import db
from datetime import datetime

def create_pablo_user_and_books():
    users = db["users"]
    libros = db["books"]

    #  Crear usuario Pablo Neruda
    user = {
        "username": "pabloneruda",
        "email": "pabloneruda@example.com",
        "favoritos": [],
        "libros": []
    }

    user_result = users.insert_one(user)
    user_id = user_result.inserted_id

    #  Insertar libros de Pablo Neruda
    books = [
        {
            "titulo": "Veinte poemas de amor y una canción desesperada",
            "autor": "Pablo Neruda",
            "anio": 1924,
            "estado": "Muy bueno",
            "etiquetas": ["poesía", "amor", "latinoamérica"],
            "categoria": "Poesía",
            "disponible": True,
            "propietario_id": user_id,
            "intercambios": []
        },
        {
            "titulo": "Canto general",
            "autor": "Pablo Neruda",
            "anio": 1950,
            "estado": "Bueno",
            "etiquetas": ["historia", "latinoamérica", "épico"],
            "categoria": "Poesía",
            "disponible": True,
            "propietario_id": user_id,
            "intercambios": []
        },
        {
            "titulo": "Residencia en la tierra",
            "autor": "Pablo Neruda",
            "anio": 1933,
            "estado": "Aceptable",
            "etiquetas": ["existencialismo", "modernismo", "poesía"],
            "categoria": "Poesía",
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

    print(f"Pablo Neruda creado con {len(result.inserted_ids)} libros.")

if __name__ == "__main__":
    create_pablo_user_and_books()

