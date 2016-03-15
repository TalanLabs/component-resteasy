# Component pour Resteasy
 
## Description

Permet la transformation de IComponent en json pour Resteasy

## Configuration

Ajouter dans le pom.xml :

```xml
<dependency>
	<groupId>com.synaptix</groupId>
	<artifactId>component-resteasy</artifactId>
	<version>2.1.0</version>
</dependency>
```

## Utilisation

Si pas de définition de nouveau module pour `ObjectMapper`, ajouter à Resteasy `ComponentBeanJacksonConfig`.

Sinon ajouter le module à votre `ObjectMapper`

``` java
objectMapper = new ObjectMapper();
objectMapper.registerModule(new ComponentBeanModule());
```

Si vous avez des champs dans le Json qui ne sont pas dans votre Component ajouter

``` java
this.objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
```
