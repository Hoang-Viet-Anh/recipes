# Recipes

JetBrains Academy. Project: Recipes.

<b>In first stage</b> implemented a simple service that supports two operations: adding (POST /api/recipe),
and retrieving (GET /api/recipe) a recipe. The service will be able to store only one recipe at a time.

A recipe includes 4 fields: name , description, ingredients, directions. Here's an example of the Fresh Mint Tea recipe:

{</br>
"name": "Fresh Mint Tea",</br>
"description": "Light, aromatic and refreshing beverage, ...",</br>
"ingredients": "boiled water, honey, fresh mint leaves",</br>
"directions": "1) Boil water. 2) Pour boiling hot water into a mug. 3) Add fresh mint leaves. 4) Mix and let the mint
leaves seep for 3-5 minutes. 5) Add honey and mix again."</br>
}

<b>In second stage</b>, improved the service to store a lot of recipes and access recipes by a unique id.

Rearranged the existing endpoints; the service should support the following:

POST /api/recipe/new receives a recipe as a JSON object and returns a JSON object with one id field. This is a uniquely generated number by which we can identify and retrieve a recipe later. The status code should be 200 (Ok).

GET /api/recipe/{id} returns a recipe with a specified id as a JSON object (where {id} is the id of a recipe). The server should respond with the 200 (Ok) status code. If a recipe with a specified id does not exist, the server should respond with 404 (Not found).

The new structure of a recipe includes the same 4 fields, but the type of two of them is different. ingredients and directions are arrays. Here's an example of the new structure:

{</br>
"name": "Warming Ginger Tea",</br>
"description": "Ginger tea is a warming drink for cool weather, ...",</br>
"ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],</br>
"directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]</br>
}

<b>In third stage</b> implemented one of the main features of the service – connect the service to a database and store 
the recipes there.

Implemented a new DELETE /api/recipe/{id} endpoint. It deletes a recipe with a specified {id}. The server should respond
with the 204 (No Content) status code. If a recipe with a specified id does not exist, the server should return 404 (Not found);

<b>In fourth stage</b> added two additional fields: <b>category</b> and <b>date</b>. Also added two endpoints:

PUT /api/recipe/{id} receives a recipe as a JSON object and updates a recipe with a specified id. Also, update the date field too. The server should return the 204 (No Content) status code. If a recipe with a specified id does not exist, the server should return 404 (Not found). The server should respond with 400 (Bad Request) if a recipe doesn't follow the restrictions indicated above (all fields are required, string fields can't be blank, arrays should have at least one item);

GET /api/recipe/search takes one of the two mutually exclusive query parameters:

1.category – if this parameter is specified, it returns a JSON array of all recipes of the specified category. Search is case-insensitive, sort the recipes by date (newer first);

2.name – if this parameter is specified, it returns a JSON array of all recipes with the names that contain the specified parameter. Search is case-insensitive, sort the recipes by date (newer first).

If no recipes are found, the program should return an empty JSON array. If 0 parameters were passed, or more than 1, the server should return 400 (Bad Request). The same response should follow if the specified parameters are not valid. If everything is correct, it should return 200 (Ok).