#  pabloneruda_isabelallende

from db import db
from datetime import datetime

def enviar_mensaje(remitente_username, destinatario_username, contenido):
    users = db["users"]
    mensajes = db["mensajes"]

    # Buscar los usuarios por su username
    remitente = users.find_one({ "username": remitente_username })
    destinatario = users.find_one({ "username": destinatario_username })

    if not remitente or not destinatario:
        print(" Usuario no encontrado.")
        return

    mensaje = {
        "remitente_id": remitente["_id"],
        "destinatario_id": destinatario["_id"],
        "contenido": contenido,
        "timestamp": datetime.utcnow()
    }

    result = mensajes.insert_one(mensaje)
    print(f"Mensaje enviado. ID: {result.inserted_id}")

# Ejemplo de uso
if __name__ == "__main__":
    enviar_mensaje(
        remitente_username="pabloneruda",
        destinatario_username="isabelallende",
        contenido="Hola Isabel, ¿te interesa intercambiar 'Eva Luna' por 'El amor en los tiempos del cólera'?"
    )
    enviar_mensaje(
        remitente_username="isabelallende",
        destinatario_username="pabloneruda",
        contenido="Hola Pablo, claro que sí. ¿Te parece bien el viernes a las 5pm?"
    )
    enviar_mensaje(
        remitente_username="isabelallende",
        destinatario_username="pabloneruda",
        contenido="Hola Isabel, claro que sí. esta bien el viernes a las 5pm"
    )
    enviar_mensaje(
        remitente_username="isabelallende",
        destinatario_username="pabloneruda",
        contenido="Hola Pablo, te veo el viernes a las 5pm"
    )

# Masajes entre Isabel Allende y Gabriel García Márquez - completados

# Mensajes entre Isabel Allende y Pablo Neruda

# Mensajes entre Isabel Grabreie García Márquez y Pablo Neruda

