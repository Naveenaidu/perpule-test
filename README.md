# Instructions
---------------
Please find below the instructions on how to run the assignment. Also please find the war file for the project.

**_Note_** : 
* Please use Postman or RESTED client extension for the post/get request
* The index URL to the assignment is: https://perpule-test-256018.appspot.com/

-----------------------
### 1. Authentication
----------------------
We'll pass the username and password here to fetch the Token. This token would then be used to access the other rest services.
This is a POST method

**Rest Endpoint**: https://perpule-test-256018.appspot.com/webapi/authentication

**Request Type**: POST

**Headers**:
```
	Content-Type : application/json
```

**Request Body**:
_Please choose the type as JSON_
```
    username: admin
    password: password
```

-----------------------
### 2. Get all Customers
----------------------
We can use this service to fetch all the customers in the database. This is secured. So we need to pass the token key to access the REST service.

For the test of Security - Ignore the token field when sending the request or Just type some random numbers such as `123ih2ien` in token filed. You'll see that you
won't be able to access it. 

**Rest Endpoint**: https://perpule-test-256018.appspot.com/webapi/customers

**Request Type**: GET

**Headers**:
```
	Content-Type : application/json
	Token : < the token you get from the authentication mode >
```

-----------------------
### 3. Get Customer by ID
----------------------
We can use this service to fetch the customers in the database using the ID. This is secured. So we need to pass the token key to access the REST service

For the test of Security - Ignore the token field when sending the request or Just type some random numbers such as `123ih2ien` in token filed. You'll see that you
won't be able to access it.  

**Rest Endpoint**: `https://perpule-test-256018.appspot.com/webapi/get-customer/<id_number>`

**Eg**: https://perpule-test-256018.appspot.com/webapi/get-customer/2

**Request Type**: GET

**Headers**:
```
	Content-Type : application/json
	Token : < the token you get from the authentication mode >
```

-----------------------
### 4. Add a customer to data base
----------------------
We can use this service to add the customers in the database. This is secured. So we need to pass the token key to access the REST service.

The table of Customer has the following fields:
1. id
2. firstName
3. lastName
4. address
5. city
6. phone

**Rest Endpoint**: https://perpule-test-256018.appspot.com/webapi/add-customer

**Request Type**: POST

**Headers**:
```
	Content-Type : application/json
```

**Request Body**:
_Please choose the type as JSON_
```

    id:        < Any ID greater than 7 because of excisting records >
    firstName: < First Name of Customer >
    lastName:  < Last Name of Customer >
    address:   < Address of Customer >
    city:      < City of Customer >
    phone:     <  Phone of Customer > (This is of the type int)
```

-----------------------------------------------------
This marks the end of the assignment. 

Please do let me know if there you discover any bugs in the assignment. It will really help me decipher my thought process and would let me probe into the reasons of that bug. 

And since this was my first REST Application - It would also help me understand my rookie mistakes.
