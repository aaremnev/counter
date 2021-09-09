[![Build Status](https://travis-ci.com/aaremnev/counter.svg?branch=master)](https://travis-ci.com/aaremnev/counter)


# Word Counter Service - develop


### Building and starting application

`mvn spring-boot:run`

### Uploading files

You can use html form to upload files:
* navigate to: `localhost:8080/upload.html`

### REST operations

* `POST localhost:8080/file` - upload file (with **file** parameter as content)
* `GET localhost:8080/file` - list names of all uploaded files
* `GET localhost:8080/counter` - get aggregated word stats for all loaded files 
* `GET localhost:8080/counter/{name}` - get word stats by file name
