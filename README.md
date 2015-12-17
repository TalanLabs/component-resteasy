# Component pour Resteasy
 
## Description

Permet la transformation de IComponent en json pour Resteasy

## Configuration

Ajouter dans le pom.xml :

```xml
<dependency>
	<groupId>com.synaptix</groupId>
	<artifactId>component-resteasy</artifactId>
	<version>1.0.0</version>
</dependency>
```

## Utilisation

Si pas de définition de nouveau module pour `ObjectMapper`, ajouter à Resteasy `SynaptixComponentJacksonConfig`.

Sinon ajouter le module à votre `ObjectMapper`

``` java
objectMapper = new ObjectMapper();
objectMapper.registerModule(new SynaptixComponentModule());
```
