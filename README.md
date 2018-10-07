# classificationbox-java

[Machine Box][0] [Classificationbox][1] Client Library in Java (using [Retrofit2][2])

### Installation

```java
maven artifact coming soon
```

### Usage 

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

[0]: https://machinebox.io/
[1]: https://machinebox.io/docs/classificationbox
[2]: https://square.github.io/retrofit/