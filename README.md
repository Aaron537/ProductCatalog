# Product Catalog
A simple application using Spring Boot and Hibernate to manage a catalog of products. Supported by PostgreSQL and containerized using docker-compose

# Instructions
1. Pull the main branch
2. Navigate to the root directory of the project
3. Run the following command to instruct docker-compose to containerize the application and database: <br />
```docker-compose up --build```
4. (Optional) Populate the database using the provided products.json, located in src/main/resources
   - Prepare a POST request to the following endpoint, with the body of the request being the contents of products.json <br />
  ```localhost:8080/catalog/populateProducts``` <br />
   Additional details and examples can be found in the API Overview section
5. Follow the Readme in the UI project repository to connect this backend to the React frontend: <br />
```https://github.com/Aaron537/ProductCatalog-UI```

# API Overview
## GET: localhost:8080/catalog/products <br />
- Returns a JSON object containing all products currently stored in the catalog.<br />
```No request body``` <br />
    Example return object: <br />
    ```
    {
        "retailer": "XYZ Retail",
        "brand": "Bosch",
        "model": "WTZSB30UC",
        "price": 0.0,
        "product_key": 12952635,
        "product_name": "Dryer Wall Mounting Bracket (Silver)",
        "product_description": "1: Bracket Style | 2: Stainless Steel | 3: Hardware Included"
    },
    {
        "retailer": "XYZ Retail",
        "brand": "GIANT ART",
        "model": "WAG122733A2",
        "price": 0.0,
        "product_key": 3665295,
        "product_name": "54-in H x 54-in W Abstract Print on Canvas",
        "product_description": "Giant artworks creates stunning focal points. It gives a wow factor to any room décor. All you need is a large bold piece of art to give an instant stylish upgrade. Because of its dimensions, we have patented a clever solution to allow huge canvas prints into your home easily. We include ready to assemble tool free stretchers that will enable you to assemble effortlessly. All Giant Art prints are made from highest quality Eco-friendly materials for crisp, well defined, luxurious reproduction. Highest quality inkjet printing. Poly-cotton canvas for crisp definition. Archival light-fade resistant inks. Mirror edge over 1.5&#8221; deep aluminium stretcher bars. Includes a patented ready to assemble stretching system and hardware to mount.1: Proudly made in America | 2: Fine resolution Art reproductions curated by our team of specialists | 3: High quality canvas for superior color and crisp detail | 4: Printed with eco-friendly inks and canvas for a greener world | 5: Easy to assemble Giant Art pieced with our patented stretcher bar system"
    }
    ```
## POST: localhost:8080/catalog/products <br />
- Accepts a Product as a JSON object and adds it to the database <br />
Example request body: <br />
```
{
    "product_name": "TESTNAME",
    "brand": "TESTBRAND",
    "price": 0.01,
    "model": "TESTMODEL",
    "product_description": "TESTDESCRIPTION",
    "product_key": "1100101001",
    "retailer": "TESTRETAILER"
}
```
Example return object: <br />
```
{
    "product_name": "TESTNAME",
    "brand": "TESTBRAND",
    "price": 0.01,
    "model": "TESTMODEL",
    "product_description": "TESTDESCRIPTION",
    "product_key": "1100101001",
    "retailer": "TESTRETAILER"
}
```
## POST: localhost:8080/catalog/populateProducts <br />
- Accepts any number of Products as JSON objects and adds them to the database <br />
Example request body: <br />
```
{
        "retailer": "XYZ Retail",
        "brand": "Bosch",
        "model": "WTZSB30UC",
        "price": 0.0,
        "product_key": 12952635,
        "product_name": "Dryer Wall Mounting Bracket (Silver)",
        "product_description": "1: Bracket Style | 2: Stainless Steel | 3: Hardware Included"
    },
    {
        "retailer": "XYZ Retail",
        "brand": "GIANT ART",
        "model": "WAG122733A2",
        "price": 0.0,
        "product_key": 3665295,
        "product_name": "54-in H x 54-in W Abstract Print on Canvas",
        "product_description": "Giant artworks creates stunning focal points. It gives a wow factor to any room décor. All you need is a large bold piece of art to give an instant stylish upgrade. Because of its dimensions, we have patented a clever solution to allow huge canvas prints into your home easily. We include ready to assemble tool free stretchers that will enable you to assemble effortlessly. All Giant Art prints are made from highest quality Eco-friendly materials for crisp, well defined, luxurious reproduction. Highest quality inkjet printing. Poly-cotton canvas for crisp definition. Archival light-fade resistant inks. Mirror edge over 1.5&#8221; deep aluminium stretcher bars. Includes a patented ready to assemble stretching system and hardware to mount.1: Proudly made in America | 2: Fine resolution Art reproductions curated by our team of specialists | 3: High quality canvas for superior color and crisp detail | 4: Printed with eco-friendly inks and canvas for a greener world | 5: Easy to assemble Giant Art pieced with our patented stretcher bar system"
    }
```
Example return: <br />
```
Products successfully populated
```
## GET: localhost:8080/catalog/products/{productKey}
- Generates a report of how many products are listed under each unique brand. <br />
```No request body``` <br />
Example return object: <br />
```
[
    {
        "count": 1,
        "brand": "HeadLOK  by FastenMaster"
    },
    {
        "count": 1,
        "brand": "Savoy House"
    },
    {
        "count": 1,
        "brand": "Midea"
    },
    {
        "count": 7,
        "brand": "Fortress Building Products"
    },
    {
        "count": 1,
        "brand": "Metal Sales"
    },
    {
        "count": 3,
        "brand": "Savoy House Essentials"
    },
    {
        "count": 10,
        "brand": "Amerock"
    },
    {
        "count": 1,
        "brand": "NewTechWood"
    },
    {
        "count": 2,
        "brand": "Tommy Docks"
    }
]
```
