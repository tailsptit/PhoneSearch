# PhoneSearch

## To get source code from  GitHub
  - git clode https://github.com/tailsptit/PhoneSearch.git

## Build project
  - mvn package
 
## Run project
  ### To run PhoneSearch server without inserting customer data
      java -jar target/PhoneSearch-1.0-SNAPSHOT.jar
  ### To run PhoneSearch server inserting customer data
      java -jar target/PhoneSearch-1.0-SNAPSHOT.jar X

      In which, X should be an integer with value from 1 to 9
      X = 1 : Create 10           customers with phone number from 84123456780 to 84123456789
      X = 2 : Create 100          customers with phone number from 84123456700 to 84123456799
      X = 3 : Create 1.000        customers with phone number from 84123456000 to 84123456999
      X = 4 : Create 10.000       customers with phone number from 84123450000 to 84123459999
      X = 5 : Create 100.000      customers with phone number from 84123400000 to 84123499999
      X = 6 : Create 1000.000     customers with phone number from 84123000000 to 84123999999
      X = 7 : Create 10.000.000   customers with phone number from 84120000000 to 84129999999
      X = 8 : Create 100.000.000  customers with phone number from 84100000000 to 84199999999
      X = 9 : Create 1000.000.000 customers with phone number from 84000000000 to 84999999999
      

## Test 
 ### Create a customer using CREATE API using curl command
   - Using command:
   curl -H "Content-Type: application/json" -d"{\"name\":\"value\", \"phone\":\"value\"}" localhost:8102/customers/create/

   - Example:
   curl -H "Content-Type: application/json" -d"{\"name\":\"TaiLS\", \"phone\":\"123456789129\"}" localhost:8102/customers/create/


### Get customers with prefix using curl command
  - Using command:
    curl localhost:8102/customers/search/phone/{PhonePrefix}
    
  - Example:
      curl localhost:8102/customers/search/phone/TaiLS

