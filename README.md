# Acme-Bank
You can run the API from the root with
```sh
$ mvn spring-boot:run
```
Please run the following to populate the db with data.
```sh
$ sh requests\create_all.sh.sh
```

All other requests described below can be executed like
```sh
$ sh requests\<script_name>.sh
```
NOTE : if python installed you can run the following to prettify the output
```sh
$ sh requests\<script_name>.sh | python -mjson.tool
```

| Script | Description | Type |
| ------ | ------ |------ |
| get_balance_12345678.sh | gets balance of *12345678*| REQUIREMENT
| get_balance_88888888.sh | gets balance of *88888888*| REQUIREMENT
| transfer_500000_12345678_88888888.sh | transfers 500,000HKD from *1234567* to *88888888*| REQUIREMENT
| create_all.sh | creates both accounts *1234567* *88888888*| ADMIN
| transfer_0.sh | try to transfer 0 amount| BAD_REQUEST
| transfer_not_two_decimal_places.sh | try to transfer amount that is not two decimal places| BAD_REQUEST
| transfer_invalid_from_account_number | invalid FROM account number| BAD_REQUEST
| transfer_invalid_to_account_number | invalid TO account number| BAD_REQUEST
| transfer_invalid_to_account_number | invalid TO account number| BAD_REQUEST 
| insufficient_funds.sh | try to transfer with insufficient funds | BAD_REQUEST
| get_account_not_exist.sh | get account that doesnt exist | NOT_FOUND

### Requests

The following documents the API's requests and responses with payloads. Please note that for getting an account balance the account number should be passed in a POST request as it contains sensitve information. But for the purpose of simplicity I left it in a GET request;
 ```sh
 GET http://localhost:8080/accounts?accountNumber={accountNumber} 
 ```
 RESPONSE
```sh{
{
    "accountNumber": <int>,
    "balance": <double>,
    "currency": <string>
}
```

 ```sh
 PUT http://localhost:8080/accounts/transfer
 ```
REQUEST
```sh{
{
    "fromAccountNumber": <int>,
     toAccountNumber": <int>,
    "amount": <double>,
}
```
RESPONSE
```sh[
    {
        "accountNumber": <int>,
        "balance": <double>,
        "currency": <string>
    },
    {
        "accountNumber": <int>,
        "balance": <double>,
        "currency": <string>
    }
]
```