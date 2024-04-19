## Install Redis
 * install redis windows git 
1. Download Redis from https://github.com/tporadowski/redis/releases
2. Run .msi file (Redis-x64-5.0.14.1.msi)
## Verify installation 
1. Open command prompt from the redis path installed (C:\Program Files\Redis)
2. give command redis-server
3. Result should come as Redis started
## Open ProductService API, make following changes
1. Add Redis dependency in pom.xml
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <version>3.2.3</version>
</dependency>
```
2. Add Redis configuration in application.properties
```properties
spring.data.redis.host= localhost
spring.data.redis.port= 6379
```
3. Add below configuration at path src/main/java/com/example/productservice/Config/RedisConfig.java

```java

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;

    }
    }
```
4. implement Serializable for GenericProductDto, because it is a return typ in GetproductByid controller
```java
public class GenericProductDto  implements  java.io.Serializable{
   
``` 
5. Add below code in FakeStoreProductService.java
```java
@Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
    GenericProductDto genericProductDto = ConvertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.getProductById(id));
    redisTemplate.opsForValue().set(String.valueOf(id), genericProductDto);

    return genericProductDto;
}
```
6. If oauth implemented for api call then make it nullable
```java
 public GenericProductDto getProductById(@Nullable  @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") Long id)  throws NotFoundException{
```
