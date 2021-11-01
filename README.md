

# Acme-Bank
You can run the API from the root with
```sh
$ mvn spring-boot:run
```
Please run the following to populate the db with data.
```sh
$ sh requests\create_all.sh
```

All other requests described below can be executed like
```sh
$ sh requests\<script_name>.sh
```
NOTE : if python installed you can run the following to prettify the output
```sh
$ sh requests\<script_name>.sh | python -mjson.tool
```

| script_name | Description | Type |
| ------ | ------ |------ |
| create_all | creates both accounts *1234567* *88888888*| ADMIN
| get_balance_12345678 | gets balance of *12345678*| REQUIREMENT
| get_balance_88888888 | gets balance of *88888888*| REQUIREMENT
| transfer_500000_12345678_88888888 | transfers 500,000HKD from *1234567* to *88888888*| REQUIREMENT
| transfer_0 | try to transfer 0 amount| BAD_REQUEST
| transfer_to_same_account | try to transfer to same account number| BAD_REQUEST
| transfer_invalid_from_account_number | invalid FROM account number| BAD_REQUEST
| transfer_invalid_to_account_number | invalid TO account number| BAD_REQUEST
| transfer_not_two_decimal_places| transfer amount that is not two decimal places| BAD_REQUEST 
| get_account_not_exist.| get account that doesnt exist | NOT_FOUND
| insufficient_funds | try to transfer with insufficient funds | BAD_REQUEST



### Endpoints

The following documents the API endpoints with payloads. Please note that for getting an account balance the account number should be passed in a POST request as it contains sensitve information. But for the purpose of simplicity I left it in a GET request;
<table>
<tr>
<th>
TYPE
</th>
<th>
URL
</th>
<th>
REQUEST
</th>
<th>
RESPONSE
</th>
</tr>

<tr>

<td>
<pre>
<br/>GET<br/>
</pre>
</td>



<td>
<pre>
/accounts
</pre>
</td>
<td>
<pre>
?accountNumber={accountNumber}
</pre>
</td>
<td>
<pre>
{
    "accountNumber": int,
    "balance": double,
    "currency": string
}
</pre>
</td>

</tr>

<tr>

<td>
<pre>
<br/>PUT<br/>
</pre>
</td>



<td>
<pre>
/accounts/transfer
</pre>
</td>
<td>
<pre>
{
    "fromAccountNumber": int,
     toAccountNumber": int,
    "amount": double,
}
</pre>
</td>
<td>
<pre>
[
    {
        "accountNumber": int,
        "balance": double,
        "currency": string
    },
    {
        "accountNumber": int,
        "balance": double,
        "currency": string
    }
]
</pre>
</td>



</tr>

</tr>

<tr>

<td>
<pre>
<br/>ERROR<br/>
</pre>
</td>



<td>
<pre>
/accounts
/accounts/transfer
</pre>
</td>
<td>
<pre>
Please refer to test scripts
</pre>
</td>
<td>
<pre>
{
    "timestamp": "2019-01-17T16:12:45.977+0000",
    "status": 4xx / 5xx,
    "error": BAD_REQUEST/NOT_FOUND/INTERNAL
    "message": "error message",
}
</pre>
</td>



</tr>
