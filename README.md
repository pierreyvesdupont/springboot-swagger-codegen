Génération des sources:
-----------------------

Le plugin maven de génération utilisé est : openapi-generator-maven-plugin
https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin

# A partir de src/main/resources/openapi.yml
$ mvn install

Le plugin génère depuis l'OAS (target/generated-sources/openapi/src/main/java/tej/SwaggerCodgen) :
- api: les interfaces que doivent implementer les Controller Springboots, contenant les actions décrites dans l'OAS
- model: les classes d'objets décrites dans l'OAS

# Pour lancer le serveur tomcat
$ mvn spring-boot:run

# Accéder à l'interface swagger déployée (pour tester les enpoints des @RestController)
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

# Bibliographie
open-api-generator Roadmap :
https://openapi-generator.tech/docs/roadmap/#short-term

28/01/2022 : Short-term
- OAS3.0 features support: anyOf, oneOf, callbacks, etc

oneof peut-être utilisé de trois manières différentes :

1. Comme une interface dans le modèle de composants
----------------------------------------------------
components:
  schemas:
    Cat:
      type: object
      required:
      ...
      properties:
      ...
    Dog:
      type: object
      required:
      ...
      properties:
      ...
    Pet:
      type: object
      oneOf:
        - $ref: '#/components/schemas/Cat'
        - $ref: '#/components/schemas/Dog'    

La génération va créer trois Classes :
- Cat
- Dog
- Pet
Sans lien de parentée

Le composant Pet peut être utilisé dans les paths :
paths:
  /pets:
    post:
      ...
      requestBody:
        description: Pet to be added in petstore
        content:
          application/json:
            schema:
              oneOf:
                - $ref: '#/components/schemas/Cat'
                - $ref: '#/components/schemas/Dog'
        required: true
      responses:
        ...
      x-codegen-request-body-name: pet 

La signature générée sera syntaxiquement correcte mais référera à la classe Pet, qui n'est pas une abstraction de Cat et de Dog.

De ce fait, il faut manuellement définir que Cat et Dog implements / extends Pet.
! Attention aux effets de bord dans la définition / signature des methodes de PetsApi.

2. Comme un type flottant dans le modèle de composants
------------------------------------------------------

Directement utilisé dans les paths:

post:
    summary: Create a pet
    operationId: createPets
    tags:
    - pets
    requestBody:
    description: Pet to be added in petstore
    content:
        application/json:
        schema:
            oneOf:
            - $ref: '#/components/schemas/Cat'
            - $ref: '#/components/schemas/Dog'
    required: true

La génération va créer une signature de methode dans PatsApi qui ne compile pas :
default ResponseEntity<String> createPets(UNKNOWN_BASE_TYPE pet);

Il faut donc manuellement specifier le type de l'objet pet.

3. Comme un attribut flottant dans un modèle
--------------------------------------------

components:
    SubscriptionRequest:
      type: object
      properties:
        ...
        customers:
          type: array
          items:
            discriminator:
              propertyName: legalStatus
            oneOf:
            - $ref: '#/components/schemas/NaturalPerson'
            - $ref: '#/components/schemas/LegalEntity'

Dans ce cas, customers va être généré comme un attribut de la classe SubscriptionRequest de type List<Object>.

Charge à l'implémentation de créer les bons objets qui peupleront la liste customers.