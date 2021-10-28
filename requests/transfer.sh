if [ "$#" -ne 3 ]; then
  echo "Usage: must specify toAccount, fromAccount and the transfer amount" >&2
  exit 1
fi
curl --location --request PUT 'http://localhost:8080/accounts/transfer' \
--form "fromAccountNumber=$1" \
--form "toAccountNumber=$2" \
--form "amount=$3"