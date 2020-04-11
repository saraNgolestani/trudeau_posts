# trudeau_posts
This application would crawl into [CNN](https://edition.cnn.com/search/) and [Twitter](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/intro-to-tweet-json) in order to find last 25 posts and news of J. Trudeau and displays them.
the webdesigne of this app would be refreshed every 5minutes in order to fetch the most recent contents.

# Features:
 The coolest feature of this app is that it can analysis the tonality of the text using the GET API of [IBM ToneAnalyzer](https://tone-analyzer-demo.ng.bluemix.net/?_ga=2.184625833.1243136591.1586555423-1644152405.1586250967).

This feature can help us imidiately undrestand the semantic of te content we are about to read, and in periods like the Coronaviruse outbreak, this feature can help us to go strait to news and tweets which has brought "Fear" to our governers.

> This analyser would detect the tonality of the news based on their first three sentences, and also tweets based on their first three sentences (if they are more than 3 sentences).
> In the case of not detectinf the exact tonality, the "MixedFeeling" would appear.



 The search query of CNN news and username of twitter can be manual.
> - localhost:8080/content/{query}/cnn
> - localhost:8080/content/{username}/twitter


 Also the number of posts that are being fetched can be manual to and be sent as a @PathValue.
> - localhost:8080/content/cnn/{maxPosition}
> - localhost:8080/content/twitter/{maxPosition}
> - localhost:8080/content/{query}/cnn/{maxPosition}
> - localhost:8080/content/{username}/twitter/{maxPosition}

