# DeutschBank
Test


API to add Trades -

POST - http://localhost:8080/addTrade

Body -
{
    "tradeId":"T2",
    "version":2,
    "counterPartyId":"CP2",
    "bookId":"B2",
    "maturityDate":"2020/04/20",
    "createdDate":"2019/04/20",
    "expired":"N"
}

- Added the trade with TradeId and version as primary key.

Validations -
- If MaturityDate is less than currentDate then 'Maturity Date is less than todays Date' error is given.
- If Trade (T1) with same version will get then it's replaced. (if Trade 'T2' with same version will get then it is added because trade is different).
- If Trade (T1) with lower version will get then exception is thrown. (if Trade 'T1' has maxVersion 3 & T2 has max version 1 
then T2 with version 2 will be added successfully. Because it's handlesd with 'Trade-version' pair).


API to get All Trades -

GET - http://localhost:8080/getAllTrades

- This API returns All added Trades.

Response -
[
    {
        "tradeId": "T2",
        "version": 1,
        "counterPartyId": "CP1",
        "bookId": "B2",
        "maturityDate": "20/05/2020",
        "createdDate": "20/05/2021",
        "expired": "N"
    },
    {
        "tradeId": "T1",
        "version": 3,
        "counterPartyId": "CP1",
        "bookId": "B2",
        "maturityDate": "20/05/2020",
        "createdDate": "20/05/2021",
        "expired": "N"
    }
]
