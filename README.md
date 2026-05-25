# MicroservicesExample
  #Account Service
  Added dependencies Spring Web ,lombok,dev tools, Spring data JPA , h2 database,Eureka Discovery Client,Config Client , Openfeign, Resilience4J,Actuator
  #Customer Service
  Added dependencies Spring Web ,lombok,dev tools, Spring data JPA , h2 database,Eureka Discovery Client,Config Client , Openfeign, Resilience4J,Actuator
  #Eureka Server
  Added dependencies Eureka Server
  #Config Server
  Added dependencies Config Server

Git Commands
git --version
git init
git remote add origin https://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@github.com/AmrutaKalaburgi16/MicroservicesExample.git 
git remote -v
git branch
git branch -m main
git status
git checkout main
git fetch origin main
git ls-files
git add AccountService
git ls-files
git commit -m "Account service added"
git pull origin main --allow-unrelated-histories
git push origin main
git add CustomerService
git add ConfigServer
git add EurekaServer
git commit -m " Added Customer service;Config server;Eureka Server" 
git push origin main
git add README.md
git add AccountService
git add CustomerService
git add ConfigServer
git add EurekaServer
git commit -m "Account Service and Customer service updated"
git push origin main
git add SpringCloudGateway

Issues:
1)java.lang.NullPointerException: Cannot invoke "com.example.microservices.AccountService.Repository.AccountRepo.findAll()"
Solution : @Autowired
class AccountService {

    private AccountRepo accountRepo;  // ❌ not initialized

    public List<Account> getAllAccounts() {
        return accountRepo.findAll(); // 💥 NullPointerException
    }
}

NullPointerException here =
Spring didn't inject bean → field is null
2)StaleObjectStateException ✅
Postman i passed ID also
DB generates ID ✅
You should NOT pass it ❌

3)Cannot map `null` into type `int`
→ HttpMessageNotReadableException
→ 400 BAD REQUEST

Solution:
private int id;        // ❌ primitive
private int balance;   // ❌ primitive
null → int ❌ not allowed
private Long id;   // ✅ use wrapper

int → Integer  
double → Double  
long → Long  
Example

localhost:8080/account/addAccount
{
"id": 1,
"accountNumber": "9611315919",
"accountType": "Savings",
"balance": 300.0
}
{
"accountNumber":"987654321",
"accountType":"Savings",
"balance":400
}
{
"accountNumber": "9886043076",
"accountType": "Savings",
"balance": 1000.0
}
localhost:8081/customer/addcustomer
{
"name":"Amruta",
"age":25,
"gender":"female",
"email":"kalaburgi@amruta@gmail.com",
"phoneNumber":"9886043076",
"city":"Gadag"
}

{
"name":"Anu",
"age":24,
"gender":"female",
"email":"kalaburgi@anu@gmail.com",
"phoneNumber":"8904878787",
"city":"Gadag"
}
{
"name":"Akshata",
"age":20,
"gender":"female",
"email":"kalaburgi@akshu@gmail.com",
"phoneNumber":"1234554321",
"city":"Gadag"
}

What happens if both have same property?
👉 Priority order:
Git Config (HIGH priority ✅)
↓
Local application.properties

✅ So Git value overrides local

CustomerService
├── application.properties   ✅ (minimal config)

Config Repo (GitHub)
├── CustomerService.properties ✅ (all main config)
├── AccountService.properties

Why we need FeignClient or restTemplate when we can get data AccountDetails in getCustomers method of CustomerService by using @OneToOne mapping?
JPA @ManyToOne works only within the same database
So if someone adds a customer via POST localhost:8081/customer/addcustomer, that data lives in CustomerService's DB. AccountService has no idea it exists.
Feign Client / RestTemplate works across separate databases
AccountService doesn't store customer data at all — it asks CustomerService for it at runtime. One source of truth.
JPA @ManyToOne is perfect for a single Spring Boot app (monolith). Once you split into microservices with separate databases, you must use Feign/RestTemplate/WebClient to communicate between them.


List<Account> list = accounts.stream().toList();

list.add(new Account());  // ❌ Runtime Exception


List<Account> list = accounts.stream()
.collect(Collectors.toList());

list.add(new Account());  // ✅ Works
Optional<Account> optional = accountRepo.findById(id);
Optional is a container object that may or may not contain a value.
Account OR no value (null)

GatewayIssue:
localhost:8765/accountservice/account/getAccounts
{
"timestamp": "2026-05-24T03:44:04.579Z",
"status": 404,
"error": "Not Found",
"path": "/accountservice/account/getAccounts"
}
because:
The pom has gateway-server-webmvc but the properties use spring.cloud.gateway.discovery.locator which only works with the reactive gateway. 
Since you have the MVC gateway, fix the properties to use spring.cloud.gateway.mvc.routes:

Root cause — you have two different gateways and they need different config:

gateway-server-webmvc ← you have this	spring-cloud-starter-gateway
Type	MVC (servlet)	Reactive (WebFlux)
Discovery locator	❌ Not supported	✅ Supported
Route config key	spring.cloud.gateway.mvc.routes	spring.cloud.gateway.routes

Issue:
404 Not Found when accessing via gateway

lb://AccountService  →  Needs spring-cloud-loadbalancer to look up
"AccountService" in Eureka registry

http://localhost:8080 →  Goes directly, no discovery needed
add the spring-cloud-starter-loadbalancer dependency to the pom for when we switch back to lb://

Still issue exists:
New Version 2025.1.1
spring.cloud.gateway.server.webmvc.routes

old config:
spring.cloud.gateway.mvc.routes


AuthService:

first I created User repo and user entity 
we need save username and password in db  and password should 
be encrypted for security reasons so i have used BCryptPasswordEncoder to encrypt the password before saving it to the database.
Authconfig define passwordEncoder

Written postmapping to save/register user

created JWT Service for generating token and validating token

Auth controller and auth service will call these methods to register , generate token and validate Token

SecurityConfig class to configure spring security and allow access to /auth/register 
auth/token , auth/validate without authentication and secure other endpoints

Generate token using secret and jwts

If i pass incorrect credentials to /auth/token i will get 401 Unauthorized error 
and if i pass correct credentials i will get token in response
i added authentication manager to authenticate the user and generate token if authentication is successful








