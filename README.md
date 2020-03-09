##### Installation

```
$ git clone git@github.com:Snegirekk/callcenter.git
$ cd callcenter
$ docker-compose up -d
```

Service now is available on `0.0.0.0:8808`

##### Endpoints

* POST /api/v1/task - Create a new task for the call center

    Request body:
    
 ```json
{
  "order_id": "f24dbcc7-9fe4-4d9d-b329-0b6abfb1c64a"
}
```

* GET /api/v1/task - List existing tasks

    Query parameters:
    * fromDate (yyyy-mm-dd) _required_
    * toDate (yyyy-mm-dd) _required_
    * orderNumber (eight-digits string) _optional_