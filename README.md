# BookTradeRestApi
The BookTradeRestApi interface provides the foundational data access logic for interacting with the users collection in MongoDB. It extends Spring Data’s MongoRepository, enabling out-of-the-box CRUD operations, pagination, and custom query methods on User documents.


✅ Current Capabilities:
Fetch users by unique fields:

username

email

Retrieve users with pagination (Page<User> findAll(Pageable pageable))

Delete users by:

id (ObjectId)

username

email

🧱 Technology Stack:
MongoDB with Spring Data

Primary key: ObjectId (MongoDB's default ID type)

🚧 Planned Extensions:
This repository will be extended to:

Support related collections (e.g., books and messages).

Facilitate more complex queries across users, favorites, and owned books.

Back a React frontend, enabling user list views, profile displays, and filtered interactions.

📁 Book_Trade_Scripts_Populate_Entities

🧩 Purpose


This folder contains Python scripts used to seed the MongoDB database with predefined users and books for the Book Trade application. These data sets represent notable Latin American authors and provide realistic entities for:

Backend API testing

Frontend development

Demos and presentations

QA environments

📄 Included Scripts

gabrielgm.py
Creates a user for Gabriel García Márquez and inserts several books including:

Cien años de soledad

El amor en los tiempos del cólera

Crónica de una muerte anunciada

pabloneruda.py
Adds a user for Pablo Neruda with poetic works such as:

Veinte poemas de amor y una canción desesperada

Canto general

Residencia en la tierra

isabelallende.py
Seeds a user for Isabel Allende and her titles including:

La casa de los espíritus

Paula

Eva Luna

⚙️ Usage Instructions
Make sure the MongoDB service is running (localhost or your cluster).

Set up the db.py connection string if needed.

Run any script:

python gabrielgm.py
python pabloneruda.py
python isabelallende.py