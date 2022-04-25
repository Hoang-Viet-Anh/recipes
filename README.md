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