from db import db
from datetime import datetime

def enviar_mensaje(remitente_username, destinatario_username, contenido):
    users = db["users"]
    mensajes = db["mensajes"]

    # Buscar los usuarios por su username
    remitente = users.find_one({ "username": remitente_username })
    destinatario = users.find_one({ "username": destinatario_username })

    if not remitente or not destinatario:
        print(f"Usuario no encontrado. Remitente: {remitente_username}, Destinatario: {destinatario_username}")
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
    # Gabriel <-> Isabel
    enviar_mensaje("gabrielgm", "isabelallende", "Hola Isabel, ¿te interesa intercambiar 'Eva Luna' por 'El amor en los tiempos del cólera'?")
    enviar_mensaje("isabelallende", "gabrielgm", "Hola Gabriel, claro que sí. ¿Te parece bien el viernes a las 5pm?")
    enviar_mensaje("gabrielgm", "isabelallende", "Sí, está bien el viernes a las 5pm.")
    enviar_mensaje("isabelallende", "gabrielgm", "Perfecto, nos vemos entonces.")

    # Isabel <-> Pablo
    enviar_mensaje("isabelallende", "pabloneruda", "Hola Pablo, ¿tienes disponible 'Canto general'? Me interesa mucho.")
    enviar_mensaje("pabloneruda", "isabelallende", "Hola Isabel, sí lo tengo. ¿Qué libro ofreces a cambio?")
    enviar_mensaje("isabelallende", "pabloneruda", "Te puedo ofrecer 'Paula', está en excelente estado.")
    enviar_mensaje("pabloneruda", "isabelallende", "Trato hecho, ¿nos vemos el sábado?")

    # Gabriel <-> Pablo
    enviar_mensaje("gabrielgm", "pabloneruda", "Hola Pablo, tu poesía me encanta. ¿Quieres intercambiar 'Veinte poemas de amor'?")
    enviar_mensaje("pabloneruda", "gabrielgm", "Gracias Gabriel, por supuesto. ¿Qué libro propones?")
    enviar_mensaje("gabrielgm", "pabloneruda", "¿Qué tal 'Crónica de una muerte anunciada'?")
    enviar_mensaje("pabloneruda", "gabrielgm", "Me parece bien. ¿Nos encontramos el lunes?")
