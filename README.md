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
"id": 2,
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