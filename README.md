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
