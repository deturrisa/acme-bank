curl --location --request POST 'http://localhost:8080/accounts' \
--form 'accountNumber=12345678' \
--form 'balance=1000000' \
--form 'currency=HKD'

curl --location --request POST 'http://localhost:8080/accounts' \
--form 'accountNumber=88888888' \
--form 'balance=1000000' \
--form 'currency=HKD'