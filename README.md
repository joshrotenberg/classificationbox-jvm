# classificationbox-java

[Machine Box][0] [Classificationbox][1] Client Library in Java (using [Retrofit2][2])

### Installation

```java
maven artifact coming soon
```

### Overview

`classificationbox-java` is a Java/JVM client binding for Classificationbox using Retrofit. It is overall a fairly
low level implementation, meaning that it essentially exposes all of the available REST API endpoints as methods 
with POJO request/response classes where appropriate and otherwise stays out of your way. 

This implementation grew out of small personal project and is not sponsored or supported directly by Machine Box. 

Issues, pull requests, and suggestions are encouraged and appreciated.

### Usage 

#### Run Classificationbox

It's easy! [Sign up][3] and you can run the box locally with docker in just a few minutes!

#### Create a new client and create a model
```java
// create a client. by default, the client points to localhost:8080 without basic authentication
ClassificationBoxClient defaultClient = ClassificationBoxClient.defaultClient();

// create a list of classes
List<String> classes = new ArrayList<String>();
classes.add("class1");
classes.add("class2");
classes.add("class3");

CreateModelRequest cmr = new CreateModelRequest("sentimentModel", classes)
                .withId("sentiment1")
                .addOption(ModelOption.NGRAMS, 1)
                .addOption(ModelOption.SKIPGRAMS, 1);

Response<CreateModelResponse> response = defaultClient.service.createModel(cmr).execute();
if(response.isSuccessful() && response.body().getSuccess()) {
    System.out.printf("hooray! %s\n", response.body().getName());
}
```

#### Teach the model
```java
TeachModelRequest tmr = new TeachModelRequest("class1");
tmr.addInput(new ModelInput("user_age", FeatureType.NUMBER, "25"));
tmr.addInput(new ModelInput("user_interest", FeatureType.LIST, "music,cooking,ml"));
tmr.addInput(new ModelInput("user_location", FeatureType.KEYWORD, "London"));

Response<TeachModelResponse> response = getClient().service().teachModel("sentiment1", tmr).execute();

```

See the [integration tests](src/test/java/integration) for more examples.

### TODO

- [ ] Test error cases 
- [ ] Test Request/Response POJOs
- [ ] Document Request/Response POJOs
- [x] Handle `/info`, `/healthz`, `/liveness`, `/readyz` endpoints
- [ ] Handle alternate state upload methods 
- [ ] CI/CD
- [ ] Small sample application

### License

MIT License

Copyright (c) 2018 josh rotenberg

Please see the [LICENSE](LICENSE) for more information.

[0]: https://machinebox.io/
[1]: https://machinebox.io/docs/classificationbox
[2]: https://square.github.io/retrofit/
[3]: https://machinebox.io/login?return_url=%2Faccount
