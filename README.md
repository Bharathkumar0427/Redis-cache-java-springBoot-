Redis Cache is nothing but a Cache Management feature offered by Redis. Redis is normally used as a cache to store repeatedly accessed data in memory so that the user can feel the better performance of the application. The Redis Cache offers various features like how long you want to keep data, and which data to remove first, and some other bright caching models.

Like any other Caching Technique, Redis Cache also minimizes the number of network calls made by your application, which in return improves  performance of the application as a whole. One request from an application to the DB is similar to one network call. We can also achieve the better scaling once we apply any caching mechanism in the application as the database can serve more calls in this case.


Steps to start redis server
1.Download the redis server through this link https://github.com/tporadowski/redis/releases and unzip
2.Open the command prompt in the folder location and run this command redis-server --port 6380 --slaveof 127.0.0.1 6379
3.Then redis server will start to check whether the server is successfully started or not
4.open redis-cli and type the command ping ,if server started successfully it return a pong message

Steps to implement redis server in SpringBoot
1.create a project in spring initializr and add the below dependencies

                <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>

		</dependency>

2.And add the configuration in properties file


spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/{your db name}
spring.datasource.username=
spring.datasource.password=
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true


server.port=8090



spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.cache.type=redis
spring.cache.redis.cache-null-values=true


3.Add @EnableCaching  in the starter class
4.Create a Entity class,Repository interface and service interface and service Implementaion class
5.we have used the cache annotation listed below


@Cacheable is used to fetch (retrieve) data from the DB to the application and store in Redis Cache. We apply it on the methods that get (retrieve) data from DB. @Cacheable requires a return value of the method that adds or updates data in the cache.


@CachePut 
We use @CachePut in order to update data in the Redis Cache while there is any update of data in DB. We apply it on the methods that make modifications in DB.

@CacheEvict 
We use @CacheEvict in order to remove the data in the Redis Cache while there is any removal of data in DB. We apply it on the methods that delete data from DB. It can be used with void methods.

6.kindly check the code implemented in the project



