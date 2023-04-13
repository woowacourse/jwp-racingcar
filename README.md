# jwp-racingcar

## Request

```http request
POST /plays HTTP/1.1
content-type: application/json; charset=UTF-8
host: localhost:8080

{
    "names": "브리,토미,브라운",
    "count": 10
}
```

## Response

```http request
HTTP/1.1 200 
Content-Type: application/json

{
    "winners": "브리",
    "racingCars": [
        {
            "name": "브리",
            "position": 9
        },
        {
            "name": "토미",
            "position": 7
        },
        {
            "name": "브라운",
            "position": 3
        },
    ]
}
```

